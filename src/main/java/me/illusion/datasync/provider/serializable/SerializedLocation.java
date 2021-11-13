package me.illusion.datasync.provider.serializable;

import me.illusion.datasync.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.Serializable;

public class SerializedLocation implements Serializable {

    private String format;

    /**
     * Obtains the Bukkit location
     *
     * @return the bukkit location
     * @param player
     */
    public Location getLocation(Player player) {
        if (format == null || format.isEmpty())
            return null;

        String[] split = StringUtil.split(format, ' '); // Faster split method

        double x = Double.parseDouble(split[0]);
        double y = Double.parseDouble(split[1]);
        double z = Double.parseDouble(split[2]);
        float yaw = Float.parseFloat(split[3]);
        float pitch = Float.parseFloat(split[4]);
        String world = split[5];
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    /**
     * Updates its internal location
     *
     * @param location - The new location to update to
     * @return
     */
    public Location update(Location location) {
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        float yaw = location.getYaw();
        float pitch = location.getPitch();
        String world = location.getWorld().getName();

        this.format = x + " " + y + " " + z + " " + yaw + " " + pitch + " " + world; // Not using String.format because it is awfully slow
        return location;
    }

}
