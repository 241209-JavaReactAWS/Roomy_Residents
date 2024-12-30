BEGIN TRANSACTION;

DROP TABLE IF EXISTS Bookings, Rooms, Hotels, Users, Owner, Payments;


CREATE TABLE Users (
    User_id BIGSERIAL PRIMARY KEY,
    FirstName VARCHAR(100) NOT NULL,
    LastName VARCHAR(100) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    PasswordHash VARCHAR(255) NOT NULL,
    Role user_role NOT NULL
);

CREATE TABLE Owner (
	Owner_id BIGINT PRIMARY KEY REFERENCES Users(User_id) ON DELETE CASCADE,
	Business_info VARCHAR(200),
	PhoneNumber VARCHAR(15)
);

CREATE TABLE Hotels (
    Hotel_id BIGSERIAL PRIMARY KEY,
    Owner_id BIGINT NOT NULL REFERENCES Users(User_id) ON DELETE CASCADE,
    Name VARCHAR(255) NOT NULL,
    Address TEXT NOT NULL,
    City VARCHAR(100) NOT NULL,
    State VARCHAR(100),
    Country VARCHAR(100) NOT NULL,
    PostalCode VARCHAR(20),
    Phone VARCHAR(15),
    Email VARCHAR(255),
    Description TEXT,
    Rating DECIMAL(3, 2),
    CreatedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Rooms (
    Room_id BIGSERIAL PRIMARY KEY,
    Hotel_id BIGINT NOT NULL REFERENCES Hotels(Hotel_id) ON DELETE CASCADE,
    RoomType VARCHAR(50) NOT NULL, 
    PricePerNight DECIMAL(10, 2) NOT NULL,
    Capacity INT NOT NULL, 
    Description TEXT,
    Status BOOLEAN DEFAULT TRUE,
    image VARCHAR(2000)
   
);

CREATE TABLE Bookings (
    Booking_id BIGSERIAL PRIMARY KEY,
    User_id INT NOT NULL REFERENCES Users(User_id) ON DELETE CASCADE,
    Room_id INT NOT NULL REFERENCES Rooms(Room_id) ON DELETE CASCADE,
    dateCheckIn DATE NOT NULL,
    dateCheckOut DATE NOT NULL,
    TotalCost DECIMAL(10, 2) NOT NULL,
    BookingStatus VARCHAR(50) DEFAULT 'Pending',
    atTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE Payments ( 
	Payment_id BIGSERIAL PRIMARY KEY, 
	Booking_id INT NOT NULL REFERENCES Bookings(Booking_id) ON DELETE CASCADE, 
	atDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	Amount DECIMAL(10, 2) NOT NULL, 
	PaymentStatus VARCHAR(50) DEFAULT 'Pending' 
); 



COMMIT;

