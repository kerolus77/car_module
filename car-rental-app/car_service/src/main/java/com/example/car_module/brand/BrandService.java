package com.example.car_module.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    private final BrandRepository repository;

    @Autowired
    public BrandService(BrandRepository repository) {
        this.repository = repository;
    }

    public List<BrandEntity> getAllBrands() {
        return repository.findAll();
    }

    public Optional<BrandEntity> getBrandById(Long id) {
        return repository.findById(id);
    }

    public BrandEntity createBrand(BrandEntity brand) {
        return repository.save(brand);
    }

    public BrandEntity updateBrand(BrandEntity brand) {
        return repository.save(brand);
    }

    public void deleteBrand(Long id) {
        repository.deleteById(id);
    }
}