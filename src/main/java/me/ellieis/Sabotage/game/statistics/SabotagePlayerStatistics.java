package me.ellieis.Sabotage.game.statistics;

import net.minecraft.util.Identifier;
import xyz.nucleoid.plasmid.api.game.stats.StatisticKey;

import static me.ellieis.Sabotage.Sabotage.MOD_ID;

public class SabotagePlayerStatistics {
    public static final StatisticKey<Integer> KARMA = StatisticKey.intKey(Identifier.of(MOD_ID, "karma"));
    public static final StatisticKey<Integer> CHESTS_OPENED = StatisticKey.intKey(Identifier.of(MOD_ID, "chests_opened"));
}
