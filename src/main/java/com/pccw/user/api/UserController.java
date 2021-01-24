package com.pccw.user.api;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pccw.user.entity.Heartbeat;
import com.pccw.user.entity.User;
import com.pccw.user.entity.UserDTO;
import com.pccw.user.service.UserService;

@Controller
@RequestMapping(value = "/")
public class UserController {
	
	
	@Value("${server.servlet.context-path}")
	private String contextPath;
	
	
	@Value("${springdoc.api-docs.path}")
	private String customApiDocsPath;
	
	@Value("${springdoc.swaggerui.url.template}")
	private String swaggerUiUrlTemplate;
	
	@Autowired
	private UserService userService;
	
	/**
     * Home page, Redirect to GET /docs
     * @Author Hanter
     * @Date 2021/01/23
     * @Param null
     * @Return redirect to /docs
    */
    @GetMapping(value = "")
    public String toDocs() throws Exception{
        
    	return "redirect:/docs";
    	
    }
    
    /**
     * static html page to introduce on how to use the endpoints 
     * and endpoint request/response details
     * @Author Hanter
     * @Date 2021/01/23
     * @Param null
     * @Return redirect to /docs
    */
    @GetMapping(value = "/heartbeat")
    @ResponseBody
    public Heartbeat heartbeat() throws Exception{
    	return userService.getHeartbeat();
    }
    
    // 2
    @GetMapping(value = "/docs")
    public String docs() throws Exception{
    	
    	return "redirect:" + customApiDocsPath;
    }
    
    // 3
    @GetMapping(value = "/swagger-ui\\.html")
    public String swaggerUI() throws Exception{
    	String redirect = String.format(swaggerUiUrlTemplate, contextPath, customApiDocsPath);
    	return "redirect:/"+redirect;
    	
    }
    
    // 4
    @PostMapping(value = "/user")
    @ResponseBody
    public String createUser(@RequestBody User user) throws Exception{
        return userService.createUser(user);
    	
    }
    
    // 5
    @PostMapping(value = "/user/login")
    @ResponseBody
    public String login(@RequestParam(value="email",required = true) String email, @RequestParam(value="password",required = true) String password) throws Exception{
        return userService.login(email, password);
    	
    }
    
    // 6
    @GetMapping(value = "/user")
    @ResponseBody
    public UserDTO getUserInfo(@RequestParam(value="token",required = true) String token) throws Exception{
        return userService.getUserInfoByToken(token);
    	
    }
    
    // 7
    @PostMapping(value = "/user/logout")
    @ResponseBody
    public void logout(@RequestParam(value="token",required = true) String token) throws Exception{
        userService.logout(token);
    	
    }
    
}
