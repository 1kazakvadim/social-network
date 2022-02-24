ALTER TABLE `social_network`.`message`
DROP
FOREIGN KEY `dialog_id`;
ALTER TABLE `social_network`.`message`
    ADD CONSTRAINT `dialog_id`
        FOREIGN KEY (`dialog_id`)
            REFERENCES `social_network`.`dialog` (`id`)
            ON DELETE CASCADE;
