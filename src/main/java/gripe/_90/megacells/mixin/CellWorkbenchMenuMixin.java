package gripe._90.megacells.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

import appeng.blockentity.misc.CellWorkbenchBlockEntity;
import appeng.menu.implementations.CellWorkbenchMenu;
import appeng.menu.implementations.UpgradeableMenu;

import gripe._90.megacells.item.cell.BulkCellInventory;
import gripe._90.megacells.item.cell.BulkCellItem;
import gripe._90.megacells.menu.CompressionCutoffHost;
import gripe._90.megacells.misc.CellWorkbenchHost;

@Mixin(CellWorkbenchMenu.class)
public abstract class CellWorkbenchMenuMixin extends UpgradeableMenu<CellWorkbenchBlockEntity>
        implements CompressionCutoffHost {
    public CellWorkbenchMenuMixin(MenuType<?> menuType, int id, Inventory ip, CellWorkbenchBlockEntity host) {
        super(menuType, id, ip, host);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void registerAction(int id, Inventory ip, CellWorkbenchBlockEntity te, CallbackInfo ci) {
        registerClientAction(ACTION_SET_COMPRESSION_LIMIT, this::mega$nextCompressionLimit);
    }

    @Override
    public void mega$nextCompressionLimit() {
        if (isClientSide()) {
            sendClientAction(ACTION_SET_COMPRESSION_LIMIT);
        } else {
            if (BulkCellItem.HANDLER.getCellInventory(((CellWorkbenchHost) getHost()).mega$getContainedStack(), null)
                    instanceof BulkCellInventory bulkCell) {
                var currentLimit = bulkCell.getCompressionCutoff();
                bulkCell.setCompressionCutoff(
                        currentLimit == 1 ? bulkCell.getCompressionChain().size() : currentLimit - 1);
                getHost().saveChanges();
            }
        }
    }
}
