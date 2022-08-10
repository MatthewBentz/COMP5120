-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.9.1-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping data for table finalproject_db.book: ~8 rows (approximately)
DELETE FROM `book`;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`BookID`, `Title`, `UnitPrice`, `Author`, `Quantity`, `SupplierID`, `SubjectID`) VALUES
	(1, 'book1', 12.34, 'author1', 5, 3, 1),
	(2, 'book2', 56.78, 'author2', 2, 3, 1),
	(3, 'book3', 90.12, 'author3', 10, 2, 1),
	(4, 'book4', 34.56, 'author4', 12, 3, 2),
	(5, 'book5', 78.90, 'author5', 5, 2, 2),
	(6, 'book6', 12.34, 'author6', 30, 1, 3),
	(7, 'book7', 56.90, 'author2', 17, 3, 4),
	(8, 'book8', 33.44, 'author7', 2, 1, 3);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;

-- Dumping data for table finalproject_db.customer: ~4 rows (approximately)
DELETE FROM `customer`;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`CustomerID`, `LastName`, `FirstName`, `Phone`) VALUES
	(1, 'lastname1', 'firstname1', '334-001-001'),
	(2, 'lastname2', 'firstname2', '334-002-002'),
	(3, 'lastname3', 'firstname3', '334-003-003'),
	(4, 'lastname4', 'firstname4', '334-004-004');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumping data for table finalproject_db.employee: ~3 rows (approximately)
DELETE FROM `employee`;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`EmployeeID`, `LastName`, `FirstName`) VALUES
	(1, 'lastname5', 'firstname5'),
	(2, 'lastname6', 'firstname6'),
	(3, 'lastname6', 'firstname9');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;

-- Dumping data for table finalproject_db.order: ~7 rows (approximately)
DELETE FROM `order`;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`OrderID`, `CustomerID`, `EmployeeID`, `OrderDate`, `ShippedDate`, `ShipperID`) VALUES
	(1, 1, 1, '8/1/2016', '8/3/2016', 1),
	(2, 1, 2, '8/4/2016', NULL, NULL),
	(3, 2, 1, '8/1/2016', '8/4/2016', 2),
	(4, 4, 2, '8/4/2016', '8/4/2016', 1),
	(5, 1, 1, '8/4/2016', '8/5/2016', 1),
	(6, 4, 2, '8/4/2016', '8/5/2016', 1),
	(7, 3, 1, '8/4/2016', '8/4/2016', 1);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;

-- Dumping data for table finalproject_db.order_detail: ~11 rows (approximately)
DELETE FROM `order_detail`;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` (`BookID`, `OrderID`, `Quantity`) VALUES
	(1, 1, 2),
	(4, 1, 1),
	(6, 2, 2),
	(7, 2, 3),
	(5, 3, 1),
	(3, 4, 2),
	(4, 4, 1),
	(7, 4, 1),
	(1, 5, 1),
	(1, 6, 2),
	(1, 7, 1);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;

-- Dumping data for table finalproject_db.shipper: ~4 rows (approximately)
DELETE FROM `shipper`;
/*!40000 ALTER TABLE `shipper` DISABLE KEYS */;
INSERT INTO `shipper` (`ShipperID`, `ShipperName`) VALUES
	(1, 'shipper1'),
	(2, 'shipper2'),
	(3, 'shipper3'),
	(4, 'shipper4');
/*!40000 ALTER TABLE `shipper` ENABLE KEYS */;

-- Dumping data for table finalproject_db.subject: ~5 rows (approximately)
DELETE FROM `subject`;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` (`SubjectID`, `CategoryName`) VALUES
	(1, 'category1'),
	(2, 'category2'),
	(3, 'category3'),
	(4, 'category4'),
	(5, 'category5');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;

-- Dumping data for table finalproject_db.supplier: ~4 rows (approximately)
DELETE FROM `supplier`;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` (`SupplierID`, `CompanyName`, `ContactLastName`, `ContactFirstName`, `Phone`) VALUES
	(1, 'supplier1', 'company1', 'company1', '1111111111'),
	(2, 'supplier2', 'company2', 'company2', '2222222222'),
	(3, 'supplier3', 'company3', 'company3', '3333333333'),
	(4, 'supplier4', 'company4', 'company4', '4444444444');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
