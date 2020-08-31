-- MariaDB dump 10.17  Distrib 10.4.6-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: aegisdb
-- ------------------------------------------------------
-- Server version	10.4.6-MariaDB

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
-- Table structure for table `intersection`
--

DROP TABLE IF EXISTS `intersection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `intersection` (
  `tl_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`tl_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 MAX_ROWS=6;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intersection`
--

LOCK TABLES `intersection` WRITE;
/*!40000 ALTER TABLE `intersection` DISABLE KEYS */;
INSERT INTO `intersection` VALUES (1,'intersection1',0),(2,'intersection2',0),(3,'intersection3',0),(4,'intersection4',0),(5,'intersection5',0),(6,'intersection6',0);
/*!40000 ALTER TABLE `intersection` ENABLE KEYS */;
UNLOCK TABLES;

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
  `phase` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=911 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statistic`
--

LOCK TABLES `statistic` WRITE;
/*!40000 ALTER TABLE `statistic` DISABLE KEYS */;
INSERT INTO `statistic` VALUES (761,1,'2020-08-31 21:09:06',0,4,1,2,0),(762,2,'2020-08-31 21:09:06',2,0,6,2,1),(763,3,'2020-08-31 21:09:06',1,0,4,4,1),(764,4,'2020-08-31 21:09:06',2,0,0,0,1),(765,5,'2020-08-31 21:09:06',2,0,0,0,1),(766,6,'2020-08-31 21:09:06',0,4,0,0,0),(767,1,'2020-08-31 21:09:09',0,4,1,4,0),(768,2,'2020-08-31 21:09:09',0,4,0,0,0),(769,3,'2020-08-31 21:09:09',1,0,6,4,1),(770,4,'2020-08-31 21:09:09',2,0,0,2,1),(771,5,'2020-08-31 21:09:10',2,0,1,0,1),(772,6,'2020-08-31 21:09:10',1,0,0,0,1),(773,1,'2020-08-31 21:09:13',2,0,0,0,1),(774,2,'2020-08-31 21:09:13',0,4,0,0,0),(775,3,'2020-08-31 21:09:13',1,0,7,4,1),(776,4,'2020-08-31 21:09:13',2,0,1,2,1),(777,5,'2020-08-31 21:09:13',0,2,0,0,0),(778,6,'2020-08-31 21:09:13',1,0,0,2,1),(779,1,'2020-08-31 21:09:17',2,0,0,1,1),(780,2,'2020-08-31 21:09:17',2,0,0,1,1),(781,3,'2020-08-31 21:09:17',1,0,9,6,1),(782,4,'2020-08-31 21:09:17',0,2,0,0,0),(783,5,'2020-08-31 21:09:17',0,2,0,0,0),(784,6,'2020-08-31 21:09:17',1,0,0,4,1),(785,1,'2020-08-31 21:09:20',0,4,0,0,0),(786,2,'2020-08-31 21:09:20',2,0,0,2,1),(787,3,'2020-08-31 21:09:20',1,0,9,7,1),(788,4,'2020-08-31 21:09:20',0,2,0,1,0),(789,5,'2020-08-31 21:09:20',2,0,0,0,1),(790,6,'2020-08-31 21:09:20',0,4,0,0,0),(791,1,'2020-08-31 21:09:24',0,4,0,1,0),(792,2,'2020-08-31 21:09:24',0,4,0,0,0),(793,3,'2020-08-31 21:09:24',1,0,10,7,1),(794,4,'2020-08-31 21:09:24',2,0,1,0,1),(795,5,'2020-08-31 21:09:24',2,0,0,0,1),(796,6,'2020-08-31 21:09:24',0,4,0,1,0),(797,1,'2020-08-31 21:09:27',2,0,0,3,1),(798,2,'2020-08-31 21:09:27',0,4,0,0,0),(799,3,'2020-08-31 21:09:27',1,0,10,8,1),(800,4,'2020-08-31 21:09:27',2,0,3,0,1),(801,5,'2020-08-31 21:09:27',0,2,0,0,0),(802,6,'2020-08-31 21:09:27',1,0,0,0,1),(803,1,'2020-08-31 21:09:31',2,0,0,3,1),(804,2,'2020-08-31 21:09:31',2,0,0,0,1),(805,3,'2020-08-31 21:09:31',0,4,0,0,0),(806,4,'2020-08-31 21:09:31',0,2,0,2,0),(807,5,'2020-08-31 21:09:31',0,2,0,0,0),(808,6,'2020-08-31 21:09:31',1,0,0,1,1),(809,1,'2020-08-31 21:09:34',2,0,0,3,1),(810,2,'2020-08-31 21:09:34',2,0,0,0,1),(811,3,'2020-08-31 21:09:34',0,4,0,2,0),(812,4,'2020-08-31 21:09:34',0,2,0,3,0),(813,5,'2020-08-31 21:09:34',2,0,0,0,1),(814,6,'2020-08-31 21:09:34',1,0,3,1,1),(815,1,'2020-08-31 21:09:38',0,4,0,1,0),(816,2,'2020-08-31 21:09:38',2,0,0,1,1),(817,3,'2020-08-31 21:09:38',0,4,0,3,0),(818,4,'2020-08-31 21:09:38',0,2,0,3,0),(819,5,'2020-08-31 21:09:38',2,0,0,0,1),(820,6,'2020-08-31 21:09:38',0,4,0,0,0),(821,1,'2020-08-31 21:09:41',0,4,0,2,0),(822,2,'2020-08-31 21:09:41',2,0,2,1,1),(823,3,'2020-08-31 21:09:41',0,4,0,5,0),(824,4,'2020-08-31 21:09:41',0,2,0,3,0),(825,5,'2020-08-31 21:09:41',2,0,0,1,1),(826,6,'2020-08-31 21:09:41',0,4,0,0,0),(827,1,'2020-08-31 21:09:45',2,0,0,0,1),(828,2,'2020-08-31 21:09:45',2,0,5,1,1),(829,3,'2020-08-31 21:09:45',0,4,0,7,0),(830,4,'2020-08-31 21:09:45',0,2,0,3,0),(831,5,'2020-08-31 21:09:45',0,2,0,0,0),(832,6,'2020-08-31 21:09:45',1,0,0,0,1),(833,1,'2020-08-31 21:09:48',2,0,0,2,1),(834,2,'2020-08-31 21:09:48',0,4,0,0,0),(835,3,'2020-08-31 21:09:48',1,0,1,0,1),(836,4,'2020-08-31 21:09:48',2,0,0,0,1),(837,5,'2020-08-31 21:09:48',0,2,0,0,0),(838,6,'2020-08-31 21:09:48',1,0,0,1,1),(839,1,'2020-08-31 21:09:52',0,4,0,0,0),(840,2,'2020-08-31 21:09:52',0,4,1,3,0),(841,3,'2020-08-31 21:09:52',1,0,4,2,1),(842,4,'2020-08-31 21:09:52',2,0,2,1,1),(843,5,'2020-08-31 21:09:52',2,0,0,0,1),(844,6,'2020-08-31 21:09:52',0,4,0,0,0),(845,1,'2020-08-31 21:09:55',0,4,1,1,0),(846,2,'2020-08-31 21:09:55',0,4,2,3,0),(847,3,'2020-08-31 21:09:55',1,0,6,3,1),(848,4,'2020-08-31 21:09:55',2,0,2,3,1),(849,5,'2020-08-31 21:09:55',2,0,0,0,1),(850,6,'2020-08-31 21:09:55',0,4,0,1,0),(851,1,'2020-08-31 21:09:59',2,0,0,1,1),(852,2,'2020-08-31 21:09:59',2,0,0,0,1),(853,3,'2020-08-31 21:09:59',1,0,7,3,1),(854,4,'2020-08-31 21:09:59',2,0,2,4,1),(855,5,'2020-08-31 21:09:59',0,2,0,0,0),(856,6,'2020-08-31 21:09:59',1,0,0,0,1),(857,1,'2020-08-31 21:10:02',2,0,1,1,1),(858,2,'2020-08-31 21:10:02',2,0,2,1,1),(859,3,'2020-08-31 21:10:02',0,4,0,0,0),(860,4,'2020-08-31 21:10:02',0,2,0,0,0),(861,5,'2020-08-31 21:10:02',0,2,0,0,0),(862,6,'2020-08-31 21:10:02',1,0,0,2,1),(863,1,'2020-08-31 21:10:06',0,4,1,0,0),(864,2,'2020-08-31 21:10:06',2,0,2,1,1),(865,3,'2020-08-31 21:10:06',0,4,0,2,0),(866,4,'2020-08-31 21:10:06',0,2,0,1,0),(867,5,'2020-08-31 21:10:06',0,2,0,0,0),(868,6,'2020-08-31 21:10:06',0,4,0,0,0),(869,1,'2020-08-31 21:10:09',0,4,1,1,0),(870,2,'2020-08-31 21:10:09',0,4,0,0,0),(871,3,'2020-08-31 21:10:09',1,0,0,0,1),(872,4,'2020-08-31 21:10:09',0,2,0,2,0),(873,5,'2020-08-31 21:10:09',2,0,0,0,1),(874,6,'2020-08-31 21:10:09',0,4,0,0,0),(875,1,'2020-08-31 21:10:13',0,4,3,2,0),(876,2,'2020-08-31 21:10:13',0,4,0,1,0),(877,3,'2020-08-31 21:10:13',1,0,4,3,1),(878,4,'2020-08-31 21:10:13',2,0,2,0,1),(879,5,'2020-08-31 21:10:13',2,0,0,1,1),(880,6,'2020-08-31 21:10:13',0,4,0,1,0),(881,1,'2020-08-31 21:10:16',2,0,0,0,1),(882,2,'2020-08-31 21:10:16',2,0,2,1,1),(883,3,'2020-08-31 21:10:16',1,0,4,4,1),(884,4,'2020-08-31 21:10:16',2,0,2,0,1),(885,5,'2020-08-31 21:10:16',0,2,0,0,0),(886,6,'2020-08-31 21:10:16',0,4,0,1,0),(887,1,'2020-08-31 21:10:20',2,0,1,1,1),(888,2,'2020-08-31 21:10:20',2,0,3,1,1),(889,3,'2020-08-31 21:10:20',0,4,0,0,0),(890,4,'2020-08-31 21:10:20',0,2,0,0,0),(891,5,'2020-08-31 21:10:20',0,2,0,0,0),(892,6,'2020-08-31 21:10:20',0,4,0,1,0),(893,1,'2020-08-31 21:10:23',2,0,2,1,1),(894,2,'2020-08-31 21:10:23',2,0,3,2,1),(895,3,'2020-08-31 21:10:23',0,4,0,2,0),(896,4,'2020-08-31 21:10:23',0,2,0,1,0),(897,5,'2020-08-31 21:10:23',2,0,0,0,1),(898,6,'2020-08-31 21:10:23',1,0,0,0,1),(899,1,'2020-08-31 21:10:27',0,4,0,0,0),(900,2,'2020-08-31 21:10:27',0,4,3,0,0),(901,3,'2020-08-31 21:10:27',1,0,0,0,1),(902,4,'2020-08-31 21:10:27',2,0,0,1,1),(903,5,'2020-08-31 21:10:27',2,0,0,1,1),(904,6,'2020-08-31 21:10:27',1,0,0,1,1),(905,1,'2020-08-31 21:10:30',0,4,0,0,0),(906,2,'2020-08-31 21:10:30',0,4,4,1,0),(907,3,'2020-08-31 21:10:30',1,0,0,2,1),(908,4,'2020-08-31 21:10:30',2,0,0,2,1),(909,5,'2020-08-31 21:10:31',0,2,0,0,0),(910,6,'2020-08-31 21:10:31',0,4,0,0,0);
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
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tl_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trafficlight`
--

LOCK TABLES `trafficlight` WRITE;
/*!40000 ALTER TABLE `trafficlight` DISABLE KEYS */;
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

--
-- Dumping routines for database 'aegisdb'
--
/*!50003 DROP PROCEDURE IF EXISTS `clean_db` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `clean_db`()
BEGIN 
DECLARE statCount INTEGER;
DROP TABLE IF EXISTS temp_stats;
		CREATE 
			TEMPORARY TABLE temp_stats
		SELECT 
			* 
		FROM
			aegisdb.statistic
		ORDER BY
			timestamp DESC
		LIMIT 
			150;
		DELETE FROM aegisdb.statistic; 
		INSERT INTO
			aegisdb.statistic
		SELECT 
			*
        FROM
			temp_stats;
		DROP TEMPORARY TABLE temp_stats;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-31 23:14:05
