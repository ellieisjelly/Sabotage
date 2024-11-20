package me.ellieis.Sabotage.game.statistics;

import net.minecraft.server.network.ServerPlayerEntity;
import xyz.nucleoid.plasmid.api.game.stats.GameStatisticBundle;

import static me.ellieis.Sabotage.game.statistics.GlobalPlayerStatistics.TOTAL_KARMA;
import static me.ellieis.Sabotage.game.statistics.SabotagePlayerStatistics.KARMA;

public class KarmaManager {
    private final GameStatisticBundle stats;
    public KarmaManager(GameStatisticBundle stats) {
        this.stats = stats;
    }
    public int getKarma(ServerPlayerEntity plr) {
        return stats.forPlayer(plr).get(KARMA, 20);
    }
    public void setKarma(ServerPlayerEntity plr, int karma) {
        stats.forPlayer(plr).set(KARMA, karma);
        plr.setExperienceLevel(karma);
        if (karma <= 0) {
            plr.kill(plr.getServerWorld());
        }
    }
    public void incrementKarma(ServerPlayerEntity plr, int karma) {
        setKarma(plr, getKarma(plr) + karma);
        stats.global().increment(TOTAL_KARMA, karma);
    }
    public void decrementKarma(ServerPlayerEntity plr, int karma) {
        setKarma(plr, getKarma(plr) - karma);
    }
}
