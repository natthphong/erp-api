package th.co.erp.sme.model.request;

import lombok.*;
import org.springframework.data.domain.Pageable;
import th.co.erp.sme.model.base.BaseRequest;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageRequest extends BaseRequest {
    private Pageable pageable;
}
