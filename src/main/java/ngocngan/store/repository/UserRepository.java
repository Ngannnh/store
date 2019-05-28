package ngocngan.store.repository;

import ngocngan.store.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author ngan nnh on 5/16/2019
 * @project sweet
 */

public interface UserRepository extends JpaRepository<Users,Integer> {
    @Override List<Users> findAll();
    @Override Optional<Users> findById(Integer id);
    @Override void delete(Users users);
    Users findByUuid(String uuid);
    Users findByEmail(String email);
    Users findByPhone(String phone);
}
