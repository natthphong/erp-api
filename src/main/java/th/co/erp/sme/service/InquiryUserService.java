package th.co.erp.sme.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import th.co.erp.sme.model.base.BaseResponse;
import th.co.erp.sme.model.base.BaseService;
import th.co.erp.sme.model.base.BusinessException;
import th.co.erp.sme.model.base.ResponseModel;
import th.co.erp.sme.model.request.InquiryRequest;
import th.co.erp.sme.model.wrapper.UserInquiryModel;
import th.co.erp.sme.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InquiryUserService  implements BaseService<InquiryRequest, BaseResponse> {

    private final UserRepository userRepository;
    @Override
    public BaseResponse execute(InquiryRequest baseRequest) {
        UserInquiryModel body = new UserInquiryModel();
       userRepository.inquiryByEmail(baseRequest.getEmail())
               .ifPresentOrElse(e-> BeanUtils.copyProperties(e,body),()-> {
                   throw new BusinessException(HttpStatus.NOT_FOUND, "45000", "User Not Found");
               });
        body.setRoleObjectCode(Arrays.stream(body.getObjectCode().split("\\|")).map(e-> {
            List<String> tempStr =  Arrays.stream(e.split("\\,")).toList();
            return tempStr.size()<2?new UserInquiryModel.RoleObjectModel():UserInquiryModel.RoleObjectModel
                    .builder()
                    .id(tempStr.get(0))
                    .code(tempStr.get(1))
                    .build();
        }).toList());
        body.setObjectCode(null);
        return new ResponseModel<>()
                .successWithBody(body);
    }
}
