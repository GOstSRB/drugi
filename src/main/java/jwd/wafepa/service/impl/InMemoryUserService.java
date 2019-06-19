package jwd.wafepa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;

@Service
public class InMemoryUserService implements UserService{
	
	
	private int nextId = 1;
	private Map <Integer, User> users = new HashMap<>();
	
	
	@Override
	public User findOne(Integer id) {
		return users.get(id);
	}

	@Override
	public List<User> findAll() {
		return new ArrayList <User> (users.values());
	}

	@Override
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(nextId++);
		}
		users.put(user.getId(), user);
		return user;
	}
		

	@Override
	public List<User> saveAll(List<User> user) {
		List<User> ret = new ArrayList<>();
		
		for (User u : user) {
			User saved = save(u);
			
			if (saved!=null) {
				ret.add(saved);
			}
		}
		return ret;
	}

	@Override
	public User delete(Integer id) {
		User user = users.get(id);
		if (user !=null) {
			users.remove(id);
		}
		return user;
	}

	@Override
	public void delete(List<Integer> ids) {
		for (Integer id : ids ) {
			delete(id);
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public List<User> findByFirstName(String firstName) {
		if (users.containsValue(firstName)) {
		return new ArrayList<User>(users.values());
		}
		return null;
	}

	@Override
	public List<User> findByLasttName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

}
