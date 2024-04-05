package africa.semicolon.contactManagementService.services.contactService;

import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.dtos.ContactCreationRequest;

public interface ContactService {

    Contact createContact(ContactCreationRequest contactCreationRequest);
}
