package com.youngfog.back_csgo_spy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vote {
    private String match_id;
    private String name_from;
    private String name_to;
}
