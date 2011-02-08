CREATE DATABASE  IF NOT EXISTS `infows` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `infows`;
-- MySQL dump 10.13  Distrib 5.1.40, for Win32 (ia32)
--
-- Host: localhost    Database: infows
-- ------------------------------------------------------
-- Server version	5.5.8

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
-- Table structure for table `storico`
--

DROP TABLE IF EXISTS `storico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storico` (
  `IdStorico` int(11) NOT NULL AUTO_INCREMENT,
  `IdWebService` int(11) NOT NULL,
  `DtInizioEsec` datetime NOT NULL,
  `DtFineEsec` datetime DEFAULT NULL,
  `ultagg` datetime DEFAULT NULL,
  `utente` varchar(45) NOT NULL DEFAULT 'dbo',
  PRIMARY KEY (`IdStorico`),
  KEY `IX_WebService` (`IdWebService`),
  KEY `FK_WebService` (`IdWebService`),
  CONSTRAINT `FK_WebService` FOREIGN KEY (`IdWebService`) REFERENCES `webservice` (`IdWebService`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storico`
--

LOCK TABLES `storico` WRITE;
/*!40000 ALTER TABLE `storico` DISABLE KEYS */;
INSERT INTO `storico` VALUES (1,1,'2011-02-02 04:10:00',NULL,NULL,'dbo');
/*!40000 ALTER TABLE `storico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applicativo`
--

DROP TABLE IF EXISTS `applicativo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applicativo` (
  `IdApplicativo` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(45) NOT NULL,
  `ultagg` datetime DEFAULT NULL,
  `utente` varchar(45) NOT NULL DEFAULT 'dbo',
  PRIMARY KEY (`IdApplicativo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicativo`
--

LOCK TABLES `applicativo` WRITE;
/*!40000 ALTER TABLE `applicativo` DISABLE KEYS */;
INSERT INTO `applicativo` VALUES (3,'Pass',NULL,'dbo'),(4,'Paghe',NULL,'dbo');
/*!40000 ALTER TABLE `applicativo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `webservice`
--

DROP TABLE IF EXISTS `webservice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `webservice` (
  `IdWebService` int(11) NOT NULL AUTO_INCREMENT,
  `IdApplicativo` int(11) NOT NULL,
  `Nome` varchar(45) NOT NULL,
  `Descrizione` varchar(500) DEFAULT NULL,
  `ultagg` datetime DEFAULT NULL,
  `utente` varchar(45) NOT NULL DEFAULT 'dbo',
  PRIMARY KEY (`IdWebService`),
  KEY `IX_Applicativo` (`IdApplicativo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `webservice`
--

LOCK TABLES `webservice` WRITE;
/*!40000 ALTER TABLE `webservice` DISABLE KEYS */;
INSERT INTO `webservice` VALUES (1,3,'Importa Malattie','Serve per importare le malattie da opera',NULL,'dbo');
/*!40000 ALTER TABLE `webservice` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-02-09  0:10:17
