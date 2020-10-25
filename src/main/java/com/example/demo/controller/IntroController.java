package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Introduction;
import com.example.demo.service.IntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class IntroController {
    @Autowired
    private IntroService introService;

    @RolesAllowed("admin")
    @GetMapping(value = "/intros")
    public List<Introduction> getIntros() {
        return introService.getIntros();
    }

    @GetMapping("/newest-intro")
    public Introduction getNewestIntro() {
        return introService.getNewestIntro();
    }

    @RolesAllowed("admin")
    @GetMapping("/intros/{id}")
    public ResponseEntity<Introduction> getIntroById(@PathVariable(value = "id") Long introId) throws ResourceNotFoundException {
        Introduction intro = introService.findById(introId).orElseThrow(() -> new ResourceNotFoundException("Intro not found for this id : " + introId));
        return ResponseEntity.ok().body(intro);
    }

    @RolesAllowed("admin")
    @PostMapping("/admin/intros")
    public Introduction createIntro(@RequestHeader String Authorization, @RequestBody Introduction intro) {
        return introService.save(intro);
    }

    @RolesAllowed("admin")
    @PutMapping("admin/intros/{id}")
    public ResponseEntity<Introduction> updateIntro(@PathVariable(value = "id") Long introId,
                                           @RequestBody Introduction introDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(introService.update(introId, introDetails));
    }

    @RolesAllowed("admin")
    @DeleteMapping("admin/intros/{id}")
    public Map<String, Boolean> deleteIntro(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return introService.delete(id);
    }
}
