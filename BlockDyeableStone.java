package net.minecraft.src;
import java.util.Random;

public class BlockDyeableStone extends BlockDyeable {

  public BlockDyeableStone(int i, int j, Material material) {
    super(i, j, material);
  }

  public int getBlockTextureFromSideAndMetadata(int i, int j) {
    return 226;
  }

  public int idDropped(int i, Random random) {
    return Block.cobblestone.blockID;
  }

}
