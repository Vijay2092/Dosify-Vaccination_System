package com.example.Dosify.Service.impl;

import com.example.Dosify.Exception.CenterNotPresentException;
import com.example.Dosify.Model.Doctor;
import com.example.Dosify.Model.VaccinationCenter;
import com.example.Dosify.Repository.CenterRepository;
import com.example.Dosify.Service.DoctorService;
import com.example.Dosify.dto.RequestDTO.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;
import com.example.Dosify.transformer.DoctorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorServiceImpl implements  DoctorService {
    @Autowired
    CenterRepository centerRepository;

    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws CenterNotPresentException {

        Optional<VaccinationCenter> optionalCenter = centerRepository.findById(doctorRequestDto.getCenterId());
        if(!optionalCenter.isPresent()){
            throw new CenterNotPresentException("Invalid center id!");
        }

        VaccinationCenter center = optionalCenter.get();

        // dto -> entity
        Doctor doctor = DoctorTransformer.DoctorRequestDtoToDoctor(doctorRequestDto);
        doctor.setVaccinationCenter(center);
        // add doctor to current list of doctors at that center
        center.getDoctors().add(doctor);

        VaccinationCenter savedCenter = centerRepository.save(center);  // saves both center and doctor

        // prepare response Dto
        return DoctorTransformer.DoctorToDoctorResponseDto(doctor);
    }
}
