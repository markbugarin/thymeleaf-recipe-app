package com.champstart.recipeapp.user.service.impl;

import com.champstart.recipeapp.user.dto.LoginFormDto;
import com.champstart.recipeapp.user.dto.NewPasswordFormDto;
import com.champstart.recipeapp.user.dto.UserDto;
import com.champstart.recipeapp.user.dto.dtoMapper.UserMapper;
import com.champstart.recipeapp.user.model.UserModel;
import com.champstart.recipeapp.user.repository.RoleRepository;
import com.champstart.recipeapp.user.repository.UserRepository;
import com.champstart.recipeapp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository usersRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = usersRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserModel saveUser(UserDto userDto) {

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setVerificationId(passwordEncoder.encode(userDto.getEmail()));
        UserModel userModel = UserMapper.mapToUser(userDto);
        return userRepository.save(userModel);
    }

    @Override
    public UserModel setUserVerified(String verificationId) {
        UserModel user = userRepository.findByVerificationId(verificationId);
        user.setIsVerified(true);
        return userRepository.save(user);
    }

    @Override
    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserModel mapToUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserModel findByVerificationId(String verificationId) {
        return userRepository.findByVerificationId(verificationId);
    }

    @Override
    public void updatePassword(UserDto userDto, String newPassword) {
        userDto.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(UserMapper.mapToUser(userDto));
    }
}
