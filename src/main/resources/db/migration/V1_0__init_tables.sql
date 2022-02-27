CREATE TABLE IF NOT EXISTS `role`
(
    `id`   bigint                                                        NOT NULL AUTO_INCREMENT,
    `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `user`
(
    `id`       bigint                                                        NOT NULL AUTO_INCREMENT,
    `email`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `role_id`  bigint                                                        NOT NULL,
    `locked`   tinyint                                                       NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email_UNIQUE` (`email`),
    KEY `role_id_idx` (`role_id`),
    CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `relationship`
(
    `id`   bigint                                 NOT NULL AUTO_INCREMENT,
    `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `basic_information`
(
    `id`              bigint                                                        NOT NULL AUTO_INCREMENT,
    `firstname`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `lastname`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `birthday`        datetime                                                      NOT NULL,
    `gender`          varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `relationship_id` bigint                                                       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `relationship_id_idx` (`relationship_id`),
    CONSTRAINT `relationship_id` FOREIGN KEY (`relationship_id`) REFERENCES `relationship` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `country`
(
    `id`       bigint                                                       NOT NULL AUTO_INCREMENT,
    `name`     varchar(100) COLLATE utf8mb4_unicode_ci                      NOT NULL,
    `iso_code` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `name_UNIQUE` (`name`),
    UNIQUE KEY `iso_code_UNIQUE` (`iso_code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `profile`
(
    `id`                   bigint   NOT NULL AUTO_INCREMENT,
    `user_id`              bigint   NOT NULL,
    `basic_information_id` bigint   NOT NULL,
    `job_title`            varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL,
    `country_id`           bigint                                                        DEFAULT NULL,
    `city`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `mobile_phone`         varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL,
    `home_phone`           varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL,
    `github_name`          varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL,
    `twitter_name`         varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL,
    `instagram_name`       varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL,
    `facebook_name`        varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL,
    `skype_name`           varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL,
    `friend_count`         bigint                                                        DEFAULT '0',
    `profile_photo_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `time_registration`    datetime NOT NULL,
    `update_time`          datetime NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_id_UNIQUE` (`user_id`),
    UNIQUE KEY `basic_information_id_UNIQUE` (`basic_information_id`),
    KEY `user_information_id_idx` (`user_id`),
    KEY `basic_information_id_idx` (`basic_information_id`),
    KEY `country_id_idx` (`country_id`),
    CONSTRAINT `basic_information_id` FOREIGN KEY (`basic_information_id`) REFERENCES `basic_information` (`id`),
    CONSTRAINT `country_id` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`),
    CONSTRAINT `user_table_profile_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `friend`
(
    `user_make_friend_request_id`   bigint                                        NOT NULL,
    `user_accept_friend_request_id` bigint                                        NOT NULL,
    `friend_status`                 enum ('0','1','2') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0',
    `time_creation`                 datetime                                      NOT NULL DEFAULT NULL,
    PRIMARY KEY (`user_make_friend_request_id`, `user_accept_friend_request_id`),
    KEY `user1_id_idx` (`user_make_friend_request_id`),
    KEY `user2_id_idx` (`user_accept_friend_request_id`),
    CONSTRAINT `user_accept_friend_request_id` FOREIGN KEY (`user_accept_friend_request_id`) REFERENCES `user` (`id`),
    CONSTRAINT `user_make_friend_request_id` FOREIGN KEY (`user_make_friend_request_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `dialog`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `name`          varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `time_creation` datetime                                DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `users_to_dialogs`
(
    `user_id`   bigint NOT NULL,
    `dialog_id` bigint NOT NULL,
    PRIMARY KEY (`user_id`, `dialog_id`),
    KEY `user_dialog_id_idx` (`user_id`),
    KEY `dialog_id_idx` (`dialog_id`),
    CONSTRAINT `dialog_table_id` FOREIGN KEY (`dialog_id`) REFERENCES `dialog` (`id`),
    CONSTRAINT `user_table_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `album`
(
    `id`            bigint                                                        NOT NULL AUTO_INCREMENT,
    `name`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `description`   varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `time_creation` datetime                                                      NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `photo`
(
    `id`            bigint                                                        NOT NULL AUTO_INCREMENT,
    `name`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `profile_id`    bigint                                                        NOT NULL,
    `description`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `like_count`    bigint                                                        DEFAULT '0',
    `time_creation` datetime                                                      NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `image_name_UNIQUE` (`name`),
    KEY `profile_table_photo_id_idx` (`profile_id`),
    CONSTRAINT `profile_table_photo_id` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `comment`
(
    `id`            bigint                                                        NOT NULL AUTO_INCREMENT,
    `text`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `profile_id`    bigint                                                                 DEFAULT NULL,
    `photo_id`      bigint                                                                 DEFAULT NULL,
    `like_count`    bigint                                                        NOT NULL DEFAULT '0',
    `time_creation` datetime                                                      NOT NULL,
    PRIMARY KEY (`id`),
    KEY `photo_comment_id_idx` (`photo_id`),
    KEY `profile_table_comment_id_idx` (`profile_id`),
    CONSTRAINT `photo_table_comment_id` FOREIGN KEY (`photo_id`) REFERENCES `photo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `profile_table_comment_id` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `token`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT,
    `token`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `user_id`     bigint                                                        NOT NULL,
    `expiry_date` datetime                                                      NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `token_UNIQUE` (`token`),
    KEY `user_table_verification_token_idx` (`user_id`),
    CONSTRAINT `user_table_verification_token` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
