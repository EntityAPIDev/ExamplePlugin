package org.entityapi.test;

import com.dsh105.command.*;

@Command(
        command = "entity",
        aliases = {"a"},
        description = "Basic entity manipulation",
        permission = "entityapi.test.entity",
        help = {}, // TODO
        usage = "Use \"/entity help\" for help!"
)
public class EntityCommand implements CommandListener {

    @ParentCommand
    public boolean command(CommandEvent/*<Player> TODO: Player only?*/ event) {
        // TODO: stuff
        return true;
    }

    @NestedCommand
    @Command(
            command = "help",
            description = "View help information",
            aliases = {"h"}
    )
    public boolean help(CommandEvent event) {
        // TODO: stuff
        return true;
    }
}