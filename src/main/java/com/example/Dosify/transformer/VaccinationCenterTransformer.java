package com.example.Dosify.transformer;

import com.example.Dosify.Model.VaccinationCenter;
import com.example.Dosify.dto.RequestDTO.CenterRequestDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;

public class VaccinationCenterTransformer {


    //dto to entity
    public  static VaccinationCenter CenterRequestDtoToCenter(CenterRequestDto centerRequestDto){

        return VaccinationCenter.builder()
                .name(centerRequestDto.getName())
                .location(centerRequestDto.getLocation())
                .centerType(centerRequestDto.getCenterType())
                .build();
    }

    // entity to dto
    public  static CenterResponseDto CenterToCenterResponseDto(VaccinationCenter vaccinationCenter){

        return CenterResponseDto.builder()
                .name(vaccinationCenter.getName())
                .location(vaccinationCenter.getLocation())
                .centerType(vaccinationCenter.getCenterType())
                .build();

    }


}
