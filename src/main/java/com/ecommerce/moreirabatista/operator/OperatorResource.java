package com.ecommerce.moreirabatista.operator;

import com.ecommerce.moreirabatista.percistence.OperatorRepository;
import com.ecommerce.moreirabatista.percistence.OperatorRepository;
import com.ecommerce.moreirabatista.percistence.model.Category;
import com.ecommerce.moreirabatista.percistence.model.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/operator")
public class OperatorResource {

    @Autowired
    private OperatorRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<Operator>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") long id) {
        Optional<Operator> OperatorOptional = repository.findById(id);
        if (OperatorOptional.isPresent()) {
            Operator operator = OperatorOptional.get();
            return ResponseEntity.ok(operator);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    @Validated
    public ResponseEntity<Operator> save(@RequestBody final OperatorRequest operatorRequest) {
        Operator operator = repository.save(new Operator(operatorRequest.getName(), operatorRequest.getEmail(), operatorRequest.getPassword()));
        return new ResponseEntity(operator, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Operator> upadate(@PathVariable("id") Long id,
                                            @RequestBody final OperatorRequest categoryRequest) {
        Optional<Operator> operatorOptional = repository.findById(id);

        if (operatorOptional.isPresent()) {

            Operator operator = operatorOptional.get();
            operator.setName(categoryRequest.getName());
            operator.setEmail(categoryRequest.getEmail());
            operator.setPassword(categoryRequest.getPassword());

            return new ResponseEntity(repository.save(operator), HttpStatus.CREATED);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Operator> deletebyID(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
