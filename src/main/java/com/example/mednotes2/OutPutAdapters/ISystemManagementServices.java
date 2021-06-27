package com.example.mednotes2.OutPutAdapters;

import com.example.mednotes2.Model.DoctorEntity;
import com.example.mednotes2.Model.PatientEntity;

public interface ISystemManagementServices {
    DoctorEntity doctoriE(int doctorPersonalNumber);
    PatientEntity pacientiE(int nrPersonal);
}
