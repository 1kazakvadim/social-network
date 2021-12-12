CREATE TABLE `role`
(
    `id`   int                                                     NOT NULL AUTO_INCREMENT,
    `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8_unicode_ci;

CREATE TABLE `user`
(
    `id`                int                                                     NOT NULL AUTO_INCREMENT,
    `email`             varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `password`          varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `role_id`           int                                                     NOT NULL,
    `locked`            tinyint                                                 NOT NULL,
    `time_registration` datetime                                                NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email_UNIQUE` (`email`),
    KEY `role_id_idx` (`role_id`),
    CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8_unicode_ci;

CREATE TABLE `basic_information`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `firstname`    varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    `lastname`     varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    `birthday`     datetime                                                DEFAULT NULL,
    `gender`       varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL,
    `relationship` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8_unicode_ci;

CREATE TABLE `profile`
(
    `id`                   int NOT NULL AUTO_INCREMENT,
    `user_id`              int                                                     DEFAULT NULL,
    `basic_information_id` int                                                     DEFAULT NULL,
    `job_title`            varchar(45) COLLATE utf8_unicode_ci                     DEFAULT NULL,
    `country`              varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    `city`                 varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    `mobile_phone`         varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL,
    `home_phone`           varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL,
    `github_name`          varchar(45) COLLATE utf8_unicode_ci                     DEFAULT NULL,
    `twitter_name`         varchar(45) COLLATE utf8_unicode_ci                     DEFAULT NULL,
    `instagram_name`       varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL,
    `facebook_name`        varchar(45) COLLATE utf8_unicode_ci                     DEFAULT NULL,
    `skype_name`           varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL,
    `friend_count`         int                                                     DEFAULT NULL,
    `profile_photo_name`   varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_information_id_idx` (`user_id`),
    KEY `basic_information_id_idx` (`basic_information_id`),
    CONSTRAINT `basic_information_id` FOREIGN KEY (`basic_information_id`) REFERENCES `basic_information` (`id`),
    CONSTRAINT `user_table_profile_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8_unicode_ci;

CREATE TABLE `friend`
(
    `user_make_friend_request_id`   int                                                           NOT NULL,
    `user_accept_friend_request_id` int                                                           NOT NULL,
    `friend_status`                 enum ('0','1','2') CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
    `time_creation`                 datetime                                                               DEFAULT NULL,
    PRIMARY KEY (`user_make_friend_request_id`, `user_accept_friend_request_id`),
    KEY `user1_id_idx` (`user_make_friend_request_id`),
    KEY `user2_id_idx` (`user_accept_friend_request_id`),
    CONSTRAINT `user_accept_friend_request_id` FOREIGN KEY (`user_accept_friend_request_id`) REFERENCES `user` (`id`),
    CONSTRAINT `user_make_friend_request_id` FOREIGN KEY (`user_make_friend_request_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8_unicode_ci;

CREATE TABLE `dialog`
(
    `id`            int NOT NULL AUTO_INCREMENT,
    `name`          varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    `time_creation` datetime                                                DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8_unicode_ci;

CREATE TABLE `message`
(
    `id`             int NOT NULL AUTO_INCREMENT,
    `dialog_id`      int                                                     DEFAULT NULL,
    `text`           varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    `user_firstname` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    `user_lastname`  varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    `time_creation`  datetime                                                DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `dialog_id_idx` (`dialog_id`),
    CONSTRAINT `dialog_table_message_id` FOREIGN KEY (`dialog_id`) REFERENCES `dialog` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8_unicode_ci;

CREATE TABLE `users_to_dialogs`
(
    `user_id`   int NOT NULL,
    `dialog_id` int NOT NULL,
    PRIMARY KEY (`user_id`, `dialog_id`),
    KEY `user_dialog_id_idx` (`user_id`),
    KEY `dialog_id_idx` (`dialog_id`),
    CONSTRAINT `dialog_table_id` FOREIGN KEY (`dialog_id`) REFERENCES `dialog` (`id`),
    CONSTRAINT `user_table_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8_unicode_ci;

CREATE TABLE `album`
(
    `id`            int NOT NULL AUTO_INCREMENT,
    `name`          varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    `user_id`       int                                                     DEFAULT NULL,
    `time_creation` datetime                                                DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_id_idx` (`user_id`),
    CONSTRAINT `user_table_album_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8_unicode_ci;

CREATE TABLE `photo`
(
    `id`            int NOT NULL AUTO_INCREMENT,
    `image_name`    varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    `description`   varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    `album_id`      int                                                     DEFAULT NULL,
    `like_count`    int                                                     DEFAULT NULL,
    `time_creation` datetime                                                DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `album_id_idx` (`album_id`),
    CONSTRAINT `album_id` FOREIGN KEY (`album_id`) REFERENCES `album` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8_unicode_ci;

CREATE TABLE `comment`
(
    `id`            int NOT NULL AUTO_INCREMENT,
    `text`          varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    `user_id`       int                                                     DEFAULT NULL,
    `photo_id`      int                                                     DEFAULT NULL,
    `like_count`    int                                                     DEFAULT NULL,
    `time_creation` datetime                                                DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_commet_id_idx` (`user_id`),
    KEY `photo_comment_id_idx` (`photo_id`),
    CONSTRAINT `photo_table_comment_id` FOREIGN KEY (`photo_id`) REFERENCES `photo` (`id`),
    CONSTRAINT `user_table_comment_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8_unicode_ci;

CREATE TABLE `verification_token`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `token`       varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    `user_id`     int                                  DEFAULT NULL,
    `expiry_date` datetime                             DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_table_verification_token_idx` (`user_id`),
    CONSTRAINT `user_table_verification_token` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8_unicode_ci;