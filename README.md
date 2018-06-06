# meetingRoomSystem

1. Start the server
2. Run init.sql present in src>main>resources

3. Request body to book a meeting
   {
	  "roomId": 1,
	  "meetingId":0,
	  "meetingTitle": "meeting 1",
	  "startTime": "2002-01-29 10:36:33",
	  "endTime":"2002-01-29 10:45:33",
	  "userName": "ankisriv",
	  "bookingStatus": "Booked"
   }
   endpoint :- /book
   Return :- meeting id
 4. to update a meeting , pass meeting id in the request body of booking a meeting.
 