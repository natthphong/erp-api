package th.co.erp.sme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.co.erp.sme.model.entity.CompanySettingEntity;

@Repository
public interface CompanySettingRepository extends JpaRepository<CompanySettingEntity, Integer> {}
