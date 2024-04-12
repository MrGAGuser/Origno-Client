package com.jelly.GhosterBuster.waypoints;

import lombok.Data;

import java.awt.*;

@Data
public class Waypoint {
    private final Color waypointColor;
    private final String name;
    private final int x;
    private final int y;
    private final int z;

    private final String dimension;
}
