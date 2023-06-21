package com.example.Dosify.Service.impl;

import com.example.Dosify.Enum.DoseNo;
import com.example.Dosify.Exception.DoctorNotFoundException;
import com.example.Dosify.Exception.NotEligibleForDoseException;
import com.example.Dosify.Exception.UserNotFoundException;
import com.example.Dosify.Repository.DoctorRepository;
import com.example.Dosify.Repository.UserRepository;
import com.example.Dosify.Service.AppointmentService;
import com.example.Dosify.Service.Dose1Service;
import com.example.Dosify.Service.Dose2Service;
import com.example.Dosify.dto.RequestDTO.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDTO.AppointmentResponseDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;
import com.example.Dosify.transformer.VaccinationCenterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.Dosify.Model.*;


import java.net.UnknownServiceException;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    Dose1Service dose1Service;

    @Autowired
    Dose2Service dose2Service;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto) throws UnknownServiceException, UserNotFoundException, DoctorNotFoundException, NotEligibleForDoseException {

        Optional<User> optionalUser = userRepository.findById(appointmentRequestDto.getUserId());
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("User doesn't exist!");
        }

        Optional<Doctor> optionalDoctor = doctorRepository.findById(appointmentRequestDto.getDoctorId());
        if(!optionalDoctor.isPresent()){
            throw new DoctorNotFoundException("Doctor doesn't exist!!!");
        }

        User user = optionalUser.get();
        Doctor doctor = optionalDoctor.get();

        if(appointmentRequestDto.getDoseNo()== DoseNo.DOSE_1){
            Dose1 dose1 = dose1Service.createDose1(user,appointmentRequestDto.getVaccineType());
            user.setDose1Taken(true);
            user.setDose1(dose1);
        }
        else{
            // DOSE_2
            if(!user.isDose1Taken()){
                throw new NotEligibleForDoseException("Sorry! You are not yet eligible for Dose 2");
            }

            Dose2 dose2 = dose2Service.createDose2(user,appointmentRequestDto.getVaccineType());
            user.setDose2Taken(true);
            user.setDose2(dose2);
        }


        //dto to entity
        Appointment appointment = Appointment.builder()
                .appointmentNo(String.valueOf(UUID.randomUUID()))
                .doseNo(appointmentRequestDto.getDoseNo())
                .user(user)
                .doctor(doctor)
                .build();



        user.getAppointments().add(appointment);
        User savedUser = userRepository.save(user); // save dose1/dose2 and appointment

        Appointment savedAppointment = savedUser.getAppointments().get(savedUser.getAppointments().size()-1);
        doctor.getAppointments().add(savedAppointment);
        doctorRepository.save(doctor);

        //send mail
        String text = "Congrats!!" + user.getName() + " Your dose "+ appointmentRequestDto.getDoseNo() + " has been booked!!";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dosifyproject@gmail.com");
        message.setTo(user.getEmailId());
        message.setSubject("Appointment Booked !!!");
        message.setText(text);
        emailSender.send(message);


        // prepare response dto
        CenterResponseDto centerResponseDto = VaccinationCenterTransformer.CenterToCenterResponseDto(doctor.getVaccinationCenter());
        return AppointmentResponseDto.builder()
                .userName(user.getName())
                .appointmentNo(appointment.getAppointmentNo())
                .dateOfAppointment(savedAppointment.getDateOfAppointment())
                .doseNo(appointment.getDoseNo())
                .centerResponseDto(centerResponseDto)
                .doctorName(doctor.getName())
                .vaccineType(appointmentRequestDto.getVaccineType())
                .build();


    }
}
