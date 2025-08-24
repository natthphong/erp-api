package th.co.erp.sme.model.base;



import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import th.co.erp.sme.util.JsonHelper;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class LoggingRequestFilter extends OncePerRequestFilter {

    private static final String SENSITIVE_DATA_MASK = "xxxxxx";
    private static final List<String> SENSITIVE_HEADERS = Arrays.asList("authorization", "x-api-key");
    private static final List<MediaType> SUPPORTED_MEDIA_TYPES = Arrays.asList(MediaType.APPLICATION_JSON);






    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getRequestURI().contains("/actuator");
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest httpServletRequest,
                                    @NonNull HttpServletResponse httpServletResponse,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        List<String> object_role = JsonHelper.jsonStringToObjectTypeRef(httpServletRequest.getHeader("x_user_object_role"), new TypeReference<List<String>>() {
        });
        List<GrantedAuthority> authorities = new ArrayList<>();
        object_role.forEach(e -> authorities.add(new SimpleGrantedAuthority("ROLE_" + e)));
        Authentication authentication = new UsernamePasswordAuthenticationToken( httpServletRequest.getHeader("x_user_first_name"),httpServletRequest.getHeader("x_user_email"), authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (isAsyncDispatch(httpServletRequest)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            doFilterWrapped(wrapRequest(httpServletRequest), wrapResponse(httpServletResponse), filterChain);
        }
    }

    protected void doFilterWrapped(ContentCachingRequestWrapper contentCachingRequestWrapper,
                                   ContentCachingResponseWrapper contentCachingResponseWrapper,
                                   FilterChain filterChain) throws ServletException, IOException {

        Enumeration<String> headerNames = contentCachingRequestWrapper.getHeaderNames();
        List<String> headerList = new ArrayList<>();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                headerList.add("Header: " + contentCachingRequestWrapper.getHeader(headerNames.nextElement()));
            }
        }

        long start = System.currentTimeMillis();
        String method = contentCachingRequestWrapper.getMethod();
        String requestUri = contentCachingRequestWrapper.getRequestURI();
        String queryString = contentCachingRequestWrapper.getQueryString();
        String headers = headerList.toString();
        try {
//            headers = Collections.list(contentCachingRequestWrapper.getHeaderNames())
//                    .stream()
//                    .map(h -> h + ":" + (isSensitiveHeader(h) ? SENSITIVE_DATA_MASK : contentCachingRequestWrapper.getHeader(h)))
//                    .collect(Collectors.joining(", "));
            filterChain.doFilter(contentCachingRequestWrapper, contentCachingResponseWrapper);
        } finally {


        }

        contentCachingResponseWrapper.copyBodyToResponse();
    }

    private String getMessage(byte[] content, String contentType) {
        if (content.length > 0) {
            MediaType mediaType = MediaType.valueOf(contentType);
            if (SUPPORTED_MEDIA_TYPES.stream().anyMatch(visibleType -> visibleType.includes(mediaType))) {
                try {
                    return new String(content, StandardCharsets.UTF_8);
                } catch (Exception e) {
                    return "";
                }
            }
        }
        return "";
    }

    private static ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
        if (request instanceof ContentCachingRequestWrapper) {
            return (ContentCachingRequestWrapper) request;
        } else {
            return new ContentCachingRequestWrapper(request);
        }
    }

    private static boolean isSensitiveHeader(String headerName) {
        return SENSITIVE_HEADERS.contains(headerName.toLowerCase());
    }

    private static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper) {
            return (ContentCachingResponseWrapper) response;
        } else {
            return new ContentCachingResponseWrapper(response);
        }
    }
}