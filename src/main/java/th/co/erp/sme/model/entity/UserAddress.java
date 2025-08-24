package th.co.erp.sme.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_user_address")
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "address_status")
    private String addressStatus;

    @Column(name = "address_soi")
    private String addressSoi;

    @Column(name = "address_road", nullable = false)
    private String addressRoad;

    @Column(name = "address_sub_district")
    private String addressSubDistrict;

    @Column(name = "address_district", nullable = false)
    private String addressDistrict;

    @Column(name = "address_province", nullable = false)
    private String addressProvince;

    @Column(name = "address_zip_code", nullable = false)
    private String addressZipCode;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "is_deleted")
    private String isDeleted;
}
