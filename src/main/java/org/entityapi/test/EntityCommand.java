package org.entityapi.test;

import com.dsh105.command.*;
import com.dsh105.commodus.GeneralUtil;
import org.bukkit.entity.Player;
import org.entityapi.api.EntityManager;
import org.entityapi.api.entity.ControllableEntityType;

@Command(
        command = "entity",
        aliases = {"e"},
        description = "Basic entity manipulation",
        permission = "entityapi.test.entity",
        help = {}, // TODO
        usage = "Use \"/entity help\" for help!"
)
public class EntityCommand implements CommandListener {

    @ParentCommand
    public boolean command(CommandEvent<Player> event) {
        // TODO: stuff
        return true;
    }

    @NestedCommand
    @Command(
            command = "spawn <type>",
            description = "Spawn an entity"
    )
    public boolean spawn(CommandEvent<Player> event) {
        ControllableEntityType type = null;
        try {
            type = ControllableEntityType.valueOf(event.variable("type").toUpperCase());
        } catch (IllegalArgumentException e) {
            event.respond(event.variable("type") + " is not a valid entity type!");
            return true;
        }

        EntityManager manager = Main.getInstance().getEntityManager();
        manager.spawnEntity(type, event.sender().getLocation(), false);

        event.respond(event.variable("type").toLowerCase() + " spawned!");
        return true;
    }

    @NestedCommand
    @Command(
            command = "help",
            description = "View help information",
            aliases = {"h"}
    )
    public boolean help(CommandEvent event) {
        Main.getInstance().getCommandManager().getHelpService().sendPage(event.sender(), 1);
        return true;
    }

    @NestedCommand
    @Command(
            command = "help <index>",
            description = "Retrieve a certain help page of all HoloAPI commands"
    )
    public boolean helpPage(CommandEvent event) {
        try {
            Main.getInstance().getCommandManager().getHelpService().sendPage(event.sender(), GeneralUtil.toInteger(event.variable("index")));
        } catch (NumberFormatException e) {
            event.respond(event.variable("index") + " must be a number!");
        }
        return true;
    }
}