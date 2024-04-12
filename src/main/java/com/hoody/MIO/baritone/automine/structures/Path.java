package com.jelly.GhosterBuster.baritone.automine.structures;

import com.jelly.GhosterBuster.baritone.automine.calculations.behaviour.PathMode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedList;

@AllArgsConstructor
public class Path {
    @Getter
    LinkedList<BlockNode> blocksInPath;
    @Getter
    PathMode mode;

}
