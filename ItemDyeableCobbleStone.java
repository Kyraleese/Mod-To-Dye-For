package net.minecraft.src;

public class ItemDyeableCobbleStone extends ItemDyeable {
  public ItemDyeableCobbleStone(int i) {
    super(i);
    setItemName("Dyed Cobblestone");
  }

  public int getIconFromDamage(int i) {
    return 227;
  }
}
