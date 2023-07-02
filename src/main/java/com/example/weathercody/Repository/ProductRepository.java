package com.example.weathercody.Repository;

import com.example.weathercody.JPA.ImageEntity;
import com.example.weathercody.JPA.ProductEntity;
import com.example.weathercody.JPA.ProductsEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    @Query(value = "select idx,cloths_Image,cloths_Link from Products where Products.product_idx=?",nativeQuery = true)
    List<String> getTest(int num);

    @Query(value = "DROP INDEX UK_lr8r5m9frs37cpq9xc3c812ly ON products;",nativeQuery = true)
    @Modifying
    @Transactional
    int  Dropindex();

    @Query(value = "select product_imageurl from Product where idx = ?",nativeQuery = true)
    @Transactional
    String getproductImageURL(int idx);

    @Query(value = "select *, Rank() over(order by like_count desc)as ranking from Product where weather = '맑음' limit 6 ;",nativeQuery = true)
    List<ProductEntity> getRanking();

    @Query(value = "select *, Rank() over(order by like_count desc)as ranking from Product where weather = '비' limit 6 ;",nativeQuery = true)
    List<ProductEntity> getRainRanking();

    @Query(value = "select like_count from Product where idx = ?",nativeQuery = true)
    @Transactional
    int howLike(int idx);

    @Query(value = "update Product set like_count=? where idx = ?",nativeQuery = true)
    @Modifying
    @Transactional
    int UpLike(int like_count,int idx);

    @Query(value = "select * from Product where weather = '맑음' limit ?,12 ;",nativeQuery = true)
    List<ProductEntity> ProductRender(int pageNo);

    @Query(value = "select * from Product where weather = '비' limit ?,12 ;",nativeQuery = true)
    List<ProductEntity> ProductRainRender(int pageNo);

    @Query(value = "select * from products where product_idx = ?",nativeQuery = true)
    List<List<ProductsEntity>> getProductDetail(int idx);

    @Query(value = "select image from Product where idx = ?",nativeQuery = true)
    @Transactional
    String getProductUrl(int idx);
}
