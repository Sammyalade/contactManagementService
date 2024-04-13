package africa.semicolon.contactManagementService.servicesTest;

import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.datas.models.Group;
import africa.semicolon.contactManagementService.datas.repositories.GroupRepository;
import africa.semicolon.contactManagementService.dtos.AddContactToGroupRequest;
import africa.semicolon.contactManagementService.dtos.ContactCreationRequest;
import africa.semicolon.contactManagementService.dtos.RemoveContactFromGroupRequest;
import africa.semicolon.contactManagementService.dtos.UpdateGroupRequest;
import africa.semicolon.contactManagementService.exception.EmptyStringException;
import africa.semicolon.contactManagementService.exception.GroupNotFoundException;
import africa.semicolon.contactManagementService.services.contactService.ContactService;
import africa.semicolon.contactManagementService.services.groupService.GroupService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@Transactional
public class GroupServiceTest {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ContactService contactService;

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
    public void createGroup_addContactToGroup_sizeOfContactListIsOneTest(){
        Group group = groupService.createGroup("myGroup");
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("myFirstName");
        contactCreationRequest.setLastName("myLastName");
        contactCreationRequest.setEmail("myEmail");
        contactCreationRequest.setPhoneNumber("myPhoneNumber");
        Contact contact = contactService.createContact(contactCreationRequest);
        AddContactToGroupRequest addContactToGroupRequest = new AddContactToGroupRequest();
        addContactToGroupRequest.setContactId(contact.getId());
        addContactToGroupRequest.setGroupId(group.getId());
        groupService.addContactToGroup(addContactToGroupRequest);
        assertThat(groupService.getGroupById(group.getId()).getContacts().size(), is(1));
    }

    @Test
    public void createGroup_addTwoContactToGroup_sizeOfContactListIsTwoTest(){
        Group group = groupService.createGroup("myGroup");
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("myFirstName");
        contactCreationRequest.setLastName("myLastName");
        contactCreationRequest.setEmail("myEmail");
        contactCreationRequest.setPhoneNumber("myPhoneNumber");
        ContactCreationRequest contactCreationRequest2 = new ContactCreationRequest();
        contactCreationRequest2.setFirstName("myFirstName");
        contactCreationRequest2.setLastName("myLastName");
        contactCreationRequest2.setEmail("myEmail");
        contactCreationRequest2.setPhoneNumber("myPhoneNumber");
        Contact contact2 = contactService.createContact(contactCreationRequest);
        Contact contact = contactService.createContact(contactCreationRequest);
        AddContactToGroupRequest addContactToGroupRequest = new AddContactToGroupRequest();
        addContactToGroupRequest.setContactId(contact.getId());
        addContactToGroupRequest.setGroupId(group.getId());
        AddContactToGroupRequest addContactToGroupRequest2 = new AddContactToGroupRequest();
        addContactToGroupRequest2.setContactId(contact2.getId());
        addContactToGroupRequest2.setGroupId(group.getId());
        groupService.addContactToGroup(addContactToGroupRequest);
        groupService.addContactToGroup(addContactToGroupRequest2);
        assertThat(groupService.getGroupById(group.getId()).getContacts().size(), is(2));
    }

    @Test
    public void createGroup_addContactToGroup_removeContactFromGroup_contactIsRemovedTest(){
        Group group = groupService.createGroup("myGroup");
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("myFirstName");
        contactCreationRequest.setLastName("myLastName");
        contactCreationRequest.setEmail("myEmail");
        contactCreationRequest.setPhoneNumber("myPhoneNumber");
        Contact contact = contactService.createContact(contactCreationRequest);
        AddContactToGroupRequest addContactToGroupRequest = new AddContactToGroupRequest();
        addContactToGroupRequest.setContactId(contact.getId());
        addContactToGroupRequest.setGroupId(group.getId());
        groupService.addContactToGroup(addContactToGroupRequest);
        RemoveContactFromGroupRequest removeContactFromGroupRequest = new RemoveContactFromGroupRequest();
        removeContactFromGroupRequest.setGroupId(group.getId());
        removeContactFromGroupRequest.setContactId(contact.getId());
        groupService.removeContactFromGroup(removeContactFromGroupRequest);
        assertThat(groupService.getGroupById(group.getId()).getContacts().size(), is(0));
    }

    @Test
    public void createGroup_addContactToGroup_getAllContactInTheGroup_returnsAListOfContactInTheGroupTest(){
        Group group = groupService.createGroup("myGroup");
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("myFirstName");
        contactCreationRequest.setLastName("myLastName");
        contactCreationRequest.setEmail("myEmail");
        contactCreationRequest.setPhoneNumber("myPhoneNumber");
        Contact contact = contactService.createContact(contactCreationRequest);
        AddContactToGroupRequest addContactToGroupRequest = new AddContactToGroupRequest();
        addContactToGroupRequest.setContactId(contact.getId());
        addContactToGroupRequest.setGroupId(group.getId());
        groupService.addContactToGroup(addContactToGroupRequest);
        List<Contact> contacts = groupService.getContactsInGroup(group.getId());
        assertThat(contacts, is(groupService.getContactsInGroup(group.getId())));
    }

    @Test
    public void searchForGroupNotExisting_throwsExceptionTest(){
        assertThatThrownBy(() -> {
            groupService.getGroupById(11);
        })
                .isInstanceOf(GroupNotFoundException.class)
                .hasMessageContaining("Group not found");
    }


}
