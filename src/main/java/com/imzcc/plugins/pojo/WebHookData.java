package com.imzcc.plugins.pojo;// WebHookData.java


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WebHookData {
    private long ec;
    private String em;
    private Data data;
}

