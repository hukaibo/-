package com.example.demo.manager;

import com.example.demo.converter.p2c.TagP2CConverter;
import com.example.demo.exception.InvalidParameterException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.common.Tag;
import com.example.demo.dao.TagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class TagManagerImpl implements TagManager {
    private TagP2CConverter tagP2CConverter;
    private TagDao tagDao;

    @Autowired
    public TagManagerImpl(TagP2CConverter tagP2CConverter, TagDao tagDao) {
        this.tagP2CConverter = tagP2CConverter;
        this.tagDao = tagDao;
    }

    @Override
    public Tag getTagByTagId(Long tagId) {
        final com.example.demo.model.persistence.Tag tagByTagId = tagDao.getTagByTagId(tagId);
        final Tag convert = tagP2CConverter.convert(tagByTagId);
        return Optional.ofNullable(convert).orElseThrow(() ->
                new ResourceNotFoundException(String.format("The related tag id [%s] was not found", tagId)));

    }

    @Override
    public Tag updateTag(Tag tag) {
        final com.example.demo.model.persistence.Tag updatingTag = tagP2CConverter.reverse().convert(tag);
        final com.example.demo.model.persistence.Tag tagInDb = Optional.ofNullable(tagDao.getTagByTagId(tag.getId()))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("The related tag id [%s] was not found", tag.getId())
                ));
        if (!tag.getUserId().equals(tagInDb.getUserId())) {
            throw new InvalidParameterException(
                    String.format("The record id [%s] doesn't belong to user id:[%s]", tag.getId(), tag.getUserId())
            );
        }
        tagDao.updateTag(updatingTag);
        return getTagByTagId(tag.getId());

    }

    @Override
    public Tag createTag(String description, Long userId) {
        com.example.demo.model.persistence.Tag newTag = com.example.demo.model.persistence.Tag.builder()
                .description(description)
                .userId(userId)
                .status(1)
                .build();
        int newTag1 = tagDao.createNewTag(newTag);
        System.out.println(newTag1);
        return tagP2CConverter.convert(newTag);

    }
}
