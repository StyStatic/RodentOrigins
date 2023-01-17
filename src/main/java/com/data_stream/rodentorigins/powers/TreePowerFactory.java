package com.data_stream.rodentorigins.powers;

import com.data_stream.rodentorigins.Main;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import io.github.apace100.calio.data.SerializableData;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;

import java.util.*;

public class TreePowerFactory {

    private static void register(PowerFactory<?> factory) {
        Registry.register(ApoliRegistries.POWER_FACTORY, factory.getSerializerId(), factory);
    }

    public static void register() {
        register(new PowerFactory<>(new Identifier(Main.MODID, "tree_felling"),
                new SerializableData(),
                data ->
                        (type, entity) -> new MultiMinePower(type, entity, (pl, bs, bp) -> {
                            Set<BlockPos> affected = new HashSet<>();
                            Queue<BlockPos> queue = new LinkedList<>();
                            queue.add(bp);
                            boolean foundOneWithLeaves = false;
                            BlockPos.Mutable pos = bp.mutableCopy();
                            BlockPos.Mutable newPos = bp.mutableCopy();
                            while(!queue.isEmpty()) {
                                pos.set(queue.remove());
                                for(int dx = -1; dx <= 1; dx++) {
                                    for(int dy = 0; dy <= 1; dy++) {
                                        for(int dz = -1; dz <= 1; dz++) {
                                            if(dx == 0 & dy == 0 && dz == 0) {
                                                continue;
                                            }
                                            newPos.set(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);
                                            BlockState state = pl.world.getBlockState(newPos);
                                            if(state.isOf(bs.getBlock()) && !affected.contains(newPos)) {
                                                BlockPos savedNewPos = newPos.toImmutable();
                                                affected.add(savedNewPos);
                                                queue.add(savedNewPos);
                                                if(affected.size() > 255) {
                                                    if(!foundOneWithLeaves) {
                                                        return new ArrayList<>();
                                                    }
                                                    return new ArrayList<>(affected);
                                                }
                                            } else
                                            if((state.isIn(BlockTags.LEAVES) || state.getBlock() instanceof LeavesBlock) && !state.get(LeavesBlock.PERSISTENT)) {
                                                foundOneWithLeaves = true;
                                            }
                                        }
                                    }
                                }
                            }
                            if(!foundOneWithLeaves) {
                                affected.clear();
                            }
                            return new ArrayList<>(affected);
                        }, state -> state.isIn(BlockTags.LOGS)).addCondition(e -> e instanceof LivingEntity l)
        ));
    }
}
