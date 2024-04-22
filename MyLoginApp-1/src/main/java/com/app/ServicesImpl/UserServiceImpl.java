package com.app.ServicesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Entities.User;
import com.app.Exception.UsernameNotAvailableException;
import com.app.Repo.UserRepository;
import com.app.Services.UserService;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUserInput(Long id, String userInput) {
        // Retrieve user by userId
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Set userInput for the user
            user.setUserInput(userInput);
            // Save the modified user
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found for id: " + id);
        }
    }
    //===================================================
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public boolean isValidUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password) && user.getUsername().equals(username);
    }
    
   
   
    //====================================================
    @Override
    public Long authenticateAndGetUserId(String username, String password) {
        
        User user = userRepository.findByUsername(username);
        
        if (user != null && user.getPassword().equals(password)) {
            return user.getId(); 
        } else {
            return null; 
        }
    }
    
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public void save(User user) {
        userRepository.save(user);
    }
    //==========================for duplicate user===========================
    @Override
    public boolean isUsernameAvailable(String username) {
        return userRepository.findByUsername(username) == null;
        
    }


    @Override
    public void registerUser(User user) throws UsernameNotAvailableException {
        if (!isUsernameAvailable(user.getUsername())) {
            throw new UsernameNotAvailableException("Username '" + user.getUsername() + "' is already taken.");
        }

        userRepository.save(user);
    }
}
