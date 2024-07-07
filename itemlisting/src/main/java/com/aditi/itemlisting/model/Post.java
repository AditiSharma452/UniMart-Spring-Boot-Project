

//It is a pojo class that will map my java class with the document


package com.aditi.itemlisting.model;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
@Document(collection = "ItemPost") //tells it which collection this pojo will map to
public class Post {
    private String desc;
    private String condition;
    private String category;
    private String tags[];
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }
    public Post() {
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Post{" +
                "desc='" + desc + '\'' +
                ", condition='" + condition + '\'' +
                ", category='" + category + '\'' +
                ", tags=" + Arrays.toString(tags) +
                '}';
    }
}
