package com.example.Dosify.Service;

import com.example.Dosify.Exception.DoctorNotFoundException;
import com.example.Dosify.Exception.NotEligibleForDoseException;
import com.example.Dosify.Exception.UserNotFoundException;
import com.example.Dosify.dto.RequestDTO.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDTO.AppointmentResponseDto;

import java.net.UnknownServiceException;

public interface AppointmentService {
    public AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto) throws UnknownServiceException, UserNotFoundException, DoctorNotFoundException, NotEligibleForDoseException;
}
