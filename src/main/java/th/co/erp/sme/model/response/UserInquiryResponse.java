package th.co.erp.sme.model.response;

import lombok.*;
import th.co.erp.sme.base.BaseResponse;
import th.co.erp.sme.model.wrapper.UserAddressModel;
import th.co.erp.sme.model.wrapper.UserInquiryModel;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInquiryResponse extends BaseResponse {
    private UserInquiryModel userInfo;
    private List<UserAddressModel> userAddress;

}
