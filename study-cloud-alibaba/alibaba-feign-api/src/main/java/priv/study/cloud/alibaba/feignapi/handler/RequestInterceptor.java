package priv.study.cloud.alibaba.feignapi.handler;

import feign.RequestTemplate;
import java.util.UUID;

public class RequestInterceptor implements feign.RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.header("token", UUID.randomUUID().toString());
    }
}
