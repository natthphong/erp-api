package th.co.erp.sme.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import th.co.erp.sme.base.BaseResponse;
import th.co.erp.sme.base.BaseService;
import th.co.erp.sme.util.JsonHelper;
import th.co.erp.sme.exception.model.BusinessException;
import th.co.erp.sme.base.ResponseModel;
import th.co.erp.sme.model.entity.RoleEntity;
import th.co.erp.sme.model.request.SaveRoleRequest;
import th.co.erp.sme.model.wrapper.RoleModel;
import th.co.erp.sme.repository.RoleRepository;
import th.co.erp.sme.util.Constant;
import th.co.erp.sme.util.SessionStoreUtils;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaveRoleService implements BaseService<SaveRoleRequest, BaseResponse> {
    private final RoleRepository roleRepository;
    private final SessionStoreUtils sessionStoreUtils;

    @Override
    public BaseResponse execute(SaveRoleRequest req) {
        RoleEntity roleEntity;
        if (Objects.isNull(req.getId())) {
            roleEntity = new RoleEntity();
            roleEntity.setRoleCode(req.getCode());
            roleEntity.setIsDeleted(Constant.FLAG_N);
        } else {
            roleEntity = roleRepository.findById(Integer.valueOf(req.getId()))
                    .orElseThrow(() -> new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "80001", "role not found"));
            roleEntity.setIsDeleted(req.getIsDeleted());
            sessionStoreUtils.hDelete(Constant.ROLE_LIST,roleEntity.getRoleCode());
            roleEntity.setRoleCode(req.getCode());
        }
        if (Constant.FLAG_Y.equalsIgnoreCase(req.getFlagDelete()) && !Objects.isNull(req.getId())) {
            roleRepository.delete(roleEntity);
        } else {
            roleRepository.save(roleEntity);
            RoleModel temp = new RoleModel();
            BeanUtils.copyProperties(roleEntity, temp);
            sessionStoreUtils.hSet(Constant.ROLE_LIST,temp.getRoleCode(), JsonHelper.objectToJsonString(temp));
        }

        return ResponseModel.success();
    }
}
