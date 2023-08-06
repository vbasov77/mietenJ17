package mieten17.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role")
    private String role;

}
