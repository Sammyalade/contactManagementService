package africa.semicolon.contactManagementService.repositoriesTest;

import africa.semicolon.contactManagementService.datas.models.Group;
import africa.semicolon.contactManagementService.datas.repositories.GroupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class GroupRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;


    @Test
    public void groupRepositoryTest() {
        Group group = new Group();
        groupRepository.save(group);
        assertThat(groupRepository.count(), is(1L));
    }
}
