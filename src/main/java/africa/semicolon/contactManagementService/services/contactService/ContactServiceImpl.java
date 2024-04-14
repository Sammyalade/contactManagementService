package africa.semicolon.contactManagementService.services.contactService;

import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.datas.repositories.ContactRepository;
import africa.semicolon.contactManagementService.dtos.requests.ContactCreationRequest;
import africa.semicolon.contactManagementService.dtos.requests.ContactUpdateRequest;
import africa.semicolon.contactManagementService.exception.ContactNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Contact getContactById(int contactId) {
        return contactRepository.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException("Contact not found"));
    }

    @Override
    public Contact updateContact(ContactUpdateRequest request) {
        Contact contact = getContactById((int) request.getContactId());
        updateContactFields(request, contact);
        contactRepository.save(contact);
        return contact;
    }

    @Override
    public void deleteContact(int contactId) {
        contactRepository.delete(getContactById(contactId));
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }






    private void updateContactFields(ContactUpdateRequest request, Contact contact) {
        if(request.getFirstName()!= null ) {
            String[] nameParts = contact.getName().split(" ");
            contact.setName(STR."\{request.getFirstName()} \{nameParts[1]}");
        }
        if(request.getLastName()!= null ) {
            String[] nameParts = contact.getName().split(" ");
            contact.setName(STR."\{nameParts[0]} \{request.getLastName()}");
        }
        if(request.getPhoneNumber()!= null){
            contact.setPhoneNumber(request.getPhoneNumber());
        }
        if(request.getEmail()!= null){
            contact.setEmail(request.getEmail());
        }

    }
}
