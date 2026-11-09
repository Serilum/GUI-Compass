package com.serilum.guicompass.events;

import com.mojang.blaze3d.platform.Window;
import org.joml.Matrix3x2fStack;
import com.natamus.collective.functions.ColourFunctions;
import com.natamus.collective.functions.GUIFunctions;
import com.natamus.collective.functions.PlayerFunctions;
import com.serilum.guicompass.config.ConfigHandler;
import com.serilum.guicompass.util.Util;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class GUIEvent {
	private static final Minecraft mc = Minecraft.getInstance();

	public static void renderOverlay(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker) {
		if (GUIFunctions.shouldHideGUI()) {
			return;
		}

		if (ConfigHandler.mustHaveCompassInInventory) {
			if (!PlayerFunctions.hasItemInInventory(mc.player, Util::isCompass)) {
				return;
			}
		}
		
		String coordinates = getCoordinates();

		Font fontRenderer = mc.font;
		Window scaled = mc.getWindow();
		int width = scaled.getGuiScaledWidth();
		
		int stringWidth = fontRenderer.width(coordinates);
		
		Color colour = new Color(ColourFunctions.getRgbColour(ConfigHandler.RGB));

		Matrix3x2fStack matrixStack = guiGraphics.pose();
		matrixStack.pushMatrix();
		
		int xcoord;
		if (ConfigHandler.compassPosition.equals("Left")) {
			xcoord = 5;
		}
		else if (ConfigHandler.compassPosition.equals("Center")) {
			xcoord = (width/2) - (stringWidth/2);
		}
		else {
			xcoord = width - stringWidth - 5;
		}

		drawText(fontRenderer, guiGraphics, coordinates, xcoord, ConfigHandler.compassHeightOffset, colour.getRGB(), ConfigHandler.drawTextShadow);
		
		matrixStack.popMatrix();
	}

	private static void drawText(Font font, GuiGraphicsExtractor guiGraphics, String content, int x, int y, int rgb, boolean drawShadow) {
		guiGraphics.text(font, Component.literal(content), x, y, rgb, drawShadow);
	}

	private static final List<String> direction = Arrays.asList("S", "SW", "W", "NW", "N", "NE", "E", "SE", "S");
	private static String getCoordinates() {
		Entity player = mc.getCameraEntity();
		BlockPos ppos = player.blockPosition();

		String format = ConfigHandler.guiCompassFormat;
		String toshow = "";

		if (format.contains("F")) {
			float degrees = Mth.wrapDegrees(player.getYRot());
			if (degrees < 0) {
				degrees += 360;
			}

			int facing = Math.round(degrees/45);
			toshow += direction.get(facing) + ": ";
		}
		if (format.contains("X")) {
			toshow += ppos.getX() + ", ";
		}
		if (format.contains("Y")) {
			toshow += ppos.getY() + ", ";
		}
		if (format.contains("Z")) {
			toshow += ppos.getZ() + ", ";
		}

		if (toshow.length() < 2) {
			return "";
		}

		return toshow.substring(0, toshow.length() - 2);
	}
}