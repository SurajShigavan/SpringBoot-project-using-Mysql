package com.app.Controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.Entities.User;
import com.app.Exception.UsernameNotAvailableException;
//import com.app.Exception.UsernameNotAvailableException;
import com.app.Repo.UserRepository;
//import com.app.Services.UserService;
import com.app.Services.UserService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
//@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;
   

    
    @GetMapping("/")
    public String registerForm() {
        return "index";
    }
    
    @GetMapping("/register")
	public String registerMapping() {
		return "register";
	}
    
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return "success";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
    //===============================working on  Postman ========================================================================================================
  
 
//    @PostMapping("/login")
//    public String createUser(@RequestBody User user) {
//        // Save the User object to the database
//        userRepository.save(user);
//
//        return "Data saved successfully";
//    }
    
        //===============================working on  browser========================================================================================================
    
    
//    @PostMapping("/login")
//    public String createUser(@RequestBody User user) {
//        // Save the User object to the database
//        userRepository.save(user);
//
//        return "success";
//    }
    
    
   
    @PostMapping("/reg")
    public String resister(@RequestParam String username, @RequestParam String password, Model model) {
        // Save data to database
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);

        return "successafterreg";
    }
    
    
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    @GetMapping("/all")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @PostMapping("/submit")
    public String submitUserInput(@RequestParam Long id, @RequestParam String userInput, Model model) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUserInput(userInput);
            userRepository.save(user);
            return "success";
        } else {
          
            return "error";
        }
    }
    
    @PostMapping("/saveUserInput")
    public String saveUserInput(@PathVariable Long id, @PathVariable String userInput) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Save userInput for the user
            user.setUserInput(userInput);
            userRepository.save(user);
            return "success"; 
        } else {
            return "error"; 
        }
    }
    
    @GetMapping("/previous-page")
    public String previousPage(HttpServletRequest request) {
       
        String id = request.getParameter("id");
        System.out.println("Retrieved id from previous page: " + id);
        return "my-view";
    }
   

    @PostMapping("/login2")
  
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session,Model model) {
        Long loggedInUserId = userService.authenticateAndGetUserId(username, password);
        if (userService.isValidUser(username, password)&& loggedInUserId != null) {
            session.setAttribute("loggedInUserId", loggedInUserId);
            return "success";
        }else {
          model.addAttribute("error", "Invalid username or password");
          return "index";
      }
    }
   
    @PostMapping("/save")
    public String saveUserInput(@RequestParam String userInput, HttpSession session,Model model) {
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        if (loggedInUserId != null) {
            User user = userService.getUserById(loggedInUserId);
            if (user != null) {
                user.setUserInput(userInput);
                userService.save(user);
              
                model.addAttribute("success", "Your Feedback Saved Successfully.");
                return "success";
            }
        }
        return "redirect:/login";
    }



//==========================for duplicate user===========================

    @PostMapping("/register1")
    public String registerUser(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            if (!userService.isUsernameAvailable(username) ){
                throw new UsernameNotAvailableException("Username '" + username + "' is already taken.");
            }

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            userService.registerUser(user);
            
            return "successafterreg";
        } catch (UsernameNotAvailableException e) {
            model.addAttribute("errorMessage", e.getMessage());
            
            return "register";
        }
    }


}

