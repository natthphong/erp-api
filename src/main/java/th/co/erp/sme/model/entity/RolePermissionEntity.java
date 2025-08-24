package th.co.erp.sme.model.entity;

import jakarta.persistence.*;
import lombok.*;
import th.co.erp.sme.configuration.jpa.AuditListeners;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_role_permission", uniqueConstraints = {
    @UniqueConstraint(name = "uk_tbl_role_permission__role_id__permission_id__is_deleted", columnNames = {"role_id", "permission_id", "is_deleted"})
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditListeners.class)
public class RolePermissionEntity extends BaseEntity<RolePermissionEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @Column(name = "permission_id", nullable = false)
    private Integer permissionId;

    @Column(name = "allowed", nullable = false)
    private String allowed;

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
