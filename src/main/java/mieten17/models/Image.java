package mieten17.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "obj_id")
    private Long objId;

    @Column(name = "path")
    private String path;


}
