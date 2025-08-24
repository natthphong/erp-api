package th.co.erp.sme.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import th.co.erp.sme.model.base.BaseResponse;
import th.co.erp.sme.model.base.BaseService;
import th.co.erp.sme.util.JsonHelper;
import th.co.erp.sme.model.base.BusinessException;
import th.co.erp.sme.model.base.ResponseModel;
import th.co.erp.sme.model.entity.ObjectEntity;
import th.co.erp.sme.model.wrapper.ObjectModel;
import th.co.erp.sme.model.wrapper.UserInquiryModel;
import th.co.erp.sme.repository.ObjectRepository;
import th.co.erp.sme.util.Constant;
import th.co.erp.sme.util.SessionStoreUtils;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaveObjectService implements BaseService<UserInquiryModel.RoleObjectModel, BaseResponse> {
    private final ObjectRepository objectRepository;
    private final SessionStoreUtils sessionStoreUtils;
    @Override
    public BaseResponse execute(UserInquiryModel.RoleObjectModel req) {
        ObjectEntity objectEntity;
        if (Objects.isNull(req.getId())){
            objectEntity = new ObjectEntity();
            objectEntity.setObjectCode(req.getCode());
            objectEntity.setIsDeleted(Constant.FLAG_N);
        }else{
            objectEntity = objectRepository.findById(Integer.valueOf(req.getId()))
                    .orElseThrow(()->new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,"80001","object not found"));
            objectEntity.setIsDeleted(req.getIsDeleted());
            sessionStoreUtils.hDelete(Constant.OBJECT_LIST,objectEntity.getObjectCode());
            objectEntity.setObjectCode(req.getCode());
        }

        if (Constant.FLAG_Y.equalsIgnoreCase(req.getFlagDelete())  && !Objects.isNull(req.getId()) ){
            objectRepository.delete(objectEntity);
        }else{
            objectRepository.save(objectEntity);
            ObjectModel temp = new ObjectModel();
            BeanUtils.copyProperties(objectEntity, temp);

            sessionStoreUtils.hSet(Constant.OBJECT_LIST,temp.getObjectCode(), JsonHelper.objectToJsonString(temp));
        }


        return ResponseModel.success();
    }
}
