package com.nixsolutions.apteka.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@javax.persistence.Entity
@NoArgsConstructor
@AllArgsConstructor
public class Doctor implements Entity {
    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    private String name;
}
