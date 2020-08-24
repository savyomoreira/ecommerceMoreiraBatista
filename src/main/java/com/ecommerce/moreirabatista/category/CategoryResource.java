package com.ecommerce.moreirabatista.category;

import com.ecommerce.moreirabatista.percistence.CategoryRepository;
import com.ecommerce.moreirabatista.percistence.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoryResource {

    @Autowired
    private CategoryRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") long id) {
        Optional<Category> CategoryOptional = repository.findById(id);
        if (CategoryOptional.isPresent()) {
            Category category = CategoryOptional.get();
            return ResponseEntity.ok(category);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    @Validated
    public ResponseEntity<Category> save(@RequestBody final CategoryRequest categoryRequest) {
        Category category = repository.save(new Category(categoryRequest.getName(), categoryRequest.getDescription()));
        return new ResponseEntity(category, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Category> upadate(@PathVariable Long id,
                                            @RequestBody final CategoryRequest categoryRequest) {
        Optional<Category> categoryOptional = repository.findById(id);

        if (categoryOptional.isPresent()) {

            Category category = categoryOptional.get();
            category.setName(categoryRequest.getName());
            category.setDescription(categoryRequest.getDescription());

            return new ResponseEntity(repository.save(category), HttpStatus.CREATED);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deletebyID(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
