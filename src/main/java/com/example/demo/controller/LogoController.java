package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Logo;
import com.example.demo.repository.LogoRepository;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LogoController {

    @Autowired
    private LogoRepository logoRepository;

    @RolesAllowed("admin")
    @GetMapping("/admin/logos")
    public List<Logo> getLogos() {
        return logoRepository.findAll();
    }

    @GetMapping("/newest-logo")
    public Logo getNewestLogo() {
        return logoRepository.findFirstByOrderByIdDesc();
    }

    @RolesAllowed("admin")
    @PostMapping("/admin/logos")
    public Logo createEmployee(@RequestBody Logo logo) {
        return logoRepository.save(logo);
    }

    @RolesAllowed("admin")
    @GetMapping("/admin/logos/{id}")
    public ResponseEntity<Logo> getLogoById(@PathVariable(value = "id") Long logoId) throws ResourceNotFoundException {
        Logo logo = logoRepository.findById(logoId).orElseThrow(() -> new ResourceNotFoundException("Logo not found for this id : " + logoId));
        return ResponseEntity.ok().body(logo);
    }

    @RolesAllowed("admin")
    @PutMapping("admin/logos/{id}")
    public ResponseEntity<Logo> updateEmployee(@PathVariable(value = "id") Long logoId,
                                                   @Validated @RequestBody Logo employeeDetails) throws ResourceNotFoundException {
        Logo logo = logoRepository.findById(logoId).orElseThrow(
                () -> new ResourceNotFoundException("Logo not found for this id : " + logoId));
        logo.setText(employeeDetails.getText());
        final Logo updatedLogo = logoRepository.save(logo);
        return ResponseEntity.ok(updatedLogo);
    }

    @DeleteMapping("admin/logos/{id}")
    public Map<String, Boolean> deleteLogo(@PathVariable(value = "id") Long logoId) throws ResourceNotFoundException {
        Logo logo = logoRepository.findById(logoId)
                .orElseThrow(() -> new ResourceNotFoundException("Logo not found for this id : " + logoId));

        logoRepository.delete(logo);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }
}
