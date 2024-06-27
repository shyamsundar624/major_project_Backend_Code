package com.shyam.rest;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shyam.constant.AppConstants;
import com.shyam.entity.User;
import com.shyam.prop.AppProperties;
import com.shyam.response.ApiResponse;
import com.shyam.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class UserRestController {

	private static Logger log=LoggerFactory.getLogger(UserRestController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AppProperties props;
	
	@PostMapping("register")
	public ResponseEntity<ApiResponse<User>> createUser(@RequestParam("user") String userJson, 
			@RequestParam("file") MultipartFile file) throws Exception{
	log.info("User Registration process started");	
		ApiResponse<User> response=new ApiResponse<>();
	Map<String,String> messages = props.getMessages();
	
		ObjectMapper mapper=new ObjectMapper();
		User user = mapper.readValue(userJson, User.class);
		
		User addedUser = userService.addUser(user, file);
		
		if(addedUser!=null) {
			response.setStatus(200);
			response.setMessage(messages.get(AppConstants.USER_REG));
			response.setData(addedUser);
		}else {
			response.setStatus(500);
			response.setMessage(messages.get(AppConstants.USER_REG_ERR));
		}
		log.info("User Registration process completed");
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<User>> login(@RequestBody User user){
		log.info("User Login Process Started");
		ApiResponse<User> response=new ApiResponse<>();
		Map<String,String> messages = props.getMessages();
		User login = userService.login(user);
		
		if(login!=null) {
			response.setStatus(200);
			response.setMessage(messages.get(AppConstants.LOGIN));
			response.setData(login);
		}else {
			log.info("Login Failed");
			response.setStatus(500);
			response.setMessage(messages.get(AppConstants.LOGIN_ERR));
		}
		log.info("User Login Process completed");
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	@GetMapping("/test")
	public String test() {
		return "For testing purpose";
	}
}
