drop database IF EXISTS UngDung;
CREATE DATABASE IF NOT EXISTS UngDung;
USE UngDung;

CREATE TABLE IF NOT EXISTS KhachHang (
	MaKhachHang int not null AUTO_INCREMENT,
	Ten VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL unique,
    DiaChi VARCHAR(255) NOT NULL,
    SDT VARCHAR(255) NOT NULL unique,
    Pass varchar(255) NOT NULL,
    PRIMARY KEY (MaKhachHang)
);

CREATE TABLE IF NOT EXISTS LoaiHang (
	MaLoai int not null AUTO_INCREMENT,
	Ten VARCHAR(255) NOT NULL,
    PRIMARY KEY (MaLoai)
);

CREATE TABLE IF NOT EXISTS MatHang (
	MaMatHang int not null AUTO_INCREMENT,
	Ten VARCHAR(255) NOT NULL,
    Gia int NOT NULL,
    SoLuong int NOT NULL,
    MaLoai int NOT NULL,
    PRIMARY KEY (MaMatHang),
	FOREIGN KEY (MaLoai)
        REFERENCES LoaiHang (MaLoai)
        ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS GopY (
	MaSP int NOT NULL,
	MaKH int NOT NULL,
	Cmt VARCHAR(255) NOT NULL ,
	PRIMARY KEY (MaSP,MaKH),
	FOREIGN KEY (MaKH)
        REFERENCES KhachHang (MaKhachHang)
        ON UPDATE RESTRICT ON DELETE CASCADE,
	FOREIGN KEY (MaSP)
        REFERENCES MatHang (MaMatHang)
        ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS LoaiNhanVien (
	MaLoaiNV int NOT NULL AUTO_INCREMENT,
	TenLoai VARCHAR(255) NOT NULL,
	PRIMARY KEY (MaLoaiNV)
);

CREATE TABLE IF NOT EXISTS NhanVien (
	MaNhanVien int NOT NULL AUTO_INCREMENT,
	MaLoai int NOT NULL,
    Ten varchar(255) not null,
    pass varchar(255) not null,
	PRIMARY KEY (MaNhanVien),
	FOREIGN KEY (MaLoai)
        REFERENCES LoaiNhanVien (MaLoaiNV)
        ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS DonDatHang (
	MaDon int not null AUTO_INCREMENT,
	XacNhan boolean NOT NULL,
	HinhThucThanhToan varchar(255) not null,
    TongTien long not null,
    MaKH int not null,
    MaNVGiao int not null,
	PRIMARY KEY (MaDon),
	FOREIGN KEY (MaKH)
        REFERENCES KhachHang (MaKhachHang)
        ON UPDATE RESTRICT ON DELETE CASCADE,
	FOREIGN KEY (MaNVGiao)
        REFERENCES NhanVien (MaNhanVien)
        ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ChiTietDonDat (
	MaMH int not null,
	MaDon int NOT NULL,
	SoLuong int not null,
    GiaBan long not null,
    TinhTrang varchar(255) not null,
	PRIMARY KEY (MaMH,MaDon),
	FOREIGN KEY (MaMH)
        REFERENCES MatHang (MaMatHang)
        ON UPDATE RESTRICT ON DELETE CASCADE,
	FOREIGN KEY (MaDon)
        REFERENCES DonDatHang (MaDon)
        ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS DoiTacQuangCao (
	MaDT int not null AUTO_INCREMENT,
	Ten varchar(255) NOT NULL,
	PRIMARY KEY (MaDT)
);

CREATE TABLE IF NOT EXISTS HopDongQuangCao (
	MaHD int not null AUTO_INCREMENT,
	NgayKi datetime NOT NULL,
	ThoiHan int not null,
    ThongTin varchar(255) not null,
    NVQuanLy int not null,
    MaDT int not null,
	PRIMARY KEY (MaHD),
	FOREIGN KEY (NVQuanLy)
        REFERENCES NhanVien (MaNhanVien)
        ON UPDATE RESTRICT ON DELETE CASCADE,
	FOREIGN KEY (MaDT)
        REFERENCES DoiTacQuangCao (MaDT)
        ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS NhaCungCap (
	MaNCC int not null AUTO_INCREMENT,
	Ten varchar(255) NOT NULL,
	DiaChi varchar(255) not null,
	PRIMARY KEY (MaNCC)
);

CREATE TABLE IF NOT EXISTS DonNhapHang (
	MaDonNhap int not null AUTO_INCREMENT,
	NgayLap datetime NOT NULL,
	LyDo varchar(255) not null,
    MaNCC int not null,
    MaNVLap int not null,
	PRIMARY KEY (MaDonNhap),
	FOREIGN KEY (MaNCC)
        REFERENCES NhaCungCap (MaNCC)
        ON UPDATE RESTRICT ON DELETE CASCADE,
	FOREIGN KEY (MaNVLap)
        REFERENCES NhanVien (MaNhanVien)
        ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ChiTietDonNhapHang (
	MaMH int not null,
	MaDonNhap int NOT NULL,
	SoLuongNhap int not null,
	PRIMARY KEY (MaMH,MaDonNhap),
	FOREIGN KEY (MaMH)
        REFERENCES MatHang (MaMatHang)
        ON UPDATE RESTRICT ON DELETE CASCADE,
	FOREIGN KEY (MaDonNhap)
        REFERENCES DonNhapHang (MaDonNhap)
        ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS DonTraHang (
	MaDonTra int not null AUTO_INCREMENT,
	NgayLap datetime NOT NULL,
	LyDo varchar(255) not null,
    MaNCC int not null,
    MaNVLap int not null,
	PRIMARY KEY (MaDonTra),
	FOREIGN KEY (MaNCC)
        REFERENCES NhaCungCap (MaNCC)
        ON UPDATE RESTRICT ON DELETE CASCADE,
	FOREIGN KEY (MaNVLap)
        REFERENCES NhanVien (MaNhanVien)
        ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ChiTietDonTra (
	MaMH int not null,
	MaDonTra int NOT NULL,
	SoLuongTra int not null,
	PRIMARY KEY (MaMH,MaDonTra),
	FOREIGN KEY (MaMH)
        REFERENCES MatHang (MaMatHang)
        ON UPDATE RESTRICT ON DELETE CASCADE,
	FOREIGN KEY (MaDonTra)
        REFERENCES DonTraHang (MaDonTra)
        ON UPDATE RESTRICT ON DELETE CASCADE
);

Insert into KhachHang values(1,'Nguyễn Văn A','123@gmail.com','456 Phan Đình phùng','123456789','1');
Insert into KhachHang values(2,'Nguyễn Văn B','baba@gmail.com','234 Nguyễn Đình Chiểu','128456789','1');

Insert into LoaiNhanVien values(1,'Nhân viên giao hàng');

Insert into NhanVien values(1,1,'Pokemon','1');

Insert into LoaiHang values(1,'bánh kẹo');
Insert into MatHang values(1,'bánh',1,1,1);

insert into GopY values(1,1,'dở');

insert into DonDatHang values(1,true,'atm',100,1,1);