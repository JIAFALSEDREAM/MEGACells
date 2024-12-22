package gripe._90.megacells.definition;

import appeng.core.localization.LocalizationEnum;

import gripe._90.megacells.MEGACells;

public enum MEGATranslations implements LocalizationEnum {
    AcceleratorThreads("Provides 4 co-processing threads per block."),
    ALot("A lot."),
    ClassicCellColours("MEGA: Classic Cell Colours", "pack"),
    ClassicCellColoursDesc("Old red-purple colours for both AE2 and MEGA cell tiers.", "pack"),
    Compression("Compression: %s"),
    CompressionCutoff("Bulk Compression Cutoff"),
    Contains("Contains: %s"),
    Disabled("Disabled"),
    Empty("Empty"),
    Enabled("Enabled"),
    FilterChemicalUnsupported("Filter chemical unsupported!"),
    Cutoff("Cutoff: %s"),
    MismatchedFilter("Mismatched filter!"),
    ModName("MEGA Cells", "gui"),
    NotInstalled("%s not installed."),
    PartitionedFor("Partitioned for: %s"),
    ProcessingOnly("Supports processing patterns only."),
    Quantity("Quantity: %s"),
    NotPartitioned("Not Partitioned"),
    WorkbenchCell("Cell:"),
    WorkbenchConfig("Config:");

    private final String englishText;
    private final String root;

    MEGATranslations(String englishText, String root) {
        this.englishText = englishText;
        this.root = root;
    }

    MEGATranslations(String englishText) {
        this(englishText, "gui.tooltips");
    }

    @Override
    public String getEnglishText() {
        return englishText;
    }

    @Override
    public String getTranslationKey() {
        return String.format("%s.%s.%s", root, MEGACells.MODID, name());
    }
}
