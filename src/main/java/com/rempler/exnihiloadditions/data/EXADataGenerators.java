package com.rempler.exnihiloadditions.data;

import com.rempler.exnihiloadditions.ExNihiloAdditions;
import com.rempler.exnihiloadditions.data.loot.EXALootProvider;
import com.rempler.exnihiloadditions.data.loot.botania.EXABotaniaLootModifierGenerator;
import com.rempler.exnihiloadditions.data.models.EXAItemModelGenerator;
import com.rempler.exnihiloadditions.data.recipe.EXARecipeGenerator;
import com.rempler.exnihiloadditions.data.tags.EXATagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EXADataGenerators {
    @SubscribeEvent
    public static void gatherData(@Nonnull final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new EXALootProvider(output));
        generator.addProvider(event.includeServer(), new EXATagProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new EXARecipeGenerator(output, existingFileHelper));

        generator.addProvider(event.includeClient(), new EXALangProvider(output, "en_us"));
        generator.addProvider(event.includeClient(), new EXABlockStateGenerator(output, existingFileHelper));
        if (ExNihiloAdditions.isBotaniaLoaded) {
            generator.addProvider(event.includeServer(), new EXABotaniaLootModifierGenerator(output));
            generator.addProvider(event.includeClient(), new EXAItemModelGenerator(output, existingFileHelper));
        }
    }
}
