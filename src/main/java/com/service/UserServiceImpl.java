package com.service;


import com.dto.UserDto;
import com.entity.Role;
import com.entity.User;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        List<Role> roles;
        if (Objects.isNull(userDto.getRoles()) || userDto.getRoles().isEmpty()) {
            Role defaultRole = roleRepository.findByName("ROLE_USER");
            if (defaultRole == null) {
                defaultRole = checkRoleExist();
            }
            roles = Collections.singletonList(defaultRole);
        } else {
            roles = userDto.getRoles().stream()
                .map(roleName -> roleRepository.findByName(roleName))
                .collect(Collectors.toList());
        }

        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setId(user.getId());
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());

        List<Role> roles = user.getRoles();
        List<String> roleNames = roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
        userDto.setRoles(roleNames);

        return userDto;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }

    @Override
    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            userRepository.delete(user);
        } else {
            System.out.println("User not found for email: " + email);
        }
    }

    @Override
    public void updateUserByEmail(String email, UserDto updatedUserDto) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            existingUser.setName(updatedUserDto.getFirstName() + " " + updatedUserDto.getLastName());
            existingUser.setEmail(updatedUserDto.getEmail());
            List<Role> roles = updatedUserDto.getRoles().stream()
                        .map(roleName -> roleRepository.findByName(roleName))
                        .collect(Collectors.toList());

            System.out.println(email);
            System.out.println(updatedUserDto.getFirstName() + updatedUserDto.getLastName());
            System.out.println(roles);
            existingUser.setRoles(roles);
            userRepository.save(existingUser);
            
        } else {
            throw new IllegalArgumentException("User not found for email: " + email);
        }
    }

    @Override
    public int getTotalUserRoles() {
        System.out.println(userRepository.countUsersWithUserRole());
        return userRepository.countUsersWithUserRole();
    }

}