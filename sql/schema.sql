create table orders (id bigint not null, number varchar(255), totalPrice numeric(38,2), primary key (id));
alter table if exists orders drop constraint if exists UKsft0o3ihy2jyuq8rth4o136j7;
alter table if exists orders add constraint UKsft0o3ihy2jyuq8rth4o136j7 unique (number);
create sequence orders_SEQ start with 1 increment by 50;