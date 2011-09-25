package net.minecraft.src;
import java.util.Random;

public class BlockDyeablePlanks extends BlockDyeable {

  public BlockDyeablePlanks(int i, int j, Material material) {
    super(i, j, material);
  }

  public int getBlockTextureFromSideAndMetadata(int i, int j) {
    return 228;
  }

  public int idDropped(int i, Random random) {
    return Block.planks.blockID;
  }

}
