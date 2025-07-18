package com.example.book_network.role;
import com.example.book_network.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@EntityListeners(AuditingEntityListener.class)
public class Role {
    @Id
    @GeneratedValue
private Integer id;
    @Column(unique = true)
    private String name;
    @ManyToMany(mappedBy = "roles")
private List<User> users;
    @CreatedDate
    @Column(nullable = false ,updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;
}
