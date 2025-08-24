package th.co.erp.sme.model.entity;

import jakarta.persistence.*;
import lombok.*;
import th.co.erp.sme.configuration.jpa.AuditListeners;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_permission", uniqueConstraints = {
    @UniqueConstraint(name = "uk_tbl_permission__object_code__action_code__is_deleted", columnNames = {"object_code", "action_code", "is_deleted"})
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditListeners.class)
public class PermissionEntity extends BaseEntity<PermissionEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "object_code", nullable = false)
    private String objectCode;

    @Column(name = "action_code", nullable = false)
    private String actionCode;

    @Column(name = "description")
    private String description;

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
