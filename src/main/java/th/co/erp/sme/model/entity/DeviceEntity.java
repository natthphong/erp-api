package th.co.erp.sme.model.entity;

import jakarta.persistence.*;
import lombok.*;
import th.co.erp.sme.configuration.jpa.AuditListeners;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_device", uniqueConstraints = {
    @UniqueConstraint(name = "uk_tbl_device__company_code__device_code__is_deleted", columnNames = {"company_code", "device_code", "is_deleted"})
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditListeners.class)
public class DeviceEntity extends BaseEntity<DeviceEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "company_code", nullable = false)
    private String companyCode;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "device_code", nullable = false)
    private String deviceCode;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type_code", nullable = false)
    private String typeCode;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "last_seen_at")
    private LocalDateTime lastSeenAt;

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
