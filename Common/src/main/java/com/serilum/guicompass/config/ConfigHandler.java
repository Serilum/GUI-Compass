package com.serilum.guicompass.config;

import com.natamus.collective.config.DuskConfig;
import com.serilum.guicompass.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static String guiCompassFormat = "FXYZ";
	@Entry public static boolean mustHaveCompassInInventory = true;
	@Entry(options = {"Left", "Center", "Right"}) public static String compassPosition = "Left";
	@Entry(min = 0, max = 3000) public static int compassHeightOffset = 5;
	@Entry public static boolean drawTextShadow = true;
	@Entry(rgb = true) public static String RGB = "255,255,255";

	public static void initConfig() {
		configMetaData.put("guiCompassFormat", Arrays.asList(
			"What of the GUI compass should be displayed. Default: [FXYZ]. F: facing (direction), X: x coord, Y: y coord (depth), Z: z coord."
		));
		configMetaData.put("mustHaveCompassInInventory", Arrays.asList(
			"When enabled, will only show the GUI compass when a compass is present in the inventory."
		));
		configMetaData.put("compassPosition", Arrays.asList(
			"Where the compass is shown at the top of the screen."
		));
		configMetaData.put("compassHeightOffset", Arrays.asList(
			"The vertical offset (y coord) for the Compass. This determines how far down the time should be on the screen. Can be changed to prevent GUIs from overlapping."
		));
		configMetaData.put("drawTextShadow", Arrays.asList(
			"If the text displayed should have a shadow drawn below it."
		));
		configMetaData.put("RGB", Arrays.asList(
			"The colour of the compass text, as red, green and blue values from 0 to 255."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}