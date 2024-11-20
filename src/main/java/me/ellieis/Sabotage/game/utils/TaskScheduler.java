package me.ellieis.Sabotage.game.utils;

import net.minecraft.world.World;
import xyz.nucleoid.plasmid.api.game.GameSpace;

import java.util.ArrayList;


public class TaskScheduler {
    private final GameSpace gameSpace;
    private final World world;
    private final ArrayList<Task> tasks = new ArrayList<>();

    public TaskScheduler(GameSpace gameSpace, World world) {
        this.gameSpace = gameSpace;
        this.world = world;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void onTick() {
        long time = world.getTime();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.executionTime <= time) {
                task.task.accept(gameSpace);
                tasks.remove(task);
            }
        }
    }
}
