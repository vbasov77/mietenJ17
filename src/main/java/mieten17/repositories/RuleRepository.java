package mieten17.repositories;

import jakarta.transaction.Transactional;
import mieten17.models.Obj;
import mieten17.models.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {
    Rule findRuleByObjId(Long objId);

    @Transactional
    @Modifying
    @Query(value = "update rules set children = :children, animals = :animals, smoking = :smoking, " +
            "party = :party, documents = :documents, monthly = :monthly  where obj_id = :objId",
            nativeQuery = true)
    public void updateRules(String children, String animals, String smoking, String party,
                            String documents, String monthly, Long objId);

}
