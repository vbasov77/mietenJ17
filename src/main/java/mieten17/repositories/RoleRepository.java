package mieten17.repositories;

import mieten17.models.RoleUser;
import mieten17.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleUser, Long> {
    RoleUser findByRole(String role);
}