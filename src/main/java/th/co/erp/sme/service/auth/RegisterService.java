package th.co.erp.sme.service.auth;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import th.co.erp.sme.base.BaseService;
import th.co.erp.sme.base.ResponseModel;
import th.co.erp.sme.model.entity.EmployeeEntity;
import th.co.erp.sme.model.request.auth.RegisterRequest;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterService implements BaseService<RegisterRequest, ResponseModel<Void>> {

    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public ResponseModel<Void> execute(RegisterRequest request) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(request,employeeEntity);
        String passwordHash = passwordEncoder.encode(request.getPassword());
        employeeEntity.setPasswordHash(passwordHash);
        entityManager.persist(employeeEntity);

        return ResponseModel.success();
    }
}
