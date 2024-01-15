package Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Help extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("help")){

            event.reply("```This is a Timer Bot" +
                    "\n\nCommands:" +
                    "\n\n/settimer: Sets a timer using a user provided number of hours, minutes, and seconds" +
                    "\n\tREQUIRED: Hours \n\t\t" +
                            "Range: 0 - 24" +
                    "\n\tREQUIRED: Minutes \n\t\t" +
                            "Range: 0 - 60" +
                    "\n\tREQUIRED: Seconds \n\t\t" +
                            "Range: 0 - 60" +
                    "\n\tNOTE: This DOES NOT start the timer" +
                    "\n\tNOTE: If /settimer is used while a timer is currently running, the current timer will end" +
                    "\n\n/starttimer: Starts the timer created with the /settimer command" +
                    "\n\tOPTIONAL: A specific role can be chosen to be mentioned when the timer ends" +
                    "\n\tDEFAULT: Mentions the user who started the timer when the timer ends" +
                    "\n\tNOTE: Timers can be restarted with this command" +
                    "\n\t\t- If a role was specified it must be specified again" +
                    "\n\n/pausetimer: Pauses a currently running timer" +
                    "\n\n/resumetimer: Resumes a timer paused with /pausetimer" +
                    "\n\n/stoptimer: Stops the currently running timer" +
                    "\n\tNOTE: You cannot use /resume on a stopped timer" +
                    "\n\n/timeleft: Returns how much time is left until the timer goes off" +
                    "\n\tNOTE: This command works on timers paused with /pausetimer as well" +
                    "\n\n/help: The command you are using```")
                    .queue();
        }
    }
}
