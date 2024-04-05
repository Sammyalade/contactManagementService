package africa.semicolon.contactManagementService.repositoriesTest;

import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.datas.repositories.ContactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void contactRepositoryTest(){
        Contact contact = new Contact();
        contactRepository.save(contact);
        assertThat(contactRepository.count(), is(1L));
    }
}
