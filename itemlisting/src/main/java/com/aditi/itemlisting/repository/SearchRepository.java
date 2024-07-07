package com.aditi.itemlisting.repository;
import com.aditi.itemlisting.model.Post;

import java.util.List;
public interface SearchRepository {

    List<Post> findByText(String text);

}