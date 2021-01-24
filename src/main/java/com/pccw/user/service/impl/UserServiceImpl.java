package com.pccw.user.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pccw.user.data.DataMock;
import com.pccw.user.entity.Heartbeat;
import com.pccw.user.entity.User;
import com.pccw.user.entity.UserDTO;
import com.pccw.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Value("${useradmin.version}")
	private String version;

	@Override
	public Heartbeat getHeartbeat() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
		String curTime = df.format(new Date());
		Heartbeat pong = new Heartbeat();
		pong.setReleasedAt(curTime);
		pong.setVersion(version);
		
		return pong;
	}
	
	@Override
	public String createUser(User user) {
		String id = getUuid();
		user.setId(id);
		DataMock.users.add(user);
		return id;
	}

	@Override
	public String login(String email, String password) {
		String token = null;
	    User u = DataMock.users.stream()
		.filter(user -> (user.getEmail().equals(email) && user.getPassword().equals(password)))
		.findAny().orElse(null);
	    
	    if(u != null) {
	    	
	        token = getUuid();
	    	
	    	for(User u2: DataMock.users) {
	    		if(u2.getEmail().equals(email) && u2.getPassword().equals(password)){
    				u2.setToken(token);
    				break;
    			}
	    	}
	    }
	    return token;
	}

	@Override
	public UserDTO getUserInfoByToken(String token) {
		User u = DataMock.users.stream()
				.filter(user -> (user.getToken().equals(token)))
				.findAny().orElse(null);
		UserDTO userToShow = new UserDTO();
		BeanUtils.copyProperties(u, userToShow);
		return userToShow;
	}

	@Override
	public void logout(String token) {
		for(User u2: DataMock.users) {
    		if(u2.getToken().equals(token)){
				u2.setToken(null);
				break;
			}
    	}
		
	}
	
	private String getUuid() {
		UUID uuid = UUID.randomUUID();
        return uuid.toString();
	}
}
