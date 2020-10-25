package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Feature;
import com.example.demo.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FeatureService {
    @Autowired
    private FeatureRepository featureRepository;

    public List<Feature> getFeatures(){
        return featureRepository.findAll();
    }

    public Optional<Feature> findById(Long id){
        return featureRepository.findById(id);
    }

    public Feature save(Feature feature){
        return featureRepository.save(feature);
    }

    public Feature update(long id, Feature featureUpdate) throws ResourceNotFoundException {
        Feature feature = featureRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Feature not found for this id : " + id));
        feature.setTitle(featureUpdate.getTitle());
        feature.setDescription(feature.getDescription());
        feature.setIcon(feature.getIcon());
        Feature updatedFeature = featureRepository.save(feature);
        return updatedFeature;
    }

    public Map<String, Boolean> delete(long id) throws ResourceNotFoundException {
        Feature intro = featureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feature not found for this id : " + id));

        featureRepository.delete(intro);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }
}
