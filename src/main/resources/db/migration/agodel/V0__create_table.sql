CREATE TABLE `admin` (
  `adminID` varchar(10) NOT NULL,
  `firstName` tinytext,
  `lastName` tinytext,
  `phone` tinytext,
  `email` tinytext,
  `permission` tinytext,
  PRIMARY KEY (`adminID`)
) ;

CREATE TABLE `customer` (
  `customerID` varchar(10) NOT NULL,
  `first_name` tinytext,
  `last_name` tinytext,
  `phone` tinytext,
  `email` tinytext,
  PRIMARY KEY (`customerID`)
) ;

CREATE TABLE `owner` (
  `ownerID` varchar(10) NOT NULL,
  `firstName` tinytext,
  `lastName` tinytext,
  `phone` int DEFAULT NULL,
  `email` tinytext,
  PRIMARY KEY (`ownerID`)
) ;

CREATE TABLE `hotel` (
  `hotelID` varchar(10) NOT NULL,
  `ownerID` varchar(10) DEFAULT NULL,
  `name` tinytext,
  `adress` tinytext,
  `numberOfRoom` smallint DEFAULT NULL,
  `numberOfFloor` smallint DEFAULT NULL,
  PRIMARY KEY (`hotelID`),
  KEY `ownerID` (`ownerID`),
  CONSTRAINT `hotel_ibfk_1` FOREIGN KEY (`ownerID`) REFERENCES `owner` (`ownerID`)
) ;

CREATE TABLE `hotelroomtype` (
  `hotelID` varchar(10) DEFAULT NULL,
  `TypeID` varchar(10) NOT NULL,
  `bed` smallint DEFAULT NULL,
  `facility` tinytext,
  `numberPeople` smallint DEFAULT NULL,
  `price` smallint DEFAULT NULL,
  PRIMARY KEY (`TypeID`),
  KEY `hotelID` (`hotelID`),
  CONSTRAINT `hotelroomtype_ibfk_1` FOREIGN KEY (`hotelID`) REFERENCES `hotel` (`hotelID`)
) ;

CREATE TABLE `hotelroom` (
  `hotelRoomID` varchar(10) NOT NULL,
  `hotelID` varchar(10) DEFAULT NULL,
  `TypeID` varchar(10) DEFAULT NULL,
  `floor` smallint DEFAULT NULL,
  `roomNumber` smallint DEFAULT NULL,
  `statuse` tinytext,
  PRIMARY KEY (`hotelRoomID`),
  KEY `TypeID` (`TypeID`),
  KEY `hotelID` (`hotelID`),
  CONSTRAINT `hotelroom_ibfk_1` FOREIGN KEY (`TypeID`) REFERENCES `hotelroomtype` (`TypeID`),
  CONSTRAINT `hotelroom_ibfk_2` FOREIGN KEY (`hotelID`) REFERENCES `hotel` (`hotelID`)
) ;

CREATE TABLE `paymentcustomer` (
  `paymentOwnerID` varchar(10) NOT NULL,
  `customerID` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`paymentOwnerID`),
  KEY `customerID` (`customerID`),
  CONSTRAINT `paymentcustomer_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`)
) ;

CREATE TABLE `paymentowner` (
  `paymentOwnerID` varchar(10) NOT NULL,
  `ownerID` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`paymentOwnerID`),
  KEY `ownerID` (`ownerID`),
  CONSTRAINT `paymentowner_ibfk_1` FOREIGN KEY (`ownerID`) REFERENCES `owner` (`ownerID`)
) ;

CREATE TABLE `resort` (
  `resortID` varchar(10) NOT NULL,
  `ownerID` varchar(10) DEFAULT NULL,
  `name` tinytext,
  `adress` tinytext,
  `numberOfRoom` smallint DEFAULT NULL,
  PRIMARY KEY (`resortID`),
  KEY `ownerID` (`ownerID`),
  CONSTRAINT `resort_ibfk_1` FOREIGN KEY (`ownerID`) REFERENCES `owner` (`ownerID`)
) ;

