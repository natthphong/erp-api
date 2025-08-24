package th.co.erp.sme.model.entity;

import jakarta.persistence.*;
import lombok.*;
import th.co.erp.sme.configuration.jpa.AuditListeners;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_company_setting", uniqueConstraints = {
    @UniqueConstraint(name = "uk_tbl_company_setting__company_code__key_code__is_deleted", columnNames = {"company_code", "key_code", "is_deleted"})
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditListeners.class)
public class CompanySettingEntity extends BaseEntity<CompanySettingEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "company_code", nullable = false)
    private String companyCode;

    @Column(name = "key_code", nullable = false)
    private String keyCode;

    @Column(name = "value_text")
    private String valueText;

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
