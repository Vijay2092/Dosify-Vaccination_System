package com.example.Dosify.Service.impl;

import com.example.Dosify.Model.VaccinationCenter;
import com.example.Dosify.Repository.CenterRepository;
import com.example.Dosify.Service.VaccinationCenterService;
import com.example.Dosify.dto.RequestDTO.CenterRequestDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;
import com.example.Dosify.transformer.VaccinationCenterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CenterServiceImpl implements VaccinationCenterService {

    @Autowired
    CenterRepository centerRepository;
    @Override
    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto) {

        //dto - entity
        VaccinationCenter vaccinationCenter = VaccinationCenterTransformer.CenterRequestDtoToCenter(centerRequestDto);

        //save to your db
        VaccinationCenter savedCenter = centerRepository.save(vaccinationCenter);

        //entity to dto
        return VaccinationCenterTransformer.CenterToCenterResponseDto(savedCenter);


    }
}
