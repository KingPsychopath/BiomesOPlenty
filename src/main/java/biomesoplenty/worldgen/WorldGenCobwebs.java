package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;

public class WorldGenCobwebs extends WorldGenerator
{
	/** The ID of the plant block used in this plant generator. */
	private int plantBlockId;
	private int plantBlockMeta;

	public WorldGenCobwebs(int par1, int meta)
	{
		plantBlockId = par1;
		plantBlockMeta = meta;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		for (int l = 0; l < 64; ++l)
		{
			int i1 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int j1 = par4 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int k1 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

			if (par1World.isAirBlock(i1, j1, k1) && (par1World.getBlockId(i1, j1 + 1, k1) == Blocks.leaves2.get().blockID || par1World.getBlockId(i1, j1 + 1, k1) == Blocks.leavesColorized2.get().blockID || par1World.getBlockId(i1, j1 - 1, k1) == Block.grass.blockID))
			{
				par1World.setBlock(i1, j1, k1, Block.web.blockID, 0, 2);
			}
		}

		return true;
	}
}