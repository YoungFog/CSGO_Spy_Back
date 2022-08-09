package com.youngfog.back_csgo_spy.mapper;

import com.youngfog.back_csgo_spy.domain.Vote;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VoteMapper {
    List<Vote> findAll(String match_id);
    void addVote(Vote vote);
}
