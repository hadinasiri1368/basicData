package org.basicData.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "province_city", schema = "sbd")
@Entity(name = "provinceCity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceCity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String provinceCode;
    @Column(columnDefinition = "NVARCHAR(512)")
    private String provinceDesc;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String cityCode;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String cityDesc;
}
