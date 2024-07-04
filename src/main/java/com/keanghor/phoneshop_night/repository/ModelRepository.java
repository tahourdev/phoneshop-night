package com.keanghor.phoneshop_night.repository;

import com.keanghor.phoneshop_night.entity.Brand;
import com.keanghor.phoneshop_night.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByBrandId (Long brandId);
}
