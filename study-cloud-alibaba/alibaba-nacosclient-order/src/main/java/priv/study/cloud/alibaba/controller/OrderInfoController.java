package priv.study.cloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import priv.study.cloud.alibaba.common.entity.OrderInfo;
import priv.study.cloud.alibaba.feignapi.productcenter.ProductCenterFeignApi;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RefreshScope
public class OrderInfoController {

    private final RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;

    private final ProductCenterFeignApi productCenterFeignApi;

    public OrderInfoController(DiscoveryClient discoveryClient, RestTemplate restTemplate, ProductCenterFeignApi productCenterFeignApi) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
        this.productCenterFeignApi = productCenterFeignApi;
    }

    @Value("${isNewBusi}")
    private Boolean isNewBusi;

    @Value("${commonNew}")
    private Boolean commonNew;

    @GetMapping("/getServices")
    public List<String> getServices() {
        return discoveryClient.getServices();
    }

    @GetMapping("/getServiceList")
    public List<ServiceInstance> getServiceList() {
         return discoveryClient.getInstances("order-center");
    }

    @RequestMapping("/getOrderInfoById")
    public OrderInfo getOrderInfoById(int id) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(id);
        orderInfo.setOrderCode(UUID.randomUUID().toString());
        orderInfo.setNewBusiFlag(isNewBusi);
        orderInfo.setCommonNew(commonNew);
        orderInfo.setProductInfoList(productCenterFeignApi.getProductInfoByOrderId(id));
        return orderInfo;
    }

    @RequestMapping("/saveOrder")
    public OrderInfo saveOrder(@RequestBody OrderInfo order){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(1);
        orderInfo.setOrderCode(order.getOrderCode());
        orderInfo.setNewBusiFlag(isNewBusi);
        orderInfo.setCommonNew(commonNew);
        orderInfo.setProductInfoList(productCenterFeignApi.saveProduct(order));
        return orderInfo;
    }
}
