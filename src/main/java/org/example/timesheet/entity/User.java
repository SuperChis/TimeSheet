package org.example.timesheet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.example.timesheet.enums.EUserLevel;
import org.example.timesheet.enums.EUserType;

import java.util.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    private String userType;

    private String userLevel;

    private String sex;

    private String startTimeWorking;

    private String endTimeWorking;

    private boolean isDeleted;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToOne
    private Branch branch;


    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
