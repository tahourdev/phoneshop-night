package com.keanghor.phoneshop_night.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.keanghor.phoneshop_night.entity.Brand;

import java.util.List;

@Repository // use for communicate with database
public interface BrandRepository extends JpaRepository<Brand, Integer>, JpaSpecificationExecutor<Brand> {

    List<Brand> findByNameIgnoreCase(String name);
    List<Brand> findByNameLike(String name);
    List<Brand> findByNameContaining(String name);
}
