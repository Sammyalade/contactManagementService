package africa.semicolon.contactManagementService.datas.repositories;

import africa.semicolon.contactManagementService.datas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
