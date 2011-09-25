package net.minecraft.src;

public class ItemDyeable extends ItemBlock {
  public ItemDyeable(int i) {
    super(i);
    setHasSubtypes(true);
    setMaxDamage(0);
  }

  public int getPlacedBlockMetadata(int i) {
    return i;
  }

  public String getItemNameIS(ItemStack itemstack) {
    return (new StringBuilder()).append(super.getItemName()).append(".").append(BlockDyeable.dyeColorNames[BlockCloth.getBlockFromDye(itemstack.getItemDamage())]).toString();
  }

  public int getColorFromDamage(int i) {

    int dye = BlockDyeable.getDyeFromBlock(i);
    return BlockDyeable.dyeColors[dye];
  }

}
