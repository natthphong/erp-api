package th.co.erp.sme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.co.erp.sme.model.entity.EmployeeRoleHistoryEntity;

import java.util.Optional;

@Repository
public interface EmployeeRoleHistoryRepository extends JpaRepository<EmployeeRoleHistoryEntity, Integer> {

}
