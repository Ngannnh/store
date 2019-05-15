package ngocngan.store.repository;

import ngocngan.store.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author ngan nnh on 5/13/2019
 * @project sweet
 */

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Override List<Product> findAll();
    @Override Optional<Product> findById(Integer id);
    @Override void delete(Product product);
}
