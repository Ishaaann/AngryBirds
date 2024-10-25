package com.ninjamoney.angrybirds;

import com.badlogic.gdx.graphics.Texture;

public class Wood extends SolidObjects{
    private Texture woodTexture;
    private String woodType;

    public Wood(String type) {
        this.woodType = type;
        if(woodType.equals("plank")){
            woodTexture = new Texture("wood_block.png");
        }
        if(woodType.equals("woodTriangle")){
            woodTexture = new Texture("wood_triangle.png");
        }
    }

    public Texture getWoodTexture() {
        return woodTexture;
    }
}

