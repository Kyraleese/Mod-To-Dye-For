package net.minecraft.src;
import java.util.Random;

public class BlockDyeableCloth extends BlockDyeable {

  public BlockDyeableCloth(int i, int j, Material material) {
    super(i, j, material);
  }

  public int getBlockTextureFromSideAndMetadata(int i, int j) {
    return 64;
  }

  public int idDropped(int i, Random random) {
    return Block.planks.blockID;
  }

}
