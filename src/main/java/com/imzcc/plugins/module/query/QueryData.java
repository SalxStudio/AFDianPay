package com.imzcc.plugins.module.query;// WebHookData.java


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QueryData {
    private long ec;
    private String em;
    private QueryList data;

}

