package mieten17.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "obj_id")
    private Long objId;

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "locality_id")
    private Long localityId;

    @Column(name = "locality")
    private String locality;


}
