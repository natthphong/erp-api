package th.co.erp.sme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.co.erp.sme.model.entity.ParamValueEntity;

@Repository
public interface ParamValueRepository extends JpaRepository<ParamValueEntity, Integer> {}
