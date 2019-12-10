package lt.bit.meniu.repositories;

import lt.bit.meniu.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    public Page<Product> findAllByType(Product.ProductType type, Pageable pageable);

}
