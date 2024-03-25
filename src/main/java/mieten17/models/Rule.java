package mieten17.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "rules")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "obj_id")
    private Long objId;

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

}
