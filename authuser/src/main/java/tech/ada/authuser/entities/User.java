package tech.ada.authuser.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 70, nullable = false)
    private String name;

    @Column(length = 70, nullable = false)
    private String lastName;

    @Column(length = 20)
    private String phone;
    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 40, nullable = false)
    private String password;

}
