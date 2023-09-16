package mieten17.models;

import jakarta.persistence.*;
import lombok.Data;
import mieten17.services.ObjService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
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
    private Integer published;

    @ElementCollection
    @CollectionTable(name = "coordinates", joinColumns = @JoinColumn(name = "obj_id"))
    @Column(name = "coordinates")
    private Set<String> coordinates = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "video", joinColumns = @JoinColumn(name = "obj_id"))
    @Column(name = "video")
    private Set<String> video = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "details", joinColumns = @JoinColumn(name = "obj_id"))
    private Set<String> title, floor, floors, price, capacity, countRooms, service, textObj, balcony, comfort, area, parking
            = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "rules", joinColumns = @JoinColumn(name = "obj_id"))
    private Set<String> children, animals, smoking, party, documents, monthly
            = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "addresses", joinColumns = @JoinColumn(name = "obj_id"))
    private Set<String> locality
            = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "images", joinColumns = @JoinColumn(name = "obj_id"))
    private Set<String> path = new HashSet<>();
//
//    @ElementCollection
//    @CollectionTable(name = "rules", joinColumns = @JoinColumn(name = "obj_id"))
//    private Set<String> children, animals, smoking, party, documents, monthly = new HashSet<>();


    public Long getUserId(User user) {
        return user.getId();
    }

    public String getPriceStr() {
        return String.join(", ", this.price);
    }

    public String getAnimalsStr() {
        return String.join(", ", this.animals);
    }

    public String getChildrenStr() {
        return String.join(", ", this.children);
    }

    public String getSmokingStr() {
        return String.join(", ", this.smoking);
    }

    public String getPartyStr() {
        return String.join(", ", this.party);
    }

    public String getDocumentsStr() {
        return String.join(", ", this.documents);
    }

    public String getMonthlyStr() {
        return String.join(", ", this.monthly);
    }


    public String getCoordinateStr() {
        return String.join(",", this.coordinates);
    }

    public String getVideoStr() {
        return String.join(",", this.video);
    }

    public String getTitleStr() {
        return String.join(", ", this.title);
    }

    public String getFloorStr() {
        return String.join(", ", this.floor);
    }

    public String getFloorsStr() {
        return String.join(", ", this.floors);
    }

    public String getCapacityStr() {
        return String.join(", ", this.capacity);
    }

    public String getCountRoomsStr() {
        return String.join(", ", this.countRooms);
    }

    public String getServiceStr() {
        return String.join(", ", this.service);
    }

    public List<String> getLisService() {
        String join = String.join(", ", this.service);
        return List.of(join.split(","));
    }

    public List<String> getBalconyList() {
        String join = String.join(", ", this.balcony);
        return List.of(join.split(","));
    }

    public List<String> getParkingList() {
        String join = String.join(", ", this.parking);
        return List.of(join.split(","));
    }

    public List<String> getComfortList() {
        String join = String.join(", ", this.comfort);
        return List.of(join.split(","));
    }

    public String getTextObjStr() {
        return String.join(", ", this.textObj);
    }

    public String getBalconyStr() {
        return String.join(", ", this.balcony);
    }

    public String getComfortStr() {
        return String.join(", ", this.comfort);
    }

    public String getAreaStr() {
        return String.join(", ", this.area);
    }

    public String getParkingStr() {
        return String.join(", ", this.parking);
    }

    public String getPathStr() {
        return String.join(", ", this.path);
    }

    public String getPathStrOne() {
        String string = String.join(", ", this.path);
        List<String> images = List.of(string.split(","));
        return images.get(0);
    }

    public boolean checkIsLoft(){
        return this.countRooms.equals("студия");
    }

}
