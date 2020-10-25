package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Feature;
import com.example.demo.model.Introduction;
import com.example.demo.model.Price;
import com.example.demo.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="*", allowedHeaders = "*")
public class PriceController {
    @Autowired
    private PriceService priceService;

    @RolesAllowed("admin")
    @GetMapping(value = "/prices")
    public List<Price> getPrices() {
        return priceService.getPrices();
    }

    @RolesAllowed("admin")
    @GetMapping("/prices/{id}")
    public ResponseEntity<Price> getPriceById(@PathVariable(value = "id") Long priceId) throws ResourceNotFoundException {
        Price price = priceService.findById(priceId).orElseThrow(() -> new ResourceNotFoundException("Price not found for this id : " + priceId));
        return ResponseEntity.ok().body(price);
    }

    @RolesAllowed("admin")
    @PostMapping("/admin/prices")
    public Price createPrice(@RequestHeader String Authorization, @RequestBody Price price) {
        return priceService.save(price);
    }

    @RolesAllowed("admin")
    @PutMapping("admin/prices/{id}")
    public ResponseEntity<Price> updatePrice(@PathVariable(value = "id") Long priceId,
                                                    @RequestBody Price priceDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(priceService.update(priceId, priceDetails));
    }

    @RolesAllowed("admin")
    @DeleteMapping("admin/prices/{id}")
    public Map<String, Boolean> deletePrice(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return priceService.delete(id);
    }
}
