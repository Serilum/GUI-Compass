package com.natamus.guicompass;

import com.mojang.blaze3d.vertex.PoseStack;
import com.natamus.guicompass.events.GUIEvent;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class ModFabricClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() { 
		registerEvents();
	}
	
	private void registerEvents() {
		HudRenderCallback.EVENT.register((PoseStack poseStack, float tickDelta) -> {
			GUIEvent.renderOverlay(poseStack, tickDelta);
		});
	}
}
