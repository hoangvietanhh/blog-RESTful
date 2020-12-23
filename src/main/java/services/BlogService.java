package services;

import model.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> findAll();

    Blog findById(Long id);

    Blog save(Blog blog);

    boolean remove(Long id);
}
