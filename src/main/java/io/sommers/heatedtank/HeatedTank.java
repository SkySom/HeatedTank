package io.sommers.heatedtank;

import com.teamacronymcoders.base.BaseModFoundation;
import com.teamacronymcoders.base.registrysystem.BlockRegistry;
import com.teamacronymcoders.base.registrysystem.config.ConfigEntry;
import com.teamacronymcoders.base.registrysystem.config.ConfigRegistry;
import io.sommers.heatedtank.block.BlockHeatedTank;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Property.Type;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = HeatedTank.MOD_ID,
        name = HeatedTank.MOD_NAME,
        version = HeatedTank.VERSION
)
public class HeatedTank extends BaseModFoundation<HeatedTank> {

    public static final String MOD_ID = "heated_tank";
    public static final String MOD_NAME = "Heated Tank";
    public static final String VERSION = "1.0.0";

    @Instance
    public static HeatedTank instance;

    public int hotTemperature;

    public HeatedTank() {
        super(MOD_ID, MOD_NAME, VERSION, CreativeTabs.MISC);
    }

    @Override
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        createConfigs(this.getRegistry(ConfigRegistry.class, "CONFIG"));
        this.getLibProxy().addSidedBlockDomain();
    }

    private void createConfigs(ConfigRegistry config) {
        ConfigEntry temperatureConfig = new ConfigEntry("General", "Hot Temperature", Type.INTEGER,
                "500", "The Fluid Temperature, at which, the block will be considered hot");
        config.addEntry("hot_temperature", temperatureConfig);
        this.hotTemperature = temperatureConfig.getInt(500);
        for (int tier = 1; tier <= 3; tier++) {
            String propertyName = "Tier " + tier + " Capacity";
            String amount = Integer.toString(tier * 8000);
            config.addEntry(propertyName, new ConfigEntry("General", propertyName, Type.INTEGER, amount));
        }
    }

    @Override
    @EventHandler
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void registerBlocks(BlockRegistry registry) {
        super.registerBlocks(registry);
        registry.register(new BlockHeatedTank(1));
        registry.register(new BlockHeatedTank(2));
        registry.register(new BlockHeatedTank(3));
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
