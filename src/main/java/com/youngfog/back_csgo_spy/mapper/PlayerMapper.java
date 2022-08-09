package com.youngfog.back_csgo_spy.mapper;

import com.youngfog.back_csgo_spy.domain.Player;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlayerMapper {
    void addPlayer(Player player);
    List<Player> getPlayerByMatch_id(String match_id);
    Player getPlayerByIds(@Param("match_id") String match_id, @Param("player_id")String player_id);
    Player getPlayerByName(@Param("match_id") String match_id, @Param("name")String name);
    void updatePlayer(Player player);
    List<Player> getPlayersByTeamtype(@Param("match_id")String match_id,@Param("team_type")String team_type);
}
