package com.bits.api_bp.food_del_sys_g12.controller;

import com.bits.api_bp.food_del_sys_g12.entities.Userdetail;
import com.bits.api_bp.food_del_sys_g12.services.UserManagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/food_del_sys_g12")
@CrossOrigin(origins = "http://localhost:3000")
@SuppressWarnings("unused")
public class UserDetailsController {

    @Autowired
    private UserManagingService userManagingService;

    @GetMapping(value = "/getUserDetails", produces = {"application/json"})
    public ResponseEntity<Optional<Userdetail>> getUserDetails(@RequestParam String userid) {
        Optional<Userdetail> user = Optional.of(new Userdetail());
        try {
            user = userManagingService.getUserDetails(userid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(user);
    }
}
