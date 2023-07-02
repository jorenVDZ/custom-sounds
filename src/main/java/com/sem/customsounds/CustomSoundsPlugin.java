package com.sem.customsounds;

import com.google.inject.Provides;

import java.util.concurrent.ScheduledExecutorService;
import java.util.regex.Pattern;

import javax.inject.Inject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Player;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import okhttp3.OkHttpClient;

@Slf4j
@PluginDescriptor(
	name = "Custom Sounds",
	description = "Adds the ability to add various sounds to different events in the game.",
	tags = { "sounds" }
)
public class CustomSoundsPlugin extends Plugin
{
	@Inject
	private Client client;

	@Getter(AccessLevel.PACKAGE)
	@Inject
	private ClientThread clientThread;

	@Inject
	private CustomSoundsConfig config;

	@Inject
	private ScheduledExecutorService executor;

	@Inject
	private OkHttpClient okHttpClient;

	@Inject
	private SoundEngine soundEngine;

	private static final Pattern COLLECTION_LOG_ITEM_REGEX = Pattern.compile("New item added to your collection log:.*");

	@Override
	protected void startUp() throws Exception
	{
		executor.submit(() -> {
			SoundFileManager.ensureDownloadDirectoryExists();
			SoundFileManager.downloadAllMissingSounds(okHttpClient);
		});
	}

	@Override
	protected void shutDown() throws Exception
	{
		soundEngine.close();
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
		}
	}

	@Subscribe
	public void onChatMessage(ChatMessage chatMessage) {
		if (chatMessage.getType() != ChatMessageType.GAMEMESSAGE && chatMessage.getType() != ChatMessageType.SPAM) {
			return;
		}

		if (config.announceCollectionLog() && COLLECTION_LOG_ITEM_REGEX.matcher(chatMessage.getMessage()).matches()) {
			soundEngine.playClip(Sound.COLLECTION_LOG_SLOT);

		} 
	}

	@Provides
	CustomSoundsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(CustomSoundsConfig.class);
	}
}
