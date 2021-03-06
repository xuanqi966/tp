package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_EXIT_PROMPT;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;

public class PromptVenueDescCommand extends Command {
    private final String description;

    public PromptVenueDescCommand(String description) {
        this.description = description;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ModelManager.processStateInput(description);
        ModelManager.setNextState();
        return new CommandResult(ModelManager.getNextPromptMessage() + PROMPT_MESSAGE_EXIT_PROMPT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PromptVenueDescCommand // instanceof handles nulls
                && this.description.equals(((PromptVenueDescCommand) other).description));
    }
}
