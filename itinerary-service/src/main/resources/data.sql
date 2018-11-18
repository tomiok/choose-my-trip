insert into city (id, name, code) values (15, 'Rosario', 'ROS'),
 (16, 'New York City', 'NYC');

insert into itinerary
  (id, departure_city, destiny_city, departure_time, arrival_time, num_of_stops)
  values (10, 15, 16, '2018-01-19 02:10:00', '2018-01-20 03:40:00', 0);

insert into itinerary
  (id, departure_city, destiny_city, departure_time, arrival_time, num_of_stops)
  values (11, 15, 16, '2018-01-19 02:10:00', '2018-01-20 04:55:00', 1);

insert into itinerary
(id, departure_city, destiny_city, departure_time, arrival_time, num_of_stops)
  values (12, 15, 16, '2018-01-19 02:10:00', '2018-01-20 06:30:00', 2);