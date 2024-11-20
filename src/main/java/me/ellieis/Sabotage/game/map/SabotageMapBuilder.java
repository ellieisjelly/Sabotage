package me.ellieis.Sabotage.game.map;

import me.ellieis.Sabotage.game.config.SabotageConfig;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import xyz.nucleoid.map_templates.MapTemplate;
import xyz.nucleoid.map_templates.MapTemplateSerializer;
import xyz.nucleoid.plasmid.api.game.GameOpenException;

import java.io.IOException;

public class SabotageMapBuilder {
    public static SabotageMap build(MinecraftServer server, Identifier identifier, SabotageConfig config) {
        try {
            MapTemplate template = MapTemplateSerializer.loadFromResource(server, identifier);
            return new SabotageMap(template, config);
        } catch(IOException exception) {
            throw new GameOpenException(Text.literal("Failed to load map " + identifier), exception);
        }
    }
}
