package mieten17.repositories;

import mieten17.models.Address;
import mieten17.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

//    Address findIdByAddress(Long id);
}
