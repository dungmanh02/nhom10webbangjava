package com.lapzone.lapzoneweb.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lapzone.lapzoneweb.model.entity.Category;

@Repository
// BÍ QUYẾT LÀ ĐOẠN "extends JpaRepository<Category, Long>" NÀY ĐÂY:
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    // Bạn không cần viết hàm findAll() ở đây đâu nhé, 
    // JpaRepository nó đã code sẵn ẩn ở dưới nền cho mình hết rồi!
}