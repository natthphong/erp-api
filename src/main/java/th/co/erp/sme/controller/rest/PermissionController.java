package th.co.erp.sme.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import th.co.erp.sme.base.BaseResponse;
import th.co.erp.sme.base.ResponseModel;
import th.co.erp.sme.base.list.PageBaseRequest;
import th.co.erp.sme.model.jwt.UserJwtPayload;
import th.co.erp.sme.service.permission.PermissionListService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/permission")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionListService permissionListService;

    @GetMapping("/me")
    public BaseResponse getAllPermission() {
        List<GrantedAuthority> authorities = new ArrayList<>(
                SecurityContextHolder.getContext().getAuthentication().getAuthorities()
        );
        UserJwtPayload jwtPayload = (UserJwtPayload) SecurityContextHolder.getContext().getAuthentication().getCredentials();
//        List<String> permissions =
        Map<String, List<String>> permissionsMap = new HashMap<>();
        permissionsMap.put(jwtPayload.getRole(), authorities.stream().map(GrantedAuthority::getAuthority).toList());

        return ResponseModel
                .builder()
                .body(permissionsMap)
                .build();
    }

    @PostMapping("/list")
    @PreAuthorize("hasAnyRole('API_PERMISSION_LIST')")
    public BaseResponse getListPermissions(@RequestBody PageBaseRequest req) {
        return permissionListService.execute(req);
    }

}
