package com.youngfog.back_csgo_spy.service;

import com.youngfog.back_csgo_spy.domain.Match;
import com.youngfog.back_csgo_spy.domain.Player;
import com.youngfog.back_csgo_spy.domain.Vote;
import com.youngfog.back_csgo_spy.mapper.MatchMapper;
import com.youngfog.back_csgo_spy.mapper.PlayerMapper;
import com.youngfog.back_csgo_spy.mapper.VoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MatchService {
    @Autowired
    private MatchMapper matchMapper;
    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private VoteMapper voteMapper;

    public List<Match> getAllMatch() {
        return matchMapper.findAll();
    }
    public String createMatch() {
        Date date = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String match_id = sdf.format(date);
        if (matchMapper.getMatchById(match_id)!=null) {
            return null;
        };
        matchMapper.addMatch(new Match(match_id,0));
        return match_id;
    }

    public Boolean isMatchExisting(String match_id) {
        if(matchMapper.getMatchById(match_id)==null)
            return false;
        else
            return true;
    }

    public void changeMatchStatus(String match_id,Integer status) {
        matchMapper.updateMatch(new Match(match_id,status));
    }

    public Match getMatchByMatch_id(String match_id){
        return matchMapper.getMatchById(match_id);
    }

    public List<Player> getPlayersByMatch_id(String match_id) {
        return playerMapper.getPlayerByMatch_id(match_id);
    }

    public List<Vote> getVotesByMatch_id(String match_id) {
        return voteMapper.findAll(match_id);
    }

    public void addVote(Vote vote) {
        voteMapper.addVote(vote);
    }

}
