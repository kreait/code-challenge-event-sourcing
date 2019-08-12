package contactsapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import contactsapp.boundary.ContactListBoundaryTest;
import contactsapp.boundary.internal.command_handler.HandleAddCompanyToContactListTest;
import contactsapp.boundary.internal.command_handler.HandleAddPersonToContactListTest;

@RunWith(Suite.class)
@SuiteClasses({ HandleAddCompanyToContactListTest.class, HandleAddPersonToContactListTest.class, ContactListBoundaryTest.class })
public class AllTests {

}
