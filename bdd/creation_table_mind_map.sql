-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 04 mai 2018 à 15:59
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `mind_map`
--

-- --------------------------------------------------------

--
-- Structure de la table `amis`
--

DROP TABLE IF EXISTS `amis`;
CREATE TABLE IF NOT EXISTS `amis` (
  `Num_utilisateur1` int(11) NOT NULL,
  `Num_utilisateur2` int(11) NOT NULL,
  KEY `Num_utilisateur1` (`Num_utilisateur1`),
  KEY `Num_utilisateur2` (`Num_utilisateur2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `documents`
--

DROP TABLE IF EXISTS `documents`;
CREATE TABLE IF NOT EXISTS `documents` (
  `Numero_doc` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(40) NOT NULL,
  `Lien_document` varchar(1000) NOT NULL,
  `Createur` int(11) NOT NULL,
  `Visibilite` int(11) NOT NULL,
  `Description` varchar(300) NOT NULL,
  PRIMARY KEY (`Numero_doc`),
  KEY `Createur` (`Createur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `favoris`
--

DROP TABLE IF EXISTS `favoris`;
CREATE TABLE IF NOT EXISTS `favoris` (
  `Num_utilisateur` int(11) NOT NULL,
  `Numero_doc` int(11) NOT NULL,
  KEY `Num_utilisateur` (`Num_utilisateur`),
  KEY `Numero_doc` (`Numero_doc`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `groupes`
--

DROP TABLE IF EXISTS `groupes`;
CREATE TABLE IF NOT EXISTS `groupes` (
  `Numero_groupe` int(11) NOT NULL AUTO_INCREMENT,
  `Nom_groupe` varchar(30) NOT NULL,
  `Createur_G` int(11) NOT NULL,
  `Numero_doc` int(11) NOT NULL,
  PRIMARY KEY (`Numero_groupe`),
  KEY `Numero_doc` (`Numero_doc`),
  KEY `Createur_G` (`Createur_G`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `membres`
--

DROP TABLE IF EXISTS `membres`;
CREATE TABLE IF NOT EXISTS `membres` (
  `Numero_groupe` int(11) NOT NULL,
  `Num_utilisateur` int(11) NOT NULL,
  `Droits` int(11) NOT NULL,
  KEY `Numero_groupe` (`Numero_groupe`),
  KEY `Num_utilisateur` (`Num_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `messages`
--

DROP TABLE IF EXISTS `messages`;
CREATE TABLE IF NOT EXISTS `messages` (
  `Numero_doc` int(11) NOT NULL,
  `Id_utilisateur` int(11) NOT NULL,
  `Messages` varchar(3000) NOT NULL,
  `Date` date NOT NULL,
  KEY `Numero_doc` (`Numero_doc`),
  KEY `Id_utilisateur` (`Id_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

DROP TABLE IF EXISTS `utilisateurs`;
CREATE TABLE IF NOT EXISTS `utilisateurs` (
  `Numero` int(11) NOT NULL AUTO_INCREMENT,
  `Pseudo` varchar(20) NOT NULL,
  `Mdp` varchar(20) NOT NULL,
  `Date_inscription` date NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`Numero`),
  KEY `Numero` (`Numero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `amis`
--
ALTER TABLE `amis`
  ADD CONSTRAINT `amis_ibfk_1` FOREIGN KEY (`Num_utilisateur1`) REFERENCES `utilisateurs` (`Numero`),
  ADD CONSTRAINT `amis_ibfk_2` FOREIGN KEY (`Num_utilisateur2`) REFERENCES `utilisateurs` (`Numero`);

--
-- Contraintes pour la table `documents`
--
ALTER TABLE `documents`
  ADD CONSTRAINT `documents_ibfk_1` FOREIGN KEY (`Createur`) REFERENCES `utilisateurs` (`Numero`);

--
-- Contraintes pour la table `favoris`
--
ALTER TABLE `favoris`
  ADD CONSTRAINT `favoris_ibfk_1` FOREIGN KEY (`Num_utilisateur`) REFERENCES `utilisateurs` (`Numero`),
  ADD CONSTRAINT `favoris_ibfk_2` FOREIGN KEY (`Numero_doc`) REFERENCES `documents` (`Numero_doc`);

--
-- Contraintes pour la table `groupes`
--
ALTER TABLE `groupes`
  ADD CONSTRAINT `groupes_ibfk_1` FOREIGN KEY (`Createur_G`) REFERENCES `utilisateurs` (`Numero`);

--
-- Contraintes pour la table `membres`
--
ALTER TABLE `membres`
  ADD CONSTRAINT `membres_ibfk_1` FOREIGN KEY (`Num_utilisateur`) REFERENCES `utilisateurs` (`Numero`),
  ADD CONSTRAINT `membres_ibfk_2` FOREIGN KEY (`Numero_groupe`) REFERENCES `groupes` (`Numero_groupe`);

--
-- Contraintes pour la table `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`Numero_doc`) REFERENCES `documents` (`Numero_doc`),
  ADD CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`Id_utilisateur`) REFERENCES `utilisateurs` (`Numero`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
