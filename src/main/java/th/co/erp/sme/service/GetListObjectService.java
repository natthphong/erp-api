package th.co.erp.sme.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import th.co.erp.sme.model.base.BaseRequest;
import th.co.erp.sme.model.base.BaseResponse;
import th.co.erp.sme.model.base.BaseService;
import th.co.erp.sme.util.JsonHelper;
import th.co.erp.sme.model.base.ResponseModel;
import th.co.erp.sme.model.wrapper.ObjectModel;
import th.co.erp.sme.repository.ObjectRepository;
import th.co.erp.sme.util.Constant;
import th.co.erp.sme.util.SessionStoreUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetListObjectService implements BaseService<BaseRequest, BaseResponse> {

    private final ObjectRepository objectRepository;

    private final SessionStoreUtils sessionStoreUtils;

    @Override
    public BaseResponse execute(BaseRequest baseRequest) {
        List<ObjectModel> list = sessionStoreUtils.hGetAll(Constant.OBJECT_LIST);
        if (list.isEmpty()) {
            Map<String, String> map = new HashMap<>();
            list = objectRepository.findAllByOrderByObjectCodeAsc()
                    .stream()
                    .map(e -> {
                        ObjectModel temp = new ObjectModel();
                        BeanUtils.copyProperties(e, temp);
                        map.put(temp.getObjectCode(), JsonHelper.objectToJsonString(temp));
                        return temp;
                    })
                    .toList();
            sessionStoreUtils.hSetAll(Constant.OBJECT_LIST, map);
        }
        return new ResponseModel<>()
                .successWithBody(list);
    }
}
