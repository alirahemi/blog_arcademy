package ir.arcademy.blog.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ir.arcademy.blog.enums.Roles;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "users_tbl")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Users implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;
    private String name;
    private String cover;

    private boolean enabled = true;

    @ElementCollection(targetClass = Roles.class)
    @CollectionTable(name = "authorities", joinColumns =
    @JoinColumn(name = "email", referencedColumnName = "email"))
    @Enumerated(EnumType.STRING)
    private List<Roles> roles;

    @OneToMany(mappedBy = "users")
    private List<Posts> posts;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Users() {
    }

    public Users(String email, String password, String name, String cover){
        this.email=email;
        this.password=password;
        this.name=name;
        this.cover=cover;
    }
}
