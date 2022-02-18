insert into Clients(id, name, last_name, email, create_at, photo) values(1, 'Cesar','Lopez','cesar@gmail.com','2020-02-15', '');
insert into Clients(id, name, last_name, email, create_at, photo) values(2, 'Mario','Aguirre','mario@gmail.com','2010-01-17', '');

insert into products(name, price, create_at) values('TV SMART SAMSUNG',1500,NOW());
insert into products(name, price, create_at) values('SAMSUNG GALAXY S20 ULTRA',3000,NOW());
insert into products(name, price, create_at) values('SMART WATCH SAMSUNG',500,NOW());

insert into invoices(description, observation, client_id, create_at)values('Invoice: appliances',null,1,NOW());
insert into invoices_items(amount, product_id, bill_id) values (10,1,1);
insert into invoices_items(amount, product_id, bill_id) values (5,2,1);
insert into invoices_items(amount, product_id, bill_id) values (15,3,1);

insert into users(username, password, enabled) values('cesar','$2a$10$EwK1zPvQ9MUVX5BRZoLqce.tRoMJjlBBIeuWB8/CK/qMSxpE/CBpO',1);
insert into users(username, password, enabled) values('admin','$2a$10$/HOfzh0s626CxK.UM6P/V.LkiKqZcT974/qaDOyMzDBUxDn3eV1Pi',1);

insert into authorities (user_id, authority) values(1, "ROLE_USER");
insert into authorities (user_id, authority) values(2, "ROLE_USER");
insert into authorities (user_id, authority) values(2, "ROLE_ADMIN");