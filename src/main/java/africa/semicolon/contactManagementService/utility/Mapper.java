package africa.semicolon.contactManagementService.utility;

import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.datas.models.User;
import africa.semicolon.contactManagementService.dtos.requests.ContactCreationRequest;
import africa.semicolon.contactManagementService.dtos.requests.UserCreationRequest;
import africa.semicolon.contactManagementService.dtos.responses.ContactCreationResponse;
import africa.semicolon.contactManagementService.dtos.responses.UserRegistrationResponse;

import java.util.ArrayList;
import java.util.List;

import static africa.semicolon.contactManagementService.utility.Utility.toLowerCase;

public class Mapper {

    public static User map(UserCreationRequest userCreationRequest) {
        User newUser = new User();
        newUser.setUsername(toLowerCase(userCreationRequest.getUsername()));
        newUser.setEmail(userCreationRequest.getEmail());
        return newUser;
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
}
