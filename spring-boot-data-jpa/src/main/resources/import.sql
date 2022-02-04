insert into Clients(id, name, last_name, email, create_at, photo) values(1, 'Cesar','Lopez','cesar@gmail.com','2020-02-15', '');
insert into Clients(id, name, last_name, email, create_at, photo) values(2, 'Mario','Aguirre','mario@gmail.com','2010-01-17', '');

insert into products(name, price, create_at) values('TV SMART SAMSUNG',1500,NOW());
insert into products(name, price, create_at) values('SAMSUNG GALAXY S20 ULTRA',3000,NOW());
insert into products(name, price, create_at) values('SMART WATCH SAMSUNG',500,NOW());

insert into invoices(description, observation, client_id, create_at)values('Invoice: appliances',null,1,NOW());
insert into invoices_items(amount, product_id, bill_id) values (10,1,1);
insert into invoices_items(amount, product_id, bill_id) values (5,2,1);
insert into invoices_items(amount, product_id, bill_id) values (15,3,1);