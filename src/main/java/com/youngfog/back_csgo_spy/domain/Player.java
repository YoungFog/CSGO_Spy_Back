package com.youngfog.back_csgo_spy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private String match_id;
    private String player_id;
    private String name;
    private Integer isSpy;
    private Integer hasVoted;
    private Integer voted_num;
}
