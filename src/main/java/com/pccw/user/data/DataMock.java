package com.pccw.user.data;

import java.util.concurrent.CopyOnWriteArrayList;

import com.pccw.user.entity.User;

/**
 * @ClassName DataMock
 * @Author Hanter
 * @Description user data mock
 * @Date 2021/01/23
 */
public class DataMock {
	public static final CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>() ;

	static {
		User user1 = new User("pccw1","Hanter","Huang", "hanter@126.com","123","hanter1");
        users.add(user1);
        User user2 = new User("pccw2","Jordan","Michael", "mj23@126.com","abc", "jordan23");
        users.add(user2);
        User user3 = new User("pccw3","Biden","Joe", "president@126.com","2323", "biden78");
        users.add(user3);
	}

}
