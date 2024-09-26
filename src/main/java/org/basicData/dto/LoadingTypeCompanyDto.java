package org.basicData.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class LoadingTypeCompanyDto {
    private Long id;
    private Long loadingTypeId;
    private String name;
    private String code;
    private Long companyId;
    private String companyName;
    private Double factorValue;
}
