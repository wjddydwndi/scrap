package com.scrapy.repository.news;

import com.scrapy.model.newstore.NewstoreNewsData;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;

// 해당 어노테이션을 사용해서 스프링에 해당 클래스가 Repository로 사용되도록 등록한다.
@Repository
@RequiredArgsConstructor
public class NewstoreNewsDataJdbcRepositoryImpl implements NewstoreNewsDataJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public void insertNewstoreNewsData(List<NewstoreNewsData> list) {

        String sql = "INSERT INTO metronome.t_newstore_news_data ("
                + "news_id, title, content, published_at, enveloped_at, dateline, "
                + "provider_id, category_id, hilight, byline, images, "
                + "provider_news_id, publisher_code, provider_link_page, printing_page"
                + ") VALUES ("
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
                + ")";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        jdbcTemplate.batchUpdate(sql, list, list.size(), (ps, item) -> {
            ps.setString(1, item.getNewsId());
            ps.setString(2, item.getTitle());
            ps.setString(3, item.getContent());
            ps.setString(4, item.getPublishedAt().format(formatter));
            ps.setString(5, item.getEnvelopedAt().format(formatter));
            ps.setString(6, item.getDateline().format(formatter));
            ps.setString(7, item.getProviderId());
            ps.setString(8, item.getCategoryId());
            ps.setString(9, item.getHilight());
            ps.setString(10, item.getByline());
            ps.setString(11, item.getImages());
            ps.setString(12, item.getProviderNewsId());
            ps.setString(13, item.getPublisherCode());
            ps.setString(14, item.getProviderLinkPage());
            ps.setString(15, item.getPrintingPage());
        });
    }
}
