package com.javahelps.mysqlrestservice.controller;
import com.javahelps.mysqlrestservice.Repository.*;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import com.javahelps.mysqlrestservice.data.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.javahelps.mysqlrestservice.files.UserService;
@RestController
public class HomeController{

	@Autowired
	private UserService userService;
	@GetMapping("/user")
	public List<User> index(){
		return userService.getUsers();
	}
	@RequestMapping(value = "/Testing", method = RequestMethod.GET)
	 public String authenticateUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("coming in controller    " +username +" : "+ password);  
        return "success";
    }

	//public String hello(@RequestParam(name="EmpID", defaultValue="world")String EmpID) {
		//return EmpID;
	//}
	
	/*
	@GetMapping("/user/{EmpID}")
	public User show(@PathVariable String empID, @RequestBody Map<String, String> body) {
		String userID =  body.get("EmpID");
		return userService.findOne(userID);
	}
	@PostMapping("/user/search")
	public List<User> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("EmpID");
        return dataRepository.findByEmpID(searchTerm);
    }
    /*
	

	@RequestMapping("/user")
	public List<User> index(){
		return dataRepository.findAll();
	}
	
	
@RequestMapping(value = "/{empID}", method = RequestMethod.GET)
    public ResponseEntity<User> findEmpById(@PathVariable String empID) {
        List<User> user = userService.findById(empID);
         return new ResponseEntity(user, HttpStatus.OK);	
	}
	
	public ResponseEntity<?> getAll(){
	     List<User> result = dataRepository.findAll(); 
	     return new ResponseEntity(result, HttpStatus.OK);	     
	}
	
	@PostMapping("/user/search")
	public ResponseEntity<?> addorUpdateUser(@RequestBody User user){
		dataRepository.saveOrUpdateUser(user);
		return new ResponseEntity("User added successfully", HttpStatus.OK);
	}
	import java.net.URI;
import java.util.List;
import java.util.UUID;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
@Path("/messages")
public class MessageResource {
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createMessage(@FormParam("name") String name,
                                @FormParam("message") String message,
                                @FormParam("thelist") List<String> list) {
        if(name.trim().length() > 0 && message.trim().length() > 0 && !list.isEmpty()) {
            return Response.created(URI.create("/messages/" + String.valueOf(UUID.randomUUID()))).entity(
                    name+ ": " + message + " --> the items: " + list.get(0) + " - " + list.get(1)).build();
            // This is a more real world "return"
            //return Response.created(URI.create("/messages/" + String.valueOf(UUID.randomUUID()))).build();
        }
        return Response.status(Response.Status.PRECONDITION_FAILED).build();
    }
}
	*/
}