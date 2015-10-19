-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 16, 2015 at 10:58 PM
-- Server version: 5.5.44-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `BTLon`
--

-- --------------------------------------------------------

--
-- Table structure for table `Books`
--

CREATE TABLE IF NOT EXISTS `Books` (
  `BID` int(11) NOT NULL,
  `Name_Book` varchar(40) NOT NULL,
  `NXB` varchar(100) NOT NULL,
  `Tac_Gia` varchar(50) NOT NULL,
  `Gia` int(11) NOT NULL,
  `PLID` int(11) NOT NULL,
  PRIMARY KEY (`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Don_Hang`
--

CREATE TABLE IF NOT EXISTS `Don_Hang` (
  `OID` int(11) NOT NULL,
  `UID` int(11) NOT NULL,
  `BID` int(11) NOT NULL,
  `So_Luong` int(11) NOT NULL,
  PRIMARY KEY (`OID`),
  KEY `UID` (`UID`),
  KEY `BID` (`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Even`
--

CREATE TABLE IF NOT EXISTS `Even` (
  `EID` int(11) NOT NULL,
  `BID` int(11) NOT NULL,
  `sale` int(11) DEFAULT NULL,
  `start` date DEFAULT NULL,
  `end` date DEFAULT NULL,
  PRIMARY KEY (`EID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Favorite`
--

CREATE TABLE IF NOT EXISTS `Favorite` (
  `UID` int(11) NOT NULL,
  `BID` int(11) NOT NULL,
  PRIMARY KEY (`UID`,`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Gio`
--

CREATE TABLE IF NOT EXISTS `Gio` (
  `UID` int(11) NOT NULL,
  `BID` int(11) NOT NULL,
  `So_Luong` int(11) NOT NULL,
  `Mua` int(11) DEFAULT '0',
  PRIMARY KEY (`UID`,`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Phan_Loai`
--

CREATE TABLE IF NOT EXISTS `Phan_Loai` (
  `PLID` int(11) NOT NULL,
  `Loai` varchar(50) NOT NULL,
  PRIMARY KEY (`PLID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `UID` int(11) NOT NULL,
  `pass` varchar(40) NOT NULL,
  `Ho_Ten` varchar(40) NOT NULL,
  `Dia_Chi` varchar(100) NOT NULL,
  `SDT` int(11) NOT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Don_Hang`
--
ALTER TABLE `Don_Hang`
  ADD CONSTRAINT `Don_Hang_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `User` (`UID`),
  ADD CONSTRAINT `Don_Hang_ibfk_2` FOREIGN KEY (`BID`) REFERENCES `Books` (`BID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
