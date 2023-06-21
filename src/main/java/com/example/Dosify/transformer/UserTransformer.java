package com.example.Dosify.transformer;

import com.example.Dosify.Model.User;
import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;
import lombok.experimental.UtilityClass;

//@UtilityClass  // IF you create object of this class throw error (this class object cant be intantiate)
public class UserTransformer {

    //user object using builder

    public static  User UserRequestDtoToUser(UserRequestDto userRequestDto){
        return User.builder()
                .name(userRequestDto.getName())
                .age(userRequestDto.getAge())
                .emailId(userRequestDto.getEmailId())
                .gender(userRequestDto.getGender())
                .mobNo(userRequestDto.getMobNo())
                .build();

    }

    public static UserResponseDto UserToUserResponseDto(User user){
        return UserResponseDto.builder()
                .name(user.getName())
                .message("Congrats! You have registered on Dosify")
                .build();

    }
}
