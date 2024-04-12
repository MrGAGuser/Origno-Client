package com.jelly.GhosterBuster.baritone.automine.config;

import com.jelly.GhosterBuster.baritone.automine.calculations.behaviour.PathMode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class PathFindSetting {
    @Getter
    boolean mineWithPreference;

    @Getter
    PathMode pathMode;

    @Getter
    boolean findWithBlockPos;


}
