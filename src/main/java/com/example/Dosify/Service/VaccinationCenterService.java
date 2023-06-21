package com.example.Dosify.Service;

import com.example.Dosify.dto.RequestDTO.CenterRequestDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;

public interface VaccinationCenterService {
    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto);
}
