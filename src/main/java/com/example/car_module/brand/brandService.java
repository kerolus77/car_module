package com.example.car_module.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class brandService {

    private final brandRepository repository;

    @Autowired
    public brandService(brandRepository repository) {
        this.repository = repository;
    }

    public List<brandEntity> getAllBrands() {
        return repository.findAll();
    }

    public Optional<brandEntity> getBrandById(Long id) {
        return repository.findById(id);
    }

    public brandEntity createBrand(brandEntity brand) {
        return repository.save(brand);
    }

    public brandEntity updateBrand(brandEntity brand) {
        return repository.save(brand);
    }

    public void deleteBrand(Long id) {
        repository.deleteById(id);
    }
}