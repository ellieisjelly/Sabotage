package me.ellieis.Sabotage.game.statistics;

import net.minecraft.util.Identifier;
import xyz.nucleoid.plasmid.api.game.stats.StatisticKey;

import static me.ellieis.Sabotage.Sabotage.MOD_ID;

public class GlobalPlayerStatistics {
    public static final StatisticKey<Integer> TOTAL_KARMA = StatisticKey.intKey(Identifier.of(MOD_ID, "total_karma"));
    public static final StatisticKey<Integer> TOTAL_CHESTS_OPENED = StatisticKey.intKey(Identifier.of(MOD_ID, "total_chests_opened"));
}
