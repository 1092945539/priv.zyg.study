package priv.study.cloud.alibaba.feignapi.productcenter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import priv.study.cloud.alibaba.common.entity.OrderInfo;
import priv.study.cloud.alibaba.common.entity.ProductInfo;
import priv.study.cloud.alibaba.feignapi.handler.RequestInterceptor;

import java.util.List;

@FeignClient(name = "product-center", configuration = RequestInterceptor.class)
public interface ProductCenterFeignApi {

    @GetMapping("/getProductInfoByOrderId/{orderId}")
    List<ProductInfo> getProductInfoByOrderId(@PathVariable(value = "orderId") int orderId);

    @PostMapping("/saveProduct")
    List<ProductInfo> saveProduct(@RequestBody OrderInfo orderInfo);
}
