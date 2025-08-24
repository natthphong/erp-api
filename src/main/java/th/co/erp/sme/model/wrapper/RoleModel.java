package th.co.erp.sme.model.wrapper;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleModel {
    private Integer roleId;

    private String roleCode;

    private String isDeleted;


    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private String updateBy;

    private String createBy;
}
