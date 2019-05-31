-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 31 mai 2019 à 18:21
-- Version du serveur :  10.1.38-MariaDB
-- Version de PHP :  7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `clubmanager`
--

-- --------------------------------------------------------

--
-- Structure de la table `events`
--

CREATE TABLE `events` (
  `id_event` int(11) NOT NULL,
  `date_of_event` int(30) NOT NULL,
  `name_event` varchar(30) DEFAULT 'à ajouter ulterieurement',
  `official_sponsor` varchar(30) DEFAULT 'ENSAJ',
  `place` varchar(30) DEFAULT 'Big amphitheater'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `events`
--

INSERT INTO `events` (`id_event`, `date_of_event`, `name_event`, `official_sponsor`, `place`) VALUES
(15, 643, 'kjh', 'kjh', 'saveTreasury'),
(5, 20190531, 'dfg', '123', '123'),
(6, 20190516, 'HACKATON', 'ZAZA', 'ZAZA'),
(9, 20190531, 'gjghj', '555', '555'),
(19, 20191202, 'journée doctorale', 'BMCI', 'ENSA Eljadida'),
(13, 2344, '234412222', '2344', '2344&&&&'),
(14, 85765, 'statefkj', 'SDJKFH', 'CASA'),
(20, 0, 'nameevent', 'officialsponsor', 'place'),
(16, 65789, '567890', '76890', '76890'),
(21, 2019, 'EVENT1', 'SPONSOR', 'PLACE'),
(22, 2020, 'EVENT2', 'SOUHAIL', 'casa'),
(23, 2, 'sd', 'sdf', 'sdf'),
(24, 87652, '45245', '123', '123');

-- --------------------------------------------------------

--
-- Structure de la table `members`
--

CREATE TABLE `members` (
  `id_member` int(11) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `e_mail` varchar(50) NOT NULL,
  `type` varchar(30) DEFAULT 'user'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `members`
--

INSERT INTO `members` (`id_member`, `login`, `password`, `first_name`, `last_name`, `e_mail`, `type`) VALUES
(1, 'dkaki', 'dkaki', 'abdo', 'abdo', 'abdo@gmail.com', 'admin'),
(2, 'souhail', 'souhail', 'souhail', 'samir', 'afif@gmail.com', 'admin'),
(3, 'samirA', 'samir', 'afif', 'samir', 'samir@gmail.com', 'user'),
(4, 'ayad', 'ayad', 'samir', 'ayad', 'ayad@gmail.com', 'user'),
(5, 'faycal', 'faycal', 'ayad', 'faycal', 'faycal@gmail.com', 'user'),
(6, 'samiha', 'samiha', 'faiycal', 'samiha', 'samiha@gmail.com', 'user'),
(7, 'aicha', 'aicha', 'samiha', 'aicha', 'aicha@gmail.com', 'user'),
(8, 'anass', 'anass', 'said', 'anass', 'anass@gmail.com', 'user'),
(9, 'aziz', 'aziz', 'aziz', 'salim', 'salim@gmail.com', 'user'),
(45, 'abderrahim', 'abderrahim', 'abderrahim', 'abderrahim', 'abderrahim@abderrahim.com', 'user'),
(13, 'nahed', 'nahed', 'nahed', 'nahed', 'nahed@nahed.com', 'user'),
(19, 'souhail', 'nahed', 'lqskdh', 'sdlfkh', 'sdfjkh', 'user'),
(29, 'x', 'x', 'x', 'x', 'x', 'admin'),
(30, 'c', 'c', 'c', 'c', 'c@qsd.com', 'admin'),
(53, 'said', 'abdessalama', 'raouf', 'saida', 'saida@jkhlsdf.com', 'user'),
(32, 'salmam', 'salmam', 'salmam', 'salmam', 'salmam@salmam.com', 'user'),
(35, 'souhailnahed', 'souhailnahed', 'souhailnahed', 'souhailnahed', 'souhailnahed', 'user'),
(36, 'souhailnahed', 'souhailnahed', 'souhailnahed', 'souhailnahed', 'souhailnahed', 'user'),
(46, 'sanaenah', 'sanaenah', 'sanaenah', 'sanaenah', 'sanaenah', 'user'),
(38, 'souhailnah', 'souhailnah', 'souhailnah', 'souhailnah', 'souhailnah', 'user'),
(40, 'validEmail', 'validEmail', 'validEmail', 'validEmail', 'validEmail@validemail.com', 'user'),
(42, 'qsdqsd', 'qsdqsd', 'qsdqsd', 'qsdqsd', 'qsd@s.som', 'user'),
(54, 'nahed', 'nahedaze', 'nahed', 'nahed', 'nahed@ze.aze', 'user'),
(44, ' a', 'b', 'c', 'd', 'e@sdfs.com', 'user'),
(48, '123456', '123456', '123456', '123456', '123456@SDF.COM', 'user'),
(49, 'qsd', 'qsd', 'qsd', 'dqs', 'qsddd', 'user'),
(50, 'qsd', 'sdfq', 'qqsd', 'qsdqsd', 'qsdqsd@kjh.com', 'user'),
(51, 'souhailnahed1', 'souhailnahed', 'souhailnahed1', 'souhailnahed1', 'souhailnahed1@gmail.com', 'user'),
(52, 'B', 'azerty', 'B', 'BB', 'B@b.com', 'user'),
(55, 'user', 'passwd', 'user', 'user', 'user@user.com', 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `treasury`
--

CREATE TABLE `treasury` (
  `id_treasury` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `sum` int(20) NOT NULL,
  `state_of_treasury` varchar(11) NOT NULL,
  `responsible_treasury` varchar(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `treasury`
--

INSERT INTO `treasury` (`id_treasury`, `year`, `sum`, `state_of_treasury`, `responsible_treasury`) VALUES
(1, 20190508, 10000, 'level1', 'samir'),
(2, 20200903, 20000, 'level2', 'souhail'),
(3, 20181115, 30000, 'level3', 'NAHED'),
(4, 20170801, 40000, 'level4', 'dkaki'),
(7, 34234, 0, 'GOOD', 'SAID'),
(11, 2017, 324897, 'level3', 'anass'),
(9, 2019, 300000, 'BAD', 'kamal'),
(12, 8976, 765, 'jkh', 'SAID'),
(13, 234567, 345678, '56789', '890'),
(14, 34567, 45678, '5678', '5678'),
(15, 567890, 77890, '090', '0909'),
(16, 3456789, 456789, '7890', '567890'),
(17, 6789, 6789, '7689', '789'),
(18, 876, 876, '8976', '9876'),
(19, 2019, 20000, 'GOOD', 'ANA'),
(20, 765, 34, 'ss', 'ss'),
(21, 12, 12, 'QD', 'Q'),
(22, 123, 2, 'SQDF', 'QSD'),
(23, 123, 123, 'qsdqs', 'qsdqsd'),
(24, 1233, 1233, '1233', '1233'),
(25, 234234, 234234, 'QSFQSD', 'QSDFQFQSD'),
(36, 2019, 20192019, '2019', '2019'),
(37, 11111, 11111, '11111', '11111'),
(38, 11111, 11111, '11111', '11111'),
(39, 11111, 11111, '11111', '1111100'),
(40, 99, 8888, '00QSD', '000'),
(41, 33333, 33333, '33333', '33333'),
(42, 3987, 982347, '293487', '98237'),
(43, 888, 888, '888', '888'),
(44, 5555, 5555, '5555', '5555'),
(45, 2222, 2222, '2222', '2222'),
(46, 2222, 2222, '2222', '222299'),
(47, 5476, 7645, '7645', '765');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`id_event`);

--
-- Index pour la table `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`id_member`);

--
-- Index pour la table `treasury`
--
ALTER TABLE `treasury`
  ADD PRIMARY KEY (`id_treasury`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `events`
--
ALTER TABLE `events`
  MODIFY `id_event` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT pour la table `members`
--
ALTER TABLE `members`
  MODIFY `id_member` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT pour la table `treasury`
--
ALTER TABLE `treasury`
  MODIFY `id_treasury` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
