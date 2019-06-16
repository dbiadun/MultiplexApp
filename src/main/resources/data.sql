DELETE FROM screenings;
DELETE FROM screening_rooms;
DELETE FROM movies;
DELETE FROM tickets;
DELETE FROM reservations;

INSERT INTO movies VALUES(0, "Avengers: Koniec Gry");
INSERT INTO movies VALUES(1, "Green Book");
INSERT INTO movies VALUES(2, "Smętarz dla zwierzaków");

INSERT INTO screening_rooms VALUES(1, "[20, 20, 30, 30, 30, 30, 30, 22, 22, 22, 22, 22, 30, 30, 30, 30]");
INSERT INTO screening_rooms VALUES(2, "[20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20]");
INSERT INTO screening_rooms VALUES(3, "[4, 8, 16, 16, 16, 16, 16, 16, 16]");

INSERT INTO screenings VALUES(0, "2019-05-25T15:00", 0, 1);
INSERT INTO screenings VALUES(1, "2019-05-25T14:00", 1, 3);
INSERT INTO screenings VALUES(2, "2019-05-25T13:00", 0, 2);
INSERT INTO screenings VALUES(3, "2019-05-25T10:00", 0, 1);
INSERT INTO screenings VALUES(4, "2019-05-25T19:00", 2, 3);
INSERT INTO screenings VALUES(5, "2019-05-26T13:00", 1, 2);
