package net.guizhanss.guizhanlib.command;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.bukkit.command.CommandSender;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.Predicate;

/**
 * A {@link SubCommand}.
 *
 * @author ybw0014
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("ConstantConditions")
public abstract class SubCommand {

    @Getter
    private final String name;
    @Getter
    private final String description;

    private Predicate<CommandSender> permission;
    @Getter
    private ParentCommand parent;

    protected SubCommand(String name, String description, boolean isOp) {
        Preconditions.checkArgument(name != null, "name cannot be null");
        Preconditions.checkArgument(description != null, "description cannot be null");

        this.name = name;
        this.description = description;
        this.permission = isOp ? CommandSender::isOp : sender -> true;
    }

    protected SubCommand(String name, String description, String permission) {
        Preconditions.checkArgument(name != null, "name cannot be null");
        Preconditions.checkArgument(description != null, "description cannot be null");

        this.name = name;
        this.description = description;
        this.permission = sender -> sender.hasPermission(permission);
    }

    protected SubCommand(String name, String description, Predicate<CommandSender> permission) {
        Preconditions.checkArgument(name != null, "name cannot be null");
        Preconditions.checkArgument(description != null, "description cannot be null");
        Preconditions.checkArgument(permission != null, "permission cannot be null");

        this.name = name;
        this.description = description;
        this.permission = permission;
    }

    protected SubCommand(String name, String description) {
        this(name, description, false);
    }

    protected void setParent(ParentCommand parent) {
        Preconditions.checkArgument(parent != null, "parent command cannot be null");
        this.parent = parent;
    }

    protected void setPermission(boolean isOp) {
        this.permission = isOp ? CommandSender::isOp : sender -> true;
    }

    protected void setPermission(String permission) {
        this.permission = sender -> sender.hasPermission(permission);
    }

    protected boolean checkPermission(CommandSender sender) {
        Preconditions.checkArgument(sender != null, "command sender cannot be null");
        return permission.test(sender);
    }

    protected abstract void execute(CommandSender sender, String[] args);

    protected abstract void complete(CommandSender sender, String[] args, List<String> completions);

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SubCommand && ((SubCommand) obj).name.equals(name);
    }
}
