package net.minecraft.src;

public class ItemDyeablePlanks extends ItemDyeable {
  public ItemDyeablePlanks(int i) {
    super(i);
    setItemName("Dyed Planks");
  }

  public int getIconFromDamage(int i) {
    return 228;
  }
}
