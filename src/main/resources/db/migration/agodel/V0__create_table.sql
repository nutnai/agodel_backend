CREATE TABLE `customer`
(
    `customerID` varchar(10) NOT NULL,
    `firstName`  tinytext,
    `lastName`   tinytext,
    `phone`      tinytext,
    `email`      tinytext,
    PRIMARY KEY (`customerID`)
);

CREATE TABLE `owner`
(
    `ownerID`   varchar(10) NOT NULL,
    `firstName` tinytext,
    `lastName`  tinytext,
    `phone`     int DEFAULT NULL,
    `email`     tinytext,
    PRIMARY KEY (`ownerID`)
);

CREATE TABLE `place`
(
    `placeID` varchar(10) NOT NULL,
    `ownerID` varchar(10) DEFAULT NULL,
    `name`    tinytext,
    `address` tinytext,
    PRIMARY KEY (`placeID`),
    KEY `ownerID` (`ownerID`),
    CONSTRAINT `place_ibfk_1` FOREIGN KEY (`ownerID`) REFERENCES `owner` (`ownerID`)
);

CREATE TABLE `room`
(
    `roomID`       varchar(10) NOT NULL,
    `placeID`      varchar(10) DEFAULT NULL,
    `bed`          smallint    DEFAULT NULL,
    `facility`     tinytext,
    `numberPeople` smallint    DEFAULT NULL,
    `price`        smallint    DEFAULT NULL,
    PRIMARY KEY (`roomID`),
    CONSTRAINT `room_ibfk_1` FOREIGN KEY (`placeID`) REFERENCES `place` (`placeID`)
);

CREATE TABLE `place_room`
(
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `placeID` varchar(10) NOT NULL,
    `roomID`  varchar(10) NOT NULL,
    CONSTRAINT `place_room_ibfk_1` FOREIGN KEY (`placeID`) REFERENCES `place` (`placeID`),
    CONSTRAINT `place_room_ibfk_2` FOREIGN KEY (`roomID`) REFERENCES `room` (`roomID`)
);

CREATE TABLE `place_image`
(
    `placeID` varchar(10) NOT NULL,
    `imageID` varchar(10) NOT NULL,
    `image`   MEDIUMBLOB,
    PRIMARY KEY (`imageID`),
    CONSTRAINT `place_image_ibfk_1` FOREIGN KEY (`placeID`) REFERENCES `place` (`placeID`)
);

CREATE TABLE `room_image`
(
    `roomID`  varchar(10) NOT NULL,
    `imageID` varchar(10) NOT NULL,
    `image`   MEDIUMBLOB,
    PRIMARY KEY (`imageID`),
    CONSTRAINT `room_image_ibfk_1` FOREIGN KEY (`roomID`) REFERENCES `room` (`roomID`)
);

# CREATE TABLE `paymentcustomer` (
#   `paymentOwnerID` varchar(10) NOT NULL,
#   `customerID` varchar(10) DEFAULT NULL,
#   PRIMARY KEY (`paymentOwnerID`),
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

