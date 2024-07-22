package org.basicData.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Table(name = "car_capacity" ,schema = "sbd")
@Entity(name = "carCapacity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarCapacity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "NVARCHAR(50)")
    private Long code;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String name;
    @Column(columnDefinition = "float" , name = "max_capacity")
    private  Long maxCapacity;
    @Column(columnDefinition = "float" , name = "min_capacity")
    private  Long minCapacity;
}
