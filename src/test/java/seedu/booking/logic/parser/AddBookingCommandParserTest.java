package seedu.booking.logic.parser;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.commands.CommandTestUtil.*;
import static seedu.booking.logic.commands.CommandTestUtil.VENUE_CAPACITY_DESC_HALL;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.booking.testutil.TypicalVenues.HALL;
import static seedu.booking.testutil.TypicalBookings.BOOKING_FIELD;
import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.AddBookingCommand;
import seedu.booking.logic.commands.AddVenueCommand;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.person.Email;
import seedu.booking.model.venue.Venue;
import seedu.booking.testutil.BookingBuilder;
import seedu.booking.testutil.VenueBuilder;

//public static final String BOOKING_VENUE_NAME_DESC_HALL = " " + PREFIX_VENUE + VALID_VENUE_NAME_HALL;
//public static final String BOOKING_VENUE_NAME_DESC_FIELD = " " + PREFIX_VENUE + VALID_VENUE_NAME_FIELD;
//public static final String BOOKING_BOOKER_EMAIL_AMY_DESC_HALL = " " + PREFIX_BOOKER + VALID_BOOKING_BOOKER_EMAIL_AMY;
//public static final String VALID_BOOKING_BOOKER_EMAIL_BOB_DESC_FIELD = " " + PREFIX_BOOKER + VALID_BOOKING_BOOKER_EMAIL_BOB;
//public static final String VALID_BOOKING_DESCRIPTION_DESC_HALL = " " + PREFIX_DESCRIPTION + VALID_BOOKING_DESCRIPTION_HALL;
//public static final String VALID_BOOKING_DESCRIPTION_DESC_FIELD = " " + PREFIX_DESCRIPTION + VALID_BOOKING_DESCRIPTION_FIELD;
//public static final String VALID_BOOKING_START_DESC_HALL = " " + PREFIX_BOOKING_START + VALID_BOOKING_START_HALL;
//public static final String VALID_BOOKING_START_DESC_FIELD = " " + PREFIX_BOOKING_START + VALID_BOOKING_START_FIELD;
//public static final String VALID_BOOKING_END_DESC_HALL = " " + PREFIX_BOOKING_END + VALID_BOOKING_END_HALL;
//public static final String VALID_BOOKING_END_DESC_FIELD = " " + PREFIX_BOOKING_END + VALID_BOOKING_END_FIELD;
//public static final String VALID_BOOKING_ID_DESC_HALL = " " + PREFIX_BOOKING_ID + VALID_BOOKING_ID_HALL;
//public static final String VALID_BOOKING_ID_DESC_FIELD = " " + PREFIX_BOOKING_ID + VALID_BOOKING_ID_FIELD;



