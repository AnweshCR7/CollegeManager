-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 30, 2017 at 12:52 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `classmate`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE IF NOT EXISTS `attendance` (
  `email` varchar(255) NOT NULL,
  `sub` varchar(255) NOT NULL,
  `present` int(5) NOT NULL DEFAULT '0',
  PRIMARY KEY (`email`,`sub`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`email`, `sub`, `present`) VALUES
('abhi@gmail.com', 'casp', 9),
('abhi@gmail.com', 'Computer_Networking', 0),
('abhi@gmail.com', 'cpes', 0),
('abhi@gmail.com', 'dbms', 5),
('abhi@gmail.com', 'dm', 1),
('abhi@gmail.com', 'os', 3),
('abhi@gmail.com', 'se', 2),
('anweshcr7@gmail.com', 'casp', 6),
('anweshcr7@gmail.com', 'Computer_Networking', 0),
('anweshcr7@gmail.com', 'cpes', 1),
('anweshcr7@gmail.com', 'dbms', 2),
('anweshcr7@gmail.com', 'dm', 1),
('anweshcr7@gmail.com', 'os', 8),
('anweshcr7@gmail.com', 'se', 9),
('guptaprafull2@gmail.com', 'Computer_Networking', 0),
('guptaprafull2@gmail.com', 'cpes', 0),
('helloworld@gmail.com', 'Computer_Networking', 0),
('helloworld@gmail.com', 'cpes', 0),
('jajuanuj123@gmail.com', 'casp', 9),
('jajuanuj123@gmail.com', 'Computer_Networking', 0),
('jajuanuj123@gmail.com', 'cpes', 1),
('jajuanuj123@gmail.com', 'dbms', 3),
('jajuanuj123@gmail.com', 'dm', 1),
('jajuanuj123@gmail.com', 'os', 1),
('jajuanuj123@gmail.com', 'se', 1),
('pranay.s.deshmukh@gmail.com', 'casp', 4),
('pranay.s.deshmukh@gmail.com', 'Computer_Networking', 0),
('pranay.s.deshmukh@gmail.com', 'cpes', 0),
('pranay.s.deshmukh@gmail.com', 'dbms', 0),
('pranay.s.deshmukh@gmail.com', 'dm', 0),
('pranay.s.deshmukh@gmail.com', 'os', 3),
('pranay.s.deshmukh@gmail.com', 'se', 8);

-- --------------------------------------------------------

--
-- Table structure for table `casp`
--

CREATE TABLE IF NOT EXISTS `casp` (
  `day` varchar(20) NOT NULL DEFAULT '',
  `start` time NOT NULL DEFAULT '00:00:00',
  `end` time NOT NULL DEFAULT '00:00:00',
  `venue` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`day`,`start`,`end`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `casp`
--

INSERT INTO `casp` (`day`, `start`, `end`, `venue`) VALUES
('Friday', '00:00:00', '00:00:00', 'sd'),
('Monday', '09:00:00', '10:00:00', 'AC-204'),
('Monday', '11:00:00', '12:00:00', 'cogni'),
('Thursday', '00:00:00', '00:00:00', 'asd'),
('Tuesday', '10:00:00', '11:00:00', 'AC-201');

-- --------------------------------------------------------

--
-- Table structure for table `computer_networking`
--

CREATE TABLE IF NOT EXISTS `computer_networking` (
  `day` varchar(20) NOT NULL DEFAULT '',
  `start` time NOT NULL DEFAULT '00:00:00',
  `end` time NOT NULL DEFAULT '00:00:00',
  `venue` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`day`,`start`,`end`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `computer_networking`
--

INSERT INTO `computer_networking` (`day`, `start`, `end`, `venue`) VALUES
('Monday', '09:00:00', '10:00:00', 'Cogni'),
('Thursday', '08:00:00', '09:00:00', 'Cognizant_Lab');

-- --------------------------------------------------------

--
-- Table structure for table `cpes`
--

CREATE TABLE IF NOT EXISTS `cpes` (
  `day` varchar(20) NOT NULL DEFAULT '',
  `start` time NOT NULL DEFAULT '00:00:00',
  `end` time NOT NULL DEFAULT '00:00:00',
  `venue` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`day`,`start`,`end`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cpes`
--

INSERT INTO `cpes` (`day`, `start`, `end`, `venue`) VALUES
('Tuesday', '12:00:00', '13:00:00', 'AC-201'),
('Wednesday', '10:00:00', '11:30:00', 'EnTc_Ext._Building'),
('Wednesday', '17:00:00', '18:00:00', 'GNM_Lab');

-- --------------------------------------------------------

--
-- Table structure for table `dbms`
--

CREATE TABLE IF NOT EXISTS `dbms` (
  `day` varchar(20) NOT NULL DEFAULT '',
  `start` time NOT NULL DEFAULT '00:00:00',
  `end` time NOT NULL DEFAULT '00:00:00',
  `venue` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`day`,`start`,`end`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dbms`
--

INSERT INTO `dbms` (`day`, `start`, `end`, `venue`) VALUES
('Monday', '12:00:00', '13:00:00', 'PPL_Lab');

-- --------------------------------------------------------

--
-- Table structure for table `dm`
--

CREATE TABLE IF NOT EXISTS `dm` (
  `day` varchar(20) NOT NULL DEFAULT '',
  `start` time NOT NULL DEFAULT '00:00:00',
  `end` time NOT NULL DEFAULT '00:00:00',
  `venue` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`day`,`start`,`end`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `marks`
--

CREATE TABLE IF NOT EXISTS `marks` (
  `email` varchar(255) NOT NULL,
  `sub` varchar(20) NOT NULL,
  `T1` int(5) NOT NULL DEFAULT '0',
  `T2` int(5) NOT NULL DEFAULT '0',
  `ESE` int(5) NOT NULL DEFAULT '0',
  PRIMARY KEY (`email`,`sub`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `marks`
--

INSERT INTO `marks` (`email`, `sub`, `T1`, `T2`, `ESE`) VALUES
('abhi@gmail.com', 'casp', 0, 0, 0),
('abhi@gmail.com', 'dbms', 0, 0, 0),
('abhi@gmail.com', 'dm', 0, 0, 0),
('abhi@gmail.com', 'os', 0, 0, 0),
('abhi@gmail.com', 'se', 0, 0, 0),
('anweshcr7@gmail.com', 'casp', 0, 0, 0),
('anweshcr7@gmail.com', 'os', 0, 0, 0),
('anweshcr7@gmail.com', 'se', 0, 0, 0),
('jajuanuj123@gmail.com', 'casp', 0, 0, 0),
('jajuanuj123@gmail.com', 'cpes', 0, 0, 0),
('jajuanuj123@gmail.com', 'dbms', 0, 0, 0),
('jajuanuj123@gmail.com', 'dm', 0, 0, 0),
('jajuanuj123@gmail.com', 'os', 0, 0, 0),
('jajuanuj123@gmail.com', 'se', 0, 0, 0),
('pranay.s.deshmukh@gmail.com', 'casp', 0, 0, 0),
('pranay.s.deshmukh@gmail.com', 'os', 0, 0, 0),
('pranay.s.deshmukh@gmail.com', 'se', 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `notes`
--

CREATE TABLE IF NOT EXISTS `notes` (
  `email` varchar(20) NOT NULL,
  `title` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`email`,`title`,`date`,`time`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE IF NOT EXISTS `notification` (
  `sub` varchar(20) NOT NULL,
  `day_time` datetime NOT NULL,
  `notification` varchar(500) NOT NULL,
  `tag` varchar(30) NOT NULL,
  PRIMARY KEY (`sub`,`day_time`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`sub`, `day_time`, `notification`, `tag`) VALUES
('casp', '2016-04-22 14:01:36', 'No_LEC_for_CASP_tomorrow!', 'Lec'),
('Computer_Networking', '2016-04-22 14:31:01', 'Complete_your_MiniProject_by_Monday', 'Project'),
('Computer_Networking', '2016-04-22 15:04:03', 'Computer_Networking_lecture_added_from_08:00:00_-_09:00:00_at_Cognizant_Lab', 'New_Lecture'),
('cpes', '2016-04-22 15:05:06', 'cpes_lecture_added_from_17:00:00_-_18:00:00_at_GNM_Lab', 'New_Lecture'),
('dm', '2016-04-22 14:02:48', 'a', 'a'),
('os', '2016-04-21 15:22:43', 'Practical_Exam_to_be_held_on_Monday,_the_25th', 'Practical_Exam'),
('se', '2016-04-21 17:08:10', 'The_practical_examination_is_scheduled_on_23rd_April_2016_at_11:30_AM_Venue:_Academic_Complex', 'ESE_-_Practical_Exam');

-- --------------------------------------------------------

--
-- Table structure for table `os`
--

CREATE TABLE IF NOT EXISTS `os` (
  `day` varchar(20) NOT NULL DEFAULT '',
  `start` time NOT NULL DEFAULT '00:00:00',
  `end` time NOT NULL DEFAULT '00:00:00',
  `venue` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`day`,`start`,`end`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `os`
--

INSERT INTO `os` (`day`, `start`, `end`, `venue`) VALUES
('Friday', '04:00:00', '05:00:00', 'FOSS_Lab'),
('Tuesday', '08:00:00', '09:00:00', 'AC_Ground_Floor');

-- --------------------------------------------------------

--
-- Table structure for table `se`
--

CREATE TABLE IF NOT EXISTS `se` (
  `day` varchar(20) NOT NULL DEFAULT '',
  `start` time NOT NULL DEFAULT '00:00:00',
  `end` time NOT NULL DEFAULT '00:00:00',
  `venue` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`day`,`start`,`end`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `se`
--

INSERT INTO `se` (`day`, `start`, `end`, `venue`) VALUES
('Monday', '10:00:00', '11:00:00', 'AC-204'),
('Wednesday', '11:00:00', '12:00:00', 'AC-204');

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE IF NOT EXISTS `subjects` (
  `sub_name` varchar(255) NOT NULL,
  `sub_type` varchar(255) NOT NULL,
  `total_lec` int(5) NOT NULL DEFAULT '0',
  PRIMARY KEY (`sub_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`sub_name`, `sub_type`, `total_lec`) VALUES
('casp', 'theory', 11),
('Computer_Networking', 'theory', 0),
('cpes', 'theory', 0),
('dbms', 'theory', 10),
('dm', 'theory', 1),
('os', 'theory', 10),
('se', 'theory', 10);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password_hash` text NOT NULL,
  `type` varchar(20) NOT NULL,
  `api_key` varchar(32) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password_hash`, `type`, `api_key`, `status`, `created_at`) VALUES
(1, 'Pranay', 'pranayd@gmail.com', '$2a$10$a941e4a327d3aab900847ukrnBbxViP1DkMFNuWessNlD6IaE/3sC', 'student', 'a2d3d279d2ea94c60c52a13e4b8c4e8f', 1, '2016-04-18 09:06:39'),
(2, 'Anwesh', 'anwesh@gmail.com', '$2a$10$42d51378a731f8c7f1ab0OKWl13ZC0RfckpiX6FlTpq8PDWKvqIAe', 'student', '0626e8d161af82cf1f0c8f67ad583a80', 1, '2016-04-18 09:06:53'),
(3, 'Admin', 'admin@gmail.com', '$2a$10$4d95dbb0f16a7be79892eeumqG5o3nGNfZn2Tldxq/qE0rYDONdEi', 'faculty', 'e90857642302c6e90bb406b753560d9b', 1, '2016-04-19 11:15:11'),
(4, 'Abhijeet', 'abhi@gmail.com', '$2a$10$ac2dfb588f2d282c30a9auC4Q0/pzdPo3Xfo.RXTGy8DmTDQOGu.q', 'student', 'f2acd8bdd455dbac118fd661f2e8059e', 1, '2016-04-19 12:41:10'),
(5, 'Admin2', 'admin2@gmail.com', '$2a$10$9a87ae3ac95825ab6ec95ORAdiq6nS4GzBJsHT0tWL16DUIWQc276', 'faculty', '037c6e0a081c3713ff1e0ba9589031d4', 1, '2016-04-19 12:47:29'),
(6, 'Prafull', 'guptaprafull2@gmail.com', '$2a$10$2398373e344f716a8d6f2O/9OEshF.FS.LANLLACPVSeUai8opgaO', 'student', 'f10eaada35f7213d4cdee53bc0553fcc', 1, '2016-04-21 08:57:42'),
(7, 'Helloworld', 'helloworld@gmail.com', '$2a$10$9f67f0c6f9dbf2b673bddunO.K8o.q0fllJ4otgrCAeckEHUz5zCa', 'student', '1a3a7583b2fe05b13c0a29950cab8e97', 1, '2016-04-21 09:06:53'),
(8, 'Adar', 'jajuanuj123@gmail.com', '$2a$10$dddb11f8381d05d820620OfNOmahilk/c.3eWP63fjKKdmaCK0pyG', 'student', '680ff329160e9030a6d9d3d6c325946e', 1, '2016-04-22 08:07:37');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
