package pl.edu.pjatk.tau.jmeter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pjatk.tau.jmeter.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
