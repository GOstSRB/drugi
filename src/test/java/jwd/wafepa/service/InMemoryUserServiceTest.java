package jwd.wafepa.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jwd.wafepa.model.User;
import jwd.wafepa.service.impl.InMemoryUserService;

public class InMemoryUserServiceTest {
private UserService userService;

@Before
public void setUp() {
	
	userService = new InMemoryUserService ();
	
	userService.save(new User(null, "milan1rns@gmail.com", "pas", "Milan", "Radic"));
	userService.save(new User(null, "boskobole@gmail.com", "pas", "Bosko", "Radic"));
	userService.save(new User(null, "alekalek@gmail.com", "pas", "Aleksandra", "Radic"));
	userService.save(new User(null, "lukluk@gmail.com", "pas", "Luka", "Radic"));
}

@Test
public void testFindAll(){
	List<User> users = userService.findAll();
	Assert.assertNotNull(users);
	Assert.assertEquals(4, users.size());
}

@Test
public void testFindOne() {
	User user = userService.findOne(2);
	Assert.assertEquals("Bosko", user.getFirstName());
}

@Test
public void testSaveAll () {
	User jumping = new User(null, "Jumping", null);
	User climbing = new User (null, "Climbing", null);
	User plugin = new User (null, "Plugin", null);
	List<User> res = new ArrayList<>();
	res.add(jumping);
	res.add(climbing);
	res.add(plugin);
	
	userService.saveAll(res);
	Assert.assertEquals(3, res.size());
	
	List<User> results = userService.findAll();
	Assert.assertEquals(7, results.size());	
	
}

@Test
public void remove() {
	userService.delete(1);
	List<User> result = userService.findAll();
	Assert.assertEquals(3, result.size());
}


}
