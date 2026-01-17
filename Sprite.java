package com.aimlite;

import javafx.scene.shape.Circle;

public abstract class Sprite implements Actions {
    protected Circle shape;

    public Circle getShape() {
        return shape;
    }
}
