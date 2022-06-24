package com.foodbox.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.foodbox.model.User;
import com.foodbox.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("origins=\"http://localhost:4200\"")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//get all users
	@GetMapping("/all")
	public ResponseEntity <List<User>> getUser()
	{	
		List<User> list = userService.getAllUsers();
		if (list.size()<=0) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}
	
	//get user by id
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUser(@PathVariable("userId") int userId){
		User user = userService.getUserById(userId);
		if (user==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(user));	
		}
	
    //get user by name
	@GetMapping("/search")
	public ResponseEntity<User> getName(@RequestParam String name){
		User user = userService.getUserByName(name);
		if (user==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(user));	
		}
	
	//new user handler
	@PostMapping("/signup")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User u =null;
		try {
			u = this.userService.addUser(user);
			System.out.println(user);
			System.out.println("Welcome " +u.getUsername()+ " to sporty shoes");
			return ResponseEntity.status(HttpStatus.CREATED).build();
			
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
			
	}
	//signin
//	@PostMapping("/login")
//	public String userLogin(@RequestParam String username, @RequestParam String password, @RequestParam String role)
//	{
//		if(userService.verifyUser(username, password, role))
//		{
//			return "Login Successfull, \nWelcome to sporty shoes";
//		}
//		else
//		{
//			return "Login Failed";
//		}
//	}
	
	@SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity<HashMap> userLogin(@RequestParam String username, @RequestParam String password) {
        
        HashMap<String, Object> response = new HashMap<>();
                 
        if(userService.userAuth(username, password)){
            User user = this.userService.findByUsername(username);
            response.put("data", user);
            response.put("status", 200);
            return new ResponseEntity<>(response,HttpStatus.OK);
                    
        } else {
            response.put("User not found", "404");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
        
    }
	//delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable("userId") int uId) {
		try {
			this.userService.deleteUser(uId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}		
		
	}
	
	//update user
	@PutMapping("/{userId}")
	public User updateUser(@RequestBody User user, @PathVariable("userId") int uId) {
		 this.userService.updateUser(user, uId);
		return user;
		
	}
	
	@PatchMapping("/{userId}/changePassword/{newPassword}")
	public String changePassword(@PathVariable int userId, @PathVariable String newPassword)
	{
		userService.changePassword(userId, newPassword);
		return "Password Changed Successfully";
	}
	
	@PatchMapping("/{userId}/changeName/{newName}")
	public String changeName(@PathVariable int userId, @PathVariable String newName)
	{
		userService.changeName(userId, newName);
		return "User name Changed Successfully";
	}

}
