package com.example.car_module.brand;

import com.example.car_module.brand.dto.BrandUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/brand")
public class BrandController {

    private final BrandService service;

    @Autowired
    public BrandController(BrandService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BrandEntity>> getAllBrands() {
        return new ResponseEntity<>(service.getAllBrands(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandEntity> getBrandById(@Valid @PathVariable Long id) {
        return service.getBrandById(id)
                .map(brand -> new ResponseEntity<>(brand, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<BrandEntity> createBrand(@Valid @RequestBody BrandEntity brand) {
        return new ResponseEntity<>(service.createBrand(brand), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandEntity> updateBrand(@PathVariable Long id, @Valid @RequestBody BrandUpdateDTO brandUpdateDTO) {
        BrandEntity existingBrand = service.getBrandById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));

        if (brandUpdateDTO.getName() != null) {
            existingBrand.setName(brandUpdateDTO.getName());
        }
        if (brandUpdateDTO.getCountry() != null) {
            existingBrand.setCountry(brandUpdateDTO.getCountry());
        }

        return new ResponseEntity<>(service.updateBrand(existingBrand), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@Valid @PathVariable Long id) {
        service.deleteBrand(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(Map.of("error", ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}