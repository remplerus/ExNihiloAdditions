package com.rempler.exnihiloadditions.data;

import com.rempler.exnihiloadditions.data.loot.EXNCLootProvider;
import com.rempler.exnihiloadditions.data.recipe.EXNARecipeGenerator;
import com.rempler.exnihiloadditions.data.tags.EXNCTagProvider;
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
public class EXNADataGenerators {
    @SubscribeEvent
    public static void gatherData(@Nonnull final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new EXNCLootProvider(output));
        generator.addProvider(event.includeServer(), new EXNCTagProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new EXNARecipeGenerator(output, existingFileHelper));

        generator.addProvider(event.includeClient(), new EXNALangProvider(output, "en_us"));
        generator.addProvider(event.includeClient(), new EXNCBlockStateGenerator(output, existingFileHelper));
    }
}
