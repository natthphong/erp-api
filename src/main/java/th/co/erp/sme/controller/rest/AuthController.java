package th.co.erp.sme.controller.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @GetMapping("/guest")
//    @PreAuthorize("hasAnyRole('GUEST')")
    public String testPermissionController(){
        return "HELLO";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String testPermissionController2(){
        return "HELLO ADMIN";
    }
}
