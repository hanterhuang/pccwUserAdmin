package com.pccw.user.service;

import com.pccw.user.entity.Heartbeat;
import com.pccw.user.entity.User;
import com.pccw.user.entity.UserDTO;

public interface UserService {
	
	/**
     * create user
     * @Author Hanter
     * @Date 2021/01/23
     * @Param void
     * @Return void
    **/
	Heartbeat getHeartbeat();
	
	/**
     * user login
     * @Author Hanter
     * @Date 2021/01/23
     * @Param User
     * @Return token
    **/
	String createUser(User user);
	
	/**
     * user login
     * @Author Hanter
     * @Date 2021/01/23
     * @Param email
     * @Param password
     * @Return token
    **/
	String login(String email, String password);
	
	/**
     * Use the token response from /user/login to query the user profile
     * @Author Hanter
     * @Date 2021/01/23
     * @Param token
     * @Return User
    **/
	UserDTO getUserInfoByToken(String token);
	
	/**
     * user logout
     * @Author Hanter
     * @Date 2021/01/23
     * @Param token
     * @Return void
    **/
	void logout(String token);
	
}
