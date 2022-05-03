package main.tiles;

import java.awt.Graphics2D;

public abstract class TileMap {
    public abstract Tile[] getTiles();
    public abstract void render(Graphics2D g);
}
