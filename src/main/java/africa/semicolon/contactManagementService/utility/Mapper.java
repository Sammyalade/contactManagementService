package africa.semicolon.contactManagementService.utility;

import africa.semicolon.contactManagementService.datas.models.User;
import africa.semicolon.contactManagementService.dtos.UserCreationRequest;

import java.util.ArrayList;
import java.util.List;

import static africa.semicolon.contactManagementService.utility.Utility.toLowerCase;

public class Mapper {

    public static User map(UserCreationRequest userCreationRequest) {
        User newUser = new User();
        newUser.setUsername(toLowerCase(userCreationRequest.getUsername()));
        newUser.setPassword(userCreationRequest.getPassword());
        newUser.setEmail(userCreationRequest.getEmail());
        return newUser;
    }

    public static boolean checkEmptyStringUsername(String username) {
        return username.isEmpty();
    }

    public static List<?> checkIfListIsNull(List<?> lists) {
        if(lists == null) lists = new ArrayList<>();

        return lists;
    }
}
