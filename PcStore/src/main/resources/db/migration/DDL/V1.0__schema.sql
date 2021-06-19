create table usuario (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 clave varchar(45) not null,
 fecha_creacion datetime null,
 primary key (id)
);

create table component(
    id bigint not null auto_increment,
    name varchar(100) not null,
    type varchar(20) not null,
    price decimal(20, 2) not null,
    primary key(id)
);

create table buyer_data(
    id bigint not null auto_increment,
    name varchar(100) not null,
    id_number int not null,
    email varchar(100) not null,
    phone_number varchar(10) not null,
    address varchar(100) not null,
    primary key(id)
);

create table t_order(
    id bigint not null auto_increment,
    build_service boolean not null default 0,
    placement_date datetime not null,
    shipping_date date not null,
    delivered_date date not null,
    status varchar(10) not null,
    tracking_code varchar(100) not null,
    buyer_data_id bigint not null,
    order_price decimal(20, 2) not null,
    primary key(id),
    foreign key(buyer_data_id) references buyer_data(id)
);

create table order_component(
    order_id bigint not null,
    component_id bigint not null,
    primary key(order_id, component_id),
    foreign key(order_id) references t_order(id),
    foreign key(component_id) references component(id)
);
