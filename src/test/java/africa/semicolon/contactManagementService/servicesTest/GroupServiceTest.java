package africa.semicolon.contactManagementService.servicesTest;

import africa.semicolon.contactManagementService.datas.models.Group;
import africa.semicolon.contactManagementService.datas.repositories.GroupRepository;
import africa.semicolon.contactManagementService.dtos.UpdateGroupRequest;
import africa.semicolon.contactManagementService.exception.EmptyStringException;
import africa.semicolon.contactManagementService.services.groupService.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class GroupServiceTest {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupRepository groupRepository;

    @BeforeEach
    public void setUp() {
        groupRepository.deleteAll();
    }


    @Test
    public void createGroup_groupIsCreatedTest(){
        groupService.createGroup("myGroup");
        assertThat(groupRepository.count(), is(1L));
    }

    @Test
    public void createGroupWithEmptyString_throwsException(){
        assertThatThrownBy(()->{
            groupService.createGroup("");
         })
                .isInstanceOf(EmptyStringException.class)
                .hasMessage("Group name cannot be empty");
        assertThat(groupRepository.count(), is(0L));
    }

    @Test
    public void createGroup_updateGroup_groupIsUpdatedTest(){
        Group group = groupService.createGroup("myGroup");
        UpdateGroupRequest updateGroupRequest = new UpdateGroupRequest();
        updateGroupRequest.setGroupId(group.getId());
        updateGroupRequest.setGroupName("newGroup");
        Group updatedGroup = groupService.updateGroup(updateGroupRequest);
        assertThat(updatedGroup.getName(), is("newGroup"));
    }

    @Test
    public void createGroup_deleteGroup_groupIsDeletedTest(){
        Group group = groupService.createGroup("myGroup");
        groupService.deleteGroup(group.getId());
        assertThat(groupRepository.count(), is(0L));
    }

    @Test
    public void createTwoGroups_getTwoGroups_returnsTwoGroupTest(){
       groupService.createGroup("myGroup");
       groupService.createGroup("myGroup2");
        List<Group> allGroups = groupService.findAll();
        assertThat(allGroups, containsInAnyOrder(
                hasProperty("name", is("myGroup")),
                hasProperty("name", is("myGroup2"))
        ));
    }

    @Test
    public void

}
