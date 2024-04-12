package africa.semicolon.contactManagementService.services.groupService;

import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.datas.models.Group;
import africa.semicolon.contactManagementService.dtos.*;

import java.util.List;

public interface GroupService {

    Group createGroup(CreateGroupRequest request);

    Group getGroupById(Long groupId);

    Group updateGroup(UpdateGroupRequest request);

    void deleteGroup(Long groupId);

    List<Group> listGroups(ListGroupsRequest request);

    void addContactToGroup(AddContactToGroupRequest request);

    void removeContactFromGroup(RemoveContactFromGroupRequest request);

    List<Contact> getContactsInGroup(Long groupId);
    List<Group> searchGroups(SearchGroupsRequest request);
}
