package com.javahelps.mysqlrestservice.data;
import java.util.List;
import com.javahelps.mysqlrestservice.Repository.Repositorydata;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javahelps.mysqlrestservice.files.UserService;
@Service
public class UserServiceImpl implements UserService {
@Autowired
private Repositorydata dataRepository;
	@Override
	public List<User> getUsers() {
		List<User> out=new ArrayList<User>();
		dataRepository.findAll().forEach(user -> out.add(user));
		// TODO Auto-generated method stub
		return out;
	}

}
