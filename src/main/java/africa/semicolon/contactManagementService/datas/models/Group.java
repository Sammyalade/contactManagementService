package africa.semicolon.contactManagementService.datas.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Data
@Entity
@Table(name = "group_table")
public class Group {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Contact> contacts;
}
