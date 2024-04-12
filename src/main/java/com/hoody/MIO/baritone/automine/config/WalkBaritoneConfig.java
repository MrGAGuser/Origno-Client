package com.jelly.GhosterBuster.baritone.automine.config;

import com.jelly.GhosterBuster.utils.BlockUtils.BlockUtils;

public class WalkBaritoneConfig extends BaritoneConfig{

    public WalkBaritoneConfig(int minY, int maxY, int restartTimeThreshold){
        super(MiningType.NONE,
                false,
                false,
                false,
                200,
                restartTimeThreshold,
                null,
                BlockUtils.walkables,
                maxY,
                minY);
    }
}
