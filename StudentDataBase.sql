/*
SQLyog Community v9.63 
MySQL - 5.6.14 : Database - studentdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`studentdb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `studentdb`;

/*Table structure for table `studentinfo` */

DROP TABLE IF EXISTS `studentinfo`;

CREATE TABLE `studentinfo` (
  `entranceNo` int(11) DEFAULT NULL,
  `rollNo` int(11) DEFAULT NULL,
  `studentName` text,
  `gender` text,
  `fatherName` text,
  `address` text,
  `year` text,
  `major` text,
  `specializedSubject` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `studentinfo` */

insert  into `studentinfo`(`entranceNo`,`rollNo`,`studentName`,`gender`,`fatherName`,`address`,`year`,`major`,`specializedSubject`) values (1,2,'Mg Ye Linn Aung','null','U Chan Tun ','Mandalay','First Year','Computer Science','null'),(2,4,'Ma Kant Kaw Nway Oo','null','U Min Naung','yangon','First Year','Computer Science','null'),(3,6,'Ma Karyan Cho',NULL,'U Lwin Min Maung','Dawei',NULL,NULL,NULL),(4,8,'Mg Myo Thant ',NULL,'U Myo Hein Htoo','NayPyiTaw',NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
