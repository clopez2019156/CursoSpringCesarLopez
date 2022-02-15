package com.bolsadeideas.springboot.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.models.DAO.IUserDAO;
import com.bolsadeideas.springboot.app.models.entity.Role;
import com.bolsadeideas.springboot.app.models.entity.User;

@Service("jpaUserDetailService")
public class JpaUserDetailService implements UserDetailsService{
	
	@Autowired
	IUserDAO userDao;
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailService.class);

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userDao.findByUsername(username);
		
		if(user == null) {
			logger.error("Error login: the user does not exist ".concat(username));
			throw new UsernameNotFoundException("Username: "+ username + "not exists");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(Role role: user.getRoles()) {
			logger.info("ROLE: ".concat(role.getAuthority()));
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
			
		}
		
		if(authorities.isEmpty()) {
			logger.error("Error login: ".concat(username).concat("has no roles assigned"));
			throw new UsernameNotFoundException("Username: "+" does not exist in the system");
		}
		
		return new org.springframework.security.core.userdetails.
				User(username, user.getPassword(), user.getEnabled(), true, true, true, authorities);
	}
	
	

}
