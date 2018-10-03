package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.SameStudentIdPredicate;
import seedu.address.model.student.Student;
import seedu.address.testutil.PersonBuilder;

public class ViewCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void equals() {
        SameStudentIdPredicate firstPredicate = new SameStudentIdPredicate("A1234567Z");
        SameStudentIdPredicate secondPredicate = new SameStudentIdPredicate("B1234567Y");

        ViewCommand firstViewCommand = new ViewCommand(firstPredicate);
        ViewCommand secondViewCommand = new ViewCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstViewCommand.equals(firstViewCommand));

        // same values -> returns true
        ViewCommand firstViewCommandCopy = new ViewCommand(firstPredicate);
        assertTrue(firstViewCommand.equals(firstViewCommandCopy));

        // different types -> returns false
        assertFalse(firstViewCommand.equals(1));

        // null -> returns false
        assertFalse(firstViewCommand.equals(null));

        // different student -> returns false
        assertFalse(firstViewCommand.equals(secondViewCommand));
    }

    @Test
    public void execute_viewCommand_success() {
        Student expectedStudent = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253")
                .withStudentId("A0166733Y")
                .withTags("friends").build();

        assertCommandSuccess(new ViewCommand(new SameStudentIdPredicate("A0166733Y")),
                model,
                commandHistory,
                String.format("%s\n %s\n", ViewCommand.MESSAGE_SUCCESS, expectedStudent),
                expectedModel);

        assertCommandSuccess(new ViewCommand(new SameStudentIdPredicate("B0123456Z")),
                model,
                commandHistory,
                ViewCommand.MESSAGE_FAILURE,
                expectedModel);
    }
}
