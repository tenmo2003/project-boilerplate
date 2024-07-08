package com.tenmo.boilerplate.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    private String username;
    private String email;
    @JsonIgnore
    private String password;

    private Role role;

    public enum Role {
        USER, ADMIN
    }


}
