package net.guizhanss.guizhanlib.command;

import com.google.common.base.Preconditions;
import org.bukkit.command.Command;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class PluginCommand extends ParentCommand {

    private final Command command;

    public PluginCommand(Command command, String description, boolean isOp) {
        super(command.getName(), description, isOp);
        this.command = command;
    }

    public PluginCommand(Command command, String description, String permission) {
        super(command.getName(), description, permission);
        this.command = command;
    }

    public PluginCommand(Command command, String description) {
        super(command.getName(), description);
        this.command = command;
    }
}
