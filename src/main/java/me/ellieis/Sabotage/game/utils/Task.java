package me.ellieis.Sabotage.game.utils;

import xyz.nucleoid.plasmid.api.game.GameSpace;

import java.util.function.Consumer;

public class Task {
    final int executionTime;
    final Consumer<GameSpace> task;
    public Task(int executionTime, Consumer<GameSpace> task) {
        this.executionTime = executionTime;
        this.task = task;
    }
}