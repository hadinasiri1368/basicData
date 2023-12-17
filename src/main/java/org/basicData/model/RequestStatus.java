package org.basicData.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "request_status", schema = "sbd")
@Entity(name = "requestStatus")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RequestStatus extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "NVARCHAR(50)", name = "name")
    private String name;
}
