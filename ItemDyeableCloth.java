package net.minecraft.src;

public class ItemDyeableCloth extends ItemDyeable {
  public ItemDyeableCloth(int i) {
    super(i);
    setItemName("Dyed Cloth");
  }

  public int getIconFromDamage(int i) {
    return 64;
  }

}
