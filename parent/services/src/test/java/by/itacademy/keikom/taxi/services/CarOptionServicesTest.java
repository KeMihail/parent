package by.itacademy.keikom.taxi.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.services.impl.CarOptionServicesImpl;

public class CarOptionServicesTest extends AbstractServicesTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarOptionServicesTest.class);

	@Autowired
	private ICarOptionServices services;
	private List<CarOption> list;

	@Test
	public void testGRUD() {

		CarOption carOption = null;

		try {
			services.save(carOption);
			Assert.fail();

		} catch (Exception e) {
			LOGGER.error("you cannot save the object entered all of the data");
		}

		carOption = createCarOption();
		services.save(carOption);
		Assert.assertNotNull(services.get(carOption.getId()));

		CarOption carOption1 = services.get(carOption.getId());
		Assert.assertEquals(carOption1.getId(), carOption.getId());
		Assert.assertEquals(carOption1.getName(), carOption.getName());

		carOption.setName("Коженный салон");
		services.save(carOption);
		Assert.assertNotNull(services.get(carOption.getId()));

		CarOption carOption2 = services.get(carOption.getId());
		Assert.assertEquals(carOption2.getId(), carOption.getId());
		Assert.assertEquals(carOption2.getName(), carOption.getName());

		list = services.getAll();
		Assert.assertNotNull(list);

		services.remove(carOption.getId());
		Assert.assertNull(services.get(carOption.getId()));
	}
}
