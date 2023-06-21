package com.example.Dosify.Service.impl;

import com.example.Dosify.Model.User;
import com.example.Dosify.Repository.UserRepository;
import com.example.Dosify.Service.UserService;
import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;
import com.example.Dosify.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {

     //convert request dto to entity
//       User user = new User();
//       user.setName(userRequestDto.getName());
//       user.setAge(userRequestDto.getAge());
//       user.setEmailId(userRequestDto.getEmailId());
//       user.setGender(userRequestDto.getGender());
//       user.setMobNo(userRequestDto.getMobNo());

//user object using builder
        User user = UserTransformer.UserRequestDtoToUser(userRequestDto);





       //save th object
        User savedUser = userRepository.save(user);

        //entity to dto
//        UserResponseDto userResponseDto = new UserResponseDto();
//        userResponseDto.setName(savedUser.getName());
//        userResponseDto.setMessage("Congrats! You have registered on Dosify");
        //savedUser is converting to responsedto therfore we pass savedUser
        UserResponseDto userResponseDto = UserTransformer.UserToUserResponseDto(savedUser);


        return  userResponseDto;


    }
}
