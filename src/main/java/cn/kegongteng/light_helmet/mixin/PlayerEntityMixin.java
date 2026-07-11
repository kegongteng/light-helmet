package cn.kegongteng.light_helmet.mixin;

import cn.kegongteng.light_helmet.item.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    private BlockPos lastLightPos = null;
    private int tickCounter = 0;
    private static final int TICK_INTERVAL = 4;

    @Inject(at = @At("TAIL"), method = "tick")
    private void tick(CallbackInfo info) {
        if (++tickCounter % TICK_INTERVAL != 0) return;

        PlayerEntity player = (PlayerEntity) (Object) this;
        boolean wearing = player.getInventory().getArmorStack(3).isOf(ModItems.LIGHT_HELMET);

        if (!wearing) {
            clearLight(player);
            return;
        }

        BlockPos currentPos = player.getBlockPos().up();
        if (currentPos.equals(lastLightPos)) return;

        clearOldLight(player);
        player.getWorld().setBlockState(currentPos, Blocks.LIGHT.getDefaultState());
        lastLightPos = currentPos;
    }

    private void clearOldLight(PlayerEntity player) {
        if (lastLightPos != null && player.getWorld().getBlockState(lastLightPos).isOf(Blocks.LIGHT)) {
            player.getWorld().setBlockState(lastLightPos, Blocks.AIR.getDefaultState());
        }
    }

    private void clearLight(PlayerEntity player) {
        clearOldLight(player);
        lastLightPos = null;
    }

    @Inject(at = @At("TAIL"), method = "remove")
    private void remove(CallbackInfo info) {
        clearLight((PlayerEntity) (Object) this);
    }
}