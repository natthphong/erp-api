package th.co.erp.sme.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import th.co.erp.sme.configuration.jpa.AuditListeners;
import th.co.erp.sme.model.entity.key.TblRoleObjectKey;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_role_object")
@IdClass(TblRoleObjectKey.class)
@EntityListeners(AuditListeners.class)
public class RoleObjectEntity extends BaseEntity<RoleObjectEntity>{

    @Id
    @Column(name = "object_code")
    private String objectCode;

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "is_deleted")
    private String isDeleted;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @LastModifiedBy
    @Column(name = "update_by")
    private String updateBy;

    @CreatedBy
    @Column(name = "create_by")
    private String createBy;

    @PrePersist
    public void insert(){
        createDate = LocalDateTime.now();
    }

    @PreUpdate
    public void update(){
        updateDate = LocalDateTime.now();
    }
}
