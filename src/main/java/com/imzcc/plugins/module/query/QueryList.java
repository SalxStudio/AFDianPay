package com.imzcc.plugins.module.query;

import com.imzcc.plugins.module.jooq.tables.records.AfdianOrderRecord;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QueryList {
    private List<AfdianOrderRecord> list;
    private long totalCount;
    private long totalPage;
}
