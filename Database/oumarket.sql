-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: oumarket
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chinhanh`
--

DROP TABLE IF EXISTS `chinhanh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chinhanh` (
  `MaChiNhanh` varchar(50) NOT NULL,
  `SoNha` varchar(45) DEFAULT NULL,
  `Duong` varchar(45) DEFAULT NULL,
  `Phuong` varchar(45) DEFAULT NULL,
  `Quan` varchar(45) DEFAULT NULL,
  `ThanhPho` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`MaChiNhanh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chinhanh`
--

LOCK TABLES `chinhanh` WRITE;
/*!40000 ALTER TABLE `chinhanh` DISABLE KEYS */;
INSERT INTO `chinhanh` VALUES ('32ebf525-972d-4355-9ac5-e5b0caa4ca62','166A - 166B','Trần Văn Quang','10','Tân Bình','Hồ Chí Minh'),('3dc22861-4f30-480f-9fd0-ebfa076ef894','164 ','Nguyễn Văn Cừ','Bồ Đề','Long Biên','Hà Nội'),('8bf3b5f2-3297-4f72-8484-0a11c0de87f9','124 ','Phổ Quang','9','Phú Nhuận','Hồ Chí Minh'),('961f272c-3ec7-4870-9589-fe664f07bff5','560A','Nguyễn Văn Cừ','Gia Thụy','Long Biên','Hà Nội');
/*!40000 ALTER TABLE `chinhanh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitiethoadon`
--

DROP TABLE IF EXISTS `chitiethoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitiethoadon` (
  `MaHoaDon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `MaHang` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `SoLuong` int DEFAULT NULL,
  `ThanhTien` int DEFAULT NULL,
  PRIMARY KEY (`MaHoaDon`,`MaHang`),
  KEY `FK_chitiethoadon_hanghoa1` (`MaHang`),
  CONSTRAINT `FK_chitiethoadon_hanghoa1` FOREIGN KEY (`MaHang`) REFERENCES `hanghoa` (`MaHang`),
  CONSTRAINT `FK_chitiethoadon_hoadon1` FOREIGN KEY (`MaHoaDon`) REFERENCES `hoadon` (`MaHoaDon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiethoadon`
--

LOCK TABLES `chitiethoadon` WRITE;
/*!40000 ALTER TABLE `chitiethoadon` DISABLE KEYS */;
/*!40000 ALTER TABLE `chitiethoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giamgia`
--

DROP TABLE IF EXISTS `giamgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giamgia` (
  `MaGiamGia` int NOT NULL AUTO_INCREMENT,
  `NoiDung` varchar(45) DEFAULT NULL,
  `NgayBatDau` date DEFAULT NULL,
  `NgayKetThuc` date DEFAULT NULL,
  PRIMARY KEY (`MaGiamGia`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giamgia`
--

LOCK TABLES `giamgia` WRITE;
/*!40000 ALTER TABLE `giamgia` DISABLE KEYS */;
INSERT INTO `giamgia` VALUES (1,'0%','2019-01-01','2050-12-31'),(2,'10%','2022-04-10','2022-10-25'),(3,'20%','2022-04-10','2022-10-25'),(4,'30%','2022-04-04','2022-04-20'),(5,'40%','2022-04-04','2022-04-20'),(6,'50%','2022-04-04','2022-04-20'),(7,'60%','2022-04-04','2022-04-20'),(8,'70%','2022-04-04','2022-04-20'),(9,'80%','2022-04-04','2022-04-20'),(10,'90%','2022-04-04','2022-04-20');
/*!40000 ALTER TABLE `giamgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hanghoa`
--

DROP TABLE IF EXISTS `hanghoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hanghoa` (
  `MaHang` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TenHang` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `SoLuong` int DEFAULT NULL,
  `DonGia` float DEFAULT NULL,
  `NguonGoc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `MaLoai` int DEFAULT NULL,
  `MaGiamGia` int DEFAULT NULL,
  PRIMARY KEY (`MaHang`),
  KEY `FK_hanghoa_phanloai` (`MaLoai`),
  KEY `FK_hanghoa_giamgia` (`MaGiamGia`),
  CONSTRAINT `FK_hanghoa_giamgia` FOREIGN KEY (`MaGiamGia`) REFERENCES `giamgia` (`MaGiamGia`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_hanghoa_phanloai` FOREIGN KEY (`MaLoai`) REFERENCES `phanloai` (`MaLoai`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hanghoa`
--

LOCK TABLES `hanghoa` WRITE;
/*!40000 ALTER TABLE `hanghoa` DISABLE KEYS */;
INSERT INTO `hanghoa` VALUES ('5cd6e335-ecc7-4f2f-a313-fe7b7a44172b','Bia Red Ruby lon 330ml',100,10000,'Vietnam',8,1),('be9364a4-62b6-46a3-be61-46cef5e58219','Nước tăng lực Monster Enery Ultra lon 355ml',100,29600,'Hong Kong',8,2),('d1a17b1e-0b65-4951-b847-600dfe06ed08','Bánh Mỳ',10,10000,'VietNam',3,1),('d4f2b818-5332-4649-b27b-ee6fb1566752','Bánh kẹo',100,10000,'VN',3,2),('dff121e3-fe18-4409-8c13-7687217c9b99','Bang keo',100,100000,'Viet Nam',11,1);
/*!40000 ALTER TABLE `hanghoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `MaHoaDon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `MaNV` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `MaKH` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `NgayHD` date DEFAULT NULL,
  PRIMARY KEY (`MaHoaDon`),
  KEY `FK_hoadon_khachhang` (`MaKH`),
  KEY `FK_hoadon_nhanvien` (`MaNV`),
  CONSTRAINT `FK_hoadon_khachhang` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`),
  CONSTRAINT `FK_hoadon_nhanvien` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `MaKH` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TenKH` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `SDT` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `DiaChi` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `NamSinh` date DEFAULT NULL,
  `Email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`MaKH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES ('1206ab50-ff0d-4f73-a993-f25abee74bda','Nguyen Van A','0564243269','123 ','1996-06-03','123'),('196ae587-b058-4aae-9743-13efd89bb7c7','Nguyen Van C','0909870685','456','2000-04-07','123'),('1a87eeef-0e9a-4f48-a082-9a2950a05453','Tran Thi Kieu Tho','0387653488','789','1995-10-09','123'),('1fb7fd43-7362-4cc6-b8dd-a85d5cd0c905','Huynh Tuan Tu','0394689289','12','1997-06-09','123123'),('27a01cbb-1385-48e5-a354-8046e70d2e97','Phan Thi Nha Tran','0359468139','41','2004-09-06','123'),('2ee3358e-703a-4ec2-a737-c77a468a022a','Le Duy Cuong','0382304927','415','1993-04-25','123'),('372c7092-0c50-4f1a-b9e9-827093a522c1','Phan Thi Kieu Suong','0378109060','651','1989-03-05','123'),('40691c3a-21a3-4fe0-be5c-959ea265bfad','Vu Van Duc','0348094068','61','1999-12-20','123'),('54a08aa5-060e-4be3-8df9-f432f40be46d','La Thi Kieu Thu','0904606738','76','1990-09-10','123'),('59e5b850-fb61-4ea4-aba7-b7a6ece4ebd9','Nguyen Thi Lan','0904603738','46','1990-09-10','123');
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `MaNV` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TenNV` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `NamSinh` date DEFAULT NULL,
  `SDT` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `GioiTinh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `DiaChi` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`MaNV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES ('5a82fefa-d378-4609-b074-758aee6effb9','huynh chi tuan','2001-02-10','0382406027','test2@gmail.com','Nữ','51 nguyen linh cung phuong 4 phu nhuan'),('da4e1a1b-e335-4d68-bddc-654749833230','nguyen anh tuan','2001-02-02','0382305027','test@gmail.com','Nam','123 nguyen xuan linh phuong 4 phu nhuan');
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phanloai`
--

DROP TABLE IF EXISTS `phanloai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phanloai` (
  `MaLoai` int NOT NULL AUTO_INCREMENT,
  `TenLoai` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`MaLoai`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phanloai`
--

LOCK TABLES `phanloai` WRITE;
/*!40000 ALTER TABLE `phanloai` DISABLE KEYS */;
INSERT INTO `phanloai` VALUES (1,'Rau củ quả'),(2,'Đông lạnh, tươi sống, hải sản'),(3,'Bánh kẹo - Thực phẩm'),(4,'Hoá phẩm - Chất tẩy rửa'),(5,'Mỹ phẩm'),(6,'Mẹ và bé (sữa bỉm - phụ kiện)'),(7,'Sữa tươi, chua, kem'),(8,'Đồ uống'),(9,'Thiết yếu - Phổ thông'),(10,'Giấy - Khăn ướt - BVS'),(11,'Gia dụng cơ bản'),(12,'Văn phòng phẩm cơ bản'),(13,'Đồ chơi'),(14,'Bông vải sợi, chăm sóc sk'),(15,'Rượu');
/*!40000 ALTER TABLE `phanloai` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-23 21:47:05
