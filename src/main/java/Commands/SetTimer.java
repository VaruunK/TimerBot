package Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;


public class SetTimer extends ListenerAdapter {
    //length of the timer in milliseconds
    public static int timerLength;
    public static boolean isStarted;
    public static boolean isFinished;
    public static boolean isPaused;
    public static boolean isStopped;
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("settimer")){

            //sets the timer to is not started, is not stopped, and is not paused
            isStarted = false;
            isStopped = false;
            isPaused = false;

            //if there is already a timer running, cancel it
            if(StartTimer.timer != null){
                StartTimer.timer.cancel();
                StartTimer.task.cancel();
            }
            //options from TimerBot class
            OptionMapping hours = event.getOption("hours");
            OptionMapping minutes = event.getOption("minutes");
            OptionMapping seconds = event.getOption("seconds");

            //ensures no values are null
            if(hours != null && minutes != null && seconds != null){
                //returns values as ints
                event.reply("Timer Set For:\n" + hours.getAsInt() + " hours\n" + minutes.getAsInt() + " minutes\n" + seconds.getAsInt() + " seconds")
                        .queue();
                //converts given values into milliseconds
                timerLength = (((hours.getAsInt())*(3600 * 1000)) + ((minutes.getAsInt())*(60 * 1000)) + ((seconds.getAsInt())*1000));

            }
        }
    }
    public static boolean getIsStarted(){
        return isStarted;
    }

    public static boolean getIsFinished(){
        return isFinished;
    }

    public static boolean getIsPaused(){
        return isPaused;
    }

    public static boolean getIsStopped(){
        return isStopped;
    }
}
