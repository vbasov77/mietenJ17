package mieten17.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "obj_id")
    private Long objId;

    @Column(name = "video")
    private String video;
}
