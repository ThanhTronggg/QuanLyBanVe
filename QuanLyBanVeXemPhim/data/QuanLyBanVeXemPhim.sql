
USE Master
go

CREATE DATABASE QuanLyBanVe
go

USE QuanLyBanVe
go


-- Sequence cho bảng TaiKhoan
CREATE SEQUENCE TaiKhoanSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE TaiKhoan
(
    ID CHAR(6) PRIMARY KEY DEFAULT ('TK' + RIGHT('000' + CAST(NEXT VALUE FOR TaiKhoanSequence AS VARCHAR(3)), 3)),
    Username VARCHAR(50) NOT NULL UNIQUE,
    MatKhau VARCHAR(400) NOT NULL
);
go

-- Sequence cho bảng NhanVien
CREATE SEQUENCE NhanVienSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE NhanVien
(
    MaNhanVien CHAR(6) PRIMARY KEY DEFAULT ('NV' + RIGHT('000' + CAST(NEXT VALUE FOR NhanVienSequence AS VARCHAR(3)), 3)),
    HoTen NVARCHAR(100) NOT NULL,
    GioiTinh BIT NOT NULL,
    NgaySinh SMALLDATETIME NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    SoDienThoai CHAR(10) NOT NULL UNIQUE,
    VaiTro NVARCHAR(30) NOT NULL,
		CONSTRAINT CK_VaiTro CHECK (VaiTro in (N'Quản lý', N'Nhân viên')),
    NgayBatDau SMALLDATETIME NOT NULL,
    Luong MONEY NOT NULL,
    MaTaiKhoan CHAR(6),
    CONSTRAINT FK_TaiKhoan FOREIGN KEY (MaTaiKhoan) REFERENCES TaiKhoan(ID),
	TrangThai NVARCHAR(20) NOT NULL,
		CONSTRAINT CK_TrangThaiNhanVien CHECK (TrangThai in (N'Còn làm', N'Đã nghỉ làm')),
);
go

-- Sequence cho bảng KhachHang
CREATE SEQUENCE KhachHangSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE KhachHang
(
    MaKhachHang CHAR(9) PRIMARY KEY DEFAULT ('KH' + RIGHT('000000' + CAST(NEXT VALUE FOR KhachHangSequence AS VARCHAR(6)), 6)),
    TenKhachHang NVARCHAR(50) NOT NULL,
    SoDienThoai CHAR(10) NOT NULL UNIQUE,
    Email VARCHAR(50) NOT NULL
);
go

-- Sequence cho bảng SanPham
CREATE SEQUENCE SanPhamSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE SanPham
(
    MaSanPham CHAR(6) PRIMARY KEY DEFAULT ('SP' + RIGHT('0000' + CAST(NEXT VALUE FOR SanPhamSequence AS VARCHAR(4)), 4)),
    TenSanPham NVARCHAR(100) NOT NULL,
    SoLuong INT NOT NULL,
	GiaMua MONEY NOT NULL,
    GiaBan MONEY NOT NULL,
	LoaiSanPham NVARCHAR(10)
		CONSTRAINT CK_LoaiSanPham CHECK (LoaiSanPham IN (N'Đồ ăn', N'Nước uống')),
	Anh NVARCHAR(100) NOT NULL
);
go

-- Sequence cho bảng LoaiGhe
CREATE SEQUENCE LoaiGheSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE LoaiGhe
(
    MaLoaiGhe CHAR(6) PRIMARY KEY DEFAULT ('LG' + RIGHT('000' + CAST(NEXT VALUE FOR LoaiGheSequence AS VARCHAR(3)), 3)),
    TenLoaiGhe NVARCHAR(50) NOT NULL,
    MoTaLoaiGhe NVARCHAR(500) NULL
);
go

-- Sequence cho bảng Phong
CREATE SEQUENCE PhongSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE Phong
(
    MaPhong CHAR(6) PRIMARY KEY DEFAULT ('PH' + RIGHT('000' + CAST(NEXT VALUE FOR PhongSequence AS VARCHAR(3)), 3)),
    TenPhong NVARCHAR(50) NOT NULL UNIQUE,
    SoLuongGhe INT NOT NULL
);
go

-- Sequence cho bảng Ghe
CREATE SEQUENCE GheSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE Ghe
(
    MaGhe CHAR(7) PRIMARY KEY DEFAULT ('Ghe' + RIGHT('0000' + CAST(NEXT VALUE FOR GheSequence AS VARCHAR(4)), 4)),
    ViTri NVARCHAR(10),
    MaLoaiGhe CHAR(6),
	MaPhong CHAR(6),
    CONSTRAINT FK_LoaiGhe FOREIGN KEY (MaLoaiGhe) REFERENCES LoaiGhe(MaLoaiGhe),
	CONSTRAINT FK_MaPhong FOREIGN KEY (MaPhong) REFERENCES Phong(MaPhong)
);
go

-- Sequence cho bảng Phim
CREATE SEQUENCE PhimSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE Phim
(
    MaPhim CHAR(6) PRIMARY KEY DEFAULT ('P' + RIGHT('000' + CAST(NEXT VALUE FOR PhimSequence AS VARCHAR(3)), 3)),
    TenPhim NVARCHAR(100) NOT NULL,
    TheLoai NVARCHAR(100) NOT NULL,
    DaoDien NVARCHAR(50) NOT NULL,
    ThoiLuong INT NOT NULL,
    NgayCongChieu SMALLDATETIME NOT NULL,
	NgonNgu NVARCHAR(50) NOT NULL,
    QuocGia NVARCHAR(50) NOT NULL,
    TrangThai NVARCHAR(50) NOT NULL
		CONSTRAINT CK_TrangThai CHECK (TrangThai IN (N'Đã phát hành', N'Chưa phát hành')),
	NgayBatDau SMALLDATETIME NOT NULL,
	GiaThau MONEY NOT NULL,
	Anh NVARCHAR(100) NOT NULL,
	DoanPhimGioiThieu NVARCHAR(200) NOT NULL,
	TomTat NVARCHAR(2000) NOT NULL
);
go

-- Sequence cho bảng LichChieu
CREATE SEQUENCE LichChieuSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE LichChieu
(
    MaLichChieu CHAR(8) PRIMARY KEY DEFAULT ('LC' + RIGHT('000000' + CAST(NEXT VALUE FOR LichChieuSequence AS VARCHAR(6)), 6)),
    MaPhong CHAR(6) NOT NULL,
    MaPhim CHAR(6) NOT NULL,
    GioBatDau SMALLDATETIME NOT NULL,
    GioKetThuc SMALLDATETIME NOT NULL,
    GiaMotGhe MONEY NOT NULL,
    CONSTRAINT FK_Phong FOREIGN KEY (MaPhong) REFERENCES Phong(MaPhong),
    CONSTRAINT FK_Phim FOREIGN KEY (MaPhim) REFERENCES Phim(MaPhim)
);
go

-- Sequence cho bảng KhuyenMai
CREATE SEQUENCE KhuyenMaiSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE KhuyenMai
(
    MaKhuyenMai CHAR(6) PRIMARY KEY DEFAULT ('KM' + RIGHT('0000' + CAST(NEXT VALUE FOR KhuyenMaiSequence AS VARCHAR(4)), 4)),
	TenKhuyenMai NVARCHAR(20),
	NgayBatDau SMALLDATETIME NOT NULL,
	NgayKetThuc SMALLDATETIME NOT NULL,
	TongTienToiThieu MONEY NOT NULL,
	PhanTramKhuyenMai FLOAT NOT NULL
);
go

