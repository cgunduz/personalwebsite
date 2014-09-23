package com.cemgunduz.pw.dao;

import com.cemgunduz.pw.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by cgunduz on 9/10/14.
 */
public interface CommentDao extends MongoRepository<Comment,String> {
}
