package com.youngfog.back_csgo_spy.controller;

import com.youngfog.back_csgo_spy.domain.Player;
import com.youngfog.back_csgo_spy.domain.ResponseResult;
import com.youngfog.back_csgo_spy.mapper.NameMapper;
import com.youngfog.back_csgo_spy.service.MatchService;
import com.youngfog.back_csgo_spy.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private MatchService matchService;

    @GetMapping("/getPlayers")
    public ResponseResult getPlayers(String match_id) {
        if (!matchService.isMatchExisting(match_id))
            return new ResponseResult(403,"比赛不存在");
        return new ResponseResult(200,"已获取比赛的选手信息",matchService.getPlayersByMatch_id(match_id));
    }

    @GetMapping("/getPlayersPublicly")
    public ResponseResult getPlayersPublicly(String match_id) {
        if (!matchService.isMatchExisting(match_id))
            return new ResponseResult(403,"比赛不存在");
        return new ResponseResult(200,"已获取可公开的比赛的选手信息",playerService.getPlayersPublicly(match_id));
    }

    @GetMapping("/getSpyIdPublicly")
    public ResponseResult getSpyIdPublicly(String match_id) {
        if (!matchService.isMatchExisting(match_id))
            return new ResponseResult(403,"比赛不存在");
        if (matchService.getMatchByMatch_id(match_id).getStatus()==0)
            return new ResponseResult(403,"比赛未开始，无法查询卧底");
        List<String> list_spy_id=new ArrayList<>();
        List<Player> players = matchService.getPlayersByMatch_id(match_id);
        for (Player player:players) {
            if (player.getIsSpy()==1) {
                list_spy_id.add(player.getPlayer_id());
            }
        }
        return new ResponseResult(200,"已成功获取卧底的id",list_spy_id);
    }

    @GetMapping("/addPlayer")
    public ResponseResult addPlayer(String match_id, String team, String name) {
        if (!matchService.isMatchExisting(match_id))
            return new ResponseResult(403,"比赛不存在");
        String player_id=playerService.createPlayer(match_id,team,name);
        if (player_id.equals("已满")) {
            return new ResponseResult(403,"所选队伍已满");
        }
        else if (player_id.equals("重复")) {
            return new ResponseResult(403,"名字重复");
        }
        else {
            return new ResponseResult(200,"已成功创建选手",player_id);
        }
    }

    @GetMapping("/getPlayer")
    public ResponseResult getPlayer(String match_id,String player_id) {
        Player player =playerService.getPlayerByIds(match_id,player_id);
        return new ResponseResult(200,"成功获取选手的数据",player);
    }
}
