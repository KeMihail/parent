CREATE TABLE `brand` (
	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(100) NOT NULL UNIQUE,
	`created ` TIMESTAMP NOT NULL,
	`modified ` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `model` (
	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(100) NOT NULL,
	`car_kit` varchar(100) NOT NULL,
	`engine_type` varchar(100) NOT NULL,
	`body_type` varchar(100) NOT NULL,
	`brand_id` int NOT NULL,
	`created ` TIMESTAMP NOT NULL,
	`modified ` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `legal_entity` (
	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(100) NOT NULL,
	`address` varchar(200) NOT NULL,
	`phone_number` varchar(100) NOT NULL,
	`email` varchar(100),
	`created ` TIMESTAMP NOT NULL,
	`modified ` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `rate` (
	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(100) NOT NULL,
	`price_landing` double NOT NULL,
	`price_kilometr` double NOT NULL,
	`price_minute_wait` double NOT NULL,
	`created ` TIMESTAMP NOT NULL,
	`modified ` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `car_option` (
	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(100) NOT NULL,
	`created ` TIMESTAMP NOT NULL,
	`modified ` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `service_item` (
	`id` int NOT NULL AUTO_INCREMENT,
	`driver_id` int NOT NULL,
	`item` varchar(100) NOT NULL,
	`summa` double NOT NULL,
	`comment` varchar(500) NOT NULL,
	`created ` TIMESTAMP NOT NULL,
	`modified ` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `user` (
	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(100) NOT NULL,
	`last_name` varchar(100) NOT NULL,
	`birthday` TIMESTAMP NOT NULL,
	`address` varchar(200) NOT NULL,
	`phone_number` varchar(100) NOT NULL,
	`email` varchar(100) NOT NULL UNIQUE,
	`password` varchar(100) NOT NULL,
	`role` varchar(100) NOT NULL,
	`deleted` bool NOT NULL DEFAULT 'false',
	`created ` TIMESTAMP NOT NULL,
	`modified ` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `car_2_car_option` (
	`car_id` int NOT NULL,
	`car_option_id` int NOT NULL
);

CREATE TABLE `car` (
	`id` int NOT NULL AUTO_INCREMENT,
	`release_year` int NOT NULL,
	`model_id` int NOT NULL,
	`legal_entity_id` int NOT NULL,
	`status` varchar(100) NOT NULL,
	`created ` TIMESTAMP NOT NULL,
	`modified ` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `car_order ` (
	`id` int NOT NULL AUTO_INCREMENT,
	`client_id` int NOT NULL,
	`driver_id` int,
	`departure_address` varchar(200),
	`arrival_address` varchar(200),
	`order_begin` TIMESTAMP,
	`order_end` TIMESTAMP,
	`distance_order` double,
	`distance_paid ` double,
	`inactivity_minutes` int,
	`rate_id` int,
	`total` double,
	`deleted` bool NOT NULL DEFAULT 'false',
	`created ` TIMESTAMP NOT NULL,
	`modified ` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `order_assessment ` (
	`order_id` int NOT NULL AUTO_INCREMENT,
	`assessment` int NOT NULL,
	`comment` varchar(5000) NOT NULL,
	`created ` TIMESTAMP NOT NULL,
	`modified ` TIMESTAMP NOT NULL,
	PRIMARY KEY (`order_id`)
);

CREATE TABLE `driver` (
	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(100) NOT NULL,
	`last_name` varchar(100) NOT NULL,
	`birthday` TIMESTAMP NOT NULL,
	`address` varchar(100) NOT NULL,
	`phone_number` varchar(100) NOT NULL,
	`email` varchar(100) NOT NULL UNIQUE,
	`password` varchar(100) NOT NULL,
	`role` varchar(100) NOT NULL,
	`deleted` bool NOT NULL DEFAULT 'false',
	`car_id` int(100) NOT NULL,
	`created ` TIMESTAMP NOT NULL,
	`modified ` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `model` ADD CONSTRAINT `model_fk0` FOREIGN KEY (`brand_id`) REFERENCES `brand`(`id`);

ALTER TABLE `service_item` ADD CONSTRAINT `service_item_fk0` FOREIGN KEY (`driver_id`) REFERENCES `driver`(`id`);

ALTER TABLE `car_2_car_option` ADD CONSTRAINT `car_2_car_option_fk0` FOREIGN KEY (`car_id`) REFERENCES `car`(`id`);

ALTER TABLE `car_2_car_option` ADD CONSTRAINT `car_2_car_option_fk1` FOREIGN KEY (`car_option_id`) REFERENCES `car_option`(`id`);

ALTER TABLE `car` ADD CONSTRAINT `car_fk0` FOREIGN KEY (`model_id`) REFERENCES `model`(`id`);

ALTER TABLE `car` ADD CONSTRAINT `car_fk1` FOREIGN KEY (`legal_entity_id`) REFERENCES `legal_entity`(`id`);

ALTER TABLE `car_order ` ADD CONSTRAINT `car_order _fk0` FOREIGN KEY (`client_id`) REFERENCES `user`(`id`);

ALTER TABLE `car_order ` ADD CONSTRAINT `car_order _fk1` FOREIGN KEY (`driver_id`) REFERENCES `driver`(`id`);

ALTER TABLE `car_order ` ADD CONSTRAINT `car_order _fk2` FOREIGN KEY (`rate_id`) REFERENCES `rate`(`id`);

ALTER TABLE `order_assessment ` ADD CONSTRAINT `order_assessment _fk0` FOREIGN KEY (`order_id`) REFERENCES `car_order `(`id`);

ALTER TABLE `driver` ADD CONSTRAINT `driver_fk0` FOREIGN KEY (`car_id`) REFERENCES `car`(`id`);
