ALTER TABLE `social_network`.`basic_information`
    CHANGE COLUMN `gender` `gender` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL;

ALTER TABLE `social_network`.`basic_information`
    ADD FULLTEXT INDEX `full_name_idx` (`firstname`, `lastname`) VISIBLE;

