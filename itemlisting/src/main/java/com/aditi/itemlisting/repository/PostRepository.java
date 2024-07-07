package com.aditi.itemlisting.repository;

import com.aditi.itemlisting.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post,String>
{

}
