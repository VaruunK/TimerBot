package Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class StopTimer extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("stoptimer")){
            //if the timer length has been set, the timer is not finished, the timer isn't stopped, and the timer has been started
            if((SetTimer.timerLength != 0) && !SetTimer.getIsFinished() && !SetTimer.getIsStopped() && SetTimer.isStarted){

                //sets isStopped to true to show other commands the timer is stopped
                SetTimer.isStopped = true;
                StartTimer.timer.cancel();
                StartTimer.task.cancel();

                event.reply("Timer Stopped").queue();
            } else {
                event.reply("There is no timer running").queue();
            }
        }
    }
}
