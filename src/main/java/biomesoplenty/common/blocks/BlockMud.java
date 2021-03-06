package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPItemHelper;

public class BlockMud extends Block
{
	private static final String[] types = new String[] {"mud", "quicksand"};

	private IIcon[] textures;

	public BlockMud()
	{
		//TODO:	Material.sand
		super(Material.sand);
		
		//TODO: this.setHardness
		this.setHardness(0.6F);
		this.setHarvestLevel("shovel", 0);
		
		//TODO setStepSound(Block.soundSandFootstep)
		this.setStepSound(Block.soundTypeSand);
	
		//TODO: this.setCreativeTab()
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[types.length];

		for (int i = 0; i < types.length; ++i) 
		{
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+ types[i]);
		}
	}

	@Override
	//TODO:		 getIcon()
	public IIcon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	//TODO:		getSubBlocks()
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < types.length; ++i) 
		{
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	//TODO: getCollisionBoundingBoxFromPool
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 0)
		{
			float var5 = 0.35F;
			return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + 1 - var5, z + 1);
		}
		else
			return null;
	}

	@Override
	//TODO:		onEntityCollidedWithBlock()
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (world.getBlockMetadata(x, y, z) == 0)
		{
			if (entity instanceof EntityPlayer)
			{
				InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

				if (inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == BOPItemHelper.get("wadingBoots"))
				{
					return;
				}
			}

			entity.motionX *= 0.1D;
			entity.motionZ *= 0.1D;
		}
		else
		{
			entity.setInWeb();
		}
	}

	//@Override
	//TODO:	   getItemDropped()
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		if (metadata == 0)
			return BOPItemHelper.get("mudball");
		else
			//TODO: getItemFromBlock()
			return Item.getItemFromBlock(this);
	}

	@Override
	//TODO     damageDropped()
	public int damageDropped(int meta)
	{
		return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		if (meta == 0)
			return 4;
		else
			return 1;
	}
}