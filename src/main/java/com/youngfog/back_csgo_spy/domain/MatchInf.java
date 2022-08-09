package com.youngfog.back_csgo_spy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchInf {
    private String id;
    private Integer status;
    HashMap<String, List> playersInf;
}