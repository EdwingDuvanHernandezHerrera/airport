CREATE DATABASE airport;
USE airport;

CREATE TABLE documenttype(
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(40) NOT NULL,
    CONSTRAINT pk_documenttypes PRIMARY KEY(id)
) ENGINE=InnoDB;

CREATE TABLE customer (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(30) NOT NULL,
    lastName VARCHAR(20) NOT NULL,
    age INT NOT NULL,
    idDocumentc INT(11) NOT NULL,
    CONSTRAINT pk_customers PRIMARY KEY(id),
    CONSTRAINT fk_customers_documenttypes FOREIGN KEY (idDocumentc) REFERENCES documenttype(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;
ALTER TABLE customer ADD COLUMN nroIdc INT(11) NOT NULL AFTER lastName;


CREATE TABLE flightfare (
    id INT AUTO_INCREMENT NOT NULL,
    description VARCHAR(20) NOT NULL,
    details TEXT NULL,
    value DOUBLE(7,3) NOT NULL,
    CONSTRAINT pk_flightfares PRIMARY KEY(id)
) ENGINE=InnoDB;

CREATE TABLE airline (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(30) NOT NULL,
    CONSTRAINT pk_airlines PRIMARY KEY(id)
) ENGINE=InnoDB;

CREATE TABLE tripulationroles (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(40) NOT NULL,
    CONSTRAINT pk_tripulationroles PRIMARY KEY(id)
) ENGINE=InnoDB;

CREATE TABLE country (
    id VARCHAR(5) NOT NULL,
    name VARCHAR(30) NOT NULL,
    CONSTRAINT pk_countries PRIMARY KEY(id)
) ENGINE=InnoDB;

CREATE TABLE city (
    id VARCHAR(5) NOT NULL,
    name VARCHAR(30) NOT NULL,
    idCountry VARCHAR(5) NOT NULL,
    CONSTRAINT pk_cities PRIMARY KEY(id),
    CONSTRAINT fk_cities_countries FOREIGN KEY (idCountry) REFERENCES country(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE manufacturer (
     id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(40) NOT NULL,
    CONSTRAINT pk_manufacturers PRIMARY KEY(id)
) ENGINE=InnoDB;

CREATE TABLE statusA (
     id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(30) NOT NULL,
    CONSTRAINT pk_statuses PRIMARY KEY(id)
) ENGINE=InnoDB;

CREATE TABLE model (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(30) NOT NULL,
    manufacturerId INT NOT NULL,
    CONSTRAINT pk_models PRIMARY KEY(id),
    CONSTRAINT fk_models_manufacturers FOREIGN KEY (manufacturerId) REFERENCES manufacturer(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE airport (
    id VARCHAR(5) NOT NULL,
    name VARCHAR(30) NOT NULL,
    idCity VARCHAR(5) NOT NULL,
    CONSTRAINT pk_airports PRIMARY KEY(id),
    CONSTRAINT fk_airports_cities FOREIGN KEY (idCity) REFERENCES city(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE gate (
    id INT AUTO_INCREMENT NOT NULL,
    gateNumber VARCHAR(10) NOT NULL,
    idAirport VARCHAR(5) NOT NULL,
    CONSTRAINT pk_gates PRIMARY KEY(id),
    CONSTRAINT fk_gates_airports FOREIGN KEY (idAirport) REFERENCES airport(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE trip (
    id INT AUTO_INCREMENT NOT NULL,
    tripDate DATE NOT NULL,
    priceTrip DOUBLE NOT NULL,
    idOrigin VARCHAR(5) NOT NULL,
    idDestination VARCHAR(5) NOT NULL,
    CONSTRAINT pk_trips PRIMARY KEY(id),
    CONSTRAINT pk_origin_airport FOREIGN KEY (idOrigin) REFERENCES airport(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT pk_destination_airport FOREIGN KEY (idDestination) REFERENCES airport(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE tripbooking (
    id INT AUTO_INCREMENT NOT NULL,
    date DATE NOT NULL,
    idTrip INT NOT NULL,
    CONSTRAINT pk_tripbooking PRIMARY KEY(id),
    CONSTRAINT fk_tripbooking_trips FOREIGN KEY (idTrip) REFERENCES trip(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE tripbookingdetail (
    id INT AUTO_INCREMENT NOT NULL,
    idTripBooking INT NOT NULL,
    idCustomers INT(11) NOT NULL,
    idFares INT NOT NULL,
    CONSTRAINT pk_tripbookingdetails PRIMARY KEY(id),
    CONSTRAINT fk_tripbookingdetails_tripbooking FOREIGN KEY (idTripBooking) REFERENCES tripbooking(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_tripbookingdetails_customers FOREIGN KEY (idCustomers) REFERENCES customer(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_tripbookingdetails_fares FOREIGN KEY (idFares) REFERENCES flightfare(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;
ALTER TABLE tripbookingdetail
ADD COLUMN status ENUM('active', 'cancelled') NOT NULL DEFAULT 'active';

CREATE TABLE passenger(
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(30) NOT NULL, 
    lastName VARCHAR(20) NOT NULL,
    nroId INT(11) NOT NULL,
    age INT(11) NOT NULL,
    seat INT(3) NOT NULL,
    idDocument INT(11) NOT NULL,
    idTripBookingDetails INT(11) NOT NULL,
    CONSTRAINT pk_passenger  PRIMARY KEY(id),
    CONSTRAINT fk_passenger_docu FOREIGN KEY (idDocument) REFERENCES documenttype(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_passenger_tripbookingdetail FOREIGN KEY(idTripBookingDetails) REFERENCES tripbookingdetail(id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB;

CREATE TABLE plane (
    id INT AUTO_INCREMENT NOT NULL,
    plates VARCHAR(30) NOT NULL,
    capacity INT NOT NULL,
    fabricationDate DATE NOT NULL,
    idAirline INT NOT NULL,
    idStatus INT NOT NULL,
    idModel INT NOT NULL,
    CONSTRAINT pk_planes PRIMARY KEY(id),
    CONSTRAINT fk_planes_airline FOREIGN KEY (idAirline) REFERENCES airline(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_planes_status FOREIGN KEY (idStatus) REFERENCES statusA(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_planes_models FOREIGN KEY (idModel) REFERENCES model(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE flightconnection (
    id INT AUTO_INCREMENT NOT NULL,
    connectionNumber VARCHAR(20) NOT NULL,
    idTrip INT NOT NULL,
    idPlane INT NOT NULL,
    idAirport VARCHAR(5) NOT NULL,
    CONSTRAINT pk_flight_connections PRIMARY KEY(id),
    CONSTRAINT fk_flight_connections_trips FOREIGN KEY (idTrip) REFERENCES trip(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_flight_connections_planes FOREIGN KEY (idPlane) REFERENCES plane(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_flight_connections_airports FOREIGN KEY (idAirport) REFERENCES airport(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE revision (
    id INT AUTO_INCREMENT NOT NULL,
    revisionDate DATE NOT NULL,
    idPlane INT NOT NULL,
    description TEXT NOT NULL,
    CONSTRAINT pk_revisions PRIMARY KEY(id),
    CONSTRAINT fk_revisions_planes FOREIGN KEY (idPlane) REFERENCES plane(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE employee (
    id VARCHAR(20) NOT NULL,
    name VARCHAR(40) NOT NULL,
    ingressDate DATE NOT NULL,
    idRol INT NOT NULL,
    idAirline INT NOT NULL,
    idAirport VARCHAR(5) NOT NULL,
    CONSTRAINT pk_employees PRIMARY KEY(id),
    CONSTRAINT fk_employees_roles FOREIGN KEY (idRol) REFERENCES tripulationroles(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_employees_airlines FOREIGN KEY (idAirline) REFERENCES airline(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_employees_airports FOREIGN KEY (idAirport) REFERENCES airport(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE revemployee (
    idRevision INT NOT NULL,
    idEmployee VARCHAR(20) NOT NULL,
    CONSTRAINT pk_revemployee PRIMARY KEY (idEmployee, idRevision),
    CONSTRAINT fk_revemployee_employees FOREIGN KEY (idEmployee) REFERENCES employee(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_revemployee_revisions FOREIGN KEY (idRevision) REFERENCES revision(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE tripcrew (
    idEmployees VARCHAR(20) NOT NULL,
    idConection INT NOT NULL,
    CONSTRAINT fk_tripcrews_employees FOREIGN KEY (idEmployees) REFERENCES employee(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_tripcrews_connections FOREIGN KEY (idConection) REFERENCES flightconnection(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

