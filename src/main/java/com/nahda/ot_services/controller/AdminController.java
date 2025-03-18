package com.nahda.ot_services.controller;

import com.nahda.ot_services.dao.UserInfoDAO;
import com.nahda.ot_services.service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    UserInfoService userService;

    public AdminController(UserInfoService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<UserInfoDAO> addUser(@Valid @RequestBody UserInfoDAO userInfoDAO) {
        return ResponseEntity.ok(userService.addUser(userInfoDAO));
    }

    @GetMapping("/user/{uuid}")
    public ResponseEntity<UserInfoDAO> getUserByUUID(@PathVariable UUID uuid) {
        return ResponseEntity.ok(userService.getUserByUUID(uuid));
    }

}
