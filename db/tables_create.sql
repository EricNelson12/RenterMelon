/* 
Rental(rID, title, price, desc, area, address, link, addDate)

Contact(rID, name, phone, email)
FK rID ref Rental(rID)

Users(username, pword, email, name, admin)

Report(desc, type, time, rID, username)
FK rID ref Rental(rID), FK username ref Users(username)

SavedAds(rID, username)
FK rID ref Rental(rID), FK username ref Users(username)


This isn't finished yet yall

*/ 



CREATE TABLE Rental (

	rID INTEGER AUTO_INCREMENT, 
	title VARCHAR(50),
	price DECIMAL(7,2),
	description VARCHAR(400),
	area VARCHAR(20),
	address VARCHAR(50),
	link VARCHAR(200),
	datedAdded DATETIME DEFAULT CURRENT_TIMESTAMP, --I couldn't get this to run with just the date. 
	PRIMARY KEY (rID)




);

CREATE TABLE contact (
	
	rID INTEGER,
	name VARCHAR(30),
	phone VARCHAR(15),
	email VARCHAR(30),
	PRIMARY KEY (rID, name),
	FOREIGN KEY (rID) REFERENCES Rental(rID)
		ON DELETE CASCADE 
		ON UPDATE CASCADE 

);


CREATE TABLE Users (

	username VARCHAR(20),
	password VARCHAR(20) NOT NULL,
	email VARCHAR(30) NOT NULL,
	isAdmin BOOLEAN DEFAULT FALSE,
	PRIMARY KEY (username)

);


