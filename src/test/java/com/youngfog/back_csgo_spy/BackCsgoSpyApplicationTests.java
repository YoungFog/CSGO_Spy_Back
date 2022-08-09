package com.youngfog.back_csgo_spy;

import com.youngfog.back_csgo_spy.controller.MatchController;
import com.youngfog.back_csgo_spy.domain.Match;
import com.youngfog.back_csgo_spy.domain.Player;
import com.youngfog.back_csgo_spy.mapper.MatchMapper;
import com.youngfog.back_csgo_spy.mapper.PlayerMapper;
import com.youngfog.back_csgo_spy.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class BackCsgoSpyApplicationTests {

    @Autowired
    private MatchMapper matchMapper;
    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private MatchController matchController;

    @Test
    void Test(){
        System.out.println(playerMapper.getPlayersByTeamtype("20220806101529", "A@"));
    }

}
