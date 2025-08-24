package th.co.erp.sme.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import th.co.erp.sme.base.BaseRequest;
import th.co.erp.sme.base.BaseResponse;
import th.co.erp.sme.model.request.*;
import th.co.erp.sme.service.*;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {
    private final GetListRoleService getListRoleService;

    private final SaveRoleService saveRoleService;

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMIN_ALL','ADMIN_GET' ,'OWNER')")
    public BaseResponse roleList() {
        return getListRoleService.execute(new BaseRequest());
    }


    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN_ALL','ADMIN_UPDATE_ROLE','ADMIN_ASSIGN_ROLE','ADMIN_ASSIGN_ROLE_ADMIN','OWNER')")
    public BaseResponse roleSave(@RequestBody SaveRoleRequest request){
        return saveRoleService.execute(request);
    }


}
