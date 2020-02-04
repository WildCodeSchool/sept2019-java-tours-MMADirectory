-- MySQL dump 10.13  Distrib 8.0.18, for osx10.14 (x86_64)
--
-- Host: localhost    Database: mfcg
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `photo_url` varchar(255) DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL,
  `fk_region_id` bigint(20) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo1_url` varchar(255) DEFAULT NULL,
  `photo2_url` varchar(255) DEFAULT NULL,
  `photo3_url` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `social_url` varchar(255) DEFAULT NULL,
  `valide` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhriw42i11y0m82nnhtm02h5ta` (`fk_region_id`),
  CONSTRAINT `FKhriw42i11y0m82nnhtm02h5ta` FOREIGN KEY (`fk_region_id`) REFERENCES `region` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (1,'225 Boulevard Charles de Gaulle','la description','/files/1/logo.png','MMA FIGHT CLUB GYM',NULL,'Saint-Cyr-sur-Loire',4,'0000000000','/files/1/3334.jpg','/files/1/1214.jpg','/files/1/735.jpg','37000','https://www.mfcgym.fr/',_binary '');
/*!40000 ALTER TABLE `club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club_discipline`
--

DROP TABLE IF EXISTS `club_discipline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club_discipline` (
  `club_id` bigint(20) NOT NULL,
  `discipline_id` bigint(20) NOT NULL,
  KEY `FK5hqyv2pvmnhmi018rj077n8ts` (`discipline_id`),
  KEY `FK7u2dinlcfbmsnng90g9v4sui0` (`club_id`),
  CONSTRAINT `FK5hqyv2pvmnhmi018rj077n8ts` FOREIGN KEY (`discipline_id`) REFERENCES `discipline` (`id`),
  CONSTRAINT `FK7u2dinlcfbmsnng90g9v4sui0` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_discipline`
--

LOCK TABLES `club_discipline` WRITE;
/*!40000 ALTER TABLE `club_discipline` DISABLE KEYS */;
INSERT INTO `club_discipline` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `club_discipline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discipline`
--

DROP TABLE IF EXISTS `discipline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discipline` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discipline`
--

LOCK TABLES `discipline` WRITE;
/*!40000 ALTER TABLE `discipline` DISABLE KEYS */;
INSERT INTO `discipline` VALUES (1,'Boxe Anglaise'),(2,'Boxe Thaïlandaise'),(3,'Jiu-Jitsu Brésilien'),(4,'MMA'),(5,'Kids'),(6,'Grappling'),(7,'Lutte'),(8,'Capoeira'),(9,'Muay Thaï'),(10,'Krav Maga'),(11,'Pancrace'),(12,'Luta Livre'),(13,'Savate');
/*!40000 ALTER TABLE `discipline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `region` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES (1,'Bretagne'),(2,'Auvergne-Rhône-Alpes'),(3,'Bourgogne-Franche-Comté'),(4,'Centre Val-de-loire'),(5,'Corse'),(6,'Grand-Est'),(7,'Haut-de-France'),(8,'Ile-de-France'),(9,'Normandie'),(10,'Nouvelle-Aquitaine'),(11,'Occitanie'),(12,'Pays de la Loire');
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-04 15:06:08
