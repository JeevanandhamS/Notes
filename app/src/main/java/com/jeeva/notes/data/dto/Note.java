package com.jeeva.notes.data.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.threeten.bp.OffsetDateTime;

import java.io.Serializable;

import static com.jeeva.notes.data.dto.Note.TABLE_NAME;

/**
 * Created by jeevanandham on 19/07/18
 */
@Entity(tableName = TABLE_NAME)
public class Note implements Serializable {

    public static final String TABLE_NAME = "NOTE";

    public static final String FIELD_UPDATED_TIME = "UPDATED_TIME";

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String content;

    @ColumnInfo(name = FIELD_UPDATED_TIME)
    private OffsetDateTime updatedTime;

    public Note(String content, OffsetDateTime updatedTime) {
        this.content = content;
        this.updatedTime = updatedTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public OffsetDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(OffsetDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", updatedTime=" + updatedTime +
                '}';
    }
}