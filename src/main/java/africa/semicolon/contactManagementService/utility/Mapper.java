package africa.semicolon.contactManagementService.utility;

import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.datas.models.Group;
import africa.semicolon.contactManagementService.datas.models.User;
import africa.semicolon.contactManagementService.dtos.requests.AddContactToGroupRequest;
import africa.semicolon.contactManagementService.dtos.requests.ContactCreationRequest;
import africa.semicolon.contactManagementService.dtos.requests.UserCreationRequest;
import africa.semicolon.contactManagementService.dtos.requests.UserUpdateRequest;
import africa.semicolon.contactManagementService.dtos.responses.ContactCreationResponse;
import africa.semicolon.contactManagementService.dtos.responses.GroupCreationResponse;
import africa.semicolon.contactManagementService.dtos.responses.UserRegistrationResponse;
import africa.semicolon.contactManagementService.dtos.responses.UserUpdateResponse;
import africa.semicolon.contactManagementService.exception.ContactUpdateResponse;

import java.util.ArrayList;
import java.util.List;

import static africa.semicolon.contactManagementService.utility.Utility.toLowerCase;

public class Mapper {

    public static User map(UserCreationRequest userCreationRequest) {
        User user = new User();
        user.setUsername(toLowerCase(userCreationRequest.getUsername()));
        user.setEmail(userCreationRequest.getEmail());
        user.setContacts((List<Contact>) checkIfListIsNull(user.getContacts()));
        user.setGroups((List<Group>) checkIfListIsNull(user.getGroups()));
        return user;
    }

    public static boolean isEmptyOrNullString(String title) {
        return title == null || title.isEmpty();
    }

    public static List<?> checkIfListIsNull(List<?> lists) {
        if(lists == null) lists = new ArrayList<>();

        return lists;
    }

    public static Contact map(ContactCreationRequest contactCreationRequest){
        Contact newContact = new Contact();
        newContact.setEmail(contactCreationRequest.getEmail());
        newContact.setName(STR."\{contactCreationRequest.getFirstName()} \{contactCreationRequest.getLastName()}");
        newContact.setPhoneNumber(contactCreationRequest.getPhoneNumber());
        return newContact;
    }

    public static UserRegistrationResponse map(User user){
        UserRegistrationResponse userRegistrationResponse = new UserRegistrationResponse();
        userRegistrationResponse.setUserId(user.getId());
        userRegistrationResponse.setUsername(user.getUsername());
        userRegistrationResponse.setEmail(user.getEmail());
        return userRegistrationResponse;
    }

    public static ContactCreationResponse map(User user, Contact contact) {
        ContactCreationResponse contactCreationResponse = new ContactCreationResponse();
        contactCreationResponse.setUserId(user.getId());
        contactCreationResponse.setUsername(user.getUsername());
        contactCreationResponse.setContactName(contact.getName());
        contactCreationResponse.setContactId(contact.getId());
        return contactCreationResponse;
    }

    public static UserUpdateResponse map(UserUpdateRequest userUpdateRequest, String email) {
        UserUpdateResponse userUpdateResponse = new UserUpdateResponse();
        userUpdateResponse.setUserId(userUpdateRequest.getUserId());
        userUpdateResponse.setEmail(email);
        userUpdateResponse.setUsername(userUpdateRequest.getUsername());
        return userUpdateResponse;
    }

    public static ContactUpdateResponse map(int userId, int contactId, String name) {
        ContactUpdateResponse contactUpdateResponse = new ContactUpdateResponse();
        contactUpdateResponse.setUserId(userId);
        contactUpdateResponse.setContactId(contactId);
        contactUpdateResponse.setContactName(name);
        return contactUpdateResponse;
    }

    public static GroupCreationResponse map(int groupId, String name, int userId) {
        GroupCreationResponse groupCreationResponse = new GroupCreationResponse();
        groupCreationResponse.setUserId(userId);
        groupCreationResponse.setGroupId(groupId);
        groupCreationResponse.setGroupName(name);
        return groupCreationResponse;
    }

    public static AddContactToGroupRequest map(int groupId, int contactId) {
        AddContactToGroupRequest addContactToGroupRequest = new AddContactToGroupRequest();
        addContactToGroupRequest.setGroupId(groupId);
        addContactToGroupRequest.setContactId(contactId);
        return addContactToGroupRequest;
    }
}
