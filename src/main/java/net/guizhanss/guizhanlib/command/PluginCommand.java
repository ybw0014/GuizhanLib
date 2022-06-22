package net.guizhanss.guizhanlib.command;

import com.google.common.base.Preconditions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a top level command that is registered in plugin.yml.
 *
 * @author ybw0014
 */
@ParametersAreNonnullByDefault
public class PluginCommand extends ParentCommand implements TabExecutor, Listener {

    private static final Map<String, ParentCommand> registeredCommand = new HashMap<>();

    private final Plugin plugin;
    private final String commandName;
    private final Command command;

    public PluginCommand(JavaPlugin plugin, String command) {
        this(
            Preconditions.checkNotNull(plugin, "plugin cannot be null"),
            Preconditions.checkNotNull(plugin.getCommand(command), "command '" + command + "' is not registered in plugin.yml")
        );
    }

    private PluginCommand(JavaPlugin plugin, Command command) {
        super(command.getName(), command.getDescription());

        if (registeredCommand.containsKey(command.getName())) {
            throw new IllegalStateException("The command '" + command + "' has been registered.");
        }

        this.plugin = plugin;
        this.commandName = command.getName();
        this.command = command;

        registeredCommand.put(commandName, this);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
