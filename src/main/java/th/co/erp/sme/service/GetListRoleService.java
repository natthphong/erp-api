package th.co.erp.sme.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import th.co.erp.sme.base.BaseRequest;
import th.co.erp.sme.base.BaseResponse;
import th.co.erp.sme.base.BaseService;
import th.co.erp.sme.util.JsonHelper;
import th.co.erp.sme.base.ResponseModel;
import th.co.erp.sme.model.wrapper.RoleModel;
import th.co.erp.sme.repository.RoleRepository;
import th.co.erp.sme.util.Constant;
import th.co.erp.sme.util.SessionStoreUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetListRoleService implements BaseService<BaseRequest, BaseResponse> {

    private final RoleRepository roleRepository;
    private final SessionStoreUtils sessionStoreUtils;

    @Override
    public BaseResponse execute(BaseRequest baseRequest) {
        List<RoleModel> list = sessionStoreUtils.hGetAll(Constant.ROLE_LIST);
        if (list.isEmpty()) {
            Map<String,String> map = new HashMap<>();
            list = roleRepository.findAllByOrderByRoleCodeAsc()
                    .stream()
                    .map(e -> {
                        RoleModel temp = new RoleModel();
                        BeanUtils.copyProperties(e, temp);
                        map.put(temp.getRoleCode(), JsonHelper.objectToJsonString(temp));
                        return temp;
                    }).toList();
            sessionStoreUtils.hSetAll(Constant.ROLE_LIST,map);
        }
        return new ResponseModel<>().successWithBody(list);
    }
}
