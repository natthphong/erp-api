package th.co.erp.sme.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "secondary_email")
    private String secondaryEmail;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "account_no", insertable = false, updatable = false)
    private String accountNo;

    @Column(name = "birth_day", nullable = false)
    private LocalDate birthDay;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "show_phone")
    private String showPhone;

    @Column(name = "image_profile", insertable = false, updatable = false)
    private String imageProfile;

    @Column(name = "status_profile")
    private String statusProfile;

    @Column(name = "score_profile")
    private String scoreProfile;

    @Column(name = "is_deleted")
    private String isDeleted;

    @PrePersist
    public void insert(){
        createDate = LocalDateTime.now();
    }

    @PreUpdate
    public void update(){
        updateDate = LocalDateTime.now();
    }
}
