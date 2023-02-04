
    create table roles (
       id integer not null auto_increment,
        name varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table transfer_statements (
       id bigint not null auto_increment,
        completed bit,
        fee float,
        fee_paid bit,
        text varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table users (
       id bigint not null auto_increment,
        enabled bit,
        password varchar(255),
        username varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table users_roles (
       user_id bigint not null,
        role_id integer not null,
        primary key (user_id, role_id)
    ) engine=InnoDB;

    create table user_transfer_statements (
       accepted varchar(255),
        user_role varchar(255),
        user_id bigint not null,
        transfer_statement_id bigint not null,
        primary key (transfer_statement_id, user_id)
    ) engine=InnoDB;

    alter table users_roles 
       add constraint FKj6m8fwv7oqv74fcehir1a9ffy 
       foreign key (role_id) 
       references roles (id);

    alter table users_roles 
       add constraint FK2o0jvgh89lemvvo17cbqvdxaa 
       foreign key (user_id) 
       references users (id);

    alter table user_transfer_statements 
       add constraint FKorpiaumv481c1pv40esfoeob4 
       foreign key (user_id) 
       references users (id);

    alter table user_transfer_statements 
       add constraint FK2w06a2e0sbrafpgrcnykpbchg 
       foreign key (transfer_statement_id) 
       references transfer_statements (id);

INSERT INTO `roles` (`name`) VALUES ('USER');
INSERT INTO `roles` (`name`) VALUES ('NOTARY');
INSERT INTO `roles` (`name`) VALUES ('ADMIN');

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('seller', '$2a$10$cTUErxQqYVyU2qmQGIktpup5chLEdhD2zpzNEyYqmxrHHJbSNDOG.', 1);
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('buyer', '$2a$10$.tP2OH3dEG0zms7vek4ated5AiQ.EGkncii0OpCcGq4bckS9NOULu', 1);
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('notary', '$2a$10$E2UPv7arXmp3q0LzVzCBNeb4B4AtbTAGjkefVDnSztOwE7Gix6kea', 1);
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('admin', '$2a$10$IqTJTjn39IU5.7sSCDQxzu3xug6z/LPU6IF0azE/8CkHCwYEnwBX.', 1);

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 1); -- user seller has role USER
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (2, 1); -- user buyer has role USER
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (3, 2); -- user notary has role NOTARY
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (4, 3); -- user admin has role ADMIN

INSERT INTO `transfer_statements` (`completed`, `fee`, `fee_paid`, `text`) VALUES (0, 1.5, 0, 'Text1');
INSERT INTO `transfer_statements` (`completed`, `fee`, `fee_paid`, `text`) VALUES (0, 2.3, 1, 'Text2');
INSERT INTO `transfer_statements` (`completed`, `fee`, `fee_paid`, `text`) VALUES (1, 3.3, 1, 'Text3');

insert into `user_transfer_statements` (`accepted`, `user_role`, `user_id`, `transfer_statement_id`) VALUES (0, 'ROLE_SELLER', 1, 1);
insert into `user_transfer_statements` (`accepted`, `user_role`, `user_id`, `transfer_statement_id`) VALUES (0, 'ROLE_BUYER', 2, 1);
insert into `user_transfer_statements` (`accepted`, `user_role`, `user_id`, `transfer_statement_id`) VALUES (0, 'ROLE_NOTARY', 3, 1);