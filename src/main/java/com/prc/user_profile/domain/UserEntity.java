package com.prc.user_profile.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="users")
// using for storing username and password details
public class UserEntity implements Serializable {
    @Id
    @Column("username")
    private String userName;
    @Column("password")
    private String password;
    @Column("enabled")
    private boolean enabled = true;

    @Column("role_id")
    private int roleId;
}
