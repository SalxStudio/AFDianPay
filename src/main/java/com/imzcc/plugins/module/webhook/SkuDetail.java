package com.imzcc.plugins.module.webhook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SkuDetail {
    private String skuID;
    private long count;
    private String name;
    private String albumID;
    private String pic;
}