-- Sequence cho bảng HoaDon
CREATE SEQUENCE HoaDonSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE HoaDon
(
    MaHoaDon CHAR(8) PRIMARY KEY DEFAULT ('HD' + RIGHT('000000' + CAST(NEXT VALUE FOR HoaDonSequence AS VARCHAR(6)), 6)),
    NgayDat SMALLDATETIME NOT NULL,
    SoGhe INT NOT NULL,
    GhiChu NVARCHAR(300) NULL,
    MaKhachHang CHAR(9) NOT NULL,
    MaNhanVien CHAR(6) NOT NULL,
	MaKhuyenMai CHAR(6) NOT NULL,
    TongTien MONEY,
	VAT FLOAT,
    CONSTRAINT FK_KhachHang FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang),
    CONSTRAINT FK_NhanVien FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien),
	CONSTRAINT FK_KhuyenMai FOREIGN KEY (MaKhuyenMai) REFERENCES KhuyenMai(MaKhuyenMai),
);
go

-- Sequence cho bảng ChiTietHoaDon
CREATE SEQUENCE ChiTietHoaDonSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE ChiTietHoaDon
(
    SoLuong INT NOT NULL,
    MaHoaDon CHAR(8) NOT NULL,
    MaSanPham CHAR(6) NOT NULL,
    ThanhTien MONEY,
    PRIMARY KEY (MaHoaDon, MaSanPham),
    CONSTRAINT FK_HoaDonChiTiet FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
    CONSTRAINT FK_SanPham FOREIGN KEY (MaSanPham) REFERENCES SanPham(MaSanPham)
);
go

-- Sequence cho bảng Ve
CREATE SEQUENCE VeSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE Ve
(
    MaVe CHAR(8) PRIMARY KEY DEFAULT ('Ve' + RIGHT('000000' + CAST(NEXT VALUE FOR VeSequence AS VARCHAR(6)), 6)),
    NgayPhatHanh SMALLDATETIME NOT NULL,
    MaGhe CHAR(7) NOT NULL,
    MaLichChieu CHAR(8) NOT NULL,
    MaHoaDon CHAR(8)  NOT NULL,
    CONSTRAINT FK_Ghe FOREIGN KEY (MaGhe) REFERENCES Ghe(MaGhe),
    CONSTRAINT FK_LichChieu FOREIGN KEY (MaLichChieu) REFERENCES LichChieu(MaLichChieu),
    CONSTRAINT FK_HoaDon FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon)
);
go

ALTER TABLE Phim
ALTER COLUMN Anh NVARCHAR(255) NOT NULL;
go

ALTER TABLE SanPham
ALTER COLUMN Anh NVARCHAR(255) NOT NULL;
go

/* add data */
INSERT INTO Phong(TenPhong, SoLuongGhe)
VALUES
(N'Phòng 1',192),
(N'Phòng 2',192),
(N'Phòng 3',192),
(N'Phòng 4',192),
(N'Phòng 5',192),
(N'Phòng 6',192),
(N'Phòng 7',192);
go


INSERT INTO LoaiGhe(TenLoaiGhe, MoTaLoaiGhe)
VALUES
	(N'Ghế thường', N'Là hàng ghế 1-4 (tính từ màn chiếu).'),
	(N'Ghế VIP', N'Thường sẽ bắt đầu từ hàng ghế thứ 5 trở về sau (tính từ màn chiếu).'),
	(N'Ghế đôi Sweetbox', N'Là hàng ghế cuối của phòng chiếu.');
go

CREATE PROCEDURE tao_ghe_theo_hang (@HangGhe CHAR(1), @MaPhong CHAR(6))
AS
BEGIN
    DECLARE @ViTri NVARCHAR(10)
    DECLARE @MaLoaiGhe CHAR(6)
    DECLARE @i INT = 1

    IF @HangGhe IN ('A', 'B', 'C', 'D') -- Nếu hàng là A, B, hoặc C, tạo ghế thường
        SELECT @MaLoaiGhe = MaLoaiGhe FROM LoaiGhe WHERE TenLoaiGhe = N'Ghế thường'
    ELSE IF @HangGhe = 'M' -- Nếu hàng là M, tạo ghế loại Ghế Sweetbox
        SELECT @MaLoaiGhe = MaLoaiGhe FROM LoaiGhe WHERE TenLoaiGhe = N'Ghế đôi Sweetbox'
    ELSE
        SELECT @MaLoaiGhe = MaLoaiGhe FROM LoaiGhe WHERE TenLoaiGhe = N'Ghế VIP' -- Ngược lại, tạo ghế VIP

    WHILE (@i <= 16)
    BEGIN
        IF @HangGhe = 'M'
        BEGIN
            -- Tạo một ghế Sweetbox nhưng gồm 2 vị trí liên tiếp
            SET @ViTri = @HangGhe + RIGHT('00' + CAST(@i AS VARCHAR(2)), 2) + '-' + RIGHT('00' + CAST(@i + 1 AS VARCHAR(2)), 2)
            INSERT INTO Ghe (ViTri, MaPhong, MaLoaiGhe)
            VALUES (@ViTri, @MaPhong, @MaLoaiGhe)
            
            SET @i = @i + 2
        END
        ELSE
        BEGIN
            -- Tạo một ghế cho các loại ghế khác
            SET @ViTri = @HangGhe + RIGHT('00' + CAST(@i AS VARCHAR(2)), 2)
            INSERT INTO Ghe (ViTri, MaPhong, MaLoaiGhe)
			VALUES (@ViTri, @MaPhong, @MaLoaiGhe)
            SET @i = @i + 1
        END
    END
END
GO

CREATE PROCEDURE tao_ghe_theo_phong (@MaPhong CHAR(6))
AS
BEGIN
    EXECUTE tao_ghe_theo_hang 'A', @MaPhong
    EXECUTE tao_ghe_theo_hang 'B', @MaPhong
    EXECUTE tao_ghe_theo_hang 'C', @MaPhong
    EXECUTE tao_ghe_theo_hang 'D', @MaPhong
    EXECUTE tao_ghe_theo_hang 'E', @MaPhong
    EXECUTE tao_ghe_theo_hang 'F', @MaPhong
    EXECUTE tao_ghe_theo_hang 'G', @MaPhong
    EXECUTE tao_ghe_theo_hang 'H', @MaPhong
    EXECUTE tao_ghe_theo_hang 'I', @MaPhong
    EXECUTE tao_ghe_theo_hang 'K', @MaPhong
    EXECUTE tao_ghe_theo_hang 'L', @MaPhong
	EXECUTE tao_ghe_theo_hang 'M', @MaPhong
END
go

EXECUTE tao_ghe_theo_phong 'PH001';
EXECUTE tao_ghe_theo_phong 'PH002';
EXECUTE tao_ghe_theo_phong 'PH003';
EXECUTE tao_ghe_theo_phong 'PH004';
EXECUTE tao_ghe_theo_phong 'PH005';
EXECUTE tao_ghe_theo_phong 'PH006';
EXECUTE tao_ghe_theo_phong 'PH007';
go

USE [QuanLyBanVe]
GO
INSERT [dbo].[TaiKhoan] ([Username], [MatKhau]) VALUES (N'admin', N'$2a$10$qshwvMWqFcl2VTbNUw23cOvRHsJmQ5qZb0ETyGxxTXKaSAb.kJPOO')
INSERT [dbo].[TaiKhoan] ([Username], [MatKhau]) VALUES (N'baouyen', N'$2a$10$qshwvMWqFcl2VTbNUw23cOvRHsJmQ5qZb0ETyGxxTXKaSAb.kJPOO')
INSERT [dbo].[TaiKhoan] ([Username], [MatKhau]) VALUES (N'trong', N'$2a$10$qshwvMWqFcl2VTbNUw23cOvRHsJmQ5qZb0ETyGxxTXKaSAb.kJPOO')
INSERT [dbo].[TaiKhoan] ([Username], [MatKhau]) VALUES (N'nguyenvanb', N'$2a$10$qshwvMWqFcl2VTbNUw23cOvRHsJmQ5qZb0ETyGxxTXKaSAb.kJPOO')
INSERT [dbo].[TaiKhoan] ([Username], [MatKhau]) VALUES (N'thudao', N'$2a$10$qshwvMWqFcl2VTbNUw23cOvRHsJmQ5qZb0ETyGxxTXKaSAb.kJPOO')
INSERT [dbo].[TaiKhoan] ([Username], [MatKhau]) VALUES (N'minhhoang', N'$2a$10$g.hCsU9Xj02KDyOB8.YJ/Ov3yNrcPjePQGcAsTPlWsA9xFiq8gG1e')
INSERT [dbo].[TaiKhoan] ([Username], [MatKhau]) VALUES (N'hanhpham', N'$2a$10$rA9HzfT8f7m5lXMFmhnG3.WU9Fo/sG.Raz1uAcIjvUN7RbX9A3CSW')
INSERT [dbo].[TaiKhoan] ([Username], [MatKhau]) VALUES (N'hongnhung', N'$2a$10$QXLQHGpLO8OlEFD4H2vnBe1uP/zWzISdjxYPFT8T3BvUmQbfv5mF2')
INSERT [dbo].[TaiKhoan] ([Username], [MatKhau]) VALUES (N'quangtran', N'$2a$10$6J9OEXpO.ZSRs59RLO/yhuiBf0PV/d8ZsSiMIh/8Ml.e1WEVJ4wEm')
INSERT [dbo].[TaiKhoan] ([Username], [MatKhau]) VALUES (N'namnguyen', N'$2a$10$wDEv8TbKv8JHmfXNPjZyjeXuW7esTYY7Q53ihLg9yRY.ZDtcuvXOG')
GO

