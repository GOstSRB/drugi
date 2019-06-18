package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.User;

public interface UserService {
	
	User findOne(Integer id);
	List <User> findAll();
	User save(User user);
	List <User> saveAll(List<User>user);
	User delete(Integer id);
	void delete (List <Integer> id);
	List <User> findByFirstName (String firstName);
	List <User> findByLasttName (String lastName);

}
