package africa.semicolon.contactManagementService.services.contactService;

import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.dtos.*;

import java.util.List;

public interface ContactService {

    Contact createContact(ContactCreationRequest contactCreationRequest);
    Contact getContactById(int contactId);
    Contact updateContact(ContactUpdateRequest request);
    void deleteContact(int contactId);
    List<Contact> findAll();

}
