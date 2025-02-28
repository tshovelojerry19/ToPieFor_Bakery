-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.25 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for to_pie_for_db
CREATE DATABASE IF NOT EXISTS `to_pie_for_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `to_pie_for_db`;

-- Dumping structure for table to_pie_for_db.address
CREATE TABLE IF NOT EXISTS `address` (
  `AddressID` int NOT NULL AUTO_INCREMENT,
  `Street` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Suburb` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`AddressID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table to_pie_for_db.category
CREATE TABLE IF NOT EXISTS `category` (
  `CategoryID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL DEFAULT '',
  `LastDateUpdated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Flag` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`CategoryID`),
  UNIQUE KEY `Name` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table to_pie_for_db.category_history
CREATE TABLE IF NOT EXISTS `category_history` (
  `CategoryID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`CategoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table to_pie_for_db.ingredient
CREATE TABLE IF NOT EXISTS `ingredient` (
  `IngredientID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL DEFAULT '',
  `Quantity` int NOT NULL DEFAULT '0',
  `flag` tinyint NOT NULL DEFAULT '1',
  `LastDateUpdated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UnitID` int NOT NULL,
  PRIMARY KEY (`IngredientID`),
  UNIQUE KEY `Name` (`Name`),
  KEY `UnitIngredientFK` (`UnitID`),
  CONSTRAINT `UnitID` FOREIGN KEY (`UnitID`) REFERENCES `units` (`UnitID`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table to_pie_for_db.order_line
CREATE TABLE IF NOT EXISTS `order_line` (
  `Quantity` int NOT NULL,
  `OrderID` int NOT NULL,
  `ProductID` int NOT NULL,
  KEY `ProductOrderLine` (`ProductID`),
  KEY `OrderOrderLine` (`OrderID`),
  CONSTRAINT `OrderID` FOREIGN KEY (`OrderID`) REFERENCES `order_table` (`OrderID`),
  CONSTRAINT `ProdID` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table to_pie_for_db.order_table
CREATE TABLE IF NOT EXISTS `order_table` (
  `OrderID` int NOT NULL AUTO_INCREMENT,
  `Date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DateToBeDelivered` date NOT NULL,
  `Status` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'To Be Made',
  `TotalAmount` double NOT NULL DEFAULT '0',
  `UserID` int NOT NULL,
  `AddressID` int NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `UserID` (`UserID`),
  KEY `AddressID` (`AddressID`),
  CONSTRAINT `AddressID` FOREIGN KEY (`AddressID`) REFERENCES `address` (`AddressID`),
  CONSTRAINT `UserID` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table to_pie_for_db.payment
CREATE TABLE IF NOT EXISTS `payment` (
  `PaymentID` int NOT NULL AUTO_INCREMENT,
  `PaymentMethod` varchar(25) NOT NULL DEFAULT '',
  `Status` tinyint DEFAULT NULL,
  `LastDateUpdated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `OrderID` int NOT NULL,
  PRIMARY KEY (`PaymentID`),
  KEY `OrderPayment` (`OrderID`),
  CONSTRAINT `Order_ID` FOREIGN KEY (`OrderID`) REFERENCES `order_table` (`OrderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table to_pie_for_db.product
CREATE TABLE IF NOT EXISTS `product` (
  `ProductID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(80) NOT NULL DEFAULT '',
  `Price` double NOT NULL DEFAULT '0',
  `Allergies` varchar(100) NOT NULL,
  `LastDateUpdated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Flag` tinyint NOT NULL,
  `Image` varchar(50) NOT NULL,
  `RecipeID` int NOT NULL,
  `CategoryID` int NOT NULL,
  PRIMARY KEY (`ProductID`),
  UNIQUE KEY `Name` (`Name`),
  KEY `CategoryProduct` (`CategoryID`),
  KEY `RecipeProduct` (`RecipeID`) USING BTREE,
  CONSTRAINT `CategoryID` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`CategoryID`),
  CONSTRAINT `RecipeID` FOREIGN KEY (`RecipeID`) REFERENCES `recipe` (`RecipeID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- Data exporting was unselected.

-- Dumping structure for table to_pie_for_db.product_history
CREATE TABLE IF NOT EXISTS `product_history` (
  `ProductID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(80) NOT NULL DEFAULT '',
  `Price` double NOT NULL DEFAULT '0',
  `Allergies` varchar(100) NOT NULL,
  `CategoryID` int NOT NULL,
  `Recipe` int NOT NULL,
  PRIMARY KEY (`ProductID`),
  KEY `RecipeProduct` (`Recipe`),
  KEY `CategoryProduct` (`CategoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- Data exporting was unselected.

-- Dumping structure for table to_pie_for_db.recipe
CREATE TABLE IF NOT EXISTS `recipe` (
  `RecipeID` int NOT NULL AUTO_INCREMENT,
  `RecipeName` varchar(50) DEFAULT NULL,
  `Instruction` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `flag` tinyint NOT NULL DEFAULT '1',
  `LastDateUpdated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`RecipeID`),
  UNIQUE KEY `RecipeName` (`RecipeName`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table to_pie_for_db.recipe_ingredient
CREATE TABLE IF NOT EXISTS `recipe_ingredient` (
  `RecipeID` int NOT NULL,
  `IngredientID` int NOT NULL,
  `Quantity` int NOT NULL DEFAULT '1',
  KEY `RecipeID` (`RecipeID`),
  KEY `IngredientID` (`IngredientID`),
  CONSTRAINT `IngredientID` FOREIGN KEY (`IngredientID`) REFERENCES `ingredient` (`IngredientID`),
  CONSTRAINT `Recipe_ID` FOREIGN KEY (`RecipeID`) REFERENCES `recipe` (`RecipeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table to_pie_for_db.role
CREATE TABLE IF NOT EXISTS `role` (
  `RoleID` int NOT NULL AUTO_INCREMENT,
  `Role` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`RoleID`),
  UNIQUE KEY `Role` (`Role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table to_pie_for_db.units
CREATE TABLE IF NOT EXISTS `units` (
  `UnitID` int NOT NULL AUTO_INCREMENT,
  `UnitType` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LastDateUpdated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`UnitID`),
  UNIQUE KEY `UnitType` (`UnitType`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table to_pie_for_db.user
CREATE TABLE IF NOT EXISTS `user` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) NOT NULL DEFAULT '',
  `Surname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `Title` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `TelephoneNumber` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LastDateUpdated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Flag` tinyint NOT NULL DEFAULT '1',
  `RoleID` int NOT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Email` (`Email`),
  UNIQUE KEY `TelephoneNumber` (`TelephoneNumber`),
  KEY `RoleUser` (`RoleID`),
  CONSTRAINT `RoleUser` FOREIGN KEY (`RoleID`) REFERENCES `role` (`RoleID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table to_pie_for_db.user_address
CREATE TABLE IF NOT EXISTS `user_address` (
  `AddressID` int NOT NULL,
  `UserID` int NOT NULL,
  `flag` tinyint NOT NULL DEFAULT '1',
  KEY `AddressUserAddress` (`AddressID`),
  KEY `UserUserAddress` (`UserID`),
  CONSTRAINT `Address_ID` FOREIGN KEY (`AddressID`) REFERENCES `address` (`AddressID`),
  CONSTRAINT `User_ID` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
