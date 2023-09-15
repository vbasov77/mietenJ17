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

    @Column(name = "address")
    private String address;

    public Address(Long id, Long objId, Long countryId, Long localityId, String address) {
        this.id = id;
        this.objId = objId;
        this.countryId = countryId;
        this.localityId = localityId;
        this.address = address;
    }

    public Address() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObjId() {
        return objId;
    }

    public void setObjId(Long objId) {
        this.objId = objId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getLocalityId() {
        return localityId;
    }

    public void setLocalityId(Long localityId) {
        this.localityId = localityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
