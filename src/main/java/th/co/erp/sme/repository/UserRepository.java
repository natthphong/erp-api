package th.co.erp.sme.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import th.co.erp.sme.model.entity.UserEntity;
import th.co.erp.sme.repository.model.UserInquiry;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity ,Integer> {

    @Query(value = """
            select
            	tu.user_id as userId,
            	tu.account_no as accountNo,
            	tu.birth_day as birthDay,
            	tu.create_by as createBy,
            	tu.create_date as createDate,
            	tu.email as email,
            	tu.first_name as firstName,
            	tu.gender as gender,
            	tu.image_profile as imageProfile,
            	tu.is_deleted as isDeleted,
            	tu.last_name as lastName,
            	tu.middle_name as middleName,
            	tu.phone as phone,
            	tu.role_code as roleCode,
            	tu.score_profile as scoreProfile,
            	tu.secondary_email as secondaryEmail,
            	tu.show_phone as showPhone,
            	tu.status_profile as statusProfile,
            	tu.update_by as updateBy,
            	tu.update_date as updateDate,
            	(        SELECT STRING_AGG(to3.object_id || ',' || to3.object_code, '|')
               FROM tbl_user tu
               left outer JOIN tbl_role tr ON tr.role_code = tu.role_code
               JOIN tbl_role_object tro ON tro.user_id = tu.user_id
               JOIN tbl_object to3 ON to3.object_code = tro.object_code
               WHERE tro.is_deleted = 'N'
                 AND tu.email = :email )  as objectCode
            from
            	tbl_user tu  where tu.email = :email
            """,nativeQuery = true)
    public Optional<UserInquiry> inquiryByEmail(@Param("email") String email);


    @Query("SELECT u FROM UserEntity u WHERE u.isDeleted = :isDeleted AND DATE(u.createDate) BETWEEN :startDate AND :endDate")
    List<UserEntity> findAllByIsDeletedAndCreateDateBetween(@Param("isDeleted")String isDeleted, @Param("startDate") LocalDate startDate,@Param("endDate")  LocalDate endDate);
    @Query(value = """
            select
                        	tu.user_id as userId,
                        	tu.account_no as accountNo,
                        	tu.birth_day as birthDay,
                        	tu.create_by as createBy,
                        	tu.create_date as createDate,
                        	tu.email as email,
                        	tu.first_name as firstName,
                        	tu.gender as gender,
                        	tu.image_profile as imageProfile,
                        	tu.is_deleted as isDeleted,
                        	tu.last_name as lastName,
                        	tu.middle_name as middleName,
                        	tu.phone as phone,
                        	tu.role_code as roleCode,
                        	tu.score_profile as scoreProfile,
                        	tu.secondary_email as secondaryEmail,
                        	tu.show_phone as showPhone,
                        	tu.status_profile as statusProfile,
                        	tu.update_by as updateBy,
                        	tu.update_date as updateDate,
                        	(  SELECT STRING_AGG(to3.object_id || ',' || to3.object_code, '|')
                           FROM tbl_user tu2
                           left outer JOIN tbl_role tr ON tr.role_code = tu.role_code
                           JOIN tbl_role_object tro ON tro.user_id = tu.user_id
                           JOIN tbl_object to3 ON to3.object_code = tro.object_code
                           WHERE tro.is_deleted = 'N'
                             AND tu2.email = tu.email)  as objectCode
                        from
                        	tbl_user tu
            """,
            countQuery = """
                      select
                        	count(1)
                        from
                        	tbl_user tu
                    """
            ,nativeQuery = true)
     Page<UserInquiry> pageUser(Pageable pageable);
}
