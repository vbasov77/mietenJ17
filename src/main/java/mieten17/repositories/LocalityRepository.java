package mieten17.repositories;


import mieten17.models.Locality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocalityRepository extends JpaRepository<Locality, Long> {
    Optional<Locality> findByLocality(String locality);
    Optional<List<Locality>> findLocalitiesByLocalityStartsWith(String locality);

    Locality findLocalityByLocality(String locality);
    Long findIdByLocality(String locality);

}
