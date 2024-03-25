package mieten17.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class DataObj {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "address")
    private String address;

    @Column(name = "path")
    private String path;

    @Column(name = "service")
    private String service;

    @Column(name = "text_obj")
    private String text_obj;

    @Column(name = "balcony")
    private String balcony;

    @Column(name = "comfort")
    private String comfort;

    @Column(name = "internet")
    private String internet;

    @Column(name = "parking")
    private String parking;

    @Column(name = "children")
    private String children;

    @Column(name = "animals")
    private String animals;

    @Column(name = "smoking")
    private String smoking;

    @Column(name = "party")
    private String party;

    @Column(name = "documents")
    private String documents;

    @Column(name = "monthly")
    private String monthly;

    @Column(name = "published")
    private int published;

    @Column(name = "price")
    private int price;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "count_rooms")
    private int countRooms;

    @Column(name = "floor")
    private int floor;

    @Column(name = "area")
    private Integer area;




}