INSERT [dbo].[NhanVien] ([HoTen], [GioiTinh], [NgaySinh], [Email], [SoDienThoai], [VaiTro], [NgayBatDau], [Luong], [MaTaiKhoan], [TrangThai]) 
VALUES (N'Nguyễn Thị Kiều Trang', 1, CAST(N'2004-10-16T00:00:00' AS SmallDateTime), N'tronggg01010100@gmail.com', N'0328546227', N'Quản lý', CAST(N'2023-01-01T00:00:00' AS SmallDateTime), 2000.0000, N'TK001', N'Còn làm')

INSERT [dbo].[NhanVien] ([HoTen], [GioiTinh], [NgaySinh], [Email], [SoDienThoai], [VaiTro], [NgayBatDau], [Luong], [MaTaiKhoan], [TrangThai]) 
VALUES (N'Lê Thị Bảo Uyên', 0, CAST(N'2004-09-17T00:00:00' AS SmallDateTime), N'lebaouyen17092004@gmail.com', N'0862580072', N'Nhân viên', CAST(N'2023-03-15T00:00:00' AS SmallDateTime), 1500.0000, N'TK002', N'Còn làm')

INSERT [dbo].[NhanVien] ([HoTen], [GioiTinh], [NgaySinh], [Email], [SoDienThoai], [VaiTro], [NgayBatDau], [Luong], [MaTaiKhoan], [TrangThai]) 
VALUES (N'Nguyễn Thành Trọng', 1, CAST(N'2004-10-16T00:00:00' AS SmallDateTime), N'nguyenthanhtrong16102004@gmail.com', N'0338546227', N'Nhân viên', CAST(N'2023-05-10T00:00:00' AS SmallDateTime), 1800.0000, N'TK003', N'Còn làm')

INSERT [dbo].[NhanVien] ([HoTen], [GioiTinh], [NgaySinh], [Email], [SoDienThoai], [VaiTro], [NgayBatDau], [Luong], [MaTaiKhoan], [TrangThai]) 
VALUES (N'Hoàng Thị Lan', 0, CAST(N'1992-07-30T00:00:00' AS SmallDateTime), N'lan.hoang@gmail.com', N'0938123459', N'Nhân viên', CAST(N'2023-06-20T00:00:00' AS SmallDateTime), 1700.0000, N'TK004', N'Đã nghỉ làm')

INSERT [dbo].[NhanVien] ([HoTen], [GioiTinh], [NgaySinh], [Email], [SoDienThoai], [VaiTro], [NgayBatDau], [Luong], [MaTaiKhoan], [TrangThai]) 
VALUES (N'Võ Văn Hưng', 1, CAST(N'1985-09-05T00:00:00' AS SmallDateTime), N'hung.vo@gmail.com', N'0939123460', N'Nhân viên', CAST(N'2023-08-01T00:00:00' AS SmallDateTime), 1900.0000, N'TK005', N'Đã nghỉ làm')

INSERT [dbo].[NhanVien] ([HoTen], [GioiTinh], [NgaySinh], [Email], [SoDienThoai], [VaiTro], [NgayBatDau], [Luong], [MaTaiKhoan], [TrangThai]) 
VALUES (N'Nguyễn Thị Hương', 0, CAST(N'1994-12-12T00:00:00' AS SmallDateTime), N'huong.nguyen@gmail.com', N'0940123461', N'Nhân viên', CAST(N'2023-09-15T00:00:00' AS SmallDateTime), 1600.0000, N'TK006', N'Còn làm')

INSERT [dbo].[NhanVien] ([HoTen], [GioiTinh], [NgaySinh], [Email], [SoDienThoai], [VaiTro], [NgayBatDau], [Luong], [MaTaiKhoan], [TrangThai]) 
VALUES (N'Lê Thanh Tùng', 1, CAST(N'1987-04-25T00:00:00' AS SmallDateTime), N'tung.le@gmail.com', N'0941123462', N'Nhân viên', CAST(N'2023-10-01T00:00:00' AS SmallDateTime), 2000.0000, N'TK007', N'Đã nghỉ làm')

INSERT [dbo].[NhanVien] ([HoTen], [GioiTinh], [NgaySinh], [Email], [SoDienThoai], [VaiTro], [NgayBatDau], [Luong], [MaTaiKhoan], [TrangThai]) 
VALUES (N'Đặng Thu Phương', 0, CAST(N'1993-08-18T00:00:00' AS SmallDateTime), N'phuong.dang@gmail.com', N'0942123463', N'Nhân viên', CAST(N'2023-11-10T00:00:00' AS SmallDateTime), 1750.0000, N'TK008', N'Còn làm')

INSERT [dbo].[NhanVien] ([HoTen], [GioiTinh], [NgaySinh], [Email], [SoDienThoai], [VaiTro], [NgayBatDau], [Luong], [MaTaiKhoan], [TrangThai]) 
VALUES (N'Bùi Văn Kiên', 1, CAST(N'1991-05-19T00:00:00' AS SmallDateTime), N'kien.bui@gmail.com', N'0943123464', N'Nhân viên', CAST(N'2023-12-01T00:00:00' AS SmallDateTime), 1850.0000, N'TK009', N'Còn làm')

INSERT [dbo].[NhanVien] ([HoTen], [GioiTinh], [NgaySinh], [Email], [SoDienThoai], [VaiTro], [NgayBatDau], [Luong], [MaTaiKhoan], [TrangThai]) 
VALUES (N'Hồ Thị Vân Anh', 0, CAST(N'1989-10-15T00:00:00' AS SmallDateTime), N'vananh.ho@gmail.com', N'0944123465', N'Nhân viên', CAST(N'2024-01-05T00:00:00' AS SmallDateTime), 1600.0000, N'TK010', N'Còn làm')
GO

INSERT [dbo].[KhachHang] ([TenKhachHang], [SoDienThoai], [Email]) 
VALUES (N'Lê Thị Bảo Uyên', N'0862580072', N'lebaouyen17092004@gmail.com')

INSERT [dbo].[KhachHang] ([TenKhachHang], [SoDienThoai], [Email]) 
VALUES (N'Trần Thị Bích', N'0912345678', N'bich.tran@gmail.com')

INSERT [dbo].[KhachHang] ([TenKhachHang], [SoDienThoai], [Email]) 
VALUES (N'Nguyễn Thành Trọng', N'0328546227', N'tronggg01010100@gmail.com')

INSERT [dbo].[KhachHang] ([TenKhachHang], [SoDienThoai], [Email]) 
VALUES (N'Phạm Thị Dung', N'0934567890', N'dung.pham@gmail.com')

INSERT [dbo].[KhachHang] ([TenKhachHang], [SoDienThoai], [Email]) 
VALUES (N'Hoàng Minh Đức', N'0945678901', N'duc.hoang@gmail.com')

