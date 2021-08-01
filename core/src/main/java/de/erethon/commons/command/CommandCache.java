package de.erethon.commons.command;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author Sataniel, Fyreum
 */
public class CommandCache implements Iterable<DRECommand> {

    protected final Set<DRECommand> commands;

    public CommandCache(Set<DRECommand> commands) {
        this.commands = commands;
    }

    public CommandCache(DRECommand... commands) {
        this.commands = new HashSet<>(Arrays.asList(commands));
    }

    /**
     * @param commandName usually the first command argument
     * @return the command with the given name
     */
    public DRECommand getCommand(String commandName) {
        for (DRECommand command : commands) {
            if (command.getCommand().equalsIgnoreCase(commandName) || command.getAliases().contains(commandName)) {
                return command;
            }
        }
        return null;
    }

    /**
     * @return the commands
     */
    public Set<DRECommand> getCommands() {
        return this.commands;
    }

    /**
     * @param commands the commands to add
     */
    public void addCommands(DRECommand... commands) {
        for (DRECommand command : commands) {
            addCommand(command);
        }
    }

    /**
     * @param command the command to add
     */
    public void addCommand(DRECommand command) {
        this.commands.add(command);
    }

    /**
     * @param command the command to remove
     */
    public void removeCommand(DRECommand command) {
        this.commands.remove(command);
    }

    @Override
    public Iterator<DRECommand> iterator() {
        return commands.iterator();
    }

    @Override
    public void forEach(Consumer<? super DRECommand> action) {
        commands.forEach(action);
    }

    @Override
    public Spliterator<DRECommand> spliterator() {
        return commands.spliterator();
    }
}
