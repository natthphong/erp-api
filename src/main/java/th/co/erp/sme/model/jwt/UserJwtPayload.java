package th.co.erp.sme.model.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserJwtPayload {
    private String role;
    private String branchCode;
    private String companyCode;
    private Integer employeeId;
    private String isActive;
    private List<String> permissions;
}
