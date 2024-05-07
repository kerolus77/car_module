package com.example.car_module.car;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RentCarRepository extends JpaRepository<RentCarEntity, Long>{

    // List<RentCarEntity> findAllByUserId(Long userId);

    // List<RentCarEntity> findAllByCarId(Long id);

    // List<RentCarEntity> findAllByUserIdAndCarId(Long userId, Long id);

}
