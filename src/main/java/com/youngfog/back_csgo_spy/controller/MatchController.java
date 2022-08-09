package com.youngfog.back_csgo_spy.controller;

import com.youngfog.back_csgo_spy.domain.Match;
import com.youngfog.back_csgo_spy.domain.MatchInf;
import com.youngfog.back_csgo_spy.domain.Player;
import com.youngfog.back_csgo_spy.domain.ResponseResult;
import com.youngfog.back_csgo_spy.service.MatchService;
import com.youngfog.back_csgo_spy.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/match")
public class MatchController {
    @Autowired
    private MatchService matchService;
    @Autowired
    private PlayerService playerService;

    @GetMapping("/getAll")
    public ResponseResult getAllMatch() {
        return new ResponseResult(200,"已返回所有比赛信息",matchService.getAllMatch());
    }

    @GetMapping("/getAllWithPlayerInf")
    public ResponseResult getAllMatchWithPlayerInf() {
        List<Match> matches= matchService.getAllMatch();
        List<MatchInf> matchInfs=new ArrayList<>();
        for (Match match:matches){
            MatchInf matchInf =new MatchInf(match.getId(),match.getStatus(),playerService.getPlayersPublicly(match.getId()));
            matchInfs.add(matchInf);
        }
        return new ResponseResult(200,"已返回所有比赛信息及其附属的选手信息",matchInfs);
    }

    @GetMapping("/create")
    public ResponseResult createMatch() {
        String match_id=matchService.createMatch();
        if (match_id==null){
            return new ResponseResult(403,"比赛已经存在，请勿重复创建");
        }
        return new ResponseResult(200,"成功创建比赛",match_id);
    }

    @GetMapping("/isExisting")
    public ResponseResult isMatchExisting(String match_id) {
        if(matchService.isMatchExisting(match_id))
            return new ResponseResult(200,"存在",true);
        else
            return new ResponseResult(200,"不存在",false);
    }

    @GetMapping("/start")
    public ResponseResult startMatch(String match_id) {
        if (!matchService.isMatchExisting(match_id))
            return new ResponseResult(403,"比赛不存在");
        if (matchService.getMatchByMatch_id(match_id).getStatus()!=0)
            return new ResponseResult(403,"比赛未处于未开始状态，无法开始");
        List<Player> players= matchService.getPlayersByMatch_id(match_id);
        if (players.size()!=10)
            return new ResponseResult(403,"比赛人数不够，不允许开始");
        List<String> A_ids=new ArrayList<>();
        List<String> B_ids=new ArrayList<>();
        for (Player player:players){
            if (player.getPlayer_id().contains("A")){
                A_ids.add(player.getPlayer_id());
            }
            else {
                B_ids.add(player.getPlayer_id());
            }
        }
        int spy_A1, spy_A2=0, spy_B1, spy_B2=0;
        List<String> spy_ids=new ArrayList<>();
        Random random = new Random();
        spy_A1 = random.nextInt(5)+1;
        spy_ids.add(A_ids.get(spy_A1-1));
        while (spy_A2==0 || spy_A2==spy_A1) {
            spy_A2 = random.nextInt(5)+1;
        }
        spy_ids.add(A_ids.get(spy_A2-1));
        spy_B1 = random.nextInt(5)+1;
        spy_ids.add(B_ids.get(spy_B1-1));
        while (spy_B2==0 || spy_B2==spy_B1) {
            spy_B2 = random.nextInt(5)+1;
        }
        spy_ids.add(B_ids.get(spy_B2-1));
        for (String spy_id : spy_ids) {
            playerService.setPlayerIsSpy(match_id,spy_id,1);
        }
        matchService.changeMatchStatus(match_id,1);
        return new ResponseResult(200,"已更改比赛状态为开始，并成功分配四位卧底",spy_ids);
    }

    @GetMapping("/end")
    public ResponseResult endMatch(String match_id) {
        if (!matchService.isMatchExisting(match_id))
            return new ResponseResult(403,"比赛不存在");
        if (matchService.getMatchByMatch_id(match_id).getStatus()!=1)
            return new ResponseResult(403,"比赛未处于开始状态，无法结束");
        matchService.changeMatchStatus(match_id,2);
        return new ResponseResult(200,"已更改比赛状态为结束");
    }

    @GetMapping("getStatus")
    public ResponseResult getStatus(String match_id) {
        if (!matchService.isMatchExisting(match_id))
            return new ResponseResult(403,"比赛不存在");
        else
            return new ResponseResult(200,"已获取比赛状态",matchService.getMatchByMatch_id(match_id).getStatus());
    }
}
