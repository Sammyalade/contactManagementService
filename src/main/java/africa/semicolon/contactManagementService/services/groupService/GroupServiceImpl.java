package africa.semicolon.contactManagementService.services.groupService;

import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.datas.models.Group;
import africa.semicolon.contactManagementService.datas.repositories.GroupRepository;
import africa.semicolon.contactManagementService.dtos.*;
import africa.semicolon.contactManagementService.exception.EmptyStringException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("groupService")
public class GroupServiceImpl implements GroupService{

    @Autowired
    private GroupRepository groupRepository;
    @Override
    public Group createGroup(String name) {
        if(name.isEmpty()) throw new EmptyStringException("Group name cannot be empty");
        Group group = new Group();
        group.setName(name);
        groupRepository.save(group);
        return group;
    }

    @Override
    public Group getGroupById(int groupId) {
        Optional<Group> group = groupRepository.findById(groupId);
        return group.orElse(null);
    }

    @Override
    public Group updateGroup(UpdateGroupRequest request) {
        Group group = getGroupById(request.getGroupId());
        group.setName(request.getGroupName());
        groupRepository.save(group);
        return group;
    }

    @Override
    public void deleteGroup(int groupId) {
        groupRepository.deleteById(groupId);
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
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
