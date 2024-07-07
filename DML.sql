
INSERT INTO country VALUES ('col', 'Colombia');
INSERT INTO country VALUES ('USA', 'Estados Unidos');


INSERT INTO city VALUES ('BOG', 'Bogotá', 'col');
INSERT INTO city VALUES ('BGA', 'Bucaramanga', 'col');
INSERT INTO city VALUES ('CLO', 'Cali', 'col');
INSERT INTO city VALUES ('CTG', 'Cartagena', 'col');
INSERT INTO city VALUES ('BAQ', 'Barranquilla', 'col');
INSERT INTO city VALUES ('MDE', 'Medellín', 'col');
INSERT INTO city VALUES ('PEI', 'Pereira', 'col');
INSERT INTO city VALUES ('MZL', 'Manizales', 'col');
INSERT INTO city VALUES ('CUC', 'Cúcuta', 'col');
INSERT INTO city VALUES ('SMR', 'Santa Marta', 'col');
INSERT INTO city VALUES ('PSO', 'Pasto', 'col');
INSERT INTO city VALUES ('VVC', 'Villavicencio', 'col');
INSERT INTO city VALUES ('AXM', 'Armenia', 'col');
INSERT INTO city VALUES ('NVA', 'Neiva', 'col');
INSERT INTO city VALUES ('MTR', 'Montería', 'col');
INSERT INTO city VALUES ('RCH', 'Riohacha', 'col');
INSERT INTO city VALUES ('ADZ', 'San Andrés', 'col');
INSERT INTO city VALUES ('LET', 'Leticia', 'col');



INSERT INTO airport VALUES (1, 'El Dorado', 'BOG');
INSERT INTO airport VALUES (2, 'Palonegro', 'BGA');
INSERT INTO airport VALUES (3, 'Alfonso Bonilla Aragón', 'CLO');
INSERT INTO airport VALUES (4, 'Rafael Núñez', 'CTG');
INSERT INTO airport VALUES (5, 'Ernesto Cortissoz', 'BAQ');
INSERT INTO airport VALUES (6, 'José María Córdova', 'MDE');
INSERT INTO airport VALUES (7, 'Matecaña', 'PEI');
INSERT INTO airport VALUES (8, 'La Nubia', 'MZL');
INSERT INTO airport VALUES (9, 'Camilo Daza', 'CUC');
INSERT INTO airport VALUES (10, 'Simón Bolívar', 'SMR');
INSERT INTO airport VALUES (11, 'Antonio Nariño', 'PSO');
INSERT INTO airport VALUES (12, 'Vanguardia', 'VVC');
INSERT INTO airport VALUES (13, 'El Edén', 'AXM');
INSERT INTO airport VALUES (14, 'Benito Salas', 'NVA');
INSERT INTO airport VALUES (15, 'Los Garzones', 'MTR');
INSERT INTO airport VALUES (16, 'Almirante Padilla', 'RCH');
INSERT INTO airport VALUES (17, 'Gustavo Rojas Pinilla', 'ADZ');
INSERT INTO airport VALUES (18, 'Alfredo Vásquez Cobo', 'LET');

INSERT INTO tripulationroles VALUES (1,'Piloto');
INSERT INTO tripulationroles VALUES (2,'Copiloto');
INSERT INTO tripulationroles VALUES (3,'Auxiliar de vuelo');
INSERT INTO tripulationroles VALUES (4,'Mantenimiento');

INSERT INTO airline VALUES (1,'Avianca');
INSERT INTO airline VALUES (2,'Latam');
INSERT INTO airline VALUES (3,'Wingo');

