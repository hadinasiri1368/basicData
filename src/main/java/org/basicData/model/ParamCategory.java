package org.basicData.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "param_category", schema = "sbd")
@Entity(name = "paramCategory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParamCategory extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String name;
}
