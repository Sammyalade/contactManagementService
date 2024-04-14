package africa.semicolon.contactManagementService.contollers;

import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.datas.models.Group;
import africa.semicolon.contactManagementService.dtos.requests.*;
import africa.semicolon.contactManagementService.dtos.responses.*;
import africa.semicolon.contactManagementService.exception.ContactManagementServiceException;
import africa.semicolon.contactManagementService.exception.ContactUpdateResponse;
import africa.semicolon.contactManagementService.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("contactManagementService")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody UserCreationRequest userCreationRequest) {
        try{
            UserRegistrationResponse userRegistrationResponse = userService.registerUser(userCreationRequest);
            return new ResponseEntity<>(new UserApiResponse(true, userRegistrationResponse), CREATED);
        } catch (ContactManagementServiceException e){
            return new ResponseEntity<>(new UserApiResponse(false , e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        try {
            UserUpdateResponse userUpdateResponse = new UserUpdateResponse();
            return new ResponseEntity<>(new UserApiResponse(true, userUpdateResponse), CREATED);
        } catch (ContactManagementServiceException e){
            return new ResponseEntity<>(new UserApiResponse(false , e.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("/deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId) {
        try{
            userService.deleteUser(userId);
            return new ResponseEntity<>(new UserApiResponse(true, "User Deleted Successfully"), HttpStatus.OK);
        } catch (ContactManagementServiceException e){
            return new ResponseEntity<>(new UserApiResponse(false , e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/createContact")
    public ResponseEntity<?> createContact(@RequestBody ContactCreationRequest contactCreationRequest) {
        try{
            ContactCreationResponse contactCreationResponse = userService.createContact(contactCreationRequest);
            return new ResponseEntity<>(new UserApiResponse(true, contactCreationResponse), CREATED);
        } catch (ContactManagementServiceException e){
            return new ResponseEntity<>(new UserApiResponse(false , e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/updateContact")
    public ResponseEntity<?> updateContact(@RequestBody ContactUpdateRequest contactUpdateRequest) {
        try{
            ContactUpdateResponse contactUpdateResponse = userService.updateContact(contactUpdateRequest);
            return new ResponseEntity<>(new UserApiResponse(true, contactUpdateResponse), CREATED);
        } catch(ContactManagementServiceException e){
            return new ResponseEntity<>(new UserApiResponse(false , e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/deleteContact")
    public ResponseEntity<?> deleteContact(@RequestBody ContactDeleteRequest contactDeleteRequest) {
        try{
            userService.deleteContact(contactDeleteRequest);
            return new ResponseEntity<>(new UserApiResponse(true, "User Deleted Successfully"), CREATED);
        } catch (ContactManagementServiceException e){
            return new ResponseEntity<>(new UserApiResponse(false , e.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("/findAllContact/{userId}")
    public ResponseEntity<?> findAllContact(@PathVariable("userId") int userId) {
        try {
            List<Contact> contacts = userService.findAllContact(userId);
            return new ResponseEntity<>(new UserApiResponse(true, contacts), CREATED);
        }catch (ContactManagementServiceException e){
            return new ResponseEntity<>(new UserApiResponse(false , e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/createGroup")
    public ResponseEntity<?> createGroup(@RequestBody GroupCreationRequest groupCreationRequest) {
        try{
            GroupCreationResponse groupCreationResponse = userService.createGroup(groupCreationRequest);
            return new ResponseEntity<>(new UserApiResponse(true, groupCreationResponse), CREATED);
        } catch (ContactManagementServiceException e){
            return new ResponseEntity<>(new UserApiResponse(false , e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/updateGroup")
    public ResponseEntity<?> updateGroup(UpdateGroupRequest updateGroupRequest) {
        try{
            userService.updateGroup(updateGroupRequest);
            return new ResponseEntity<>(new UserApiResponse(true, "Group Update Successfully"), CREATED);
        } catch(ContactManagementServiceException e){
            return new ResponseEntity<>(new UserApiResponse(false , e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/deleteGroup")
    public ResponseEntity<?> deleteGroup(@RequestBody GroupDeleteRequest groupDeleteRequest) {
        try{
            userService.deleteGroup(groupDeleteRequest);
            return new ResponseEntity<>(new UserApiResponse(true, "Group Deleted Successfully"), CREATED);
        } catch (ContactManagementServiceException e){
            return new ResponseEntity<>(new UserApiResponse(false , e.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("/findAllGroup/{userId}")
    public ResponseEntity<?> findAllGroup(@PathVariable("userId") int userId){
        try{
            List<Group> groups = userService.findAllGroup(userId);
            return new ResponseEntity<>(new UserApiResponse(true, groups), CREATED);
        } catch(ContactManagementServiceException e){
            return new ResponseEntity<>(new UserApiResponse(false , e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest) {
        try {
            userService.loginUser(userLoginRequest);
            return new ResponseEntity<>(new UserApiResponse(true, "Login successful"), CREATED);
        } catch (ContactManagementServiceException e){
            return new ResponseEntity<>(new UserApiResponse(false , e.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("/logout/{userId}")
    public ResponseEntity<?> logout(@PathVariable("userId") int userId) {
        try{
            userService.logoutUser(userId);
            return new ResponseEntity<>(new UserApiResponse(true, "Logout successful"), CREATED);
        } catch (ContactManagementServiceException e){
            return new ResponseEntity<>(new UserApiResponse(false , e.getMessage()), BAD_REQUEST);
        }
    }
}