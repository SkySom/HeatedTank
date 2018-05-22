package io.sommers.heatedtank;

import com.teamacronymcoders.base.BaseModFoundation;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.event.RegistryEvent;

@Mod(
        modid = HeatedTank.MOD_ID,
        name = HeatedTank.MOD_NAME,
        version = HeatedTank.VERSION
)
public class HeatedTank extends BaseModFoundation<HeatedTank> {

    public static final String MOD_ID = "ballast";
    public static final String MOD_NAME = "Ballast";
    public static final String VERSION = "1.0.0";

    public HeatedTank() {
        super(MOD_ID, MOD_NAME, VERSION, null);
    }

    @Override
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    @EventHandler
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void registerBlocks(BlockRegistry registry) {
        super.registerBlocks(registry);
        registry.register(new BlockRailDiamondCrossing());
        registry.register(new BlockRailHolding());
        registry.register(new BlockRailWye());
    }

    @Override
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public HeatedTank getInstance() {
        return this;
    }
}
