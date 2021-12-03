package yt.sehrschlecht.voidfall.config;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import yt.sehrschlecht.voidfall.VoidFall;

import java.util.Locale;
import java.util.logging.Level;

public class Config {
    private static Config config = null;

    private final int yHeight;
    private final boolean enableSound;
    private final String sound;
    private final float volume;
    private final float pitch;
    private final boolean resetFallDistance;
    private final boolean cancelDamage;
    private final boolean changeDimensions;

    public Config(int yHeight, boolean enableSound, String sound, float volume, float pitch, boolean resetFallDistance, boolean cancelDamage, boolean changeDimensions) {
        this.yHeight = yHeight;
        this.enableSound = enableSound;
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
        this.resetFallDistance = resetFallDistance;
        this.cancelDamage = cancelDamage;
        this.changeDimensions = changeDimensions;

        config = this;
    }

    public static Config getInstance() {
        if (config == null) {
            reload();
        }
        return config;
    }

    public static void reload() {
        FileConfiguration configuration = VoidFall.getPlugin().getConfig();
        config = new Config(
                configuration.getInt("y-height"),
                configuration.getBoolean("enable-sound"),
                configuration.getString("sound"),
                configuration.getLong("volume"),
                configuration.getLong("pitch"),
                configuration.getBoolean("reset-fall-distance"),
                configuration.getBoolean("cancel-damage"),
                configuration.getBoolean("change_dimensions"));
    }

    public int getYHeight() {
        return yHeight;
    }

    public boolean shouldPlaySound() {
        return enableSound;
    }

    public Sound getSound() {
        try {
            return Sound.valueOf(sound.toUpperCase(Locale.ENGLISH));
        } catch (Exception exception) {
            VoidFall.getPlugin().getLogger().log(Level.SEVERE, "The specified sound \"" + sound + "\" is invalid!");
            return Sound.ENTITY_EVOKER_PREPARE_SUMMON;
        }
    }

    public float getVolume() {
        return volume;
    }

    public float getPitch() {
        return pitch;
    }

    public boolean shouldResetFallDistance() {
        return resetFallDistance;
    }

    public boolean shouldCancelDamage() {
        return cancelDamage;
    }

    public boolean shouldChangeDimensions() {
        return changeDimensions;
    }
}
