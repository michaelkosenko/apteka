package com.nixsolutions.apteka.service;

import java.util.Set;

import com.nixsolutions.apteka.model.Prescription;
import com.nixsolutions.apteka.model.PrescriptionLine;

public class PrescriptionService {

    public void create(Prescription prescription) {
        Set<PrescriptionLine> lines = prescription.getLines();
        for (PrescriptionLine prescriptionLine : lines) {

        }
    }
}
