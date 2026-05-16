package com.lapzone.lapzoneweb.model.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.lapzone.lapzoneweb.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findTop8ByOrderByIdDesc();
    List<Product> findTop4ByOrderByIdDesc();
    List<Product> findByCategory_Name(String categoryName);
   @Query("SELECT p FROM Product p LEFT JOIN p.productDetail pd WHERE " +
           "(p.name LIKE %:keyword%) " +
           "AND (:categoryId IS NULL OR p.category.id = :categoryId) " +
           "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
           "AND (:maxPrice IS NULL OR p.price <= :maxPrice) " +
           "AND (:cpu IS NULL OR pd.cpu LIKE %:cpu%) " +
           "AND (:gpu IS NULL OR pd.gpu LIKE %:gpu%)")
    List<Product> searchAndFilterProducts(
            @Param("keyword") String keyword,
            @Param("categoryId") Long categoryId,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("cpu") String cpu,
            @Param("gpu") String gpu
    );
    List<Product> findByCategoryId(Long categoryId);
}