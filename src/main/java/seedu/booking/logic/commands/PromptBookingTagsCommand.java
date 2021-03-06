package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_EXIT_PROMPT;

import java.util.Set;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.Tag;

public class PromptBookingTagsCommand extends Command {

    private final Set<Tag> tagSet;

    public PromptBookingTagsCommand(Set<Tag> tagSet) {
        this.tagSet = tagSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ModelManager.processStateInput(tagSet);
        ModelManager.setNextState();
        return new CommandResult(ModelManager.getNextPromptMessage() + PROMPT_MESSAGE_EXIT_PROMPT);
    }
}
