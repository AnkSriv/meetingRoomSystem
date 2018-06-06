package com.poc.bookingSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poc.bookingSystem.entity.RoomDetails;
import com.poc.bookingSystem.entity.UserInfo;

@Repository
public interface IUserInfoRepo extends JpaRepository<UserInfo, Long>{

	
	UserInfo getUserInfoByUserName(String userName);

	
}
