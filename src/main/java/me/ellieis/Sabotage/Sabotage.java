package me.ellieis.Sabotage;

import me.ellieis.Sabotage.game.config.SabotageConfig;
import me.ellieis.Sabotage.game.custom.SabotageBlocks;
import me.ellieis.Sabotage.game.custom.SabotageItems;
import me.ellieis.Sabotage.game.phase.SabotageActive;
import me.ellieis.Sabotage.game.phase.SabotageWaiting;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import xyz.nucleoid.plasmid.api.game.GameType;

import java.util.ArrayList;
import java.util.List;

public class Sabotage implements ModInitializer {
	public static final String MOD_ID = "sabotage";
	public static final List<SabotageActive> activeGames = new ArrayList<>();

	@Override
	public void onInitialize() {
		SabotageBlocks.register();
		SabotageItems.register();
		GameType.register(Sabotage.identifier("sabotage"), SabotageConfig.CODEC, SabotageWaiting::Open);
	}

	public static Identifier identifier(String value) {
		return Identifier.of(MOD_ID, value);
	}
}