package org.basicData.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.basicData.model.CountryDivision;

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
