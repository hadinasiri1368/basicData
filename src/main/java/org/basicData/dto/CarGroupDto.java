package org.basicData.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.basicData.model.CarCapacity;

@AllArgsConstructor
@Getter
@Setter
public class CarGroupDto {
    private Long id;
    private Long f_car_capacity_id;
    private String name;
}
