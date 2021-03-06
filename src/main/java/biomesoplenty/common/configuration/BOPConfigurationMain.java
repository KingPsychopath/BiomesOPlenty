package biomesoplenty.common.configuration;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;

public class BOPConfigurationMain
{
	public static Configuration config;

	public static Property seenVersion;

	public static void init(File configFile)
	{
		config = new Configuration(configFile);

		try
		{
			config.load();

			seenVersion = config.get("Vars", "Seen Version", "null");
		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, "Biomes O Plenty has had a problem loading its configuration", e);
		}
		finally
		{
			if (config.hasChanged()) {
				config.save();
			}
		}
	}
}