package com.example.weathercody.Repository;

import com.example.weathercody.JPA.ProductEntity;
import com.example.weathercody.JPA.WomanProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WomanProductRepository extends JpaRepository<WomanProductEntity,Long> {
    @Query(value = "DROP INDEX UK_7hi9qtptu5jr19ignmyqtjyr3 ON woman_products;",nativeQuery = true)
    @Modifying
    @Transactional
    int  Dropindex();

    @Query(value = "select *, Rank() over(order by like_count desc)as ranking from woman_product where weather='맑음' limit 6",nativeQuery = true)
    List<WomanProductEntity> getRanking();

    @Query(value = "select *, Rank() over(order by like_count desc)as ranking from woman_product where weather='비' limit 6",nativeQuery = true)
    List<WomanProductEntity> getRainRanking();

    @Query(value = "select * from woman_product where weather='맑음' limit ?,12",nativeQuery = true)
    List<WomanProductEntity> ProductRender(int pageNo);

    @Query(value = "select * from woman_product where weather='비' limit ?,12",nativeQuery = true)
    List<WomanProductEntity> ProductRainRender(int pageNo);

    @Query(value = "select image from woman_product where idx = ?",nativeQuery = true)
    @Transactional
    String getProductUrl(int idx);

    @Query(value = "select like_count from woman_product where idx = ?",nativeQuery = true)
    @Transactional
    int howLike(int idx);

    @Query(value = "update woman_product set like_count=? where idx = ?",nativeQuery = true)
    @Modifying
    @Transactional
    int UpLike(int like_count,int idx);

}
