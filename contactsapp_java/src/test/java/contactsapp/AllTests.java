package contactsapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import contactsapp.command.AddCompanyToContactListTest;
import contactsapp.command.AddPersonToContactListTest;

@RunWith(Suite.class)
@SuiteClasses({ AddCompanyToContactListTest.class, AddPersonToContactListTest.class })
public class AllTests {

}
