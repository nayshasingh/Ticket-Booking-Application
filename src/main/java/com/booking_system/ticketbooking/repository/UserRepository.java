package com.booking_system.ticketbooking.repository;

import com.booking_system.ticketbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
