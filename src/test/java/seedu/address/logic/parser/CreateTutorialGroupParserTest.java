package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.CreateTutorialGroupCommand;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.TutorialGroupBuilder;

public class CreateTutorialGroupParserTest {
    private CreateTutorialGroupCommandParser parser = new CreateTutorialGroupCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        TutorialGroup expected = new TutorialGroupBuilder().build();
        String commandWord = CreateTutorialGroupCommand.COMMAND_WORD;

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + " n/" + expected.getName() + " id/" + expected.getId(),
                           new CreateTutorialGroupCommand(expected));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        TutorialGroup expected = new TutorialGroupBuilder().build();
        String expectedMessage = String.format(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                                             CreateTutorialGroupCommand.MESSAGE_USAGE));
        // id missing
        assertParseFailure(parser, "n/" + expected.getName(), expectedMessage);

        // name missing
        assertParseFailure(parser, "id/" + expected.getId(), expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, "", expectedMessage);
    }
}
