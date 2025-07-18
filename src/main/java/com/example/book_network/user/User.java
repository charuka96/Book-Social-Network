package com.example.book_network.user;

import com.example.book_network.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails , Principal {

@Id
@GeneratedValue
private Integer id;
private String firstname;
private  String lastname;
private LocalDate dateOfBirth;
@Column(unique = true)
private String email;
private String password;
private boolean accountLocked;
private boolean enabled;
@ManyToMany(fetch = FetchType.EAGER)
private List<Role>roles;


@CreatedDate
@Column(nullable = false ,updatable = false)
private LocalDateTime createdDate;
@LastModifiedDate
@Column(insertable = false)
private LocalDateTime lastModifiedDate;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getName() {
        return email;
    }

    private String fullName(){
        return firstname+" "+lastname;
    }
}
