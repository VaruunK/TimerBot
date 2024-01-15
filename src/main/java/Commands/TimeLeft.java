package Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class TimeLeft extends ListenerAdapter {
    static long timeLeftMillis;
    public static long totalPause = 0;

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("timeleft")) {
                //if time left is negative no timer was started
                // if the timer is stopped there is no time to get
                // if the timer is not started there is no time to get
                //if the timer is finished there is no time to get
                if (getTimeLeftMillis() < 0 || SetTimer.isStopped || !SetTimer.isStarted || SetTimer.isFinished) {
                    event.reply("A timer has not been started").queue();
                } else {
                    //converts remaining time to int
                    int timeLeftSeconds = (int) getTimeLeftMillis() / (1000);
                    int secondsLeft = timeLeftSeconds % (60);
                    int minutesLeft = (timeLeftSeconds / 60) % 60;
                    int hoursLeft = (timeLeftSeconds / 3600);

                    event.reply("Hours: " + hoursLeft + "\nMinutes: " + minutesLeft + "\nSeconds: " + secondsLeft).queue();
                }
            }
        }

        //method for getting the time left in milliseconds
        public static long getTimeLeftMillis(){
            //gets currenttime in milliseconds
            long currenttime = System.currentTimeMillis();

            //adds the new pause to any previous time the timer was paused
            totalPause = ResumeTimer.timePaused + totalPause;

            //sets the time paused back to 0 afterwards to avoid compounding the time paused
            ResumeTimer.timePaused = 0;

            //if the timer is already paused we want to keep the time left the same
            if(SetTimer.getIsPaused()){
                timeLeftMillis = (SetTimer.timerLength + StartTimer.starttime + totalPause) - (PauseTimer.timePaused);
            } else {
                timeLeftMillis = (SetTimer.timerLength + StartTimer.starttime + totalPause) - (currenttime);
            }
            return timeLeftMillis;
        }
    }