package ir.arcademy.blog.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "category_tbl")
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "categories")
    private List<Posts> posts;

    public Category() {
    }

    public Category(String title){
        this.title = title;
    }

}
