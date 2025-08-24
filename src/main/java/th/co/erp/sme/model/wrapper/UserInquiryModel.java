package th.co.erp.sme.model.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import th.co.erp.sme.base.BaseRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInquiryModel {
    private Integer userId;
    private String accountNo;
    private LocalDate birthDay;
    private String createBy;
    private LocalDateTime createDate;
    private String email;
    private String firstName;
    private String gender;
    private String imageProfile;
    private String isDeleted;
    private String lastName;
    private String middleName;
    private String phone;
    private String roleCode;
    private String scoreProfile;
    private String secondaryEmail;
    private String showPhone;
    private String statusProfile;
    private String updateBy;
    private LocalDateTime updateDate;
    private String objectCode;

    private List<RoleObjectModel> roleObjectCode;


    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static  class RoleObjectModel extends BaseRequest {
        private String id;
        private String code;
        private String isDeleted;
        private String flagDelete;
    }
}
