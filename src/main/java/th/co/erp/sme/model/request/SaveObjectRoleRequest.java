package th.co.erp.sme.model.request;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import th.co.erp.sme.base.BaseRequest;
import th.co.erp.sme.model.wrapper.UserInquiryModel;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveObjectRoleRequest extends BaseRequest {
    @NotNull
    private Integer id;
    private String roleCode;
    private UserInquiryModel.RoleObjectModel roleObject;
}
