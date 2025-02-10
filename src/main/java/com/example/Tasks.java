package com.example;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Tasks {
    private int taskId;
    private String title;
    private String description;
    private boolean status;
    private Timestamp createAt;

    public Tasks(int taskId, String title, String description, boolean status, Timestamp createAt) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + taskId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + (status ? "Hoàn thành" : "Chưa hoàn thành") +
                ", createdAt=" + createAt +
                '}';
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public boolean isStatus() {
        return status;
    }
}
