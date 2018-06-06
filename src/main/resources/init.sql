insert into room_details (room_id,room_name,booking_status,floor_no)values(1,'meeting room 1','Available',1);
insert into room_details (room_id,room_name,booking_status,floor_no)values((select nextval('room_details_room_id_seq')),'meeting room 2','Available',2);
insert into room_details (room_id,room_name,booking_status,floor_no)values((select nextval('room_details_room_id_seq')),'meeting room 3','Available',3);
insert into room_details (room_id,room_name,booking_status,floor_no)values((select nextval('room_details_room_id_seq')),'meeting room 4','Available',4);

//---------user-info

insert into user_details(user_id, user_name)values(1,'ankisriv');