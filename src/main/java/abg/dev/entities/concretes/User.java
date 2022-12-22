package abg.dev.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibarnateLazyInitializer", "handler", "tweetList", "commentList"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(max = 50, message = "Gireceğiniz ifade 50 karakterden az olmalıdır.")
    @Column(name = "email")
    private String email;

    @Size(max = 50, message = "Gireceğiniz ifade 50 karakterden az olmalıdır.")
    @Column(name = "password")
    private String password;

    @Column(name = "password_hash")
    private String passwordHash;

    @Size(max = 50, message = "Gireceğiniz ifade 50 karakterden az olmalıdır.")
    @Column(name = "user_name")
    private String userName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Tweet> tweetList = new ArrayList<Tweet>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<Comment>();
}
