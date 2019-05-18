//package ngocngan.store.repository;
//
//import ngocngan.store.models.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//import java.util.Optional;
//
///**
// * @author ngan nnh on 5/16/2019
// * @project sweet
// */
//
//public interface UserRepository extends JpaRepository<User,Integer> {
//    @Override List<User> findAll();
//    @Override Optional<User> findById(Integer id);
//    @Override void delete(User user);
//    User findByUuid(String uuid);
//}
