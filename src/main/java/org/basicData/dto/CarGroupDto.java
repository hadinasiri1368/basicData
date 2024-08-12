package org.basicData.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CarGroupDto {
    private Long id;
    private Long carCapacityId;
    private String carCapacityName;
    private Long carTypeId;
    private String carTypeName;
    private Long companyId;
    private String companyName;
    private Float factorValue;

}
