-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 22, 2019 at 03:40 AM
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_pemesanan`
--

INSERT INTO `detail_pemesanan` (`id_detail_pemesanan`, `id_pemesanan`, `kode_barang`, `jumlah`, `keterangan`, `tanggal_entri`, `update_by`) VALUES
(1, 1, 'PR0002', 56, 'hehee', '2019-08-19 17:34:26', '00000'),
(2, 2, 'PR0001', 56, 'Tambahkan', '2019-08-19 18:00:16', '00000'),
(3, 3, 'PR0001', 67, '', '2019-08-19 18:12:56', '00000'),
(4, 4, 'PR0001', 6, '', '2019-08-19 18:14:33', '00000'),
(5, 4, 'PR0003', 1, 'aaaa', '2019-08-19 18:15:46', '00000'),
(8, 5, 'PR0003', 55, '', '2019-08-19 18:20:14', '00000'),
(9, 6, 'PR0003', 5, '', '2019-08-19 18:27:19', '00000'),
(11, 8, 'PR0002', 6, 'gegegegeg', '2019-08-19 18:38:23', '00000'),
(13, 9, 'PR0002', 6, 'gggg', '2019-08-19 18:39:32', '00000'),
(15, 9, 'PR0003', 5, 'gggg', '2019-08-19 18:39:56', '00000');

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `header_pemesanan`
--

INSERT INTO `header_pemesanan` (`id_pemesanan`, `no_pemesanan`, `no_customer`, `total_pemesanan`, `tanggal_pemesanan`, `tanggal_bayar`, `status_pemesanan`, `tanggal_entri`, `update_by`) VALUES
(1, 'PO/2019/08/0001', 'CS20190001', 0, '2019-08-19', NULL, 0, '2019-08-19 17:33:46', '00000'),
(2, 'PO/2019/08/0002', 'CS20190001', 0, '2019-08-19', NULL, 0, '2019-08-19 18:00:00', '00000'),
(3, 'PO/2019/08/0003', 'CS20190001', 0, '2019-08-19', NULL, 0, '2019-08-19 18:12:46', '00000'),
(4, 'PO/2019/08/0004', 'CS20190001', 7, '2019-08-19', NULL, 0, '2019-08-19 18:14:28', '00000'),
(5, 'PO/2019/08/0005', 'CS20190001', 128, '2019-08-21', NULL, 0, '2019-08-19 18:17:40', '00000'),
(6, 'PO/2019/08/0006', 'CS20190001', -10, '2019-08-19', NULL, 0, '2019-08-19 18:27:12', '00000'),
(7, 'PO/2019/08/0007', 'CS20190001', 0, '2019-08-19', NULL, 0, '2019-08-19 18:36:29', '00000'),
(8, 'PO/2019/08/0008', 'CS20190001', 12, '2019-08-19', NULL, 0, '2019-08-19 18:38:15', '00000'),
(9, 'PO/2019/08/0009', 'CS20190001', 11, '2019-08-19', NULL, 0, '2019-08-19 18:39:26', '00000');

-- --------------------------------------------------------

--
-- Table structure for table `mst_barang`
--

CREATE TABLE IF NOT EXISTS `mst_barang` (
  `kode_barang` varchar(6) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `warna` varchar(20) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `kode_supplier` varchar(5) NOT NULL,
  `tanggal_entri` datetime NOT NULL,
  `update_by` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mst_barang`
--

INSERT INTO `mst_barang` (`kode_barang`, `nama_barang`, `warna`, `jumlah`, `harga`, `kode_supplier`, `tanggal_entri`, `update_by`) VALUES
('PR0001', 'SINTETIS MERAH', 'Merah', 439, 10000, 'SU001', '2019-08-19 10:12:23', '00001'),
('PR0002', 'SINTETIS BIRU', 'Biru', 182, 20000, 'SU001', '2019-08-19 11:37:13', '00001'),
('PR0003', 'SINTETIS HITAM', 'Hitam', 451, 15000, 'SU002', '2019-08-19 18:14:08', '00001');

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

--
-- Dumping data for table `mst_customer`
--

INSERT INTO `mst_customer` (`no_customer`, `nama_customer`, `alamat`, `kota`, `no_telp`, `tanggal_system`, `update_by`) VALUES
('CS20190001', 'PT Puninar Jaya', 'Jl. Tipar Cakung', 'Jakarta', '02186589907', '2019-08-19 00:00:00', '00001');

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

--
-- Dumping data for table `mst_supplier`
--

INSERT INTO `mst_supplier` (`kode_supplier`, `nama_supplier`, `alamat`, `kota`, `no_telp`, `tanggal_entri`, `nik`) VALUES
('SU001', 'Edmondo', 'Jl. Kebagusan V', 'Jakarta', '0812345678901', '2019-08-17 12:33:02', '00000'),
('SU002', 'Lazada', 'Jl. Cideng Raya', 'Jakarta', '0821311212114', '2019-08-17 12:35:11', '00000'),
('SU003', 'Latex Co', 'Jl. Radio Dalam', 'Jakarta', '085671234890', '2019-08-17 12:34:27', '00000'),
('SU004', 'Origami', 'Jl. Dago Raya', 'Bandung', '08783122467', '2019-08-17 12:35:45', '00000'),
('SU005', 'Docomo Dotel', 'Jl. Kemanggisan Barat', 'Jakarta', '0812234981256', '2019-08-17 22:48:19', '00000');

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
('00000', 'ADMINISTRATOR', 'Admin', 'e10adc3949ba59abbe56e057f20f883e', '0000-00-00 00:00:00', ''),
('00001', 'Norman Adi', 'Admin', 'd11caf44a791c0cf6a2a981937019adf', '2019-08-15 23:03:48', '00000'),
('00002', 'Danila R', 'Kasir', 'd19f12b4b87cc69454157743e77ecef5', '2019-08-17 10:20:19', '00000'),
('00003', 'Sarmadi', 'Kurir', '1914f94b0ecb5b35bc6c3bcd8887ccb6', '2019-08-17 22:48:04', '00000');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `surat_jalan`
--

INSERT INTO `surat_jalan` (`id_surat_jalan`, `no_surat_jalan`, `id_pemesanan`, `nik_pengirim`, `tanggal_pengiriman`, `tanggal_entri`, `update_by`) VALUES
(2, 'SJL/2019/08/0001', 9, '00003', '2019-08-20', '2019-08-20 20:27:36', '00000');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `no_transaksi`, `metode_pembayaran`, `jenis_pembayaran`, `tanggal_transaksi`, `tanggal_bayar`, `id_pemesanan`, `id_surat_jalan`, `total_bayar`, `yang_dibayar`, `selisih`, `tanggal_entri`, `update_by`) VALUES
(1, 'STR/2019/08/21/0001', 'Tunai', '', '2019-08-21 00:00:00', '2019-08-21 00:00:00', 9, 2, 195000, 200000, -5000, '2019-08-21 20:53:40', '00000'),
(2, 'STR/2019/08/21/0002', '', '', '2019-08-21 00:00:00', '2019-08-21 00:00:00', 8, 0, 120000, 150000, -30000, '2019-08-21 21:00:41', '00000');

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
  MODIFY `id_detail_pemesanan` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `header_pemesanan`
--
ALTER TABLE `header_pemesanan`
  MODIFY `id_pemesanan` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `surat_jalan`
--
ALTER TABLE `surat_jalan`
  MODIFY `id_surat_jalan` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
