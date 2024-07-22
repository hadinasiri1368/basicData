package org.basicData.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "loading_type", schema = "sbd", uniqueConstraints = {@UniqueConstraint(name = "UK_company_code", columnNames = {"f_company_id", "code"})})
@Entity(name = "loadingType")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoadingType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String code;
    @Column(columnDefinition = "NVARCHAR(512)")
    private String name;
    @Column(columnDefinition = "decimal(18, 0)", name = "f_company_id")
    private Long companyId;
    @Column(columnDefinition = "float", name = "factor_value")
    private Float factorValue;
}
