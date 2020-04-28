package ir.arcademy.blog.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "posts_tbl")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Posts {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String body;

    private String cover;

    @Transient
    @JsonIgnore
    private MultipartFile file;

    @ManyToOne
    //@JoinColumn(name = "user_fk", referencedColumnName = "id")
    private Users users;

    @ManyToMany
    @JoinTable(name = "posts_tbl_categories")
    @NotEmpty
    private List<Category> categories;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_id")
    @UpdateTimestamp
    private LocalDateTime updatedAt;



    public Posts() {
    }

    public Posts(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Posts(String title, String body, String cover) {
        this.title = title;
        this.body = body;
        this.cover = cover;
    }


}
