package com.example.Ecomm.repo;

import com.example.Ecomm.model.Product;
import com.example.Ecomm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
}
