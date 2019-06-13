package by.itacademy.keikom.taxi.services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.itacademy.keikom.taxi.dao.dbmodel.Brand;
import by.itacademy.keikom.taxi.dao.dbmodel.Car;
import by.itacademy.keikom.taxi.dao.dbmodel.Car2CarOption;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOption;
import by.itacademy.keikom.taxi.dao.dbmodel.LegalEntity;
import by.itacademy.keikom.taxi.dao.dbmodel.Model;
import by.itacademy.keikom.taxi.dao.dbmodel.CarOrder;
import by.itacademy.keikom.taxi.dao.dbmodel.OrderAssessment;
import by.itacademy.keikom.taxi.dao.dbmodel.Rate;
import by.itacademy.keikom.taxi.dao.dbmodel.ServiceItem;
import by.itacademy.keikom.taxi.dao.dbmodel.User;
import by.itacademy.keikom.taxi.dao.enums.BodyType;
import by.itacademy.keikom.taxi.dao.enums.CarKit;
import by.itacademy.keikom.taxi.dao.enums.CarStatus;
import by.itacademy.keikom.taxi.dao.enums.EServiceItem;
import by.itacademy.keikom.taxi.dao.enums.EngineType;
import by.itacademy.keikom.taxi.dao.enums.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-context.xml")
public abstract class AbstractServicesTest {

	public Brand createBrand() {

		Brand brand = new Brand();
		brand.setName("Рено");

		return brand;
	}

	public Model createModel(Brand brand) {

		Model model = new Model();
		model.setName("Лагуна");
		model.setBodyType(BodyType.Minivan);
		model.setBrand(brand);
		model.setCarKit(CarKit.Classic);
		model.setEngineType(EngineType.Diesel);
		return model;
	}

	public LegalEntity createLegalEntity() {

		LegalEntity legalEntity = new LegalEntity();
		legalEntity.setName("ООО Такси");
		legalEntity.setAddress("г.Гродно");
		legalEntity.setEmail("Taxi@tut.by");
		legalEntity.setPhoneNumber("80297875512");
		return legalEntity;
	}

	public User createUser() throws ParseException {

		User user = new User();
		user.setName("Миша");
		user.setAddress("г.Гродно");
		user.setBirthday(new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse("1984-07-28").getTime()));
		user.setEmail("Mihaila4038@yandex.ru");
		user.setLastName("Кейко");
		user.setPhoneNumber("80297875512");
		user.setDeleted(false);
		return user;
	}

	public User createUserClient() throws ParseException {

		User user = new User();
		user.setName("Оля");
		user.setAddress("г.Гродно");
		user.setBirthday(new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse("1984-04-22").getTime()));
		user.setEmail("Olga@yandex.ru");
		user.setLastName("Кейко");
		user.setPhoneNumber("80445006793");
		user.setDeleted(false);
		return user;
	}

	public Car createCar(User user, Model model, LegalEntity legalEntity) {

		Car car = new Car();
		car.setReleaseYear(2001);
		car.setModel(model);
		car.setLegalEntity(legalEntity);
		car.setStatus(CarStatus.Online);
		return car;
	}

	public Rate createRate() {

		Rate rate = new Rate();
		rate.setName("Дневной");
		rate.setPriceKilometr(1.1);
		rate.setPriceLanding(2.0);
		rate.setPriceMinuteWait(0.3);
		return rate;
	}

	public CarOption createCarOption() {

		CarOption carOption = new CarOption();
		carOption.setName("Кондиционер");
		return carOption;
	}

	public CarOption createCarOptionUpdate() {

		CarOption carOption = new CarOption();
		carOption.setName("Автомобиль Бизнес класса");
		return carOption;
	}

	public CarOrder createOrder(Car car, Rate rate, User userClient) {

		CarOrder order = new CarOrder();
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.MINUTE, 5);

		order.setClient(userClient);
		order.setDepartureAddress("Соломовой 40/38");
		order.setArrivalAddress("Клецкова 70/125");
		order.setOrderBegin(new Timestamp(instance.getTimeInMillis()));

		instance.add(Calendar.MINUTE, 10);

		order.setOrderEnd(new Timestamp(instance.getTimeInMillis()));
		order.setDistanceOrder(12.3);
		order.setDistancePaid(7.4);
		order.setInactivityMinutes(5);
		order.setRate(rate);
		order.setDeleted(false);
		return order;
	}

	public ServiceItem createServiceItem(Car car) {

		ServiceItem serviceItem = new ServiceItem();
		serviceItem.setItem(EServiceItem.CARSERVICE);
		serviceItem.setSumma(50.0);
		return serviceItem;
	}

	public OrderAssessment createOrderAssessment(CarOrder order) {

		OrderAssessment orderAssessment = new OrderAssessment();
		orderAssessment.setAssessment(5);
		orderAssessment.setComment("The Best");
		orderAssessment.setOrder(order);

		return orderAssessment;
	}
}
