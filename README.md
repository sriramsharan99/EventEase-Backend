# EventEase Backend

Starter backend for the EventEase Smart Event & Ticket Booking Management System.

## Technology
- Java 21
- Spring Boot
- Spring Web / REST API
- Spring Data JPA
- MySQL
- Maven
- Dockerfile for Render deployment

## Current features
- Health endpoints: `/` and `/api/health`
- Create, list, search, view, update, and delete events under `/api/events`
- Event data validation
- MySQL persistence

> This is a starter backend, not the full abstract implementation yet. Login/JWT, user roles, seat locking, bookings, payment integration, QR tickets, and organizer/admin dashboards still need to be added and tested before production use.

## Run locally
1. Install Java 21 and MySQL.
2. Create the database:
   ```sql
   CREATE DATABASE eventease;
   ```
3. Set environment variables if your MySQL credentials differ from defaults:
   - `DB_URL=jdbc:mysql://localhost:3306/eventease`
   - `DB_USERNAME=root`
   - `DB_PASSWORD=your_local_password`
4. Run:
   ```bash
   mvn spring-boot:run
   ```
5. Check `http://localhost:8080/api/health`.

## Example event JSON
Use a future date/time for `eventDateTime`:
```json
{
  "name": "College Music Night",
  "description": "Live music and performances",
  "category": "Music",
  "eventDateTime": "2027-01-15T18:00:00",
  "venue": "University Auditorium",
  "ticketPrice": 499.00,
  "capacity": 200,
  "imageUrl": "https://example.com/event.jpg"
}
```

## Deploy to Render
1. Push this repository to GitHub.
2. In Render choose **New + → Web Service**, then connect the repository.
3. Choose **Docker** as the runtime; Render will use the included `Dockerfile`.
4. Add environment variables:
   - `DB_URL` = JDBC URL supplied by your hosted MySQL provider
   - `DB_USERNAME` = database username
   - `DB_PASSWORD` = database password
5. Deploy, then test `/api/health`.

Do not commit passwords or other secrets to GitHub.
