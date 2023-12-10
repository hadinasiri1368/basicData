package org.basicData.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "packing_type" ,schema = "sbd")
@Entity(name = "packingType")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PackingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String code;
    @Column(columnDefinition = "NVARCHAR(512)")
    private String name;
}
