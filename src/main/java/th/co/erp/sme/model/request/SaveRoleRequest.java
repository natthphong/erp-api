package th.co.erp.sme.model.request;


import lombok.*;
import th.co.erp.sme.base.BaseRequest;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveRoleRequest extends BaseRequest {
    private String id;
    private String code;
    private String isDeleted;
    private String flagDelete;
}
