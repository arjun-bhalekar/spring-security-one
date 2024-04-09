---password is - test1234

insert into user_account(username, password, roles)
values('Admin', '$2a$12$8Ds62A0B6dZOhcMuNv.ZoeXF/fK98dwCXrCicG/IVF6lRzwxAj5xq', 'ROLE_ADMIN');

insert into user_account(username, password, roles)
values('John', '$2a$12$8Ds62A0B6dZOhcMuNv.ZoeXF/fK98dwCXrCicG/IVF6lRzwxAj5xq', 'ROLE_USER');
