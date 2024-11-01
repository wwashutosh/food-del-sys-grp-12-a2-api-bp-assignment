package com.bits.api_bp.food_del_sys_g12.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("unused")
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/food_del_sys_g12")
public class CustomerController {

    @GetMapping(value = "/getAppVersion", produces = {"application/json"})
    public ResponseEntity<String> getAppVersion() {

        String appVersion = "1.0.0";

        try {
            System.out.println(appVersion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(appVersion);
    }


}
