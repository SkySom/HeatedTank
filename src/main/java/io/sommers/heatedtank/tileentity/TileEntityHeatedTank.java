package io.sommers.heatedtank.tileentity;

import com.teamacronymcoders.base.capability.fluid.FluidHandlerInput;
import com.teamacronymcoders.base.capability.fluid.FluidHandlerOutput;
import com.teamacronymcoders.base.registrysystem.config.ConfigRegistry;
import com.teamacronymcoders.base.tileentities.TileEntitySidedBase;
import io.sommers.heatedtank.HeatedTank;
import io.sommers.heatedtank.block.BlockHeatedTank;
import io.sommers.heatedtank.block.EnumTankState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.Random;

public class TileEntityHeatedTank extends TileEntitySidedBase<IFluidHandler> implements ITickable {
    private static final Random random = new Random();
    private FluidTank fluidTank;
    private IFluidHandler outputHandler;
    private IFluidHandler inputHandler;
    private int tier;

    public TileEntityHeatedTank() {

    }

    public TileEntityHeatedTank(int tier) {
        this();
        this.tier = tier;
        createFluidTank(tier);
        this.fluidTank.setTileEntity(this);
    }

    private void createFluidTank(int tier) {
        int capacity = HeatedTank.instance.getRegistry(ConfigRegistry.class, "CONFIG").getInt("Tier " + tier + " Capacity", tier * 8000);
        this.fluidTank = new FluidTank(capacity);
        this.outputHandler = new FluidHandlerInput(fluidTank);
        this.inputHandler = new FluidHandlerOutput(fluidTank);
    }

    @Override
    public void update() {
        if (!this.getWorld().isRemote && random.nextInt(20) == 0) {
            FluidStack fluid = fluidTank.getFluid();
            if (fluid == null) {
                ensureStateIs(EnumTankState.EMPTY);
            } else {
                if (fluid.getFluid().getTemperature(fluid) > HeatedTank.instance.hotTemperature) {
                    ensureStateIs(EnumTankState.HOT);
                } else {
                    ensureStateIs(EnumTankState.COLD);
                }
            }
        }
    }

    private void ensureStateIs(EnumTankState expectedTankState) {
        IBlockState currentState = this.getWorld().getBlockState(this.getPos());
        EnumTankState currentTankState = currentState.getValue(BlockHeatedTank.TANK_STATE);
        if (currentTankState != expectedTankState) {
            world.setBlockState(this.getPos(), currentState.withProperty(BlockHeatedTank.TANK_STATE, expectedTankState));
        }
    }

    @Override
    public void readFromDisk(NBTTagCompound data) {
        super.readFromDisk(data);
        this.createFluidTank(data.getInteger("tier"));
        this.fluidTank.readFromNBT(data.getCompoundTag("tank"));
        this.fluidTank.setTileEntity(this);
    }

    @Override
    public NBTTagCompound writeToDisk(NBTTagCompound data) {
        data = super.writeToDisk(data);
        data.setInteger("tier", tier);
        data.setTag("tank", fluidTank.writeToNBT(new NBTTagCompound()));
        return data;
    }

    @Override
    public Capability<?> getCapabilityType() {
        return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
    }

    @Override
    public IFluidHandler getInternalCapability() {
        return fluidTank;
    }

    @Override
    public IFluidHandler getOutputCapability() {
        return outputHandler;
    }

    @Override
    public IFluidHandler getInputCapability() {
        return inputHandler;
    }

    @Override
    public <T> T castCapability(IFluidHandler capability) {
        return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(capability);
    }

}
