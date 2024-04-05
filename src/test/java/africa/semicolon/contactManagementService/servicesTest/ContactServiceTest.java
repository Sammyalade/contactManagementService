package africa.semicolon.contactManagementService.servicesTest;

import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.services.contactService.ContactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContactServiceTest {

    @Autowired
    private ContactService contactService;

    @Test
    public void createContact_contactIsCreatedTest() {
        Contact contact = new Contact();

    }
}
