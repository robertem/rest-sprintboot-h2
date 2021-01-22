package pe.robertem.example.newsapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.robertem.example.newsapplication.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}
