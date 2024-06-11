package org.basicData.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "param_type", schema = "sbd")
@Entity(name = "paramType")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParamType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String name;
}
