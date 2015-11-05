-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 05, 2015 at 01:32 PM
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
  `Name_Book` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Publisher` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Author` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Price` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  PRIMARY KEY (`BID`),
  UNIQUE KEY `BID_2` (`BID`),
  KEY `BID` (`BID`),
  KEY `BID_3` (`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Book`
--

INSERT INTO `Book` (`BID`, `Name_Book`, `Publisher`, `Author`, `Price`, `Quantity`) VALUES
(1, 'Có những bàn tay', 'NXB Văn Học', 'September Rain', 59000, 10),
(2, 'Mật Mã Thanh Minh Thượng Hà ', 'NXB Văn Học\r\n', 'Dã Văn Bưu', 135000, 15),
(3, 'Steve Jobs', 'NXB Lao Động\r\n', 'Brent Schlender, Rick Tetzeli', 155000, 13),
(4, 'Triệu Phú Khởi Nghiệp Từ Hai Bàn Tay Trắng', 'NXB Lao Động\r\n', 'Triệu Phàm Vũ', 130000, 12),
(5, 'Tôi Là Bêtô', 'NXB Trẻ\r\n', 'Nguyễn Nhật Ánh', 40000, 14),
(6, 'Candy Book - Chuyến Đi Thực Tế Đầu Tiên Của Tôi\r\n', 'NXB Mỹ Thuật\r\n', 'Dream Cartoon', 52000, 7),
(7, 'Ca, P, Mg Có Gì Hay?\r\n', 'NXB Văn Hóa - Văn Nghệ\r\n', 'BS. Lương Lễ Hoàng', 78000, 5),
(8, 'Câu Chuyện Nhỏ - Trí Tuệ Lớn\r\n', 'NXB Văn Học', 'Bùi Thị Thiên Thai', 36000, 11),
(9, 'Sống Tích Cực Để Yêu Thương\r\n', 'NXB Lao Động Xã Hội\r\n', 'Nguyễn Tuấn Quỳnh', 83300, 20),
(10, 'Dược Học Tham Luận\r\n', 'NXB Lao Động\r\n', 'Chơn Nguyên', 90000, 18),
(11, 'Góc Nhìn Sử Việt - Đất Nước Việt Nam Qua Các Đời\r\n', 'NXB Hồng Đức', 'Đào Duy Anh', 97000, 13),
(12, 'Từ Điển Hàn - Việt (Khoảng 120.000 Mục Từ) - Bìa Đỏ\r\n', 'NXB Hồng Đức\r\n', 'Lê Huy Khoa', 294000, 5),
(13, 'Deutsch Wodoku: Vui Học Từ Vựng Với Ô Chữ Sudoku\r\n', 'NXB Tổng hợp TP.HCM', 'Elke Huppertz', 198000, 7),
(14, 'Con Hoang\r\n', 'NXB Hội Nhà Văn', 'Lê Hồng Nguyên', 55000, 8),
(15, '12 Chòm Sao Và Những Điều Chưa Nói\r\n', 'NXB Dân Trí\r\n', 'Herbert Schendl', 55000, 9),
(16, 'Những Chuyến Phiêu Lưu Nhất Quả Đất - Hoàng Tử Ivan, Con Chim Lửa Và Con Sói Xám\r\n', 'NXB Dân Trí\r\n', '', 24500, 5),
(17, 'Ngụ Ngôn Aesop (Nhã Nam)\r\n', 'NXB Văn Học\r\n', 'Aesop', 67000, 13),
(18, '50 Điều Trường Học Không Dạy Bạn Và 20 Điều Cần Làm Trước Khi Rời Ghế Nhà Trường\r\n', 'NXB Lao Động Xã Hội\r\n', 'Alphabooks', 89000, 4),
(19, 'Ban Ki Moon - Hãy Học Như Kẻ Ngốc Và Ước Mơ Như Thiên Tài\r\n', 'NXB Thế Giới\r\n', 'Shin Woong Jin', 89000, 11),
(20, 'Nhân Quả Không Miễn Trừ Ai\r\n', 'NXB Hội Nhà Văn\r\n', 'Nguyễn Chu Phác', 100000, 6);

-- --------------------------------------------------------

--
-- Table structure for table `Cart`
--

CREATE TABLE IF NOT EXISTS `Cart` (
  `UID` int(11) NOT NULL,
  `BID` int(11) NOT NULL,
  `Buy` int(11) DEFAULT '0',
  PRIMARY KEY (`UID`,`BID`),
  KEY `BID` (`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Cart`
--

INSERT INTO `Cart` (`UID`, `BID`, `Buy`) VALUES
(195842465, 1, 1),
(195842465, 2, 0),
(195842465, 3, 1),
(195875456, 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Distribute`
--

