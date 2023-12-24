package org.basicData.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "[country_division]", schema = "sbd")
@Entity(name = "countryDivision")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryDivision extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "f_parent_id")
    private CountryDivision parent;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String code;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String name;
    @Column(columnDefinition = "INT" , name = "level_to_root")
    private Integer levelToRoot;
    @Column(columnDefinition = "BIT" , name = "is_free_zone")
    private Boolean isFreeZone;

}
