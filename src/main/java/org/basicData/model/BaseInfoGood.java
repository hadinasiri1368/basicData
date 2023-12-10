package org.basicData.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "base_info_good" ,schema = "sbd")
@Entity(name = "baseInfoGood")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseInfoGood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String code;
    @Column(columnDefinition = "NVARCHAR(512)")
    private String name;
}
