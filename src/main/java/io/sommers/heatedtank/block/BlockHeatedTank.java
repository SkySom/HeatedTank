package io.sommers.heatedtank.block;

import com.teamacronymcoders.base.blocks.BlockSidedBase;
import com.teamacronymcoders.base.blocks.IHasBlockStateMapper;
import com.teamacronymcoders.base.blocks.properties.PropertySideType;
import io.sommers.heatedtank.tileentity.TileEntityHeatedTank;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

import javax.annotation.Nonnull;

public class BlockHeatedTank extends BlockSidedBase<TileEntityHeatedTank> implements IHasBlockStateMapper {
    public static final PropertyEnum<EnumTankState> TANK_STATE = PropertyEnum.create("tank_state", EnumTankState.class);

    private final int tier;

    public BlockHeatedTank(int tier) {
        super(Material.IRON, "heated_tank_tier_" + tier);
        this.tier = tier;
    }

    @Override
    @Nonnull
    public BlockStateContainer createBlockState() {
        return new ExtendedBlockState(this, new IProperty[] {TANK_STATE},
                new IUnlistedProperty[] {PropertySideType.SIDE_TYPE[0], PropertySideType.SIDE_TYPE[1],
                        PropertySideType.SIDE_TYPE[2], PropertySideType.SIDE_TYPE[3],
                        PropertySideType.SIDE_TYPE[4], PropertySideType.SIDE_TYPE[5]});
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(TANK_STATE, EnumTankState.VALUES[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(TANK_STATE).ordinal();
    }

    @Override
    public String getTileName(ResourceLocation blockName) {
        return "heated_tank:heated_tank";
    }

    @Nonnull
    @Override
    public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState blockState) {
        return new TileEntityHeatedTank(tier);
    }

    @Override
    public Class<? extends TileEntity> getTileEntityClass() {
        return TileEntityHeatedTank.class;
    }
}
