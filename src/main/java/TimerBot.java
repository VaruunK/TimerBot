import Commands.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class TimerBot extends ListenerAdapter {
    //creates the discord JDA
    public static JDA buildjda() throws InterruptedException {
        return JDABuilder.createDefault("MTA5NTQxNzA4OTU5MTg4MTc4OA.G-Lb9u.EpA_fc6SePqRzkAM-xPnc1xXATzJA9-T-bVL-w")
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES)

                //activity
                .setActivity(Activity.listening("/help"))

                //event listeners
                .addEventListeners(new SetTimer())
                .addEventListeners(new StartTimer())
                .addEventListeners(new ResumeTimer())
                .addEventListeners(new PauseTimer())
                .addEventListeners(new StopTimer())
                .addEventListeners(new TimeLeft())
                .addEventListeners(new Help())
                .build().awaitReady();
    }

    public static void main(String args[]) throws InterruptedException {
        //builds the JDA
        JDA jda = buildjda();

        Guild guild = jda.getGuildById("541291577566429201");

        if(guild != null) {
            //commands
            jda.upsertCommand("settimer", "set a timer")
                    .addOptions(
                            new OptionData(OptionType.INTEGER, "hours", "how many hours", true)
                                    .setRequiredRange(0, 24),
                            new OptionData(OptionType.INTEGER, "minutes", "how many minutes", true)
                                    .setRequiredRange(0, 60),
                            new OptionData(OptionType.INTEGER, "seconds", "how many seconds", true)
                                    .setRequiredRange(0, 60))
                    .queue();

            jda.upsertCommand("starttimer", "start a preset timer")
                    .addOption(
                            OptionType.ROLE, "role", "who to mention when the timer ends", false)
                    .queue();

            jda.upsertCommand("stoptimer", "stops a timer from running")
                    .queue();

            jda.upsertCommand("pausetimer", "pauses a timer")
                    .queue();

            jda.upsertCommand("resumetimer", "resumes a timer that was paused")
                    .queue();

            jda.upsertCommand("timeleft", "gets the time left")
                    .queue();

            jda.upsertCommand("help", "gives basic information on the bot")
                    .queue();
        }
    }
}