package com.nahda.ot_services.controller;

import com.nahda.ot_services.dao.UserInfoDAO;
import com.nahda.ot_services.service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    UserInfoService userService;

    @PostMapping("/user")
    public ResponseEntity<UserInfoDAO> getUserById(@Valid @RequestBody UserInfoDAO userInfoDAO) {
        return ResponseEntity.ok(userService.addUser(userInfoDAO));
    }

}
