package com.ecommerce.moreirabatista.percistence;

import com.ecommerce.moreirabatista.percistence.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
