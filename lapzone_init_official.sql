DROP DATABASE IF EXISTS lapzone_db;
CREATE DATABASE IF NOT EXISTS lapzone_db;
USE lapzone_db;

-- 1. Bảng users
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL,
    address TEXT,
    role VARCHAR(20) NOT NULL DEFAULT 'USER'
);

-- 2. Bảng categories
CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- 3. Bảng products
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    stock INT NOT NULL,
    image VARCHAR(255),
    category_id BIGINT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- 4. Bảng product_details
-- 4. Bảng product_details (Nâng cấp full thông số Laptop)
CREATE TABLE product_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cpu VARCHAR(255),          -- Ví dụ: Intel Core i7-13700H
    ram VARCHAR(255),          -- Ví dụ: 16GB DDR5 4800MHz
    storage VARCHAR(255),      -- Ví dụ: 512GB SSD NVMe PCIe
    gpu VARCHAR(255),          -- Ví dụ: NVIDIA GeForce RTX 4060 8GB
    screen VARCHAR(255),       -- Ví dụ: 15.6 inch FHD (1920x1080) 144Hz
    battery VARCHAR(255),      -- Ví dụ: 4 Cell, 60Wh
    audio VARCHAR(255),        -- Ví dụ: Dolby Atmos, Dual Speakers
    ports VARCHAR(255),        -- Ví dụ: 2x USB 3.2, 1x Type-C, 1x HDMI
    wireless VARCHAR(255),     -- Ví dụ: Wi-Fi 6E, Bluetooth 5.3
    weight VARCHAR(100),       -- Ví dụ: 2.1 kg
    color VARCHAR(100),        -- Ví dụ: Đen nhám (Matte Black)
    os VARCHAR(255),           -- Ví dụ: Windows 11 Home
    condition_status VARCHAR(100), -- Ví dụ: Mới 100%, Nguyên seal
    warranty VARCHAR(100),     -- Ví dụ: 24 tháng chính hãng
    more_info TEXT,            -- Thông tin mô tả thêm
    product_id BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- 5. Bảng cart_items
CREATE TABLE cart_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity INT NOT NULL,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- 6. Bảng orders
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    total_amount DOUBLE NOT NULL,
    status VARCHAR(50) NOT NULL,
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    customer_name VARCHAR(100),
    customer_phone VARCHAR(20),
    address TEXT,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 7. Bảng order_details
CREATE TABLE order_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
-- bảng ảnh chi tiết sản phẩm
CREATE TABLE product_images (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    image_url VARCHAR(255) NOT NULL,
    product_id BIGINT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(id)
);