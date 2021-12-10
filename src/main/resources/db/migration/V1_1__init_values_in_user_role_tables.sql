INSERT INTO `social_network`.`role` (`id`, `name`) VALUES ('1', 'USER');
INSERT INTO `social_network`.`role` (`id`, `name`) VALUES ('2', 'ADMIN');

INSERT INTO `social_network`.`user` (`id`, `email`, `password`, `role_id`, `locked`, `time_registration`) VALUES ('1', 'admin', '$2a$10$qkvOI97twvl9Q/tDZwA16O30Xj7pl7Yeu.bmgzO8KNfNOknj5g3lC', '2', '0', '2021-01-01 23:23:00');
INSERT INTO `social_network`.`user` (`id`, `email`, `password`, `role_id`, `locked`, `time_registration`) VALUES ('2', 'user', '$2a$12$XflvxeAQi7VBQ.BXsr0Fu.cSG67m7t/Xc36u7F8yxzx9Sc1kY9dw.', '1', '0', '2021-01-01 23:23:00');
