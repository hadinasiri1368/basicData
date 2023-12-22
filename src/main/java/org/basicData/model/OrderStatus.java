package org.basicData.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "[order_status]", schema = "sbd")
@Entity(name = "orderStatus")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String name;
    @Column(columnDefinition = "DECIMAL(18, 0)", name = "f_parent_id")
    private Long parentId;
}
