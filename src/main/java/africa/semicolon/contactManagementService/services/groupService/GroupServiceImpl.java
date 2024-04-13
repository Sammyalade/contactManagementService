package africa.semicolon.contactManagementService.services.groupService;

import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.datas.models.Group;
import africa.semicolon.contactManagementService.datas.repositories.GroupRepository;
import africa.semicolon.contactManagementService.dtos.requests.AddContactToGroupRequest;
import africa.semicolon.contactManagementService.dtos.requests.RemoveContactFromGroupRequest;
import africa.semicolon.contactManagementService.dtos.requests.UpdateGroupRequest;
import africa.semicolon.contactManagementService.exception.EmptyStringException;
import africa.semicolon.contactManagementService.exception.GroupNotFoundException;
import africa.semicolon.contactManagementService.services.contactService.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static africa.semicolon.contactManagementService.utility.Mapper.checkIfListIsNull;

@Service("groupService")
public class GroupServiceImpl implements GroupService{

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ContactService contactService;
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
        return group
                .orElseThrow(()-> new GroupNotFoundException("Group not found"));
    }

    @Override
    public Group updateGroup(UpdateGroupRequest request) {
        if(request.getGroupName().isEmpty()) throw new EmptyStringException("Group name cannot be empty");
        Group group = getGroupById(request.getGroupId());
        group.setName(request.getGroupName());
        groupRepository.save(group);
        return group;
    }

    @Override
    public void deleteGroup(int groupId) {
        groupRepository.delete(getGroupById(groupId));
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public void addContactToGroup(AddContactToGroupRequest request) {
        Contact contact = contactService.getContactById(request.getContactId());
        Group group = getGroupById(request.getGroupId());
        group.setContacts((List<Contact>) checkIfListIsNull(group.getContacts()));
        group.getContacts().add(contact);
        groupRepository.save(group);
    }

    @Override
    public void removeContactFromGroup(RemoveContactFromGroupRequest request) {
        Contact contact = contactService.getContactById(request.getContactId());
        Group group = getGroupById(request.getGroupId());
        group.getContacts().remove(contact);
        groupRepository.save(group);
    }

    @Override
    public List<Contact> getContactsInGroup(int groupId) {
        return getGroupById(groupId).getContacts();
    }
}
