import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import javax.security.auth.login.LoginException;
public class Bot {
    private final ShardManager shardManager;
    public Bot() throws LoginException {
        String token = "MTA0MDI0NjQxNzEwNjY4MTk0Nw.GBBgRd.p5aXcr4V2SAEvOGlcdGP_51eraXOZXjT322_Us";
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token).
                enableIntents(GatewayIntent.MESSAGE_CONTENT);

        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.setActivity(Activity.competing("รรฬส"));

        shardManager = builder.build();

        shardManager.addEventListener(new EventListeners(), new CommandManager());

    }

    public ShardManager getShardManager(){
        return shardManager;
    }

    public static void main(String[] args) {

        try {
            Bot bot = new Bot();
        }catch (LoginException e){
            System.out.println("Error");
        }

    }

}

