package th.co.erp.sme.model.entity;


import jakarta.persistence.*;
import lombok.*;
import th.co.erp.sme.configuration.jpa.AuditListeners;

import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_role")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditListeners.class)
public class RoleEntity  extends BaseEntity<RoleEntity>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "is_deleted")
    private String isDeleted;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "update_by")
    private String updateBy;

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
