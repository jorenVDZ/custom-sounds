package com.sem.customsounds;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("customsounds")
public interface CustomSoundsConfig extends Config
{
	@ConfigItem(
		keyName = "greeting",
		name = "Welcome Greeting",
		description = "The message to show to the user when they login"
	)
	default String greeting()
	{
		return "Hello";
	}

	@ConfigItem(
		keyName = "soundVolume",
		name = "Sound volume",
		description = "Adjust how loud the audio is played!"
	)
	default int soundVolume() {
		return 100;
	}

	@ConfigItem(
		keyName = "announceCollectionLog",
		name = "New collection log entry",
		description = "Should a sound play when you fill in a new slot in your collection log? This one relies on you having chat messages (included with the popup option) enabled in game settings!"
	)
	default boolean announceCollectionLog() {
		return true;
	}
}
