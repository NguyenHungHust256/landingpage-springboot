package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Feature;
import com.example.demo.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FeatureController {
    @Autowired
    private FeatureService featureService;

    @RolesAllowed("admin")
    @GetMapping(value = "/features")
    public List<Feature> getFeatures() {
        return featureService.getFeatures();
    }

    @RolesAllowed("admin")
    @GetMapping("/features/{id}")
    public ResponseEntity<Feature> getFeatureById(@PathVariable(value = "id") Long featureId) throws ResourceNotFoundException {
        Feature feature = featureService.findById(featureId).orElseThrow(() -> new ResourceNotFoundException("Intro not found for this id : " + featureId));
        return ResponseEntity.ok().body(feature);
    }

    @RolesAllowed("admin")
    @PostMapping("/admin/features")
    public Feature createFeatures(@RequestHeader String Authorization, @RequestBody Feature feature) {
        return featureService.save(feature);
    }

    @RolesAllowed("admin")
    @PutMapping("admin/features/{id}")
    public ResponseEntity<Feature> updateFeature(@PathVariable(value = "id") Long featureId,
                                                    @RequestBody Feature feature) throws ResourceNotFoundException {
        return ResponseEntity.ok(featureService.update(featureId, feature));
    }

    @RolesAllowed("admin")
    @DeleteMapping("admin/features/{id}")
    public Map<String, Boolean> deleteFeature(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return featureService.delete(id);
    }
}
