package net.minecraft.src;
import java.util.Random;

public class BlockDyeableCobbleStone extends BlockDyeable {

  public BlockDyeableCobbleStone(int i, int j, Material material) {
    super(i, j, material);
  }

  public int getBlockTextureFromSideAndMetadata(int i, int j) {
    return 227;
  }

  public int idDropped(int i, Random random) {
    return Block.cobblestone.blockID;
  }

}
