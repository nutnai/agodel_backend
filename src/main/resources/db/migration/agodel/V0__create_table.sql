CREATE TABLE `customer`
(
    `customer_id` varchar(10) NOT NULL,
    `firstname`  tinytext,
    `lastname`   tinytext,
    `phone`      tinytext,
    `email`      tinytext,
    PRIMARY KEY (`customerID`)
);

CREATE TABLE `owner`
(
    `owner_id`   varchar(10) NOT NULL,
    `firstname` tinytext,
    `lastname`  tinytext,
    `phone`     int DEFAULT NULL,
    `email`     tinytext,
    PRIMARY KEY (`ownerID`)
);

CREATE TABLE `place`
(
    `place_id` varchar(10) NOT NULL,
    `owner_id` varchar(10) DEFAULT NULL,
    `name`    tinytext,
    `address` tinytext,
    PRIMARY KEY (`placeID`),
    KEY `owner_id` (`owner_id`),
    CONSTRAINT `place_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `owner` (`owner_id`)
);

CREATE TABLE `room`
(
    `room_id`       varchar(10) NOT NULL,
    `place_id`      varchar(10) DEFAULT NULL,
    `bed`          smallint    DEFAULT NULL,
    `facility`     tinytext,
    `numberPeople` smallint    DEFAULT NULL,
    `price`        smallint    DEFAULT NULL,
    PRIMARY KEY (`room_id`),
    CONSTRAINT `room_ibfk_1` FOREIGN KEY (`place_id`) REFERENCES `place` (`place_id`)
);

CREATE TABLE `place_room`
(
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `placeID` varchar(10) NOT NULL,
    `roomID`  varchar(10) NOT NULL,
    CONSTRAINT `place_room_ibfk_1` FOREIGN KEY (`placeID`) REFERENCES `place` (`place_id`),
    CONSTRAINT `place_room_ibfk_2` FOREIGN KEY (`roomID`) REFERENCES `room` (`room_id`)
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
    PRIMARY KEY (`image_id`),
    CONSTRAINT `room_image_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`)
);

# CREATE TABLE `paymentcustomer` (
#   `paymentOwner_id` varchar(10) NOT NULL,
#   `customer_id` varchar(10) DEFAULT NULL,
#   PRIMARY KEY (`paymentOwner_id`),
#   KEY `customerID` (`customerID`),
#   CONSTRAINT `paymentcustomer_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`)
# ) ;

# CREATE TABLE `paymentowner` (
#   `paymentOwnerID` varchar(10) NOT NULL,
#   `ownerID` varchar(10) DEFAULT NULL,
#   PRIMARY KEY (`paymentOwnerID`),
#   KEY `ownerID` (`ownerID`),
#   CONSTRAINT `paymentowner_ibfk_1` FOREIGN KEY (`ownerID`) REFERENCES `owner` (`ownerID`)
# ) ;

# CREATE TABLE `report` (
#   `reportID` varchar(10) NOT NULL,
#   `placeRoomID` varchar(10) DEFAULT NULL,
#   `resortRoomID` varchar(10) DEFAULT NULL,
#   `customerID` varchar(10) DEFAULT NULL,
#   `reportType` tinytext,
#   `dateStart` datetime DEFAULT NULL,
#   `dateEnd` datetime DEFAULT NULL,
#   `price` smallint DEFAULT NULL,
#   `numberOfDay` smallint DEFAULT NULL,
#   PRIMARY KEY (`reportID`),
#   KEY `hotelRoomID` (`hotelRoomID`),
#   KEY `customerID` (`customerID`),
#   KEY `resortRoomID` (`resortRoomID`),
#   CONSTRAINT `report_ibfk_1` FOREIGN KEY (`hotelRoomID`) REFERENCES `hotelroom` (`hotelRoomID`),
#   CONSTRAINT `report_ibfk_2` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`),
#   CONSTRAINT `report_ibfk_3` FOREIGN KEY (`resortRoomID`) REFERENCES `resortroom` (`resortRoomID`)
# ) ;


CREATE TABLE `receipt`
(
    `receiptID`    varchar(10) DEFAULT NULL,
    `status`       tinytext,
    `dateCreate`   datetime    DEFAULT NULL,
    `datePay`      datetime    DEFAULT NULL,
    `dateCheckIn`  datetime    DEFAULT NULL,
    `dateCheckOut` datetime    DEFAULT NULL,
    `dayCount`     smallint    DEFAULT NULL,
    `price`        smallint    DEFAULT NULL,
    `roomID`       varchar(10) DEFAULT NULL,
    `customerID`   varchar(10) DEFAULT NULL,
    PRIMARY KEY (`receiptID`),
    KEY `roomID` (`roomID`),
    KEY `customerID` (`customerID`),
    CONSTRAINT `receipt_ibfk_1` FOREIGN KEY (`roomID`) REFERENCES `room` (`roomID`),
    CONSTRAINT `receipt_ibfk_2` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`)
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

