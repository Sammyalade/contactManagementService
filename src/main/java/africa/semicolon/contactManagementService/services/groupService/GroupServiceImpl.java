package africa.semicolon.contactManagementService.services.groupService;

import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.datas.models.Group;
import africa.semicolon.contactManagementService.dtos.*;

import java.util.List;

public class GroupServiceImpl implements GroupService{
    @Override
    public Group createGroup(CreateGroupRequest request) {
        return null;
    }

    @Override
    public Group getGroupById(Long groupId) {
        return null;
    }

    @Override
    public Group updateGroup(UpdateGroupRequest request) {
        return null;
    }

    @Override
    public void deleteGroup(Long groupId) {

    }

    @Override
    public List<Group> listGroups(ListGroupsRequest request) {
        return null;
    }

    @Override
    public void addContactToGroup(AddContactToGroupRequest request) {

    }

    @Override
    public void removeContactFromGroup(RemoveContactFromGroupRequest request) {

    }

    @Override
    public List<Contact> getContactsInGroup(Long groupId) {
        return null;
    }

    @Override
    public List<Group> searchGroups(SearchGroupsRequest request) {
        return null;
    }
}
