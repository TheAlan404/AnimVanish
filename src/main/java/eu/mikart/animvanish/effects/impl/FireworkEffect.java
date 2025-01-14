package eu.mikart.animvanish.effects.impl;

import eu.mikart.animvanish.Main;
import eu.mikart.animvanish.annonations.EffectAnnotation;
import eu.mikart.animvanish.effects.Effect;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

@EffectAnnotation(name = "firework", description = "Spawns colorful fireworks", item = Material.FIREWORK_ROCKET)
public class FireworkEffect extends Effect implements Listener {

	@Override
	public void start(Player player) {
		Firework fw = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
		FireworkMeta fwm = fw.getFireworkMeta();
		fwm.setPower(1);

		// Add colors
		fwm.addEffect(org.bukkit.FireworkEffect.builder().with(org.bukkit.FireworkEffect.Type.BURST).withColor(Color.RED).build());
		fwm.addEffect(org.bukkit.FireworkEffect.builder().with(org.bukkit.FireworkEffect.Type.BURST).withColor(Color.GREEN).build());
		fwm.addEffect(org.bukkit.FireworkEffect.builder().with(org.bukkit.FireworkEffect.Type.BURST).withColor(Color.BLUE).build());
		fwm.addEffect(org.bukkit.FireworkEffect.builder().with(org.bukkit.FireworkEffect.Type.BURST).withColor(Color.YELLOW).build());
		fwm.addEffect(org.bukkit.FireworkEffect.builder().with(org.bukkit.FireworkEffect.Type.BURST).withColor(Color.AQUA).build());

		fw.setVelocity(new Vector(0, 2, 0));
		fw.setMetadata("nodamage", new FixedMetadataValue(Main.getInstance(), true));
		fw.setFireworkMeta(fwm);

		// This wasn't an intended feature, but it's so cool, the firework instantly explodes!
		// It isn't a bug, it's a feature
		fw.detonate();
		toggleVanish(player);
	}

	@EventHandler
	public void EntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Firework fw) {
			if (fw.hasMetadata("nodamage")) {
				e.setCancelled(true);
			}
		}
	}


}
