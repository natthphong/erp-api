package th.co.erp.sme.model.entity;

import jakarta.persistence.*;
import lombok.*;
import th.co.erp.sme.configuration.jpa.AuditListeners;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_param_value", uniqueConstraints = {
    @UniqueConstraint(name = "uk_tbl_param_value__company_code__group_code__param_code__is_deleted", columnNames = {"company_code", "group_code", "param_code", "is_deleted"})
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditListeners.class)
public class ParamValueEntity extends BaseEntity<ParamValueEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "company_code", nullable = false)
    private String companyCode;

    @Column(name = "group_code", nullable = false)
    private String groupCode;

    @Column(name = "param_code", nullable = false)
    private String paramCode;

    @Column(name = "name_th", nullable = false)
    private String nameTh;

    @Column(name = "name_en", nullable = false)
    private String nameEn;

    @Column(name = "param_value")
    private String paramValue;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    @Column(name = "is_active", nullable = false)
    private String isActive;

    @Column(name = "is_deleted", nullable = false)
    private String isDeleted;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "created_by")
    private Integer createBy;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updateDate;

    @Column(name = "updated_by")
    private Integer updateBy;

    @PrePersist public void insert(){ this.createDate = LocalDateTime.now(); }
    @PreUpdate public void update(){ this.updateDate = LocalDateTime.now(); }
}
