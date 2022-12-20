package abg.dev.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(min = 2, max = 255, message = "Please enter a text within the borders")
    @NotNull
    @NotBlank
    @Column(name = "comment")
    private String comment;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "like_count")
    private int likeCount = 0;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "tweet_id", insertable = false, updatable = false)
    @JsonIgnore
    private Tweet tweet;

}


