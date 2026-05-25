package com.example.service;


import com.example.dto.UserDTO;
import com.example.entity.Users;
import com.example.repository.UserRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
//pagination
@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Page<UserDTO> getUsers(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy).ascending()
        );

        Page<Users> userPage = repository.findAll(pageable);

        // ✅ convert to DTO
        return userPage.map(user ->
                new UserDTO(user.getId(), user.getName())
        );
    }
}