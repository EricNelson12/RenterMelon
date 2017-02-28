/* 

SCHEMA:

	Rental(rID, title, price, desc, area, address, link, addDate)

	Contact(rID, name, phone, email)
	FK rID ref Rental(rID)

	Users(username, pword, email, name, admin)

	Report(desc, type, time, rID, username)
	FK rID ref Rental(rID), FK username ref Users(username)

	SavedAds(rID, username)
	FK rID ref Rental(rID), FK username ref Users(username)




*/ 




CREATE TABLE rental (

	rID INTEGER AUTO_INCREMENT, 
	title VARCHAR(50) NOT NULL,
	price DECIMAL(7,2) NOT NULL,
	description VARCHAR(400),
	area VARCHAR(20),
	address VARCHAR(50),
	link VARCHAR(200),
	datedAdded DATETIME DEFAULT CURRENT_TIMESTAMP, 
	PRIMARY KEY (rID)




);

CREATE TABLE contact (
	
	rID INTEGER,
	name VARCHAR(30),
	phone VARCHAR(15),
	email VARCHAR(30),
	PRIMARY KEY (rID, name),
	FOREIGN KEY (rID) REFERENCES rental(rID)
		ON DELETE CASCADE 
		ON UPDATE CASCADE 

);


CREATE TABLE users (

	username VARCHAR(20),
	password VARCHAR(20) NOT NULL,
	email VARCHAR(30) NOT NULL,
	isAdmin BOOLEAN DEFAULT FALSE,
	PRIMARY KEY (username)

);

/*
		Below here not used in release 1 but they seemed easy enough to have in here. 
*/


CREATE TABLE savedAds ( 

	username VARCHAR(20),
	rID INTEGER,
	PRIMARY KEY (username,rID),
	FOREIGN KEY (rID) REFERENCES rental(rID)
		ON DELETE CASCADE 
		ON UPDATE CASCADE, 
	FOREIGN KEY (username) REFERENCES users(username)
		ON DELETE CASCADE 
		ON UPDATE CASCADE 


);

CREATE TABLE report ( 
	
	username VARCHAR(20),
	rID INTEGER,
	timeAdded DATETIME DEFAULT CURRENT_TIMESTAMP,
	reportType VARCHAR(25),
	description VARCHAR(100),
	PRIMARY KEY (username, timeAdded),
	FOREIGN KEY (rID) REFERENCES rental(rID)
		ON DELETE CASCADE 
		ON UPDATE CASCADE, 
	FOREIGN KEY (username) REFERENCES users(username)
		ON DELETE CASCADE 
		ON UPDATE CASCADE 

);
