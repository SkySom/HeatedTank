package io.sommers.heatedtank;

import com.teamacronymcoders.base.BaseModFoundation;
import com.teamacronymcoders.base.registrysystem.BlockRegistry;
import io.sommers.heatedtank.block.BlockHeatedTank;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = HeatedTank.MOD_ID,
        name = HeatedTank.MOD_NAME,
        version = HeatedTank.VERSION
)
public class HeatedTank extends BaseModFoundation<HeatedTank> {

    public static final String MOD_ID = "heatedtank";
    public static final String MOD_NAME = "Heated Tank";
    public static final String VERSION = "1.0.0";

    public HeatedTank() {
        super(MOD_ID, MOD_NAME, VERSION, CreativeTabs.MISC);
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
        registry.register(new BlockHeatedTank());
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
