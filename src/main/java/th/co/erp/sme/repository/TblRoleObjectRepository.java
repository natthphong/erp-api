package th.co.erp.sme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.co.erp.sme.model.entity.RoleObjectEntity;
import th.co.erp.sme.model.entity.key.TblRoleObjectKey;

@Repository
public interface TblRoleObjectRepository extends JpaRepository<RoleObjectEntity, TblRoleObjectKey> {



}