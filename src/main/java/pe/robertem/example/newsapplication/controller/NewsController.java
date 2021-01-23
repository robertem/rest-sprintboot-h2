package pe.robertem.example.newsapplication.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.robertem.example.newsapplication.entity.News;
import pe.robertem.example.newsapplication.exception.NewsNotFoundException;
import pe.robertem.example.newsapplication.repository.NewsRepository;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsRepository newsRepository;

    public NewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @GetMapping("/{id}")
    public News findOne(@PathVariable Long id) {
        return newsRepository.findById(id).orElseThrow(() -> new NewsNotFoundException(id));
    }

    @PostMapping
    public News create(@RequestBody News news) {
        news.setId(null);
        return newsRepository.save(news);
    }

    @PutMapping("/{id}")
    public News update(@RequestBody News news, @PathVariable Long id) {
        return newsRepository.findById(id).map(newsToUpdate -> {
            newsToUpdate.setTitle(news.getTitle());
            newsToUpdate.setDescription(news.getDescription());
            newsToUpdate.setDate(news.getDate());
            return newsRepository.save(newsToUpdate);
        }).orElseThrow(() -> new NewsNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        newsRepository.deleteById(id);
    }
}
