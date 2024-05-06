CREATE TABLE `customer`
(
    `customer_id` varchar(10) NOT NULL,
    `username`   tinytext,
    `firstname`  tinytext,
    `lastname`   tinytext,
    `phone`      tinytext,
    `email`      tinytext,
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE `owner`
(
    `owner_id`   varchar(10) NOT NULL,
    `username`   tinytext,
    `firstname` tinytext,
    `lastname`  tinytext,
    `phone`     int DEFAULT NULL,
    `email`     tinytext,
    PRIMARY KEY (`owner_id`)
);

CREATE TABLE `place`
(
    `place_id` varchar(10) NOT NULL PRIMARY KEY,
    `owner_id` varchar(10) NOT NULL,
    `name`    tinytext,
    `address` TEXT,
    `status` enum('AVAILABLE', 'UNAVAILABLE') DEFAULT 'AVAILABLE',
    CONSTRAINT `place_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `owner` (`owner_id`)
);

CREATE TABLE `room`
(
    `place_id` varchar(10) NOT NULL,
    `room_id`       varchar(10) NOT NULL,
    `owner_id`      varchar(10) NOT NULL,
    `room_type`      tinytext,
    `facility`     tinytext,
    `number_people` smallint    DEFAULT NULL,
    `price`        DOUBLE    DEFAULT NULL,
    `status`      enum('AVAILABLE', 'UNAVAILABLE') DEFAULT 'AVAILABLE',
    PRIMARY KEY (`room_id`),
    CONSTRAINT `room_ibfk_1` FOREIGN KEY (`place_id`) REFERENCES `place` (`place_id`)
);

CREATE TABLE `place_room`
(
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `place_id` varchar(10) NOT NULL,
    `room_id`  varchar(10) NOT NULL,
    CONSTRAINT `place_room_ibfk_1` FOREIGN KEY (`place_id`) REFERENCES `place` (`place_id`),
    CONSTRAINT `place_room_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`)
);

CREATE TABLE `place_image`
(
    `place_id` varchar(10) NOT NULL,
    `image_id` varchar(10) NOT NULL,
    `image`   MEDIUMBLOB,
    PRIMARY KEY (`image_id`),
    CONSTRAINT `place_image_ibfk_1` FOREIGN KEY (`place_id`) REFERENCES `place` (`place_id`)
);

CREATE TABLE `room_image`
(
    `room_id`  varchar(10) NOT NULL,
    `image_id` varchar(10) NOT NULL,
    `image`   MEDIUMBLOB,
    PRIMARY KEY (`image_id`) ,
    CONSTRAINT `room_image_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`)
);

# CREATE TABLE `paymentcustomer` (
#   `payment_owner_id` varchar(10) NOT NULL,
#   `customer_id` varchar(10) DEFAULT NULL,
#   PRIMARY KEY (`paymentOwner_id`),
#   KEY `customer_id` (`customer_id`),
#   CONSTRAINT `paymentcustomer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
# ) ;

# CREATE TABLE `paymentowner` (
#   `payment_owner_id` varchar(10) NOT NULL,
#   `owner_id` varchar(10) DEFAULT NULL,
#   PRIMARY KEY (`paymentOwner_id`),
#   KEY `owner_id` (`owner_id`),
#   CONSTRAINT `paymentowner_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `owner` (`owner_id`)
# ) ;

# CREATE TABLE `report` (
#   `report_id` varchar(10) NOT NULL,
#   `placeRoom_id` varchar(10) DEFAULT NULL,
#   `resortRoom_id` varchar(10) DEFAULT NULL,
#   `customer_id` varchar(10) DEFAULT NULL,
#   `report_type` tinytext,
#   `date_start` datetime DEFAULT NULL,
#   `date_end` datetime DEFAULT NULL,
#   `price` smallint DEFAULT NULL,
#   `number_of_day` smallint DEFAULT NULL,
#   PRIMARY KEY (`report_id`),
#   KEY `hotelRoom_id` (`hotelRoom_id`),
#   KEY `customer_id` (`customer_id`),
#   KEY `resortRoom_id` (`resortRoom_id`),
#   CONSTRAINT `report_ibfk_1` FOREIGN KEY (`hotelRoom_id`) REFERENCES `hotelroom` (`hotelRoom_id`),
#   CONSTRAINT `report_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
#   CONSTRAINT `report_ibfk_3` FOREIGN KEY (`resortRoom_id`) REFERENCES `resortroom` (`resortRoom_id`)
# ) ;


CREATE TABLE `receipt`
(
    `receipt_id`    varchar(10) NOT NULL PRIMARY KEY,
    `status`       enum('UNAPPROVED', 'UNPAID', 'CANCELLED', 'PAID', 'COMPLETED') DEFAULT 'UNAPPROVED',
    `date_create`   datetime    DEFAULT NULL,
    `date_pay`      datetime    DEFAULT NULL,
    `date_check_in`  datetime    DEFAULT NULL,
    `date_check_out` datetime    DEFAULT NULL,
    `day_count`     smallint    DEFAULT NULL,
    `price`        DOUBLE    DEFAULT NULL,
    `room_id`       varchar(10) DEFAULT NULL,
    `customer_id`   varchar(10) DEFAULT NULL,
    KEY `room_id` (`room_id`),
    KEY `customer_id` (`customer_id`),
    CONSTRAINT `receipt_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`),
    CONSTRAINT `receipt_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
);

CREATE TABLE `user`
(
    `id`       varchar(255) NOT NULL,
    `username` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    UNIQUE KEY `id` (`id`),
    PRIMARY KEY (`id`)
);

CREATE TABLE `user_count`
(
    `type`  varchar(255) NOT NULL,
    `count` INT          NOT NULL
);

CREATE TABLE `facility`
(
    `facility_id` varchar(10) NOT NULL PRIMARY KEY,
    `name`        tinytext,
    `description` TEXT
);

CREATE TABLE `room_facility`
(
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `room_id`    varchar(10) NOT NULL,
    `facility_id` varchar(10) NOT NULL,
    CONSTRAINT `room_facility_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`),
    CONSTRAINT `room_facility_ibfk_2` FOREIGN KEY (`facility_id`) REFERENCES `facility` (`facility_id`)
);
