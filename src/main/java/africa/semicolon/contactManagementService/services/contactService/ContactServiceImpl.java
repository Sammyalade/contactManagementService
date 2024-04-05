package africa.semicolon.contactManagementService.services.contactService;

import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.datas.repositories.ContactRepository;
import africa.semicolon.contactManagementService.dtos.ContactCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.contactManagementService.utility.Mapper.map;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;
    @Override
    public Contact createContact(ContactCreationRequest contactCreationRequest) {
        Contact contact = map(contactCreationRequest);
        contactRepository.save(contact);
        return contact;
    }
}
