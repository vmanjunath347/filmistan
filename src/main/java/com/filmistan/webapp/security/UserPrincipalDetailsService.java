package com.filmistan.webapp.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.filmistan.webapp.entity.User;
import com.filmistan.webapp.exception.NotFoundException;
import com.filmistan.webapp.repository.UserRepository;

@Service
public class UserPrincipalDetailsService implements UserDetailsService{

		private UserRepository userRepository;
		
		public UserPrincipalDetailsService(UserRepository userRepository) {
		 this.userRepository = userRepository;
		}
		
		@Override
		public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
			List<User> users =  this.userRepository.findAll();
			
			User thisUser = new User();
			
			int userFound = 0;
			for(User user : users) {
				if(user.getUsername().equals(s)) {
					thisUser = user;
					userFound =1;
					break;
				}
			}
			
			if(userFound==0)
				throw new NotFoundException("user name wrong");
			
			UserPrincipal UserPrincipal = new UserPrincipal(thisUser);
			
			return UserPrincipal;
		}
}
