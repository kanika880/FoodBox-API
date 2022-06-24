package com.foodbox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.foodbox.model.Role;
import com.foodbox.model.User;
import com.foodbox.model.UserRole;
import com.foodbox.repository.UserRepository;

@Service
public class UserService  {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private Role role;
	@Autowired
	private UserRole userRole;
	@Autowired
	private User user;
	
	//user sign in
//		public Boolean verifyUser(String username, String password, String role)
//		{
//			List<User> users = (List<User>) userRepository.findAll();
//			
//			for(User user : users)
//			{
//				if(user.getUsername().equals(username) && user.getPassword().equals(password) && user.getRole().equals(role)) 
//				{
//					return true;
//				}
//			}
//			return false;
//		}
	
	public List<User> getAllUsers(){
		List<User> list = (List<User>)this.userRepository.findAll();
		return list;
		
	}
	
	public User getUserById(int userId) {
		User user = null;
		try {
			user = userRepository.findByUserId(userId);
		}catch (Exception e) {
			e.printStackTrace();
		}
	return user;
		
	}
	
	public User getUserByName(String username) {
		User user = null;
		try {
			user = this.userRepository.findByUsername(username);
		}catch (Exception e) {
			e.printStackTrace();
		}
	return user;
		
	}
	
	public User addUser(User user1) {
		User results = userRepository.save(user1);
		return results;
		
	}
	
	public void deleteUser(int uId) {
		userRepository.deleteById(uId);
	}
	
	public void updateUser(User user, int userId) {
		user.setUserId(userId);
		userRepository.save(user);
	}
	
	public void changePassword(int userId, String newPassword)
	{
		User user = userRepository.findByUserId(userId);
		
		user.setPassword(newPassword);
		
		userRepository.save(user);
		
		System.out.println("Password Changed Successfully");
	}
	
	public void changeName(int userId, String newName)
	{
		User user = userRepository.findByUserId(userId);
		
		user.setUsername(newName);
		
		userRepository.save(user);
		
		System.out.println("User name Changed Successfully");
	}

	public boolean checkAdmin(String userLogin) {
		Iterable<User> users = this.userRepository.findAll();
		int flag = 0;
		for(User u : users) {
			if(u.getUsername().equals(userLogin)){
				flag = 1;
				break;
			}
		}
		if(flag == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean userAuth(String username, String password) {
		List<User> users = userRepository.findAll();
		for(User us : users) {
			if(us.getUsername().equals(username) && us.getPassword().equals(password)) {
				return true;
			}
		}
	
		return false;
	}
//	
//	public User getUserByUname(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public User findByUsername(String username) {
		List<User> users = userRepository.findAll();
		for(User us : users) {
			if(us.getUsername().equals(username)) 
				return us;
	    }
		return null;
	}

	}
