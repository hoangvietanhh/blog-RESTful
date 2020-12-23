package services;

import model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import repo.BlogRepository;

import java.util.List;

public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findById(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public Blog save(Blog blog) {
        blogRepository.save(blog);
        return blog;
    }

    @Override
    public boolean remove(Long id) {
        blogRepository.delete(id);
        return false;
    }
}
