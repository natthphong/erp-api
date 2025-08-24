package th.co.erp.sme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.co.erp.sme.model.entity.RoleEntity;

import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {

    List<RoleEntity> findAllByOrderByRoleCodeAsc();
}
