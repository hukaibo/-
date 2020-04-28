package com.example.demo.converter.p2c;

import com.example.demo.model.persistence.Tag;
import com.google.common.base.Converter;
import org.springframework.stereotype.Service;

@Service
public class TagP2CConverter extends Converter<Tag, com.example.demo.model.common.Tag> {
    private static final String ENABLE = "ENABLE";
    private static final String DISABLE = "DISABLE";

    @Override
    protected com.example.demo.model.common.Tag doForward(Tag tag) {
        return com.example.demo.model.common.Tag.builder()
                .id(tag.getId())
                .description(tag.getDescription())
                .userId(tag.getUserId())
                .status(tag.getStatus() == 1 ? ENABLE : DISABLE)
                .build();
    }

    @Override
    protected Tag doBackward(com.example.demo.model.common.Tag tag) {
        final Tag tagInDb = Tag.builder()
                .id(tag.getId())
                .description(tag.getDescription())
                .userId(tag.getUserId())
                .build();
        if (tag.getStatus() != null) {
            tagInDb.setStatus(ENABLE.equals(tag.getStatus()) ? 1 : 0);

        }
        return tagInDb;
    }

}
