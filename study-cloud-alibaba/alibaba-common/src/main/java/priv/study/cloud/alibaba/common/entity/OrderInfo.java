package priv.study.cloud.alibaba.common.entity;

import lombok.Data;

import java.util.List;

@Data
public class OrderInfo {
    private int id;
    private String orderCode;
    private Boolean newBusiFlag;
    private Boolean commonNew;
    private List<ProductInfo> productInfoList;
}
