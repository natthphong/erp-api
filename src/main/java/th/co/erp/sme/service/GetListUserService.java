package th.co.erp.sme.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import th.co.erp.sme.model.base.BaseResponse;
import th.co.erp.sme.model.base.BaseService;
import th.co.erp.sme.model.base.ResponseModel;
import th.co.erp.sme.model.request.PageRequest;
import th.co.erp.sme.model.wrapper.UserInquiryModel;
import th.co.erp.sme.repository.UserRepository;
import th.co.erp.sme.repository.model.UserInquiry;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetListUserService implements BaseService<PageRequest, BaseResponse> {
    private final UserRepository userRepository;

    @Override
    public BaseResponse execute(PageRequest baseRequest) {
        Page<UserInquiry> page = userRepository.pageUser(baseRequest.getPageable());
        return new ResponseModel<>()
                .successWithBody(
                        new PageImpl<>(
                                page.stream().map(e -> {
                                    UserInquiryModel temp = new UserInquiryModel();
                                    BeanUtils.copyProperties(e, temp);
                                    temp.setRoleObjectCode(Arrays.stream(temp.getObjectCode().split("\\|"))
                                            .map(o -> {
                                                List<String> tempStr = Arrays.stream(o.split("\\,")).toList();
                                                return tempStr.size() < 2 ? new UserInquiryModel.RoleObjectModel() : UserInquiryModel.RoleObjectModel
                                                        .builder()
                                                        .id(tempStr.get(0))
                                                        .code(tempStr.get(1))
                                                        .build();
                                            }).toList());
                                    temp.setObjectCode(null);
                                    return temp;
                                }).toList()
                                , baseRequest.getPageable()
                                , page.getTotalElements()
                        )
                );
    }
}
