package com.aditi.itemlisting.repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.aditi.itemlisting.model.Post;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class SearchRepositoryImpl implements SearchRepository{

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter; // converts the doc which is in document format to java format (same format as posts) then adds in posts.

    @Override
    public List<Post> findByText(String text) {

        final List<Post> posts = new ArrayList<>();

        MongoDatabase database = client.getDatabase("Aditi");
        MongoCollection<Document> collection = database.getCollection("ItemPost");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("text",
                        new Document("query", text)// pass the text to be searched
                                .append("path", Arrays.asList("tags", "desc", "condition")))),// searches word in these fields
                        new Document("$sort",
                        new Document("price", 1L)),// 1 for ascending and -1 for descending order sort
                        new Document("$limit", 5L)));// limits the search result


        // converter.read(Goaltype, currenttype)
        result.forEach(doc -> posts.add(converter.read(Post.class,doc)));// result is iterable and
                                                                        // whatever result we get in each iteration say doc,
                                                                        // we add it in the posts

        return posts;
    }
}