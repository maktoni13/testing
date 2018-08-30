INSERT INTO `testing_db`.`user` (`username`, `password`, `email`, `first_name`, `first_name_ua`, `last_name`, `last_name_ua`, `enabled_flag`, `admin_flag`) VALUES ('admin', 'admin', 'qnicky.13@gmail.com', 'Anton', 'Антон', 'Makuhin', 'Макухін', '1', '1');
INSERT INTO `testing_db`.`user` (`username`, `password`, `email`, `first_name`, `first_name_ua`, `last_name`, `last_name_ua`, `enabled_flag`, `admin_flag`) VALUES ('user', 'user', 'maktoni.13@gmail.com', 'Toni', 'Тоні', 'Mak', 'Мак', '1', '0');
INSERT INTO `testing_db`.`theme` (`description`, `description_ua`) VALUES ('Java Core', 'Java Core');
INSERT INTO `testing_db`.`test` (`description`, `description_ua`, `theme_id`, `inactive`) VALUES ('Beginner level', 'Рівень новенького', '1', '0');
INSERT INTO `testing_db`.`question` (`description`, `description_ua`, `test_id`) VALUES ('How to call in Java symbol with code 514?', 'Как записать в Java-символ с кодом 514?', '1');
INSERT INTO `testing_db`.`answer` (`description`, `description_ua`, `correct_flag`, `question_id`) VALUES ('\'514\'', '\'514\'', '0', '1');
INSERT INTO `testing_db`.`answer` (`description`, `description_ua`, `correct_flag`, `question_id`) VALUES ('\'\\u0514\'', '\'\\u0514\'', '0', '1');
INSERT INTO `testing_db`.`answer` (`description`, `description_ua`, `correct_flag`, `question_id`) VALUES ('\'\\u0202\'', '\'\\u0202\'', '1', '1');
