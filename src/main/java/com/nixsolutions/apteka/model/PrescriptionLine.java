package com.nixsolutions.apteka.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class PrescriptionLine {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Medicine medicine;

    private int quantity;
    private int cost;

    @ManyToOne
    private Prescription prescription;
}
