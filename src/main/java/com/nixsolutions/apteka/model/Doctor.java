package com.nixsolutions.apteka.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Doctor implements Entity {
    private Long id;
    private String name;
}
