package africa.semicolon.contactManagementService.datas.models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "user_table")
public class User {

    @Id
    private int id;
    private String username;
    private String password;
    private String email;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Contact> contacts;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Group> groups;

}
