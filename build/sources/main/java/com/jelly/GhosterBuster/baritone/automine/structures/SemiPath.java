package com.jelly.GhosterBuster.baritone.automine.structures;

import com.jelly.GhosterBuster.baritone.automine.calculations.behaviour.PathMode;

import java.util.LinkedList;

public class SemiPath extends Path {
    public SemiPath(LinkedList<BlockNode> blocksInPath, PathMode mode) {
        super(blocksInPath, mode);
    }
}
