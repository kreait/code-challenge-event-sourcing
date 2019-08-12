package contactsapp.boundary.internal.command_handler;

import java.util.function.Function;

import contactsapp.boundary.internal.event.CompanyAddedToContactList;
import contactsapp.command.AddCompanyToContactList;

public class HandleAddCompanyToContactList implements Function<AddCompanyToContactList, Object> {
	@Override
	public Object apply(AddCompanyToContactList command) {
		CompanyAddedToContactList companyAddedToContactList = new CompanyAddedToContactList(command.getCompanyName());
		return companyAddedToContactList;
	}
}
