package pl.edu.pjatk.tau.jmeter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.tau.jmeter.model.Product;
import pl.edu.pjatk.tau.jmeter.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
        //TODO dodać wartości początkowe do DB
    }

    @GetMapping
    public ResponseEntity<?> list() {
        List<Product> products = repository.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(products);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Product newProduct) {

        repository.save(newProduct);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {

        Optional<Product> product = repository.findById(id);

        if (!product.isPresent())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replace(@RequestBody Product newProduct, @PathVariable Long id) {

        Optional<Product> product = repository.findById(id);

        if (!product.isPresent())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();

        newProduct.setId(id);
        repository.save(newProduct);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Optional<Product> product = repository.findById(id);

        if (!product.isPresent())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();

        repository.deleteById(id);

        return ResponseEntity
                .noContent()
                .build();
    }

}
