package mieten17.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "objects")
public class Obj {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "address")
    private String address;

    @Column(name = "published")
    private int published;

    @ElementCollection
    @CollectionTable(name = "coordinates", joinColumns = @JoinColumn(name = "obj_id"))
    @Column(name = "coordinates")
    private Set<String> coordinates = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "video", joinColumns = @JoinColumn(name = "obj_id"))
    @Column(name = "video")
    private Set<String> video = new HashSet<>();

//    @ElementCollection
//    @CollectionTable(name = "details", joinColumns = @JoinColumn(name = "obj_id"))
//    private Set<String> title, floor, floors, price, capacity, countRooms, service, textObj, balcony, comfort,
//            internet, area, parking
//            = new HashSet<>();
//
//    @ElementCollection
//    @CollectionTable(name = "rules", joinColumns = @JoinColumn(name = "obj_id"))
//    private Set<String> children, animals, smoking, party, documents, monthly = new HashSet<>();

    public Long getUserId(User user) {
        return user.getId();
    }
}
