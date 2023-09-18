package com.imzcc.plugins.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Data {
    private String type;
    private Order order;
}
