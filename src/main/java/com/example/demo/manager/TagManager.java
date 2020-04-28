package com.example.demo.manager;


import com.example.demo.model.common.Tag;

public interface TagManager {
    Tag getTagByTagId(Long tagId);
    Tag updateTag(Tag tag);

    Tag createTag(String description,Long userId);
}
