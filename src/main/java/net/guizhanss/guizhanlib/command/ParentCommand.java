package net.guizhanss.guizhanlib.command;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    protected void execute(CommandSender sender, String[] args) {
    }

    protected void complete(CommandSender sender, String[] args, List<String> completions) {
    }
}
