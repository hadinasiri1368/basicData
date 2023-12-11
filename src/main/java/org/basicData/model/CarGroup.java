package org.basicData.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "car_group", schema = "sbd")
@Entity(name = "carGroup")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarGroup extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "f_car_capacity_id")
    private CarCapacity carCapacity;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String name;
}
