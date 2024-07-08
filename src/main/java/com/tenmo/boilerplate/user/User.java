package com.tenmo.boilerplate.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.IndexOptions;
import dev.morphia.annotations.Indexed;
import lombok.Data;

@Data
@Entity("users")
public class User {
    @Id
    private String id;

    @Indexed(options = @IndexOptions(unique = true, background = true))
    private String username;
    private String email;
    @JsonIgnore
    private String password;

    private Role role;

    public enum Role {
        USER, ADMIN
    }


}
