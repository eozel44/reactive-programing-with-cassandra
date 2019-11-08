CREATE KEYSPACE IF NOT EXISTS weatherspace WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 1};

USE weatherspace;

CREATE TABLE weather (id UUID, city text,completeDate timestamp,temperature double,PRIMARY KEY (id));

INSERT INTO weather (id, city, completeDate,temperature) VALUES (now(), 'istanbul',toTimestamp(now()),22.05);
INSERT INTO weather (id, city, completeDate,temperature) VALUES (now(), 'ankara',toTimestamp(now()),22.05);
INSERT INTO weather (id, city, completeDate,temperature) VALUES (now(), 'izmir',toTimestamp(now()),24.00);
INSERT INTO weather (id, city, completeDate,temperature) VALUES (now(), 'malatya',toTimestamp(now()),23.00);
INSERT INTO weather (id, city, completeDate,temperature) VALUES (now(), 'istanbul',toTimestamp(now()),22.06);
INSERT INTO weather (id, city, completeDate,temperature) VALUES (now(), 'istanbul',toTimestamp(now()),22.04);