CREATE TABLE IF NOT EXISTS `Distribute` (
  `PID` int(11) NOT NULL,
  `Sort` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'Ten loai',
  PRIMARY KEY (`PID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Distribute`
--

INSERT INTO `Distribute` (`PID`, `Sort`) VALUES
(1, 'Sách Tiếng Anh'),
(2, 'Sách Văn Học - Tiểu thuyết'),
(3, 'Sách Kinh Tế'),
(4, 'Sách Chuyên Ngành'),
(5, 'Sách Kỹ Năng sống - Nghệ thuật sống'),
(6, 'Sách Giáo Khoa - Tham Khảo'),
(7, 'Sách Thiếu Nhi'),
(8, 'Sách Thường Thức - Đời Sống'),
(9, 'Sách Văn Hóa - Nghệ thuật - Du Lịch'),
(10, 'Sách Ngoại Ngữ - Từ Điển');

-- --------------------------------------------------------

--
-- Table structure for table `Favorite`
--

CREATE TABLE IF NOT EXISTS `Favorite` (
  `UID` int(11) NOT NULL,
  `BID` int(11) NOT NULL,
  PRIMARY KEY (`UID`,`BID`),
  KEY `BID` (`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Favorite`
--

INSERT INTO `Favorite` (`UID`, `BID`) VALUES
(195842465, 1),
(195842465, 2),
(195842465, 3),
(1672483929, 3),
(195875465, 4),
(195875456, 5),
(1672483929, 6),
(195875465, 7),
(195875456, 8),
(195875456, 9),
(195875656, 10);

-- --------------------------------------------------------

--
-- Table structure for table `Genre_Book`
--

CREATE TABLE IF NOT EXISTS `Genre_Book` (
  `PID` int(11) NOT NULL,
  `BID` int(11) NOT NULL,
  PRIMARY KEY (`BID`,`PID`),
  KEY `PID` (`PID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Genre_Book`
--

INSERT INTO `Genre_Book` (`PID`, `BID`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 6),
(2, 1),
(2, 3),
(2, 9),
(2, 10),
(3, 10),
(3, 15),
(3, 19),
(4, 11),
(4, 13),
(4, 15),
(5, 11),
(5, 12),
(5, 14),
(5, 17),
(6, 7),
(6, 8),
(6, 14),
(7, 9),
(7, 10),
(7, 18),
(8, 15),
(8, 18),
(8, 19),
(9, 15),
(10, 12),
(10, 14),
(10, 15);

-- --------------------------------------------------------

--
-- Table structure for table `Order_Book`
--

CREATE TABLE IF NOT EXISTS `Order_Book` (
  `UID` int(11) NOT NULL,
  `BID` int(11) NOT NULL,
  `Quantity` int(11) DEFAULT '0',
  PRIMARY KEY (`UID`,`BID`),
  KEY `BID` (`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Order_Book`
--

INSERT INTO `Order_Book` (`UID`, `BID`, `Quantity`) VALUES
(195842465, 1, 5);

-- --------------------------------------------------------

--
-- Table structure for table `Order_User`
--

CREATE TABLE IF NOT EXISTS `Order_User` (
  `OID` int(11) NOT NULL,
  `UID` int(11) NOT NULL,
  `Payment` int(11) DEFAULT '0',
  PRIMARY KEY (`OID`),
  KEY `UID` (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `UID` int(11) NOT NULL COMMENT 'Ten dang nhap',
  `Password` varchar(40) NOT NULL,
  `Name` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Address` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`UID`, `Password`, `Name`, `Address`) VALUES
(195842465, '1225560', 'Phạm Ngọc Thạch', 'Hà Nội'),
(195875456, '124430', 'Phạm Việt Thanh', 'Nghệ An'),
(195875465, '124460', 'Phạm Ngọc Thành', 'Hà Nội'),
(195875656, '124430', 'Nguyễn Việt Anh', 'Nam Định'),
(1672483929, '1234567', 'Nguyễn Văn Quang', 'Thanh Hóa');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Cart`
--
ALTER TABLE `Cart`
  ADD CONSTRAINT `Cart_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `User` (`UID`),
  ADD CONSTRAINT `Cart_ibfk_2` FOREIGN KEY (`BID`) REFERENCES `Book` (`BID`);

--
-- Constraints for table `Favorite`
--
ALTER TABLE `Favorite`
  ADD CONSTRAINT `Favorite_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `User` (`UID`),
  ADD CONSTRAINT `Favorite_ibfk_2` FOREIGN KEY (`BID`) REFERENCES `Book` (`BID`);

--
-- Constraints for table `Genre_Book`
--
ALTER TABLE `Genre_Book`
  ADD CONSTRAINT `Genre_Book_ibfk_1` FOREIGN KEY (`BID`) REFERENCES `Book` (`BID`),
  ADD CONSTRAINT `Genre_Book_ibfk_2` FOREIGN KEY (`PID`) REFERENCES `Distribute` (`PID`);

--
-- Constraints for table `Order_Book`
--
ALTER TABLE `Order_Book`
  ADD CONSTRAINT `Order_Book_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `User` (`UID`),
  ADD CONSTRAINT `Order_Book_ibfk_2` FOREIGN KEY (`BID`) REFERENCES `Book` (`BID`);

--
-- Constraints for table `Order_User`
--
ALTER TABLE `Order_User`
  ADD CONSTRAINT `Order_User_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `Order_Book` (`UID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
