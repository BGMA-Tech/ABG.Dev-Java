package abg.dev.entities.concretes;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(max = 50, message = "Gireceğiniz ifade 50 karakterden az olmalıdır.")
    @Column(name = "email")
    private String email;

    @Size(max = 50, message = "Gireceğiniz ifade 50 karakterden az olmalıdır.")
    @NotNull
    @NotBlank
    @Column(name = "password")
    private String password;

    @Size(max = 50, message = "Gireceğiniz ifade 50 karakterden az olmalıdır.")
    @NotNull
    @NotBlank
    @Column(name = "user_name")
    private String userName;


}
