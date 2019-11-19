package com.aaron.game;

public interface HandFactory <T extends Hand> {
    T create();
}
