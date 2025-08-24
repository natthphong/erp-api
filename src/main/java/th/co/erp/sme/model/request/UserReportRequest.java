package th.co.erp.sme.model.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import th.co.erp.sme.base.BaseRequest;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReportRequest extends BaseRequest {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate from;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate to;
}
