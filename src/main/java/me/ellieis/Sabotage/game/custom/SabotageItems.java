package me.ellieis.Sabotage.game.custom;

import eu.pb4.polymer.core.api.item.PolymerBlockItem;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import me.ellieis.Sabotage.Sabotage;
import me.ellieis.Sabotage.game.custom.items.DetectiveShears;
import me.ellieis.Sabotage.game.custom.items.TesterSign;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public class SabotageItems {
    public static final Item SABOTAGE_CHEST = new PolymerBlockItem(SabotageBlocks.SABOTAGE_CHEST, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(RegistryKey.of(RegistryKeys.ITEM, Sabotage.identifier("sabotage_chest"))), Items.CHEST);
    public static final Item TESTER_WOOL = new PolymerBlockItem(SabotageBlocks.TESTER_WOOL, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(RegistryKey.of(RegistryKeys.ITEM, Sabotage.identifier("tester_wool"))), Items.WHITE_WOOL);
    public static final Item TESTER_SIGN = new TesterSign(new Item.Settings().useBlockPrefixedTranslationKey().registryKey(RegistryKey.of(RegistryKeys.ITEM, Sabotage.identifier("tester_sign"))), SabotageBlocks.TESTER_SIGN, SabotageBlocks.WALL_TESTER_SIGN);
    public static final Item DETECTIVE_SHEARS = new DetectiveShears(new Item.Settings().useBlockPrefixedTranslationKey().registryKey(RegistryKey.of(RegistryKeys.ITEM, Sabotage.identifier("detective_shears"))).maxDamage(101));
    public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .displayName(Text.translatable("gameType.sabotage.sabotage"))
            .icon(SABOTAGE_CHEST::getDefaultStack)
            .entries((context, entries) -> {
                entries.add(SABOTAGE_CHEST);
                entries.add(TESTER_WOOL);
                entries.add(TESTER_SIGN);
            })
            .build();
    public static void register() {
        register("sabotage_chest", SABOTAGE_CHEST);
        register("detective_shears", DETECTIVE_SHEARS);
        register("tester_sign", TESTER_SIGN);
        register("tester_wool", TESTER_WOOL);
        PolymerItemGroupUtils.registerPolymerItemGroup(Sabotage.identifier("general"), ITEM_GROUP);
    }
    private static <T extends Item> T register(String id, T item) {
        return Registry.register(Registries.ITEM, Sabotage.identifier(id), item);
    }
}
