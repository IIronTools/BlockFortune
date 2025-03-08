package me.iirontools.blockFortune.listeners;

import me.iirontools.blockFortune.configs.MainConfig;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;


public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Material brokenBlockType = event.getBlock().getType();

        if (MainConfig.getInstance().getMaterials().contains(brokenBlockType)) {
            event.setDropItems(false);
            Player player = event.getPlayer();
            
            Random random = new Random();

            int minFortune = MainConfig.getInstance().getFortuneMultiplierMin();
            int maxFortune = MainConfig.getInstance().getFortuneMultiplierMax();

            Material tool = event.getPlayer().getInventory().getItemInMainHand().getType();

            if (MainConfig.getInstance().getTools().contains(tool)) {
                System.out.println("debug - right tool");

                int quantity = random.nextInt(maxFortune - minFortune + 1) + minFortune;
                ItemStack dropItem = new ItemStack(brokenBlockType, quantity);
                player.getInventory().addItem(dropItem);

            }
        }
    }
}
