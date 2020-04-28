package com.example.demo.dao;

import com.example.demo.model.persistence.Tag;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TagInfoImpl implements TagDao {
    private final SqlSession sqlSession;

    @Autowired
    public TagInfoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }


    @Override
    public Tag getTagByTagId(Long id) {
        return null;
    }

    @Override
    public Tag getTagByDescription(String description, Long id) {
        return null;
    }

    @Override
    public int createNewTag(Tag tag) {
        Map<String,Object> parameters=new HashMap<>();
        String description = tag.getDescription();
        Long userId = tag.getUserId();
        parameters.put("description",description);
        parameters.put("userId",userId);

        int createTag = sqlSession.insert("createTag", parameters);
        return createTag;



    }

    @Override
    public void updateTag(Tag tag) {

    }
}
