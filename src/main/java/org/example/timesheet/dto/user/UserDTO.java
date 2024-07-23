package org.example.timesheet.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.timesheet.entity.Branch;
import org.example.timesheet.entity.Role;
import org.example.timesheet.enums.EUserLevel;
import org.example.timesheet.enums.EUserType;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;

    private String username;

    private String email;

    private String userType;

    private String userLevel;

    private String sex;

    private boolean isDeleted;

    private Set<Role> roles;

    private Branch branch;

    public UserDTO(Long id, String username, String email, String userType, String userLevel,
                   boolean isDeleted, String sex, Branch branch) {
        this.id = id;   
        this.username = username;
        this.email = email;
        this.userType = userType;
        this.userLevel = userLevel;
        this.isDeleted = isDeleted;
        this.sex= sex;
        this.branch = branch;
    }
}
