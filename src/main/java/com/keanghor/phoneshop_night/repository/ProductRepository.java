package com.keanghor.phoneshop_night.repository;
import com.keanghor.phoneshop_night.entity.Color;
import com.keanghor.phoneshop_night.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository // use for communicate with database
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

}
