1.để chạy thì clone cái project này về máy, có cái .mvn là mãi chả up được lên nên mọi người cứ thử chạy xem
2. cài MySQL,Mở file `lapzone_init_official.sql` có trong dự án và chạy toàn bộ lệnh để tạo database `lapzone_db` cùng cấu trúc các bảng.
3.Mở file `src/main/java/com/lapzone/lapzoneweb/dao/DBContext.java`.
Tìm đến dòng `private static final String DB_PASS = "123456";` và sửa thành mật khẩu MySQL thành mật khẩu ở máy tính của mình.
