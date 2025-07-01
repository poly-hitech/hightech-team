package com.example.test.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MyOrderList {
    private Long ordersId;
    private Date ordersDate;
    private Long ordersPrice;
    private Long resourceCategoryId;
    private String resourceCategoryName;
    private Long resourceSubCategoryId;
    private String resourceSubCategoryName;
    private String resourceContent;
    private String resourceImage;
    private Long itemId;
    private String itemName;
    private Long itemPrice;
    private String itemWriter;
    private List<ResourceFile> resourceFile;
}
