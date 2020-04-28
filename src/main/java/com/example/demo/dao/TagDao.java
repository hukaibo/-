package com.example.demo.dao;

import com.example.demo.model.persistence.Tag;
import org.springframework.stereotype.Service;

@Service
public interface TagDao {
    Tag getTagByTagId(Long id);

    Tag getTagByDescription(String description, Long id);

    int createNewTag(Tag tag);

    void updateTag(Tag tag );
}
