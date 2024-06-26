package com.keanghor.phoneshop_night.repository;

import com.keanghor.phoneshop_night.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer>, JpaSpecificationExecutor<Model> {

}
