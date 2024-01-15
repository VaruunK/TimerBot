package Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.lang3.time.StopWatch;

public class PauseTimer extends ListenerAdapter {
    public static long timePaused;
    public static StopWatch watch;
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("pausetimer")){
            //if the timer is already paused
            if(SetTimer.getIsPaused()) {
                event.reply("Timer is already paused").queue();
                //if the timer length is 0 and a timer hasn't been set
                //if the timer hasn't yet been created(redundant?)
                //if the timer has been stopped
                //if the timer has already finished
                //if the timer hasn't been started
            } else if(SetTimer.timerLength == 0 || StartTimer.timer == null || SetTimer.getIsStopped() || SetTimer.getIsFinished() || !SetTimer.getIsStarted()) {
                event.reply("There is no timer to pause").queue();
            } else {
                //creates a stopwatch to count the time between when the timer is paused and when it is resumed
                watch = new StopWatch();
                watch.start();

                //the time the timer was pasued
                timePaused = System.currentTimeMillis();

                //sets is paused to true to show other commands the timer is paused
                SetTimer.isPaused = true;

                //stops the timer
                StartTimer.timer.cancel();
                event.reply("Timer paused").queue();
            }
        }
    }
}
