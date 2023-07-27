-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 23, 2019 at 04:43 PM
-- Server version: 5.6.26
-- PHP Version: 5.5.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sistempenjualan`
--

-- --------------------------------------------------------

--
-- Table structure for table `detail_pemesanan`
--

CREATE TABLE IF NOT EXISTS `detail_pemesanan` (
  `id_detail_pemesanan` int(11) NOT NULL,
  `id_pemesanan` int(11) NOT NULL,
  `kode_barang` varchar(6) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `keterangan` varchar(200) NOT NULL,
  `tanggal_entri` datetime NOT NULL,
  `update_by` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `header_pemesanan`
--

CREATE TABLE IF NOT EXISTS `header_pemesanan` (
  `id_pemesanan` int(11) NOT NULL,
  `no_pemesanan` varchar(15) NOT NULL,
  `no_customer` varchar(10) NOT NULL,
  `total_pemesanan` int(11) NOT NULL,
  `tanggal_pemesanan` date NOT NULL,
  `tanggal_bayar` date DEFAULT NULL,
  `status_pemesanan` int(11) NOT NULL DEFAULT '0' COMMENT '0 = save, 1 = confirm',
  `tanggal_entri` datetime NOT NULL,
  `update_by` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `mst_barang`
--

CREATE TABLE IF NOT EXISTS `mst_barang` (
  `kode_barang` varchar(6) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `warna` varchar(20) DEFAULT NULL,
  `jumlah` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `kode_supplier` varchar(5) DEFAULT NULL,
  `tanggal_entri` datetime NOT NULL,
  `update_by` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `mst_customer`
--

CREATE TABLE IF NOT EXISTS `mst_customer` (
  `no_customer` varchar(10) NOT NULL,
  `nama_customer` varchar(100) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `kota` varchar(20) NOT NULL,
  `no_telp` varchar(15) NOT NULL,
  `tanggal_system` datetime NOT NULL,
  `update_by` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `mst_supplier`
--

CREATE TABLE IF NOT EXISTS `mst_supplier` (
  `kode_supplier` varchar(5) NOT NULL,
  `nama_supplier` varchar(100) NOT NULL,
  `alamat` varchar(200) NOT NULL,
  `kota` varchar(25) NOT NULL,
  `no_telp` varchar(15) NOT NULL,
  `tanggal_entri` datetime NOT NULL,
  `nik` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `mst_user`
--

CREATE TABLE IF NOT EXISTS `mst_user` (
  `nik` varchar(5) NOT NULL,
  `nama_user` varchar(100) NOT NULL,
  `role` varchar(50) NOT NULL,
  `password` varchar(32) NOT NULL,
  `tanggal_system` datetime NOT NULL,
  `update_by` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mst_user`
--

INSERT INTO `mst_user` (`nik`, `nama_user`, `role`, `password`, `tanggal_system`, `update_by`) VALUES
('00000', 'ADMINISTRATOR', 'Admin', 'e10adc3949ba59abbe56e057f20f883e', '0000-00-00 00:00:00', '');

-- --------------------------------------------------------

--
-- Table structure for table `surat_jalan`
--

CREATE TABLE IF NOT EXISTS `surat_jalan` (
  `id_surat_jalan` int(11) NOT NULL,
  `no_surat_jalan` varchar(16) NOT NULL,
  `id_pemesanan` int(11) NOT NULL,
  `nik_pengirim` varchar(5) NOT NULL,
  `tanggal_pengiriman` date DEFAULT NULL,
  `tanggal_entri` datetime NOT NULL,
  `update_by` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE IF NOT EXISTS `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `no_transaksi` varchar(19) NOT NULL,
  `metode_pembayaran` varchar(20) NOT NULL,
  `jenis_pembayaran` varchar(20) NOT NULL,
  `tanggal_transaksi` datetime NOT NULL,
  `tanggal_bayar` datetime DEFAULT NULL,
  `id_pemesanan` int(11) NOT NULL,
  `id_surat_jalan` int(11) DEFAULT NULL,
  `total_bayar` int(11) NOT NULL,
  `yang_dibayar` int(11) NOT NULL,
  `selisih` int(11) NOT NULL,
  `tanggal_entri` datetime NOT NULL,
  `update_by` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detail_pemesanan`
--
ALTER TABLE `detail_pemesanan`
  ADD PRIMARY KEY (`id_detail_pemesanan`);

--
-- Indexes for table `header_pemesanan`
--
ALTER TABLE `header_pemesanan`
  ADD PRIMARY KEY (`id_pemesanan`),
  ADD UNIQUE KEY `no_pemesanan` (`no_pemesanan`);

--
-- Indexes for table `mst_customer`
--
ALTER TABLE `mst_customer`
  ADD PRIMARY KEY (`no_customer`);

--
-- Indexes for table `mst_supplier`
--
ALTER TABLE `mst_supplier`
  ADD PRIMARY KEY (`kode_supplier`);

--
-- Indexes for table `mst_user`
--
ALTER TABLE `mst_user`
  ADD PRIMARY KEY (`nik`);

--
-- Indexes for table `surat_jalan`
--
ALTER TABLE `surat_jalan`
  ADD PRIMARY KEY (`id_surat_jalan`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD UNIQUE KEY `no_transaksi` (`no_transaksi`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_pemesanan`
--
ALTER TABLE `detail_pemesanan`
  MODIFY `id_detail_pemesanan` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `header_pemesanan`
--
ALTER TABLE `header_pemesanan`
  MODIFY `id_pemesanan` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `surat_jalan`
--
ALTER TABLE `surat_jalan`
  MODIFY `id_surat_jalan` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
