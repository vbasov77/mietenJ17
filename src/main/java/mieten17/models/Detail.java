package mieten17.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
@Table(name = "details")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "obj_id")
    private Long objId;

    @Column(name = "floor")
    private int floor;

    @Column(name = "floors")
    private int floors;

    @Column(name = "balcony")
    private String balcony;

    @Column(name = "area")
    private Integer area;

    @Column(name = "price")
    private int price;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "countRooms")
    private String countRooms;

    @Column(name = "service")
    private String service;

    @Column(name = "comfort")
    private String comfort;

    @Column(name = "parking")
    private String parking;

    @Column(name = "textObj")
    private String textObj;

}
