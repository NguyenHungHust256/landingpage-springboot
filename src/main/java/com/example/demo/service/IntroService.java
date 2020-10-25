package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Introduction;
import com.example.demo.model.Menu;
import com.example.demo.repository.IntroductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class IntroService {
    @Autowired
    private IntroductionRepository introRepo;

    public List<Introduction> getIntros(){
        return introRepo.findAll();
    }

    public Introduction getNewestIntro(){
        return introRepo.findFirstByOrderByIdDesc();
    }
    public Optional<Introduction> findById(Long id){
        return introRepo.findById(id);
    }

    public Introduction save(Introduction intro){
        return introRepo.save(intro);
    }

    public Introduction update(long id, Introduction introUpdate) throws ResourceNotFoundException {
        Introduction intro = introRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Intro not found for this id : " + id));
        intro.setTitle(introUpdate.getTitle());
        intro.setDescription(introUpdate.getDescription());
        Introduction updatedIntro = introRepo.save(intro);
        return updatedIntro;
    }

    public Map<String, Boolean> delete(long id) throws ResourceNotFoundException {
        Introduction intro = introRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Intro not found for this id : " + id));

        introRepo.delete(intro);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }

}
