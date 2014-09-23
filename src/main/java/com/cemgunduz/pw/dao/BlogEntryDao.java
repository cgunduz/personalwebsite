package com.cemgunduz.pw.dao;

import com.cemgunduz.pw.model.BlogEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by cgunduz on 9/8/14.
 */
public interface BlogEntryDao extends MongoRepository<BlogEntry,String> {


}
