-- MariaDB dump 10.17  Distrib 10.4.13-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: aegisdb
-- ------------------------------------------------------
-- Server version	10.4.13-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin'),(2,'normal');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statistic`
--

DROP TABLE IF EXISTS `statistic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statistic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tl_id` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `stationary_vehicles_X` float NOT NULL,
  `stationary_vehicles_Y` float NOT NULL,
  `moving_vehicles_X` float NOT NULL,
  `moving_vehicles_Y` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tl_id` (`tl_id`),
  CONSTRAINT `fk_tl_id` FOREIGN KEY (`tl_id`) REFERENCES `trafficlight` (`tl_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statistic`
--

LOCK TABLES `statistic` WRITE;
/*!40000 ALTER TABLE `statistic` DISABLE KEYS */;
INSERT INTO `statistic` VALUES (1,1,'2020-07-17 20:35:09',5,10,6,11),(2,1,'2020-07-17 20:35:09',2,12,3,4),(3,2,'2020-07-17 20:35:09',4,7,2,1),(4,2,'2020-07-17 20:35:09',0,14,9,7);
/*!40000 ALTER TABLE `statistic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trafficlight`
--

DROP TABLE IF EXISTS `trafficlight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trafficlight` (
  `tl_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`tl_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trafficlight`
--

LOCK TABLES `trafficlight` WRITE;
/*!40000 ALTER TABLE `trafficlight` DISABLE KEYS */;
INSERT INTO `trafficlight` VALUES (1,'light1',0),(2,'light2',0);
/*!40000 ALTER TABLE `trafficlight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(12) NOT NULL,
  `password` varchar(256) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`),
  CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (21,'person1','$2a$10$rn8lu/oGTiL5jTqkFWke6uDJRCcqHxV.25/DlSXCgXw9WLxWayI6K',1),(22,'person2','$2a$10$jIlGgVVHH83hUHELFcsS5u2jEpnn2NBBMvBGq1lxvvG2x0WmHHIVq',1),(27,'person3','$2a$10$MtRTdRO3/GWu7D.1piaQQ.Bxq/tZaXw03HhWhkQtT3l7EUOVHciuC',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-17 23:00:14
