package com.example.Dosify.Service;

import com.example.Dosify.Enum.VaccineType;
import com.example.Dosify.Model.Dose1;
import com.example.Dosify.Model.User;

public interface Dose1Service {
    public Dose1 createDose1(User user, VaccineType vaccineType);
}
