-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: projectegor
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `access_device`
--

DROP TABLE IF EXISTS `access_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `access_device` (
  `Type` varchar(50) NOT NULL,
  PRIMARY KEY (`Type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `access_device`
--

LOCK TABLES `access_device` WRITE;
/*!40000 ALTER TABLE `access_device` DISABLE KEYS */;
INSERT INTO `access_device` VALUES ('1'),('Card'),('Face'),('Finger');
/*!40000 ALTER TABLE `access_device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `access_option`
--

DROP TABLE IF EXISTS `access_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `access_option` (
  `Access_device_Type` varchar(50) NOT NULL,
  `Room_ID` int NOT NULL,
  PRIMARY KEY (`Access_device_Type`,`Room_ID`),
  KEY `fk_Access_option_Room1_idx` (`Room_ID`),
  CONSTRAINT `fk_Access_option_Access_device1` FOREIGN KEY (`Access_device_Type`) REFERENCES `access_device` (`Type`),
  CONSTRAINT `fk_Access_option_Room1` FOREIGN KEY (`Room_ID`) REFERENCES `room` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `access_option`
--

LOCK TABLES `access_option` WRITE;
/*!40000 ALTER TABLE `access_option` DISABLE KEYS */;
INSERT INTO `access_option` VALUES ('Card',101),('Card',102),('Card',103),('Face',201),('Finger',202),('Finger',301),('Face',302);
/*!40000 ALTER TABLE `access_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `Name` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  PRIMARY KEY (`Name`,`Password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('Admin','admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `ID` int NOT NULL,
  `First_Name` varchar(45) NOT NULL,
  `Last_Name` varchar(45) NOT NULL,
  `e-mail` varchar(45) NOT NULL,
  `Telephone_number` varchar(45) NOT NULL,
  `Passport_ID` int NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1000,'Egor','Kolpakov','kolp_e@DBandJava.com','89810000000',10001,'Egor1000'),(1001,'Ivan','Kuleshov','kule_i@DBandJava.com','89811111111',10011,'Ivan1001'),(1002,'Aleksandr','Lomachenkov','loma_a@DBandJava.com','89812222222',10021,'Aleksandr1002'),(1003,'Daniil','Rukosuev','ruko_d@DBandJava.com','89813333333',10031,'Daniil1003'),(1004,'Andrew','Aristov','aris_a@DBandJava.com','89814444444',10041,'Andrew1004'),(1005,'Vlad','Patrichnyj','patr_v@DBandJava.com','89815555555',100599,'Vlad1005');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `list_of_visits`
--

DROP TABLE IF EXISTS `list_of_visits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `list_of_visits` (
  `Time_of_visit` datetime NOT NULL,
  `Employee_ID` int NOT NULL,
  `Room_ID` int NOT NULL,
  PRIMARY KEY (`Employee_ID`,`Room_ID`),
  KEY `fk_List_of_visits_Room1_idx` (`Room_ID`),
  CONSTRAINT `fk_List_of_visits_Employee1` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`ID`),
  CONSTRAINT `fk_List_of_visits_Room1` FOREIGN KEY (`Room_ID`) REFERENCES `room` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list_of_visits`
--

LOCK TABLES `list_of_visits` WRITE;
/*!40000 ALTER TABLE `list_of_visits` DISABLE KEYS */;
INSERT INTO `list_of_visits` VALUES ('2009-06-04 18:13:56',1000,101),('2021-05-12 21:10:43',1000,102),('2021-05-12 21:15:51',1000,103),('2021-05-12 22:19:00',1003,201);
/*!40000 ALTER TABLE `list_of_visits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `ID` int NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Windows_number` int DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (101,'MainRoom',3),(102,'Toilet',0),(103,'Room103',2),(201,'PracticeRoom',2),(202,'DinnerRoom',2),(301,'RestRoom',2),(302,'BedRoom',5);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_level`
--

DROP TABLE IF EXISTS `security_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `security_level` (
  `Security_levelname_Levelname` varchar(50) NOT NULL,
  `Room_ID` int NOT NULL,
  PRIMARY KEY (`Security_levelname_Levelname`,`Room_ID`),
  KEY `fk_Security_level_Room1_idx` (`Room_ID`),
  CONSTRAINT `fk_Security_level_Room1` FOREIGN KEY (`Room_ID`) REFERENCES `room` (`ID`),
  CONSTRAINT `fk_Security_level_Security_levelname1` FOREIGN KEY (`Security_levelname_Levelname`) REFERENCES `security_levelname` (`Levelname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_level`
--

LOCK TABLES `security_level` WRITE;
/*!40000 ALTER TABLE `security_level` DISABLE KEYS */;
INSERT INTO `security_level` VALUES ('4',101),('1',102),('2',103),('3',201),('3',202),('4',301),('4',302);
/*!40000 ALTER TABLE `security_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_level_employee`
--

DROP TABLE IF EXISTS `security_level_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `security_level_employee` (
  `Security_levelname_Levelname` varchar(50) NOT NULL,
  `Employee_ID` int NOT NULL,
  PRIMARY KEY (`Security_levelname_Levelname`,`Employee_ID`),
  KEY `fk_Security_level_employee_Employee1_idx` (`Employee_ID`),
  CONSTRAINT `fk_Security_level_employee_Employee1` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`ID`),
  CONSTRAINT `fk_Security_level_employee_Security_levelname1` FOREIGN KEY (`Security_levelname_Levelname`) REFERENCES `security_levelname` (`Levelname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_level_employee`
--

LOCK TABLES `security_level_employee` WRITE;
/*!40000 ALTER TABLE `security_level_employee` DISABLE KEYS */;
INSERT INTO `security_level_employee` VALUES ('4',1000),('2',1001),('2',1002),('2',1003),('4',1004),('3',1005);
/*!40000 ALTER TABLE `security_level_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_levelname`
--

DROP TABLE IF EXISTS `security_levelname`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `security_levelname` (
  `Levelname` varchar(50) NOT NULL,
  PRIMARY KEY (`Levelname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_levelname`
--

LOCK TABLES `security_levelname` WRITE;
/*!40000 ALTER TABLE `security_levelname` DISABLE KEYS */;
INSERT INTO `security_levelname` VALUES ('1'),('2'),('3'),('4');
/*!40000 ALTER TABLE `security_levelname` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'projectegor'
--

--
-- Dumping routines for database 'projectegor'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-12 23:29:04
