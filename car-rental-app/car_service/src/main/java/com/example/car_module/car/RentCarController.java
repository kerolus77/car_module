package com.example.car_module.car;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = "*")
public class RentCarController {
    private final RentCarService service;

    @Autowired
    public RentCarController(RentCarService service) {
        this.service = service;
    }

    @GetMapping("/getAllCars")
    public ResponseEntity<List<RentCarEntity>> getAllRentCars() {
        return new ResponseEntity<>(service.getAllRentCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentCarEntity> getRentCarById(@Valid @PathVariable Long id) {
        return service.getRentCarById(id)
                .map(rentCar -> new ResponseEntity<>(rentCar, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    // @GetMapping("/user-rent-car/{userId}")
    // public ResponseEntity<List<RentCarEntity>> getRentCarsByUserId(@Valid @PathVariable Long userId) {
    //     return new ResponseEntity<>(service.getRentCarsByUserId(userId), HttpStatus.OK);
    // }

    // @GetMapping("/car-rent-car/{carId}")   
    // public ResponseEntity<List<RentCarEntity>> getRentCarsByCarId(@Valid @PathVariable Long carId) {
    //     return new ResponseEntity<>(service.getRentCarsByCarId(carId), HttpStatus.OK);
    // }

    // @GetMapping("/user-car-rent-car/{userId}/{carId}")
    // public ResponseEntity<List<RentCarEntity>> getRentCarsByUserIdAndCarId(@Valid @PathVariable Long userId, @Valid @PathVariable Long carId) {
    //     return new ResponseEntity<>(service.getRentCarsByUserIdAndCarId(userId, carId), HttpStatus.OK);
    // }

    @PostMapping("/addCar")
    public ResponseEntity<?> createRentCar(@RequestBody RentCarRequest request) {
        try {
            RentCarEntity savedCar = service.createRentCar(request);
            return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create rental car: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // @PutMapping("/{id}")
    // public ResponseEntity<RentCarEntity> updateRentCar(@PathVariable Long id, @RequestBody RentCarEntity rentCar) {
    //     RentCarEntity existingRentCar = service.getRentCarById(id)
    //             .orElseThrow(() -> new RuntimeException("RentCar not found with id: " + id));
    //     if (rentCar.getId() != null) {
    //         existingRentCar.setCarId(rentCar.getCarId());
    //     }
    //     if (rentCar.getUser() != null) {
    //         existingRentCar.setUserId(rentCar.getUserId());
    //     }
    //     // if (rentCar.getYear() != null) {
    //     //     existingRentCar.setRentDate(rentCar.getRentDate());
    //     // }
    //     // if (rentCar. != null) {
    //     //     existingRentCar.setReturnDate(rentCar.getReturnDate());
    //     // }
    //     return new ResponseEntity<>(service.updateRentCar(existingRentCar), HttpStatus.OK);
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRentCar(@Valid @PathVariable Long id) {
        service.deleteRentCar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
