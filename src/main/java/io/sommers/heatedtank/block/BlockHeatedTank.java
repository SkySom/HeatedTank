package io.sommers.heatedtank.block;

import com.teamacronymcoders.base.blocks.BlockSidedBase;
import com.teamacronymcoders.base.blocks.IHasItemBlock;
import com.teamacronymcoders.base.blocks.properties.PropertySideType;
import io.sommers.heatedtank.tileentity.TileEntityHeatedTank;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

import javax.annotation.Nonnull;

public class BlockHeatedTank extends BlockSidedBase<TileEntityHeatedTank> {
    //public static final PropertyEnum<EnumTankState> TANK_STATE = PropertyEnum.create("tank_state", EnumTankState.class);

    public BlockHeatedTank() {
        super(Material.IRON, "heated_tank");
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new ExtendedBlockState(this, new IProperty[] {},
                new IUnlistedProperty[] {PropertySideType.SIDE_TYPE[0], PropertySideType.SIDE_TYPE[1],
                        PropertySideType.SIDE_TYPE[2], PropertySideType.SIDE_TYPE[3],
                        PropertySideType.SIDE_TYPE[4], PropertySideType.SIDE_TYPE[5]});
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Nonnull
    @Override
    public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState blockState) {
        return new TileEntityHeatedTank();
    }

    @Override
    public Class<? extends TileEntity> getTileEntityClass() {
        return TileEntityHeatedTank.class;
    }
}
