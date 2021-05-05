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
@AllArgsConstructor
@NoArgsConstructor
@javax.persistence.Entity
public class Medicine implements Entity {

    @Id
    @GeneratedValue
    private Long id;

    private String sku;

    private Integer price;
}
