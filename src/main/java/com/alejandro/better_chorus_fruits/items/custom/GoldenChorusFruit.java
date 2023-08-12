package com.alejandro.better_chorus_fruits.items.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import org.jetbrains.annotations.NotNull;
import net.minecraftforge.common.util.ITeleporter;

import java.awt.*;

public class GoldenChorusFruit extends Item {

    public GoldenChorusFruit(Properties pProperties) {
        super(pProperties);
    }

    //when eating the item (it is a food), it will teleport the player to his spawn point (if there is no bed, the player will be teleported to the world's spawnpoint)

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {

        //check if is server side
        if (pLevel.isClientSide() || !(pEntityLiving instanceof Player)) {
            //return the original stack if it is client side
            return super.finishUsingItem(pStack, pLevel, pEntityLiving);
        }

        //get the ServerPlayer entity from the LivingEntity
        ServerPlayer player = (ServerPlayer) pEntityLiving;

        //get client player
        Player clientPlayer = (Player) pEntityLiving;

        //get the spawn point and the server world
        BlockPos spawnPoint = player.getRespawnPosition();
        ServerLevel serverWorld = player.server.getLevel(player.getRespawnDimension());

        //if the player has no respawn position, use the world's spawn position
        if (spawnPoint == null) {
            spawnPoint = player.level().getSharedSpawnPos();
        }

        //teleport the player to the spawn point's dimension and position
        player.teleportTo(serverWorld, spawnPoint.getX() + 0.5, spawnPoint.getY(), spawnPoint.getZ() + 0.5, player.getYRot(), player.getXRot());

        //music effect
        pLevel.playSound((Player)null, spawnPoint, SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
        pEntityLiving.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);

        //put item on cooldown
        player.getCooldowns().addCooldown(this, 40);

        return super.finishUsingItem(pStack, pLevel, pEntityLiving);
    }
}
