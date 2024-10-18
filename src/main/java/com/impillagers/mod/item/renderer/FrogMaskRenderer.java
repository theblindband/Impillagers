package com.impillagers.mod.item.renderer;

import com.impillagers.mod.Impillagers;
import com.impillagers.mod.item.custom.FrogMaskItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public final class FrogMaskRenderer extends GeoArmorRenderer<FrogMaskItem> {
    public FrogMaskRenderer() {
        super(new DefaultedItemGeoModel<>(ResourceLocation.fromNamespaceAndPath(Impillagers.MOD_ID, "armor/frog_mask"))); // Using DefaultedItemGeoModel like this puts our 'location' as item/armor/example armor in the assets folders.
    }
}
