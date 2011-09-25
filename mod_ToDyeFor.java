package net.minecraft.src;

import java.util.Random;
import java.io.File;
import net.minecraft.client.Minecraft;

public class mod_ToDyeFor extends BaseMod {

  public static Block dyeableStone;
  public static Block dyeableCobbleStone;
  public static Block dyeablePlanks;
  public static Block dyeableCloth;
  private static MyProp props;

  static {
    props = new MyProp((new File((new StringBuilder()).append(Minecraft.getMinecraftDir()).append("/").append("mod_todyefor.props").toString())).getPath());

    dyeableStone        = (new BlockDyeableStone((props.getInt("dyedstone")), 226, Material.rock)).setHardness(1.5F).setResistance(10F).setBlockName("dyedstone").setRequiresSelfNotify();
    dyeableCobbleStone  = (new BlockDyeableCobbleStone((props.getInt("dyedstonebrick")), 227, Material.rock)).setHardness(2.0F).setResistance(10F).setBlockName("dyedstonebrick").setRequiresSelfNotify();
    dyeablePlanks       = (new BlockDyeablePlanks((props.getInt("dyedwood")), 228, Material.wood)).setHardness(2.0F).setResistance(5F).setBlockName("dyedwood").setRequiresSelfNotify();
    dyeableCloth        = (new BlockDyeableCloth((props.getInt("dyedcloth")), 64, Material.cloth)).setHardness(0.8F).setBlockName("dyedcloth").setRequiresSelfNotify();
  }

  public static Block dyeableBlocks[] = { dyeableStone, dyeableCobbleStone, dyeablePlanks, dyeableCloth };
  public static String blockNames[] = { "stone", "cobblestone", "wooden planks", "cloth" };
  public static Block dropped[] = { Block.stone, Block.cobblestone, Block.planks, Block.cloth };

  public mod_ToDyeFor() {

    ModLoader.RegisterBlock(dyeableStone, net.minecraft.src.ItemDyeableStone.class);
    ModLoader.AddName(dyeableStone, "Dyed Stone");
    ModLoader.RegisterBlock(dyeableCobbleStone, net.minecraft.src.ItemDyeableCobbleStone.class);
    ModLoader.AddName(dyeableCobbleStone, "Dyed Cobblestone");
    ModLoader.RegisterBlock(dyeablePlanks, net.minecraft.src.ItemDyeablePlanks.class);
    ModLoader.AddName(dyeablePlanks, "Dyed Planks");
    ModLoader.RegisterBlock(dyeableCloth, net.minecraft.src.ItemDyeableCloth.class);
    ModLoader.AddName(dyeablePlanks, "Dyed Cloth");

    for(int j = 0; j < dyeableBlocks.length; j++) {
      for(int i = 0; i < 16; i++) {
          int BlockID = dyeableBlocks[j].blockID;
          String s  = (new StringBuilder().append(Item.itemsList[BlockID].getItemName()).append(".").append(BlockDyeable.dyeColorNames[BlockDyeableStone.getDyeFromBlock(i)]).append(".name").toString());
          String s1 = (new StringBuilder(BlockDyeable.dyeColorNames[BlockDyeable.getDyeFromBlock(i)]).append(" ").append(blockNames[j]).toString());

          ModLoader.AddRecipe(new ItemStack(dyeableBlocks[j], 6, BlockDyeable.getDyeFromBlock(i)), new Object[] {
              "xox","xox","xox", Character.valueOf('x'), dropped[j], Character.valueOf('o'), new ItemStack(Item.dyePowder, 1, i)
          });

          ModLoader.AddRecipe(new ItemStack(dyeableBlocks[j], 6, BlockDyeable.getDyeFromBlock(i)), new Object[] {
              "xxx","ooo","xxx", Character.valueOf('x'), dropped[j], Character.valueOf('o'), new ItemStack(Item.dyePowder, 1, i)
          });

          ModLoader.AddRecipe(new ItemStack(dropped[j], 1, 15), new Object[] {
            "x", Character.valueOf('x'), dyeableBlocks[j]
          });

          ModLoader.AddLocalization(s, s1);
      }
    }
  }

  public String Version() {
    return "1.8.1 Beta";
  }

}
