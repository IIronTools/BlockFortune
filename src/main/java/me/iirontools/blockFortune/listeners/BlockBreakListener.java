package me.iirontools.blockFortune.listeners;

import me.iirontools.blockFortune.configs.MainConfig;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;


public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Material brokenBlockType = event.getBlock().getType();

        if (MainConfig.getInstance().getFortuneMaterials().contains(brokenBlockType)) {
            event.setDropItems(false);
            Player player = event.getPlayer();

            ItemStack dropItem = new ItemStack(brokenBlockType, MainConfig.getInstance().getFortuneMultiplier());

            player.getInventory().addItem(dropItem);
        }


    }
}
