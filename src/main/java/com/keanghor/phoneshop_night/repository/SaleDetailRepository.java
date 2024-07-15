package com.keanghor.phoneshop_night.repository;
import com.keanghor.phoneshop_night.entity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {

}

