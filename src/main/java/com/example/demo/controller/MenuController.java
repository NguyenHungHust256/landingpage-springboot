package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Menu;
import com.example.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/menus")
    public List<Menu> getMenu() {
        return menuService.getMenus();
    }

    @GetMapping("/menus/{id}")
    public ResponseEntity<Menu> getLogoById(@PathVariable(value = "id") Long menuId) throws ResourceNotFoundException {
        Menu menu = menuService.findById(menuId).orElseThrow(() -> new ResourceNotFoundException("Logo not found for this id : " + menuId));
        return ResponseEntity.ok().body(menu);
    }

    @GetMapping("/menus/name/{name}")
    public ResponseEntity<Menu> getLogoByName(@PathVariable(value = "name") String name) throws ResourceNotFoundException {
        Menu menu = menuService.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Menu not found for this name : " + name));
        return ResponseEntity.ok().body(menu);
    }


    @RolesAllowed("admin")
    @PostMapping("/admin/menus")
    public Menu createMenu(@RequestHeader String Authorization, @RequestBody Menu logo) {
        return menuService.save(logo);
    }

    @RolesAllowed("admin")
    @PutMapping("admin/menus/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable(value = "id") Long menuId,
                                               @RequestBody Menu menuDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(menuService.update(menuId, menuDetails));
    }

    @RolesAllowed("admin")
    @DeleteMapping("admin/menus/{id}")
    public Map<String, Boolean> deleteMenu(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
       return menuService.delete(id);
    }
}
