package africa.semicolon.contactManagementService.services.groupService;

import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.datas.models.Group;
import africa.semicolon.contactManagementService.dtos.requests.AddContactToGroupRequest;
import africa.semicolon.contactManagementService.dtos.requests.RemoveContactFromGroupRequest;
import africa.semicolon.contactManagementService.dtos.requests.UpdateGroupRequest;

import java.util.List;

public interface GroupService {

    Group createGroup(String name);

    Group getGroupById(int groupId);

    Group updateGroup(UpdateGroupRequest request);

    void deleteGroup(int groupId);

    List<Group> findAll();

    void addContactToGroup(AddContactToGroupRequest request);

    void removeContactFromGroup(RemoveContactFromGroupRequest request);

    List<Contact> getContactsInGroup(int groupId);

    Group findByName(String name);
}
