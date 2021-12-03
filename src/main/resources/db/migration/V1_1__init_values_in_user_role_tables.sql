INSERT INTO `social_network`.`role` (`id`, `name`) VALUES ('1', 'ROLE_USER');
INSERT INTO `social_network`.`role` (`id`, `name`) VALUES ('2', 'ROLE_ADMIN');

INSERT INTO `social_network`.`user` (`id`, `email`, `password`, `role_id`, `locked`, `time_registration`) VALUES ('1', 'test1@email.com', 'test_password1', '1', '0', '2021-01-01 23:23:00');
INSERT INTO `social_network`.`user` (`id`, `email`, `password`, `role_id`, `locked`, `time_registration`) VALUES ('2', 'test2@email.com', 'test_password2', '2', '0', '2021-01-01 23:23:00');
INSERT INTO `social_network`.`user` (`id`, `email`, `password`, `role_id`, `locked`, `time_registration`) VALUES ('3', 'test3@email.com', 'test_password3', '1', '1', '2021-01-01 23:23:00');
