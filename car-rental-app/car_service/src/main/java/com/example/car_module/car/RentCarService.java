package com.example.car_module.car;

import java.util.List;
import java.util.Optional;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.car_module.brand.BrandEntity;
import com.example.car_module.brand.BrandRepository;


@Service
public class RentCarService {
    @Autowired
    private final RentCarRepository repository;
     
    public RentCarService(RentCarRepository repository) {
        this.repository = repository;
    }


    @Autowired
    private BrandRepository brandRepository;


    public RentCarEntity createRentCar(RentCarRequest request) throws ParseException {
        BrandEntity brand = brandRepository.findById(request.getBrandId()).orElseThrow(() -> new RuntimeException("Brand not found"));

        RentCarEntity rentCar = new RentCarEntity(
                request.getLicensePlate(),
                brand,
                request.getRentPrice(),
                request.getYear(),
                request.getDescription(),
                request.getStatus(),
                request.getModel(),
                request.getCarImage()

        );
        rentCar.setStatus(RentCarEntity.Status.AVAILABLE);
        return repository.save(rentCar);
    }

    public RentCarEntity updateRentCar(RentCarEntity rentCar) {
        return repository.save(rentCar);
    }

    public void deleteRentCar(Long id) {
        repository.deleteById(id);
    }

    public Optional<RentCarEntity> getRentCarById(Long id) {
        return repository.findById(id);
    }

    public List<RentCarEntity> getAllRentCars() {
        return repository.findAll();
    }

    // public List<RentCarEntity> getRentCarsByUserId(Long userId) {
    //     return repository.findAllByUserId(userId);
    // }

    // public List<RentCarEntity> getRentCarsByCarId(Long carId) {
    //     return repository.findAllByCarId(carId);
    // }

    // public List<RentCarEntity> getRentCarsByUserIdAndCarId(Long userId, Long carId) {
    //     return repository.findAllByUserIdAndCarId(userId, carId);
    // }



}
