package com.nahda.ot_services.service;

import com.nahda.ot_services.dao.UserInfoDAO;
import com.nahda.ot_services.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.nahda.ot_services.model.UserInfoDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepositoty;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserInfoDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            Optional<UserInfoDAO> userDetail = userInfoRepositoty.findByUsername(username); // Assuming 'email' is used as username
            if(userDetail.isPresent()) {
                return userDetail.map(UserInfoDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public UserInfoDAO addUser(UserInfoDAO userInfoDAO) {
        try {
            if(loadUserByUsername(userInfoDAO.getUsername()) == null){
                UUID userID = UUID.randomUUID();
                userInfoDAO.setUuid(userID);
                userInfoDAO.setPassword(encoder.encode(userInfoDAO.getPassword()));
                userInfoRepositoty.insertUser(userInfoDAO);
                Optional<UserInfoDAO> createdUser = userInfoRepositoty.findByUsername(userInfoDAO.getUsername());
                return createdUser.get();
            } else {
                throw new DuplicateKeyException("username already exists");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public UserInfoDAO getUserByUUID(UUID uuid) {
        Optional<UserInfoDAO> createdUser = userInfoRepositoty.findByUUID(uuid);
        return createdUser.get();
    }
}