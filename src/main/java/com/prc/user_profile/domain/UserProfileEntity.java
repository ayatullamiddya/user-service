package com.prc.user_profile.domain;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "user_profile")
// storing user details like name, dept etc
public class UserProfileEntity implements Serializable {

    @Id
    @Column("email")
    private String email;
    @Column("firstname")
    private String firstname;
    @Column("lastname")
    private String lastname;
    @Column("department")
    private String department;
    @Column("gender")
    private String gender;
    @Column("coursename")
    private String coursename;

}