CREATE TABLE `resortroomtype` (
  `resortID` varchar(10) DEFAULT NULL,
  `TypeID` varchar(10) NOT NULL,
  `bed` smallint DEFAULT NULL,
  `facility` tinytext,
  `numberPeople` smallint DEFAULT NULL,
  `price` smallint DEFAULT NULL,
  PRIMARY KEY (`TypeID`),
  KEY `resortID` (`resortID`),
  CONSTRAINT `resortroomtype_ibfk_1` FOREIGN KEY (`resortID`) REFERENCES `resort` (`resortID`)
) ;

CREATE TABLE `resortroom` (
  `resortRoomID` varchar(10) NOT NULL,
  `resortID` varchar(10) DEFAULT NULL,
  `TypeID` varchar(10) DEFAULT NULL,
  `roomNumber` smallint DEFAULT NULL,
  `statuse` tinytext,
  PRIMARY KEY (`resortRoomID`),
  KEY `TypeID` (`TypeID`),
  KEY `resortID` (`resortID`),
  CONSTRAINT `resortroom_ibfk_1` FOREIGN KEY (`TypeID`) REFERENCES `resortroomtype` (`TypeID`),
  CONSTRAINT `resortroom_ibfk_2` FOREIGN KEY (`resortID`) REFERENCES `resort` (`resortID`)
) ;

CREATE TABLE `report` (
  `reportID` varchar(10) NOT NULL,
  `hotelRoomID` varchar(10) DEFAULT NULL,
  `resortRoomID` varchar(10) DEFAULT NULL,
  `customerID` varchar(10) DEFAULT NULL,
  `reportType` tinytext,
  `dateStart` datetime DEFAULT NULL,
  `dateEnd` datetime DEFAULT NULL,
  `price` smallint DEFAULT NULL,
  `numberOfDay` smallint DEFAULT NULL,
  PRIMARY KEY (`reportID`),
  KEY `hotelRoomID` (`hotelRoomID`),
  KEY `customerID` (`customerID`),
  KEY `resortRoomID` (`resortRoomID`),
  CONSTRAINT `report_ibfk_1` FOREIGN KEY (`hotelRoomID`) REFERENCES `hotelroom` (`hotelRoomID`),
  CONSTRAINT `report_ibfk_2` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`),
  CONSTRAINT `report_ibfk_3` FOREIGN KEY (`resortRoomID`) REFERENCES `resortroom` (`resortRoomID`)
) ;

CREATE TABLE `reportroom` (
  `reportID` varchar(10) DEFAULT NULL,
  `hotelRoomID` varchar(10) DEFAULT NULL,
  `resortRoomID` varchar(10) DEFAULT NULL,
  KEY `hotelRoomID` (`hotelRoomID`),
  KEY `reportID` (`reportID`),
  KEY `resortRoomID` (`resortRoomID`),
  CONSTRAINT `reportroom_ibfk_1` FOREIGN KEY (`hotelRoomID`) REFERENCES `hotelroom` (`hotelRoomID`),
  CONSTRAINT `reportroom_ibfk_2` FOREIGN KEY (`reportID`) REFERENCES `report` (`reportID`),
  CONSTRAINT `reportroom_ibfk_3` FOREIGN KEY (`resortRoomID`) REFERENCES `resortroom` (`resortRoomID`)
) ;

CREATE TABLE `reciept` (
  `recieptID` varchar(10) DEFAULT NULL,
  `reportID` varchar(10) DEFAULT NULL,
  `datePay` datetime DEFAULT NULL,
  KEY `reportID` (`reportID`),
  CONSTRAINT `reciept_ibfk_1` FOREIGN KEY (`reportID`) REFERENCES `report` (`reportID`)
) ;

CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  UNIQUE KEY `id` (`id`)
) ;

CREATE TABLE `user_count` (
  `type` varchar(255) NOT NULL,
  `count` INT NOT NULL
) ;