INSERT INTO employee VALUES (1,'Pedro Páramo','2000-12-31', 1, 1, 1);
INSERT INTO employee VALUES (2,'Rafael Escalante','2005-06-23', 2, 1, 1);
INSERT INTO employee VALUES (3,'Andrea Perdomo','2013-01-01', 3, 1, 1);
INSERT INTO employee VALUES (4,'Nicolás Espinoza','2020-04-21', 3, 1, 1);
INSERT INTO employee VALUES (5, 'María García', '2020-05-15', 1, 2, 2);
INSERT INTO employee VALUES (6, 'Juan Pérez', '2020-06-10', 2, 2, 2);
INSERT INTO employee VALUES (7, 'Ana Martínez', '2020-07-05', 3, 2, 2);
INSERT INTO employee VALUES (8, 'Carlos Gómez', '2020-08-20', 3, 2, 2);
INSERT INTO employee VALUES (9, 'Laura Rodríguez', '2020-09-12', 1,3, 3);
INSERT INTO employee VALUES (10, 'Pedro Sánchez', '2020-10-18', 2, 3, 3);
INSERT INTO employee VALUES (11, 'Luisa Herrera', '2020-11-25', 3, 3, 3);
INSERT INTO employee VALUES (12, 'Diego López', '2020-12-01', 3, 3, 3);
INSERT INTO employee VALUES (13, 'Valentina Castro', '2021-01-07', 1, 1, 4);
INSERT INTO employee VALUES (14, 'Andrés Ramírez', '2021-02-14', 2, 1, 4);
INSERT INTO employee VALUES (15, 'Gabriela Vargas', '2021-03-20', 3, 1, 4);
INSERT INTO employee VALUES (16, 'Jorge Rojas', '2021-04-25', 3, 1, 4);
INSERT INTO employee VALUES (17, 'Andrés Franco', '2023-07-20', 4, 1, 1);


INSERT INTO manufacturer VALUES (1,'Airbus');
INSERT INTO manufacturer VALUES (2,'Boeing');

INSERT INTO model VALUES (1,'A320',1);
INSERT INTO model VALUES (2,'A330',1);
INSERT INTO model VALUES (3,'737',2);
INSERT INTO model VALUES (4,'787',2);

INSERT INTO statusA VALUES (1,'En vuelo');
INSERT INTO statusA VALUES (2,'Disponible');
INSERT INTO statusA VALUES (3,'En reparación');

INSERT INTO plane VALUES (1,'HK-4546',150,'2001-11-05',1,1,1);
INSERT INTO plane VALUES (2,'HK-7890',277,'2011-08-25',1,1,2);
INSERT INTO plane VALUES (3,'HK-2312',162,'2015-07-20',2,1,3);
INSERT INTO plane VALUES (4,'HK-5678',150,'2020-02-02',2,1,1);
INSERT INTO plane VALUES (5,'HK-1234',218,'2015-03-17',3,1,4);
INSERT INTO plane VALUES (6,'HK-8901',277,'2017-05-09',3,1,2);

INSERT INTO revision VALUES (1,'2024-06-01',1,'Revision del motor');

INSERT INTO revemployee VALUES (1,17);

INSERT INTO trip VALUES (1,'2024-06-21',200000,2,4);
INSERT INTO trip VALUES (2,'2024-06-01',250000,2,3);

INSERT INTO flightconnection VALUES (1,'AA123',1,1,1);
INSERT INTO flightconnection VALUES (2,'UA789',1,2,4);
INSERT INTO flightconnection VALUES (3,'AA123',2,3,1);
INSERT INTO flightconnection VALUES (4,'AA123',2,4,3);

INSERT INTO tripcrew VALUES (1,1), (2,1), (3,1), (4,1);
INSERT INTO tripcrew VALUES (5,2), (6,2), (7,2), (8,2);

INSERT INTO documenttype VALUES (1,'Cédula');
INSERT INTO documenttype VALUES (2,'Tarjeta de identidad');
INSERT INTO documenttype VALUES (3,'Pasaporte');
INSERT INTO documenttype VALUES (4,'Cédula extranjera');

INSERT INTO customer VALUES (1,'Alexis', 'Chacón', 1234567890, 23, 1);
INSERT INTO customer VALUES (2,'Santiago', 'Gómez', 23456789, 32, 1);
INSERT INTO customer VALUES (3,'Ana', 'Pérez', 34567890, 50, 1);
INSERT INTO customer VALUES (4,'Valentina', 'Sánchez', 45678901, 13, 2);

INSERT INTO flightfare (id, description, details, value) VALUES (1, 'Economy', 'Economy class fare', 100.000);
INSERT INTO flightfare VALUES (2,'Básico', 'Equipaje de mano + cabina 10kg', 1000);
INSERT INTO flightfare VALUES (3,'Básico', 'Equipaje de mano + cabina 10kg + bodega 23kg', 1500);

INSERT INTO tripbooking VALUES (1,'2024-05-12', 1);

INSERT INTO tripbookingdetail VALUES (1, 1, 1, 1, 'active');

INSERT INTO passenger VALUES (1, 'Andrés','Alfonso',10056782, 41, 5, 1, 1);


