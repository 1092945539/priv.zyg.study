package priv.study.cloud.alibaba.common.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfo {
    private int id;

    private int orderId;

    private String productName;

    private BigDecimal productPrice;
}
