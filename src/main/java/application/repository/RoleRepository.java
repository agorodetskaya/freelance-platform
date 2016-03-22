package application.repository;

import application.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String roleName);

    Collection<Role> findByNameIn(Collection<String> name);
}
