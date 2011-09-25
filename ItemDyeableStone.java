package net.minecraft.src;

public class ItemDyeableStone extends ItemDyeable {
  public ItemDyeableStone(int i) {
    super(i);
    setItemName("Dyed Stone");
  }

  public int getIconFromDamage(int i) {
    return 226;
  }
}
