/*
 * Copyright (C) 2016 MCME
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mcmiddleearth.guidebook.listener;

import com.mcmiddleearth.guidebook.data.InfoArea;
import com.mcmiddleearth.guidebook.data.PluginData;
import com.mcmiddleearth.guidebook.util.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 *
 * @author Eriol_Eandur
 */
public class PlayerListener implements Listener{
    
    @EventHandler
    public void playerMove(PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        if(!PluginData.isRecipient(player)) {
            return;
        }
        Location playerLocation = player.getLocation();
        for(String key : PluginData.getInfoAreas().keySet()) {
            InfoArea area = PluginData.getInfoAreas().get(key);
            if(area.isInside(playerLocation) && !area.isInfomed(player)) { 
                MessageUtil.sendInfoMessage(player,ChatColor.GOLD+"Welcome to "+key+".");
                MessageUtil.sendNoPrefixInfoMessage(player, ChatColor.YELLOW+area.getDescription());
                area.addInformedPlayer(player);
            }
            if(!area.isNear(playerLocation)) {
                area.removeInformedPlayer(player);
            }
        }
    }
    
}

