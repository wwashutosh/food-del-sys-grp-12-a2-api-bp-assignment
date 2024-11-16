package com.bits.api_bp.food_del_sys_g12.services;

import com.bits.api_bp.food_del_sys_g12.entities.Userdetail;
import com.bits.api_bp.food_del_sys_g12.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@ComponentScan
public class UserManagingService {

    @Autowired
    private UserDetailsRepository userDetailsRepo;

    public Optional<Userdetail> getUserDetails(String userid) {
        return userDetailsRepo.findById(userid);
    }

    public Userdetail getUserDetailsByEmail(String email) {
        return userDetailsRepo.findByEmailaddress(email).get();
    }

    public void updateUser(String userId, Userdetail updatedUser) {
        Optional<Userdetail> existingUser = userDetailsRepo.findById(userId);
        if (existingUser.isPresent()) {
            Userdetail user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setEmailaddress(updatedUser.getEmailaddress());
            user.setRole(updatedUser.getRole());
            userDetailsRepo.save(user);
        }
    }

    public void deactivateUser(String userId) {
        Optional<Userdetail> user = userDetailsRepo.findById(userId);
        user.ifPresent(u -> {
            u.setActive(false);
            userDetailsRepo.save(u);
        });
    }
}