package com.poc.bookingSystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poc.bookingSystem.entity.RoomDetails;

@Repository
public interface IRoomDetailsRepo extends JpaRepository<RoomDetails, Long>{

	@Query("Select rd From ROOM_DETAILS rd")
	List<RoomDetails> fetchAll();

	
}
