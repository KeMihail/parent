package by.itacademy.keikom.taxi.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;

public class LegalEntityServicesTest extends AbstractServicesTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(LegalEntityServicesTest.class);

	@Autowired
	private ILegalEntityServices services;

	private List<LegalEntity> list;

	@Test
	public void testGRUD() {

		LegalEntity legalEntity = null;

		try {
			services.save(legalEntity);
			Assert.fail();
		} catch (Exception e) {
			LOGGER.error("you cannot save the object entered all of the data");
		}

		legalEntity = createLegalEntity();

		services.save(legalEntity);
		Assert.assertNotNull(services.get(legalEntity.getId()));

		LegalEntity legalEntity1 = services.get(legalEntity.getId());
		Assert.assertEquals(legalEntity1.getId(), legalEntity.getId());
		Assert.assertEquals(legalEntity1.getName(), legalEntity.getName());
		Assert.assertEquals(legalEntity1.getAddress(), legalEntity.getAddress());
		Assert.assertEquals(legalEntity1.getPhone_number(), legalEntity.getPhone_number());
		Assert.assertEquals(legalEntity1.getEmail(), legalEntity.getEmail());

		legalEntity.setName("OAO Такси");
		services.save(legalEntity);
		Assert.assertNotNull(services.get(legalEntity.getId()));

		LegalEntity legalEntity2 = services.get(legalEntity.getId());
		Assert.assertEquals(legalEntity2.getId(), legalEntity.getId());
		Assert.assertEquals(legalEntity2.getName(), legalEntity.getName());
		Assert.assertEquals(legalEntity2.getAddress(), legalEntity.getAddress());
		Assert.assertEquals(legalEntity2.getPhone_number(), legalEntity.getPhone_number());
		Assert.assertEquals(legalEntity2.getEmail(), legalEntity.getEmail());

		list = services.getAll();
		Assert.assertNotNull(list);

		services.remove(legalEntity.getId());
		Assert.assertNull(services.get(legalEntity.getId()));
	}
}
