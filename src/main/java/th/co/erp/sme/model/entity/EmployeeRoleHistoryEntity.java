package th.co.erp.sme.model.entity;

import jakarta.persistence.*;
import lombok.*;
import th.co.erp.sme.configuration.jpa.AuditListeners;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_employee_role_history")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditListeners.class)
public class EmployeeRoleHistoryEntity extends BaseEntity<EmployeeRoleHistoryEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "company_code", nullable = false)
    private String companyCode;

    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;

    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "approved_by")
    private Integer approvedBy;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "note")
    private String note;

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
