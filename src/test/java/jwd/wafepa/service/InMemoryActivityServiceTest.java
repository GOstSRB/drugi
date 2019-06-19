package jwd.wafepa.service;

import java.util.ArrayList;
import java.util.List;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.impl.InMemoryActivityService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InMemoryActivityServiceTest {
	private ActivityService activityService;
	
	@Before
	public void setUp(){
		activityService = new InMemoryActivityService();
		
		activityService.save(new Activity("Swimming"));
		activityService.save(new Activity("Running"));
	}
	
	
	@Test
	public void testFindOne(){
		Activity activity = activityService.findOne(1L);
		Assert.assertNotNull(activity);
		Assert.assertEquals("Swimming", activity.getName());
	}
	
	@Test
	public void testFindAll(){
		List<Activity> activities = activityService.findAll();
		Assert.assertNotNull(activities);
		Assert.assertEquals(2, activities.size());
	}
	@Test
	public void remove() {
		activityService.delete(1l);
		List<Activity> result = activityService.findAll();
		Assert.assertEquals(1, result.size());
	}
	
	@Test
	public void testSaveAndRemoveIds () {
		Activity plugin = new Activity ("Plugin");
		Activity jumping = new Activity("Jumping");
		List<Activity> result = new ArrayList<>();
		result.add(plugin);
		result.add(jumping);
		activityService.save(result);

		Activity activity = activityService.findOne(2l);
		Assert.assertEquals("Running", activity.getName());

		List<Activity> results = activityService.findAll();
		Assert.assertEquals(4, results.size());
		
		List <Long> ids = new ArrayList<>();
		ids.add(1l);
		ids.add(2l);
		activityService.delete(ids);
		Assert.assertEquals(2, ids.size());
	}
}
