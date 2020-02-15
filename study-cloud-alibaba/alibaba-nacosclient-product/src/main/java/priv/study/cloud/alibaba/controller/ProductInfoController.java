package priv.study.cloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import priv.study.cloud.alibaba.common.entity.OrderInfo;
import priv.study.cloud.alibaba.common.entity.ProductInfo;
import priv.study.cloud.alibaba.feignapi.productcenter.ProductCenterFeignApi;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@RestController
public class ProductInfoController implements ProductCenterFeignApi {

    private final HttpServletRequest servletRequest;

    public ProductInfoController(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    @Override
    @GetMapping("/getProductInfoByOrderId/{orderId}")
    public List<ProductInfo> getProductInfoByOrderId(@PathVariable(value = "orderId") int orderId) {
        log.info("我被调用");
        String token = servletRequest.getHeader("token");
        log.info(token);
        int i = new Random().nextInt(10) + 1;
        List<ProductInfo> productInfos = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            ProductInfo productInfo = new ProductInfo();
            productInfo.setId(i + j);
            productInfo.setOrderId(orderId);
            productInfo.setProductName("产品" + j);
            productInfo.setProductPrice(new BigDecimal((i + j) * 100));
            productInfos.add(productInfo);
        }
        return productInfos;
    }

    @Override
    @PostMapping("/saveProduct")
    public List<ProductInfo> saveProduct(@RequestBody OrderInfo orderInfo) {
        return orderInfo.getProductInfoList();
    }
}
