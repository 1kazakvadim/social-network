CREATE TABLE IF NOT EXISTS `message`
(
    `id`             bigint   NOT NULL AUTO_INCREMENT,
    `message_text`   varchar(255) DEFAULT NULL,
    `message_sender` bigint   NOT NULL,
    `dialog_id`      bigint   NOT NULL,
    `time_creation`  datetime NOT NULL,
    PRIMARY KEY (`id`),
    KEY `message_from` (`message_sender`),
    KEY `dialog_id_idx` (`dialog_id`),
    CONSTRAINT `dialog_id` FOREIGN KEY (`dialog_id`) REFERENCES `dialog` (`id`),
    CONSTRAINT `message_sender_profile_id` FOREIGN KEY (`message_sender`) REFERENCES `profile` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci