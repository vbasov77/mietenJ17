package mieten17.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "from_user_id")
    private Long fromUserId;

    @Column(name = "to_user_id")
    private Long toUserId;

    @Column(name = "obj_id")
    private Long objId;

    @Column(name = "body")
    private String body;

    @Column(name = "status")
    private int status;

    @Column(name = "created_at")
    private String createdAt;

//    @ElementCollection
//    @CollectionTable(name = "images", joinColumns = @JoinColumn(name = "obj_id", referencedColumnName="obj_id"))
//    private Set<String> images = new HashSet<>();
//
//    public String getImageStr() {
//        String string = String.join(", ", this.images);
//        List<String> images = List.of(string.split(","));
//        return images.get(0);
//    }

}
