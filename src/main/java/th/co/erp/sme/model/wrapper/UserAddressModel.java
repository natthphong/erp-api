package th.co.erp.sme.model.wrapper;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddressModel {
    private Integer addressId;
    private Integer userId;
    private String addressStatus;
    private String addressSoi;
    private String addressRoad;
    private String addressSubDistrict;
    private String addressDistrict;
    private String addressProvince;
    private String addressZipCode;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String updateBy;
    private String createBy;
    private String isDeleted;
}
