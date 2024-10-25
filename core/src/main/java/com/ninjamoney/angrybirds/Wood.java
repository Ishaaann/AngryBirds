package com.ninjamoney.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Wood extends SolidObjects{
    private Texture woodTexture;
    private String woodType;
    private TextureRegion region;

    public Wood(String type) {
        this.woodType = type;
        if(woodType.equals("plank")){
            woodTexture = new Texture("plank.png");
            region = new TextureRegion(woodTexture);
            region.flip(true, false);
        }
        if(woodType.equals("woodTriangle")){
            woodTexture = new Texture("wood_triangle.png");
            region = new TextureRegion(woodTexture);
            region.flip(true, false);
        }
    }

    public Texture getWoodTexture() {
        return woodTexture;
    }

    public TextureRegion getRegion() {
        return region;
    }
}

