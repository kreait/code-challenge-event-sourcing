package contactsapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import contactsapp.boundary.ContactListBoundaryTest;
import contactsapp.boundary.internal.command_handler.HandleAddCompanyTest;
import contactsapp.boundary.internal.command_handler.HandleAddPersonTest;
import contactsapp.boundary.internal.command_handler.HandleEnterEmploymentTest;
import contactsapp.boundary.internal.command_handler.HandleRenameContactTest;
import contactsapp.boundary.internal.event_handler.HandleEmploymentEnteredTest;

@RunWith(Suite.class)
@SuiteClasses({ HandleAddCompanyTest.class, HandleAddPersonTest.class, HandleRenameContactTest.class,
		HandleEnterEmploymentTest.class, HandleEmploymentEnteredTest.class, ContactListBoundaryTest.class })
public class AllTests {

}
