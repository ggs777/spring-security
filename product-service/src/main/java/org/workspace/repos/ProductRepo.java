package org.workspace.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.workspace.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
