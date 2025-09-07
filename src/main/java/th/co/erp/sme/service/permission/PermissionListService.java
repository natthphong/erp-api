package th.co.erp.sme.service.permission;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import th.co.erp.sme.base.BaseResponse;
import th.co.erp.sme.base.BaseService;
import th.co.erp.sme.base.ResponseModel;
import th.co.erp.sme.base.list.PageBaseRequest;
import th.co.erp.sme.service.permission.cache.PermissionCache;

@Service
@Slf4j
@RequiredArgsConstructor
public class PermissionListService implements BaseService<PageBaseRequest, BaseResponse> {
    private final PermissionCache permissionCache;
    @Override
    public BaseResponse execute(PageBaseRequest req) {
        return new ResponseModel<>()
                .successWithBody(permissionCache.findAllByPageAble(
                        req.getPageable()
                ));
    }
}
