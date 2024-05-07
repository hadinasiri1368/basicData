package org.basicData.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class CountryDivisionDto {
    private Long id;
    private Long parentId;
    private String code;
    private String name;
    private Integer levelToRoot;
    private Boolean isFreeZone;

}
