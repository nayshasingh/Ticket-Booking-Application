package com.booking_system.ticketbooking.service;

import com.booking_system.ticketbooking.dto.CreateUserRequest;
import com.booking_system.ticketbooking.entity.User;
import com.booking_system.ticketbooking.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequest createUserRequest){
        User user = new User();
        user.setName(createUserRequest.getName());
        user.setEmail(createUserRequest.getEmail());
        return userRepository.save(user);
    }
}
