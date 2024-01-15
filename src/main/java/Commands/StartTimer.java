package Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.util.Timer;
import java.util.TimerTask;

public class StartTimer extends ListenerAdapter {
    public static Timer timer;
    public static TimerTask task;
    public static long starttime;
    public static OptionMapping option;

   @Override
    public void onSlashCommandInteraction(final SlashCommandInteractionEvent event) {
       if (event.getName().equals("starttimer")) {

           //sets the timer to not finished for other commands to see
           SetTimer.isFinished = false;

           //checks if timerlength is zero, so it does not start a 0 second timer
           if(SetTimer.timerLength == 0){

               event.reply("A timer has not been set").queue();

           } else {

               //sets isStarted to true to show other commands the timer is started
               //also shows that the timer is not paused or stopped
               SetTimer.isStarted = true;
               SetTimer.isPaused = false;
               SetTimer.isStopped = false;

               //resets any totalPause from a previous timer
               TimeLeft.totalPause = 0;

               //if a timer has already been started, cancel it
               if(timer != null){
                   timer.cancel();
                   task.cancel();
               }

               //gets option from TimerBot
               option = event.getOption("role");

               //creates timer object
               timer = new Timer();

               //gets current time in milliseconds
               starttime = System.currentTimeMillis();

               //Assigns TimerTask
               task = new TimerTask() {
                   @Override
                   public void run() {
                       if(option != null){
                           //gets role as Role and converts to mentionable String
                           String role = option.getAsRole().getAsMention();
                           event.getChannel().sendMessage(role + " timer has ended").queue();
                       } else {
                           event.getChannel().sendMessage(event.getUser().getAsMention() + " timer has ended").queue();
                       }
                       TimeLeft.totalPause = 0;
                       SetTimer.isFinished = true;
                   }
               };
               //timer schedule
               timer.schedule(task, SetTimer.timerLength);

               //math for converting length of timer to seconds, minutes, and hours
               int timeTotalSeconds = SetTimer.timerLength / (1000);
               int seconds = timeTotalSeconds % (60);
               int minutes = (timeTotalSeconds / 60) % 60;
               int hours = (timeTotalSeconds / 3600);

               event.reply("Timer Started For:\n" + hours + " hours\n" + minutes + " minutes\n" + seconds + " seconds").queue();
           }
       }
   }
}