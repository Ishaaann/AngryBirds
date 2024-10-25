package com.ninjamoney.angrybirds;

import com.badlogic.gdx.graphics.Texture;

public class Rock extends SolidObjects {
    private Texture rockTexture;
    private String rockType;

    public Rock( String type) {
        this.rockType = type;
        if(rockType.equals("circle")){
            rockTexture = new Texture("elements/struct/rock_circle.png");
        }
        if(rockType.equals("rockBlock")){
            rockTexture = new Texture("elements/struct/rock_block.png");
        }
    }

    public Texture getRockTexture() {
        return rockTexture;
    }
}

