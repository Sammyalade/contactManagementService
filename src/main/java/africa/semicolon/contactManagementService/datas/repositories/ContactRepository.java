package africa.semicolon.contactManagementService.datas.repositories;

import africa.semicolon.contactManagementService.datas.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
