package com.example.Dosify.Controller;

import com.example.Dosify.Exception.DoctorNotFoundException;
import com.example.Dosify.Exception.NotEligibleForDoseException;
import com.example.Dosify.Exception.UserNotFoundException;
import com.example.Dosify.Service.AppointmentService;
import com.example.Dosify.dto.RequestDTO.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDTO.AppointmentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownServiceException;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/book")
    public AppointmentResponseDto bookAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto) throws UserNotFoundException, DoctorNotFoundException, UnknownServiceException, NotEligibleForDoseException {

        return appointmentService.bookAppointment(appointmentRequestDto);
    }

}
