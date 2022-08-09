package com.youngfog.back_csgo_spy.controller;


import com.youngfog.back_csgo_spy.domain.RequestBody_Vote;
import com.youngfog.back_csgo_spy.domain.ResponseResult;
import com.youngfog.back_csgo_spy.domain.Vote;
import com.youngfog.back_csgo_spy.service.MatchService;
import com.youngfog.back_csgo_spy.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    private MatchService matchService;
    @Autowired
    private PlayerService playerService;

    @GetMapping("/getVotes")
    public ResponseResult getVotes(String match_id) {
        if (!matchService.isMatchExisting(match_id))
            return new ResponseResult(403,"比赛不存在");
        if (matchService.getMatchByMatch_id(match_id).getStatus()!=2)
            return new ResponseResult(403,"比赛尚未结束，无法查看票数");
        return new ResponseResult(200,"已获取比赛的投票信息",matchService.getVotesByMatch_id(match_id));
    }

    @PostMapping("/add")
    public ResponseResult addVote(@RequestBody RequestBody_Vote requestBody_vote) {
        String match_id=requestBody_vote.getMatch_id();
        String player_id_from=requestBody_vote.getPlayer_id_from();
        if (!matchService.isMatchExisting(match_id))
            return new ResponseResult(403,"比赛不存在");
        if(playerService.getPlayerByIds(match_id,player_id_from)==null)
            return new ResponseResult(403,"选手不存在");
        if (matchService.getMatchByMatch_id(match_id).getStatus()!=1)
            return new ResponseResult(403,"比赛不处于进行状态，无法投票");
        if (playerService.getPlayerByIds(match_id,player_id_from).getHasVoted()==1) {
            return new ResponseResult(403,"已经投过票了，请勿重复投票");
        }
        for (String player_name_to : requestBody_vote.getPlayer_name_to()) {
            Vote vote=new Vote(match_id,
                    playerService.getPlayerByIds(match_id,player_id_from).getName(),
                    player_name_to);
            matchService.addVote(vote);
            playerService.setPlayerVoted_num(match_id,player_name_to,
                    playerService.getPlayerByName(match_id,player_name_to).getVoted_num()+1);
        }
        playerService.setPlayerHasVoted(match_id,player_id_from,1);
        return new ResponseResult(200,"成功投票,并成功更改hasVoted状态",requestBody_vote);
    }
}
