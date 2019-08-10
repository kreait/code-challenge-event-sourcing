package contactsapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import contactsapp.boundary.ContactListBoundaryTest;
import contactsapp.command.AddCompanyToContactListTest;
import contactsapp.command.AddPersonToContactListTest;

@RunWith(Suite.class)
@SuiteClasses({ AddCompanyToContactListTest.class, AddPersonToContactListTest.class, ContactListBoundaryTest.class })
public class AllTests {

}
