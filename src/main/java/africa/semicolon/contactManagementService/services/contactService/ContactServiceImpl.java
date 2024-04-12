package africa.semicolon.contactManagementService.services.contactService;

import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.datas.repositories.ContactRepository;
import africa.semicolon.contactManagementService.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Contact> contactOptional = contactRepository.findById(contactId);
        return contactOptional.orElse(null);
    }

    @Override
    public Contact updateContact(ContactUpdateRequest request) {
        Contact contact = getContactById((int) request.getId());
        checkForUpdate(request, contact);
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


    @Override
    public void assignContactToGroup(AssignContactToGroupRequest request) {

    }

    @Override
    public void removeContactFromGroup(RemoveContactFromGroupRequest request) {

    }





    private void checkForUpdate(ContactUpdateRequest request, Contact contact) {
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
