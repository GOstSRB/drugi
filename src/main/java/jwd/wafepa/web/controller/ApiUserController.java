package jwd.wafepa.web.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;

@RestController
@RequestMapping(value="/api/users")
public class ApiUserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers () {
		List <User> users = userService.findAll();
		return new ResponseEntity <> (users, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity <User> getUser(@PathVariable Integer id) {
		User user = userService.findOne(id);
		if (user == null) {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
			return new ResponseEntity <> (user, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity <User> delete(@PathVariable Integer id) {
		User deleted = userService.delete(id);
		
		if (deleted  == null) {
			return new ResponseEntity <> (HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity <> (deleted, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity <User> add(@RequestBody User users) {
		User presisted = userService.save(users);
		if (presisted != null) {
			System.out.println("uspesno dodat user");
		}
		return new ResponseEntity <> (presisted, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity <User> edit(@PathVariable Integer id, @RequestBody User user) {
		User edited = userService.save(user);
		return new ResponseEntity <> (edited, HttpStatus.OK);
	}
	
	@PostConstruct
	public void init() {
	userService.save(new User(null, "milan1rns@gmail.com", "pas", "Milan", "Radic"));
	userService.save(new User(null, "boskobole@gmail.com", "pas", "Bosko", "Radic"));
	userService.save(new User(null, "alekalek@gmail.com", "pas", "Aleksandra", "Radic"));
	userService.save(new User(null, "lukluk@gmail.com", "pas", "Luka", "Radic"));
	}
	
}
