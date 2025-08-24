package th.co.erp.sme.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import th.co.erp.sme.model.base.BaseResponse;
import th.co.erp.sme.model.base.BaseService;
import th.co.erp.sme.model.base.BusinessException;
import th.co.erp.sme.model.base.ResponseModel;
import th.co.erp.sme.model.entity.RoleObjectEntity;
import th.co.erp.sme.model.entity.UserEntity;
import th.co.erp.sme.model.entity.key.TblRoleObjectKey;
import th.co.erp.sme.model.request.SaveObjectRoleRequest;
import th.co.erp.sme.repository.TblRoleObjectRepository;
import th.co.erp.sme.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaveRoleObjectService implements BaseService<SaveObjectRoleRequest, BaseResponse> {

    private final TblRoleObjectRepository tblRoleObjectRepository;
    private final UserRepository userRepository;

    @Override
    public BaseResponse execute(SaveObjectRoleRequest req) {
        if (StringUtils.isNotBlank(req.getRoleCode())) {
            UserEntity userEntity = userRepository.findById(req.getId())
                    .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "80001", "user not found"));
            userEntity.setRoleCode(req.getRoleCode());
            userRepository.save(userEntity);
        } else {
            TblRoleObjectKey key = TblRoleObjectKey
                    .builder()
                    .userId(req.getId())
                    .objectCode(req.getRoleObject().getCode())
                    .build();
            RoleObjectEntity roleObjectEntity = tblRoleObjectRepository.findById(key)
                    .orElseGet(() -> RoleObjectEntity.builder().build());
            roleObjectEntity.setUserId(req.getId());
            roleObjectEntity.setObjectCode(req.getRoleObject().getCode());
            roleObjectEntity.setIsDeleted(req.getRoleObject().getIsDeleted());
            tblRoleObjectRepository.save(roleObjectEntity);
        }

        return ResponseModel.success();
    }
}
