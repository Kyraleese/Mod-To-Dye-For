package net.minecraft.src;
import java.util.Random;

public class BlockDyeable extends Block {

    public BlockDyeable(int i, int j, Material material) {
        super(i, j, material);
    }

    protected int damageDropped(int i) {
        return i;
    }

    public static int getBlockFromDye(int i) {
        return ~i & 0xf;
    }

    public static int getDyeFromBlock(int i) {
        return ~i & 0xf;
    }

    public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k) {
      int metadata  = iblockaccess.getBlockMetadata(i,j,k);
      return dyeColors[getDyeFromBlock(metadata)];
    }

    public int getRenderColor(int i) {
      int dye = getDyeFromBlock(i);
      return dyeColors[dye];
    }

    public static final String dyeColorNames[] = {
        "black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", 
        "lime", "yellow", "lightBlue", "magenta", "orange", "white"
    };

    public static final int dyeColors[] = {
        0x1e1b1b, 0xb3312c, 0x3b511a, 0x51301a, 0x253192, 0x7b2fbe, 0x287697, 0xd0d4d6, 0x434343, 0xd88198, 
        0x41cd34, 0xdecf2a, 0x6689d3, 0xc354cd, 0xeb8844, 0xffffff
    };


}
