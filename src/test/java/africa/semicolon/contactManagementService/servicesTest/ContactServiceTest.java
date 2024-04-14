package africa.semicolon.contactManagementService.servicesTest;

import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.datas.repositories.ContactRepository;
import africa.semicolon.contactManagementService.dtos.requests.ContactCreationRequest;
import africa.semicolon.contactManagementService.dtos.requests.ContactUpdateRequest;
import africa.semicolon.contactManagementService.exception.ContactNotFoundException;
import africa.semicolon.contactManagementService.services.contactService.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class ContactServiceTest {

    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    public void setUp() {
        contactRepository.deleteAll();
    }

    @Test
    public void createContact_contactIsCreatedTest() {
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("John");
        contactCreationRequest.setLastName("Doe");
        contactCreationRequest.setEmail("john@doe.com");
        contactCreationRequest.setPhoneNumber("1234567890");
        contactService.createContact(contactCreationRequest);
        assertThat(contactRepository.count(), is(1L));
    }

    @Test
    public void createContact_contactWithFirstNameOnly_contactIsCreatedTest() {
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("John");
        contactCreationRequest.setLastName("");
        contactCreationRequest.setEmail("john@doe.com");
        contactCreationRequest.setPhoneNumber("1234567890");
        contactService.createContact(contactCreationRequest);
        assertThat(contactRepository.count(), is(1L));
    }

    @Test
    public void createContactWithoutName_contactIsCreatedTest() {
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("");
        contactCreationRequest.setLastName("");
        contactCreationRequest.setEmail("");
        contactCreationRequest.setPhoneNumber("1234567890");
        contactService.createContact(contactCreationRequest);
        assertThat(contactRepository.count(), is(1L));
    }
    
    @Test
    public void createContact_updateContact_contactIsUpdatedTest() {
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("John");
        contactCreationRequest.setLastName("Doe");
        contactCreationRequest.setEmail("john@doe.com");
        contactCreationRequest.setPhoneNumber("1234567890");
        Contact contact = contactService.createContact(contactCreationRequest);
        ContactUpdateRequest contactUpdateRequest = new ContactUpdateRequest();
        contactUpdateRequest.setContactId(contact.getId());
        contactUpdateRequest.setFirstName("Kim");
        contactUpdateRequest.setLastName("Candie");
        contactUpdateRequest.setEmail("kim@doe.com");
        contactService.updateContact(contactUpdateRequest);
        assertThat(contactService.getContactById(contact.getId()).getName(), is("Kim Candie"));
    }

    @Test
    public void createContact_updateOnlyFirstName_contactIsUpdatedTest(){
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("John");
        contactCreationRequest.setLastName("Doe");
        contactCreationRequest.setEmail("john@doe.com");
        contactCreationRequest.setPhoneNumber("1234567890");
        Contact contact = contactService.createContact(contactCreationRequest);
        ContactUpdateRequest contactUpdateRequest = new ContactUpdateRequest();
        contactUpdateRequest.setContactId(contact.getId());
        contactUpdateRequest.setFirstName("Kim");
        contactService.updateContact(contactUpdateRequest);
        assertThat(contactService.getContactById(contact.getId()).getName(), is("Kim Doe"));
    }

    @Test
    public void createContact_updateOnlyLastName_contactIsUpdatedTest(){
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("John");
        contactCreationRequest.setLastName("Doe");
        contactCreationRequest.setEmail("john@doe.com");
        contactCreationRequest.setPhoneNumber("1234567890");
        Contact contact = contactService.createContact(contactCreationRequest);
        ContactUpdateRequest contactUpdateRequest = new ContactUpdateRequest();
        contactUpdateRequest.setContactId(contact.getId());
        contactUpdateRequest.setLastName("Gu");
        contactService.updateContact(contactUpdateRequest);
        assertThat(contactService.getContactById(contact.getId()).getName(), is("John Gu"));
    }

    @Test
    public void createContact_updateOnlyEmail_contactIsUpdatedTest(){
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("John");
        contactCreationRequest.setLastName("Doe");
        contactCreationRequest.setEmail("john@doe.com");
        contactCreationRequest.setPhoneNumber("1234567890");
        Contact contact = contactService.createContact(contactCreationRequest);
        ContactUpdateRequest contactUpdateRequest = new ContactUpdateRequest();
        contactUpdateRequest.setContactId(contact.getId());
        contactUpdateRequest.setEmail("johnDoe@contact.com");
        contactService.updateContact(contactUpdateRequest);
        assertThat(contactService.getContactById(contact.getId()).getEmail(), is("johnDoe@contact.com"));
    }

    @Test
    public void createContact_updateOnlyPhoneNumber_contactIsUpdatedTest(){
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("John");
        contactCreationRequest.setLastName("Doe");
        contactCreationRequest.setEmail("john@doe.com");
        contactCreationRequest.setPhoneNumber("1234567890");
        Contact contact = contactService.createContact(contactCreationRequest);
        ContactUpdateRequest contactUpdateRequest = new ContactUpdateRequest();
        contactUpdateRequest.setContactId(contact.getId());
        contactUpdateRequest.setPhoneNumber("09012345678");
        contactService.updateContact(contactUpdateRequest);
        assertThat(contactService.getContactById(contact.getId()).getPhoneNumber(), is("09012345678"));
    }

    @Test
    public void createContact_deleteContact_contactIsDeletedTest(){
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("John");
        contactCreationRequest.setLastName("Doe");
        contactCreationRequest.setEmail("john@doe.com");
        contactCreationRequest.setPhoneNumber("1234567890");
        Contact contact = contactService.createContact(contactCreationRequest);
        contactService.deleteContact(contact.getId());
        assertThat(contactRepository.count(), is(0L));
    }

    @Test
    public void createContact_findAllContacts_listOfContactIsReturnedTest(){
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("John");
        contactCreationRequest.setLastName("Doe");
        contactCreationRequest.setEmail("john@doe.com");
        contactCreationRequest.setPhoneNumber("1234567890");
        contactService.createContact(contactCreationRequest);
        ContactCreationRequest contactCreationRequest2 = new ContactCreationRequest();
        contactCreationRequest2.setFirstName("John");
        contactCreationRequest2.setLastName("Doe");
        contactCreationRequest2.setEmail("john@doe.com");
        contactCreationRequest2.setPhoneNumber("1234567890");
        contactService.createContact(contactCreationRequest2);
        List<Contact> contacts = contactService.findAll();
        assertThat(contactService.findAll(), is(contacts));
    }

    @Test
    public void searchForContactThatDoesNotExist_exceptionIsThrownTest(){
        assertThatThrownBy(()->{
            contactService.getContactById(1);
        })
                .isInstanceOf(ContactNotFoundException.class)
                .hasMessageContaining("Contact not found");
    }
}
