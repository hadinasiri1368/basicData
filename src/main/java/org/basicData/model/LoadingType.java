package org.basicData.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "loading_type" ,schema = "sbd")
@Entity(name = "loadingType")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoadingType {  @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String code;
    @Column(columnDefinition = "NVARCHAR(512)")
    private String name;
}
