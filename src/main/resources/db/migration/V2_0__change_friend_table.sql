ALTER TABLE `social_network`.`friend`
    CHANGE COLUMN `friend_status` `friend_status` ENUM ('NON_FRIEND', 'FRIEND_REQUEST', 'IN_FRIEND') NOT NULL DEFAULT 'NON_FRIEND';

ALTER TABLE `social_network`.`friend`
    DROP FOREIGN KEY `user_accept_friend_request_id`,
    DROP FOREIGN KEY `user_make_friend_request_id`;

ALTER TABLE `social_network`.`friend`
    CHANGE COLUMN `user_make_friend_request_id` `profile_make_friend_request_id` BIGINT NOT NULL,
    CHANGE COLUMN `user_accept_friend_request_id` `profile_accept_friend_request_id` BIGINT NOT NULL;

ALTER TABLE `social_network`.`friend`
    ADD CONSTRAINT `profile_accept_friend_request_id`
        FOREIGN KEY (`profile_accept_friend_request_id`)
            REFERENCES `social_network`.`profile` (`id`),
    ADD CONSTRAINT `profile_make_friend_request_id`
        FOREIGN KEY (`profile_make_friend_request_id`)
            REFERENCES `social_network`.`profile` (`id`);

ALTER TABLE `social_network`.`friend` RENAME INDEX `user1_id_idx` TO `profile_make_friend_request_id_idx`;
ALTER TABLE `social_network`.`friend`
    ALTER INDEX `profile_make_friend_request_id_idx` VISIBLE;
ALTER TABLE `social_network`.`friend` RENAME INDEX `user2_id_idx` TO `profile_accept_friend_request_id_idx`;
ALTER TABLE `social_network`.`friend`
    ALTER INDEX `profile_accept_friend_request_id_idx` VISIBLE;



