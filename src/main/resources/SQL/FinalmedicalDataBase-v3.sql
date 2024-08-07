-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: medicalcomplexdb
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `deleted_patient_medicine`
--

DROP TABLE IF EXISTS `deleted_patient_medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deleted_patient_medicine` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `medicineId` bigint NOT NULL,
  `treatmentDeletedId` bigint NOT NULL,
  `createdDate` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8rg2pn41221y0dkbmig10nhpi` (`medicineId`),
  KEY `FK2oqrg8y2nappkgfw1fsi09ghf` (`treatmentDeletedId`),
  CONSTRAINT `FK2oqrg8y2nappkgfw1fsi09ghf` FOREIGN KEY (`treatmentDeletedId`) REFERENCES `treatmens_deleted` (`id`),
  CONSTRAINT `FK8rg2pn41221y0dkbmig10nhpi` FOREIGN KEY (`medicineId`) REFERENCES `medicine` (`id`),
  CONSTRAINT `FKq7fxrmnqnbmkkrgnu27krqfb5` FOREIGN KEY (`treatmentDeletedId`) REFERENCES `treatments_deleted` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deleted_patient_medicine`
--

LOCK TABLES `deleted_patient_medicine` WRITE;
/*!40000 ALTER TABLE `deleted_patient_medicine` DISABLE KEYS */;
/*!40000 ALTER TABLE `deleted_patient_medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdDate` datetime(6) NOT NULL,
  `name` varchar(255) NOT NULL,
  `headId` bigint DEFAULT NULL,
  `secretaryId` bigint DEFAULT NULL,
  `isDeleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FK1jrdm9kercgq2flsescfntnpu` (`headId`),
  KEY `FKcr8qxpu6ulpkxqyvxlbf2yhdc` (`secretaryId`),
  CONSTRAINT `FK1jrdm9kercgq2flsescfntnpu` FOREIGN KEY (`headId`) REFERENCES `users` (`id`),
  CONSTRAINT `FKcr8qxpu6ulpkxqyvxlbf2yhdc` FOREIGN KEY (`secretaryId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments`
--

LOCK TABLES `departments` WRITE;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` VALUES (1,'2024-07-25 16:52:11.853000','Departmrnt 1',1,2,_binary ''),(2,'2024-07-25 16:52:18.085000','Departmrnt 1',1,3,_binary ''),(3,'2024-07-25 16:52:24.146000','Departmrnt 1',2,3,_binary '\0'),(4,'2024-07-25 16:52:27.943000','Departmrnt 1',3,3,_binary '\0'),(5,'2024-07-26 02:47:14.788000','department 4',2,3,_binary '\0'),(6,'2024-07-26 03:35:38.718000','mohammad AbuThaher',1,1,_binary '\0'),(7,'2024-07-27 12:27:22.599000','Department Test',NULL,NULL,_binary ''),(8,'2024-07-27 12:27:33.265000','Department Test2',NULL,1,_binary ''),(9,'2024-07-27 12:59:20.072000','sdfsdfsd',1,1,_binary '\0'),(10,'2023-01-15 08:00:00.000000','Cardiology',1,2,_binary '\0'),(11,'2023-02-20 09:30:00.000000','Neurology',2,3,_binary '\0'),(12,'2023-03-25 10:45:00.000000','Oncology',3,4,_binary '\0'),(13,'2023-04-10 11:00:00.000000','Pediatrics',4,5,_binary '\0'),(14,'2023-05-18 12:30:00.000000','Orthopedics',5,6,_binary '\0'),(15,'2023-06-22 14:00:00.000000','Dermatology',6,7,_binary '\0'),(16,'2023-07-05 15:15:00.000000','Gynecology',7,1,_binary '\0'),(17,'2023-07-10 16:45:00.000000','Urology',1,3,_binary '\0'),(18,'2023-07-15 17:30:00.000000','Gastroenterology',2,4,_binary '\0'),(19,'2023-01-22 10:20:00.000000','Nephrology',3,5,_binary '\0'),(20,'2023-02-28 13:45:00.000000','Pulmonology',4,6,_binary '\0'),(21,'2023-03-19 11:30:00.000000','Rheumatology',5,7,_binary '\0'),(22,'2023-04-25 12:00:00.000000','Endocrinology',6,1,_binary '\0'),(23,'2023-05-30 14:30:00.000000','Hematology',7,2,_binary '\0'),(24,'2023-06-15 15:00:00.000000','Immunology',1,4,_binary '\0'),(25,'2023-07-18 09:00:00.000000','Pathology',2,5,_binary '\0'),(26,'2023-01-10 13:10:00.000000','Ophthalmology',3,6,_binary '\0'),(27,'2023-02-15 08:40:00.000000','Otolaryngology',4,7,_binary '\0'),(28,'2023-03-10 10:10:00.000000','Allergy and Immunology',5,1,_binary '\0'),(29,'2023-04-15 09:30:00.000000','Infectious Disease',6,2,_binary '\0'),(30,'2023-05-20 16:30:00.000000','General Surgery',7,3,_binary '\0'),(31,'2023-06-25 11:50:00.000000','Plastic Surgery',1,5,_binary '\0'),(32,'2023-07-20 08:20:00.000000','Thoracic Surgery',2,6,_binary '\0'),(33,'2023-01-30 14:40:00.000000','Vascular Surgery',3,7,_binary '\0'),(34,'2023-02-25 15:50:00.000000','Neurosurgery',4,1,_binary '\0'),(35,'2023-03-30 13:00:00.000000','Pediatric Surgery',5,2,_binary '\0'),(36,'2023-04-20 09:20:00.000000','Anesthesiology',6,3,_binary '\0'),(37,'2023-05-25 16:00:00.000000','Critical Care Medicine',7,4,_binary '\0'),(38,'2023-06-30 10:50:00.000000','Emergency Medicine',1,6,_binary '\0'),(39,'2023-07-25 14:10:00.000000','Geriatric Medicine',2,7,_binary '\0'),(40,'2023-01-25 08:50:00.000000','Hospital Medicine',3,1,_binary '\0'),(41,'2023-02-20 11:15:00.000000','Sports Medicine',4,2,_binary '\0'),(42,'2023-03-15 12:45:00.000000','Occupational Medicine',5,3,_binary '\0'),(43,'2023-04-30 16:20:00.000000','Preventive Medicine',6,4,_binary '\0'),(44,'2023-05-15 09:45:00.000000','Radiology',7,5,_binary '\0'),(45,'2023-06-20 10:30:00.000000','Nuclear Medicine',1,7,_binary '\0'),(46,'2023-07-05 13:20:00.000000','Physical Medicine and Rehabilitation',2,1,_binary '\0'),(47,'2023-01-12 09:05:00.000000','Pain Medicine',3,2,_binary '\0'),(48,'2023-02-17 14:50:00.000000','Palliative Care',4,3,_binary '\0'),(49,'2023-03-23 15:30:00.000000','Sleep Medicine',5,4,_binary '\0'),(50,'2023-04-29 16:45:00.000000','Medical Genetics',6,5,_binary '\0'),(51,'2023-05-11 10:15:00.000000','Clinical Pharmacology',7,6,_binary '\0'),(52,'2023-06-14 13:05:00.000000','Adolescent Medicine',1,7,_binary '\0'),(53,'2023-07-07 15:25:00.000000','Neonatal-Perinatal Medicine',2,1,_binary '\0'),(54,'2023-01-09 14:55:00.000000','Genetics',3,2,_binary '\0'),(55,'2023-02-21 12:20:00.000000','Gastrointestinal Surgery',4,3,_binary '\0'),(56,'2023-03-11 08:35:00.000000','Microbiology',5,4,_binary '\0'),(57,'2023-04-06 10:05:00.000000','Public Health',6,5,_binary '\0'),(58,'2023-05-16 11:50:00.000000','Occupational Therapy',7,6,_binary '\0');
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctors` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `beginTime` time(6) NOT NULL,
  `endTime` time(6) NOT NULL,
  `specialization` enum('CARDIOLOGIST','DERMATOLOGIST','NEUROLOGIST','ORTHOPEDIST','PEDIATRICIAN','PSYCHIATRIST','SURGEON') NOT NULL,
  `userId` bigint DEFAULT NULL,
  `createdDate` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKe6x00khiy7dsx1wpb16710k56` (`userId`),
  CONSTRAINT `FK4y20awggxgbm502fbnn3qxuql` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` VALUES (1,'09:00:00.000000','17:00:00.000000','PEDIATRICIAN',4,'2024-08-05 00:42:23.000000'),(2,'22:00:00.000000','23:00:00.000000','NEUROLOGIST',41,'2024-08-05 00:42:25.000000'),(3,'08:00:00.000000','16:00:00.000000','CARDIOLOGIST',42,'2024-08-05 00:42:25.000000'),(4,'09:00:00.000000','17:00:00.000000','DERMATOLOGIST',43,'2024-08-05 00:42:25.000000'),(5,'10:00:00.000000','18:00:00.000000','ORTHOPEDIST',44,'2024-08-05 00:42:25.000000'),(6,'11:00:00.000000','19:00:00.000000','PEDIATRICIAN',45,'2024-08-05 00:42:25.000000'),(7,'12:00:00.000000','20:00:00.000000','PSYCHIATRIST',46,'2024-08-05 00:42:25.000000'),(8,'13:00:00.000000','21:00:00.000000','SURGEON',47,'2024-08-05 00:42:25.000000'),(9,'14:00:00.000000','22:00:00.000000','NEUROLOGIST',48,'2024-08-05 00:42:25.000000'),(10,'15:00:00.000000','23:00:00.000000','CARDIOLOGIST',49,'2024-08-05 00:42:25.000000'),(11,'16:00:00.000000','00:00:00.000000','DERMATOLOGIST',50,'2024-08-05 00:42:25.000000'),(12,'17:00:00.000000','01:00:00.000000','ORTHOPEDIST',51,'2024-08-05 00:42:25.000000'),(13,'18:00:00.000000','02:00:00.000000','PEDIATRICIAN',52,'2024-08-05 00:42:25.000000'),(14,'19:00:00.000000','03:00:00.000000','PSYCHIATRIST',53,'2024-08-05 00:42:25.000000'),(15,'20:00:00.000000','04:00:00.000000','SURGEON',54,'2024-08-05 00:42:26.000000'),(16,'21:00:00.000000','05:00:00.000000','NEUROLOGIST',55,'2024-08-05 00:42:26.000000'),(17,'22:00:00.000000','06:00:00.000000','CARDIOLOGIST',56,'2024-08-05 00:42:26.000000'),(18,'23:00:00.000000','07:00:00.000000','DERMATOLOGIST',57,'2024-08-05 00:42:26.000000'),(19,'00:00:00.000000','08:00:00.000000','ORTHOPEDIST',58,'2024-08-05 00:42:26.000000'),(20,'01:00:00.000000','09:00:00.000000','PEDIATRICIAN',59,'2024-08-05 00:42:26.000000'),(21,'02:00:00.000000','10:00:00.000000','PSYCHIATRIST',60,'2024-08-05 00:42:26.000000'),(22,'03:00:00.000000','11:00:00.000000','SURGEON',61,'2024-08-05 00:42:26.000000'),(23,'04:00:00.000000','12:00:00.000000','NEUROLOGIST',62,'2024-08-05 00:42:26.000000'),(24,'05:00:00.000000','13:00:00.000000','CARDIOLOGIST',63,'2024-08-05 00:42:26.000000'),(25,'06:00:00.000000','14:00:00.000000','DERMATOLOGIST',64,'2024-08-05 00:42:26.000000'),(26,'07:00:00.000000','15:00:00.000000','ORTHOPEDIST',65,'2024-08-05 00:42:26.000000'),(27,'08:00:00.000000','16:00:00.000000','PEDIATRICIAN',66,'2024-08-05 00:42:26.000000'),(28,'09:00:00.000000','17:00:00.000000','PSYCHIATRIST',67,'2024-08-05 00:42:26.000000'),(29,'10:00:00.000000','18:00:00.000000','SURGEON',68,'2024-08-05 00:42:26.000000'),(30,'11:00:00.000000','19:00:00.000000','NEUROLOGIST',69,'2024-08-05 00:42:27.000000'),(31,'12:00:00.000000','20:00:00.000000','CARDIOLOGIST',70,'2024-08-05 00:42:27.000000'),(32,'13:00:00.000000','21:00:00.000000','DERMATOLOGIST',71,'2024-08-05 00:42:27.000000'),(33,'14:00:00.000000','22:00:00.000000','ORTHOPEDIST',72,'2024-08-05 00:42:27.000000'),(34,'15:00:00.000000','23:00:00.000000','PEDIATRICIAN',73,'2024-08-05 00:42:27.000000'),(35,'16:00:00.000000','00:00:00.000000','PSYCHIATRIST',74,'2024-08-05 00:42:27.000000'),(36,'17:00:00.000000','01:00:00.000000','SURGEON',75,'2024-08-05 00:42:27.000000'),(37,'18:00:00.000000','02:00:00.000000','NEUROLOGIST',76,'2024-08-05 00:42:27.000000'),(38,'09:00:00.000000','17:00:00.000000','ORTHOPEDIST',79,'2024-08-06 10:17:19.251000');
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emails`
--

DROP TABLE IF EXISTS `emails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emails` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `verificationCode` varchar(255) NOT NULL,
  `verified` bit(1) NOT NULL,
  `createdDate` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emails`
--

LOCK TABLES `emails` WRITE;
/*!40000 ALTER TABLE `emails` DISABLE KEYS */;
INSERT INTO `emails` VALUES (12,'mohammadabuthaher06@gmail.com','moha',_binary '','2024-08-05 00:42:14.000000'),(14,'mohammadabuthaher06@gmail.com','370e0298-b974-4304-8aa1-89590d6a62d4',_binary '\0','2024-08-05 00:42:16.000000');
/*!40000 ALTER TABLE `emails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdDate` datetime(6) NOT NULL,
  `comment` varchar(255) NOT NULL,
  `rating` int NOT NULL,
  `doctorId` bigint NOT NULL,
  `patientId` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5mhpk2u5gm992x7xo7xwj82gn` (`doctorId`),
  KEY `FK6yjobvocowqlepc7arr6fguhy` (`patientId`),
  CONSTRAINT `FK5mhpk2u5gm992x7xo7xwj82gn` FOREIGN KEY (`doctorId`) REFERENCES `doctors` (`id`),
  CONSTRAINT `FK6yjobvocowqlepc7arr6fguhy` FOREIGN KEY (`patientId`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,'2024-08-06 13:10:48.070000','good doctor',3,38,102);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filedata`
--

DROP TABLE IF EXISTS `filedata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `filedata` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdDate` datetime(6) NOT NULL,
  `filePath` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filedata`
--

LOCK TABLES `filedata` WRITE;
/*!40000 ALTER TABLE `filedata` DISABLE KEYS */;
INSERT INTO `filedata` VALUES (1,'2024-08-05 13:36:29.069000','C:\\Users\\AbuThaher\\Desktop\\Traning Project\\ProjectTraninng\\src\\main\\resources\\PDF\\Treatments Report1722854188550.pdf','TreatmentsReport1722854188550.pdf','application/pdf'),(2,'2024-08-05 13:38:35.209000','C:\\Users\\moham\\Desktop\\ProjectFinalTraninng\\projecttraning\\src\\main\\resources\\PDF\\Treatments Report1722854314715.pdf','TreatmentsReport1722854314715.pdf','application/pdf'),(3,'2024-08-05 13:40:09.299000','C:\\Users\\moham\\Desktop\\ProjectFinalTraninng\\projecttraning\\src\\main\\resources\\PDF\\Treatments Report1722854408794.pdf','TreatmentsReport1722854408794.pdf','application/pdf'),(4,'2024-08-05 13:59:26.105000','C:\\Users\\moham\\Desktop\\ProjectFinalTraninng\\projecttraning\\src\\main\\resources\\PDF\\Treatments Report1722855565597.pdf','TreatmentsReport1722855565597.pdf','application/pdf'),(5,'2024-08-05 14:02:05.990000','C:\\Users\\moham\\Desktop\\ProjectFinalTraninng\\projecttraning\\src\\main\\resources\\PDF\\Treatments Report1722855725480.pdf','TreatmentsReport1722855725480.pdf','application/pdf'),(6,'2024-08-07 14:57:43.206000','C:\\Users\\moham\\Desktop\\ProjectFinalTraninng\\projecttraning\\src\\main\\resources\\Excels\\PationData1723031862927.xls','PationData1723031863199.xls','application/vnd.ms-excel');
/*!40000 ALTER TABLE `filedata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicine` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `buyPrice` double NOT NULL,
  `createdDate` datetime(6) NOT NULL,
  `expirationDate` datetime(6) NOT NULL,
  `name` varchar(255) NOT NULL,
  `purchasePrice` double NOT NULL,
  `isDeleted` bit(1) NOT NULL DEFAULT b'0',
  `supplierId` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhtlm9imtfynryp6nknm8fva71` (`supplierId`),
  CONSTRAINT `FKhtlm9imtfynryp6nknm8fva71` FOREIGN KEY (`supplierId`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
INSERT INTO `medicine` VALUES (1,25,'2024-07-25 15:46:18.616000','2002-07-12 03:00:00.000000','acmool',20,_binary '',1),(2,30,'2024-07-25 15:46:42.438000','2002-07-12 03:00:00.000000','pandooll',25,_binary '',2),(3,30,'2024-07-25 15:46:52.536000','2002-07-12 03:00:00.000000','asbreen',25,_binary '\0',3),(4,10.5,'2023-01-01 12:00:00.000000','2024-01-01 12:00:00.000000','Medicine A',12,_binary '\0',4),(5,8.75,'2023-02-01 12:00:00.000000','2024-02-01 12:00:00.000000','Medicine B',10,_binary '\0',5),(6,15,'2023-03-01 12:00:00.000000','2024-03-01 12:00:00.000000','Medicine C',17,_binary '\0',6),(7,9.5,'2023-04-01 12:00:00.000000','2024-04-01 12:00:00.000000','Medicine D',11,_binary '\0',7),(8,11.25,'2023-05-01 12:00:00.000000','2024-05-01 12:00:00.000000','Medicine E',13,_binary '\0',8),(9,7.75,'2023-06-01 12:00:00.000000','2024-06-01 12:00:00.000000','Medicine F',9,_binary '\0',9),(10,13.5,'2023-07-01 12:00:00.000000','2024-07-01 12:00:00.000000','Medicine G',15,_binary '\0',10),(11,10,'2023-08-01 12:00:00.000000','2024-08-01 12:00:00.000000','Medicine H',11.5,_binary '\0',1),(12,8.25,'2023-09-01 12:00:00.000000','2024-09-01 12:00:00.000000','Medicine I',9.5,_binary '\0',2),(13,16.75,'2023-10-01 12:00:00.000000','2024-10-01 12:00:00.000000','Medicine J',18,_binary '\0',3),(14,9,'2023-11-01 12:00:00.000000','2024-11-01 12:00:00.000000','Medicine K',10.25,_binary '\0',4),(15,12.5,'2023-12-01 12:00:00.000000','2024-12-01 12:00:00.000000','Medicine L',14,_binary '\0',5),(16,14.75,'2024-01-01 12:00:00.000000','2025-01-01 12:00:00.000000','Medicine M',16.5,_binary '\0',6),(17,10.5,'2024-02-01 12:00:00.000000','2025-02-01 12:00:00.000000','Medicine N',12.25,_binary '\0',7),(18,8,'2024-03-01 12:00:00.000000','2025-03-01 12:00:00.000000','Medicine O',9.75,_binary '\0',8),(19,15.25,'2024-04-01 12:00:00.000000','2025-04-01 12:00:00.000000','Medicine P',17.5,_binary '\0',9),(20,11.75,'2024-05-01 12:00:00.000000','2025-05-01 12:00:00.000000','Medicine Q',13.5,_binary '\0',10),(21,9.5,'2024-06-01 12:00:00.000000','2025-06-01 12:00:00.000000','Medicine R',11.25,_binary '\0',1),(22,13,'2024-07-01 12:00:00.000000','2025-07-01 12:00:00.000000','Medicine S',14.75,_binary '\0',2),(23,8.75,'2024-08-01 12:00:00.000000','2025-08-01 12:00:00.000000','Medicine T',10,_binary '\0',3),(24,14,'2024-09-01 12:00:00.000000','2025-09-01 12:00:00.000000','Medicine U',15.75,_binary '\0',4),(25,10.25,'2024-10-01 12:00:00.000000','2025-10-01 12:00:00.000000','Medicine V',11.5,_binary '\0',5),(26,12.75,'2024-11-01 12:00:00.000000','2025-11-01 12:00:00.000000','Medicine W',14,_binary '\0',6),(27,15.5,'2024-12-01 12:00:00.000000','2025-12-01 12:00:00.000000','Medicine X',17.25,_binary '\0',7),(28,9.25,'2025-01-01 12:00:00.000000','2026-01-01 12:00:00.000000','Medicine Y',10.5,_binary '\0',8),(29,11,'2025-02-01 12:00:00.000000','2026-02-01 12:00:00.000000','Medicine Z',12.75,_binary '\0',9),(30,8.5,'2025-03-01 12:00:00.000000','2026-03-01 12:00:00.000000','Medicine AA',9.75,_binary '\0',10),(31,14.25,'2025-04-01 12:00:00.000000','2026-04-01 12:00:00.000000','Medicine BB',16,_binary '\0',1),(32,10.75,'2025-05-01 12:00:00.000000','2026-05-01 12:00:00.000000','Medicine CC',12,_binary '\0',2),(33,7.5,'2025-06-01 12:00:00.000000','2026-06-01 12:00:00.000000','Medicine DD',9,_binary '\0',3),(34,13.25,'2025-07-01 12:00:00.000000','2026-07-01 12:00:00.000000','Medicine EE',14.5,_binary '\0',4),(35,8,'2025-08-01 12:00:00.000000','2026-08-01 12:00:00.000000','Medicine FF',9.75,_binary '\0',5),(36,15.75,'2025-09-01 12:00:00.000000','2026-09-01 12:00:00.000000','Medicine GG',17,_binary '\0',6),(37,9.75,'2025-10-01 12:00:00.000000','2026-10-01 12:00:00.000000','Medicine HH',11.25,_binary '\0',7),(38,12.25,'2025-11-01 12:00:00.000000','2026-11-01 12:00:00.000000','Medicine II',14,_binary '\0',8),(39,14.5,'2025-12-01 12:00:00.000000','2026-12-01 12:00:00.000000','Medicine JJ',16.25,_binary '\0',9),(40,10,'2026-01-01 12:00:00.000000','2027-01-01 12:00:00.000000','Medicine KK',11.5,_binary '\0',10),(41,11.75,'2026-02-01 12:00:00.000000','2027-02-01 12:00:00.000000','Medicine LL',13.5,_binary '\0',1),(42,8.75,'2026-03-01 12:00:00.000000','2027-03-01 12:00:00.000000','Medicine MM',10,_binary '\0',2),(43,15,'2026-04-01 12:00:00.000000','2027-04-01 12:00:00.000000','Medicine NN',16.75,_binary '\0',3),(44,11.25,'2026-05-01 12:00:00.000000','2027-05-01 12:00:00.000000','Medicine OO',12.5,_binary '\0',4),(45,9,'2026-06-01 12:00:00.000000','2027-06-01 12:00:00.000000','Medicine PP',10.75,_binary '\0',5),(46,13.5,'2026-07-01 12:00:00.000000','2027-07-01 12:00:00.000000','Medicine QQ',14.75,_binary '\0',6),(47,10.5,'2026-08-01 12:00:00.000000','2027-08-01 12:00:00.000000','Medicine RR',12,_binary '\0',7),(48,7.75,'2026-09-01 12:00:00.000000','2027-09-01 12:00:00.000000','Medicine SS',9,_binary '\0',8),(49,14.75,'2026-10-01 12:00:00.000000','2027-10-01 12:00:00.000000','Medicine TT',16.5,_binary '\0',9),(50,9.25,'2026-11-01 12:00:00.000000','2027-11-01 12:00:00.000000','Medicine UU',10.5,_binary '\0',10),(51,11,'2026-12-01 12:00:00.000000','2027-12-01 12:00:00.000000','Medicine VV',12.75,_binary '\0',1),(52,8.5,'2027-01-01 12:00:00.000000','2028-01-01 12:00:00.000000','Medicine WW',9.75,_binary '\0',2),(53,14,'2027-02-01 12:00:00.000000','2028-02-01 12:00:00.000000','Medicine XX',15.75,_binary '\0',3),(54,10.25,'2027-03-01 12:00:00.000000','2028-03-01 12:00:00.000000','Medicine YY',11.5,_binary '\0',4),(55,12.75,'2027-04-01 12:00:00.000000','2028-04-01 12:00:00.000000','Medicine ZZ',14,_binary '\0',5),(56,15.5,'2027-05-01 12:00:00.000000','2028-05-01 12:00:00.000000','Medicine AAA',17.25,_binary '\0',6),(57,9.5,'2027-06-01 12:00:00.000000','2028-06-01 12:00:00.000000','Medicine BBB',11,_binary '\0',7),(58,11.25,'2027-07-01 12:00:00.000000','2028-07-01 12:00:00.000000','Medicine CCC',12.5,_binary '\0',8),(59,8,'2027-08-01 12:00:00.000000','2028-08-01 12:00:00.000000','Medicine DDD',9.75,_binary '\0',9),(60,13.75,'2027-09-01 12:00:00.000000','2028-09-01 12:00:00.000000','Medicine EEE',15.25,_binary '\0',10),(61,10,'2027-10-01 12:00:00.000000','2028-10-01 12:00:00.000000','Medicine FFF',11.5,_binary '\0',1),(62,12.25,'2027-11-01 12:00:00.000000','2028-11-01 12:00:00.000000','Medicine GGG',14,_binary '\0',2),(63,15.75,'2027-12-01 12:00:00.000000','2028-12-01 12:00:00.000000','Medicine HHH',17,_binary '\0',3),(64,20,'2024-07-29 10:26:40.556000','2028-09-22 03:00:00.000000','ansoleen',15,_binary '\0',4);
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdDate` datetime(6) NOT NULL,
  `isRead` bit(1) NOT NULL,
  `message` text NOT NULL,
  `patientId` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn2u1etulk717bvcvs4n56ln9w` (`patientId`),
  CONSTRAINT `FKn2u1etulk717bvcvs4n56ln9w` FOREIGN KEY (`patientId`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (6,'2024-08-07 16:50:00.162000',_binary '\0','You have a treatment scheduled for today with Dr. mohammad Ahmad.\nDisease Description: disses diseaseDescription\nNote: test note',100),(7,'2024-08-07 16:50:00.227000',_binary '\0','You have a treatment scheduled for today with Dr. mohammad Ahmad.\nDisease Description: disssssss\nNote: noteeeeeeeeeee',102),(8,'2024-08-07 16:50:00.253000',_binary '\0','You have a treatment scheduled for today with Dr. mohammad Ahmad.\nDisease Description: disssssss\nNote: noteeeeeeeeeee',102),(9,'2024-08-07 16:53:00.158000',_binary '\0','You have a treatment scheduled for today with Dr. mohammad Ahmad.\nDisease Description: disssssss22\nNote: noteeeeeeeeeee222',99);
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_medicine`
--

DROP TABLE IF EXISTS `patient_medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient_medicine` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `medicineId` bigint NOT NULL,
  `treatmentId` bigint DEFAULT NULL,
  `createdDate` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKno88osn5qj7c6xfsijj7tjpfq` (`medicineId`),
  KEY `FK95wahk45fe2ungydaq90fg2db` (`treatmentId`),
  CONSTRAINT `FK95wahk45fe2ungydaq90fg2db` FOREIGN KEY (`treatmentId`) REFERENCES `treatments` (`id`),
  CONSTRAINT `FKno88osn5qj7c6xfsijj7tjpfq` FOREIGN KEY (`medicineId`) REFERENCES `medicine` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_medicine`
--

LOCK TABLES `patient_medicine` WRITE;
/*!40000 ALTER TABLE `patient_medicine` DISABLE KEYS */;
INSERT INTO `patient_medicine` VALUES (1,50,5,1,1,'2024-08-05 00:41:39.000000'),(2,50,5,1,1,'2024-08-05 00:41:41.000000'),(3,100,10,1,1,'2024-08-05 00:41:41.000000'),(4,100,10,1,2,'2024-08-05 00:41:41.000000'),(5,44,3,1,2,'2024-08-05 00:41:41.000000'),(6,460,3,1,2,'2024-08-05 00:41:41.000000'),(7,460,3,2,2,'2024-08-05 00:41:41.000000'),(8,34,3,2,2,'2024-08-05 00:41:41.000000'),(9,34,3,2,1,'2024-08-05 00:41:41.000000'),(10,50,5,3,1,'2024-08-05 00:41:41.000000'),(11,134,9,3,1,'2024-08-05 00:41:41.000000'),(12,134,9,3,2,'2024-08-05 00:41:41.000000'),(13,12.75,2,1,3,'2024-08-05 00:41:41.000000'),(14,9.5,1,2,4,'2024-08-05 00:41:41.000000'),(15,11.25,3,3,5,'2024-08-05 00:41:42.000000'),(16,8,1,4,6,'2024-08-05 00:41:42.000000'),(17,13.75,2,5,7,'2024-08-05 00:41:42.000000'),(18,10,1,6,8,'2024-08-05 00:41:42.000000'),(19,12.25,3,7,9,'2024-08-05 00:41:42.000000'),(20,15.75,2,8,10,'2024-08-05 00:41:42.000000'),(21,10.5,1,9,11,'2024-08-05 00:41:42.000000'),(22,13,2,10,12,'2024-08-05 00:41:42.000000'),(23,11.5,1,11,13,'2024-08-05 00:41:42.000000'),(24,14.25,3,12,14,'2024-08-05 00:41:42.000000'),(25,9.75,2,13,15,'2024-08-05 00:41:42.000000'),(26,12,1,14,16,'2024-08-05 00:41:42.000000'),(27,10.75,3,15,17,'2024-08-05 00:41:42.000000'),(28,13.5,2,16,18,'2024-08-05 00:41:42.000000'),(29,11,1,17,19,'2024-08-05 00:41:42.000000'),(30,14.75,3,18,20,'2024-08-05 00:41:43.000000'),(31,9.25,2,19,21,'2024-08-05 00:41:43.000000'),(32,12.5,1,20,22,'2024-08-05 00:41:43.000000'),(33,10.25,3,21,23,'2024-08-05 00:41:43.000000'),(34,13.75,2,22,24,'2024-08-05 00:41:43.000000'),(35,11.25,1,23,25,'2024-08-05 00:41:43.000000'),(36,15,3,24,26,'2024-08-05 00:41:43.000000'),(37,9,2,25,27,'2024-08-05 00:41:43.000000'),(38,12.75,1,26,28,'2024-08-05 00:41:43.000000'),(39,10.5,3,27,29,'2024-08-05 00:41:43.000000'),(40,14.25,2,28,30,'2024-08-05 00:41:43.000000'),(41,11,1,29,31,'2024-08-05 00:41:43.000000'),(42,15.5,3,30,32,'2024-08-05 00:41:43.000000'),(43,9.5,2,31,33,'2024-08-05 00:41:43.000000'),(44,12.75,1,32,34,'2024-08-05 00:41:43.000000'),(45,10,3,33,35,'2024-08-05 00:41:44.000000'),(46,13.5,2,34,36,'2024-08-05 00:41:44.000000'),(47,11.25,1,35,37,'2024-08-05 00:41:44.000000'),(48,15.75,3,36,38,'2024-08-05 00:41:44.000000'),(49,9.25,2,37,39,'2024-08-05 00:41:44.000000'),(50,12.5,1,38,40,'2024-08-05 00:41:44.000000'),(51,10.75,3,39,41,'2024-08-05 00:41:44.000000'),(52,14,2,40,42,'2024-08-05 00:41:44.000000'),(53,11,1,41,43,'2024-08-05 00:41:44.000000'),(54,15.75,3,42,44,'2024-08-05 00:41:44.000000'),(55,9,2,43,45,'2024-08-05 00:41:44.000000'),(56,13,1,44,46,'2024-08-05 00:41:44.000000'),(57,10.5,3,45,47,'2024-08-05 00:41:44.000000'),(58,14.25,2,46,48,'2024-08-05 00:41:44.000000'),(59,11.25,1,47,49,'2024-08-05 00:41:44.000000'),(60,15.5,3,48,50,'2024-08-05 00:41:44.000000'),(61,9.75,2,49,51,'2024-08-05 00:41:45.000000'),(62,12,1,50,52,'2024-08-05 00:41:45.000000'),(63,10.5,3,51,53,'2024-08-05 00:41:45.000000'),(64,14,2,52,54,'2024-08-05 00:41:45.000000'),(65,11.5,1,53,55,'2024-08-05 00:41:45.000000'),(66,15.25,3,54,56,'2024-08-05 00:41:45.000000'),(67,9,2,55,57,'2024-08-05 00:41:45.000000'),(68,13.75,1,56,58,'2024-08-05 00:41:45.000000'),(69,10.25,3,57,59,'2024-08-05 00:41:45.000000'),(70,14.5,2,58,60,'2024-08-05 00:41:45.000000'),(71,11,1,59,61,'2024-08-05 00:41:45.000000'),(72,15,3,60,62,'2024-08-05 00:41:45.000000'),(73,9.25,2,61,63,'2024-08-05 00:41:45.000000'),(74,12.5,1,62,64,'2024-08-05 00:41:45.000000'),(75,10.75,3,63,65,'2024-08-05 00:41:45.000000'),(76,14,2,1,66,'2024-08-05 00:41:46.000000'),(77,11.5,1,2,67,'2024-08-05 00:41:46.000000'),(78,15.25,3,3,68,'2024-08-05 00:41:46.000000'),(79,9.75,2,4,69,'2024-08-05 00:41:46.000000'),(80,12,1,5,70,'2024-08-05 00:41:46.000000'),(81,10.25,3,6,71,'2024-08-05 00:41:46.000000'),(82,14.75,2,7,72,'2024-08-05 00:41:46.000000'),(83,11,1,8,73,'2024-08-05 00:41:46.000000'),(84,15.5,3,9,74,'2024-08-05 00:41:46.000000'),(85,9.5,2,10,75,'2024-08-05 00:41:46.000000'),(86,12.75,1,11,76,'2024-08-05 00:41:46.000000'),(87,10,3,12,77,'2024-08-05 00:41:46.000000'),(88,13.5,2,13,78,'2024-08-05 00:41:46.000000'),(89,11.25,1,14,79,'2024-08-05 00:41:46.000000'),(90,15.75,3,15,80,'2024-08-05 00:41:46.000000'),(91,9.25,2,16,81,'2024-08-05 00:41:47.000000'),(92,12.5,1,17,82,'2024-08-05 00:41:47.000000'),(93,10.75,3,18,83,'2024-08-05 00:41:47.000000'),(94,14,2,19,84,'2024-08-05 00:41:47.000000'),(95,11,1,20,85,'2024-08-05 00:41:47.000000'),(96,15.75,3,21,86,'2024-08-05 00:41:47.000000'),(97,9,2,22,87,'2024-08-05 00:41:47.000000'),(98,13,1,23,88,'2024-08-05 00:41:47.000000'),(99,10.5,3,24,89,'2024-08-05 00:41:47.000000'),(100,14.25,2,25,90,'2024-08-05 00:41:47.000000'),(101,11.25,1,26,91,'2024-08-05 00:41:47.000000'),(102,15.5,3,27,92,'2024-08-05 00:41:47.000000'),(103,9.75,2,28,93,'2024-08-05 00:41:47.000000'),(104,12,1,29,94,'2024-08-05 00:41:47.000000'),(105,10.5,3,30,95,'2024-08-05 00:41:47.000000'),(106,14,2,31,96,'2024-08-05 00:41:48.000000'),(107,11.5,1,32,97,'2024-08-05 00:41:48.000000'),(108,15.25,3,33,98,'2024-08-05 00:41:48.000000'),(109,9,2,34,99,'2024-08-05 00:41:48.000000'),(110,13.75,1,35,100,'2024-08-05 00:41:48.000000'),(111,10.25,3,36,101,'2024-08-05 00:41:48.000000'),(112,14.5,2,37,102,'2024-08-05 00:41:48.000000'),(113,11,1,38,103,'2024-08-05 00:41:48.000000'),(114,15,3,39,104,'2024-08-05 00:41:48.000000'),(115,9.25,2,40,105,'2024-08-05 00:41:48.000000'),(116,12.5,1,41,106,'2024-08-05 00:41:48.000000'),(117,10.75,3,42,107,'2024-08-05 00:41:48.000000'),(118,14,2,43,108,'2024-08-05 00:41:48.000000'),(119,11.5,1,44,109,'2024-08-05 00:41:48.000000'),(120,15.25,3,45,110,'2024-08-05 00:41:48.000000'),(121,9.75,2,46,111,'2024-08-05 00:41:49.000000'),(122,12,1,47,112,'2024-08-05 00:41:49.000000'),(123,10.25,3,48,113,'2024-08-05 00:41:49.000000'),(124,14.75,2,49,114,'2024-08-05 00:41:49.000000'),(125,11,1,50,115,'2024-08-05 00:41:49.000000'),(126,15.5,3,51,116,'2024-08-05 00:41:49.000000'),(127,9.5,2,52,117,'2024-08-05 00:41:49.000000'),(128,12.75,1,53,118,'2024-08-05 00:41:49.000000'),(129,10,3,54,119,'2024-08-05 00:41:49.000000'),(130,13.5,2,55,120,'2024-08-05 00:41:49.000000');
/*!40000 ALTER TABLE `patient_medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `age` int NOT NULL,
  `createdDate` datetime(6) NOT NULL,
  `userId` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKigjhgvbk9kjqx1yg9bpt99d3v` (`userId`),
  CONSTRAINT `FKigjhgvbk9kjqx1yg9bpt99d3v` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (1,50,'2024-07-25 10:43:40.895000',81),(2,50,'2024-07-25 10:49:01.629000',82),(53,25,'2023-01-10 10:00:00.000000',83),(54,30,'2023-02-12 11:00:00.000000',84),(55,35,'2023-03-15 12:00:00.000000',85),(56,40,'2023-04-18 13:00:00.000000',86),(57,45,'2023-05-20 14:00:00.000000',87),(58,50,'2023-06-22 15:00:00.000000',88),(59,55,'2023-07-25 16:00:00.000000',89),(60,60,'2023-08-28 17:00:00.000000',90),(61,65,'2023-09-30 18:00:00.000000',91),(62,70,'2023-10-12 19:00:00.000000',92),(63,75,'2023-11-14 20:00:00.000000',93),(64,80,'2023-12-16 21:00:00.000000',94),(65,85,'2024-01-18 22:00:00.000000',95),(66,90,'2024-02-20 23:00:00.000000',96),(67,95,'2024-03-22 23:00:00.000000',97),(68,100,'2024-04-24 00:00:00.000000',98),(69,105,'2024-05-26 01:00:00.000000',99),(70,110,'2024-06-28 02:00:00.000000',100),(71,115,'2024-07-30 03:00:00.000000',101),(72,120,'2024-08-01 04:00:00.000000',102),(73,125,'2024-09-02 05:00:00.000000',103),(74,130,'2024-10-03 06:00:00.000000',104),(75,135,'2024-11-04 07:00:00.000000',105),(76,140,'2024-12-05 08:00:00.000000',106),(77,145,'2025-01-06 09:00:00.000000',107),(78,150,'2025-02-07 10:00:00.000000',108),(79,155,'2025-03-08 11:00:00.000000',109),(80,160,'2025-04-09 12:00:00.000000',110),(81,165,'2025-05-10 13:00:00.000000',111),(82,170,'2025-06-11 14:00:00.000000',112),(83,175,'2025-07-12 15:00:00.000000',113),(84,180,'2025-08-13 16:00:00.000000',114),(85,185,'2025-09-14 17:00:00.000000',115),(86,190,'2025-10-15 18:00:00.000000',116),(87,195,'2025-11-16 19:00:00.000000',117),(88,200,'2025-12-17 20:00:00.000000',118),(89,205,'2026-01-18 21:00:00.000000',119),(90,210,'2026-02-19 22:00:00.000000',120),(91,215,'2026-03-20 23:00:00.000000',121),(92,220,'2026-04-21 00:00:00.000000',122),(93,225,'2026-05-22 01:00:00.000000',123),(94,230,'2026-06-23 02:00:00.000000',124),(95,235,'2026-07-24 03:00:00.000000',125),(96,240,'2026-08-25 04:00:00.000000',126),(97,245,'2026-09-26 05:00:00.000000',127),(98,250,'2026-10-27 06:00:00.000000',128),(99,255,'2026-11-28 07:00:00.000000',129),(100,260,'2026-12-29 08:00:00.000000',130),(101,265,'2027-01-30 09:00:00.000000',131),(102,270,'2024-08-07 13:21:17.000000',132),(103,56,'2024-08-07 13:25:26.384000',133);
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients_deleted`
--

DROP TABLE IF EXISTS `patients_deleted`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients_deleted` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `age` int NOT NULL,
  `createdDate` datetime(6) DEFAULT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `patientDeletedId` bigint DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients_deleted`
--

LOCK TABLES `patients_deleted` WRITE;
/*!40000 ALTER TABLE `patients_deleted` DISABLE KEYS */;
/*!40000 ALTER TABLE `patients_deleted` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salarypayments`
--

DROP TABLE IF EXISTS `salarypayments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salarypayments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `paymentDate` date NOT NULL,
  `salaryType` varchar(255) NOT NULL,
  `salaryId` bigint NOT NULL,
  `createdDate` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqrbqjhvkd6jin3c9kl0ukq88h` (`salaryId`),
  CONSTRAINT `FKqrbqjhvkd6jin3c9kl0ukq88h` FOREIGN KEY (`salaryId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=216 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salarypayments`
--

LOCK TABLES `salarypayments` WRITE;
/*!40000 ALTER TABLE `salarypayments` DISABLE KEYS */;
INSERT INTO `salarypayments` VALUES (1,1663,'2023-01-01','MONTHLY',1,'2024-08-05 00:41:05.000000'),(2,1833,'2023-02-01','MONTHLY',1,'2024-08-05 00:41:07.000000'),(3,1175,'2023-03-01','MONTHLY',1,'2024-08-05 00:41:07.000000'),(4,1379,'2023-04-01','MONTHLY',1,'2024-08-05 00:41:07.000000'),(5,1371,'2023-05-01','MONTHLY',1,'2024-08-05 00:41:07.000000'),(6,1716,'2023-06-01','MONTHLY',2,'2024-08-05 00:41:07.000000'),(7,1469,'2023-07-01','MONTHLY',2,'2024-08-05 00:41:07.000000'),(8,1198,'2023-08-01','MONTHLY',2,'2024-08-05 00:41:07.000000'),(9,1585,'2023-09-01','MONTHLY',2,'2024-08-05 00:41:07.000000'),(10,1329,'2023-10-01','MONTHLY',2,'2024-08-05 00:41:07.000000'),(11,1890,'2023-06-01','MONTHLY',3,'2024-08-05 00:41:07.000000'),(12,1466,'2023-07-01','MONTHLY',3,'2024-08-05 00:41:07.000000'),(13,1658,'2023-08-01','MONTHLY',3,'2024-08-05 00:41:07.000000'),(14,1892,'2023-09-01','MONTHLY',3,'2024-08-05 00:41:08.000000'),(15,1488,'2023-10-01','MONTHLY',3,'2024-08-05 00:41:08.000000'),(16,1763,'2023-05-01','MONTHLY',4,'2024-08-05 00:41:08.000000'),(17,1353,'2023-02-01','MONTHLY',4,'2024-08-05 00:41:08.000000'),(18,1476,'2023-04-01','MONTHLY',4,'2024-08-05 00:41:08.000000'),(19,1322,'2023-03-01','MONTHLY',4,'2024-08-05 00:41:08.000000'),(20,1184,'2023-08-01','MONTHLY',4,'2024-08-05 00:41:08.000000'),(21,1953,'2023-05-01','MONTHLY',5,'2024-08-05 00:41:08.000000'),(22,1215,'2023-02-01','MONTHLY',5,'2024-08-05 00:41:08.000000'),(23,1216,'2023-04-01','MONTHLY',5,'2024-08-05 00:41:08.000000'),(24,1433,'2023-03-01','MONTHLY',5,'2024-08-05 00:41:08.000000'),(25,1520,'2023-08-01','MONTHLY',5,'2024-08-05 00:41:08.000000'),(26,1299,'2023-02-01','MONTHLY',6,'2024-08-05 00:41:08.000000'),(27,1938,'2023-06-01','MONTHLY',6,'2024-08-05 00:41:08.000000'),(28,1793,'2023-09-01','MONTHLY',6,'2024-08-05 00:41:08.000000'),(29,1152,'2023-11-01','MONTHLY',6,'2024-08-05 00:41:09.000000'),(30,1383,'2023-12-01','MONTHLY',6,'2024-08-05 00:41:09.000000'),(31,1457,'2023-05-01','MONTHLY',41,'2024-08-05 00:41:09.000000'),(32,1139,'2023-06-01','MONTHLY',41,'2024-08-05 00:41:09.000000'),(33,1323,'2023-08-01','MONTHLY',41,'2024-08-05 00:41:09.000000'),(34,1201,'2023-11-01','MONTHLY',41,'2024-08-05 00:41:09.000000'),(35,1034,'2023-12-01','MONTHLY',41,'2024-08-05 00:41:09.000000'),(36,1566,'2023-02-01','MONTHLY',42,'2024-08-05 00:41:09.000000'),(37,1731,'2023-01-01','MONTHLY',42,'2024-08-05 00:41:09.000000'),(38,1956,'2023-03-01','MONTHLY',42,'2024-08-05 00:41:09.000000'),(39,1586,'2023-11-01','MONTHLY',42,'2024-08-05 00:41:09.000000'),(40,1064,'2023-12-01','MONTHLY',42,'2024-08-05 00:41:09.000000'),(41,1563,'2023-02-01','MONTHLY',44,'2024-08-05 00:41:09.000000'),(42,1625,'2023-01-01','MONTHLY',44,'2024-08-05 00:41:09.000000'),(43,1433,'2023-03-01','MONTHLY',44,'2024-08-05 00:41:09.000000'),(44,1292,'2023-04-01','MONTHLY',44,'2024-08-05 00:41:10.000000'),(45,1163,'2023-05-01','MONTHLY',44,'2024-08-05 00:41:10.000000'),(46,1939,'2023-05-01','HOURLY',43,'2024-08-05 00:41:10.000000'),(47,1205,'2023-07-01','HOURLY',43,'2024-08-05 00:41:10.000000'),(48,1211,'2023-08-01','HOURLY',43,'2024-08-05 00:41:10.000000'),(49,1440,'2023-12-01','HOURLY',43,'2024-08-05 00:41:10.000000'),(50,1567,'2023-11-01','HOURLY',43,'2024-08-05 00:41:10.000000'),(51,1515,'2023-02-01','MONTHLY',46,'2024-08-05 00:41:10.000000'),(52,1874,'2023-01-01','MONTHLY',46,'2024-08-05 00:41:10.000000'),(53,1824,'2023-03-01','MONTHLY',46,'2024-08-05 00:41:10.000000'),(54,1500,'2023-04-01','MONTHLY',46,'2024-08-05 00:41:10.000000'),(55,1028,'2023-05-01','MONTHLY',46,'2024-08-05 00:41:10.000000'),(56,1639,'2023-02-01','HOURLY',45,'2024-08-05 00:41:10.000000'),(57,1114,'2023-01-01','HOURLY',45,'2024-08-05 00:41:10.000000'),(58,1653,'2023-03-01','HOURLY',45,'2024-08-05 00:41:10.000000'),(59,1924,'2023-06-01','HOURLY',45,'2024-08-05 00:41:11.000000'),(60,1660,'2023-07-01','HOURLY',45,'2024-08-05 00:41:11.000000'),(61,1531,'2023-02-01','MONTHLY',48,'2024-08-05 00:41:11.000000'),(62,1676,'2023-01-01','MONTHLY',48,'2024-08-05 00:41:11.000000'),(63,1784,'2023-03-01','MONTHLY',48,'2024-08-05 00:41:11.000000'),(64,1895,'2023-04-01','MONTHLY',48,'2024-08-05 00:41:11.000000'),(65,1123,'2023-05-01','MONTHLY',48,'2024-08-05 00:41:11.000000'),(66,1930,'2023-02-01','HOURLY',47,'2024-08-05 00:41:11.000000'),(67,1280,'2023-01-01','HOURLY',47,'2024-08-05 00:41:11.000000'),(68,1614,'2023-03-01','HOURLY',47,'2024-08-05 00:41:11.000000'),(69,1227,'2023-06-01','HOURLY',47,'2024-08-05 00:41:11.000000'),(70,1294,'2023-07-01','HOURLY',47,'2024-08-05 00:41:11.000000'),(71,1790,'2023-02-01','MONTHLY',50,'2024-08-05 00:41:11.000000'),(72,1068,'2023-01-01','MONTHLY',50,'2024-08-05 00:41:11.000000'),(73,1971,'2023-03-01','MONTHLY',50,'2024-08-05 00:41:11.000000'),(74,1653,'2023-04-01','MONTHLY',50,'2024-08-05 00:41:12.000000'),(75,1353,'2023-05-01','MONTHLY',50,'2024-08-05 00:41:12.000000'),(76,1807,'2023-02-01','HOURLY',49,'2024-08-05 00:41:12.000000'),(77,1976,'2023-01-01','HOURLY',49,'2024-08-05 00:41:12.000000'),(78,1457,'2023-03-01','HOURLY',49,'2024-08-05 00:41:12.000000'),(79,1359,'2023-06-01','HOURLY',49,'2024-08-05 00:41:12.000000'),(80,1426,'2023-07-01','HOURLY',49,'2024-08-05 00:41:12.000000'),(81,1051,'2023-02-01','MONTHLY',52,'2024-08-05 00:41:12.000000'),(82,1978,'2023-01-01','MONTHLY',52,'2024-08-05 00:41:12.000000'),(83,1738,'2023-03-01','MONTHLY',52,'2024-08-05 00:41:12.000000'),(84,1757,'2023-04-01','MONTHLY',52,'2024-08-05 00:41:12.000000'),(85,1572,'2023-05-01','MONTHLY',52,'2024-08-05 00:41:12.000000'),(86,1590,'2023-02-01','HOURLY',51,'2024-08-05 00:41:12.000000'),(87,1234,'2023-01-01','HOURLY',51,'2024-08-05 00:41:12.000000'),(88,1400,'2023-03-01','HOURLY',51,'2024-08-05 00:41:12.000000'),(89,1298,'2023-06-01','HOURLY',51,'2024-08-05 00:41:13.000000'),(90,1290,'2023-07-01','HOURLY',51,'2024-08-05 00:41:13.000000'),(91,1557,'2023-02-01','MONTHLY',54,'2024-08-05 00:41:13.000000'),(92,1915,'2023-01-01','MONTHLY',54,'2024-08-05 00:41:13.000000'),(93,1905,'2023-03-01','MONTHLY',54,'2024-08-05 00:41:13.000000'),(94,1779,'2023-04-01','MONTHLY',54,'2024-08-05 00:41:13.000000'),(95,1184,'2023-05-01','MONTHLY',54,'2024-08-05 00:41:13.000000'),(96,1580,'2023-02-01','HOURLY',53,'2024-08-05 00:41:13.000000'),(97,1350,'2023-01-01','HOURLY',53,'2024-08-05 00:41:13.000000'),(98,1011,'2023-03-01','HOURLY',53,'2024-08-05 00:41:13.000000'),(99,1004,'2023-06-01','HOURLY',53,'2024-08-05 00:41:13.000000'),(100,1986,'2023-07-01','HOURLY',53,'2024-08-05 00:41:13.000000'),(101,1921,'2023-02-01','MONTHLY',56,'2024-08-05 00:41:13.000000'),(102,1646,'2023-01-01','MONTHLY',56,'2024-08-05 00:41:13.000000'),(103,1468,'2023-03-01','MONTHLY',56,'2024-08-05 00:41:13.000000'),(104,1404,'2023-04-01','MONTHLY',56,'2024-08-05 00:41:14.000000'),(105,1616,'2023-05-01','MONTHLY',56,'2024-08-05 00:41:14.000000'),(106,1867,'2023-02-01','HOURLY',55,'2024-08-05 00:41:14.000000'),(107,1490,'2023-01-01','HOURLY',55,'2024-08-05 00:41:14.000000'),(108,1848,'2023-03-01','HOURLY',55,'2024-08-05 00:41:14.000000'),(109,1771,'2023-06-01','HOURLY',55,'2024-08-05 00:41:14.000000'),(110,1311,'2023-07-01','HOURLY',55,'2024-08-05 00:41:14.000000'),(111,1243,'2023-02-01','MONTHLY',58,'2024-08-05 00:41:14.000000'),(112,1282,'2023-01-01','MONTHLY',58,'2024-08-05 00:41:14.000000'),(113,1680,'2023-03-01','MONTHLY',58,'2024-08-05 00:41:14.000000'),(114,1557,'2023-04-01','MONTHLY',58,'2024-08-05 00:41:14.000000'),(115,1745,'2023-05-01','MONTHLY',58,'2024-08-05 00:41:14.000000'),(116,1053,'2023-02-01','HOURLY',57,'2024-08-05 00:41:14.000000'),(117,1033,'2023-01-01','HOURLY',57,'2024-08-05 00:41:14.000000'),(118,1006,'2023-03-01','HOURLY',57,'2024-08-05 00:41:14.000000'),(119,1929,'2023-06-01','HOURLY',57,'2024-08-05 00:41:14.000000'),(120,1631,'2023-07-01','HOURLY',57,'2024-08-05 00:41:15.000000'),(121,1367,'2023-02-01','MONTHLY',60,'2024-08-05 00:41:15.000000'),(122,1941,'2023-01-01','MONTHLY',60,'2024-08-05 00:41:15.000000'),(123,1606,'2023-03-01','MONTHLY',60,'2024-08-05 00:41:15.000000'),(124,1208,'2023-04-01','MONTHLY',60,'2024-08-05 00:41:15.000000'),(125,1222,'2023-05-01','MONTHLY',60,'2024-08-05 00:41:15.000000'),(126,1486,'2023-02-01','HOURLY',59,'2024-08-05 00:41:15.000000'),(127,1764,'2023-01-01','HOURLY',59,'2024-08-05 00:41:15.000000'),(128,1364,'2023-03-01','HOURLY',59,'2024-08-05 00:41:15.000000'),(129,1528,'2023-06-01','HOURLY',59,'2024-08-05 00:41:15.000000'),(130,1549,'2023-07-01','HOURLY',59,'2024-08-05 00:41:15.000000'),(131,1162,'2023-02-01','MONTHLY',62,'2024-08-05 00:41:15.000000'),(132,1164,'2023-01-01','MONTHLY',62,'2024-08-05 00:41:15.000000'),(133,1334,'2023-03-01','MONTHLY',62,'2024-08-05 00:41:15.000000'),(134,1180,'2023-04-01','MONTHLY',62,'2024-08-05 00:41:15.000000'),(135,1896,'2023-05-01','MONTHLY',62,'2024-08-05 00:41:16.000000'),(136,1943,'2023-02-01','HOURLY',61,'2024-08-05 00:41:16.000000'),(137,1027,'2023-01-01','HOURLY',61,'2024-08-05 00:41:16.000000'),(138,1307,'2023-03-01','HOURLY',61,'2024-08-05 00:41:16.000000'),(139,1453,'2023-06-01','HOURLY',61,'2024-08-05 00:41:16.000000'),(140,1345,'2023-07-01','HOURLY',61,'2024-08-05 00:41:16.000000'),(141,1368,'2023-06-01','MONTHLY',64,'2024-08-05 00:41:16.000000'),(142,1805,'2023-08-01','MONTHLY',64,'2024-08-05 00:41:16.000000'),(143,1922,'2023-09-01','MONTHLY',64,'2024-08-05 00:41:16.000000'),(144,1196,'2023-10-01','MONTHLY',64,'2024-08-05 00:41:16.000000'),(145,1215,'2023-11-01','MONTHLY',64,'2024-08-05 00:41:16.000000'),(146,1485,'2023-04-01','HOURLY',63,'2024-08-05 00:41:16.000000'),(147,1783,'2023-05-01','HOURLY',63,'2024-08-05 00:41:16.000000'),(148,1460,'2023-03-01','HOURLY',63,'2024-08-05 00:41:16.000000'),(149,1953,'2023-12-01','HOURLY',63,'2024-08-05 00:41:16.000000'),(150,1384,'2023-07-01','HOURLY',63,'2024-08-05 00:41:17.000000'),(151,1063,'2023-06-01','MONTHLY',66,'2024-08-05 00:41:17.000000'),(152,1162,'2023-08-01','MONTHLY',66,'2024-08-05 00:41:17.000000'),(153,1620,'2023-09-01','MONTHLY',66,'2024-08-05 00:41:17.000000'),(154,1616,'2023-10-01','MONTHLY',66,'2024-08-05 00:41:17.000000'),(155,1221,'2023-11-01','MONTHLY',66,'2024-08-05 00:41:17.000000'),(156,1256,'2023-04-01','HOURLY',65,'2024-08-05 00:41:17.000000'),(157,1620,'2023-05-01','HOURLY',65,'2024-08-05 00:41:17.000000'),(158,1332,'2023-03-01','HOURLY',65,'2024-08-05 00:41:17.000000'),(159,1798,'2023-12-01','HOURLY',65,'2024-08-05 00:41:17.000000'),(160,1998,'2023-07-01','HOURLY',65,'2024-08-05 00:41:17.000000'),(161,1594,'2023-06-01','MONTHLY',68,'2024-08-05 00:41:17.000000'),(162,1976,'2023-08-01','MONTHLY',68,'2024-08-05 00:41:17.000000'),(163,1098,'2023-09-01','MONTHLY',68,'2024-08-05 00:41:17.000000'),(164,1564,'2023-10-01','MONTHLY',68,'2024-08-05 00:41:17.000000'),(165,1524,'2023-11-01','MONTHLY',68,'2024-08-05 00:41:18.000000'),(166,1931,'2023-04-01','HOURLY',67,'2024-08-05 00:41:18.000000'),(167,1081,'2023-05-01','HOURLY',67,'2024-08-05 00:41:18.000000'),(168,1615,'2023-03-01','HOURLY',67,'2024-08-05 00:41:18.000000'),(169,1833,'2023-12-01','HOURLY',67,'2024-08-05 00:41:18.000000'),(170,1320,'2023-07-01','HOURLY',67,'2024-08-05 00:41:18.000000'),(171,1101,'2023-06-01','MONTHLY',70,'2024-08-05 00:41:18.000000'),(172,1547,'2023-08-01','MONTHLY',70,'2024-08-05 00:41:18.000000'),(173,1431,'2023-09-01','MONTHLY',70,'2024-08-05 00:41:18.000000'),(174,1514,'2023-10-01','MONTHLY',70,'2024-08-05 00:41:18.000000'),(175,1278,'2023-11-01','MONTHLY',70,'2024-08-05 00:41:18.000000'),(176,1847,'2023-04-01','HOURLY',69,'2024-08-05 00:41:18.000000'),(177,1405,'2023-05-01','HOURLY',69,'2024-08-05 00:41:18.000000'),(178,1482,'2023-03-01','HOURLY',69,'2024-08-05 00:41:18.000000'),(179,1195,'2023-12-01','HOURLY',69,'2024-08-05 00:41:18.000000'),(180,1528,'2023-07-01','HOURLY',69,'2024-08-05 00:41:19.000000'),(181,1059,'2023-06-01','MONTHLY',72,'2024-08-05 00:41:19.000000'),(182,1711,'2023-08-01','MONTHLY',72,'2024-08-05 00:41:19.000000'),(183,1376,'2023-09-01','MONTHLY',72,'2024-08-05 00:41:19.000000'),(184,1750,'2023-10-01','MONTHLY',72,'2024-08-05 00:41:19.000000'),(185,1621,'2023-11-01','MONTHLY',72,'2024-08-05 00:41:19.000000'),(186,1857,'2023-04-01','HOURLY',71,'2024-08-05 00:41:19.000000'),(187,1423,'2023-05-01','HOURLY',71,'2024-08-05 00:41:19.000000'),(188,1543,'2023-03-01','HOURLY',71,'2024-08-05 00:41:19.000000'),(189,1449,'2023-12-01','HOURLY',71,'2024-08-05 00:41:19.000000'),(190,1615,'2023-07-01','HOURLY',71,'2024-08-05 00:41:19.000000'),(191,1728,'2023-06-01','MONTHLY',74,'2024-08-05 00:41:19.000000'),(192,1796,'2023-08-01','MONTHLY',74,'2024-08-05 00:41:19.000000'),(193,1797,'2023-09-01','MONTHLY',74,'2024-08-05 00:41:19.000000'),(194,1598,'2023-10-01','MONTHLY',74,'2024-08-05 00:41:19.000000'),(195,1597,'2023-11-01','MONTHLY',74,'2024-08-05 00:41:19.000000'),(196,1192,'2023-04-01','HOURLY',73,'2024-08-05 00:41:20.000000'),(197,1170,'2023-05-01','HOURLY',73,'2024-08-05 00:41:20.000000'),(198,1273,'2023-03-01','HOURLY',73,'2024-08-05 00:41:20.000000'),(199,1858,'2023-12-01','HOURLY',73,'2024-08-05 00:41:20.000000'),(200,1469,'2023-07-01','HOURLY',73,'2024-08-05 00:41:20.000000'),(201,1771,'2023-06-01','MONTHLY',76,'2024-08-05 00:41:20.000000'),(202,1451,'2023-08-01','MONTHLY',76,'2024-08-05 00:41:20.000000'),(203,1940,'2023-09-01','MONTHLY',76,'2024-08-05 00:41:20.000000'),(204,1349,'2023-10-01','MONTHLY',76,'2024-08-05 00:41:20.000000'),(205,1924,'2023-11-01','MONTHLY',76,'2024-08-05 00:41:20.000000'),(206,1576,'2023-04-01','HOURLY',75,'2024-08-05 00:41:20.000000'),(207,1108,'2023-05-01','HOURLY',75,'2024-08-05 00:41:20.000000'),(208,1813,'2023-03-01','HOURLY',75,'2024-08-05 00:41:20.000000'),(209,1739,'2023-12-01','HOURLY',75,'2024-08-05 00:41:20.000000'),(210,1258,'2023-07-01','HOURLY',75,'2024-08-05 00:41:20.000000'),(211,1075,'2023-06-01','MONTHLY',78,'2024-08-05 00:41:21.000000'),(212,1602,'2023-08-01','MONTHLY',78,'2024-08-05 00:41:21.000000'),(213,1784,'2023-09-01','MONTHLY',78,'2024-08-05 00:41:21.000000'),(214,1113,'2023-10-01','MONTHLY',78,'2024-08-05 00:41:21.000000'),(215,1216,'2023-11-01','MONTHLY',78,'2024-08-05 00:41:21.000000');
/*!40000 ALTER TABLE `salarypayments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `companyName` enum('COMPANY_NAMES','COMPANY_NAMES1','COMPANY_NAMES10','COMPANY_NAMES2','COMPANY_NAMES3','COMPANY_NAMES4','COMPANY_NAMES5','COMPANY_NAMES6','COMPANY_NAMES7','COMPANY_NAMES8','COMPANY_NAMES9') NOT NULL,
  `createdDate` datetime(6) NOT NULL,
  `isDeleted` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'Address 1','COMPANY_NAMES','2024-07-31 10:55:55.000000',_binary '\0','Supplier 1','123-456-7890'),(2,'Address 2','COMPANY_NAMES1','2024-07-31 10:55:55.000000',_binary '\0','Supplier 2','234-567-8901'),(3,'Address 3','COMPANY_NAMES2','2024-07-31 10:55:55.000000',_binary '\0','Supplier 3','345-678-9012'),(4,'Address 4','COMPANY_NAMES3','2024-07-31 10:55:55.000000',_binary '\0','Supplier 4','456-789-0123'),(5,'Address 5','COMPANY_NAMES4','2024-07-31 10:55:55.000000',_binary '\0','Supplier 5','567-890-1234'),(6,'Address 6','COMPANY_NAMES5','2024-07-31 10:55:55.000000',_binary '\0','Supplier 6','678-901-2345'),(7,'Address 7','COMPANY_NAMES6','2024-07-31 10:55:55.000000',_binary '\0','Supplier 7','789-012-3456'),(8,'Address 8','COMPANY_NAMES7','2024-07-31 10:55:55.000000',_binary '\0','Supplier 8','890-123-4567'),(9,'Address 9','COMPANY_NAMES8','2024-07-31 10:55:55.000000',_binary '\0','Supplier 9','901-234-5678'),(10,'Address 10','COMPANY_NAMES9','2024-07-31 10:55:55.000000',_binary '\0','Supplier 10','012-345-6789'),(11,'noor','COMPANY_NAMES7','2024-07-31 11:48:46.292000',_binary '','noor','noor');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdDate` datetime(6) NOT NULL,
  `expired` bit(1) NOT NULL,
  `revoked` bit(1) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `tokenType` enum('BEARER') DEFAULT NULL,
  `userId` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK3wi2t4g8oiplxjflw3o2lkv2y` (`token`),
  KEY `FKed879fkhgwgjlc8vsaig5fere` (`userId`),
  CONSTRAINT `FKed879fkhgwgjlc8vsaig5fere` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,'2024-08-07 11:57:15.509000',_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtb2hhbW1hZEBnbWFpbC5jb20iLCJpYXQiOjE3MjMwMjEwMzUsImV4cCI6MTcyMzEwNzQzNX0.mcrnLjAEj6CF9tcWjeYJQMl5YVtXkvuhVuFd1qaJfug','BEARER',1),(2,'2024-08-07 13:25:26.392000',_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXRpZW50NTNAZ21haWwuY29tIiwiaWF0IjoxNzIzMDI2MzI2LCJleHAiOjE3MjMxMTI3MjZ9._gG5v1BhqCM-5i7uKfHXvk4R3g1GGCB78hSJaGNZcEI','BEARER',133),(3,'2024-08-07 13:26:08.225000',_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXRpZW50NTNAZ21haWwuY29tIiwiaWF0IjoxNzIzMDI2MzY4LCJleHAiOjE3MjMxMTI3Njh9.jwPyFeFXbDvHB1BZ-KcOXfyAyEvYWibRMieY9CBfXRI','BEARER',133);
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token_seq`
--

DROP TABLE IF EXISTS `token_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token_seq`
--

LOCK TABLES `token_seq` WRITE;
/*!40000 ALTER TABLE `token_seq` DISABLE KEYS */;
INSERT INTO `token_seq` VALUES (1),(101);
/*!40000 ALTER TABLE `token_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatmens_deleted`
--

DROP TABLE IF EXISTS `treatmens_deleted`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treatmens_deleted` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `diseaseDescription` varchar(255) NOT NULL,
  `note` text,
  `treatmentDate` datetime(6) NOT NULL,
  `treatmentDeletedId` bigint NOT NULL,
  `doctorId` bigint NOT NULL,
  `createdDate` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmgbdyc847a1uwlnwodwqytxlb` (`doctorId`),
  CONSTRAINT `FKmgbdyc847a1uwlnwodwqytxlb` FOREIGN KEY (`doctorId`) REFERENCES `doctors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatmens_deleted`
--

LOCK TABLES `treatmens_deleted` WRITE;
/*!40000 ALTER TABLE `treatmens_deleted` DISABLE KEYS */;
/*!40000 ALTER TABLE `treatmens_deleted` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatments`
--

DROP TABLE IF EXISTS `treatments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treatments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `diseaseDescription` varchar(255) NOT NULL,
  `note` text,
  `treatmentDate` datetime(6) NOT NULL,
  `doctorId` bigint NOT NULL,
  `patientId` bigint NOT NULL,
  `createdDate` datetime(6) NOT NULL,
  `price` double NOT NULL,
  `notificationSent` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnv6j25drj2va9qua5uf5fxcfs` (`doctorId`),
  KEY `FKe8facmc8tfqvnndtbprjhvpdh` (`patientId`),
  CONSTRAINT `FKe8facmc8tfqvnndtbprjhvpdh` FOREIGN KEY (`patientId`) REFERENCES `patients` (`id`),
  CONSTRAINT `FKnv6j25drj2va9qua5uf5fxcfs` FOREIGN KEY (`doctorId`) REFERENCES `doctors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatments`
--

LOCK TABLES `treatments` WRITE;
/*!40000 ALTER TABLE `treatments` DISABLE KEYS */;
INSERT INTO `treatments` VALUES (1,'has an issue with his stomec','any thing','2024-07-25 11:07:00.586000',1,1,'2024-07-25 11:07:00.586000',20,_binary '\0'),(2,'has an issue with his stomec2','any thing','2024-07-25 11:07:25.809000',1,1,'2024-08-05 02:39:03.000000',20,_binary '\0'),(3,'Treatment 3','any thing','2024-07-25 11:07:41.602000',1,1,'2024-08-05 00:39:10.000000',29,_binary '\0'),(4,'Treatment 1','any thing','2024-07-25 11:07:48.473000',1,2,'2024-08-05 00:39:13.000000',30,_binary '\0'),(5,'Treatment 2','any thing','2024-07-25 11:07:51.894000',1,2,'2024-08-05 00:39:15.000000',22,_binary '\0'),(6,'Treatment 3','any thing','2024-07-25 11:07:55.906000',1,2,'2024-08-05 00:39:16.000000',22,_binary '\0'),(7,'Treatment 4','any thing','2024-07-25 11:08:00.866000',1,2,'2024-08-05 00:39:17.000000',22,_binary '\0'),(8,'Disease A','Note A','2023-01-10 23:00:00.000000',1,53,'2024-08-05 00:39:19.000000',22,_binary '\0'),(9,'Disease B','Note B','2023-01-11 22:00:00.000000',2,54,'2024-08-05 00:39:20.000000',22,_binary '\0'),(10,'Disease C','Note C','2023-01-12 21:00:00.000000',3,55,'2024-08-05 00:39:21.000000',22,_binary '\0'),(11,'Disease D','Note D','2023-01-13 20:00:00.000000',4,56,'2024-08-05 00:39:22.000000',22,_binary '\0'),(12,'Disease E','Note E','2023-01-14 19:00:00.000000',5,57,'2024-08-05 00:39:24.000000',22,_binary '\0'),(13,'Disease F','Note F','2023-01-15 18:00:00.000000',6,58,'2024-08-05 00:39:25.000000',22,_binary '\0'),(14,'Disease G','Note G','2023-01-16 17:00:00.000000',7,59,'2024-08-05 00:39:26.000000',22,_binary '\0'),(15,'Disease H','Note H','2023-01-17 16:00:00.000000',8,60,'2024-08-05 00:39:27.000000',22,_binary '\0'),(16,'Disease I','Note I','2023-01-18 15:00:00.000000',9,61,'2024-08-05 00:39:30.000000',22,_binary '\0'),(17,'Disease J','Note J','2023-01-19 14:00:00.000000',10,62,'2024-08-05 00:39:28.000000',22,_binary '\0'),(18,'Disease K','Note K','2023-01-20 13:00:00.000000',11,63,'2024-08-05 00:39:31.000000',22,_binary '\0'),(19,'Disease L','Note L','2023-01-21 12:00:00.000000',12,64,'2024-08-05 00:39:33.000000',22,_binary '\0'),(20,'Disease M','Note M','2023-01-22 11:00:00.000000',13,65,'2024-08-05 00:39:32.000000',22,_binary '\0'),(21,'Disease N','Note N','2023-01-23 10:00:00.000000',14,66,'2024-08-05 00:39:35.000000',22,_binary '\0'),(22,'Disease O','Note O','2023-01-24 09:00:00.000000',15,67,'2024-08-05 00:39:36.000000',22,_binary '\0'),(23,'Disease P','Note P','2023-01-25 08:00:00.000000',16,68,'2024-08-05 00:39:37.000000',22,_binary '\0'),(24,'Disease Q','Note Q','2023-01-26 07:00:00.000000',17,69,'2024-08-05 00:39:39.000000',22,_binary '\0'),(25,'Disease R','Note R','2023-01-27 06:00:00.000000',18,70,'2024-08-05 00:39:41.000000',22,_binary '\0'),(26,'Disease S','Note S','2023-01-28 05:00:00.000000',19,71,'2024-08-05 00:39:47.000000',22,_binary '\0'),(27,'Disease T','Note T','2023-01-29 04:00:00.000000',20,72,'2024-08-05 00:39:48.000000',22,_binary '\0'),(28,'Disease U','Note U','2023-01-30 03:00:00.000000',21,73,'2024-08-05 00:39:49.000000',22,_binary '\0'),(29,'Disease V','Note V','2023-01-31 02:00:00.000000',22,74,'2024-08-05 00:39:50.000000',22,_binary '\0'),(30,'Disease W','Note W','2023-02-01 01:00:00.000000',23,75,'2024-08-05 00:39:52.000000',22,_binary '\0'),(31,'Disease X','Note X','2023-02-02 00:00:00.000000',24,76,'2024-08-05 00:39:53.000000',22,_binary '\0'),(32,'Disease Y','Note Y','2023-02-03 23:00:00.000000',25,77,'2024-08-05 00:39:54.000000',22,_binary '\0'),(33,'Disease Z','Note Z','2023-02-04 22:00:00.000000',26,78,'2024-08-05 00:39:56.000000',22,_binary '\0'),(34,'Disease AA','Note AA','2023-02-05 21:00:00.000000',27,79,'2024-08-05 00:39:57.000000',22,_binary '\0'),(35,'Disease BB','Note BB','2023-02-06 20:00:00.000000',28,80,'2024-08-05 00:39:58.000000',22,_binary '\0'),(36,'Disease CC','Note CC','2023-02-07 19:00:00.000000',29,81,'2024-08-05 00:40:02.000000',22,_binary '\0'),(37,'Disease DD','Note DD','2023-02-08 18:00:00.000000',30,82,'2024-08-05 00:40:03.000000',22,_binary '\0'),(38,'Disease EE','Note EE','2023-02-09 17:00:00.000000',31,83,'2024-08-05 00:40:04.000000',22,_binary '\0'),(39,'Disease FF','Note FF','2023-02-10 16:00:00.000000',32,84,'2024-08-05 00:40:04.000000',22,_binary '\0'),(40,'Disease GG','Note GG','2023-02-11 15:00:00.000000',33,85,'2024-08-05 00:40:04.000000',22,_binary '\0'),(41,'Disease HH','Note HH','2023-02-12 14:00:00.000000',34,86,'2024-08-05 00:40:05.000000',22,_binary '\0'),(42,'Disease II','Note II','2023-02-13 13:00:00.000000',35,87,'2024-08-05 00:40:05.000000',22,_binary '\0'),(43,'Disease JJ','Note JJ','2023-02-14 12:00:00.000000',36,88,'2024-08-05 00:40:05.000000',22,_binary '\0'),(44,'Disease KK','Note KK','2023-02-15 11:00:00.000000',37,89,'2024-08-05 00:40:06.000000',22,_binary '\0'),(45,'Disease LL','Note LL','2023-02-16 10:00:00.000000',1,90,'2024-08-05 00:40:06.000000',22,_binary '\0'),(46,'Disease MM','Note MM','2023-02-17 09:00:00.000000',2,91,'2024-08-05 00:40:06.000000',22,_binary '\0'),(47,'Disease NN','Note NN','2023-02-18 08:00:00.000000',3,92,'2024-08-05 00:40:06.000000',22,_binary '\0'),(48,'Disease OO','Note OO','2023-02-19 07:00:00.000000',4,93,'2024-08-05 00:40:07.000000',22,_binary '\0'),(49,'Disease PP','Note PP','2023-02-20 06:00:00.000000',5,94,'2024-08-05 00:40:07.000000',22,_binary '\0'),(50,'Disease QQ','Note QQ','2023-02-21 05:00:00.000000',6,95,'2024-08-05 00:40:07.000000',22,_binary '\0'),(51,'Disease RR','Note RR','2023-02-22 04:00:00.000000',7,96,'2024-08-05 00:40:07.000000',22,_binary '\0'),(52,'Disease SS','Note SS','2023-02-23 03:00:00.000000',8,97,'2024-08-05 00:40:07.000000',22,_binary '\0'),(53,'Disease TT','Note TT','2023-02-24 02:00:00.000000',9,98,'2024-08-05 00:40:07.000000',22,_binary '\0'),(54,'Disease UU','Note UU','2023-02-25 01:00:00.000000',10,99,'2024-08-05 00:40:07.000000',22,_binary '\0'),(55,'Disease VV','Note VV','2023-02-26 00:00:00.000000',11,100,'2024-08-05 00:40:07.000000',22,_binary '\0'),(56,'Disease WW','Note WW','2023-02-27 23:00:00.000000',12,101,'2024-08-05 00:40:07.000000',22,_binary '\0'),(57,'Disease XX','Note XX','2023-02-28 22:00:00.000000',13,102,'2024-08-05 00:40:08.000000',22,_binary '\0'),(58,'Disease YY','Note YY','2023-03-01 21:00:00.000000',14,53,'2024-08-05 00:40:08.000000',22,_binary '\0'),(59,'Disease ZZ','Note ZZ','2023-03-02 20:00:00.000000',15,54,'2024-08-05 00:40:08.000000',22,_binary '\0'),(60,'Disease AAA','Note AAA','2023-03-03 19:00:00.000000',16,55,'2024-08-05 00:40:08.000000',22,_binary '\0'),(61,'Disease BBB','Note BBB','2023-03-04 18:00:00.000000',17,56,'2024-08-05 00:40:08.000000',22,_binary '\0'),(62,'Disease CCC','Note CCC','2023-03-05 17:00:00.000000',18,57,'2024-08-05 00:40:08.000000',22,_binary '\0'),(63,'Disease DDD','Note DDD','2023-03-06 16:00:00.000000',19,58,'2024-08-05 00:40:08.000000',22,_binary '\0'),(64,'Disease EEE','Note EEE','2023-03-07 15:00:00.000000',20,59,'2024-08-05 00:40:08.000000',22,_binary '\0'),(65,'Disease FFF','Note FFF','2023-03-08 14:00:00.000000',21,60,'2024-08-05 00:40:08.000000',22,_binary '\0'),(66,'Disease GGG','Note GGG','2023-03-09 13:00:00.000000',22,61,'2024-08-05 00:40:09.000000',22,_binary '\0'),(67,'Disease HHH','Note HHH','2023-03-10 12:00:00.000000',23,62,'2024-08-05 00:40:09.000000',22,_binary '\0'),(68,'Disease III','Note III','2023-03-11 11:00:00.000000',24,63,'2024-08-05 00:40:09.000000',22,_binary '\0'),(69,'Disease JJJ','Note JJJ','2023-03-12 10:00:00.000000',25,64,'2024-08-05 00:40:09.000000',22,_binary '\0'),(70,'Disease KKK','Note KKK','2023-03-13 09:00:00.000000',26,65,'2024-08-05 00:40:09.000000',22,_binary '\0'),(71,'Disease LLL','Note LLL','2023-03-14 08:00:00.000000',27,66,'2024-08-05 00:40:13.000000',22,_binary '\0'),(72,'Disease MMM','Note MMM','2023-03-15 07:00:00.000000',28,67,'2024-08-05 00:40:09.000000',22,_binary '\0'),(73,'Disease NNN','Note NNN','2023-03-16 06:00:00.000000',29,68,'2024-08-05 00:40:09.000000',22,_binary '\0'),(74,'Disease OOO','Note OOO','2023-03-17 05:00:00.000000',30,69,'2024-08-05 00:40:09.000000',22,_binary '\0'),(75,'Disease PPP','Note PPP','2023-03-18 04:00:00.000000',31,70,'2024-08-05 00:40:09.000000',22,_binary '\0'),(76,'Disease QQQ','Note QQQ','2023-03-19 03:00:00.000000',32,71,'2024-08-05 00:40:10.000000',22,_binary '\0'),(77,'Disease RRR','Note RRR','2023-03-20 02:00:00.000000',33,72,'2024-08-05 00:40:10.000000',22,_binary '\0'),(78,'Disease SSS','Note SSS','2023-03-21 01:00:00.000000',34,73,'2024-08-05 00:40:10.000000',22,_binary '\0'),(79,'Disease TTT','Note TTT','2023-03-22 00:00:00.000000',35,74,'2024-08-05 00:40:10.000000',22,_binary '\0'),(80,'Disease UUU','Note UUU','2023-03-23 23:00:00.000000',36,75,'2024-08-05 00:40:10.000000',22,_binary '\0'),(81,'Disease VVV','Note VVV','2023-03-24 22:00:00.000000',37,76,'2024-08-05 00:40:10.000000',22,_binary '\0'),(82,'Disease WWW','Note WWW','2023-03-25 21:00:00.000000',1,77,'2024-08-05 00:40:10.000000',22,_binary '\0'),(83,'Disease XXX','Note XXX','2023-03-26 20:00:00.000000',2,78,'2024-08-05 00:40:11.000000',22,_binary '\0'),(84,'Disease YYY','Note YYY','2023-03-27 19:00:00.000000',3,79,'2024-08-05 00:40:15.000000',22,_binary '\0'),(85,'Disease ZZZ','Note ZZZ','2023-03-28 18:00:00.000000',4,80,'2024-08-05 00:40:16.000000',22,_binary '\0'),(86,'Disease AAAA','Note AAAA','2023-03-29 17:00:00.000000',5,81,'2024-08-05 00:40:16.000000',22,_binary '\0'),(87,'Disease BBBB','Note BBBB','2023-03-30 16:00:00.000000',6,82,'2024-08-05 00:40:16.000000',22,_binary '\0'),(88,'Disease CCCC','Note CCCC','2023-03-31 15:00:00.000000',7,83,'2024-08-05 00:40:17.000000',22,_binary '\0'),(89,'Disease DDDD','Note DDDD','2023-04-01 14:00:00.000000',8,84,'2024-08-05 00:40:17.000000',22,_binary '\0'),(90,'Disease EEEE','Note EEEE','2023-04-02 13:00:00.000000',9,85,'2024-08-05 00:40:17.000000',22,_binary '\0'),(91,'Disease FFFF','Note FFFF','2023-04-03 12:00:00.000000',10,86,'2024-08-05 00:40:17.000000',22,_binary '\0'),(92,'Disease GGGG','Note GGGG','2023-04-04 11:00:00.000000',11,87,'2024-08-05 00:40:17.000000',22,_binary '\0'),(93,'Disease HHHH','Note HHHH','2023-04-05 10:00:00.000000',12,88,'2024-08-05 00:40:17.000000',22,_binary '\0'),(94,'Disease IIII','Note IIII','2023-04-06 09:00:00.000000',13,89,'2024-08-05 00:40:17.000000',22,_binary '\0'),(95,'Disease JJJJ','Note JJJJ','2023-04-07 08:00:00.000000',14,90,'2024-08-05 00:40:18.000000',23,_binary '\0'),(96,'Disease KKKK','Note KKKK','2023-04-08 07:00:00.000000',15,91,'2024-08-05 00:40:18.000000',33,_binary '\0'),(97,'Disease LLLL','Note LLLL','2023-04-09 06:00:00.000000',16,92,'2024-08-05 00:40:18.000000',33,_binary '\0'),(98,'Disease MMMM','Note MMMM','2023-04-10 05:00:00.000000',17,93,'2024-08-05 00:40:18.000000',33,_binary '\0'),(99,'Disease NNNN','Note NNNN','2023-04-11 04:00:00.000000',18,94,'2024-08-05 00:40:18.000000',333,_binary '\0'),(100,'Disease OOOO','Note OOOO','2023-04-12 03:00:00.000000',19,95,'2024-08-05 00:40:18.000000',33,_binary '\0'),(101,'Disease PPPP','Note PPPP','2023-04-13 02:00:00.000000',20,96,'2024-08-05 00:40:18.000000',33,_binary '\0'),(102,'Disease QQQQ','Note QQQQ','2023-04-14 01:00:00.000000',21,97,'2024-08-05 00:40:18.000000',33,_binary '\0'),(103,'Disease RRRR','Note RRRR','2023-04-15 00:00:00.000000',22,98,'2024-08-05 00:40:18.000000',33,_binary '\0'),(104,'Disease SSSS','Note SSSS','2023-04-16 23:00:00.000000',23,99,'2024-08-05 00:40:18.000000',44,_binary '\0'),(105,'Disease TTTT','Note TTTT','2023-04-17 22:00:00.000000',24,100,'2024-08-05 00:40:18.000000',44,_binary '\0'),(106,'Disease UUUU','Note UUUU','2023-04-18 21:00:00.000000',25,101,'2024-08-05 00:40:19.000000',44,_binary '\0'),(107,'Disease VVVV','Note VVVV','2023-04-19 20:00:00.000000',26,102,'2024-08-05 00:40:19.000000',55,_binary '\0'),(108,'Disease WWWW','Note WWWW','2023-04-20 19:00:00.000000',27,53,'2024-08-05 00:40:19.000000',55,_binary '\0'),(109,'Disease XXXX','Note XXXX','2023-04-21 18:00:00.000000',28,54,'2024-08-05 00:40:19.000000',55,_binary '\0'),(110,'Disease YYYY','Note YYYY','2023-04-22 17:00:00.000000',29,55,'2024-08-05 00:40:19.000000',55,_binary '\0'),(111,'Disease ZZZZ','Note ZZZZ','2023-04-23 16:00:00.000000',30,56,'2024-08-05 00:40:19.000000',33,_binary '\0'),(112,'Disease AAAAA','Note AAAAA','2023-04-24 15:00:00.000000',31,57,'2024-08-05 00:40:19.000000',33,_binary '\0'),(113,'Disease BBBBB','Note BBBBB','2023-04-25 14:00:00.000000',32,58,'2024-08-05 00:40:19.000000',33,_binary '\0'),(114,'Disease CCCCC','Note CCCCC','2023-04-26 13:00:00.000000',33,59,'2024-08-05 00:40:19.000000',33,_binary '\0'),(115,'Disease DDDDD','Note DDDDD','2023-04-27 12:00:00.000000',34,60,'2024-08-05 00:40:19.000000',33,_binary '\0'),(116,'Disease EEEEE','Note EEEEE','2023-04-28 11:00:00.000000',35,61,'2024-08-05 00:40:19.000000',33,_binary '\0'),(117,'Disease FFFFF','Note FFFFF','2023-04-29 10:00:00.000000',36,62,'2024-08-05 00:40:19.000000',33,_binary '\0'),(118,'Disease GGGGG','Note GGGGG','2023-04-30 09:00:00.000000',37,63,'2024-08-05 00:40:19.000000',33,_binary '\0'),(119,'Disease HHHHH','Note HHHHH','2023-05-01 08:00:00.000000',1,64,'2024-08-05 00:40:20.000000',33,_binary '\0'),(120,'Disease IIIII','Note IIIII','2023-05-02 07:00:00.000000',2,65,'2024-08-05 00:40:20.000000',33,_binary '\0'),(121,'Disease JJJJJ','Note JJJJJ','2023-05-03 06:00:00.000000',3,66,'2024-08-05 00:40:20.000000',33,_binary '\0'),(122,'Disease KKKKK','Note KKKKK','2023-05-04 05:00:00.000000',4,67,'2024-08-05 00:40:20.000000',33,_binary '\0'),(123,'Disease LLLLL','Note LLLLL','2023-05-05 04:00:00.000000',5,68,'2024-08-05 00:40:20.000000',33,_binary '\0'),(124,'Disease MMMMM','Note MMMMM','2023-05-06 03:00:00.000000',6,69,'2024-08-05 00:40:20.000000',33,_binary '\0'),(125,'Disease NNNNN','Note NNNNN','2023-05-07 02:00:00.000000',7,70,'2024-08-05 00:40:23.000000',33,_binary '\0'),(126,'Disease OOOOO','Note OOOOO','2023-05-08 01:00:00.000000',8,71,'2024-08-05 00:40:20.000000',33,_binary '\0'),(127,'Disease PPPPP','Note PPPPP','2023-05-09 00:00:00.000000',9,72,'2024-08-05 00:40:20.000000',33,_binary '\0'),(128,'Disease QQQQQ','Note QQQQQ','2023-05-10 23:00:00.000000',10,73,'2024-08-05 00:40:20.000000',33,_binary '\0'),(129,'Disease RRRRR','Note RRRRR','2023-05-11 22:00:00.000000',11,74,'2024-08-05 00:40:20.000000',33,_binary '\0'),(130,'Disease SSSSS','Note SSSSS','2023-05-12 21:00:00.000000',12,75,'2024-08-05 00:40:20.000000',11,_binary '\0'),(131,'Disease TTTTT','Note TTTTT','2023-05-13 20:00:00.000000',13,76,'2024-08-05 00:40:20.000000',11,_binary '\0'),(132,'Disease UUUUU','Note UUUUU','2023-05-14 19:00:00.000000',14,77,'2024-08-05 00:40:21.000000',11,_binary '\0'),(133,'Disease VVVVV','Note VVVVV','2023-05-15 18:00:00.000000',15,78,'2024-08-05 00:40:21.000000',11,_binary '\0'),(134,'Disease WWWWW','Note WWWWW','2023-05-16 17:00:00.000000',16,79,'2024-08-05 00:40:21.000000',11,_binary '\0'),(135,'Disease XXXXX','Note XXXXX','2023-05-17 16:00:00.000000',17,80,'2024-08-05 00:40:21.000000',11,_binary '\0'),(136,'Disease YYYYY','Note YYYYY','2023-05-18 15:00:00.000000',18,81,'2024-08-05 00:40:21.000000',11,_binary '\0'),(137,'Disease ZZZZZ','Note ZZZZZ','2023-05-19 14:00:00.000000',19,82,'2024-08-05 00:40:21.000000',66,_binary '\0'),(138,'Disease AAAAAA','Note AAAAAA','2023-05-20 13:00:00.000000',20,83,'2024-08-05 00:40:21.000000',66,_binary '\0'),(139,'Disease BBBBBB','Note BBBBBB','2023-05-21 12:00:00.000000',21,84,'2024-08-05 00:40:21.000000',66,_binary '\0'),(140,'Disease CCCCCC','Note CCCCCC','2023-05-22 11:00:00.000000',22,85,'2024-08-05 00:40:21.000000',66,_binary '\0'),(141,'Disease DDDDDD','Note DDDDDD','2023-05-23 10:00:00.000000',23,86,'2024-08-05 00:40:21.000000',66,_binary '\0'),(142,'Disease EEEEEE','Note EEEEEE','2023-05-24 09:00:00.000000',24,87,'2024-08-05 00:40:21.000000',66,_binary '\0'),(143,'Disease FFFFFF','Note FFFFFF','2023-05-25 08:00:00.000000',25,88,'2024-08-05 00:40:21.000000',66,_binary '\0'),(144,'Disease GGGGGG','Note GGGGGG','2023-05-26 07:00:00.000000',26,89,'2024-08-05 00:40:26.000000',66,_binary '\0'),(145,'Disease HHHHHH','Note HHHHHH','2023-05-27 06:00:00.000000',27,90,'2024-08-05 00:40:26.000000',66,_binary '\0'),(146,'Disease IIIIII','Note IIIIII','2023-05-28 05:00:00.000000',28,91,'2024-08-05 00:40:26.000000',66,_binary '\0'),(147,'Disease JJJJJJ','Note JJJJJJ','2023-05-29 04:00:00.000000',29,92,'2024-08-05 00:40:26.000000',66,_binary '\0'),(148,'Disease KKKKKK','Note KKKKKK','2023-05-30 03:00:00.000000',30,93,'2024-08-05 00:40:27.000000',66,_binary '\0'),(149,'Disease LLLLLL','Note LLLLLL','2023-05-31 02:00:00.000000',31,94,'2024-08-05 00:40:27.000000',66,_binary '\0'),(150,'Disease MMMMMM','Note MMMMMM','2023-06-01 01:00:00.000000',32,95,'2024-08-05 00:40:27.000000',66,_binary '\0'),(151,'Disease NNNNNN','Note NNNNNN','2023-06-02 00:00:00.000000',33,96,'2024-08-05 00:40:27.000000',77,_binary '\0'),(152,'Disease OOOOOO','Note OOOOOO','2023-06-03 23:00:00.000000',34,97,'2024-08-05 00:40:27.000000',77,_binary '\0'),(153,'Disease PPPPPP','Note PPPPPP','2023-06-04 22:00:00.000000',35,98,'2024-08-05 00:40:27.000000',77,_binary '\0'),(154,'Disease QQQQQQ','Note QQQQQQ','2023-06-05 21:00:00.000000',36,99,'2024-08-05 00:40:27.000000',77,_binary '\0'),(155,'Disease RRRRRR','Note RRRRRR','2023-06-06 20:00:00.000000',37,100,'2024-08-05 00:40:27.000000',77,_binary '\0'),(156,'Disease SSSSSS','Note SSSSSS','2023-06-07 19:00:00.000000',1,101,'2024-08-05 00:40:27.000000',67,_binary '\0'),(157,'Disease TTTTTT','Note TTTTTT','2023-06-08 18:00:00.000000',2,102,'2024-08-05 00:40:27.000000',67,_binary '\0'),(158,'Disease UUUUUU','Note UUUUUU','2023-06-09 17:00:00.000000',3,53,'2024-08-05 00:40:27.000000',67,_binary '\0'),(159,'Disease VVVVVV','Note VVVVVV','2023-06-10 16:00:00.000000',4,54,'2024-08-05 00:40:27.000000',67,_binary '\0'),(160,'Disease WWWWWW','Note WWWWWW','2023-06-11 15:00:00.000000',5,55,'2024-08-05 00:40:28.000000',34,_binary '\0'),(161,'Disease XXXXXX','Note XXXXXX','2023-06-12 14:00:00.000000',6,56,'2024-08-05 00:40:28.000000',34,_binary '\0'),(162,'Disease YYYYYY','Note YYYYYY','2023-06-13 13:00:00.000000',7,57,'2024-08-05 00:40:28.000000',34,_binary '\0'),(163,'Disease ZZZZZZ','Note ZZZZZZ','2023-06-14 12:00:00.000000',8,58,'2024-08-05 00:40:28.000000',34,_binary '\0'),(164,'Disease AAAAAAA','Note AAAAAAA','2023-06-15 11:00:00.000000',9,59,'2024-08-05 00:40:28.000000',23,_binary '\0'),(165,'Disease BBBBBBB','Note BBBBBBB','2023-06-16 10:00:00.000000',10,60,'2024-08-05 00:40:28.000000',23,_binary '\0'),(166,'Disease CCCCCCC','Note CCCCCCC','2023-06-17 09:00:00.000000',11,61,'2024-08-05 00:40:28.000000',23,_binary '\0'),(167,'Disease DDDDDDD','Note DDDDDDD','2023-06-18 08:00:00.000000',12,62,'2024-08-05 00:40:28.000000',23,_binary '\0'),(168,'Disease EEEEEEE','Note EEEEEEE','2023-06-19 07:00:00.000000',13,63,'2024-08-05 00:40:28.000000',23,_binary '\0'),(169,'Disease FFFFFFF','Note FFFFFFF','2023-06-20 06:00:00.000000',14,64,'2024-08-05 00:40:28.000000',23,_binary '\0'),(170,'Disease GGGGGGG','Note GGGGGGG','2023-06-21 05:00:00.000000',15,65,'2024-08-05 00:40:28.000000',12,_binary '\0'),(171,'Disease HHHHHHH','Note HHHHHHH','2023-06-22 04:00:00.000000',16,66,'2024-08-05 00:40:28.000000',12,_binary '\0'),(172,'Disease IIIIIII','Note IIIIIII','2023-06-23 03:00:00.000000',17,67,'2024-08-05 00:40:28.000000',12,_binary '\0'),(173,'Disease JJJJJJJ','Note JJJJJJJ','2023-06-24 02:00:00.000000',18,68,'2024-08-05 00:40:29.000000',12,_binary '\0'),(174,'Disease KKKKKKK','Note KKKKKKK','2023-06-25 01:00:00.000000',19,69,'2024-08-05 00:40:29.000000',12,_binary '\0'),(175,'Disease LLLLLLL','Note LLLLLLL','2023-06-26 00:00:00.000000',20,70,'2024-08-05 00:40:29.000000',12,_binary '\0'),(176,'Disease MMMMMMM','Note MMMMMMM','2023-06-27 23:00:00.000000',21,71,'2024-08-05 00:40:29.000000',12,_binary '\0'),(177,'Disease NNNNNNN','Note NNNNNNN','2023-06-28 22:00:00.000000',22,72,'2024-08-05 00:40:29.000000',12,_binary '\0'),(178,'Disease OOOOOOO','Note OOOOOOO','2023-06-29 21:00:00.000000',23,73,'2024-08-05 00:40:29.000000',12,_binary '\0'),(179,'Disease PPPPPPP','Note PPPPPPP','2023-06-30 20:00:00.000000',24,74,'2024-08-05 00:40:29.000000',43,_binary '\0'),(180,'Disease QQQQQQQ','Note QQQQQQQ','2023-07-01 19:00:00.000000',25,75,'2024-08-05 00:40:29.000000',43,_binary '\0'),(181,'Disease RRRRRRR','Note RRRRRRR','2023-07-02 18:00:00.000000',26,76,'2024-08-05 00:40:29.000000',43,_binary '\0'),(182,'Disease SSSSSSS','Note SSSSSSS','2023-07-03 17:00:00.000000',27,77,'2024-08-05 00:40:29.000000',43,_binary '\0'),(183,'Disease TTTTTTT','Note TTTTTTT','2023-07-04 16:00:00.000000',28,78,'2024-08-05 00:40:29.000000',43,_binary '\0'),(184,'Disease UUUUUUU','Note UUUUUUU','2023-07-05 15:00:00.000000',29,79,'2024-08-05 00:40:29.000000',54,_binary '\0'),(185,'Disease VVVVVVV','Note VVVVVVV','2023-07-06 14:00:00.000000',30,80,'2024-08-05 00:40:31.000000',54,_binary '\0'),(186,'Disease WWWWWWW','Note WWWWWWW','2023-07-07 13:00:00.000000',31,81,'2024-08-05 00:40:31.000000',54,_binary '\0'),(187,'Disease XXXXXXX','Note XXXXXXX','2023-07-08 12:00:00.000000',32,82,'2024-08-05 00:40:31.000000',54,_binary '\0'),(188,'Disease YYYYYYY','Note YYYYYYY','2023-07-09 11:00:00.000000',33,83,'2024-08-05 00:40:31.000000',45,_binary '\0'),(189,'Disease ZZZZZZZ','Note ZZZZZZZ','2023-07-10 10:00:00.000000',34,84,'2024-08-05 00:40:31.000000',54,_binary '\0'),(190,'Disease AAAAAAAA','Note AAAAAAAA','2023-07-11 09:00:00.000000',35,85,'2024-08-05 00:40:31.000000',45,_binary '\0'),(191,'Disease BBBBBBBB','Note BBBBBBBB','2023-07-12 08:00:00.000000',36,86,'2024-08-05 00:40:31.000000',35,_binary '\0'),(192,'Disease CCCCCCCC','Note CCCCCCCC','2023-07-13 07:00:00.000000',37,87,'2024-08-05 00:40:31.000000',35,_binary '\0'),(193,'Disease DDDDDDDD','Note DDDDDDDD','2023-07-14 06:00:00.000000',1,88,'2024-08-05 00:40:31.000000',35,_binary '\0'),(194,'Disease EEEEEEEE','Note EEEEEEEE','2023-07-15 05:00:00.000000',2,89,'2024-08-05 00:40:31.000000',35,_binary '\0'),(195,'Disease FFFFFFFF','Note FFFFFFFF','2023-07-16 04:00:00.000000',3,90,'2024-08-05 00:40:31.000000',35,_binary '\0'),(196,'Disease GGGGGGGG','Note GGGGGGGG','2023-07-17 03:00:00.000000',4,91,'2024-08-05 00:40:31.000000',35,_binary '\0'),(197,'Disease HHHHHHHH','Note HHHHHHHH','2023-07-18 02:00:00.000000',5,92,'2024-08-05 00:40:31.000000',35,_binary '\0'),(198,'Disease IIIIIII','Note IIIIIII','2023-07-19 01:00:00.000000',6,93,'2024-08-05 00:40:32.000000',35,_binary '\0'),(199,'Disease JJJJJJJJ','Note JJJJJJJJ','2023-07-20 00:00:00.000000',7,94,'2024-08-05 00:40:32.000000',35,_binary '\0'),(200,'Disease KKKKKKKK','Note KKKKKKKK','2023-07-21 23:00:00.000000',8,95,'2024-08-05 00:40:32.000000',21,_binary '\0'),(201,'Disease LLLLLLLL','Note LLLLLLLL','2023-07-22 22:00:00.000000',9,96,'2024-08-05 00:40:32.000000',21,_binary '\0'),(202,'Disease MMMMMMMM','Note MMMMMMMM','2023-07-23 21:00:00.000000',10,97,'2024-08-05 00:40:32.000000',21,_binary '\0'),(203,'Disease NNNNNNNN','Note NNNNNNNN','2023-07-24 20:00:00.000000',11,98,'2024-08-05 00:40:32.000000',20,_binary '\0'),(204,'Disease OOOOOOOO','Note OOOOOOOO','2023-07-25 19:00:00.000000',12,99,'2024-08-05 00:40:32.000000',20,_binary '\0'),(205,'Disease PPPPPPPP','Note PPPPPPPP','2023-07-26 18:00:00.000000',13,100,'2024-08-05 00:40:32.000000',56,_binary '\0'),(206,'Disease QQQQQQQQ','Note QQQQQQQQ','2023-07-27 17:00:00.000000',14,101,'2024-08-05 00:40:32.000000',56,_binary '\0'),(207,'Disease RRRRRRRR','Note RRRRRRRR','2023-07-28 16:00:00.000000',15,102,'2024-08-05 00:40:32.000000',56,_binary '\0'),(208,'disses diseaseDescription','test note','2024-08-06 10:32:11.707000',38,102,'2024-08-06 10:32:11.707000',40,_binary '\0'),(209,'disses diseaseDescription','test note','2024-08-06 10:42:20.456000',38,101,'2024-08-06 10:42:20.456000',40,_binary '\0'),(210,'disses diseaseDescription','test note','2024-08-07 10:42:24.791000',38,100,'2024-08-06 10:42:24.791000',40,_binary ''),(211,'disssssss','noteeeeeeeeeee','2024-08-07 03:00:00.000000',38,102,'2024-08-07 16:18:45.804000',40,_binary ''),(212,'disssssss','noteeeeeeeeeee','2024-08-07 03:00:00.000000',38,102,'2024-08-07 16:19:59.581000',50,_binary ''),(213,'disssssss22','noteeeeeeeeeee222','2024-08-07 03:00:00.000000',38,99,'2024-08-07 16:51:46.665000',150,_binary '');
/*!40000 ALTER TABLE `treatments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatments_deleted`
--

DROP TABLE IF EXISTS `treatments_deleted`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treatments_deleted` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdDate` datetime(6) NOT NULL,
  `diseaseDescription` varchar(255) NOT NULL,
  `note` text,
  `treatmentDate` datetime(6) NOT NULL,
  `treatmentDeletedId` bigint NOT NULL,
  `doctorId` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbb1t4ug6jex318m0px3uegwbf` (`doctorId`),
  CONSTRAINT `FKbb1t4ug6jex318m0px3uegwbf` FOREIGN KEY (`doctorId`) REFERENCES `doctors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatments_deleted`
--

LOCK TABLES `treatments_deleted` WRITE;
/*!40000 ALTER TABLE `treatments_deleted` DISABLE KEYS */;
/*!40000 ALTER TABLE `treatments_deleted` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `createdDate` datetime(6) NOT NULL,
  `dateOfBirth` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `role` enum('ADMIN','DOCTOR','PATIENT','SECRETARY','WAREHOUSE_EMPLOYEE') NOT NULL,
  `salary` json DEFAULT NULL,
  `isDeleted` bit(1) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Ramallah','2024-07-25 10:36:56.996000','2002-07-12 03:00:00.000000','mohammad@gmail.com','mohammad','AbuThaher','$2a$10$4.WCeHMazylaEDzCRFS9wOKzX3mMC8vyck2zwfaqGzfDvmnzDRPz2','0599782941','ADMIN','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0','C:\\Users\\AbuThaher\\Desktop\\Traning Project\\ProjectTraninng\\src\\main\\resources\\Images\\test.jpg'),(2,'Ramallah','2024-07-25 10:56:23.453000','2002-07-12 03:00:00.000000','noor@gmail.com','noor','AbuThaher','$2a$10$eGX7NKr7KKbHNkM7QfJFX.X.zJXVBHLC5ZTcDQdEU6ztiKZMWqauK','0599782941','SECRETARY','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(3,'Ramallah','2024-07-25 10:56:47.186000','2002-07-12 03:00:00.000000','ahmad@gmail.com','ahmad','AbuThaher','$2a$10$ogoQOCOzW6iEbwmULLHRYe2gI68kzTsB5VpoGW8pL72xhTiHVRVxy','0599782941','WAREHOUSE_EMPLOYEE','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(4,'Ramallah','2024-07-25 10:59:37.887000','2002-07-12 03:00:00.000000','yousef@gmail.com','yousef','AbuThaher','$2a$10$2e3qYTMaYE3x7ZpDI9R1S.ToQ2bMU19lZQ3U0ASo51INAki0z7/92','0599782941','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(5,'jgjjh','2024-07-26 03:32:01.519000','2024-07-26 03:00:00.000000','mohammadabuthaher06@gmail.com','Yousef','Hassouneh','$2a$10$Q8zGbBS5n5ZmMXVSp4ruqOL4WJWSd3Qi8yPDUkDvknhUDtwNxkqlG','+970569482508','ADMIN','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": \"2503\"}',_binary '\0',''),(6,'sfsf','2024-07-26 03:33:58.601000','2024-07-19 03:00:00.000000','botcher@gmail.com','botcher','Ahmad','$2a$10$4ZcGOewq3PwLi63uOT2xZu1nplxqMiS3ymnjY4nfckm1P1RQSR3FO','+970569482508','ADMIN','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": \"250\"}',_binary '\0',''),(7,'Ramallah','2024-07-27 11:39:31.660000','2002-07-12 03:00:00.000000','abed@gmail.com','abed','abed','$2a$10$0fHOSLx.Q0rZWvIcjZYGIOA9o0pvCghiNSyRiwOW58CX1FOICc26W','0599782941','ADMIN','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 2500}',_binary '',''),(41,'test','2024-07-28 10:35:28.313000','2002-07-12 03:00:00.000000','test@gmail.com','test','test','$2a$10$SJc6vRSIvP6Vr80m3z./8Oqb6j6MX4ZSp3G6449ipQlTsLyctc8Bu','0599782941','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(42,'123 Main St','2024-07-28 10:38:30.148000','1985-05-15 03:00:00.000000','doctor8@example.com','John','Doe','$2a$10$mCk7Ki4ueZXIrrlRgD/r9OFRTAroIfDLmXX7LUhwlcQGBiJfWZ1Ea','555-1234','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(43,'456 Elm St','2024-07-28 10:38:55.955000','1975-06-20 03:00:00.000000','doctor9@example.com','Jane','Smith','$2a$10$80/MQb97ydYUHOAM.3JsS.jZ5CqcqiUl9S0WSjvgPU57nPEcqSqOW','555-5678','DOCTOR','{\"hourRate\": 25, \"hourWork\": 150, \"salaryType\": \"HOURLY\"}',_binary '\0',''),(44,'789 Oak St','2024-07-28 10:39:16.042000','1980-07-25 02:00:00.000000','doctor10@example.com','James','Brown','$2a$10$7JY8OgzR7CJ86X8u3AzhquPTS/FiNtIUBkN9UtzXu86S.9RnVZ8jG','555-8765','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(45,'101 Maple St','2024-07-28 10:39:42.398000','1990-08-30 02:00:00.000000','doctor11@example.com','Emily','Davis','$2a$10$V2dHWB5rEhq0BnO7h8l7/eoGSZlSFEAp27gj03IKZv9L.00CNLNXG','555-4321','DOCTOR','{\"hourRate\": 25, \"hourWork\": 150, \"salaryType\": \"HOURLY\"}',_binary '\0',''),(46,'202 Pine St','2024-07-28 10:40:10.826000','1985-09-15 02:00:00.000000','doctor12@example.com','Michael','Wilson','$2a$10$2o1MDnuCOdeoukRoDxRocuGV9RNZGR1KABC9ELuOv4BymSkDdZ9t6','555-9876','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(47,'303 Cedar St','2024-07-28 10:40:34.320000','1978-10-10 02:00:00.000000','doctor13@example.com','Sarah','Miller','$2a$10$aZZ8NzM..wASaYdy7mFYVeuUAQOf44k2oV2sUQFGCsZMX5d79fxBK','555-6543','DOCTOR','{\"hourRate\": 25, \"hourWork\": 150, \"salaryType\": \"HOURLY\"}',_binary '\0',''),(48,'404 Birch St','2024-07-28 10:45:29.804000','1982-11-20 02:00:00.000000','doctor14@example.com','David','Martinez','$2a$10$Ms/thrSps7/ldXQZUdmeduhLlQn6X4YuD4C1g8IBG.pXgfEe/mQgy','555-3210','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(49,'505 Spruce St','2024-07-28 10:45:41.146000','1970-12-25 02:00:00.000000','doctor15@example.com','Laura','Garcia','$2a$10$jzv.kUHkUO4Gi4M1gCVsauyyxbu6QM5xieCVcjz9u0Q.3bo.z.9YS','555-7890','DOCTOR','{\"hourRate\": 25, \"hourWork\": 150, \"salaryType\": \"HOURLY\"}',_binary '\0',''),(50,'606 Willow St','2024-07-28 10:45:51.169000','1983-01-30 02:00:00.000000','doctor16@example.com','Robert','Rodriguez','$2a$10$wdNvO8.EIzIuB.tWhdbCt.SJ.pk.1NZApLrARRqjC4T2IUGT3eyvS','555-4567','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(51,'707 Aspen St','2024-07-28 10:46:07.229000','1992-02-14 02:00:00.000000','doctor17@example.com','Anna','Martinez','$2a$10$rgZ/qfVT2058YVph.C41YuLzCoH5Lkx22C2j82d/SJIIRPmfKMfCa','555-1235','DOCTOR','{\"hourRate\": 25, \"hourWork\": 150, \"salaryType\": \"HOURLY\"}',_binary '\0',''),(52,'808 Fir St','2024-07-28 10:46:20.756000','1975-03-22 02:00:00.000000','doctor18@example.com','George','Harris','$2a$10$d2R35quoo6Lq5sAD/lEKS.LGUGFcmiLKcfejcliizROwREf3ov/lu','555-6789','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(53,'909 Redwood St','2024-07-28 10:46:31.574000','1980-04-25 02:00:00.000000','doctor19@example.com','Sophia','Clark','$2a$10$Q/2ygBzCneYXtumE2tHTrOHXmdWmC.VxEXKZTFdmQoZ/5/cndfZtm','555-4329','DOCTOR','{\"hourRate\": 25, \"hourWork\": 150, \"salaryType\": \"HOURLY\"}',_binary '\0',''),(54,'1000 Pine St','2024-07-28 10:46:42.599000','1990-05-15 03:00:00.000000','doctor20@example.com','Oliver','Lewis','$2a$10$F3wT1lduKgst.Cnkjun7L.m8Ydsr1EfStkGzlStALJ1dnrFuUrNZ6','555-5673','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(55,'1100 Birch St','2024-07-28 10:46:53.290000','1970-06-20 02:00:00.000000','doctor21@example.com','Emma','Robinson','$2a$10$xwsb3F67PtP.aa/t.5Kxg.7Hw1ftNuAXpJl/xdfOxoqU8rRbraciu','555-8761','DOCTOR','{\"hourRate\": 25, \"hourWork\": 150, \"salaryType\": \"HOURLY\"}',_binary '\0',''),(56,'1200 Maple St','2024-07-28 10:47:03.153000','1985-07-06 03:00:00.000000','doctor22@example.com','Ava','Walker','$2a$10$81fisBOT/WMUekfrt60RGOnS1kHYCxdtIjGFrb71v4roJ8ZzBYyLu','555-3212','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(57,'1300 Oak St','2024-07-28 10:47:17.983000','1980-08-08 03:00:00.000000','doctor23@example.com','Liam','Young','$2a$10$ohznypxdk0aVJQ1KrGEaG.r3dRUVA1mvOQRFtkugVF.ORSLwZTL56','555-5670','DOCTOR','{\"hourRate\": 25, \"hourWork\": 150, \"salaryType\": \"HOURLY\"}',_binary '\0',''),(58,'1400 Cedar St','2024-07-28 10:47:55.167000','1990-09-09 02:00:00.000000','doctor24@example.com','Mason','Allen','$2a$10$hfqbFLLk5sm6q4KQNt1kTeBHZZwGMvW/oaYj.KGcYMn7CDGgFlIqi','555-8762','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(59,'1500 Birch St','2024-07-28 10:48:07.892000','1985-10-11 02:00:00.000000','doctor25@example.com','Lucas','King','$2a$10$NlyuPZr4uzkeHe5Y2YfxQuXSfA5RdQ2EIKzARyMvFSQbu3/wsloAe','555-3213','DOCTOR','{\"hourRate\": 25, \"hourWork\": 150, \"salaryType\": \"HOURLY\"}',_binary '\0',''),(60,'1600 Pine St','2024-07-28 10:48:17.389000','1978-11-13 02:00:00.000000','doctor26@example.com','Charlotte','Scott','$2a$10$HnQvqVh7W9K0ivhQaQKNfOTQFwkNe9I4b5qogld6wrTBzspoe/Zza','555-8763','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(61,'1700 Elm St','2024-07-28 10:48:31.505000','1982-12-15 02:00:00.000000','doctor27@example.com','Ella','Adams','$2a$10$HwJZ16GlRzO4oBSHlQDE6ediw0fwZibAbh0YunDKjgzqUwBMCo0Di','555-3214','DOCTOR','{\"hourRate\": 25, \"hourWork\": 150, \"salaryType\": \"HOURLY\"}',_binary '\0',''),(62,'1800 Spruce St','2024-07-28 10:48:40.431000','1992-01-17 02:00:00.000000','doctor28@example.com','Henry','Baker','$2a$10$ZOGOfE6CVmuqdbb0W5aj5uZPTa7Uew/xvatWRvAulCm0gfw/bQy5C','555-8764','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(63,'1900 Fir St','2024-07-28 10:48:48.490000','1980-02-18 02:00:00.000000','doctor29@example.com','Sebastian','Rivera','$2a$10$yRQCb5/IdbEG2nr7lDE8uOrybXlC7hzdO.2vTA3vSiU7gqHjlOXZi','555-4325','DOCTOR','{\"hourRate\": 25, \"hourWork\": 150, \"salaryType\": \"HOURLY\"}',_binary '\0',''),(64,'2000 Willow St','2024-07-28 10:48:58.514000','1975-03-04 02:00:00.000000','doctor30@example.com','Jack','Lee','$2a$10$DlvXZyhMDQO7y9imll8G8e3euyy.0HheH80LJvhymVltYyHTZzgfW','555-9872','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(65,'2100 Aspen St','2024-07-28 10:49:08.116000','1985-04-01 02:00:00.000000','doctor31@example.com','Jacob','Evans','$2a$10$nbrts5EPS5JYXkGQDx2MrOzy7XkFmGZSPCmufN4F9i/m5McDYldPS','555-6542','DOCTOR','{\"hourRate\": 25, \"hourWork\": 150, \"salaryType\": \"HOURLY\"}',_binary '\0',''),(66,'2200 Cedar St','2024-07-28 10:49:17.466000','1978-05-15 02:00:00.000000','doctor32@example.com','Thomas','Collins','$2a$10$FmO9uiRoaJhwpgpxePBK1eRHsqBn9sYocJcdtpVnbjjQF8qkC/etW','555-7893','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(67,'2300 Birch St','2024-07-28 10:49:28.148000','1982-06-20 02:00:00.000000','doctor33@example.com','Dylan','Stewart','$2a$10$mSWXPvld0xjPd5ghoza2UOn3zIcVKw.U7X5yuGPMPEnqaa0aG1mgu','555-1236','DOCTOR','{\"hourRate\": 25, \"hourWork\": 150, \"salaryType\": \"HOURLY\"}',_binary '\0',''),(68,'2400 Pine St','2024-07-28 10:49:39.908000','1990-07-25 03:00:00.000000','doctor34@example.com','Grayson','Flores','$2a$10$WBV8j8C/PuJa8PBhxX3akehZ.WUrdHFqsy2/TTvhDrL7yGinWgr4G','555-3215','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(69,'2500 Elm St','2024-07-28 10:49:50.660000','1980-08-30 03:00:00.000000','doctor35@example.com','Nora','Morales','$2a$10$5DhNIqj4EK0tClsLjSHaX.znQTD.6heRwKY1ORYSjiyg5NSvLW8Pa','555-8767','DOCTOR','{\"hourRate\": 25, \"hourWork\": 150, \"salaryType\": \"HOURLY\"}',_binary '\0',''),(70,'2600 Spruce St','2024-07-28 10:50:00.831000','1992-09-10 02:00:00.000000','doctor36@example.com','Hannah','Nguyen','$2a$10$dUAf/7hXzTNmRRhO75x0fOXx3bqBgYnVBdvW2tZN9AaoCzf3zkr56','555-5675','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(71,'2700 Fir St','2024-07-28 10:50:09.501000','1985-11-05 02:00:00.000000','doctor37@example.com','Layla','Kim','$2a$10$Hcc471Vn5VJEMxta/qKJ4enHYanHc.HDwvDqwv1DXwO/HLn8EF9U.','555-3217','DOCTOR','{\"hourRate\": 25, \"hourWork\": 150, \"salaryType\": \"HOURLY\"}',_binary '\0',''),(72,'2800 Willow St','2024-07-28 10:50:21.013000','1982-12-20 02:00:00.000000','doctor38@example.com','Lillian','Parker','$2a$10$.yXf/8BPfYYnLoOGQoBd1O4sw4lWYGVTqmQbnLBurFG.YqHm3dvTa','555-8768','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(73,'2900 Aspen St','2024-07-28 10:50:30.425000','1978-03-03 02:00:00.000000','doctor39@example.com','Penelope','Young','$2a$10$YwsDTPoajajo0Ltbe4zOUemfprKdiBPCum6RSirN5O3RzFbSXQ1va','555-3218','DOCTOR','{\"hourRate\": 25, \"hourWork\": 150, \"salaryType\": \"HOURLY\"}',_binary '\0',''),(74,'3000 Cedar St','2024-07-28 10:51:09.161000','1980-06-25 02:00:00.000000','doctor40@example.com','Zoey','Perez','$2a$10$Cw1AoD.g4U2N9TMoWxlooOFPtbY2MQp2cSJcDtSszfS6Pdi4/kajW','555-5679','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(75,'3100 Birch St','2024-07-28 10:51:21.429000','1990-07-10 03:00:00.000000','doctor41@example.com','Sofia','Richardson','$2a$10$UB34hG5iwQcQhKR5Nb8sD.whllkC42nUGdXC4rCoSwUr.e3eUPRs2','555-8769','DOCTOR','{\"hourRate\": 25, \"hourWork\": 150, \"salaryType\": \"HOURLY\"}',_binary '\0',''),(76,'3200 Pine St','2024-07-28 10:51:30.147000','1982-08-15 02:00:00.000000','doctor42@example.com','Eleanor','Cox','$2a$10$UDL75gdnr5oC3BlBYw9SGOK7dJXhnuQqJAohJ6WHMOyiH4u8hd3fK','555-9874','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(78,'123 Main St','2024-07-28 15:23:29.830000','1990-01-01 02:00:00.000000','john.doe@example.com','John','Doe','$2a$10$kAuzilwtlbeahVjZD.0W/Onh7N.mUgki.514lYZ1BBjr8Ny43ZpqK','1234567890','SECRETARY','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0','C:\\Users\\AbuThaher\\Desktop\\Traning Project\\ProjectTraninng\\src\\main\\resources\\Images\\test.jpg'),(79,'Ramallah','2024-08-06 10:17:19.249000','2002-07-12 03:00:00.000000','mohalada85@gmail.com','mohammad','Ahmad','$2a$10$.6vU44BU.d.ggILBfxbnvuQPdOBwHrP8AjbWFxCPAwFSHYK7vxgM.','0599782941','DOCTOR','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 1500}',_binary '\0',''),(80,'mohammad','2024-08-06 11:11:20.227000','2002-07-12 03:00:00.000000','ddanarafi@gmail.com','mohammad','AbuThaher','$2a$10$xvKlYzHcguGkNucqULa5t.oHKnU/zo9nJpHgi/hEfHQjXKyCd8lx.','0599782941','WAREHOUSE_EMPLOYEE','{\"salaryType\": \"MONTHLY\", \"salaryAmount\": 2500}',_binary '\0',''),(81,'123 Patient St','2024-07-25 10:43:40.895000','1974-07-25 00:00:00.000000','patient1@example.com','John','Doe','$2a$10$04dGSl6tVTubpKebmC3BfOQwzajDECT3gfiVnwbVyqnZ3QnrS686m','555-1234','PATIENT',NULL,_binary '\0',NULL),(82,'124 Patient St','2024-07-25 10:49:01.629000','1974-07-25 00:00:00.000000','patient2@example.com','Jane','Doe','$2a$10$WGPWZZX9T7mccGpwrKz4YeLfI25T6ojVFazF/uQOF.25dprXgxneK','555-1235','PATIENT',NULL,_binary '\0',NULL),(83,'125 Patient St','2023-01-10 10:00:00.000000','1998-01-10 00:00:00.000000','patient3@example.com','Mike','Smith','$2a$10$rfDvDQjVJ/14V6MSJuDVFOVyu9xFSWFZZmaznj/Ovg/7vs30gPRs2','555-1236','PATIENT',NULL,_binary '\0',NULL),(84,'126 Patient St','2023-02-12 11:00:00.000000','1993-02-12 00:00:00.000000','patient4@example.com','Emma','Johnson','$2a$10$9DsN5m2f53pUorVfeYBKAOkf2iRCWFcOwcRuGcLHY47OxXUmE.Tmm','555-1237','PATIENT',NULL,_binary '\0',NULL),(85,'127 Patient St','2023-03-15 12:00:00.000000','1988-03-15 00:00:00.000000','patient5@example.com','Lucas','Brown','$2a$10$zKzGM14ioIrcnPH5mYJvaeCzfKFnGOdt/eyV3q1O/PwoWW2kuMziK','555-1238','PATIENT',NULL,_binary '\0',NULL),(86,'128 Patient St','2023-04-18 13:00:00.000000','1983-04-18 00:00:00.000000','patient6@example.com','Olivia','Davis','$2a$10$j7FqvN4nh8Rzo.kTQjkZNubqZANZWRCqoj./2RKtjm1Rv.TAeX3kq','555-1239','PATIENT',NULL,_binary '\0',NULL),(87,'129 Patient St','2023-05-20 14:00:00.000000','1978-05-20 00:00:00.000000','patient7@example.com','Aiden','Garcia','$2a$10$u1Ig4OOCxSzjD4HHluEI9e.4uMTobt6BH4k7YKSqJD.0M58Z2k83O','555-1240','PATIENT',NULL,_binary '\0',NULL),(88,'130 Patient St','2023-06-22 15:00:00.000000','1973-06-22 00:00:00.000000','patient8@example.com','Sophia','Martinez','$2a$10$QeCXz3GGU50EkZ0KGe6bHuwRu7criQdhpnMHI1RcUXGBKPjd4rJ0y','555-1241','PATIENT',NULL,_binary '\0',NULL),(89,'131 Patient St','2023-07-25 16:00:00.000000','1968-07-25 00:00:00.000000','patient9@example.com','Liam','Rodriguez','$2a$10$a/CaSMck6roXIgSYqQoNg.aDpqtIxcz6BQ3e2.G82Bv9VTjxgSBUK','555-1242','PATIENT',NULL,_binary '\0',NULL),(90,'132 Patient St','2023-08-28 17:00:00.000000','1963-08-28 00:00:00.000000','patient10@example.com','Isabella','Wilson','$2a$10$pKpKpUTpXt5j.2FHbZ94AuZXa0ybI9fJ63z3fCZzxz7R2OSCA4nEm','555-1243','PATIENT',NULL,_binary '\0',NULL),(91,'133 Patient St','2023-09-30 18:00:00.000000','1958-09-30 00:00:00.000000','patient11@example.com','Mason','Lopez','$2a$10$VZD3QN.9.Q4eHb7/s4O7Lu.Lg1/BP0.X9TTBK8hivjnGStx.vWCG.','555-1244','PATIENT',NULL,_binary '\0',NULL),(92,'134 Patient St','2023-10-12 19:00:00.000000','1953-10-12 00:00:00.000000','patient12@example.com','Emily','Hernandez','$2a$10$.3l5OtcPCHHpznRvWKivmOv9eohkfe4ELlLpGpkO0.R/.WO6uTCRa','555-1245','PATIENT',NULL,_binary '\0',NULL),(93,'135 Patient St','2023-11-14 20:00:00.000000','1948-11-14 00:00:00.000000','patient13@example.com','Logan','Gonzalez','$2a$10$0X0QQmKEFkOdkJUY24dOh..hCU16CiN1DFtTQczfbGg4R1bxYUmVS','555-1246','PATIENT',NULL,_binary '\0',NULL),(94,'136 Patient St','2023-12-16 21:00:00.000000','1943-12-16 00:00:00.000000','patient14@example.com','Avery','Perez','$2a$10$dBQ7bSGc4pORItlK31l.y..Mrayb1/DypG4FAz6//bOLBP9m0KW.C','555-1247','PATIENT',NULL,_binary '\0',NULL),(95,'137 Patient St','2024-01-18 22:00:00.000000','1938-01-18 00:00:00.000000','patient15@example.com','Ethan','Taylor','$2a$10$4CgjqxdxjlaefbFjSUuu9edTc8VXPXeM.XamdDbmeOgX7AdhbG7Gu','555-1248','PATIENT',NULL,_binary '\0',NULL),(96,'138 Patient St','2024-02-20 23:00:00.000000','1933-02-20 00:00:00.000000','patient16@example.com','Charlotte','Anderson','$2a$10$Up.ifeP.b6Sml0U08DYdquzyI1hpgiqaSvZSPwd.pJizKh6dFM.tm','555-1249','PATIENT',NULL,_binary '\0',NULL),(97,'139 Patient St','2024-03-22 23:00:00.000000','1928-03-22 00:00:00.000000','patient17@example.com','Alexander','Thomas','$2a$10$buq2A2z9/d0w6jgGHxZ1GuM1XsyKtY7581g7GKRpzzzagOe375z1C','555-1250','PATIENT',NULL,_binary '\0',NULL),(98,'140 Patient St','2024-04-24 00:00:00.000000','1923-04-24 00:00:00.000000','patient18@example.com','Amelia','Jackson','$2a$10$a8fprC7awAUxbOBd4UAzIOj/5DH9IIVbFONM.oBZqtGkc7SnFtm.K','555-1251','PATIENT',NULL,_binary '\0',NULL),(99,'141 Patient St','2024-05-26 01:00:00.000000','1918-05-26 00:00:00.000000','patient19@example.com','James','White','$2a$10$oqlHFn/my/V9vlABwdETK.AcVHUhhvALXjqzDWntxtaA4zYsxLpXy','555-1252','PATIENT',NULL,_binary '\0',NULL),(100,'142 Patient St','2024-06-28 02:00:00.000000','1913-06-28 00:00:00.000000','patient20@example.com','Mia','Harris','$2a$10$RzYY71cAn7BBOHWnDYfKW.Rrks1HzeVSmceHcr87hRg2M2kS5qel.','555-1253','PATIENT',NULL,_binary '\0',NULL),(101,'143 Patient St','2024-07-30 03:00:00.000000','1908-07-30 00:00:00.000000','patient21@example.com','Benjamin','Clark','$2a$10$7fB0S.0CX4XBcJqP5b8mWuqF0nmufQT22wi5JYV8um6qg18xXb13C','555-1254','PATIENT',NULL,_binary '\0',NULL),(102,'144 Patient St','2024-08-01 04:00:00.000000','1903-08-01 00:00:00.000000','patient22@example.com','Harper','Lewis','$2a$10$ZLF2xHGgG41nEWSEkYOIvuhwrCB/2mtTQHWnh8.0JqAvvQ4xLRNnu','555-1255','PATIENT',NULL,_binary '\0',NULL),(103,'145 Patient St','2024-09-02 05:00:00.000000','1898-09-02 00:00:00.000000','patient23@example.com','Sebastian','Young','$2a$10$/0cip7UrWG4fdpmW4j3BOuZM9pOMS4zYbfmW/v6YLhH4P7T4l6JBG','555-1256','PATIENT',NULL,_binary '\0',NULL),(104,'146 Patient St','2024-10-03 06:00:00.000000','1893-10-03 00:00:00.000000','patient24@example.com','Ella','Walker','$2a$10$eK6Lg7BcY0q6.fRgxkAZHe7YOm.QZN3bWVpLrMhglQ.SDDKFSDxN.','555-1257','PATIENT',NULL,_binary '\0',NULL),(105,'147 Patient St','2024-11-04 07:00:00.000000','1888-11-04 00:00:00.000000','patient25@example.com','Jack','Hall','$2a$10$jx2DqdexP/HS52mC7OW7fOou2Yq.xn8LL39pWgm8AAHkxqz0a9YBC','555-1258','PATIENT',NULL,_binary '\0',NULL),(106,'148 Patient St','2024-12-05 08:00:00.000000','1883-12-05 00:00:00.000000','patient26@example.com','Ava','Allen','$2a$10$z7bfoKifBNJSi5k.pvwKoePboQ8pGY0gilhom5FR9W0.KEiujPK1S','555-1259','PATIENT',NULL,_binary '\0',NULL),(107,'149 Patient St','2025-01-06 09:00:00.000000','1878-01-06 00:00:00.000000','patient27@example.com','Henry','Young','$2a$10$lGVHNrWok.VjuVhipn2k1.qaA7qTAQn.gLKjSecnYVKdasGzH8Y/q','555-1260','PATIENT',NULL,_binary '\0',NULL),(108,'150 Patient St','2025-02-07 10:00:00.000000','1873-02-07 00:00:00.000000','patient28@example.com','Abigail','Hernandez','$2a$10$HNmNmpOM0PM2JG9ly147LuQNhRqWV.SGvd/1gt6PvKiEOyoK8q4Cq','555-1261','PATIENT',NULL,_binary '\0',NULL),(109,'151 Patient St','2025-03-08 11:00:00.000000','1868-03-08 00:00:00.000000','patient29@example.com','Jackson','King','$2a$10$szox6ZuvMqZf0Ys5wyxAceKBEVCytjPuyRMq0Xck8RLnW4b7Amkl6','555-1262','PATIENT',NULL,_binary '\0',NULL),(110,'152 Patient St','2025-04-09 12:00:00.000000','1863-04-09 00:00:00.000000','patient30@example.com','Sofia','Wright','$2a$10$yDq8XzMWXecMzBfqNtHJh.czU.1gMcvjSLkNpz8hjkGtZ1krSAooq','555-1263','PATIENT',NULL,_binary '\0',NULL),(111,'153 Patient St','2025-05-10 13:00:00.000000','1858-05-10 00:00:00.000000','patient31@example.com','Matthew','Hill','$2a$10$AHXdXqqap0PI77aP.y0bje8UgQfkeJQykZNI7QafsMt2f4By1qWDK','555-1264','PATIENT',NULL,_binary '\0',NULL),(112,'154 Patient St','2025-06-11 14:00:00.000000','1853-06-11 00:00:00.000000','patient32@example.com','Amelia','Scott','$2a$10$bbNWgCWOhrzYey33T7SETOF8ptuxvsCOhxH3didQeqdvm6nhAtm1K','555-1265','PATIENT',NULL,_binary '\0',NULL),(113,'155 Patient St','2025-07-12 15:00:00.000000','1848-07-12 00:00:00.000000','patient33@example.com','Logan','Green','$2a$10$MOZkDsiAQC5e0r15a5/oYOlkds7OtwBUSyHfTc.YXxKySuXk1BsAe','555-1266','PATIENT',NULL,_binary '\0',NULL),(114,'156 Patient St','2025-08-13 16:00:00.000000','1843-08-13 00:00:00.000000','patient34@example.com','Aria','Adams','$2a$10$VoIY9mNARoAT8Qmf1/Q81OqrCC/P/rRIy0GfJC9t6tU5FaeQ6iO2u','555-1267','PATIENT',NULL,_binary '\0',NULL),(115,'157 Patient St','2025-09-14 17:00:00.000000','1838-09-14 00:00:00.000000','patient35@example.com','Oliver','Baker','$2a$10$eLuYvqSmAR.0kvmpGatrXOoGIr7bRQhgXHj5f3le9XYR5O34JMSTe','555-1268','PATIENT',NULL,_binary '\0',NULL),(116,'158 Patient St','2025-10-15 18:00:00.000000','1833-10-15 00:00:00.000000','patient36@example.com','Chloe','Perez','$2a$10$gp0g6tTHbpvw.7pSTSVPHe68epvSqM0jIQqZBpjr/Ocf1hqfikEnG','555-1269','PATIENT',NULL,_binary '\0',NULL),(117,'159 Patient St','2025-11-16 19:00:00.000000','1828-11-16 00:00:00.000000','patient37@example.com','Ethan','Mitchell','$2a$10$NSihdUL2oV81Kxew1gOpnu9oYk5LbtChIwkWTEG71mH4sBJ1qKLVS','555-1270','PATIENT',NULL,_binary '\0',NULL),(118,'160 Patient St','2025-12-17 20:00:00.000000','1823-12-17 00:00:00.000000','patient38@example.com','Avery','Campbell','$2a$10$EhQJlFhVZL4he0aYsoLfJOrslQkmsaEau1vI6cqazPg9LojuWz1Ga','555-1271','PATIENT',NULL,_binary '\0',NULL),(119,'161 Patient St','2026-01-18 21:00:00.000000','1818-01-18 00:00:00.000000','patient39@example.com','Mia','Roberts','$2a$10$.YK6nY8JHzR8gSoaVdvexODFCMET.YUZHSqf9IOufNZplfL8UNyCi','555-1272','PATIENT',NULL,_binary '\0',NULL),(120,'162 Patient St','2026-02-19 22:00:00.000000','1813-02-19 00:00:00.000000','patient40@example.com','Benjamin','Carter','$2a$10$gq8yKyuVSsNQEOf2VZ9aI.mZQaCjXhBqHeSwgEoDXVJuRnJuyTeiK','555-1273','PATIENT',NULL,_binary '\0',NULL),(121,'163 Patient St','2026-03-20 23:00:00.000000','1808-03-20 00:00:00.000000','patient41@example.com','Harper','Phillips','$2a$10$5vIaCvxRjH6XyO2q5VYMB.xUkEULGfmxLe/FzNY.3/UeL7tX3wZgO','555-1274','PATIENT',NULL,_binary '\0',NULL),(122,'164 Patient St','2026-04-21 00:00:00.000000','1803-04-21 00:00:00.000000','patient42@example.com','Sebastian','Evans','$2a$10$zdbrI6MWeTSKOKwebyw5deLZJq8yuqTwQiKqrALOqk55FPpLtD0Xq','555-1275','PATIENT',NULL,_binary '\0',NULL),(123,'165 Patient St','2026-05-22 01:00:00.000000','1798-05-22 00:00:00.000000','patient43@example.com','Ella','Turner','$2a$10$.7aAAqMNdBSid0yWiC/TQelw0qySoQZk0COFLMyMz84siotAL7hRG','555-1276','PATIENT',NULL,_binary '\0',NULL),(124,'166 Patient St','2026-06-23 02:00:00.000000','1793-06-23 00:00:00.000000','patient44@example.com','Jack','Collins','$2a$10$Qw.4P0YcEy3m3CB5X9CkgulLFp7SEWkcm8kTIJiBJ9p0wJWVEgMhm','555-1277','PATIENT',NULL,_binary '\0',NULL),(125,'167 Patient St','2026-07-24 03:00:00.000000','1788-07-24 00:00:00.000000','patient45@example.com','Ava','Edwards','$2a$10$zW8dLRf0a3HrWk0tAUemK.wMZdzOIhsR0fg7lFhVq4iSj6osg.uz6','555-1278','PATIENT',NULL,_binary '\0',NULL),(126,'168 Patient St','2026-08-25 04:00:00.000000','1783-08-25 00:00:00.000000','patient46@example.com','Henry','Reed','$2a$10$ARPNqO6U3Hygrj0T8bZhlusS49teACjNWWmOcz1qImllVFs7TyuT6','555-1279','PATIENT',NULL,_binary '\0',NULL),(127,'169 Patient St','2026-09-26 05:00:00.000000','1778-09-26 00:00:00.000000','patient47@example.com','Abigail','Stewart','$2a$10$fWwfk4xOOS2aOhme.wRn5uQXMjVLelMrgAvEHpxWJndrL8jQXkLpC','555-1280','PATIENT',NULL,_binary '\0',NULL),(128,'170 Patient St','2026-10-27 06:00:00.000000','1773-10-27 00:00:00.000000','patient48@example.com','Jackson','Morris','$2a$10$VU8o/AipkAf.0OIZT8LfYeIDpkIuOaPQOTLfsw4MKruRNAEG5HlSi','555-1281','PATIENT',NULL,_binary '\0',NULL),(129,'171 Patient St','2026-11-28 07:00:00.000000','1768-11-28 00:00:00.000000','patient49@example.com','Sofia','Rogers','$2a$10$mgGXvbfNMg8mpb/3dq1UOevPrSIAy.WK4NXpJEt.7I7L4MI9VZDPW','555-1282','PATIENT',NULL,_binary '\0',NULL),(130,'172 Patient St','2026-12-29 08:00:00.000000','1763-12-29 00:00:00.000000','patient50@example.com','Matthew','Cook','$2a$10$9HRMyx.WqDMQnjB3AjLvGumM/hGeAhNnLd4slkel0GKOUjFQ3i2ze','555-1283','PATIENT',NULL,_binary '\0',NULL),(131,'173 Patient St','2027-01-30 09:00:00.000000','1758-01-30 00:00:00.000000','patient51@example.com','Amelia','Morgan','$2a$10$P8X21RK/E/k8C1MTc8d6b.PzHQIMCafOqU7qEwWbEreX3XHs2csv.','555-1284','PATIENT',NULL,_binary '\0',NULL),(132,'174 Patient St','2024-08-07 13:20:12.000000','2024-08-07 13:20:06.000000','patient52@example.com','Logan','Bell','$2a$10$ynJ1zbh3aQPLqi2p46hT3ujBgdGmX/H2efIPor.IEiYOMfv8TdOoe','555-1285','PATIENT',NULL,_binary '\0',NULL),(133,'Ramallah','2024-08-07 13:25:26.371000','1978-07-12 02:00:00.000000','patient53@gmail.com','bily','botcher','$2a$10$31H/f41rOA69HnmFO42rEeXI3jfe560eyhEvKm579vwxPpyHfLGn.','0599782941','PATIENT',NULL,_binary '\0',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse_store`
--

DROP TABLE IF EXISTS `warehouse_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehouse_store` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdDate` datetime(6) NOT NULL,
  `quantity` int NOT NULL,
  `medicineId` bigint NOT NULL,
  `isDeleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FKo7ynrgs1v3t8ifjvhqlj2tet8` (`medicineId`),
  CONSTRAINT `FKo7ynrgs1v3t8ifjvhqlj2tet8` FOREIGN KEY (`medicineId`) REFERENCES `medicine` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse_store`
--

LOCK TABLES `warehouse_store` WRITE;
/*!40000 ALTER TABLE `warehouse_store` DISABLE KEYS */;
INSERT INTO `warehouse_store` VALUES (1,'2024-07-25 16:40:43.246000',55,1,_binary ''),(71,'2023-02-01 12:00:00.000000',200,2,_binary '\0'),(72,'2023-03-01 12:00:00.000000',150,3,_binary '\0'),(73,'2023-04-01 12:00:00.000000',120,4,_binary '\0'),(74,'2023-05-01 12:00:00.000000',130,5,_binary '\0'),(75,'2023-06-01 12:00:00.000000',90,6,_binary '\0'),(76,'2023-07-01 12:00:00.000000',180,7,_binary '\0'),(77,'2023-08-01 12:00:00.000000',110,8,_binary '\0'),(78,'2023-09-01 12:00:00.000000',170,9,_binary '\0'),(79,'2023-10-01 12:00:00.000000',140,10,_binary '\0'),(80,'2023-11-01 12:00:00.000000',160,11,_binary '\0'),(81,'2023-12-01 12:00:00.000000',190,12,_binary '\0'),(82,'2024-01-01 12:00:00.000000',210,13,_binary '\0'),(83,'2024-02-01 12:00:00.000000',100,14,_binary '\0'),(84,'2024-03-01 12:00:00.000000',200,15,_binary '\0'),(85,'2024-04-01 12:00:00.000000',150,16,_binary '\0'),(86,'2024-05-01 12:00:00.000000',120,17,_binary '\0'),(87,'2024-06-01 12:00:00.000000',130,18,_binary '\0'),(88,'2024-07-01 12:00:00.000000',90,19,_binary '\0'),(89,'2024-08-01 12:00:00.000000',180,20,_binary '\0'),(90,'2024-09-01 12:00:00.000000',110,21,_binary '\0'),(91,'2024-10-01 12:00:00.000000',170,22,_binary '\0'),(92,'2024-11-01 12:00:00.000000',140,23,_binary '\0'),(93,'2024-12-01 12:00:00.000000',160,24,_binary '\0'),(94,'2025-01-01 12:00:00.000000',190,25,_binary '\0'),(95,'2025-02-01 12:00:00.000000',210,26,_binary '\0'),(96,'2025-03-01 12:00:00.000000',100,27,_binary '\0'),(97,'2025-04-01 12:00:00.000000',200,28,_binary '\0'),(98,'2025-05-01 12:00:00.000000',150,29,_binary '\0'),(99,'2025-06-01 12:00:00.000000',120,30,_binary '\0'),(100,'2025-07-01 12:00:00.000000',130,31,_binary '\0'),(101,'2025-08-01 12:00:00.000000',90,32,_binary '\0'),(102,'2025-09-01 12:00:00.000000',180,33,_binary '\0'),(103,'2025-10-01 12:00:00.000000',110,34,_binary '\0'),(104,'2025-11-01 12:00:00.000000',170,35,_binary '\0'),(105,'2025-12-01 12:00:00.000000',140,36,_binary '\0'),(106,'2026-01-01 12:00:00.000000',160,37,_binary '\0'),(107,'2026-02-01 12:00:00.000000',190,38,_binary '\0'),(108,'2026-03-01 12:00:00.000000',210,39,_binary '\0'),(109,'2026-04-01 12:00:00.000000',100,40,_binary '\0'),(110,'2026-05-01 12:00:00.000000',200,41,_binary '\0'),(111,'2026-06-01 12:00:00.000000',150,42,_binary '\0'),(112,'2026-07-01 12:00:00.000000',120,43,_binary '\0'),(113,'2026-08-01 12:00:00.000000',130,44,_binary '\0'),(114,'2026-09-01 12:00:00.000000',90,45,_binary '\0'),(115,'2026-10-01 12:00:00.000000',180,46,_binary '\0'),(116,'2026-11-01 12:00:00.000000',110,47,_binary '\0'),(117,'2026-12-01 12:00:00.000000',170,48,_binary '\0'),(118,'2027-01-01 12:00:00.000000',140,49,_binary '\0'),(119,'2027-02-01 12:00:00.000000',160,50,_binary '\0'),(120,'2027-03-01 12:00:00.000000',190,51,_binary '\0'),(121,'2027-04-01 12:00:00.000000',210,52,_binary '\0'),(122,'2027-05-01 12:00:00.000000',100,53,_binary '\0'),(123,'2027-06-01 12:00:00.000000',200,54,_binary '\0'),(124,'2027-07-01 12:00:00.000000',150,55,_binary '\0'),(125,'2027-08-01 12:00:00.000000',120,56,_binary '\0'),(126,'2027-09-01 12:00:00.000000',130,57,_binary '\0'),(127,'2027-10-01 12:00:00.000000',90,58,_binary '\0'),(128,'2027-11-01 12:00:00.000000',180,59,_binary '\0'),(129,'2027-12-01 12:00:00.000000',110,60,_binary '\0'),(130,'2028-01-01 12:00:00.000000',7,61,_binary '\0'),(131,'2028-02-01 12:00:00.000000',6,62,_binary '\0'),(132,'2028-03-01 12:00:00.000000',5,63,_binary '\0');
/*!40000 ALTER TABLE `warehouse_store` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-07 17:02:54
