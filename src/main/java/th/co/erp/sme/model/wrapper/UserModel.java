package th.co.erp.sme.model.wrapper;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private Integer userId;
    private String email;
    private String gender;
    private String roleCode;
    private String phone;
    private String firstName;
    private String middleName;
    private String lastName;
    private String accountNo;
    private LocalDate birthDay;
    private LocalDateTime createDate;
    private String imageProfile;
    private String statusProfile;
    private String scoreProfile;
    private String isDeleted;
}
