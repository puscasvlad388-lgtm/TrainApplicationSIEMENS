INSERT INTO stations (name) VALUES ('Bucuresti Nord');
INSERT INTO stations (name) VALUES ('Brasov');
INSERT INTO stations (name) VALUES ('Constanta');

INSERT INTO trains (name, capacity, is_delayed) VALUES ('InterRegio 1581', 100, false);
INSERT INTO trains (name, capacity, is_delayed) VALUES ('Regio 3001', 50, false);

INSERT INTO routes (name) VALUES ('Bucuresti - Brasov');
INSERT INTO route_stations (route_id, station_id) VALUES (1, 1);
INSERT INTO route_stations (route_id, station_id) VALUES (1, 2);

INSERT INTO schedules (train_id, route_id, departure_time, available_seats, version)
VALUES (1, 1, '2026-06-01 10:00:00', 100, 0);

INSERT INTO users (email, password, role)
VALUES ('admin@train.com', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'ADMIN');

INSERT INTO users (email, password, role)
VALUES ('user@train.com', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'USER');