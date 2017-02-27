CREATE DATABASE IF NOT EXISTS test
  COLLATE utf8_general_ci;

USE test;

DROP TABLE IF EXISTS user;
CREATE TABLE USER (
  id           INT         NOT NULL UNIQUE AUTO_INCREMENT,
  name         VARCHAR(25) NOT NULL UNIQUE,
  age          INT(3)      NOT NULL,
  is_admin     BIT         NOT NULL,
  created_date TIMESTAMP   NOT NULL        DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

DROP PROCEDURE IF EXISTS load_user_test_data;

DELIMITER #
CREATE PROCEDURE load_user_test_data()
  BEGIN

    DECLARE v_max INT UNSIGNED DEFAULT 16;
    DECLARE v_counter INT UNSIGNED DEFAULT 0;

    TRUNCATE TABLE user;
    START TRANSACTION;
    WHILE v_counter < v_max DO
      INSERT INTO user (name, age, is_admin, created_date) VALUES (
        CONCAT('name', v_counter)
        , FLOOR(RAND() * 120) + 1
        , FLOOR(RAND() * 1.3)
        , (SELECT from_unixtime(unix_timestamp('2000-01-01') + floor(rand() * 510000000))));
      SET v_counter = v_counter + 1;
    END WHILE;
    COMMIT;
  END #

DELIMITER ;

CALL load_user_test_data();