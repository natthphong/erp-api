package th.co.erp.sme.repository.model;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;


public interface UserInquiry {

    Integer getUserId();
    String getAccountNo();
    LocalDate getBirthDay();
    String getCreateBy();
    LocalDateTime getCreateDate();
    String getEmail();
    String getFirstName();
    String getGender();
    String getImageProfile();
    String getIsDeleted();
    String getLastName();
    String getMiddleName();
    String getPhone();
    String getRoleCode();
    String getScoreProfile();
    String getSecondaryEmail();
    String getShowPhone();
    String getStatusProfile();
    String getUpdateBy();
    LocalDateTime getUpdateDate();
    String getObjectCode();
}
