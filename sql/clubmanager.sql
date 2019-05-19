-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  Dim 19 mai 2019 à 19:28
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
(1, 20190515, 'dfg', NULL, NULL),
(2, 20190514, 'dfg', NULL, NULL),
(4, 20190530, NULL, NULL, NULL),
(5, 20190531, 'dfg', NULL, NULL),
(6, 20190516, 'HACKATON', 'ZAZA', 'ZAZA'),
(7, 20190524, NULL, NULL, NULL),
(8, 20190525, 'dfg', NULL, NULL),
(9, 20190531, 'gjghj', NULL, NULL),
(10, 20190525, 'dfgdfg', NULL, NULL),
(11, 3, 'sdf', 'sdf', 'sdf'),
(13, 2344, '2344', '2344', '2344'),
(14, 85765, 'statefkj', 'SDJKFH', 'CASA');

-- --------------------------------------------------------

--
-- Structure de la table `login`
--

CREATE TABLE `login` (
  `login` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `login`
--

INSERT INTO `login` (`login`, `password`) VALUES
('dkaki', 'dkaki'),
('souhail', 'souhail');

-- --------------------------------------------------------

--
-- Structure de la table `members`
--

CREATE TABLE `members` (
  `id_member` int(11) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `first_name` varchar(11) NOT NULL,
  `last_name` varchar(11) NOT NULL,
  `e_mail` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `members`
--

INSERT INTO `members` (`id_member`, `login`, `password`, `first_name`, `last_name`, `e_mail`) VALUES
(1, 'dkaki', 'dkaki', 'abdo', 'abdo', 'abdo@gmail.com'),
(2, 'souhail', 'souhail', 'souhail', 'samir', 'afif@gmail.com'),
(3, 'samirA', 'samir', 'afif', 'samir', 'samir@gmail.com'),
(4, 'ayad', 'ayad', 'samir', 'ayad', 'ayad@gmail.com'),
(5, 'faycal', 'faycal', 'ayad', 'faycal', 'faycal@gmail.com'),
(6, 'samiha', 'samiha', 'faiycal', 'samiha', 'samiha@gmail.com'),
(7, 'aicha', 'aicha', 'samiha', 'aicha', 'aicha@gmail.com'),
(8, 'anass', 'anass', 'said', 'anass', 'anass@gmail.com'),
(9, 'aziz', 'aziz', 'aziz', 'salim', 'salim@gmail.com'),
(13, 'nahed', 'nahed', 'nahed', 'nahed', 'nahed@nahed.com'),
(19, 'souhail', 'nahed', 'lqskdh', 'sdlfkh', 'sdfjkh');

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
(9, 2019, 300000, 'BAD', 'kamal');

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
  MODIFY `id_event` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `members`
--
ALTER TABLE `members`
  MODIFY `id_member` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `treasury`
--
ALTER TABLE `treasury`
  MODIFY `id_treasury` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
