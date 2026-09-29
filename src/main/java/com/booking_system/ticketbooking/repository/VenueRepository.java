package com.booking_system.ticketbooking.repository;

import com.booking_system.ticketbooking.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends JpaRepository<Venue,Long> {
    public boolean existsByName(String name);
}
