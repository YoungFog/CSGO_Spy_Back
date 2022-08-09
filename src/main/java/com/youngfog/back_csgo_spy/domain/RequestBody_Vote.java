package com.youngfog.back_csgo_spy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestBody_Vote {
    String match_id;
    String player_id_from;
    List<String> player_name_to;
}
