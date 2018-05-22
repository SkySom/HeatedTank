package io.sommers.heatedtank.block;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum EnumTankState implements IStringSerializable {
    EMPTY,
    COLD,
    HOT;

    public static final EnumTankState[] VALUES = values();
    @Override
    public String getName() {
        return this.toString().toLowerCase(Locale.US);
    }
}