INSERT [dbo].[KhachHang] ([TenKhachHang], [SoDienThoai], [Email]) 
VALUES (N'Bùi Ngọc Anh', N'0956789012', N'anh.bui@gmail.com')

INSERT [dbo].[KhachHang] ([TenKhachHang], [SoDienThoai], [Email]) 
VALUES (N'Vũ Thanh Phong', N'0967890123', N'phong.vu@gmail.com')

INSERT [dbo].[KhachHang] ([TenKhachHang], [SoDienThoai], [Email]) 
VALUES (N'Đặng Huyền My', N'0978901234', N'my.dang@gmail.com')

INSERT [dbo].[KhachHang] ([TenKhachHang], [SoDienThoai], [Email]) 
VALUES (N'Tô Hải Yến', N'0989012345', N'yen.to@gmail.com')

INSERT [dbo].[KhachHang] ([TenKhachHang], [SoDienThoai], [Email]) 
VALUES (N'Lý Bảo Long', N'0990123456', N'long.ly@gmail.com')
GO

INSERT [dbo].[KhuyenMai] ([TenKhuyenMai], [NgayBatDau], [NgayKetThuc], [TongTienToiThieu], [PhanTramKhuyenMai]) 
VALUES (N'Khuyến mãi 1', CAST(N'2024-06-01T15:30:00' AS SmallDateTime), CAST(N'2024-11-01T15:30:00' AS SmallDateTime), 50000.0000, 0.02)

INSERT [dbo].[KhuyenMai] ([TenKhuyenMai], [NgayBatDau], [NgayKetThuc], [TongTienToiThieu], [PhanTramKhuyenMai]) 
VALUES (N'Khuyến mãi 2', CAST(N'2024-06-29T15:30:00' AS SmallDateTime), CAST(N'2024-10-01T15:30:00' AS SmallDateTime), 200000.0000, 0.03)

INSERT [dbo].[KhuyenMai] ([TenKhuyenMai], [NgayBatDau], [NgayKetThuc], [TongTienToiThieu], [PhanTramKhuyenMai]) 
VALUES (N'Khuyến mãi 3', CAST(N'2024-06-01T15:30:00' AS SmallDateTime), CAST(N'2024-09-01T15:30:00' AS SmallDateTime), 600000.0000, 0.4)

INSERT [dbo].[KhuyenMai] ([TenKhuyenMai], [NgayBatDau], [NgayKetThuc], [TongTienToiThieu], [PhanTramKhuyenMai]) 
VALUES (N'Khuyến mãi 4', CAST(N'2024-06-01T15:30:00' AS SmallDateTime), CAST(N'2024-07-01T15:30:00' AS SmallDateTime), 30000.0000, 0.15)

INSERT [dbo].[KhuyenMai] ([TenKhuyenMai], [NgayBatDau], [NgayKetThuc], [TongTienToiThieu], [PhanTramKhuyenMai]) 
VALUES (N'Khuyến mãi 5', CAST(N'2024-10-01T15:30:00' AS SmallDateTime), CAST(N'2024-12-01T15:30:00' AS SmallDateTime), 300000.0000, 0.3)

INSERT [dbo].[KhuyenMai] ([TenKhuyenMai], [NgayBatDau], [NgayKetThuc], [TongTienToiThieu], [PhanTramKhuyenMai]) 
VALUES (N'Khuyến mãi 6', CAST(N'2024-07-15T10:00:00' AS SmallDateTime), CAST(N'2024-11-15T10:00:00' AS SmallDateTime), 250000.0000, 0.05)

INSERT [dbo].[KhuyenMai] ([TenKhuyenMai], [NgayBatDau], [NgayKetThuc], [TongTienToiThieu], [PhanTramKhuyenMai]) 
VALUES (N'Khuyến mãi 7', CAST(N'2024-08-01T12:00:00' AS SmallDateTime), CAST(N'2024-10-30T12:00:00' AS SmallDateTime), 100000.0000, 0.1)

INSERT [dbo].[KhuyenMai] ([TenKhuyenMai], [NgayBatDau], [NgayKetThuc], [TongTienToiThieu], [PhanTramKhuyenMai]) 
VALUES (N'Khuyến mãi 8', CAST(N'2024-09-01T00:00:00' AS SmallDateTime), CAST(N'2024-11-30T00:00:00' AS SmallDateTime), 39000.0000, 0.3)

INSERT [dbo].[KhuyenMai] ([TenKhuyenMai], [NgayBatDau], [NgayKetThuc], [TongTienToiThieu], [PhanTramKhuyenMai]) 
VALUES (N'Khuyến mãi 9', CAST(N'2024-08-15T09:30:00' AS SmallDateTime), CAST(N'2024-10-15T09:30:00' AS SmallDateTime), 99000.0000, 0.08)

INSERT [dbo].[KhuyenMai] ([TenKhuyenMai], [NgayBatDau], [NgayKetThuc], [TongTienToiThieu], [PhanTramKhuyenMai]) 
VALUES (N'Khuyến mãi 10', CAST(N'2024-11-01T15:00:00' AS SmallDateTime), CAST(N'2025-01-01T15:00:00' AS SmallDateTime), 59000.0000, 0.35)

INSERT [dbo].[KhuyenMai] ([TenKhuyenMai], [NgayBatDau], [NgayKetThuc], [TongTienToiThieu], [PhanTramKhuyenMai]) 
VALUES (N'Khuyến mãi 11', CAST(N'2024-11-01T15:00:00' AS SmallDateTime), CAST(N'2025-12-01T15:00:00' AS SmallDateTime), 1000000.0000, 0.35)
GO

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2024-06-01T15:30:00' AS SmallDateTime), 2, N'No butter', N'KH000001', N'NV001', N'KM0001', 260180.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2024-06-02T18:00:00' AS SmallDateTime), 4, N'Extra ice', N'KH000002', N'NV002', N'KM0002', 553960.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2024-06-03T20:00:00' AS SmallDateTime), 3, N'No sugar', N'KH000003', N'NV003', N'KM0003', 415770.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2024-06-04T19:45:00' AS SmallDateTime), 5, N'VIP seats', N'KH000004', N'NV004', N'KM0004', 643450.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2024-06-05T14:30:00' AS SmallDateTime), 1, N'Extra cheese', N'KH000005', N'NV005', N'KM0001', 138490.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2024-06-06T21:00:00' AS SmallDateTime), 2, N'Family package', N'KH000006', N'NV006', N'KM0001', 257180.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2024-06-07T16:15:00' AS SmallDateTime), 6, N'Student discount', N'KH000007', N'NV007', N'KM0002', 771540.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2024-06-08T13:45:00' AS SmallDateTime), 3, N'No notes', N'KH000008', N'NV008', N'KM0003', 405000.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2024-06-09T11:00:00' AS SmallDateTime), 4, N'Child ticket', N'KH000009', N'NV009', N'KM0002', 520000.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2024-06-10T17:30:00' AS SmallDateTime), 2, N'Birthday discount', N'KH000010', N'NV010', N'KM0004', 274000.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2024-01-15T14:20:00' AS SmallDateTime), 3, N'Khuyến mãi sinh nhật', N'KH000002', N'NV003', N'KM0001', 390470.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2024-02-10T18:45:00' AS SmallDateTime), 2, N'Gói gia đình', N'KH000004', N'NV002', N'KM0002', 282380.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2024-03-05T16:00:00' AS SmallDateTime), 4, N'Không bơ', N'KH000006', N'NV005', N'KM0003', 454360.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2024-04-18T19:30:00' AS SmallDateTime), 3, N'Khuyến mãi sinh viên', N'KH000007', N'NV004', N'KM0004', 355770.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2024-05-22T15:15:00' AS SmallDateTime), 5, N'Thêm phô mai', N'KH000009', N'NV006', N'KM0005', 615000.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2024-06-11T17:45:00' AS SmallDateTime), 2, N'Khuyến mãi sinh nhật', N'KH000010', N'NV007', N'KM0004', 294000.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2024-07-20T20:30:00' AS SmallDateTime), 3, N'Ghế VIP', N'KH000001', N'NV001', N'KM0001', 390270.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2024-09-10T13:00:00' AS SmallDateTime), 4, N'Gói gia đình', N'KH000003', N'NV003', N'KM0002', 554360.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2024-10-28T18:00:00' AS SmallDateTime), 2, N'Không bơ', N'KH000005', N'NV002', N'KM0003', 286980.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2024-11-11T16:30:00' AS SmallDateTime), 5, N'Khuyến mãi sinh viên', N'KH000008', N'NV004', N'KM0005', 620000.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2025-01-14T15:45:00' AS SmallDateTime), 2, N'Ghế VIP', N'KH000002', N'NV005', N'KM0004', 306980.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2025-03-28T17:15:00' AS SmallDateTime), 4, N'Không bơ', N'KH000004', N'NV006', N'KM0001', 469760.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2025-04-14T13:30:00' AS SmallDateTime), 3, N'Khuyến mãi sinh viên', N'KH000006', N'NV007', N'KM0002', 390770.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2025-06-10T19:00:00' AS SmallDateTime), 5, N'Gói gia đình', N'KH000009', N'NV003', N'KM0004', 615000.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2025-07-25T21:30:00' AS SmallDateTime), 4, N'Thêm phô mai', N'KH000010', N'NV001', N'KM0005', 518000.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2025-08-20T14:15:00' AS SmallDateTime), 2, N'Khuyến mãi sinh nhật', N'KH000001', N'NV002', N'KM0002', 300180.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2025-09-15T16:45:00' AS SmallDateTime), 3, N'Ghế VIP', N'KH000003', N'NV004', N'KM0001', 390770.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2025-10-28T20:10:00' AS SmallDateTime), 2, N'Không bơ', N'KH000005', N'NV006', N'KM0004', 316980.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2025-11-05T18:30:00' AS SmallDateTime), 5, N'Khuyến mãi sinh viên', N'KH000007', N'NV007', N'KM0003', 597950.0000, 0.1)

