package com.aimlite;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class TargetSprite extends Sprite {

    private Random random = new Random();

    public TargetSprite() {
        shape = new Circle(18);
        shape.setFill(Color.HOTPINK);
        relocate();
    }

    public void relocate() {
        shape.setCenterX(50 + random.nextInt(500));
        shape.setCenterY(70 + random.nextInt(300));
    }

    @Override
    public void update() {
        relocate();
    }
}

