package com.data_stream.rodentorigins;

import com.data_stream.rodentorigins.item.RodentItems;
import com.data_stream.rodentorigins.powers.TreePowerFactory;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.LeavesBlock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main implements ModInitializer {
    public static String MODID = "rodentorigins";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    @Override
    public void onInitialize() {
        TreePowerFactory.register();
        RodentItems.RegisterRodentItems();
    }
}
