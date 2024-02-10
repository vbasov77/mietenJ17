package mieten17.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mieten17.config.MyUserDetails;
import mieten17.services.MyUserDetailsService;
import mieten17.services.NotificationService;
import mieten17.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notifications")
public class Notification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;


}
