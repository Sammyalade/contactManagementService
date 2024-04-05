package africa.semicolon.contactManagementService.services.contactService;

import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.dtos.ContactCreationRequest;
import org.springframework.stereotype.Service;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService{
    @Override
    public Contact createContact(ContactCreationRequest contactCreationRequest) {
        return null;
    }
}
