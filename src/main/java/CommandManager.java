import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
public class CommandManager extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

       String command = event.getName();

       if (command.equals("welcome")) {
           String userTag = event.getUser().getAsTag();
           event.reply("Welcome to the Server **" + userTag + "**!").queue();

       } else if (command.equals("roles")){
           event.deferReply().queue();
           String response = " ";

           for (Role role : event.getGuild().getRoles()){
                response += role.getAsMention() + "\n";
           }

           event.getHook().sendMessage(response).queue();

       }
       else if (command.equals("say")){
            OptionMapping messageOption = event.getOption("message");
            String message = messageOption.getAsString();

            event.getChannel().sendMessage(message).queue();
            event.reply("Your message was sent: ").setEphemeral(true).queue();
       }
       else if (command.equals("giverole")){
           Member member = event.getOption("user").getAsMember();
           Role role = event.getOption("role").getAsRole();

           event.getGuild().addRoleToMember(member, role).queue();
           event.reply(member.getAsMention() + "has been " +
                   "given the " + role.getAsMention() + " role!").queue();
       }
       else if (command.equals("createchannel")){
           Channel channel = event.getChannel().asTextChannel();
           event.getGuild().createTextChannel("channel one").queue();
           event.reply(channel.getAsMention() + " created").queue();

       }

    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {

        List<CommandData> commandData = new ArrayList();

        commandData.add(Commands.slash("welcome", "Get welcome by the bot."));

        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {

        List<CommandData> commandData = new ArrayList();

        commandData.add(Commands.slash("roles", "Display all Roles"));
        commandData.add(Commands.slash("welcome", "Get welcome by the bot."));

        OptionData option1 = new OptionData(OptionType.STRING, "message", "The message you " +
                "want to bot you say", true );

        commandData.add(Commands.slash("say", "Make the bot say a message.").addOptions(option1 ));

        OptionData option4 = new OptionData(OptionType.USER, "user", "the user to give" +
                " the role to", true);

        OptionData option5 = new OptionData(OptionType.ROLE, "role", "the role to" +
                " be given",true);

        commandData.add(Commands.slash("giverole", "Give a user a role").addOptions(option4, option5));

        OptionData option6 = new OptionData(OptionType.CHANNEL, "channel", "create" +
                " channel", true);

        commandData.add(Commands.slash("createchannel", "Creating a channel").addOptions(option6));

        event.getGuild().updateCommands().addCommands(commandData).queue();

    }

}
