package com.example.demo.converter.c2s;

import com.example.demo.model.common.Tag;
import com.google.common.base.Converter;
import org.springframework.stereotype.Service;

@Service
public class TagC2SConverter extends Converter<Tag, com.example.demo.model.service.Tag> {
    private static final String ENABLE = "ENABLE";
    private static final String DISABLE = "DISABLE";

    @Override
    protected com.example.demo.model.service.Tag doForward(Tag tag) {
        return com.example.demo.model.service.Tag.builder()
                .id(tag.getId())
                .description(tag.getDescription())
                .userId(tag.getUserId())
                .status(tag.getStatus())
                .build();
    }

    @Override
    protected Tag doBackward(com.example.demo.model.service.Tag tag) {
        return Tag.builder()
                .id(tag.getId())
                .description(tag.getDescription())
                .userId(tag.getUserId())
                .status(tag.getStatus())
                .build();
    }
}
