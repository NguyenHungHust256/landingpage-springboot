package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Menu;
import com.example.demo.model.Review;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.ReviewRepository;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviews(){
        return reviewRepository.findAll();
    }

//    public Optional<Menu> findById(Long id){
//        return menuRepository.findById(id);
//    }
//
//    public Menu save(Menu menu){
//        return menuRepository.save(menu);
//    }
//
//    public Menu update(long id, Menu menuUpdate) throws ResourceNotFoundException {
//        Menu menu = menuRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Menu not found for this id : " + id));
//        menu.setName(menuUpdate.getName());
//        menu.setDescription(menuUpdate.getDescription());
//        menu.setTitle(menuUpdate.getTitle());
//        Menu updatedMenu = menuRepository.save(menu);
//        return updatedMenu;
//    }
//
//    public Map<String, Boolean> delete(long id) throws ResourceNotFoundException {
//        Menu menu = menuRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Menu not found for this id : " + id));
//
//        menuRepository.delete(menu);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("delete", Boolean.TRUE);
//        return response;
//    }
}
