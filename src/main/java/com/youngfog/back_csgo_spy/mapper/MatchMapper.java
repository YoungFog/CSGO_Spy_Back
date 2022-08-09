package com.youngfog.back_csgo_spy.mapper;

import com.youngfog.back_csgo_spy.domain.Match;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MatchMapper {
    void addMatch(Match match);
    void updateMatch(Match match);
    Match getMatchById(String id);
    List<Match> findAll();
}
