package com.beingatushar.ubercommons.service.user.impl;

import com.beingatushar.ubercommons.dto.UserDTO;
import com.beingatushar.ubercommons.entity.user.User;
import com.beingatushar.ubercommons.exception.ResourceNotFoundException;
import com.beingatushar.ubercommons.mapper.UserMapper;
import com.beingatushar.ubercommons.repository.UserRepository;
import com.beingatushar.ubercommons.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDTO create(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        // Remember to hash the password here in a real application
        // user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User savedUser = userRepository.save(user);
        return UserMapper.toDTO(savedUser);
    }

    @Override
    @Transactional
    public UserDTO update(Long id, UserDTO userDTO) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));

        // Safe, manual mapping of fields
        userToUpdate.setName(userDTO.getName());
        userToUpdate.setEmail(userDTO.getEmail());

        // Only update the password if a new one is provided
        if (userDTO.getPassword() != null && !userDTO.getPassword().isBlank()) {
            // Hash the password before saving
            userToUpdate.setPassword(userDTO.getPassword());
        }

        User updatedUser = userRepository.save(userToUpdate);
        return UserMapper.toDTO(updatedUser);
    }

    @Override
    public UserDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        return UserMapper.toDTO(user);
    }

    @Override
    @Transactional
    public Boolean deleteById(Long id) {
        if (!existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public User getByRef(Long id) {
        return userRepository.getReferenceById(id);
    }
}