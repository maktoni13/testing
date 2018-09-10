INSERT INTO `testing_db`.`user` (`username`, `password`, `email`, `first_name`, `first_name_ua`, `last_name`, `last_name_ua`, `enabled_flag`, `admin_flag`)
VALUES ('admin', '$s0$e0801$2P4j8fpZnrHAKjuKM9cdyw==$ZojJeRuZaeBC/PGE1L7AXQHWdHAzRLg62GsEnVvxBcs=', 'qnicky.13@gmail.com', 'Anton', 'Антон', 'Makuhin', 'Макухін', '1', '1');

INSERT INTO `testing_db`.`user` (`username`, `password`, `email`, `first_name`, `first_name_ua`, `last_name`, `last_name_ua`, `enabled_flag`, `admin_flag`)
VALUES ('user', '$s0$e0801$6vKHD+4ekaIUW00YKuLVLQ==$TaHbMa63jfK4s4Lfhnnw7Bu0X7dHiRJAgBtsT9H7O+g=', 'maktoni.13@gmail.com', 'Toni', 'Тоні', 'Mak', 'Мак', '1', '0');

INSERT INTO `testing_db`.`user` (`username`, `password`, `email`, `first_name`, `first_name_ua`, `last_name`, `last_name_ua`, `enabled_flag`, `admin_flag`)
VALUES ('ivanivanov', '$s0$e0801$gWPnoSPLpFEHmtGfVqi4/A==$Htd3jmxyniTRMd91v4NqtfeHt1p+JhPO2EraWQdqoBo=', 'ivanivanov@gmail.com', 'Ivan', 'Іван', 'Ivanov', 'Іванов', '1', '0');

INSERT INTO `testing_db`.`theme` (`name`, `name_ua`, `description`, `description_ua`) VALUES ('Java Core', 'Вивчення Java Core', 'Java Core', 'Вивчення Java Core');
INSERT INTO `testing_db`.`test` (`name`, `name_ua`, `description`, `description_ua`, `theme_id`, `inactive`) VALUES ('Beginner level', 'Рівень новенького', 'Beginner level', 'Рівень новенького', '1', '0');

INSERT INTO `testing_db`.`question` (`id_local`, `description`, `description_ua`, `test_id`) VALUES (1, 'How to call in Java symbol with code 514?', 'Как записать в Java-символ с кодом 514?', '1');
INSERT INTO `testing_db`.`answer` (`id_local`, `description`, `description_ua`, `correct_flag`, `question_id`) VALUES (1, '\'514\'', '\'514\'', '0', '1');
INSERT INTO `testing_db`.`answer` (`id_local`, `description`, `description_ua`, `correct_flag`, `question_id`) VALUES (2, '\'\\u0514\'', '\'\\u0514\'', '0', '1');
INSERT INTO `testing_db`.`answer` (`id_local`, `description`, `description_ua`, `correct_flag`, `question_id`) VALUES (3, '\'\\u0202\'', '\'\\u0202\'', '1', '1');
