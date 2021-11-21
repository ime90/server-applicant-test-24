/**
 * CREATE Script for init of DB
 */

 --

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');


-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (1, now(), false, 'OFFLINE',
'driver01pw', 'driver01');

insert into driver (id, date_created, deleted, online_status, password, username) values (2, now(), false, 'OFFLINE',
'driver02pw', 'driver02');

insert into driver (id, date_created, deleted, online_status, password, username) values (3, now(), false, 'OFFLINE',
'driver03pw', 'driver03');


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (4, now(), false, 'ONLINE',
'driver04pw', 'driver04');

insert into driver (id, date_created, deleted, online_status, password, username) values (5, now(), false, 'ONLINE',
'driver05pw', 'driver05');

insert into driver (id, date_created, deleted, online_status, password, username) values (6, now(), false, 'ONLINE',
'driver06pw', 'driver06');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (7,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (8,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE',
'driver08pw', 'driver08');

-- Create 1 Car

insert into car (id, date_created, license_plate, convertible, seat_count, rating, deleted, engine_type, is_being_used) values
(1, now(), 'MU12333', false, 4, 9, false, 'GAS', true);

insert into car (id, date_created, license_plate, convertible, seat_count, rating, deleted, engine_type, manufacturer_name, manufacturer_model) values
(2, now(), 'BE133GH', false, 4, 9, false, 'GAS', 'BMW', 'M5');

insert into car (id, date_created, license_plate, convertible, seat_count, rating, deleted, engine_type, manufacturer_name, manufacturer_model) values
(3, now(), 'HA1812DD', false, 5, 6, false, 'ELECTRIC', 'Mercedes', 'C class');

insert into car (id, date_created, license_plate, convertible, seat_count, rating, deleted, engine_type, manufacturer_name, manufacturer_model, is_being_used) values
(4, now(), 'HA1444F', false, 5, 9, false, 'DIESEL', 'Audi', 'Q5', true);


-- Create 1 ONLINE driver with car

insert into driver (id, date_created, deleted, online_status, password, username, car_id) values (9, now(), false, 'ONLINE',
                                                                                          'driver09pw', 'driver09', 1);

insert into driver (id, date_created, deleted, online_status, password, username, car_id) values (10, now(), false, 'ONLINE',
                                                                                                  'driver10pw', 'driver10', 4);