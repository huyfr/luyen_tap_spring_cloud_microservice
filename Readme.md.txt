Thực hành về microservices

1. Database vẫn đánh constraint như bình thường nhưng chỉ lưu id của đối tượng này ở bảng còn lại thay vì cả entity
2. Viết 2 service student và address như 2 back-end spring boot bình thường
3. Service nào cần gọi sang các service khác thì thêm thư viện openfeign
4. Tạo 1 project eureka để đứng giữa quản lý các service. Các service gọi nhau qua service name thông qua eureka.
5. Config load balancing, thêm thư viện loadbalancer của spring cloud để phân tải request từ student sang address. Chạy 2 instances của address service
6. Tạo 1 project api gateway sử dụng thư viện gateway của spring cloud. Đăng ký api gateway với eureka. cấu hình cho api gw tìm các service trong eureka
Nếu dùng api gw thì sẽ tự động load balancing không cần phải cấu hình load balacing thủ công ở từng service