package org.basicData.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ParametersDto {
    private Long id;
    private String paramName;
    private String paramCode;
    private Long paramTypeId;
    private String paramTypeName;
    private Long paramCategoryId;
    private String paramCategoryName;
    private Long companyId;
    private String companyName;
    private String value;
}
