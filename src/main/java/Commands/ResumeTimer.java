package Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Timer;
import java.util.TimerTask;

public class ResumeTimer extends ListenerAdapter {
    public static long timePaused;

    @Override
    public void onSlashCommandInteraction(final SlashCommandInteractionEvent event) {
        if(event.getName().equals("resumetimer")){
            if(SetTimer.getIsStopped()){
                event.reply("The timer has been stopped and cannot be resumed").queue();
            } else if(SetTimer.getIsPaused()) {
                //shows other commands the timer isn't finished and isn't paused anymore
                SetTimer.isFinished = false;
                SetTimer.isPaused = false;

                //stops the stopwatch in PauseTimer
                PauseTimer.watch.stop();

                //this is the amount of time the timer was paused
                timePaused = PauseTimer.watch.getTime();

                //resets the watch for later use
                PauseTimer.watch.reset();

                //creates a new timer
                StartTimer.timer = new Timer();

                //this is the time left for the timer to run
                long timeResume = TimeLeft.getTimeLeftMillis();

                //creates a new task
                TimerTask task2 = new TimerTask() {
                    @Override
                    public void run() {
                        if(StartTimer.option != null){
                            String role = StartTimer.option.getAsRole().getAsMention();
                            event.getChannel().sendMessage(role + " timer has ended").queue();
                            TimeLeft.totalPause = 0;
                            SetTimer.isFinished = true;
                        } else {
                            event.getChannel().sendMessage(event.getUser().getAsMention() + " timer has ended").queue();
                            TimeLeft.totalPause = 0;
                            SetTimer.isFinished = true;
                        }
                    }
                };
                //timer schedule
                StartTimer.timer.schedule(task2, timeResume);

                //math to get the amount of time in seconds, minutes, and hours
                int timeResumeSeconds = (int) TimeLeft.getTimeLeftMillis() / (1000);
                int secondsResume = timeResumeSeconds % (60);
                int minutesResume = (timeResumeSeconds / 60) % 60;
                int hoursResume = (timeResumeSeconds / 3600);
                event.reply("Timer Resumed For:\n" + hoursResume + " hours\n" + minutesResume + " minutes\n" + secondsResume + " seconds").queue();
            } else {
                event.reply("A timer has not been paused").queue();
            }
        }
    }
}
