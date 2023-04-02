-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Апр 02 2023 г., 23:17
-- Версия сервера: 8.0.24
-- Версия PHP: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `school`
--

-- --------------------------------------------------------

--
-- Структура таблицы `class`
--

CREATE TABLE `class` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Структура таблицы `classes`
--

CREATE TABLE `classes` (
  `id` int UNSIGNED NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `classes`
--

INSERT INTO `classes` (`id`, `name`) VALUES
(1, '6'),
(2, '7'),
(3, '8'),
(4, '9'),
(5, '10'),
(6, '11');

-- --------------------------------------------------------

--
-- Структура таблицы `classes_seq`
--

CREATE TABLE `classes_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `classes_seq`
--

INSERT INTO `classes_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `class_seq`
--

CREATE TABLE `class_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `class_seq`
--

INSERT INTO `class_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Дублирующая структура для представления `journal`
-- (См. Ниже фактическое представление)
--
CREATE TABLE `journal` (
`surname` varchar(32)
,`name` varchar(32)
,`patronymic` varchar(32)
,`subject` varchar(255)
,`score` int unsigned
);

-- --------------------------------------------------------

--
-- Структура таблицы `marks`
--

CREATE TABLE `marks` (
  `id` int UNSIGNED NOT NULL,
  `id_student` int UNSIGNED NOT NULL,
  `id_subject` int UNSIGNED NOT NULL,
  `score` int UNSIGNED NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `marks`
--

INSERT INTO `marks` (`id`, `id_student`, `id_subject`, `score`, `date`) VALUES
(1, 1, 2, 7, '2020-09-20'),
(3, 4, 1, 10, '2001-09-20');

-- --------------------------------------------------------

--
-- Структура таблицы `students`
--

CREATE TABLE `students` (
  `id` int UNSIGNED NOT NULL,
  `surname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `patronymic` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `students`
--

INSERT INTO `students` (`id`, `surname`, `name`, `patronymic`, `email`) VALUES
(1, 'Жуков', 'Денис', 'Владимирович', NULL),
(2, 'Глушенок', 'Анна', 'Александровна', NULL),
(3, 'Макеев', 'Михаил', 'Андреевич', NULL),
(4, 'Кобельчук', 'Андрей', 'Сергеевич', NULL),
(5, 'Горбанёв', 'Роман', 'Витальевич', NULL),
(6, 'Шевкунова', 'Виолетта', 'Сергеевна', NULL),
(9, 'е', 'е', 'е', NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `students_seq`
--

CREATE TABLE `students_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `students_seq`
--

INSERT INTO `students_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `subject`
--

CREATE TABLE `subject` (
  `id` int NOT NULL,
  `alt` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Структура таблицы `subjects`
--

CREATE TABLE `subjects` (
  `id` int UNSIGNED NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `alt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `subjects`
--

INSERT INTO `subjects` (`id`, `name`, `path`, `alt`) VALUES
(1, 'Биология', '/images/biology_logo.svg', 'biology'),
(2, 'Химия', '/images/chemistry_logo.svg', 'chemistry');

-- --------------------------------------------------------

--
-- Структура таблицы `subjects_seq`
--

CREATE TABLE `subjects_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `subjects_seq`
--

INSERT INTO `subjects_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `subject_seq`
--

CREATE TABLE `subject_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `subject_seq`
--

INSERT INTO `subject_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура для представления `journal`
--
DROP TABLE IF EXISTS `journal`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`127.0.0.1` SQL SECURITY DEFINER VIEW `journal`  AS SELECT `students`.`surname` AS `surname`, `students`.`name` AS `name`, `students`.`patronymic` AS `patronymic`, `subjects`.`name` AS `subject`, `marks`.`score` AS `score` FROM ((`marks` join `students` on((`students`.`id` = `marks`.`id_student`))) join `subjects` on((`subjects`.`id` = `marks`.`id_subject`))) ;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `classes`
--
ALTER TABLE `classes`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `marks`
--
ALTER TABLE `marks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_student` (`id_student`),
  ADD KEY `id_subject` (`id_subject`);

--
-- Индексы таблицы `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `classes`
--
ALTER TABLE `classes`
  MODIFY `id` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT для таблицы `marks`
--
ALTER TABLE `marks`
  MODIFY `id` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT для таблицы `students`
--
ALTER TABLE `students`
  MODIFY `id` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT для таблицы `subjects`
--
ALTER TABLE `subjects`
  MODIFY `id` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `marks`
--
ALTER TABLE `marks`
  ADD CONSTRAINT `marks_ibfk_1` FOREIGN KEY (`id_student`) REFERENCES `students` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `marks_ibfk_2` FOREIGN KEY (`id_subject`) REFERENCES `subjects` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
