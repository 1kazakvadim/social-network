# password: GMgH4/8>
INSERT INTO `social_network`.`user` (`id`, `email`, `password`, `role_id`, `locked`)
VALUES ('1', 'admin@gmail.com', '$2a$10$IG.XYKMHcpgxPhYZgInQZuCBXzsrDt5XFdU8PmcZLq25TeGmxgxZK', '2',
        '0');
INSERT INTO `social_network`.`basic_information` (`id`, `firstname`, `lastname`, `birthday`, `gender`)
VALUES ('1', 'Admin', 'Admin', '1990-01-01 00:00:00', 'MALE');
INSERT INTO `social_network`.`profile` (`id`, `user_id`, `basic_information_id`,
                                        `profile_photo_name`, `time_registration`, `update_time`)
VALUES ('1', '1', '1', 'default-profile-photo', '2022-02-27 19:28:05', '2022-02-27 19:28:05');

# password: !q5aWEtw
INSERT INTO `social_network`.`user` (`id`, `email`, `password`, `role_id`, `locked`)
VALUES ('2', 'aleksandr-ivanov@gmail.com',
        '$2a$10$rOCjXFm8EZ93O2an5QJ7hOFzmHkwcZRcA7K/RCqM.2maw8p093vbm', '1',
        '0');
INSERT INTO `social_network`.`basic_information` (`id`, `firstname`, `lastname`, `birthday`, `gender`)
VALUES ('2', 'Александр', 'Иванов', '1993-01-02 00:00:00', 'NOT_APPLICABLE');
INSERT INTO `social_network`.`profile` (`id`, `user_id`, `basic_information_id`, `job_title`,
                                        `country_id`, `city`, `mobile_phone`, `home_phone`,
                                        `github_name`, `twitter_name`, `instagram_name`,
                                        `facebook_name`, `skype_name`, `profile_photo_name`,
                                        `time_registration`, `update_time`)
VALUES ('2', '2', '2', 'Photographer', '31', '', '+375297568492', '', '', '',
        'https://instagram.com/aleksivanovphoto', '', '', '5043d8f2-829e-423c-a88b-77b4642191c9',
        '2022-02-27 19:34:27', '2022-02-27 19:43:36');
INSERT INTO `social_network`.`photo` (`id`, `name`, `profile_id`, `time_creation`)
VALUES ('1', 'abc04092-d93d-4024-bbbb-dcee1005841d', '2', '2022-02-27 19:40:05');
INSERT INTO `social_network`.`photo` (`id`, `name`, `profile_id`, `time_creation`)
VALUES ('2', '266c1314-f145-456b-b193-43d3decd32e2', '2', '2022-02-27 19:40:14');
INSERT INTO `social_network`.`photo` (`id`, `name`, `profile_id`, `time_creation`)
VALUES ('3', 'a432a6ff-3f7b-4c2c-91cf-794d9ffdd3f8', '2', '2022-02-27 19:40:19');
INSERT INTO `social_network`.`photo` (`id`, `name`, `profile_id`, `time_creation`)
VALUES ('4', 'd962005b-e465-4077-be3e-9f79e6a1b51e', '2', '2022-02-27 19:40:25');

# password: 3DRb}24!
INSERT INTO `social_network`.`user` (`id`, `email`, `password`, `role_id`, `locked`)
VALUES ('3', 'nianastya@gmail.com',
        '$2a$10$9Fk3mRmGQGszXMgfpolaguqN.1/ZskPc2vL64BByIV/PjuoGbx3Sa', '1',
        '0');
INSERT INTO `social_network`.`basic_information` (`id`, `firstname`, `lastname`, `birthday`, `gender`)
VALUES ('3', 'Анастасия', 'Петровская', '1994-07-06 00:00:00', 'FEMALE');
INSERT INTO `social_network`.`profile` (`id`, `user_id`, `basic_information_id`, `job_title`,
                                        `country_id`, `city`, `mobile_phone`, `home_phone`,
                                        `github_name`, `twitter_name`, `instagram_name`,
                                        `facebook_name`, `skype_name`, `profile_photo_name`,
                                        `time_registration`, `update_time`)
VALUES ('3', '3', '3', 'Model', '29', '', '+375291649571', '', '', '',
        'https://instagram.com/nianastya', '', '', '86662fcc-ecbb-44d4-99f2-366f19d118ad',
        '2022-02-27 19:34:27', '2022-02-27 19:43:36');
