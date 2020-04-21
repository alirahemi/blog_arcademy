package ir.arcademy.blog.modules.users.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users_tbl")
public class Users {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;
    private String name;
    private String cover;

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
