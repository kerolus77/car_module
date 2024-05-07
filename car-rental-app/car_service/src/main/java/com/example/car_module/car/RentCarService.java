package com.example.car_module.car;

import java.util.List;
import java.util.Optional;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.Jsr310Converters.DateToLocalDateConverter;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import com.example.car_module.User;
import com.example.car_module.UserRepository;
import com.example.car_module.brand.BrandEntity;
import com.example.car_module.brand.BrandRepository;
import com.example.car_module.car.RentCarRepository;


@Service
public class RentCarService {
    @Autowired
    private final RentCarRepository repository;
     
    public RentCarService(RentCarRepository repository) {
        this.repository = repository;
    }

    //   @Autowired
    // private UserRepository userRepository; 

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private UserRepository userRepository;

    public RentCarEntity createRentCar(RentCarRequest request) throws ParseException {
         User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        BrandEntity brand = brandRepository.findById(request.getBrandId()).orElseThrow(() -> new RuntimeException("Brand not found"));

        RentCarEntity rentCar = new RentCarEntity(
                request.getModel(),
                request.getLicensePlate(),
                request.getColor(),
                request.getYear(),
                request.getDescription(),
                brand,
                request.getRentPrice(),
                request.getStatus(),
                user
        );

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
