package contactsapp.boundary.internal.event_handler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import contactsapp.boundary.internal.domain.Company;
import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.CompanyAdded;

public class HandleCompanyAddedTest {
	private static final String FOO_COM = "Foo.com";
	private static final String BAR_COM = "Foo.com";
	
	private ContactList contactList;
	private HandleCompanyAdded eventHandler;
	
	@Before
	public void setup() {
		contactList = new ContactList();
		eventHandler = new HandleCompanyAdded(contactList);
	}
	
	@Test
	public void adds_company() {
		CompanyAdded companyAdded = new CompanyAdded(FOO_COM, FOO_COM);
		eventHandler.accept(companyAdded);
		
		Company company = (Company)contactList.getContact(FOO_COM).get();
		assertEquals(FOO_COM, company.getName());
	}
	
	@Test
	public void adds_two_companies_with_different_names() {
		CompanyAdded fooComAdded = new CompanyAdded(FOO_COM, FOO_COM);
		CompanyAdded barComAdded = new CompanyAdded(BAR_COM, BAR_COM);

		eventHandler.accept(fooComAdded);
		eventHandler.accept(barComAdded);
		
		Company fooCom = (Company)contactList.getContact(FOO_COM).get();
		Company barCom = (Company)contactList.getContact(BAR_COM).get();
		
		assertEquals(FOO_COM, fooCom.getName());
		assertEquals(BAR_COM, barCom.getName());
	}
	
	@Test
	public void adds_two_companies_with_the_same_name() {
		CompanyAdded fooCom1Added = new CompanyAdded(FOO_COM + "1", FOO_COM);
		CompanyAdded fooCom2Added = new CompanyAdded(FOO_COM + "2", FOO_COM);

		eventHandler.accept(fooCom1Added);
		eventHandler.accept(fooCom2Added);
		
		Company fooCom1 = (Company)contactList.getContact(FOO_COM + "1").get();
		Company fooCom2 = (Company)contactList.getContact(FOO_COM + "2").get();
		
		assertEquals(FOO_COM, fooCom1.getName());
		assertEquals(FOO_COM, fooCom2.getName());
	}
}
