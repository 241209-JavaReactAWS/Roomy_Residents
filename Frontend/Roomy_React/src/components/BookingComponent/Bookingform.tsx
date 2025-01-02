import React, { useState, useEffect } from "react";
import { Booking } from "../../interfaces/Booking";

interface Room {
  roomId: number;
  roomType: string;
  pricePerNight: number;
  capacity: number;
  status: boolean;
}

const BookingForm: React.FC = () => {
  const [booking, setBooking] = useState<Partial<Booking>>({
    userId: 0,
    roomId: 0,
    dateCheckIn: "",
    dateCheckOut: "",
    totalCost: 0,
    bookingStatus: "Pending",
  });
  const [rooms, setRooms] = useState<Room[]>([]);

  // Fetch available rooms from the backend
  useEffect(() => {
    const fetchRooms = async () => {
      try {
        const response = await fetch("http://roomy-residents.ch8gq6o8g6bf.us-east-2.rds.amazonaws.com:8080/rooms");
        const data = await response.json();
        setRooms(data);
      } catch (error) {
        console.error("Error fetching rooms:", error);
      }
    };

    fetchRooms();
  }, []);

  // Calculate total cost dynamically based on room rate and stay duration
  const calculateTotalCost = (roomId: number, dateCheckIn: string, dateCheckOut: string) => {
    const room = rooms.find((room) => room.roomId === roomId);
    if (room && dateCheckIn && dateCheckOut) {
      const checkInDate = new Date(dateCheckIn);
      const checkOutDate = new Date(dateCheckOut);
      const days = Math.ceil((checkOutDate.getTime() - checkInDate.getTime()) / (1000 * 60 * 60 * 24));
      return days * room.pricePerNight;
    }
    return 0;
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setBooking((prev) => {
      const updatedBooking = { ...prev, [name]: value };
      if (name === "roomId" || name === "dateCheckIn" || name === "dateCheckOut") {
        updatedBooking.totalCost = calculateTotalCost(
          Number(updatedBooking.roomId),
          updatedBooking.dateCheckIn || "",
          updatedBooking.dateCheckOut || ""
        );
      }
      return updatedBooking;
    });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await fetch("http://roomy-residents.ch8gq6o8g6bf.us-east-2.rds.amazonaws.com:8080/bookings", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(booking),
      });

      if (!response.ok) throw new Error("Failed to create booking");
      const data = await response.json();
      alert("Booking created successfully!");
      console.log(data);
    } catch (error) {
      console.error(error);
      alert("An error occurred while creating the booking.");
    }
  };

  return (
    <div style={{ maxWidth: "600px", margin: "auto", padding: "20px" }}>
      <h1>Hotel Booking Form</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>User ID:</label>
          <input type="number" name="userId" value={booking.userId} onChange={handleChange} required />
        </div>
        <div>
          <label>Room:</label>
          <select name="roomId" value={booking.roomId} onChange={handleChange} required>
            <option value="">Select a Room</option>
            {rooms.map((room) => (
              <option key={room.roomId} value={room.roomId}>
                {room.roomType} - ${room.pricePerNight}/night
              </option>
            ))}
          </select>
        </div>
        <div>
          <label>Check-In Date:</label>
          <input type="date" name="dateCheckIn" value={booking.dateCheckIn} onChange={handleChange} required />
        </div>
        <div>
          <label>Check-Out Date:</label>
          <input type="date" name="dateCheckOut" value={booking.dateCheckOut} onChange={handleChange} required />
        </div>
        <div>
          <label>Total Cost:</label>
          <input type="number" name="totalCost" value={booking.totalCost} readOnly />
        </div>
        <button type="submit">Book</button>
      </form>
    </div>
  );
};

export default BookingForm;
