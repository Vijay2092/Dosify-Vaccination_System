package com.example.Dosify.Service;

import com.example.Dosify.Model.User;
import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;

public interface UserService {

    public UserResponseDto addUser(UserRequestDto userRequestDto);

    }

