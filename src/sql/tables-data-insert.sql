INSERT INTO `testing_db`.`user` (`username`, `password`, `email`, `first_name`, `first_name_ua`, `last_name`, `last_name_ua`, `enabled_flag`, `admin_flag`)
VALUES ('admin', '$s0$e0801$2P4j8fpZnrHAKjuKM9cdyw==$ZojJeRuZaeBC/PGE1L7AXQHWdHAzRLg62GsEnVvxBcs=', 'qnicky.13@gmail.com', 'Anton', 'Антон', 'Makuhin', 'Макухін', '1', '1');

INSERT INTO `testing_db`.`user` (`username`, `password`, `email`, `first_name`, `first_name_ua`, `last_name`, `last_name_ua`, `enabled_flag`, `admin_flag`)
VALUES ('user', '$s0$e0801$6vKHD+4ekaIUW00YKuLVLQ==$TaHbMa63jfK4s4Lfhnnw7Bu0X7dHiRJAgBtsT9H7O+g=', 'maktoni.13@gmail.com', 'Toni', 'Тоні', 'Mak', 'Мак', '1', '0');

INSERT INTO `testing_db`.`user` (`username`, `password`, `email`, `first_name`, `first_name_ua`, `last_name`, `last_name_ua`, `enabled_flag`, `admin_flag`)
VALUES ('ivanivanov', '$s0$e0801$gWPnoSPLpFEHmtGfVqi4/A==$Htd3jmxyniTRMd91v4NqtfeHt1p+JhPO2EraWQdqoBo=', 'ivanivanov@gmail.com', 'Ivan', 'Іван', 'Ivanov', 'Іванов', '1', '0');

INSERT INTO `testing_db`.`theme` (`name`, `name_ua`, `description`, `description_ua`) VALUES ('Java Core', 'Вивчення Java Core', 'Java Core', 'Вивчення Java Core');
INSERT INTO `testing_db`.`test` (`name`, `name_ua`, `description`, `description_ua`, `theme_id`, `inactive`) VALUES ('Beginner level', 'Рівень новенького', 'Beginner level', 'Рівень новенького', '1', '0');

INSERT INTO `testing_db`.`question` (`id_local`, `description`, `description_ua`, `test_id`) VALUES (1, 'How to call in Java symbol with code 514?', 'Як записати в Java-символ із кодом 514?', '1');
INSERT INTO `testing_db`.`question` (`id_local`, `description`, `description_ua`, `test_id`) VALUES (1, 'How to call in Java symbol with code 514?', 'Як записати в Java-символ із кодом 514?', '1');
INSERT INTO `testing_db`.`question` (`id_local`, `description`, `description_ua`, `test_id`) VALUES (1, 'How to call in Java symbol with code 514?', 'Як записати в Java-символ із кодом 514?', '1');
INSERT INTO `testing_db`.`answer` (`id_local`, `description`, `description_ua`, `correct_flag`, `question_id`) VALUES (1, '\'514\'', '\'514\'', '0', '1');
INSERT INTO `testing_db`.`answer` (`id_local`, `description`, `description_ua`, `correct_flag`, `question_id`) VALUES (2, '\'\\u0514\'', '\'\\u0514\'', '0', '1');
INSERT INTO `testing_db`.`answer` (`id_local`, `description`, `description_ua`, `correct_flag`, `question_id`) VALUES (3, '\'\\u202\'', '\'\\u202\'', '0', '1');
INSERT INTO `testing_db`.`answer` (`id_local`, `description`, `description_ua`, `correct_flag`, `question_id`) VALUES (4, '\'\\u2202\'', '\'\\u0202\'', '1', '1');
INSERT INTO `testing_db`.`answer` (`id_local`, `description`, `description_ua`, `correct_flag`, `question_id`) VALUES (1, '\'514\'', '\'514\'', '0', '1');
INSERT INTO `testing_db`.`answer` (`id_local`, `description`, `description_ua`, `correct_flag`, `question_id`) VALUES (2, '\'\\u0514\'', '\'\\u0514\'', '0', '1');
INSERT INTO `testing_db`.`answer` (`id_local`, `description`, `description_ua`, `correct_flag`, `question_id`) VALUES (3, '\'\\u202\'', '\'\\u202\'', '0', '1');
INSERT INTO `testing_db`.`answer` (`id_local`, `description`, `description_ua`, `correct_flag`, `question_id`) VALUES (4, '\'\\u2202\'', '\'\\u0202\'', '1', '1');
INSERT INTO `testing_db`.`answer` (`id_local`, `description`, `description_ua`, `correct_flag`, `question_id`) VALUES (1, '1\\n2', '1\\n2', '0', '10');
INSERT INTO `testing_db`.`answer` (`id_local`, `description`, `description_ua`, `correct_flag`, `question_id`) VALUES (2, '2\\n1', '2\\n1', '0', '10');
INSERT INTO `testing_db`.`answer` (`id_local`, `description`, `description_ua`, `correct_flag`, `question_id`) VALUES (3, '1\\n1+1', '1\\n1+1', '0', '10');
INSERT INTO `testing_db`.`answer` (`id_local`, `description`, `description_ua`, `correct_flag`, `question_id`) VALUES (4, 'Compilation error', 'Помилка компіляціі', '1', '10');
INSERT INTO `testing_db`.`answer` (`id_local`, `description`, `description_ua`, `correct_flag`, `question_id`) VALUES (1, 'Yes. 1: Compilation error 2: v1=1;v2=3', 'Так. 1: Помилка компіляціі 2: v1=1;v2=3', '0', '11');
INSERT INTO `testing_db`.`answer` (`id_local`, `description`, `description_ua`, `correct_flag`, `question_id`) VALUES (2, 'Yes. 1: Compilation error 2: v1=3;v2=2', 'Так. 1: Помилка компіляціі 2: v1=3;v2=2', '1', '11');
INSERT INTO `testing_db`.`answer` (`id_local`, `description`, `description_ua`, `correct_flag`, `question_id`) VALUES (3, 'No. 1: v1=1;v2=3 2: v1=1;v2=3', 'Ні. 1: v1=1;v2=3 2: v1=1;v2=3', '0', '11');
INSERT INTO `testing_db`.`answer` (`id_local`, `description`, `description_ua`, `correct_flag`, `question_id`) VALUES (4, 'No. 1: v1=3;v2=2 2: v1=3;v2=2', 'Ні. 1: v1=3;v2=2 2: v1=3;v2=2', '0', '11');
