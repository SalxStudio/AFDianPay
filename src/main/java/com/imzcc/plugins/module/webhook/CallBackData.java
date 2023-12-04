package com.imzcc.plugins.module.webhook;

import com.imzcc.plugins.module.jooq.tables.records.AfdianOrderRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CallBackData {
    private String type;
    private AfdianOrderRecord order;
}
