package org.basicData.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "plaque_tag_persian_part", schema = "sbd")
@Entity(name = "plaqueTagPersianPart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaqueTagPersianPart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String name;
}
