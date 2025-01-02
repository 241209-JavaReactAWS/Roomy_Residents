BEGIN TRANSACTION;

DROP TABLE IF EXISTS Bookings, Rooms, Hotels, Users, Owner, Payments;


CREATE TABLE Users (
    User_id BIGSERIAL PRIMARY KEY,
    username VARCHAR (100) UNIQUE NOT NULL,
    first_Name VARCHAR(100) NOT NULL,
    last_Name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    birthday DATE ,
    Role VARCHAR(50) NOT NULL CHECK (Role IN ('Admin', 'Owner', 'User'))
   
);

CREATE TABLE Owner (
        Owner_id BIGINT PRIMARY KEY REFERENCES Users(User_id) ON DELETE CASCADE,
        business_email VARCHAR(200) UNIQUE NOT NULL,
        business_phone_number VARCHAR(15)
);

CREATE TABLE Hotels (
    Hotel_id BIGSERIAL PRIMARY KEY,
    Owner_id BIGINT NOT NULL REFERENCES Users(User_id) ON DELETE CASCADE,
    hotel_name VARCHAR(255) NOT NULL,
    hotel_Address TEXT NOT NULL,
    hotel_city VARCHAR(100) NOT NULL,
    hotel_state VARCHAR(100) NOT NULL,
    hotel_zipcode INT NOT NULL,
    hotel_phone_number VARCHAR(15),
    Hotel_email VARCHAR(255),
    Rating DECIMAL(3, 2)
    
);

CREATE TABLE Rooms (
    Room_id BIGSERIAL PRIMARY KEY,
    Hotel_id BIGINT NOT NULL REFERENCES Hotels(Hotel_id) ON DELETE CASCADE,
    room_type VARCHAR(50) NOT NULL, 
    price DECIMAL(10, 2) NOT NULL,
    Capacity INT NOT NULL, 
    Description TEXT,
    status BOOLEAN DEFAULT TRUE,
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