INSERT [dbo].[HoaDon] ([NgayDat], [SoGhe], [GhiChu], [MaKhachHang], [MaNhanVien], [MaKhuyenMai], [TongTien], [VAT]) 
VALUES (CAST(N'2025-12-23T19:45:00' AS SmallDateTime), 4, N'Gói gia đình', N'KH000009', N'NV002', N'KM0001', 480000.0000, 0.1)

GO

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'Bắp rang bơ', 100, 15000.0000, 30000.0000, N'Đồ ăn', N'images/bap_rang_bo.jpg')

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'Nước ngọt Coca-Cola', 200, 10000.0000, 20000.0000, N'Nước uống', N'images/coca_cola.png')

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'Nước suối Aquafina', 150, 5000.0000, 10000.0000, N'Nước uống', N'images/aquafina.jpg')

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'Combo Bắp nước', 50, 30000.0000, 60000.0000, N'Đồ ăn', N'images/combo_bap_nuoc.png')

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'Bắp Rang Bơ', 150, 25000.0000, 50000.0000, N'Đồ ăn', N'images/baprangbo.jpg');

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'Bắp Rang Bơ Lớn', 120, 45000.0000, 90000.0000, N'Đồ ăn', N'images/bapranglon.jpg');

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'Bắp Thường', 200, 20000.0000, 40000.0000, N'Đồ ăn', N'images/bapthuong.jpg');

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'Combo Bắp Lớn', 50, 75000.0000, 150000.0000, N'Đồ ăn', N'images/combobaplon.jpg');

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'Combo Bắp Nhỏ', 80, 60000.0000, 120000.0000, N'Đồ ăn', N'images/combobapnho.jpg');

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'Combo Bắp Thường', 100, 47500.0000, 95000.0000, N'Đồ ăn', N'images/combobapthuong.jpg');

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'Combo Bắp Nước A', 40, 125000.0000, 250000.0000, N'Đồ ăn', N'images/combobapnuoc.jpg');

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'Combo Bắp Nước B', 40, 125000.0000, 250000.0000, N'Đồ ăn', N'images/combobu.jpg');

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'Pessi', 50, 121000.0000, 121000.0000, N'Nước uống', N'images/pepsi.png');

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'Coca', 100, 150000.0000, 150000.0000, N'Nước uống', N'images/coca_cola.png');

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'Nước Suối', 200, 10000.0000, 10000.0000, N'Nước uống', N'images/nuocsuoi.jpg');

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'Fanta Cam', 80, 25000.0000, 25000.0000, N'Nước uống', N'images/fanta.jpg');

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'Sprite', 60, 25000.0000, 25000.0000, N'Nước uống', N'images/sprite.jpg');

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'7Up', 75, 23000.0000, 23000.0000, N'Nước uống', N'images/7up.jpg');

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'Combo Nước A', 40, 270000.0000, 270000.0000, N'Nước uống', N'images/Combonuocngot.jpg');

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'Combo Nước B', 40, 270000.0000, 270000.0000, N'Nước uống', N'images/combonuoc.jpg');

INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) 
VALUES (N'Combo Nước C', 40, 270000.0000, 270000.0000, N'Nước uống', N'images/comboC.jpg');


GO

INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (3, N'HD000001', N'SP0002', 60000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (2, N'HD000001', N'SP0004', 120000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (3, N'HD000002', N'SP0001', 90000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (2, N'HD000002', N'SP0002', 40000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (5, N'HD000002', N'SP0004', 300000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (4, N'HD000003', N'SP0001', 120000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (1, N'HD000004', N'SP0006', 24000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (4, N'HD000005', N'SP0002', 80000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (6, N'HD000005', N'SP0005', 240000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (2, N'HD000011', N'SP0002', 40000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (1, N'HD000011', N'SP0003', 10000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (3, N'HD000012', N'SP0001', 90000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (2, N'HD000013', N'SP0004', 120000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (3, N'HD000013', N'SP0005', 120000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (1, N'HD000014', N'SP0006', 24000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (2, N'HD000015', N'SP0001', 60000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (4, N'HD000015', N'SP0002', 80000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (3, N'HD000016', N'SP0003', 30000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (1, N'HD000017', N'SP0004', 60000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (2, N'HD000017', N'SP0006', 48000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (3, N'HD000018', N'SP0002', 60000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (2, N'HD000019', N'SP0003', 20000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (4, N'HD000019', N'SP0005', 160000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (3, N'HD000020', N'SP0002', 60000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (2, N'HD000021', N'SP0001', 60000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (1, N'HD000021', N'SP0006', 24000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (4, N'HD000022', N'SP0004', 240000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (2, N'HD000023', N'SP0003', 20000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (3, N'HD000023', N'SP0005', 120000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (1, N'HD000024', N'SP0002', 20000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (3, N'HD000025', N'SP0001', 90000.0000)
INSERT [dbo].[ChiTietHoaDon] ([SoLuong], [MaHoaDon], [MaSanPham], [ThanhTien]) VALUES (2, N'HD000025', N'SP0006', 48000.0000)
GO
INSERT [dbo].[Phim] ([TenPhim], [TheLoai], [DaoDien], [ThoiLuong], [NgayCongChieu], [NgonNgu], [QuocGia], [TrangThai], [NgayBatDau], [GiaThau], [Anh], [DoanPhimGioiThieu], [TomTat]) 
VALUES (N'Venom: Kèo Cuối', N'Hành Động, Giả Tưởng', N'Kelly Marcel', 109, CAST(N'2024-10-23T00:00:00' AS SmallDateTime), N'Tiếng Anh', N'Mỹ', N'Đã phát hành', CAST(N'2024-10-23T00:00:00' AS SmallDateTime), 12000000.0000, N'images/venom_keo_cuoi.jpg', N'https://www.youtube.com/watch?v=P0C9ccMXtqc', N'Sau chuyến du lịch ngắn sang quê nhà của Spider-Man: No Way Home (2021), Eddie Brock (Tom Hardy) giờ đây cùng Venom “hành hiệp trượng nghĩa” và “nhai đầu” hết đám tội phạm trong thành phố...')

INSERT [dbo].[Phim] ([TenPhim], [TheLoai], [DaoDien], [ThoiLuong], [NgayCongChieu], [NgonNgu], [QuocGia], [TrangThai], [NgayBatDau], [GiaThau], [Anh], [DoanPhimGioiThieu], [TomTat]) 
VALUES (N'Cô Dâu Hào Môn', N'Tình Cảm', N'Vũ Ngọc Đãng', 114, CAST(N'2024-10-16T00:00:00' AS SmallDateTime), N'Tiếng Việt', N'Việt Nam', N'Đã phát hành', CAST(N'2024-10-16T00:00:00' AS SmallDateTime), 2500000.0000, N'images/co_dau_hao_mon.jpg', N'https://www.youtube.com/watch?v=cwumN4-rLWY&t', N'Uyển Ân chính thức lên xe hoa trong thế giới thượng lưu...')

INSERT [dbo].[Phim] ([TenPhim], [TheLoai], [DaoDien], [ThoiLuong], [NgayCongChieu], [NgonNgu], [QuocGia], [TrangThai], [NgayBatDau], [GiaThau], [Anh], [DoanPhimGioiThieu], [TomTat]) 
VALUES (N'Elli và Bí Ẩn Chiếc Tàu Ma', N'Hoạt Hình, Phiêu Lưu, Gia Đình', N'Jespe Møller, Piet De Rycker', 87, CAST(N'2024-10-25T00:00:00' AS SmallDateTime), N'Tiếng Đức', N'Đức', N'Đã phát hành', CAST(N'2024-10-25T00:00:00' AS SmallDateTime), 1200000.0000, N'images/elli_va_tau_ma.jpg', N'https://www.youtube.com/watch?v=9tDZpBbg8Ow&t', N'Một cuộc truy đuổi của hồn ma nhỏ Elli và những người bạn...')

INSERT [dbo].[Phim] ([TenPhim], [TheLoai], [DaoDien], [ThoiLuong], [NgayCongChieu], [NgonNgu], [QuocGia], [TrangThai], [NgayBatDau], [GiaThau], [Anh], [DoanPhimGioiThieu], [TomTat]) 
VALUES (N'Ngày Xưa Có Một Chuyện Tình', N'Tình Cảm', N'Trịnh Đình Lê Minh', 135, CAST(N'2024-10-25T00:00:00' AS SmallDateTime), N'Tiếng Việt', N'Việt Nam', N'Đã phát hành', CAST(N'2024-10-25T00:00:00' AS SmallDateTime), 35000000.0000, N'images/ngay_xua_co_mot_chuyen_tinh.jpg', N'https://www.youtube.com/watch?v=68xnG7hkEOE&t', N'Sau thành công của Mắt Biếc, tác phẩm do nhà văn Nguyễn Nhật Ánh chấp bút...')

INSERT [dbo].[Phim] ([TenPhim], [TheLoai], [DaoDien], [ThoiLuong], [NgayCongChieu], [NgonNgu], [QuocGia], [TrangThai], [NgayBatDau], [GiaThau], [Anh], [DoanPhimGioiThieu], [TomTat]) 
VALUES (N'Mufasa: Vua Sư Tử', N'Hoạt Hình, Phiêu Lưu', N'Barry Jenkins', 109, CAST(N'2024-12-20T00:00:00' AS SmallDateTime), N'Tiếng Anh', N'Mỹ', N'Chưa phát hành', CAST(N'2024-12-20T00:00:00' AS SmallDateTime), 45000000.0000, N'images/mufasa_vua_su_tu.jpg', N'https://www.youtube.com/watch?v=XcS9JwQEUag&t', N'Thương hiệu kinh điển và nổi tiếng nhất nhì hãng Disney sẽ trở lại...')

INSERT [dbo].[Phim] ([TenPhim], [TheLoai], [DaoDien], [ThoiLuong], [NgayCongChieu], [NgonNgu], [QuocGia], [TrangThai], [NgayBatDau], [GiaThau], [Anh], [DoanPhimGioiThieu], [TomTat]) 
VALUES (N'Thiên Đường Quả Báo', N'Tâm Lý, Giật Gân', N'Boss Kuno', 120, CAST(N'2024-10-30T00:00:00' AS SmallDateTime), N'Tiếng Thái', N'Thái Lan', N'Chưa phát hành', CAST(N'2024-10-30T00:00:00' AS SmallDateTime), 15000000.0000, N'images/thien_duong_qua_bao.jpg', N'https://www.youtube.com/watch?v=2-oT85FKYsQ&t', N'Thongkam và Sek làm lụng vất vả để xây dựng cơ ngơi...')

INSERT [dbo].[Phim] ([TenPhim], [TheLoai], [DaoDien], [ThoiLuong], [NgayCongChieu], [NgonNgu], [QuocGia], [TrangThai], [NgayBatDau], [GiaThau], [Anh], [DoanPhimGioiThieu], [TomTat]) 
VALUES (N'Godzilla -1.0', N'Giả Tưởng', N'Yamazaki Takashi', 125, CAST(N'2024-11-01T00:00:00' AS SmallDateTime), N'Tiếng Nhật', N'Nhật Bản', N'Chưa phát hành', CAST(N'2024-11-01T00:00:00' AS SmallDateTime), 20000000.0000, N'images/godzilla_minus_one.jpg', N'https://www.youtube.com/watch?v=ba7WqCQ_cOQ', N'Godzilla Minus One/ Godzilla -1.0 là niềm tự hào của Nhật Bản...')

INSERT [dbo].[Phim] ([TenPhim], [TheLoai], [DaoDien], [ThoiLuong], [NgayCongChieu], [NgonNgu], [QuocGia], [TrangThai], [NgayBatDau], [GiaThau], [Anh], [DoanPhimGioiThieu], [TomTat]) 
VALUES (N'Đôi Bạn Học Yêu', N'Hài, Lãng Mạn', N'E.Oni', 109, CAST(N'2024-11-07T00:00:00' AS SmallDateTime), N'Tiếng Hàn', N'Hàn Quốc', N'Chưa phát hành', CAST(N'2024-11-07T00:00:00' AS SmallDateTime), 14000000.0000, N'images/doi_ban_hoc_yeu.jpg', N'https://www.youtube.com/watch?v=WOpnzT2Jb0A', N'Jae-hee và Heung-soo là đôi bạn học đã tạo nên câu chuyện đầy màu sắc...')

INSERT [dbo].[Phim] ([TenPhim], [TheLoai], [DaoDien], [ThoiLuong], [NgayCongChieu], [NgonNgu], [QuocGia], [TrangThai], [NgayBatDau], [GiaThau], [Anh], [DoanPhimGioiThieu], [TomTat]) 
VALUES (N'Tee Yod: Quỷ Ăn Tạng Phần 2', N'Kinh Dị', N'Taweewat Wantha', 111, CAST(N'2024-10-10T00:00:00' AS SmallDateTime), N'Tiếng Thái', N'Thái Lan', N'Đã phát hành', CAST(N'2024-10-10T00:00:00' AS SmallDateTime), 12000000.0000, N'images/tee_yod_quy_an_tang_phan_2.jpg', N'https://www.youtube.com/watch?v=kZZkCDc38yI', N'Ba năm sau cái chết của Yam, Yak tiếp tục săn lùng linh hồn bí ẩn...')

INSERT [dbo].[Phim] ([TenPhim], [TheLoai], [DaoDien], [ThoiLuong], [NgayCongChieu], [NgonNgu], [QuocGia], [TrangThai], [NgayBatDau], [GiaThau], [Anh], [DoanPhimGioiThieu], [TomTat]) 
VALUES (N'Trò Chơi Nhân Tính', N'Kinh Dị, Giật Gân', N'William Aherne', 125, CAST(N'2024-10-25T00:00:00' AS SmallDateTime), N'Tiếng Thái', N'Thái Lan', N'Chưa phát hành', CAST(N'2024-10-25T00:00:00' AS SmallDateTime), 17000000.0000, N'images/tro_choi_nhan_tinh.jpg', N'https://www.youtube.com/watch?v=qlcHaI-jRIk', N'Lễ hội trường bỗng biến thành sân chơi "khát máu" của thế lực bí ẩn...')

GO

INSERT [dbo].[LichChieu] ([MaPhong], [MaPhim], [GioBatDau], [GioKetThuc], [GiaMotGhe]) 
VALUES (N'PH001', N'P001', CAST(N'2024-10-30T20:00:00' AS SmallDateTime), CAST(N'2024-10-30T21:49:00' AS SmallDateTime), 85090.0000)

INSERT [dbo].[LichChieu] ([MaPhong], [MaPhim], [GioBatDau], [GioKetThuc], [GiaMotGhe]) 
VALUES (N'PH002', N'P002', CAST(N'2024-10-30T00:00:00' AS SmallDateTime), CAST(N'2024-10-30T01:54:00' AS SmallDateTime), 93490.0000)

INSERT [dbo].[LichChieu] ([MaPhong], [MaPhim], [GioBatDau], [GioKetThuc], [GiaMotGhe]) 
VALUES (N'PH003', N'P003', CAST(N'2024-10-30T00:00:00' AS SmallDateTime), CAST(N'2024-10-30T01:27:00' AS SmallDateTime), 93590.0000)

INSERT [dbo].[LichChieu] ([MaPhong], [MaPhim], [GioBatDau], [GioKetThuc], [GiaMotGhe]) 
VALUES (N'PH004', N'P004', CAST(N'2024-10-30T00:00:00' AS SmallDateTime), CAST(N'2024-10-30T02:15:00' AS SmallDateTime), 83690.0000)

INSERT [dbo].[LichChieu] ([MaPhong], [MaPhim], [GioBatDau], [GioKetThuc], [GiaMotGhe]) 
VALUES (N'PH005', N'P005', CAST(N'2024-10-30T00:00:00' AS SmallDateTime), CAST(N'2024-10-30T01:49:00' AS SmallDateTime), 93490.0000)

INSERT [dbo].[LichChieu] ([MaPhong], [MaPhim], [GioBatDau], [GioKetThuc], [GiaMotGhe]) 
VALUES (N'PH006', N'P006', CAST(N'2024-10-30T00:00:00' AS SmallDateTime), CAST(N'2024-10-30T02:00:00' AS SmallDateTime), 83590.0000)

INSERT [dbo].[LichChieu] ([MaPhong], [MaPhim], [GioBatDau], [GioKetThuc], [GiaMotGhe]) 
VALUES (N'PH007', N'P007', CAST(N'2024-10-30T00:00:00' AS SmallDateTime), CAST(N'2024-10-30T02:05:00' AS SmallDateTime), 83590.0000)

INSERT [dbo].[LichChieu] ([MaPhong], [MaPhim], [GioBatDau], [GioKetThuc], [GiaMotGhe]) 
VALUES (N'PH001', N'P008', CAST(N'2024-11-01T14:00:00' AS SmallDateTime), CAST(N'2024-11-01T15:49:00' AS SmallDateTime), 90000.0000)

INSERT [dbo].[LichChieu] ([MaPhong], [MaPhim], [GioBatDau], [GioKetThuc], [GiaMotGhe]) 
VALUES (N'PH002', N'P009', CAST(N'2024-11-01T18:30:00' AS SmallDateTime), CAST(N'2024-11-01T20:21:00' AS SmallDateTime), 85000.0000)

INSERT [dbo].[LichChieu] ([MaPhong], [MaPhim], [GioBatDau], [GioKetThuc], [GiaMotGhe]) 
VALUES (N'PH003', N'P010', CAST(N'2024-11-01T21:00:00' AS SmallDateTime), CAST(N'2024-11-01T23:05:00' AS SmallDateTime), 92000.0000)

INSERT [dbo].[LichChieu] ([MaPhong], [MaPhim], [GioBatDau], [GioKetThuc], [GiaMotGhe]) 
VALUES (N'PH001', N'P001', CAST(N'2024-11-10T19:33:00' AS SmallDateTime), CAST(N'2024-11-10T21:22:00' AS SmallDateTime), 50000.0000)

INSERT [dbo].[LichChieu] ([MaPhong], [MaPhim], [GioBatDau], [GioKetThuc], [GiaMotGhe]) 
VALUES (N'PH001', N'P001', CAST(N'2024-11-10T19:47:00' AS SmallDateTime), CAST(N'2024-11-10T21:36:00' AS SmallDateTime), 55000.0000)

INSERT [dbo].[LichChieu] ([MaPhong], [MaPhim], [GioBatDau], [GioKetThuc], [GiaMotGhe]) 
VALUES (N'PH001', N'P002', CAST(N'2024-11-17T19:47:00' AS SmallDateTime), CAST(N'2024-11-17T21:41:00' AS SmallDateTime), 450000.0000)

INSERT [dbo].[LichChieu] ([MaPhong], [MaPhim], [GioBatDau], [GioKetThuc], [GiaMotGhe]) 
VALUES (N'PH001', N'P001', CAST(N'2024-11-17T19:47:00' AS SmallDateTime), CAST(N'2024-11-17T21:36:00' AS SmallDateTime), 60000.0000)

INSERT [dbo].[LichChieu] ([MaPhong], [MaPhim], [GioBatDau], [GioKetThuc], [GiaMotGhe]) 
VALUES (N'PH001', N'P001', CAST(N'2024-11-17T19:48:00' AS SmallDateTime), CAST(N'2024-11-17T21:37:00' AS SmallDateTime), 47000.0000)

INSERT [dbo].[LichChieu] ([MaPhong], [MaPhim], [GioBatDau], [GioKetThuc], [GiaMotGhe]) 
VALUES (N'PH001', N'P001', CAST(N'2024-11-17T15:51:00' AS SmallDateTime), CAST(N'2024-11-17T17:40:00' AS SmallDateTime), 50000.0000)

INSERT [dbo].[LichChieu] ([MaPhong], [MaPhim], [GioBatDau], [GioKetThuc], [GiaMotGhe]) 
VALUES (N'PH004', N'P004', CAST(N'2024-11-17T03:15:00' AS SmallDateTime), CAST(N'2024-11-17T05:30:00' AS SmallDateTime), 50000.0000)

INSERT [dbo].[LichChieu] ([MaPhong], [MaPhim], [GioBatDau], [GioKetThuc], [GiaMotGhe]) 
VALUES (N'PH001', N'P009', CAST(N'2024-11-10T02:16:00' AS SmallDateTime), CAST(N'2024-11-10T04:07:00' AS SmallDateTime), 50000.0000)

INSERT [dbo].[LichChieu] ([MaPhong], [MaPhim], [GioBatDau], [GioKetThuc], [GiaMotGhe]) 
VALUES (N'PH001', N'P009', CAST(N'2024-11-17T02:11:00' AS SmallDateTime), CAST(N'2024-11-17T04:02:00' AS SmallDateTime), 50000.0000)

INSERT [dbo].[LichChieu] ([MaPhong], [MaPhim], [GioBatDau], [GioKetThuc], [GiaMotGhe]) 
VALUES (N'PH007', N'P003', CAST(N'2024-11-17T01:24:00' AS SmallDateTime), CAST(N'2024-11-17T02:51:00' AS SmallDateTime), 50000.0000)

GO

INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-01T00:00:00' AS SmallDateTime), N'Ghe0001', N'LC000001', N'HD000001')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-01T00:00:00' AS SmallDateTime), N'Ghe0002', N'LC000001', N'HD000001')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-02T00:00:00' AS SmallDateTime), N'Ghe0003', N'LC000002', N'HD000002')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-02T00:00:00' AS SmallDateTime), N'Ghe0004', N'LC000002', N'HD000002')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-02T00:00:00' AS SmallDateTime), N'Ghe0005', N'LC000002', N'HD000002')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-02T00:00:00' AS SmallDateTime), N'Ghe0006', N'LC000002', N'HD000002')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-03T00:00:00' AS SmallDateTime), N'Ghe0007', N'LC000003', N'HD000003')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-03T00:00:00' AS SmallDateTime), N'Ghe0008', N'LC000003', N'HD000003')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-03T00:00:00' AS SmallDateTime), N'Ghe0009', N'LC000003', N'HD000003')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-04T00:00:00' AS SmallDateTime), N'Ghe0010', N'LC000004', N'HD000004')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-04T00:00:00' AS SmallDateTime), N'Ghe0011', N'LC000004', N'HD000004')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-04T00:00:00' AS SmallDateTime), N'Ghe0012', N'LC000004', N'HD000004')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-04T00:00:00' AS SmallDateTime), N'Ghe0013', N'LC000004', N'HD000004')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-04T00:00:00' AS SmallDateTime), N'Ghe0014', N'LC000004', N'HD000004')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-05T00:00:00' AS SmallDateTime), N'Ghe0015', N'LC000005', N'HD000005')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-06T00:00:00' AS SmallDateTime), N'Ghe0016', N'LC000006', N'HD000006')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-06T00:00:00' AS SmallDateTime), N'Ghe0017', N'LC000006', N'HD000006')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-07T00:00:00' AS SmallDateTime), N'Ghe0018', N'LC000007', N'HD000007')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-07T00:00:00' AS SmallDateTime), N'Ghe0019', N'LC000007', N'HD000007')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-07T00:00:00' AS SmallDateTime), N'Ghe0020', N'LC000007', N'HD000007')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-07T00:00:00' AS SmallDateTime), N'Ghe0021', N'LC000007', N'HD000007')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-07T00:00:00' AS SmallDateTime), N'Ghe0022', N'LC000007', N'HD000007')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-07T00:00:00' AS SmallDateTime), N'Ghe0023', N'LC000007', N'HD000007')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-08T00:00:00' AS SmallDateTime), N'Ghe0024', N'LC000001', N'HD000008')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-08T00:00:00' AS SmallDateTime), N'Ghe0025', N'LC000001', N'HD000008')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-08T00:00:00' AS SmallDateTime), N'Ghe0026', N'LC000001', N'HD000008')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-09T00:00:00' AS SmallDateTime), N'Ghe0027', N'LC000002', N'HD000009')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-09T00:00:00' AS SmallDateTime), N'Ghe0028', N'LC000002', N'HD000009')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-09T00:00:00' AS SmallDateTime), N'Ghe0029', N'LC000002', N'HD000009')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-09T00:00:00' AS SmallDateTime), N'Ghe0030', N'LC000002', N'HD000009')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-10T00:00:00' AS SmallDateTime), N'Ghe0031', N'LC000003', N'HD000010')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-10T00:00:00' AS SmallDateTime), N'Ghe0032', N'LC000003', N'HD000010')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-01-15T00:00:00' AS SmallDateTime), N'Ghe0033', N'LC000001', N'HD000011')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-01-15T00:00:00' AS SmallDateTime), N'Ghe0034', N'LC000001', N'HD000011')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-02-10T00:00:00' AS SmallDateTime), N'Ghe0035', N'LC000002', N'HD000012')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-02-10T00:00:00' AS SmallDateTime), N'Ghe0036', N'LC000002', N'HD000012')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-03-05T00:00:00' AS SmallDateTime), N'Ghe0037', N'LC000003', N'HD000013')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-03-05T00:00:00' AS SmallDateTime), N'Ghe0038', N'LC000003', N'HD000013')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-03-05T00:00:00' AS SmallDateTime), N'Ghe0039', N'LC000003', N'HD000013')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-04-18T00:00:00' AS SmallDateTime), N'Ghe0040', N'LC000004', N'HD000014')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-04-18T00:00:00' AS SmallDateTime), N'Ghe0041', N'LC000004', N'HD000014')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-04-18T00:00:00' AS SmallDateTime), N'Ghe0042', N'LC000004', N'HD000014')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-05-22T00:00:00' AS SmallDateTime), N'Ghe0043', N'LC000005', N'HD000015')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-05-22T00:00:00' AS SmallDateTime), N'Ghe0044', N'LC000005', N'HD000015')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-05-22T00:00:00' AS SmallDateTime), N'Ghe0045', N'LC000005', N'HD000015')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-05-22T00:00:00' AS SmallDateTime), N'Ghe0046', N'LC000005', N'HD000015')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-11T00:00:00' AS SmallDateTime), N'Ghe0047', N'LC000006', N'HD000016')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-06-11T00:00:00' AS SmallDateTime), N'Ghe0048', N'LC000006', N'HD000016')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-07-20T00:00:00' AS SmallDateTime), N'Ghe0049', N'LC000007', N'HD000017')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-07-20T00:00:00' AS SmallDateTime), N'Ghe0050', N'LC000007', N'HD000017')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-07-20T00:00:00' AS SmallDateTime), N'Ghe0051', N'LC000007', N'HD000017')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-09-10T00:00:00' AS SmallDateTime), N'Ghe0052', N'LC000001', N'HD000018')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-09-10T00:00:00' AS SmallDateTime), N'Ghe0053', N'LC000001', N'HD000018')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-09-10T00:00:00' AS SmallDateTime), N'Ghe0054', N'LC000001', N'HD000018')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-09-10T00:00:00' AS SmallDateTime), N'Ghe0055', N'LC000001', N'HD000018')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-10-28T00:00:00' AS SmallDateTime), N'Ghe0056', N'LC000002', N'HD000019')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-10-28T00:00:00' AS SmallDateTime), N'Ghe0057', N'LC000002', N'HD000019')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-11-11T00:00:00' AS SmallDateTime), N'Ghe0058', N'LC000003', N'HD000020')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-11-11T00:00:00' AS SmallDateTime), N'Ghe0059', N'LC000003', N'HD000020')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2024-11-11T00:00:00' AS SmallDateTime), N'Ghe0060', N'LC000003', N'HD000020')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2025-01-14T00:00:00' AS SmallDateTime), N'Ghe0061', N'LC000004', N'HD000021')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2025-01-14T00:00:00' AS SmallDateTime), N'Ghe0062', N'LC000004', N'HD000021')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2025-03-28T00:00:00' AS SmallDateTime), N'Ghe0063', N'LC000005', N'HD000022')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2025-03-28T00:00:00' AS SmallDateTime), N'Ghe0064', N'LC000005', N'HD000022')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2025-03-28T00:00:00' AS SmallDateTime), N'Ghe0065', N'LC000005', N'HD000022')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2025-03-28T00:00:00' AS SmallDateTime), N'Ghe0066', N'LC000005', N'HD000022')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2025-04-14T00:00:00' AS SmallDateTime), N'Ghe0067', N'LC000006', N'HD000023')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2025-04-14T00:00:00' AS SmallDateTime), N'Ghe0068', N'LC000006', N'HD000023')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2025-04-14T00:00:00' AS SmallDateTime), N'Ghe0069', N'LC000006', N'HD000023')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2025-06-10T00:00:00' AS SmallDateTime), N'Ghe0070', N'LC000007', N'HD000024')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2025-06-10T00:00:00' AS SmallDateTime), N'Ghe0071', N'LC000007', N'HD000024')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2025-06-10T00:00:00' AS SmallDateTime), N'Ghe0072', N'LC000007', N'HD000024')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2025-06-10T00:00:00' AS SmallDateTime), N'Ghe0073', N'LC000007', N'HD000024')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2025-07-25T00:00:00' AS SmallDateTime), N'Ghe0074', N'LC000001', N'HD000025')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2025-07-25T00:00:00' AS SmallDateTime), N'Ghe0075', N'LC000001', N'HD000025')
INSERT [dbo].[Ve] ([NgayPhatHanh], [MaGhe], [MaLichChieu], [MaHoaDon]) VALUES (CAST(N'2025-07-25T00:00:00' AS SmallDateTime), N'Ghe0076', N'LC000001', N'HD000025')
GO
