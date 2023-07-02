package com.sem.customsounds;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("customsounds")
public interface CustomSoundsConfig extends Config
{
	@ConfigItem(
		keyName = "soundVolume",
		name = "Sound volume",
		description = "Adjust how loud the audio is played!",
		position = 0
	)
	default int soundVolume() {
		return 100;
	}

	@ConfigItem(
		keyName = "announceCollectionLog",
		name = "New collection log entry",
		description = "Should a sound play when you fill in a new slot in your collection log? This one relies on you having chat messages (included with the popup option) enabled in game settings!",
		position = 1
	)
	default boolean announceCollectionLog() {
		return true;
	}
}