class AddBookingCommandParserTest {
    private AddBookingCommandParser parser = new AddBookingCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Booking expectedBooking = new BookingBuilder(BOOKING_FIELD).build();

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + BOOKING_BOOKER_EMAIL_AMY_DESC_HALL
                        + BOOKING_VENUE_NAME_DESC_HALL+ BOOKING_DESCRIPTION_DESC_HALL
                        + BOOKING_START_DESC_HALL + BOOKING_END_DESC_HALL,
                new AddBookingCommand(expectedBooking));

        // multiple venue names - last venue name accepted
        assertParseSuccess(parser, BOOKING_BOOKER_EMAIL_AMY_DESC_HALL
                        + BOOKING_VENUE_NAME_DESC_FIELD + BOOKING_VENUE_NAME_DESC_HALL
                        + BOOKING_DESCRIPTION_DESC_HALL
                        + BOOKING_START_DESC_HALL + BOOKING_END_DESC_HALL,
                new AddBookingCommand(expectedBooking));

        // multiple booker emails - last booker email accepted
        assertParseSuccess(parser, BOOKING_BOOKER_EMAIL_BOB_DESC_FIELD + BOOKING_BOOKER_EMAIL_AMY_DESC_HALL
                        + BOOKING_VENUE_NAME_DESC_HALL
                        + BOOKING_DESCRIPTION_DESC_HALL
                        + BOOKING_START_DESC_HALL + BOOKING_END_DESC_HALL,
                new AddBookingCommand(expectedBooking));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, BOOKING_BOOKER_EMAIL_AMY_DESC_HALL
                        + BOOKING_VENUE_NAME_DESC_HALL
                        + BOOKING_DESCRIPTION_DESC_FIELD
                        + BOOKING_DESCRIPTION_DESC_HALL
                        + BOOKING_START_DESC_HALL + BOOKING_END_DESC_HALL,
                new AddBookingCommand(expectedBooking));

        // multiple start time - last start time accepted
        assertParseSuccess(parser,BOOKING_BOOKER_EMAIL_AMY_DESC_HALL
                        + BOOKING_VENUE_NAME_DESC_HALL
                        + BOOKING_DESCRIPTION_DESC_HALL
                        + BOOKING_START_DESC_FIELD + BOOKING_START_DESC_HALL
                        + BOOKING_END_DESC_HALL,
                new AddBookingCommand(expectedBooking));

        // multiple end time - last end time accepted
        assertParseSuccess(parser, BOOKING_BOOKER_EMAIL_AMY_DESC_HALL
                        + BOOKING_VENUE_NAME_DESC_HALL
                        + BOOKING_DESCRIPTION_DESC_HALL
                        + BOOKING_START_DESC_HALL + BOOKING_END_DESC_FIELD
                        + BOOKING_END_DESC_HALL,
                new AddBookingCommand(expectedBooking));
    }



    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBookingCommand.MESSAGE_USAGE);

        // missing booker email prefix
        assertParseFailure(parser, VALID_EMAIL_AMY + BOOKING_VENUE_NAME_DESC_HALL
                        + BOOKING_DESCRIPTION_DESC_HALL
                        + BOOKING_START_DESC_HALL + BOOKING_END_DESC_HALL,
                expectedMessage);

        // missing venue name prefix
        assertParseFailure(parser, BOOKING_BOOKER_EMAIL_AMY_DESC_HALL + VALID_BOOKING_VENUE_NAME_HALL
                        + BOOKING_DESCRIPTION_DESC_HALL
                        + BOOKING_START_DESC_HALL + BOOKING_END_DESC_HALL,
                expectedMessage);

        // missing description prefix
        assertParseFailure(parser, BOOKING_BOOKER_EMAIL_AMY_DESC_HALL + BOOKING_VENUE_NAME_DESC_HALL
                        + VALID_BOOKING_DESCRIPTION_HALL
                        + BOOKING_START_DESC_HALL + BOOKING_END_DESC_HALL,
                expectedMessage);


        // missing start time prefix
        assertParseFailure(parser, BOOKING_BOOKER_EMAIL_AMY_DESC_HALL + BOOKING_VENUE_NAME_DESC_HALL
                        + BOOKING_DESCRIPTION_DESC_HALL
                        + VALID_BOOKING_START_HALL + BOOKING_END_DESC_HALL,
                expectedMessage);

        // all end time missing
        assertParseFailure(parser, BOOKING_BOOKER_EMAIL_AMY_DESC_HALL + BOOKING_VENUE_NAME_DESC_HALL
                        + BOOKING_DESCRIPTION_DESC_HALL
                        + BOOKING_START_DESC_HALL + VALID_BOOKING_END_HALL,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_BOOKING_BOOKER_EMAIL_AMY + VALID_BOOKING_VENUE_NAME_HALL
                        + VALID_BOOKING_DESCRIPTION_HALL
                        + VALID_BOOKING_START_HALL + VALID_BOOKING_END_HALL,
                expectedMessage);
    }



    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBookingCommand.MESSAGE_USAGE);

        // invalid email
        // missing booker email prefix
        assertParseFailure(parser, INVALID_BOOKER_EMAIL + BOOKING_VENUE_NAME_DESC_HALL
                        + BOOKING_DESCRIPTION_DESC_HALL
                        + BOOKING_START_DESC_HALL + BOOKING_END_DESC_HALL,
                expectedMessage);
    }
}