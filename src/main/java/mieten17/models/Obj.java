package mieten17.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
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


    //    @OneToOne
//    @JoinTable(name = "coordinates",
//            joinColumns = @JoinColumn(name = "obj_id"),
//            inverseJoinColumns = @JoinColumn(name = "coordinates"))
//    private String coordinates;

    @ElementCollection
    @CollectionTable(name = "coordinates", joinColumns = @JoinColumn(name = "obj_id"))
    @Column(name = "coordinates")
    private Set<String> coordinates = new HashSet<>();

    public Obj(Long userId, String address) {
        this.userId = userId;
        this.address = address;
    }

    public Obj() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId(User user) {
        return user.getId();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }
}
