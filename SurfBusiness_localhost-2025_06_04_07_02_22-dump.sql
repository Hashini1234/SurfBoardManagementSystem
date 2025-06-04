-- MySQL dump 10.13  Distrib 8.0.41, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: SurfBusiness
-- ------------------------------------------------------
-- Server version	8.0.41-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Beach_Location`
--

DROP TABLE IF EXISTS `Beach_Location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Beach_Location` (
  `beach_id` varchar(10) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `peak_season` varchar(100) DEFAULT NULL,
  `month` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`beach_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Beach_Location`
--

LOCK TABLES `Beach_Location` WRITE;
/*!40000 ALTER TABLE `Beach_Location` DISABLE KEYS */;
INSERT INTO `Beach_Location` VALUES ('B002','ttt',NULL,NULL),('B003','jhvhjbjvbjh','February','February'),('B004','','January','February'),('B005','Matara','January','January'),('B006','rtyu','January','January'),('B007','rtghjk','January','January'),('B008','asdfghj','January','January'),('B009','asdfghjk','January','January'),('B010','asdfg','January','January');
/*!40000 ALTER TABLE `Beach_Location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Bill`
--

DROP TABLE IF EXISTS `Bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Bill` (
  `bill_id` varchar(10) NOT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `tourist_id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`bill_id`),
  KEY `tourist_id` (`tourist_id`),
  CONSTRAINT `Bill_ibfk_1` FOREIGN KEY (`tourist_id`) REFERENCES `Tourist` (`tourist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bill`
--

LOCK TABLES `Bill` WRITE;
/*!40000 ALTER TABLE `Bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `Bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Guide`
--

DROP TABLE IF EXISTS `Guide`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Guide` (
  `guide_id` varchar(10) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `contact_details` varchar(100) DEFAULT NULL,
  `experience_level` varchar(50) DEFAULT NULL,
  `pay_for` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`guide_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Guide`
--

LOCK TABLES `Guide` WRITE;
/*!40000 ALTER TABLE `Guide` DISABLE KEYS */;
INSERT INTO `Guide` VALUES ('C001','hhh','00','gggg',12.00),('C002','Hashini','11','11',88888888.00),('C004','ll','0754548956','8',8.00),('C005','ddd','000000000','55',15000.00),('G006','devindi','0764779894','5',10000.00);
/*!40000 ALTER TABLE `Guide` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Item`
--

DROP TABLE IF EXISTS `Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Item` (
  `item_id` varchar(10) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `conditions` varchar(50) DEFAULT NULL,
  `availability_status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item`
--

LOCK TABLES `Item` WRITE;
/*!40000 ALTER TABLE `Item` DISABLE KEYS */;
INSERT INTO `Item` VALUES ('I001','HH','dd','dd','dd'),('I002','hh','kk','ee','ii'),('I003','rohan','xx','ee','ss'),('I004','ha','uu','nn','nn'),('I005','ha','uu','ff','nn'),('I006','hhhh','fffff','ffff','eeee'),('I007','hashiniu','sjhdsyw','defrtge','weg');
/*!40000 ALTER TABLE `Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Meal_plane`
--

DROP TABLE IF EXISTS `Meal_plane`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Meal_plane` (
  `Meal_plane_id` varchar(10) NOT NULL,
  `menu` varchar(10) DEFAULT NULL,
  `session_id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`Meal_plane_id`),
  KEY `session_id` (`session_id`),
  CONSTRAINT `Meal_plane_ibfk_1` FOREIGN KEY (`session_id`) REFERENCES `Session` (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Meal_plane`
--

LOCK TABLES `Meal_plane` WRITE;
/*!40000 ALTER TABLE `Meal_plane` DISABLE KEYS */;
/*!40000 ALTER TABLE `Meal_plane` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Payment`
--

DROP TABLE IF EXISTS `Payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Payment` (
  `payment_id` varchar(10) NOT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `session_id` varchar(10) DEFAULT NULL,
  `payment_method` varchar(50) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `session_id` (`session_id`),
  CONSTRAINT `Payment_ibfk_1` FOREIGN KEY (`session_id`) REFERENCES `Session` (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Payment`
--

LOCK TABLES `Payment` WRITE;
/*!40000 ALTER TABLE `Payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `Payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reservation_session`
--

DROP TABLE IF EXISTS `Reservation_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Reservation_session` (
  `Reservation_id` varchar(10) NOT NULL,
  `tourist_id` varchar(10) DEFAULT NULL,
  `session_id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`Reservation_id`),
  KEY `tourist_id` (`tourist_id`),
  KEY `session_id` (`session_id`),
  CONSTRAINT `Reservation_session_ibfk_1` FOREIGN KEY (`tourist_id`) REFERENCES `Tourist` (`tourist_id`),
  CONSTRAINT `Reservation_session_ibfk_2` FOREIGN KEY (`session_id`) REFERENCES `Session` (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservation_session`
--

LOCK TABLES `Reservation_session` WRITE;
/*!40000 ALTER TABLE `Reservation_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `Reservation_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Session`
--

DROP TABLE IF EXISTS `Session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Session` (
  `session_id` varchar(10) NOT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `duration` varchar(50) DEFAULT NULL,
  `tourist_id` varchar(10) DEFAULT NULL,
  `beach_id` varchar(10) DEFAULT NULL,
  `guide_id` varchar(10) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`session_id`),
  KEY `tourist_id` (`tourist_id`),
  KEY `beach_id` (`beach_id`),
  KEY `guide_id` (`guide_id`),
  CONSTRAINT `Session_ibfk_1` FOREIGN KEY (`tourist_id`) REFERENCES `Tourist` (`tourist_id`),
  CONSTRAINT `Session_ibfk_2` FOREIGN KEY (`beach_id`) REFERENCES `Beach_Location` (`beach_id`),
  CONSTRAINT `Session_ibfk_3` FOREIGN KEY (`guide_id`) REFERENCES `Guide` (`guide_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Session`
--

LOCK TABLES `Session` WRITE;
/*!40000 ALTER TABLE `Session` DISABLE KEYS */;
INSERT INTO `Session` VALUES ('S001','2025-06-07','02:02:02','2hour','T001','B003','C001','Complete'),('S002','2025-06-28','05:05:05','5hours','T001','B004','C004','Complete'),('S003','2025-06-18','02:04:03','6hours','T001','B002','C001','Sceduled');
/*!40000 ALTER TABLE `Session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Session_item`
--

DROP TABLE IF EXISTS `Session_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Session_item` (
  `session_item_id` varchar(10) NOT NULL,
  `item_id` varchar(10) DEFAULT NULL,
  `session_id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`session_item_id`),
  KEY `item_id` (`item_id`),
  KEY `session_id` (`session_id`),
  CONSTRAINT `Session_item_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`),
  CONSTRAINT `Session_item_ibfk_2` FOREIGN KEY (`session_id`) REFERENCES `Session` (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Session_item`
--

LOCK TABLES `Session_item` WRITE;
/*!40000 ALTER TABLE `Session_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `Session_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Supplier`
--

DROP TABLE IF EXISTS `Supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Supplier` (
  `supplier_id` varchar(10) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `contact_info` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Supplier`
--

LOCK TABLES `Supplier` WRITE;
/*!40000 ALTER TABLE `Supplier` DISABLE KEYS */;
INSERT INTO `Supplier` VALUES ('S001','YTI','8888888888'),('S002','etred','rtru'),('S003','gkuyl','0000--'),('S004','giyi','gyjit'),('S005','fjgfjg','0000000'),('S006','srtydu','Zxcvbn');
/*!40000 ALTER TABLE `Supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Supply_Item`
--

DROP TABLE IF EXISTS `Supply_Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Supply_Item` (
  `item_id` varchar(10) NOT NULL,
  `supplier_id` varchar(10) NOT NULL,
  PRIMARY KEY (`item_id`,`supplier_id`),
  KEY `supplier_id` (`supplier_id`),
  CONSTRAINT `Supply_Item_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`),
  CONSTRAINT `Supply_Item_ibfk_2` FOREIGN KEY (`supplier_id`) REFERENCES `Supplier` (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Supply_Item`
--

LOCK TABLES `Supply_Item` WRITE;
/*!40000 ALTER TABLE `Supply_Item` DISABLE KEYS */;
/*!40000 ALTER TABLE `Supply_Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Surf_Board`
--

DROP TABLE IF EXISTS `Surf_Board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Surf_Board` (
  `surfboard_id` varchar(10) NOT NULL,
  `brand` varchar(100) DEFAULT NULL,
  `conditions` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`surfboard_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Surf_Board`
--

LOCK TABLES `Surf_Board` WRITE;
/*!40000 ALTER TABLE `Surf_Board` DISABLE KEYS */;
INSERT INTO `Surf_Board` VALUES ('S001','yy','yy'),('S002','yrgseur','fuif');
/*!40000 ALTER TABLE `Surf_Board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tourist`
--

DROP TABLE IF EXISTS `Tourist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Tourist` (
  `tourist_id` varchar(10) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `address` text,
  `contact_details` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`tourist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tourist`
--

LOCK TABLES `Tourist` WRITE;
/*!40000 ALTER TABLE `Tourist` DISABLE KEYS */;
INSERT INTO `Tourist` VALUES ('T001','uygjhg','fdgdfhg','4544'),('T002','ehigeg','suhfugy','kdwhi');
/*!40000 ALTER TABLE `Tourist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Transport`
--

DROP TABLE IF EXISTS `Transport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Transport` (
  `transport_id` varchar(10) NOT NULL,
  `location` varchar(100) DEFAULT NULL,
  `cost` varchar(10) DEFAULT NULL,
  `tourist_id` varchar(10) DEFAULT NULL,
  `vehicle_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`transport_id`),
  KEY `tourist_id` (`tourist_id`),
  CONSTRAINT `Transport_ibfk_1` FOREIGN KEY (`tourist_id`) REFERENCES `Tourist` (`tourist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Transport`
--

LOCK TABLES `Transport` WRITE;
/*!40000 ALTER TABLE `Transport` DISABLE KEYS */;
INSERT INTO `Transport` VALUES ('T001','hghgk','55555','T001','Vehicle Type'),('T002','jnjkn','54454','T001','Vehicle Type'),('T003','ertyui','fghjk','T001','Car ');
/*!40000 ALTER TABLE `Transport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` varchar(4) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `role` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('U001','Hasini','hasini@gmail.com','Admin','12345678');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-04  7:02:22
