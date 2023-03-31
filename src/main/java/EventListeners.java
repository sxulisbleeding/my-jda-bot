
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventListeners extends ListenerAdapter {


    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {

       User user = event.getUser();
       String emoji = event.getReaction().getEmoji().getAsReactionCode();
        String channelMention = event.getChannel().getAsMention();
        String jumpLink = event.getJumpUrl();
        String message = user.getAsTag() + " определил свою страну как: " + emoji + " в " + channelMention +
                " канале!";
        event.getGuild().getDefaultChannel().asStandardGuildMessageChannel().sendMessage(message).queue();

    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (message.contains("!Гойда!!!")){
            event.getChannel().sendMessage("Гойда братья и сестра!!! Гойдаааа!").queue();

        }
    }

}
