-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 23, 2022 at 02:10 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electro_grid(eg)_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `billing_management`
--

CREATE TABLE `billing_management` (
  `billingID` int(11) NOT NULL,
  `meterNumber` int(11) NOT NULL,
  `consumerName` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `durationPeriod` int(11) NOT NULL,
  `noOfUnits` int(11) NOT NULL,
  `totalAmount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `billing_management`
--

INSERT INTO `billing_management` (`billingID`, `meterNumber`, `consumerName`, `address`, `durationPeriod`, `noOfUnits`, `totalAmount`) VALUES
(1, 145, 'Thamoda', 'No.123/1, Gamunu Mawatha, Kalaniya', 30, 24, 400),
(2, 145, 'Thamoda', 'No.123/1, Gamunu Mawatha, Kalaniya', 30, 24, 400);

-- --------------------------------------------------------

--
-- Table structure for table `connection_management`
--

CREATE TABLE `connection_management` (
  `connectionNo` int(20) NOT NULL,
  `userName` varchar(40) NOT NULL,
  `address` varchar(50) NOT NULL,
  `mainTown` varchar(20) NOT NULL,
  `postalCode` varchar(20) NOT NULL,
  `postNumber` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `connection_management`
--

INSERT INTO `connection_management` (`connectionNo`, `userName`, `address`, `mainTown`, `postalCode`, `postNumber`) VALUES
(1, 'pradeep', 'alawwa ', 'alawwa', '60280', '23');

-- --------------------------------------------------------

--
-- Table structure for table `user_management`
--

CREATE TABLE `user_management` (
  `userID` int(11) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `userAddress` varchar(255) NOT NULL,
  `contactNumber` varchar(20) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `userType` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_management`
--

INSERT INTO `user_management` (`userID`, `FirstName`, `LastName`, `userAddress`, `contactNumber`, `Email`, `userType`) VALUES
(1, 'Peter', 'Parker', 'No.12/1, Hotel Road, Colombo 04.', '011-2194598', 'peter219@gmail.com', 'Technician'),
(8, 'Kevin', 'Fernando', 'No.217/1, Church Road, Mt.Lavinia ', '011-2789012', 'Kevin902@gmail.com', 'Consumer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `billing_management`
--
ALTER TABLE `billing_management`
  ADD PRIMARY KEY (`billingID`);

--
-- Indexes for table `connection_management`
--
ALTER TABLE `connection_management`
  ADD PRIMARY KEY (`connectionNo`);

--
-- Indexes for table `user_management`
--
ALTER TABLE `user_management`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `billing_management`
--
ALTER TABLE `billing_management`
  MODIFY `billingID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `connection_management`
--
ALTER TABLE `connection_management`
  MODIFY `connectionNo` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user_management`
--
ALTER TABLE `user_management`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
