package com.keanghor.phoneshop_night.repository;
import com.keanghor.phoneshop_night.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // use for communicate with database
public interface ColorRepository extends JpaRepository<Color, Long> {

}
