package th.co.erp.sme.model.base;


public interface BaseService<T extends BaseRequest, V extends BaseResponse> {
    V execute(T input);
}

