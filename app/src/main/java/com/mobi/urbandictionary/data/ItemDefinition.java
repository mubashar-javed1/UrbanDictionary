package com.mobi.urbandictionary.data;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.mobi.urbandictionary.enums.SortMethod;

public class ItemDefinition {

    @SerializedName("defid")
    private int defid;

    @SerializedName("sound_urls")
    private List<String> soundUrls;

    @SerializedName("thumbs_down")
    private int thumbsDown;

    @SerializedName("author")
    private String author;

    @SerializedName("written_on")
    private String writtenOn;

    @SerializedName("definition")
    private String definition;

    @SerializedName("permalink")
    private String permalink;

    @SerializedName("thumbs_up")
    private int thumbsUp;

    @SerializedName("word")
    private String word;

    @SerializedName("current_vote")
    private String currentVote;

    @SerializedName("example")
    private String example;

    public void setDefid(int defid) {
        this.defid = defid;
    }

    public int getDefid() {
        return defid;
    }

    public void setSoundUrls(List<String> soundUrls) {
        this.soundUrls = soundUrls;
    }

    public List<String> getSoundUrls() {
        return soundUrls;
    }

    public void setThumbsDown(int thumbsDown) {
        this.thumbsDown = thumbsDown;
    }

    public int getThumbsDown() {
        return thumbsDown;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setWrittenOn(String writtenOn) {
        this.writtenOn = writtenOn;
    }

    public String getWrittenOn() {
        return writtenOn;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getDefinition() {
        return definition;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setThumbsUp(int thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public int getThumbsUp() {
        return thumbsUp;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setCurrentVote(String currentVote) {
        this.currentVote = currentVote;
    }

    public String getCurrentVote() {
        return currentVote;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getExample() {
        return example;
    }

    public static void sort(List<ItemDefinition> definitions, SortMethod method) {
        class DefinitionSort implements Comparator<ItemDefinition> {
            @Override
            public int compare(ItemDefinition o1, ItemDefinition o2) {
                switch (method) {
                    case THUMB_UP_ASC:
                        return o1.thumbsUp - o2.thumbsUp;
                    case THUMB_UP_DES:
                        return o2.thumbsUp - o1.thumbsUp;
                    case THUMB_DOWN_ASC:
                        return o1.thumbsDown - o2.thumbsDown;
                    case THUMB_DOWN_DES:
                        return o2.thumbsDown - o1.thumbsDown;
                    default:
                        return 0;
                }
            }
        }
        Collections.sort(definitions, new DefinitionSort());
    }
}