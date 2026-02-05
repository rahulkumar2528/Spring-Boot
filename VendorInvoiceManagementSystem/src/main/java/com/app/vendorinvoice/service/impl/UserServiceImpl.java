package com.app.vendorinvoice.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.vendorinvoice.dto.request.RegisterRequest;
import com.app.vendorinvoice.entity.Role;
import com.app.vendorinvoice.entity.User;
import com.app.vendorinvoice.repository.RoleRepository;
import com.app.vendorinvoice.repository.UserRepository;
import com.app.vendorinvoice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public User registerUser(RegisterRequest request) {
        if(userRepository.findByUserName(request.getUsername()).isPresent()){
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUserName(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus("ACTIVE");

        Set<Role> roles = request.getRoles().stream()
                .map(roleName -> roleRepository.findByRoleName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

        user.setRoles(roles);
        return userRepository.save(user);
    }
}
