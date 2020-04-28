package ir.arcademy.blog.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ir.arcademy.blog.enums.Roles;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
    @NotBlank
    @Email
    private String email;

    @JsonIgnore
    @NotBlank
    private String password;

    @NotBlank
    private String name;

    private String cover;

    private boolean enabled = true;

    @NotEmpty
    @ElementCollection(targetClass = Roles.class)
    @CollectionTable(name = "authorities", joinColumns =
    @JoinColumn(name = "email", referencedColumnName = "email"))
    @Enumerated(EnumType.STRING)
    private List<Roles> roles;

    @Transient
    @JsonIgnore
    private MultipartFile file;

    @OneToMany(mappedBy = "users")
    private List<Posts> posts;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
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
