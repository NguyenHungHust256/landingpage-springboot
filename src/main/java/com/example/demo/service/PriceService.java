package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Introduction;
import com.example.demo.model.Price;
import com.example.demo.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;

    public List<Price> getPrices(){
        return priceRepository.findAll();
    }

    public Optional<Price> findById(Long id){
        return priceRepository.findById(id);
    }

    public Price save(Price price){
        return priceRepository.save(price);
    }

    public Price update(long id, Price priceUpdate) throws ResourceNotFoundException {
        Price price = priceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Price not found for this id : " + id));
        price.setBandwidth(priceUpdate.getBandwidth());
        price.setCost(priceUpdate.getCost());
        price.setNumAccountEmail(priceUpdate.getNumAccountEmail());
        price.setNumDatabase(priceUpdate.getNumDatabase());
        price.setTime(priceUpdate.getTime());
        price.setStorage(priceUpdate.getStorage());

        Price updatedPrice = priceRepository.save(price);
        return updatedPrice;
    }

    public Map<String, Boolean> delete(long id) throws ResourceNotFoundException {
        Price price = priceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Price not found for this id : " + id));

        priceRepository.delete(price);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }
}
