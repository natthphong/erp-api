package th.co.erp.sme.controller.rest;


import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import th.co.erp.sme.model.base.BaseRequest;
import th.co.erp.sme.model.base.BaseResponse;
import th.co.erp.sme.model.request.*;
import th.co.erp.sme.service.*;
import th.co.erp.sme.model.request.*;
import th.co.erp.sme.model.wrapper.UserInquiryModel;
import th.co.erp.sme.service.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AdminController {
    private final GetListRoleService getListRoleService;
    private final GetListObjectService getListObjectService;
    private final GetListUserService getListUserService;
    private final InquiryUserService inquiryUserService;
//    private final GenerateReportService generateReportService;
    private final SaveRoleObjectService saveRoleObjectService;
    private final SaveObjectService saveObjectService;
    private final SaveRoleService saveRoleService;

    @GetMapping("/role/list")
    @PreAuthorize("hasAnyRole('ADMIN_ALL','ADMIN_GET' ,'OWNER')")
    public BaseResponse roleList() {
        return getListRoleService.execute(new BaseRequest());
    }


    @GetMapping("/object/list")
    @PreAuthorize("hasAnyRole('ADMIN_ALL','ADMIN_GET','OWNER')")
    public BaseResponse objectList() {
        return getListObjectService.execute(new BaseRequest());
    }

    @GetMapping("/user/list")
    @PreAuthorize("hasAnyRole('ADMIN_ALL','ADMIN_GET','OWNER')")
    public BaseResponse userList(Pageable pageable) {
        return getListUserService.execute(new PageRequest(pageable));
    }

    @PostMapping("/user/inquiry")
    @PreAuthorize("hasAnyRole('ADMIN_ALL','ADMIN_GET','OWNER')")
    public BaseResponse userInquiry(@RequestBody InquiryRequest inquiryRequest) {
        return inquiryUserService.execute(inquiryRequest);
    }

//    @PostMapping("/user/report")
//    @PreAuthorize("hasAnyRole('ADMIN_ALL','ADMIN_GET','ADMIN_UPDATE_ROLE','ADMIN_ASSIGN_ROLE','ADMIN_ASSIGN_ROLE_ADMIN','OWNER')")
//    public BaseResponse userReport(@RequestBody(required = false) UserReportRequest userReportRequest, HttpServletResponse response){
//        return generateReportService.execute(response,userReportRequest);
//    }

    @PostMapping("/user/role-object/save")
    @PreAuthorize("hasAnyRole('ADMIN_ALL','ADMIN_UPDATE_ROLE','ADMIN_ASSIGN_ROLE','ADMIN_ASSIGN_ROLE_ADMIN','OWNER')")
    public BaseResponse roleObjectSave(@RequestBody @Valid SaveObjectRoleRequest request){
        return saveRoleObjectService.execute(request);
    }

    @PostMapping("/user/role/save")
    @PreAuthorize("hasAnyRole('ADMIN_ALL','ADMIN_UPDATE_ROLE','ADMIN_ASSIGN_ROLE','ADMIN_ASSIGN_ROLE_ADMIN','OWNER')")
    public BaseResponse roleSave(@RequestBody SaveRoleRequest request){
        return saveRoleService.execute(request);
    }
    @PostMapping("/user/object/save")
    @PreAuthorize("hasAnyRole('ADMIN_ALL','ADMIN_ASSIGN_ROLE','ADMIN_ASSIGN_ROLE_ADMIN','OWNER')")
    public BaseResponse ObjectSave(@RequestBody @Valid UserInquiryModel.RoleObjectModel request){
            return saveObjectService.execute(request);
    }

}