INSERT INTO `social_network`.`photo` (`id`, `name`, `profile_id`, `time_creation`)
VALUES ('5', '31c465e7-af48-4d32-9a5f-9490ad04e568', '3', '2022-02-27 19:57:37');
INSERT INTO `social_network`.`photo` (`id`, `name`, `profile_id`, `time_creation`)
VALUES ('6', '13add3c2-78fd-4aab-ae31-d32a7f6493a9', '3', '2022-02-27 19:57:43');
INSERT INTO `social_network`.`photo` (`id`, `name`, `profile_id`, `time_creation`)
VALUES ('7', 'be944c26-0312-41e3-b501-0331e46fea49', '3', '2022-02-27 19:57:47');
INSERT INTO `social_network`.`photo` (`id`, `name`, `profile_id`, `time_creation`)
VALUES ('8', '9af28f83-2f9d-40b9-becf-df271cb987e7', '3', '2022-02-27 19:57:53');
INSERT INTO `social_network`.`photo` (`id`, `name`, `profile_id`, `time_creation`)
VALUES ('9', '8f1c175e-f8e0-4ae2-9e71-2d0094f4d1ed', '3', '2022-02-27 19:57:59');
INSERT INTO `social_network`.`photo` (`id`, `name`, `profile_id`, `time_creation`)
VALUES ('10', '42b72ff8-2d82-4391-98d5-934d0a5e67c3', '3', '2022-02-27 19:58:07');
INSERT INTO `social_network`.`photo` (`id`, `name`, `profile_id`, `time_creation`)
VALUES ('11', 'd2b625bb-63b9-4e2b-b0aa-93370caf44a9', '3', '2022-02-27 19:58:14');
INSERT INTO `social_network`.`photo` (`id`, `name`, `profile_id`, `time_creation`)
VALUES ('12', '42ef04b6-3624-4003-b05b-807add998eda', '3', '2022-02-27 19:58:25');
INSERT INTO `social_network`.`photo` (`id`, `name`, `profile_id`, `time_creation`)
VALUES ('13', 'eea3347c-f341-43bb-845d-f6fe658c17de', '3', '2022-02-27 19:58:30');
INSERT INTO `social_network`.`photo` (`id`, `name`, `profile_id`, `time_creation`)
VALUES ('14', 'c56b9166-c2f7-46d7-8501-823efe69172b', '3', '2022-02-27 19:58:36');
INSERT INTO `social_network`.`friend` (`profile_make_friend_request_id`,
                                       `profile_accept_friend_request_id`, `friend_status`,
                                       `time_creation`)
VALUES ('3', '2', 'IN_FRIEND', '2022-02-27 20:00:26');

#password: P9E_p+VM
INSERT INTO `social_network`.`user` (`id`, `email`, `password`, `role_id`, `locked`)
VALUES ('4', 'popko.e@gmail.com',
        '$2a$10$2GeKgMAixBU0zldVvt/Z3.apMk9hIr73lAQ/VDU1Yez1Mt0nWXuOu', '1',
        '0');
INSERT INTO `social_network`.`basic_information` (`id`, `firstname`, `lastname`, `birthday`, `gender`)
VALUES ('4', 'Егор', 'Попко', '1989-03-06 00:00:00', 'NOT_KNOWN');
INSERT INTO `social_network`.`profile` (`id`, `user_id`, `basic_information_id`, `job_title`,
                                        `country_id`, `city`, `mobile_phone`, `home_phone`,
                                        `github_name`, `twitter_name`, `instagram_name`,
                                        `facebook_name`, `skype_name`, `profile_photo_name`,
                                        `time_registration`, `update_time`)
VALUES ('4', '4', '4', 'Administrator', '12', '', '+375335424312', '', '', '',
        'https://instagram.com/popko', '', '', '328bf4f4-c386-4bd3-830a-ed93deabd75f',
        '2022-02-27 19:34:27', '2022-02-27 19:43:36');
INSERT INTO `social_network`.`photo` (`id`, `name`, `profile_id`, `time_creation`) VALUES ('15', '9257167c-29c0-4390-9663-915017d2ff48', '4', '2022-02-27 20:10:34');
INSERT INTO `social_network`.`photo` (`id`, `name`, `profile_id`, `time_creation`) VALUES ('16', '8b36744f-b50e-4ca3-b8aa-a480ffe17a9c', '4', '2022-02-27 20:10:45');
INSERT INTO `social_network`.`photo` (`id`, `name`, `profile_id`, `time_creation`) VALUES ('17', '8691857b-c830-4bd7-af64-d3389ff30c31', '4', '2022-02-27 20:10:50');
INSERT INTO `social_network`.`friend` (`profile_make_friend_request_id`, `profile_accept_friend_request_id`, `friend_status`, `time_creation`) VALUES ('4', '2', 'IN_FRIEND', '2022-02-27 20:11:57');
INSERT INTO `social_network`.`friend` (`profile_make_friend_request_id`, `profile_accept_friend_request_id`, `friend_status`, `time_creation`) VALUES ('4', '3', 'IN_FRIEND', '2022-02-27 20:11:55');


