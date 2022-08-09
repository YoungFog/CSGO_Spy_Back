package com.youngfog.back_csgo_spy.service;

import com.youngfog.back_csgo_spy.domain.Player;
import com.youngfog.back_csgo_spy.mapper.MatchMapper;
import com.youngfog.back_csgo_spy.mapper.PlayerMapper;
import com.youngfog.back_csgo_spy.mapper.VoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerService {
    @Autowired
    private MatchMapper matchMapper;
    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private VoteMapper voteMapper;

    public String createPlayer(String match_id,String team,String name) {
        String player_id="";
        Integer team_num;
        if (team.contains("A")){
            team_num=playerMapper.getPlayersByTeamtype(match_id,"A@").size();
            player_id+="A@";
        }
        else{
            team_num=playerMapper.getPlayersByTeamtype(match_id,"B@").size();
            player_id+="B@";
        }
        // 队伍已满
        if (team_num>=5)
            return "已满";
        // 名字重复
        List<Player> players=playerMapper.getPlayerByMatch_id(match_id);
        for (Player player:players) {
            if (name.equals(player.getName())) {
                return "重复";
            }
        }

        String uuid= UUID.randomUUID().toString().replace("-", "");
        player_id+=uuid;
        playerMapper.addPlayer(new Player(match_id,player_id,name,0,0,0));
        return player_id;
    }
    public HashMap<String,List> getPlayersPublicly(String match_id){
        List<Player> players = playerMapper.getPlayerByMatch_id(match_id);
        Collections.shuffle(players);
        HashMap<String,List> hashMap=new HashMap<>();
        hashMap.put("A队",new ArrayList<>());
        hashMap.put("B队",new ArrayList<>());
        for (Player player:players) {
            if (player.getPlayer_id().contains("A@")){
                hashMap.get("A队").add(player);
            }
            else
                hashMap.get("B队").add(player);
            player.setPlayer_id("已隐藏");
            player.setHasVoted(null);
            player.setVoted_num(null);
            player.setIsSpy(null);
        }
        return hashMap;
    }
    public Player getPlayerByIds(String match_id,String player_id) {
        return playerMapper.getPlayerByIds(match_id,player_id);
    }

    public Player getPlayerByName(String match_id,String player_name) {
        return playerMapper.getPlayerByName(match_id,player_name);
    }

    public void setPlayerIsSpy(String match_id,String player_id,Integer isSpy) {
        Player player = playerMapper.getPlayerByIds(match_id,player_id);
        player.setIsSpy(isSpy);
        playerMapper.updatePlayer(player);
    }

    public void setPlayerHasVoted(String match_id,String player_id,Integer hasVoted) {
        Player player = playerMapper.getPlayerByIds(match_id,player_id);
        player.setHasVoted(hasVoted);
        playerMapper.updatePlayer(player);
    }

    public void setPlayerVoted_num(String match_id,String player_name,Integer voted_num) {
        Player player = playerMapper.getPlayerByName(match_id,player_name);
        player.setVoted_num(voted_num);
        playerMapper.updatePlayer(player);
    }
}
