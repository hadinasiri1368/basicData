package org.basicData.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "loading_type_company", schema = "sbd")
@Entity(name = "loadingTypeCompany")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoadingTypeCompany extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "f_loading_type_id")
    private LoadingType loadingType;
    @Column(columnDefinition = "decimal(18, 0)", name = "f_company_id")
    private Long companyId;
    @Column(name = "factor_value")
    private Double factorValue;
}
