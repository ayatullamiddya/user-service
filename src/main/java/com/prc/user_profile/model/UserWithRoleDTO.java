package com.prc.user_profile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithRoleDTO {
    private String username;
    private String password;
    private boolean enabled;
    private String role;
    private String authority;
}
