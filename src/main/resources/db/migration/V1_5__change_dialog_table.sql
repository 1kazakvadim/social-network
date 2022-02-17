ALTER TABLE `social_network`.`dialog`
    DROP COLUMN `name`,
    ADD COLUMN `sender_profile_id`    BIGINT(10) NOT NULL AFTER `id`,
    ADD COLUMN `recipient_profile_id` BIGINT(10) NOT NULL AFTER `sender_profile_id`,
    CHANGE COLUMN `time_creation` `time_creation` DATETIME NOT NULL,
    ADD INDEX `recipient_profile_id_idx` (`recipient_profile_id` ASC) VISIBLE,
    ADD INDEX `sender_profile_id_idx` (`sender_profile_id` ASC) VISIBLE;
;
ALTER TABLE `social_network`.`dialog`
    ADD CONSTRAINT `recipient_profile_id`
        FOREIGN KEY (`recipient_profile_id`)
            REFERENCES `social_network`.`profile` (`id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    ADD CONSTRAINT `sender_profile_id`
        FOREIGN KEY (`sender_profile_id`)
            REFERENCES `social_network`.`profile` (`id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT;