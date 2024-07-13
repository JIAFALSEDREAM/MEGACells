package gripe._90.megacells.datagen;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import gripe._90.megacells.MEGACells;

@SuppressWarnings("unused")
@EventBusSubscriber(modid = MEGACells.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MEGADataGenerators {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        var generator = event.getGenerator();

        var output = generator.getPackOutput();
        generator.addProvider(event.includeClient(), new MEGALanguageProvider(output));

        var existing = event.getExistingFileHelper();
        generator.addProvider(event.includeClient(), new MEGAModelProvider(output, existing));

        var registries = event.getLookupProvider();
        generator.addProvider(event.includeServer(), new MEGARecipeProvider(output, registries));
        generator.addProvider(event.includeServer(), new MEGALootProvider(output, registries));

        var blockTags = new MEGATagProvider.BlockTags(output, registries, existing);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(
                event.includeServer(),
                new MEGATagProvider.ItemTags(output, registries, blockTags.contentsGetter(), existing));
    }
}
