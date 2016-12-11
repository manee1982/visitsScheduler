-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 12, 2016 at 01:34 AM
-- Server version: 5.7.16-0ubuntu0.16.04.1
-- PHP Version: 7.0.8-0ubuntu0.16.04.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `visits_scheduler`
--

-- --------------------------------------------------------

--
-- Table structure for table `technicians`
--

CREATE TABLE `technicians` (
  `id` int(11) NOT NULL,
  `name` varchar(70) NOT NULL,
  `email` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `technicians`
--

INSERT INTO `technicians` (`id`, `name`, `email`) VALUES
(1, 'Ahmed Abd-elrahime Hussain', 'ahmed.hussain@tech-co.com'),
(2, 'Ali Ahmed Omer', 'ali.omer@tech-co.com'),
(3, 'Osman bin mohamed', 'osman.mohamed@tech-co.com'),
(4, 'Mohamed Ibrahim Hassan', 'mohamed.hassan@tech-co.com');

-- --------------------------------------------------------

--
-- Table structure for table `visits_schedule`
--

CREATE TABLE `visits_schedule` (
  `id` int(11) NOT NULL,
  `technician_id` int(11) NOT NULL,
  `title` varchar(70) NOT NULL,
  `start_date` date NOT NULL,
  `start_time` varchar(10) NOT NULL,
  `end_time` varchar(10) NOT NULL,
  `percentage` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `visits_schedule`
--

INSERT INTO `visits_schedule` (`id`, `technician_id`, `title`, `start_date`, `start_time`, `end_time`, `percentage`) VALUES
(1, 4, 'This is visit test for, mohamed', '2016-12-12', '12:00', '13:00', 25),
(2, 1, 'Client visit to day', '2016-12-11', '13:30', '14:00', 25),
(3, 2, 'This is yellow percentage test', '2016-12-13', '13:00', '14:00', 25),
(4, 2, 'Red, full time schedule', '2016-12-14', '12:00', '15:30', 90),
(5, 1, 'Another visit', '2016-12-14', '13:00', '15:00', 50),
(6, 4, 'Testing for working load context', '2016-12-14', '12:00', '14:30', 65);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `technicians`
--
ALTER TABLE `technicians`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `visits_schedule`
--
ALTER TABLE `visits_schedule`
  ADD PRIMARY KEY (`id`),
  ADD KEY `technician_id` (`technician_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `technicians`
--
ALTER TABLE `technicians`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `visits_schedule`
--
ALTER TABLE `visits_schedule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `visits_schedule`
--
ALTER TABLE `visits_schedule`
  ADD CONSTRAINT `visits_schedule_ibfk_1` FOREIGN KEY (`technician_id`) REFERENCES `technicians` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
