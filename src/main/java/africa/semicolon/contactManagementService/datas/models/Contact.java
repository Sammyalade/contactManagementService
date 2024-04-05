package africa.semicolon.contactManagementService.datas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "contact_table")
public class Contact {

    @Id
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
}
