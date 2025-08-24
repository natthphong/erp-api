package th.co.erp.sme.model.wrapper;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObjectModel {
    private Integer objectId;
    private String objectCode;
    private String isDeleted;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String updateBy;
    private String createBy;
}
