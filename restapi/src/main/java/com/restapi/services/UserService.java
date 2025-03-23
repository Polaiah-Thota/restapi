package com.restapi.services;

import com.restapi.models.User;
import com.restapi.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setDob(updatedUser.getDob());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User patchUser(Long id, User partialUser) {
        return userRepository.findById(id).map(user -> {
            if (partialUser.getName() != null) {
                user.setName(partialUser.getName());
            }
            if (partialUser.getEmail() != null) {
                user.setEmail(partialUser.getEmail());
            }
            if (partialUser.getPassword() != null) {
                user.setPassword(partialUser.getPassword());
            }
            if (partialUser.getDob() != null) {
                user.setDob(partialUser.getDob());
            }
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
