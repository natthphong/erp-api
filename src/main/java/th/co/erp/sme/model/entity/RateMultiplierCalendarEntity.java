package th.co.erp.sme.model.entity;

import jakarta.persistence.*;
import lombok.*;
import th.co.erp.sme.configuration.jpa.AuditListeners;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_rate_multiplier_calendar", uniqueConstraints = {
    @UniqueConstraint(name = "uk_tbl_rate_multiplier_calendar__branch_code__company_code__rate_date__rate_type_code__is_deleted", columnNames = {"branch_code", "company_code", "rate_date", "rate_type_code", "is_deleted"})
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditListeners.class)
public class RateMultiplierCalendarEntity extends BaseEntity<RateMultiplierCalendarEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "company_code", nullable = false)
    private String companyCode;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "rate_date", nullable = false)
    private LocalDate rateDate;

    @Column(name = "rate_type_code", nullable = false)
    private String rateTypeCode;

    @Column(name = "multiplier", precision = 12, scale = 4, nullable = false)
    private BigDecimal multiplier;

    @Column(name = "reason")
    private String reason;

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
