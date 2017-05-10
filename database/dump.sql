-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: time_tracker_schema
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `idcompany` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `logo_idimage` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `legal_number` varchar(45) NOT NULL,
  PRIMARY KEY (`idcompany`),
  KEY `fk_company_logo_idx` (`logo_idimage`),
  CONSTRAINT `fk_company_logo` FOREIGN KEY (`logo_idimage`) REFERENCES `image` (`idimage`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'EPAM',1,'Engineering is in our DNA','LALA1'),(2,'Itransition',2,'Itransition is a US-headquartered software development company with a pool of 1300 developers delivering full-cycle custom software development services.','LALA2'),(3,'ItechArt',3,'US-headquartered one-stop custom software development company with a pool of 650+ rock star devs. Bespoke software development services.\n','LALA3'),(4,'Resilio',4,'Eric is co-Founder and CEO of Resilio Inc. and brings over two decades of proven leadership experience in networking and technology companies. ','LALA4'),(5,'Paralect',5,'Your business should operate 24/7 with as little downtime as possible.','LALA5'),(6,'IBA',6,'IBA Group is one of the largest IT service providers in Eastern Europe. ','LALA6'),(7,'Grinteq',NULL,NULL,'LALA7'),(8,'Google',7,'Google is an American multinational technology company specializing in Internet-related services and products.','LALA8');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `idemployee` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `avatar_idimage` int(11) DEFAULT NULL,
  `role_idrole` int(11) NOT NULL,
  `company_idcompany` int(11) NOT NULL,
  `reset_password_token` varchar(255) DEFAULT NULL,
  `change_email_token` varchar(255) DEFAULT NULL,
  `confirm_register_token` varchar(255) DEFAULT NULL,
  `temp_email` varchar(45) DEFAULT NULL,
  `patronymic` varchar(45) DEFAULT NULL,
  `workday_idworkday_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`idemployee`),
  UNIQUE KEY `idx_employee_idemployee` (`idemployee`),
  KEY `fk_employee_avatar_idx` (`avatar_idimage`),
  KEY `fk_employee_role_idx` (`role_idrole`),
  KEY `fk_employee_company_idx` (`company_idcompany`),
  KEY `fk_employee_workday_idx` (`workday_idworkday_type`),
  CONSTRAINT `fk_employee_avatar` FOREIGN KEY (`avatar_idimage`) REFERENCES `image` (`idimage`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_company` FOREIGN KEY (`company_idcompany`) REFERENCES `company` (`idcompany`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_role` FOREIGN KEY (`role_idrole`) REFERENCES `role` (`idrole`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_workday` FOREIGN KEY (`workday_idworkday_type`) REFERENCES `workday_type` (`idworkday_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Marry','Popins','marry@gmai.com','fwFW8E4F',8,2,1,'FEW654FW6E5F',NULL,NULL,NULL,'Patronymic1',1),(2,'Nastya','Udot','nastya@gmai.com','F8E49FW65FWE56F',9,5,1,NULL,NULL,NULL,NULL,'Patronymic2',2),(3,'Anton','Rudov','anton@gmail.com','F4EW79F84FEW',10,5,2,'FEWF97EW965F1W6',NULL,NULL,NULL,'Patronymic3',3),(4,'Pasha','Carpesh','pasha@gmail.com','EF4EW8FEF6w5EF',11,2,1,NULL,NULL,'F498WEF4WE5F',NULL,'Patronymic4',4),(5,'Kolya','Ramashka','kolya@gmail.com','HRT1T56H4R65T',12,2,2,NULL,NULL,NULL,NULL,'Patronymic5',5),(6,'Olya','Shpoko','olya@gmail.com','B1ES64H9E8RG',13,4,1,NULL,'19WE8F7WE8F94E',NULL,'shporko@gmai.com',NULL,1),(7,'Denis','Zak','denis@gmail.com','EG98E7G98E4V6E5',14,3,2,'F4WE8F4WE65F',NULL,NULL,NULL,NULL,2),(8,'Sergey','Matalycki','sergey@gmail.com','FWE98F7WE89F',15,3,1,NULL,NULL,'WEFWFF62WE55F41',NULL,'Patronymic6',3),(9,'Dima','Mishin','dima@gmail.com','V2W7E9W8F7',16,2,3,NULL,NULL,NULL,NULL,NULL,4),(10,'Alex','Budnicki','alex@gmail.com','FFQ498ER79FV1CWE65',17,2,4,NULL,NULL,NULL,NULL,NULL,5),(11,'Oksana','Piskunova','oksana@gmail.com','WF89w78f4v1w465V',18,5,3,NULL,'F9WE8F9EWF561',NULL,'piskunova@gmail.com',NULL,1),(12,'Anna','Sripko','anna@gmail.com','WA98GWG456',19,3,4,NULL,NULL,NULL,NULL,'Patronymic7',2),(13,'Liza','Sandul','liza@gmail.com','we7fw9v56',20,4,3,'F1EW8F79WE8F75',NULL,NULL,NULL,NULL,3),(14,'Kate','Dobrilko','kate@gmail.com','VV2WFWE9F87',NULL,2,4,NULL,'FEWFEW4W89EF4EW56',NULL,'dobrilko@gmail.com',NULL,4),(15,'Andrew','Popov','andrew@gmail.com','QW98756VWE8',NULL,5,4,NULL,NULL,NULL,NULL,NULL,5);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `idimage` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `public_id` varchar(255) NOT NULL,
  PRIMARY KEY (`idimage`),
  UNIQUE KEY `idx_image_idimage` (`idimage`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,'dawdadawd','adwadwa'),(2,'dwadwadse','awrgthydj'),(3,'xawdfefsfes','dtjdjsrns'),(4,'sfesfesfsf4es4f','shsrhsj'),(5,'dadwa787wa','rhrtututyt'),(6,'awdwadcas2','jtru6ejtj'),(7,'adwadad78','rhr6uujtrj7trj7'),(8,'fw49e8f4e9w8f','lklfjwkjkewlf'),(9,'fw59e49465','vwe156w4e56v'),(10,'c1ew68f7w8e','c1ew6f79ew8f'),(11,'v2weewfwef','dqw98d7qw8'),(12,'fwe9fe8','dw9879wq8'),(13,'v1ew9878w9e','wv98e798ew'),(14,'c1ew98f798wef7','cew98798ew'),(15,'fwe987w9e8','few1f8we798f7'),(16,'f1w98ef7ew9f87','f2ew+98798wer7'),(17,'vew87r98qw','v7e98wf7we95f'),(18,'v2+w9e8798ew','c498we79we4w5f'),(19,'2f+we87r98w4','g4e9r8ge8a7g'),(20,'few98f7we89f','f4we98f79e8w4');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `period`
--

DROP TABLE IF EXISTS `period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `period` (
  `idperiod` int(11) NOT NULL,
  `start` datetime NOT NULL,
  `finish` datetime DEFAULT NULL,
  `employee_idemployee` int(11) NOT NULL,
  PRIMARY KEY (`idperiod`),
  UNIQUE KEY `idx_period_start_employee` (`start`,`employee_idemployee`),
  KEY `fk_period_employee_idx` (`employee_idemployee`),
  CONSTRAINT `fk_period_employee` FOREIGN KEY (`employee_idemployee`) REFERENCES `employee` (`idemployee`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `period`
--

LOCK TABLES `period` WRITE;
/*!40000 ALTER TABLE `period` DISABLE KEYS */;
INSERT INTO `period` VALUES (1,'2015-02-01 14:00:00','2015-02-01 22:00:00',1),(2,'2015-03-01 10:00:00','2015-03-01 20:00:00',12),(3,'2015-03-01 22:00:00','2015-03-02 06:00:00',12),(4,'2016-12-01 07:00:00','2016-12-01 22:00:00',1),(5,'2015-05-04 17:00:00','2015-05-04 18:00:00',4),(6,'2015-05-04 19:00:00','2015-05-04 22:00:00',4),(7,'2016-11-11 15:00:00','2016-11-12 00:00:00',5),(8,'2016-10-10 20:00:00','2016-10-10 23:00:00',6),(9,'2016-09-08 19:00:00','2016-09-08 21:00:00',7),(10,'2016-10-02 07:00:00','2016-10-02 09:00:00',8),(11,'2016-10-02 10:00:00','2016-10-02 12:00:00',8),(12,'2016-10-02 13:00:00','2016-10-02 15:00:00',8),(13,'2016-10-02 16:00:00','2016-10-02 18:00:00',8),(14,'2017-01-01 00:00:00','2017-01-01 22:00:00',9),(15,'2017-02-02 06:00:00','2017-02-02 18:00:00',1),(16,'2017-01-02 04:00:00','2017-01-02 22:00:00',12),(17,'2017-03-28 06:00:00','2017-03-28 10:00:00',14),(18,'2017-03-28 12:00:00',NULL,14),(19,'2017-03-28 12:00:00','2017-03-28 13:00:00',1),(20,'2017-03-28 12:00:00',NULL,9),(21,'2017-03-28 06:00:00',NULL,10),(22,'2017-03-28 06:00:00',NULL,13),(23,'2017-03-28 12:00:00',NULL,4),(24,'2017-03-28 14:00:00',NULL,1),(25,'2017-03-28 06:00:00',NULL,5);
/*!40000 ALTER TABLE `period` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `idproject` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `company_idcompany` int(11) NOT NULL,
  PRIMARY KEY (`idproject`),
  KEY `fk_project_company_idx` (`company_idcompany`),
  CONSTRAINT `fk_project_company` FOREIGN KEY (`company_idcompany`) REFERENCES `company` (`idcompany`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'Time Traker','Application for employees ',1),(2,'Torrent','Files synchronzation without common server',2),(3,'Sync',NULL,3),(4,'Connect',NULL,4),(5,'Mersedes',NULL,1),(6,'Sony Phone',NULL,2),(7,'Black notebook',NULL,3),(8,'LG matrix','Inovation in lg production',4),(9,'Copyrights',NULL,1),(10,'Intel core',NULL,2),(11,'VK mobile','Util for mobile vk application',3),(12,'Facebook',NULL,4),(13,'Maps',NULL,1);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `idrequest` int(11) NOT NULL,
  `source_idemployee` int(11) NOT NULL,
  `destination_idemployee` int(11) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`idrequest`),
  KEY `fk_request_destination_idx` (`destination_idemployee`),
  KEY `fk_request_source_idx` (`source_idemployee`),
  CONSTRAINT `fk_request_destination` FOREIGN KEY (`destination_idemployee`) REFERENCES `employee` (`idemployee`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_request_source` FOREIGN KEY (`source_idemployee`) REFERENCES `employee` (`idemployee`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,7,5,'2016-05-02 15:00:00'),(2,15,14,'2017-03-02 16:00:00'),(3,15,10,'2017-03-02 17:00:00'),(4,11,9,'2016-11-05 15:00:00'),(5,8,1,'2016-12-01 14:00:00'),(6,13,10,'2017-10-02 11:00:00'),(7,2,1,'2015-10-01 12:00:00');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `idrole` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `code` varchar(45) NOT NULL,
  PRIMARY KEY (`idrole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'GUEST','0'),(2,'EMPLOYEE','2'),(3,'HR','17'),(4,'ADMIN','26'),(5,'DIRECTOR','31');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `idtask` int(11) NOT NULL,
  `code` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `project_idproject` int(11) NOT NULL,
  PRIMARY KEY (`idtask`),
  KEY `fk_task_project_idx` (`project_idproject`),
  KEY `idx_task_project_idproject` (`project_idproject`),
  CONSTRAINT `fk_task_project` FOREIGN KEY (`project_idproject`) REFERENCES `project` (`idproject`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,'SYS-1','Insert button',1),(2,'MSP-2','Update requsets ',2),(3,'GDL-3','Create new module',3),(4,'MLO-8',NULL,4),(5,'LOA-5','Launch process',5),(6,'KIA-26','Fix bugs',1),(7,'LOA-8',NULL,6),(8,'KIA-78','Find error on client',7),(9,'LOAM-79','Create architecture',8),(10,'HYT-6','Validate input',9),(11,'MAT-646',NULL,10),(12,'MAT-64',NULL,2),(13,'ACR-7','Create script fot peers launch',1),(14,'MAR-5','New design',2),(15,'MAR-32','Remove connection',3),(16,'JEK-8','Quick access',4),(17,'ARG-5','Error during loading',3),(18,'AMT-2',NULL,10),(19,'HSK-789',NULL,9);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_period`
--

DROP TABLE IF EXISTS `task_period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_period` (
  `task_idtask` int(11) NOT NULL,
  `period_idperiod` int(11) NOT NULL,
  KEY `fk_task_period_task_idx` (`task_idtask`),
  KEY `fk_task_period_period_idx` (`period_idperiod`),
  CONSTRAINT `fk_task_period_period` FOREIGN KEY (`period_idperiod`) REFERENCES `period` (`idperiod`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_period_task` FOREIGN KEY (`task_idtask`) REFERENCES `task` (`idtask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_period`
--

LOCK TABLES `task_period` WRITE;
/*!40000 ALTER TABLE `task_period` DISABLE KEYS */;
INSERT INTO `task_period` VALUES (1,1),(1,5),(1,8),(7,7),(5,1),(7,7),(1,10),(2,25),(3,14),(10,24),(1,12),(2,9),(14,7),(19,13),(2,7),(1,10),(7,7),(9,17),(11,7),(12,9),(14,7),(13,10),(13,12),(5,15),(4,16),(15,5),(16,2);
/*!40000 ALTER TABLE `task_period` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workday_type`
--

DROP TABLE IF EXISTS `workday_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workday_type` (
  `idworkday_type` int(11) NOT NULL,
  `typename` varchar(45) NOT NULL,
  `time` time DEFAULT NULL,
  PRIMARY KEY (`idworkday_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workday_type`
--

LOCK TABLES `workday_type` WRITE;
/*!40000 ALTER TABLE `workday_type` DISABLE KEYS */;
INSERT INTO `workday_type` VALUES (1,'1/3','13:00:00'),(2,'2/3','26:00:00'),(3,'3/3','40:00:00'),(4,'1/2','20:00:00'),(5,'no','00:00:00');
/*!40000 ALTER TABLE `workday_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-24 11:55:52
