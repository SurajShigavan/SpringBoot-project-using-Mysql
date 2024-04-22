package com.app.Services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Entities.User;
import com.app.Exception.UsernameNotAvailableException;
import com.app.Repo.UserRepository;

@Service
public interface UserService {


    public User findByUsername(String username) ;

    public boolean isValidUser(String username, String password) ;
       
    void saveUserInput(Long id, String userInput);
    //===============================
    Long authenticateAndGetUserId(String username, String password);
    
    User getUserById(Long id);
    
    void save(User user);

//==========================for duplicate user===========================
    boolean isUsernameAvailable(String username);
    void registerUser(User user) throws UsernameNotAvailableException;
    


}