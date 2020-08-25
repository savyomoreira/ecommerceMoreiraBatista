package com.ecommerce.moreirabatista.percistence;

import com.ecommerce.moreirabatista.percistence.model.Category;
import com.ecommerce.moreirabatista.percistence.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {
}
