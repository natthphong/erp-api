package th.co.erp.sme.model.base;



import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import th.co.erp.sme.util.JsonHelper;


import java.io.IOException;


@Component
@Slf4j
public class AccessException implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String jsonError  = JsonHelper.objectToJsonString(ErrorResponse.builder()
                .code("66600")
                .title("EntryPoint Invalid")
                .message(authException.getMessage())
                .description(authException.getLocalizedMessage())
                .build());
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        response.getWriter().write(jsonError);
    }
}
