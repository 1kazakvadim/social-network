CREATE TABLE IF NOT EXISTS `role`
(
    `id`   bigint       NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `user`
(
    `id`       bigint       NOT NULL AUTO_INCREMENT,
    `email`    varchar(255) NOT NULL,
    `password` varchar(100) NOT NULL,
    `role_id`  bigint       NOT NULL,
    `locked`   tinyint      NOT NULL
);

CREATE TABLE IF NOT EXISTS `relationship`
(
    `id`   bigint      NOT NULL AUTO_INCREMENT,
    `name` varchar(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS `token`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT,
    `token`       varchar(255) NOT NULL,
    `user_id`     bigint       NOT NULL,
    `expiry_date` datetime     NOT NULL
);

CREATE TABLE IF NOT EXISTS `basic_information`
(
    `id`              bigint       NOT NULL AUTO_INCREMENT,
    `firstname`       varchar(255) NOT NULL,
    `lastname`        varchar(255) NOT NULL,
    `birthday`        datetime     NOT NULL,
    `gender`          varchar(45)  NOT NULL,
    `relationship_id` bigint DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `country`
(
    `id`       bigint       NOT NULL AUTO_INCREMENT,
    `name`     varchar(100) NOT NULL,
    `iso_code` varchar(45)  NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `profile`
(
    `id`                   bigint   NOT NULL AUTO_INCREMENT,
    `user_id`              bigint   NOT NULL,
    `basic_information_id` bigint   NOT NULL,
    `job_title`            varchar(45)  DEFAULT NULL,
    `country_id`           bigint       DEFAULT NULL,
    `city`                 varchar(255) DEFAULT NULL,
    `mobile_phone`         varchar(45)  DEFAULT NULL,
    `home_phone`           varchar(45)  DEFAULT NULL,
    `github_name`          varchar(45)  DEFAULT NULL,
    `twitter_name`         varchar(45)  DEFAULT NULL,
    `instagram_name`       varchar(45)  DEFAULT NULL,
    `facebook_name`        varchar(45)  DEFAULT NULL,
    `skype_name`           varchar(45)  DEFAULT NULL,
    `profile_photo_name`   varchar(255) DEFAULT NULL,
    `time_registration`    datetime NOT NULL,
    `update_time`          datetime NOT NULL
);

CREATE TABLE IF NOT EXISTS `friend`
(
    `profile_make_friend_request_id`   bigint                                             NOT NULL,
    `profile_accept_friend_request_id` bigint                                             NOT NULL,
    `friend_status`                    enum ('NON_FRIEND', 'FRIEND_REQUEST', 'IN_FRIEND') NOT NULL,
    `time_creation`                    datetime                                           NOT NULL
);

CREATE TABLE IF NOT EXISTS `photo`
(
    `id`            bigint       NOT NULL AUTO_INCREMENT,
    `name`          varchar(255) NOT NULL,
    `profile_id`    bigint       NOT NULL,
    `description`   varchar(255) DEFAULT NULL,
    `time_creation` datetime     NOT NULL
);

CREATE TABLE IF NOT EXISTS `comment`
(
    `id`            bigint       NOT NULL AUTO_INCREMENT,
    `text`          varchar(255) NOT NULL,
    `profile_id`    bigint DEFAULT NULL,
    `photo_id`      bigint DEFAULT NULL,
    `time_creation` datetime     NOT NULL
);


CREATE TABLE IF NOT EXISTS `dialog`
(
    `id`                   bigint   NOT NULL AUTO_INCREMENT,
    `sender_profile_id`    bigint   NOT NULL,
    `recipient_profile_id` bigint   NOT NULL,
    `time_creation`        datetime NOT NULL
);

CREATE TABLE IF NOT EXISTS `message`
(
    `id`             bigint   NOT NULL AUTO_INCREMENT,
    `dialog_id`      bigint   NOT NULL,
    `message_text`   varchar(255) DEFAULT NULL,
    `message_sender` bigint   NOT NULL,
    `time_creation`  datetime NOT NULL
);

