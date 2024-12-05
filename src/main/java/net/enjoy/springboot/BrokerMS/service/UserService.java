package net.enjoy.springboot.BrokerMS.service;

import net.enjoy.springboot.BrokerMS.dto.UserDto;
import net.enjoy.springboot.BrokerMS.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<User> findAllUsers();
    UserDto getUserById(Long id);
    void updateUser(UserDto userDto);
    void deleteUser(Long id);
}