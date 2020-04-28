package com.example.demo.controller;

import com.example.demo.converter.c2s.TagC2SConverter;
import com.example.demo.exception.InvalidParameterException;
import com.example.demo.manager.TagManager;
import com.example.demo.model.service.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1.0/tags")
public class TagController {
    private TagManager tagManager;
    private TagC2SConverter tagC2SConverter;

    @Autowired
    public TagController(TagManager tagManager, TagC2SConverter tagC2SConverter) {
        this.tagManager = tagManager;
        this.tagC2SConverter = tagC2SConverter;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public Tag createTag(@RequestBody Tag tag) {
        com.example.demo.model.common.Tag resource = tagManager.createTag(tag.getDescription(), tag.getUserId());
        return tagC2SConverter.convert(resource);


    }
    @GetMapping(path = "/{id}",produces = "application/json",consumes = "application/json")
    public Tag getTagByTagId(@PathVariable("id")Long tagId){
        com.example.demo.model.common.Tag tagByTagId = tagManager.getTagByTagId(tagId);
        return tagC2SConverter.convert(tagByTagId);
    }

    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public Tag updateTag(@PathVariable("id") Long tagId, @RequestBody Tag tag) {
        String status = tag.getStatus();
        if (status != null && !"ENABLE".equals(status) && !"DISABLE".equals(status)) {
            throw new InvalidParameterException(String.format("The status [%s] to update is invalid status", status));
        }
        tag.setId(tagId);
        com.example.demo.model.common.Tag convert = tagC2SConverter.reverse().convert(tag);
        com.example.demo.model.common.Tag resource = tagManager.updateTag(convert);
        return tagC2SConverter.convert(resource);

    }
}
