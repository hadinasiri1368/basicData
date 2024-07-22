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
    private Long carCapacityId;
    private Long carTypeId;
    private Long companyId;
    private Float factorValue;
}
