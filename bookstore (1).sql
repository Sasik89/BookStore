-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 21, 2024 at 05:46 PM
-- Wersja serwera: 10.4.28-MariaDB
-- Wersja PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bookstore`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tbook`
--

CREATE TABLE `tbook` (
  `id` int(11) NOT NULL,
  `title` varchar(256) NOT NULL,
  `author` varchar(256) NOT NULL,
  `price` decimal(5,2) NOT NULL,
  `quantity` int(3) NOT NULL,
  `isbn` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbook`
--

INSERT INTO `tbook` (`id`, `title`, `author`, `price`, `quantity`, `isbn`) VALUES
(1, 'Java EE 6. Tworzenie aplikacji w NetBeans 7', 'David R. Heffelfinger', 59.00, 4, '978-83-246-8936-1'),
(2, 'Java. Rusz głową! Wydanie III', 'Kathy Sierra, Bert Bates, Trisha Gee', 95.00, 5, '978-83-283-9984-6'),
(4, 'Java. Przewodnik dla początkujących. Wydanie VIII', 'Herbert Schildt', 61.00, 6, '978-83-283-9118-5'),
(5, 'Java. Efektywne programowanie. Wydanie III', 'Joshua Bloch', 60.00, 6, '978-83-283-9896-2'),
(7, 'Struktury danych i algorytmy w języku Java. Przewodnik dla początkujących.', 'Cutajar James', 25.29, 50, '978-83-283-5330-5');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `torder`
--

CREATE TABLE `torder` (
  `id` int(11) NOT NULL,
  `status` varchar(20) NOT NULL,
  `total` decimal(6,2) NOT NULL,
  `dateTime` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `torder`
--

INSERT INTO `torder` (`id`, `status`, `total`, `dateTime`, `user_id`) VALUES
(8, 'NEW', 154.00, '2024-01-11 23:22:28', 2),
(9, 'NEW', 120.00, '2024-01-11 23:22:40', 2),
(10, 'NEW', 239.00, '2024-01-11 23:28:39', 3),
(11, 'NEW', 214.00, '2024-01-11 23:32:58', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `torderposition`
--

CREATE TABLE `torderposition` (
  `id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `torderposition`
--

INSERT INTO `torderposition` (`id`, `quantity`, `order_id`, `book_id`) VALUES
(10, 1, 10, 1),
(11, 3, 10, 5),
(12, 1, 11, 1),
(13, 1, 11, 2),
(14, 1, 11, 5);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tuser`
--

CREATE TABLE `tuser` (
  `id` int(11) NOT NULL,
  `login` varchar(40) NOT NULL,
  `password` varchar(33) NOT NULL,
  `name` varchar(20) NOT NULL,
  `surname` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tuser`
--

INSERT INTO `tuser` (`id`, `login`, `password`, `name`, `surname`, `email`, `role`) VALUES
(1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'Karol', 'Krawczyk', 'karolkrawczyk@gmail.com', 'ADMIN'),
(2, 'janusz', '087d9c5e13bdd64a82bef8e013625c32', 'Janusz', 'Kowalski', 'januszkowalski@gmail.com', 'USER'),
(3, 'sebastian', 'c2d628ba98ed491776c9335e988e2e3b', 'Sebastian', 'Sas', 'basteksas@poczta.fm', 'USER');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `tbook`
--
ALTER TABLE `tbook`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `torder`
--
ALTER TABLE `torder`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeksy dla tabeli `torderposition`
--
ALTER TABLE `torderposition`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indeksy dla tabeli `tuser`
--
ALTER TABLE `tuser`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbook`
--
ALTER TABLE `tbook`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `torder`
--
ALTER TABLE `torder`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `torderposition`
--
ALTER TABLE `torderposition`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `tuser`
--
ALTER TABLE `tuser`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
