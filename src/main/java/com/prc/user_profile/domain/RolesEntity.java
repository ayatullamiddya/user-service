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
@Table("users_roles")
public class RolesEntity implements Serializable {
    @Id
    @Column("role_id")
    private int roleId;
    @Column("role")
    private String role;
    @Column("authority")
    private String authority;
}
