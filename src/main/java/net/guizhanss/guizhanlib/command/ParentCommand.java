package net.guizhanss.guizhanlib.command;

import com.google.common.base.Preconditions;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A {@link ParentCommand} can contain several {@link SubCommand}s.
 *
 * @author ybw0014
 */
@ParametersAreNonnullByDefault
public class ParentCommand extends SubCommand {
    private final Map<String, SubCommand> subCommands = new HashMap<>();

    protected ParentCommand(String name, String description, boolean isOp) {
        super(name, description, isOp);
    }

    protected ParentCommand(String name, String description, String permission) {
        super(name, description, permission);
    }

    protected ParentCommand(String name, String description) {
        super(name, description);
    }

    @Nonnull
    public final ParentCommand addSub(@Nonnull SubCommand command) {
        Preconditions.checkArgument(command != null, "sub command cannot be null");
        if (command == this) {
            throw new IllegalArgumentException("'" + command.getName() + "' cannot be added to itself!");
        }
        if (command == getParent()) {
            throw new IllegalArgumentException("Parent command '" + command.getName() + "' cannot be added to child " + getName());
        }
        subCommands.compute(command.getName(), (name, cmd) -> {
            if (cmd != null) {
                throw new IllegalArgumentException("Duplicate command '" + command.getName() + "' cannot be added to " + getName());
            }
            command.setParent(this);
            return command;
        });
        return this;
    }

    protected void execute(CommandSender sender, String[] args) {
    }

    protected void complete(CommandSender sender, String[] args, List<String> completions) {
    }
}
