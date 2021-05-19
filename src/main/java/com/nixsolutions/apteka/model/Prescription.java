package com.nixsolutions.apteka.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Prescription implements com.nixsolutions.apteka.model.Entity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne()
    private Doctor doctor;

    @OneToMany(mappedBy = "prescription")
    private Set<PrescriptionLine> lines;

    private Date date;

    private String patient;

    private Status status;

    public static enum Status {
        NEW,
        OLD
    }

}
