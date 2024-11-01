package com.bits.api_bp.food_del_sys_g12.services;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDService {
    // Method to return a string uuid of length 6
    public String generateShortUUID() {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString().replace("-", "");
        return uuidString.substring(0, 6);
    }
}