# 📋 Kiem-Thu-Phan-Mem

Repository cho **bài tập lớn môn Kiểm thử phần mềm** tại Đại học Công nghiệp Hà Nội.

## 🎯 Mục tiêu

- Xây dựng hệ thống kiểm thử phần mềm cho một website đặt phòng khách sạn.
- Sử dụng các công cụ kiểm thử chức năng và hiệu năng:
  - Selenium WebDriver (Java)
  - Apache JMeter
  - k6 (hiệu năng hiện đại dựa trên JavaScript)

---

## 🧩 Cấu trúc dự án

.
├── LearnSelenium/ # Kiểm thử chức năng tự động bằng Selenium WebDriver (Java)
├── bookingroom-client/ # Giao diện người dùng (ReactJS hoặc tương tự)
├── bookingroom-server-ver1/ # Dịch vụ backend (Spring Boot)
├── README.md # Tài liệu mô tả dự án

---

## ⚙️ Công nghệ sử dụng

| Thành phần         | Công nghệ/Sản phẩm             |
|--------------------|--------------------------------|
| Kiểm thử chức năng | Selenium WebDriver (Java), JUnit |
| Kiểm thử hiệu năng | Apache JMeter, k6 (JavaScript) |
| Frontend           | JavaScript, ReactJS, SCSS      |
| Backend            | Java, Spring Boot, MySQL       |

---

## 🧪 Chi tiết kiểm thử

### ✅ Kiểm thử chức năng (Functional Testing)

- **Công cụ:** Selenium WebDriver + JUnit
- **Ngôn ngữ:** Java
- **Kịch bản kiểm thử:**
  - Đăng nhập/Đăng ký
  - Tìm kiếm phòng
  - Đặt phòng
  - Xem lịch sử đơn đặt
- **Mục tiêu:** Đảm bảo các chức năng chính của người dùng và quản trị hoạt động đúng như yêu cầu.

### 📊 Kiểm thử hiệu năng (Performance Testing)

#### 📌 Apache JMeter

- **Mục tiêu:** Kiểm tra khả năng chịu tải của hệ thống backend.
- **Các kịch bản:**
  - Gửi nhiều yêu cầu POST/GET đồng thời tới API đặt phòng.
  - Phân tích response time, throughput, error rate.

#### 📌 k6

- **Ưu điểm:** Viết script bằng JavaScript, dễ tích hợp CI/CD.
- **Mục tiêu:** Mô phỏng hàng trăm/thousands users truy cập hệ thống.
- **Chỉ số đo lường:**
  - Response time
  - Request per second (RPS)
  - CPU/memory impact (khi tích hợp Prometheus)

---

## 🚀 Hướng dẫn chạy

### ✅ Chạy kiểm thử với Selenium

```bash
# Yêu cầu: Java JDK 11+, Maven
cd LearnSelenium
mvn clean test
