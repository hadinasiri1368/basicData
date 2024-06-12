package org.basicData.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "parameters", schema = "sbd")
@Entity(name = "parameters")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parameters extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "NVARCHAR(250)", name = "param_name")
    private String paramName;
    @Column(columnDefinition = "NVARCHAR(250)",name = "param_code")
    private String paramCode;
    @ManyToOne
    @JoinColumn(name = "f_param_type_id")
    private ParamType paramType;
    @ManyToOne
    @JoinColumn(name = "f_param_category_id")
    private ParamCategory paramCategory;
    @Column(name = "f_company_id")
    private Long companyId;
    @Column(columnDefinition = "NVARCHAR(50)",name = "value")
    private String value;
}

