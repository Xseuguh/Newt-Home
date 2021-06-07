CREATE DATABASE  IF NOT EXISTS `db_newthome` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_newthome`;
-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: db_newthome
-- ------------------------------------------------------
-- Server version	8.0.24

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
-- Table structure for table `contraintes`
--

DROP TABLE IF EXISTS `contraintes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contraintes` (
  `id_contrainte` int NOT NULL AUTO_INCREMENT,
  `nom_contrainte` varchar(255) NOT NULL,
  PRIMARY KEY (`id_contrainte`),
  UNIQUE KEY `nom_contrainte` (`nom_contrainte`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contraintes`
--

LOCK TABLES `contraintes` WRITE;
/*!40000 ALTER TABLE `contraintes` DISABLE KEYS */;
INSERT INTO `contraintes` VALUES (3,'2 enfants maximum par logement'),(5,'Pas d\'animaux autorisé'),(4,'Pas d\'enfants autorisé'),(2,'Pas de bruit après 23h'),(1,'Pas de cigarette');
/*!40000 ALTER TABLE `contraintes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversation`
--

DROP TABLE IF EXISTS `conversation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conversation` (
  `id_conversation` int NOT NULL AUTO_INCREMENT,
  `id_sender` int NOT NULL,
  `id_receiver` int NOT NULL,
  PRIMARY KEY (`id_conversation`),
  UNIQUE KEY `id_conversation` (`id_conversation`),
  KEY `fk user (sender)` (`id_sender`),
  KEY `fk user (receiver)` (`id_receiver`),
  CONSTRAINT `conversation_ibfk_1` FOREIGN KEY (`id_sender`) REFERENCES `user_info` (`id_user`) ON DELETE CASCADE,
  CONSTRAINT `conversation_ibfk_2` FOREIGN KEY (`id_receiver`) REFERENCES `user_info` (`id_user`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation`
--

LOCK TABLES `conversation` WRITE;
/*!40000 ALTER TABLE `conversation` DISABLE KEYS */;
/*!40000 ALTER TABLE `conversation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (19);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `liaison_offre_contrainte`
--

DROP TABLE IF EXISTS `liaison_offre_contrainte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `liaison_offre_contrainte` (
  `id_liaison` int NOT NULL AUTO_INCREMENT,
  `id_offre` int NOT NULL,
  `id_contrainte` int NOT NULL,
  PRIMARY KEY (`id_liaison`),
  UNIQUE KEY `id_liaison` (`id_liaison`),
  KEY `fk liaison_offre_c` (`id_offre`) USING BTREE,
  KEY `fk liaison_contrainte` (`id_contrainte`) USING BTREE,
  CONSTRAINT `fk liaison_constrainte` FOREIGN KEY (`id_contrainte`) REFERENCES `contraintes` (`id_contrainte`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk liaison_offre_c` FOREIGN KEY (`id_offre`) REFERENCES `offres` (`id_offre`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liaison_offre_contrainte`
--

LOCK TABLES `liaison_offre_contrainte` WRITE;
/*!40000 ALTER TABLE `liaison_offre_contrainte` DISABLE KEYS */;
INSERT INTO `liaison_offre_contrainte` VALUES (2,1,5),(3,1,4),(4,1,2),(5,1,1),(9,8,3),(10,8,1),(13,12,2);
/*!40000 ALTER TABLE `liaison_offre_contrainte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `liaison_offre_service`
--

DROP TABLE IF EXISTS `liaison_offre_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `liaison_offre_service` (
  `id_liaison` int NOT NULL AUTO_INCREMENT,
  `id_offre` int NOT NULL,
  `id_service` int NOT NULL,
  PRIMARY KEY (`id_liaison`),
  UNIQUE KEY `id_liaison` (`id_liaison`),
  KEY `fk liaison_offre` (`id_offre`) USING BTREE,
  KEY `fk liaison_service` (`id_service`) USING BTREE,
  CONSTRAINT `fk liaison_offre` FOREIGN KEY (`id_offre`) REFERENCES `offres` (`id_offre`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk liaison_service` FOREIGN KEY (`id_service`) REFERENCES `services` (`id_service`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liaison_offre_service`
--

LOCK TABLES `liaison_offre_service` WRITE;
/*!40000 ALTER TABLE `liaison_offre_service` DISABLE KEYS */;
INSERT INTO `liaison_offre_service` VALUES (6,1,2),(7,1,3),(11,8,2),(14,12,1);
/*!40000 ALTER TABLE `liaison_offre_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id_msg` int NOT NULL AUTO_INCREMENT,
  `id_conversation` int NOT NULL,
  `msg` text NOT NULL,
  `date_message` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_origine` int NOT NULL COMMENT 'fk de la table user_info',
  PRIMARY KEY (`id_msg`),
  UNIQUE KEY `id_msg` (`id_msg`),
  KEY `fk_conversation` (`id_conversation`) USING BTREE,
  KEY `fk user` (`id_origine`) USING BTREE,
  CONSTRAINT `fk user` FOREIGN KEY (`id_origine`) REFERENCES `user_info` (`id_user`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_conversation` FOREIGN KEY (`id_conversation`) REFERENCES `conversation` (`id_conversation`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offres`
--

DROP TABLE IF EXISTS `offres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offres` (
  `id_offre` int NOT NULL AUTO_INCREMENT,
  `id_user` int NOT NULL COMMENT 'fk de la table user_info',
  `titre` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `pays` varchar(255) NOT NULL,
  `ville` varchar(255) NOT NULL,
  `code_postal` int NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `date_debut` date NOT NULL,
  `date_limite` date NOT NULL,
  `pourvu` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_offre`),
  UNIQUE KEY `id_offre` (`id_offre`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `fk offres_user` FOREIGN KEY (`id_user`) REFERENCES `user_info` (`id_user`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offres`
--

LOCK TABLES `offres` WRITE;
/*!40000 ALTER TABLE `offres` DISABLE KEYS */;
INSERT INTO `offres` VALUES (1,9,'Un bel appartement à Paris','Situé dans un écrin de verdure cette appartement est parfait pour y passer vos vacances.\r\nGrand, calme et situé à 2 pas du métro Barbes Rochechouart et à 2 pas de Montmartre vous aurez une vue incroyable sur le sacré cœur qui illuminera vos soirées.','France','Paris',75003,'23 rue des Gardes','2021-07-01','2021-08-31',1),(8,10,'Maison avec piscine en bordure de Lyon','Situé à 15 min du stade Gerland par métro, l\'endroit est cosy.\r\nIl s\'agit d\'un ancien restaurant reconverti en maison dans un quartier cossu.\r\nL\'été, la piscine devient un endroit très agréable.','France','Lyon',69000,'15 rue Croix-Barret','2021-06-09','2021-06-30',0),(12,9,'Maison zen et cosy','Qui n\'a jamais eu envie de voir la ville de Marcel Pagnol!\r\nSon port, ses calanques, son soleil!\r\nLa maison proposée est situé dans la banlieue sud.\r\nDe nombreuses personnalité y ont séjourné tel que Pompidou, Jean Rochefort, Edinho DOS-REIS....','France','Marseille',13000,'45 avenue de la pointe rouge','2021-06-12','2021-07-24',0);
/*!40000 ALTER TABLE `offres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offres_postulees`
--

DROP TABLE IF EXISTS `offres_postulees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offres_postulees` (
  `id_postula` int NOT NULL AUTO_INCREMENT,
  `id_offre` int NOT NULL COMMENT 'fk de la table offres',
  `id_user` int NOT NULL COMMENT 'fk de la table user_info',
  `accepte` tinyint NOT NULL DEFAULT '0' COMMENT 'vaut 1 si le proprio a accepté la demande ',
  PRIMARY KEY (`id_postula`),
  UNIQUE KEY `id_postula` (`id_postula`),
  KEY `id_offre` (`id_offre`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `fk offrepostulées_offre` FOREIGN KEY (`id_offre`) REFERENCES `offres` (`id_offre`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk offrespostulées_user` FOREIGN KEY (`id_user`) REFERENCES `user_info` (`id_user`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offres_postulees`
--

LOCK TABLES `offres_postulees` WRITE;
/*!40000 ALTER TABLE `offres_postulees` DISABLE KEYS */;
/*!40000 ALTER TABLE `offres_postulees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `id` int NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'User',1),(2,'Admin',2);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `id_service` int NOT NULL AUTO_INCREMENT,
  `nom_service` varchar(255) NOT NULL,
  PRIMARY KEY (`id_service`),
  UNIQUE KEY `nom_service` (`nom_service`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (2,'Arroser les plantes'),(1,'Garder les animaux'),(3,'Nettoyer la maison');
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `birthday` date NOT NULL,
  `mail` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `id_user` (`id_user`),
  UNIQUE KEY `mail` (`mail`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'Prévert','Jacques','2000-02-05','jacques.prev@newt.com','$2a$10$HwhMN05VBpJuPZA9LRQrXOAQEgqGJ4pHlZF9bXEJ66ylUS0IYo2G2',1),(9,'Langlois','Marc','2000-06-07','marc.langlois@gmail.com','$2a$10$ffnWQRKw78nPnG.KrnQaK.2LtEl1n.8JJ9Fk7RVWNhEnz.KaDOodC',0),(10,'Koscieny','Marie','1987-06-01','marie65@gmail.com','$2a$10$HB9lnP.Jl22PqSVg5w90rezWp0nG1wv8GUl9831IEBXbPI/VA5XYK',0);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1),(1,2),(9,1),(10,1);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-07 23:44:39
