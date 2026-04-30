package com.lapzone.lapzoneweb.model.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.lapzone.lapzoneweb.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Hàm 1: Lấy 8 sản phẩm mới nhất (cho mục "Hàng mới cập nhật")
    List<Product> findTop8ByOrderByIdDesc();

    // Hàm 2: Lấy 4 sản phẩm tham khảo ngẫu nhiên hoặc mới nhất (cho mục "Sản phẩm tham khảo")
    List<Product> findTop4ByOrderByIdDesc();
    
    // Hàm 3: Lấy sản phẩm theo tên hãng (Cho mục "Apple", "Dell",...)
    List<Product> findByCategory_Name(String categoryName);
    // Tìm kiếm động: Có từ khóa thì tìm, có bộ lọc thì lọc, không có thì bỏ qua
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% " +
           "AND (:categoryId IS NULL OR p.category.id = :categoryId) " +
           "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
           "AND (:maxPrice IS NULL OR p.price <= :maxPrice)")
    List<Product> searchAndFilterProducts(
            @Param("keyword") String keyword,
            @Param("categoryId") Long categoryId,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );
}