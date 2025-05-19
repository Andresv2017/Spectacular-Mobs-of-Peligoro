package net.darkblade.smopmod.entity.interfaces.variants;

import net.minecraft.util.RandomSource;

/**
 * Para entidades con variantes simples aleatorias (colores, patrones, etc.).
 */
public interface RandomVariantCapable {

    void setRandomVariant(RandomSource random);
    int getVariantId();
    int getMaxVariants();

}
