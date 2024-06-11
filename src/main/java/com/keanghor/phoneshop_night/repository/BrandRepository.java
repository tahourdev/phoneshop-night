package com.keanghor.phoneshop_night.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.keanghor.phoneshop_night.entity.Brand;

@Repository // use for communicate with database
public interface BrandRepository extends JpaRepository<Brand, Integer>{
    
}
