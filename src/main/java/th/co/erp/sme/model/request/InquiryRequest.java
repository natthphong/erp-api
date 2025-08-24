package th.co.erp.sme.model.request;

import lombok.*;
import th.co.erp.sme.model.base.BaseRequest;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InquiryRequest extends BaseRequest {
    private String email;
}
