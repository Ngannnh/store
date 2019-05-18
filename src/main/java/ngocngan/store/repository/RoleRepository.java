package ngocngan.store.repository;

import ngocngan.store.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author ngan nnh on 5/16/2019
 * @project store
 */

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Override List<Role> findAll();
    @Override Optional<Role> findById(Integer id);
    @Override void delete(Role role);
    Role findByUuid(String uuid);
}
