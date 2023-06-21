package com.example.Dosify.Service;

import com.example.Dosify.Enum.VaccineType;
import com.example.Dosify.Model.Dose2;
import com.example.Dosify.Model.User;

public interface Dose2Service {
    public Dose2 createDose2(User user, VaccineType vaccineType);
}
