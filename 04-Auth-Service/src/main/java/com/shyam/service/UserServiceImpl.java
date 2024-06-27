package com.shyam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shyam.entity.User;
import com.shyam.repo.UserRepo;
import com.shyam.utils.FileUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authManager;

	

	@Override
	public User addUser(User user, MultipartFile file) throws Exception {

		User u = userRepo.findByEmail(user.getEmail());
		if(u==null) {
		String fileCode = FileUtils.saveFile(file.getName(), file);
		user.setUserpic(fileCode);
		user.setPwd(passwordEncoder.encode(user.getPwd()));

		return userRepo.save(user);
		}else {
			return u;
		}
	}

	@Override
	public User login(User user) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(),
				user.getPwd());

		Authentication authenticate = null;

		try {
			authenticate = authManager.authenticate(token);
			if (authenticate.isAuthenticated()) {
				return userRepo.findByEmail(user.getEmail());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUserById(Integer userId) {
		return userRepo.findById(userId).orElseThrow();
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public User deleteUserById(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow();
		if(user!=null) {
			userRepo.deleteById(userId);
			return user;
		}else {
			return null;
		}
	}

}
