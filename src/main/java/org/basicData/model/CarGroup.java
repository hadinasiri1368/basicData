package org.basicData.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "car_group", schema = "sbd", uniqueConstraints = {@UniqueConstraint(name = "UK_company_capacity_type", columnNames = {"f_company_id", "f_car_capacity_id", "f_car_type_id"})})
@Entity(name = "carGroup")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarGroup extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "f_car_capacity_id")
    private Long carCapacityId;
    @Column(name = "f_car_type_id")
    private Long carTypeId;
    @Column(columnDefinition = "decimal(18, 0)", name = "f_company_id")
    private Long companyId;
    @Column(columnDefinition = "float", name = "factor_value")
    private Float factorValue;
}
