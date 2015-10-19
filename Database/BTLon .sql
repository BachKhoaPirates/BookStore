-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 19, 2015 at 02:52 PM
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
-- Table structure for table `Don_Hang`
--

CREATE TABLE IF NOT EXISTS `Don_Hang` (
  `OID` int(11) NOT NULL,
  `UID` int(11) NOT NULL,
  `BID` int(11) NOT NULL,
  `So_Luong` int(11) NOT NULL,
  `Thanh_Toan` int(11) DEFAULT '0',
  PRIMARY KEY (`OID`),
  KEY `UID` (`UID`),
  KEY `BID` (`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Gio_Hang`
--

CREATE TABLE IF NOT EXISTS `Gio_Hang` (
  `UID` int(11) NOT NULL,
  `BID` int(11) NOT NULL,
  `So_Luong` int(11) NOT NULL,
  `Mua` int(11) DEFAULT '0',
  PRIMARY KEY (`UID`,`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Nguoi_Dung`
--

CREATE TABLE IF NOT EXISTS `Nguoi_Dung` (
  `UID` int(11) NOT NULL,
  `pass` varchar(40) NOT NULL,
  `Ho_Ten` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Dia_Chi` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `SDT` int(11) NOT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Nguoi_Dung`
--

INSERT INTO `Nguoi_Dung` (`UID`, `pass`, `Ho_Ten`, `Dia_Chi`, `SDT`) VALUES
(2, '1234', 'Thach', 'Ha Noi', 2),
(3, '1234', 'Minh', 'Ha Noi', 3),
(4, '1234', 'Son', 'Ha Noi', 4),
(5, '1234df', 'Hai', 'Ha Noi', 5),
(6, '1234ddf', 'Hoa', 'Ha Nam', 6),
(7, '12d34ddf', 'Tung', 'Ha Tinh', 7),
(8, '12d34ddf', 'Trang', 'Ha Tinh', 8),
(9, '12d34ddf', 'Tra', 'Ha Tinh', 9),
(10, '12d34dddf', 'Ha', 'Nghe An', 10),
(123213, '1234', 'Quang', 'Thanh Hoa', 123213);

-- --------------------------------------------------------

--
-- Table structure for table `Phan_Loai`
--

CREATE TABLE IF NOT EXISTS `Phan_Loai` (
  `PLID` int(11) NOT NULL,
  `Loai` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`PLID`),
  UNIQUE KEY `Loai` (`Loai`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Sach`
--

CREATE TABLE IF NOT EXISTS `Sach` (
  `BID` int(11) NOT NULL,
  `Ten_sach` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `NXB` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Tac_Gia` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Gia` int(11) NOT NULL,
  `So_luong` int(11) NOT NULL,
  PRIMARY KEY (`BID`),
  UNIQUE KEY `BID_2` (`BID`),
  KEY `BID` (`BID`),
  KEY `BID_3` (`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `The_Loai_Sach`
--

CREATE TABLE IF NOT EXISTS `The_Loai_Sach` (
  `BID` int(11) NOT NULL,
  `PLID` int(11) NOT NULL,
  PRIMARY KEY (`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Yeu_Thich`
--

CREATE TABLE IF NOT EXISTS `Yeu_Thich` (
  `UID` int(11) NOT NULL,
  `BID` int(11) NOT NULL,
  PRIMARY KEY (`UID`,`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Don_Hang`
--
ALTER TABLE `Don_Hang`
  ADD CONSTRAINT `Don_Hang_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `Nguoi_Dung` (`UID`),
  ADD CONSTRAINT `Don_Hang_ibfk_2` FOREIGN KEY (`BID`) REFERENCES `Sach` (`BID`);

--
-- Constraints for table `The_Loai_Sach`
--
ALTER TABLE `The_Loai_Sach`
  ADD CONSTRAINT `The_Loai_Sach_ibfk_1` FOREIGN KEY (`BID`) REFERENCES `Sach` (`BID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
