package contactsapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import contactsapp.boundary.ContactListBoundaryTest;
import contactsapp.boundary.internal.command_handler.HandleAddCompanyTest;
import contactsapp.boundary.internal.command_handler.HandleAddPersonTest;

@RunWith(Suite.class)
@SuiteClasses({ HandleAddCompanyTest.class, HandleAddPersonTest.class, ContactListBoundaryTest.class })
public class AllTests {

}
