package africa.semicolon.contactManagementService.datas.repositories;

import africa.semicolon.contactManagementService.datas.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    Group findByName(String name);
}
