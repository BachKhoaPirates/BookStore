-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 20, 2015 at 07:12 PM
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
-- Table structure for table `Book`
--

CREATE TABLE IF NOT EXISTS `Book` (
  `BID` int(11) NOT NULL COMMENT 'book id',
  `Name_Book` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `Publisher` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Author` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Price` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  PRIMARY KEY (`BID`),
  UNIQUE KEY `BID_2` (`BID`),
  KEY `BID` (`BID`),
  KEY `BID_3` (`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Cart`
--

CREATE TABLE IF NOT EXISTS `Cart` (
  `UID` int(11) NOT NULL,
  `BID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Buy` int(11) DEFAULT '0',
  PRIMARY KEY (`UID`,`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Distribute`
--

CREATE TABLE IF NOT EXISTS `Distribute` (
  `PID` int(11) NOT NULL,
  `Sort` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`PID`),
  UNIQUE KEY `Loai` (`Sort`)
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
-- Table structure for table `Order_Book`
--

CREATE TABLE IF NOT EXISTS `Order_Book` (
  `OID` int(11) NOT NULL,
  `Phonenumber` int(11) NOT NULL,
  `BID` int(11) NOT NULL,
  `Quantity` int(11) DEFAULT '0',
  `Payment` int(11) DEFAULT '0',
  PRIMARY KEY (`OID`),
  KEY `Phonenumber` (`Phonenumber`),
  KEY `BID` (`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `The_Loai_Sach`
--

CREATE TABLE IF NOT EXISTS `The_Loai_Sach` (
  `BID` int(11) NOT NULL,
  `PID` int(11) NOT NULL,
  PRIMARY KEY (`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `Phonenumber` int(11) NOT NULL COMMENT 'Ten dang nhap',
  `UID` int(11) NOT NULL,
  `Password` varchar(40) NOT NULL,
  `Name` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Address` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`Phonenumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Order_Book`
--
ALTER TABLE `Order_Book`
  ADD CONSTRAINT `Order_Book_ibfk_1` FOREIGN KEY (`Phonenumber`) REFERENCES `User` (`Phonenumber`),
  ADD CONSTRAINT `Order_Book_ibfk_2` FOREIGN KEY (`BID`) REFERENCES `Book` (`BID`);

--
-- Constraints for table `The_Loai_Sach`
--
ALTER TABLE `The_Loai_Sach`
  ADD CONSTRAINT `The_Loai_Sach_ibfk_1` FOREIGN KEY (`BID`) REFERENCES `Book` (`BID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
