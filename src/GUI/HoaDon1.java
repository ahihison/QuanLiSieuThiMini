/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import java.util.Calendar;
import java.util.GregorianCalendar;
import DTO.HoaDon;
import DTO.ChiTietHoaDon;
import DAL.HoaDonDAL;
import DAL.ChiTietHoaDonDAL;
import DAL.LoaiHangDAL;
import DAL.SanPhamDAL;
import DTO.LoaiHang;
import DTO.SanPham;
import com.sun.jdi.connect.spi.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.Query.value;
import javax.swing.DefaultComboBoxModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/*import com.example.package1.ConnectionProvider;*/
/**
 *
 * @author TruongHo
 */

public class HoaDon1 extends javax.swing.JFrame {

    public String formatDateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateFormat.format(date);
		return dateString;
	}
    /**
     * Creates new form HoaDon1
     */
    public HoaDon1() throws SQLException {
        initComponents();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
model.setRowCount(0);
        antext();
        new Thread() {
            public void run() {
                while (true) {
                    Calendar ca = new GregorianCalendar();
                    int hour = ca.get(Calendar.HOUR);
                    int minute = ca.get(Calendar.MINUTE);
                    int second = ca.get(Calendar.SECOND);
                    int PM_AM = ca.get(Calendar.AM_PM);
                    String day_night;
                    if (PM_AM == 1) {
                        day_night = "PM";
                    } else {
                        day_night = "AM";
                    }
                    String time = hour + ":" + minute + ":" + second + " " + day_night;
                    LBgio.setText(time);
                }
            }
        }.start();
    }
    
    boolean add,chon, fix,addsp = false;

    public void annut() {
        btnxoa.setEnabled(false);
        btnsua.setEnabled(false);
        btnluu.setEnabled(false);
        btnthanhtoan.setEnabled(false);
        btnthem.setEnabled(false);

    }

    public void hiennut() {
        btnxoa.setEnabled(true);
        btnsua.setEnabled(true);
        btnluu.setEnabled(true);
        btnthanhtoan.setEnabled(true);
        btnthem.setEnabled(true);
    }

    public void hientext() {
        TFtenkh.setEnabled(true);
        TFsdt.setEnabled(true);
        TFmakh.setEnabled(true);
        TFngay.setEnabled(true);
        TFsoluong.setEnabled(true);
        TFmahh.setEnabled(true);
        TFthanhtien.setEnabled(true);
        TFgia.setEnabled(true);
        TFmahh.setEnabled(true);
        TFtongtien.setEnabled(true);
        TFdiemthuong.setEnabled(true);
        TFtienkhach.setEnabled(true);
        TFgiamgia.setEnabled(true);
        TFtienthoi.setEnabled(true);
        CBtenhh.setEnabled(true);
    }

    public void antext() {
        CBtenhh.setEnabled(false);
        TFtenkh.setEnabled(false);
        TFsdt.setEnabled(false);
        TFmakh.setEnabled(false);
        TFngay.setEnabled(false);
        TFsoluong.setEnabled(false);
        TFmahh.setEnabled(false);
        TFthanhtien.setEnabled(false);
        TFgia.setEnabled(false);
        TFmahh.setEnabled(false);
        TFtongtien.setEnabled(false);
        TFdiemthuong.setEnabled(false);
        TFtienkhach.setEnabled(false);
        TFgiamgia.setEnabled(false);
        TFtienthoi.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TFtenkh = new javax.swing.JTextField();
        TFsdt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TFmakh = new javax.swing.JTextField();
        TFngay = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TFmahh = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TFsoluong = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        TFgia = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        TFthanhtien = new javax.swing.JTextField();
        CBtenhh = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        TFtongtien = new javax.swing.JTextField();
        btnxoa = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnluu = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btnthem = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        TFtienkhach = new javax.swing.JTextField();
        TFtienthoi = new javax.swing.JTextField();
        TFdiemthuong = new javax.swing.JTextField();
        TFgiamgia = new javax.swing.JTextField();
        btnthanhtoan = new javax.swing.JButton();
        TFmahd = new javax.swing.JTextField();
        btnthemsanpham2 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        TFtong = new javax.swing.JTextField();
        btnxoasanpham = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        LBgio = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        LBmanv = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lbstatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 28)); // NOI18N
        jLabel1.setText("HÓA ĐƠN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(368, 368, 368)
                .addComponent(jLabel1)
                .addContainerGap(571, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(23, 23, 23))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Tên Khách Hàng:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Số Điện Thoại:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Mã Khách Hàng:");

        TFtenkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFtenkhActionPerformed(evt);
            }
        });

        TFsdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFsdtActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Ngày Đặt:");

        TFmakh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFmakhActionPerformed(evt);
            }
        });

        TFngay.setMinSelectableDate(new java.util.Date(-62135791093000L));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TFtenkh, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(TFsdt))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TFmakh, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(TFngay, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TFtenkh)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TFmakh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TFsdt)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFngay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Tên Hàng Hóa:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Mã Hàng Hóa:");

        TFmahh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFmahhActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Số Lượng:");

        TFsoluong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFsoluongActionPerformed(evt);
            }
        });
        TFsoluong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TFsoluongKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Giá:");

        TFgia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFgiaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Thành Tiền:");

        TFthanhtien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFthanhtienActionPerformed(evt);
            }
        });
        TFthanhtien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TFthanhtienKeyReleased(evt);
            }
        });

        CBtenhh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CBtenhhMouseClicked(evt);
            }
        });
        CBtenhh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBtenhhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(142, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TFsoluong)
                    .addComponent(TFthanhtien)
                    .addComponent(CBtenhh, 0, 122, Short.MAX_VALUE))
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TFmahh, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(TFgia))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CBtenhh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)
                        .addComponent(TFmahh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(TFsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(TFgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(TFthanhtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel11.setText("Mã Hóa Đơn:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel12.setText("Tổng Tiền :");

        TFtongtien.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                TFtongtienCaretUpdate(evt);
            }
        });
        TFtongtien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFtongtienActionPerformed(evt);
            }
        });
        TFtongtien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TFtongtienKeyReleased(evt);
            }
        });

        btnxoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnxoa.setIcon(new javax.swing.ImageIcon("D:\\laptrinhjava\\QuanLiSieuThi1\\src\\GUI\\Image\\Delete.png")); // NOI18N
        btnxoa.setText("Hóa Đơn");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnsua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnsua.setIcon(new javax.swing.ImageIcon("D:\\laptrinhjava\\QuanLiSieuThi1\\src\\GUI\\Image\\Change.png")); // NOI18N
        btnsua.setText("Sản Phẩm");

        btnluu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnluu.setIcon(new javax.swing.ImageIcon("D:\\laptrinhjava\\QuanLiSieuThi1\\src\\GUI\\Image\\Save.png")); // NOI18N
        btnluu.setText("Lưu");
        btnluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnluuActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon("D:\\laptrinhjava\\QuanLiSieuThi1\\src\\GUI\\Image\\Print Sale.png")); // NOI18N
        jButton6.setText("Xuất Hóa Đơn");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        btnthem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnthem.setIcon(new javax.swing.ImageIcon("D:\\laptrinhjava\\QuanLiSieuThi1\\src\\GUI\\Image\\Add.png")); // NOI18N
        btnthem.setText("Hóa Đơn");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel13.setText("Tiền Khách:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel14.setText("Điểm Thưởng:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel15.setText("Giảm giá:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel16.setText("Tiền Thối Lại:");

        TFtienkhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFtienkhachActionPerformed(evt);
            }
        });

        TFdiemthuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFdiemthuongActionPerformed(evt);
            }
        });
        TFdiemthuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TFdiemthuongKeyReleased(evt);
            }
        });

        TFgiamgia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFgiamgiaActionPerformed(evt);
            }
        });
        TFgiamgia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TFgiamgiaKeyReleased(evt);
            }
        });

        btnthanhtoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnthanhtoan.setIcon(new javax.swing.ImageIcon("D:\\laptrinhjava\\QuanLiSieuThi1\\src\\GUI\\Image\\Pay.png")); // NOI18N
        btnthanhtoan.setText("Thanh Toán");
        btnthanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthanhtoanActionPerformed(evt);
            }
        });

        TFmahd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFmahdActionPerformed(evt);
            }
        });

        btnthemsanpham2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnthemsanpham2.setIcon(new javax.swing.ImageIcon("D:\\laptrinhjava\\QuanLiSieuThi1\\src\\GUI\\Image\\Add.png")); // NOI18N
        btnthemsanpham2.setText("Sản Phẩm");
        btnthemsanpham2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemsanpham2ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel21.setText("Tổng Tiền Sau Khi Giam:");

        TFtong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFtongActionPerformed(evt);
            }
        });
        TFtong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TFtongKeyReleased(evt);
            }
        });

        btnxoasanpham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnxoasanpham.setIcon(new javax.swing.ImageIcon("D:\\laptrinhjava\\QuanLiSieuThi1\\src\\GUI\\Image\\Delete.png")); // NOI18N
        btnxoasanpham.setText("Sản Phẩm");
        btnxoasanpham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoasanphamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(TFmahd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(123, 123, 123))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(113, 113, 113)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TFtongtien, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                            .addComponent(TFdiemthuong))
                        .addGap(64, 64, 64)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnthemsanpham2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(btnxoasanpham)))
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TFtienkhach, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFgiamgia, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TFtienthoi, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFtong, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnluu, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(btnthanhtoan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6)
                        .addGap(11, 11, 11)))
                .addGap(28, 28, 28))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(TFtongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFtienkhach, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFtienthoi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TFdiemthuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFgiamgia, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFmahd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(TFtong, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthemsanpham2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoasanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnluu, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 241, Short.MAX_VALUE)
        );

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon("D:\\laptrinhjava\\QuanLiSieuThi1\\src\\GUI\\Image\\Back.png")); // NOI18N
        jButton7.setText("Hệ Thống");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel7.setToolTipText("Tên");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Tên:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Mã Nhân Viên:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Giờ:");

        LBgio.setForeground(new java.awt.Color(51, 51, 255));
        LBgio.setText("2:19:00 PM");

        jLabel19.setText("An");

        LBmanv.setText("11");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LBgio, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(131, 131, 131))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(164, 164, 164))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LBmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(LBmanv))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(LBgio))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Hàng", "Mã Hàng", "Số lượng", "Thành tiền"
            }
        ));
        jTable1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTable1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(jTable1);

        lbstatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbstatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbstatus.setText("  Trạng Thái");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(372, 372, 372)
                                .addComponent(lbstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(317, 317, 317))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>                        

    private void TFtenkhActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void TFsdtActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    private void TFmakhActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void TFmahhActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void TFsoluongActionPerformed(java.awt.event.ActionEvent evt) {                                          

    }                                         

    private void TFgiaActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    private void TFthanhtienActionPerformed(java.awt.event.ActionEvent evt) {                                            
      
    }                                           

    private void TFtongtienActionPerformed(java.awt.event.ActionEvent evt) {                                           
       
    }                                          

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {                                        

        try {

            annut();
            antext();
            add = true;
         
            hienthisanpham("them");
            btnluu.setEnabled(true);
            TFmahd.setEnabled(false);
            TFtenkh.setEnabled(true);
            TFmakh.setEnabled(true);
            TFsdt.setEnabled(true);
            TFngay.setEnabled(true);
            TFmahh.setEnabled(true);
            TFsoluong.setEnabled(true);
            TFgia.setEnabled(true);
            TFtienkhach.setEnabled(true);
            CBtenhh.setEnabled(true);
            HoaDonDAL hoadonDAL = new HoaDonDAL();
            int lastMaHD = hoadonDAL.getLastMaHD();
            TFmahd.setText("" + (lastMaHD + 1));
//            SanPhamDAL sanphamDAL = new SanPhamDAL();
//            String tensp = sanphamDAL.gettensp();
//            
//            String[] tenSPArray = sanphamDAL.getTenSP();
//String tenSanPhamText = String.join(", ", tenSPArray); // Nối các phần tử trong mảng bằng dấu phẩy và khoảng trắng
//List<String> keywordList = new ArrayList<>();
//keywordList.addAll(Arrays.asList(tenSPArray));
//String[] keywords = keywordList.toArray(new String[keywordList.size()]);
//
//// Tạo một TextField
//

//
//// Tạo một AutoCompletionBinding và đặt danh sách từ khóa cho nó
//AutoCompletionBinding<String> autoCompleteBinding = TFsanpham.bindAutoCompletion(TFsanpham, keywords);

//
//// Đặt số lượng hàng hiển thị trong danh sách gợi ý
//autoCompleteBinding.setVisibleRowCount(10);

//TFsanpham.setText(tenSanPhamText);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }                                       
    public boolean check() throws SQLException {
       
       if (TFtenkh.getText().isEmpty()) {
    JOptionPane.showMessageDialog(rootPane, "Tên khách hàng trống");
    TFtenkh.requestFocus();
}
  
    
   
          String phone = TFsdt.getText();
if (phone.length() != 10) {
    JOptionPane.showMessageDialog(null, "Số điện thoại phải có 10 số.");
    return false;
}
if (phone.charAt(0) != '0') {
    JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu bằng số 0.");
    return false;
}
for (int i = 0; i < phone.length(); i++) {
    if (!Character.isDigit(phone.charAt(i))) {
        JOptionPane.showMessageDialog(null, "Số điện thoại chỉ chứa các ký tự số.");
        return false;
    }
}
    String makh=TFmakh.getText();
    for (int i = 0; i < makh.length(); i++) {
    if (!Character.isDigit(makh.charAt(i))) {
        JOptionPane.showMessageDialog(null, "Mã khách hàng chỉ chứa các ký tự số.");
        return false;
        }
    }return true;

}
        

    private void btnluuActionPerformed(java.awt.event.ActionEvent evt) {                                       
        try {
           HoaDonDAL goiham=new HoaDonDAL();
            if (check() == true) {
                HoaDon hd = new HoaDon();
                HoaDonDAL hdDAL = new HoaDonDAL();
                if (add) {
                    hd.setMaHd(Integer.parseInt(TFmahd.getText()));
                    hd.setDiemThuong(Integer.parseInt(TFdiemthuong.getText()));
                    hd.setGio(LBgio.getText());
                    hd.setMaKh(Integer.parseInt(TFmakh.getText()));
                    hd.setMaNv(Integer.parseInt(LBmanv.getText()));
                      java.util.Date dateFromUtil = TFngay.getDate();
                      
                    java.sql.Date dateFromSql = new java.sql.Date(dateFromUtil.getTime());

                    hd.setThoiDiem(formatDateToString(dateFromSql));
                    hd.setTienTra(Float.parseFloat(TFtong.getText()));
                    hd.setMucGiam(Float.parseFloat(TFgiamgia.getText()));
                    boolean kiemtra = goiham.themhoadon(hd, "themhoadon", null);
                    if (kiemtra) {

                        hiennut();
                        hientext();
                        btnthanhtoan.setEnabled(true);
                        hienthisanpham("hien thi");
                        add = false;
                     
                    }
 
            }
                 if(addsp){
                        hienthisanpham("hien thi");
                        addsp = false;
                    }
             ChiTietHoaDonDAL hdctdal=new ChiTietHoaDonDAL();
             ChiTietHoaDon hdct=new ChiTietHoaDon();
          
                   hdct.setMaHd(Integer.parseInt(TFmahd.getText()));
                   hdct.setMaSp(Integer.parseInt(TFmahh.getText()));
                   hdct.setSl(Integer.parseInt(TFsoluong.getText()));
                    boolean kiemtra2 = hdctdal.themhoadon(hdct, "themhoadon");
                 
                    if (kiemtra2) {
                        JOptionPane.showMessageDialog(rootPane, "Thêm thành công");
                  
                          
                    
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Thêm thất bại");
                    }
                      hiennut();
                        hientext();
                        btnthanhtoan.setEnabled(true);
                        this.lbstatus.setText("Thêm đơn thành công");
                
        }
            
            }catch (SQLException ex) {
            Logger.getLogger(HoaDon1.class.getName()).log(Level.SEVERE, null, ex);      
        
            
    
            }
    }                                      
public void start()
{
    
}
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        HomeNhanVien nv = new HomeNhanVien();
        this.setVisible(false);
        nv.setVisible(true);
    }                                        

    private void TFtienkhachActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {                                       
      
            int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa Hóa đơn này?", "Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                ChiTietHoaDonDAL deletehdct;
                HoaDonDAL deletehd ;
                try {
                    deletehd=new HoaDonDAL();
                    deletehdct = new  ChiTietHoaDonDAL();
                    if (deletehdct.xoaHoaDon(TFmahd.getText())&& deletehd.xoaHoaDon(TFmahd.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "Xóa hóa đơn thành công!");
                        hienthisanpham("hien thi");
                        hiennut();
                        annut();
                         this.lbstatus.setText("Xóa đơn thành công");
                    }
          
                } catch (SQLException ex) {
            Logger.getLogger(HoaDon1.class.getName()).log(Level.SEVERE, null, ex);
        }

            } 		
           
    }                                      

    private void TFmahdActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void CBtenhhMouseClicked(java.awt.event.MouseEvent evt) {                                     

    }                                    

    private void CBtenhhActionPerformed(java.awt.event.ActionEvent evt) {                                        
        try {

            SanPhamDAL sanphamDAL = new SanPhamDAL();
            String tensp = (String) CBtenhh.getSelectedItem();

            int ma = sanphamDAL.getma(tensp);
            TFmahh.setText("" + ma);
            float giatien = sanphamDAL.getgia(tensp);
            TFgia.setText("" + giatien);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }                                       

    private void btnthemsanpham2ActionPerformed(java.awt.event.ActionEvent evt) {                                                
     try {

            annut();
            antext();
            addsp=true;
            hienthisanpham("them");
            btnluu.setEnabled(true);
            TFmahh.setEnabled(true);
            TFsoluong.setEnabled(true);
            TFgia.setEnabled(true);
            TFtienkhach.setEnabled(true);
            CBtenhh.setEnabled(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }                                               

    private void btnthanhtoanActionPerformed(java.awt.event.ActionEvent evt) {                                             
        hiennut();
        hientext();
        float tongtien = Float.parseFloat(TFtong.getText());
        float tienkhach = Float.parseFloat(TFtienkhach.getText());
        if (tienkhach < tongtien) {
            JOptionPane.showMessageDialog(rootPane, "Số tiền không đủ để thực hiện giao dịch");
        } else {
            float tienthoilai = tienkhach - tongtien;
            TFtienthoi.setText(String.valueOf(tienthoilai));
            this.lbstatus.setText("Thanh toán thành công");
        }
        

    }                                            

    private void TFdiemthuongActionPerformed(java.awt.event.ActionEvent evt) {                                             


    }                                            

    private void TFgiamgiaActionPerformed(java.awt.event.ActionEvent evt) {                                          

    }                                         

    private void TFthanhtienKeyReleased(java.awt.event.KeyEvent evt) {                                        


       
    }                                       

public float tongtien=0;
    private void TFsoluongKeyReleased(java.awt.event.KeyEvent evt) {                                      
        int soluong = Integer.parseInt(TFsoluong.getText());
        float gia = Float.parseFloat(TFgia.getText());
        float thanhtien = soluong * gia;
        TFthanhtien.setText(String.valueOf(thanhtien));
        float thanhtien2 = Float.parseFloat(TFthanhtien.getText());

        
        tongtien+=thanhtien2;
        TFtongtien.setText(String.valueOf(tongtien));
        float value = Float.parseFloat(TFtongtien.getText());
        int diemthuong = 0;
        if (value >= 50000) {
            int bonus =  (int) (value / 50000);
            diemthuong += bonus;
            TFdiemthuong.setText(String.valueOf(diemthuong));
        }
        int value2 = Integer.parseInt(TFdiemthuong.getText());
        float giamgia = 0;
        if (value2 >= 5 && value2 < 10) {
            giamgia = 25000;
        } else if (value2 >= 10 && value2 < 15) {
            giamgia = 50000;
        } else if (value2 >= 15) {
            giamgia = 100000;

        }
        TFgiamgia.setText(String.valueOf(giamgia));
        float value3 = Float.parseFloat(TFgiamgia.getText());
        float value4 = Float.parseFloat(TFtongtien.getText());
        float tong = value4 - value3;
        TFtong.setText(String.valueOf(tong));
    }                                     

    private void TFdiemthuongKeyReleased(java.awt.event.KeyEvent evt) {                                         

    }                                        

    private void TFtongtienKeyReleased(java.awt.event.KeyEvent evt) {                                       


    }                                      

    private void TFgiamgiaKeyReleased(java.awt.event.KeyEvent evt) {                                      

    }                                     

    private void TFtongKeyReleased(java.awt.event.KeyEvent evt) {                                   

    }                                  

    private void TFtongActionPerformed(java.awt.event.ActionEvent evt) {                                       

    }                                      

    private void jTable1AncestorAdded(javax.swing.event.AncestorEvent evt) {                                      

    }                                     

    private void btnxoasanphamActionPerformed(java.awt.event.ActionEvent evt) {                                              
       int Click = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa sản phẩm hay không?", "Thông Báo", 2);
        if (Click == JOptionPane.YES_OPTION) {
            ChiTietHoaDonDAL hdd = null;
           try {
               hdd = new ChiTietHoaDonDAL();
               DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
int selectedRowIndex = jTable1.getSelectedRow();
Object value = jTable1.getValueAt(selectedRowIndex, 1); // Lấy giá trị của cột được chọn
  


if (selectedRowIndex != -1) {
    model.removeRow(selectedRowIndex);
}
            hdd.xoaSanPham(value.toString(), TFmahd.getText());
           } catch (SQLException ex) {
               Logger.getLogger(HoaDon1.class.getName()).log(Level.SEVERE, null, ex);
           }
           
            this.lbstatus.setText("Xóa sản phẩm thành công!");
          
        }
    }                                             

    private void TFtongtienCaretUpdate(javax.swing.event.CaretEvent evt) {                                       
        
    }                                      

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {                                         
 try {
    File file = new File("ketqua.txt");
    FileWriter writer = new FileWriter(file, true); // thêm true để ghi tiếp dữ liệu vào cuối file
    
    String tenKhachHang = TFtenkh.getText();
    String tien = TFtong.getText();
    String Tensp = CBtenhh.getSelectedItem().toString();
    String soluong=TFsoluong.getText();
    writer.write("Hóa Đơn Bán Hàng\n"); 
    writer.write("Tên khách hàng: " + tenKhachHang + "\n");  
    writer.write("Tổng tiền: " + tien + "\n");        
    writer.write("Tên sản phẩm: " + Tensp + "\n");
    writer.write("Số lượng: " + soluong + "\n");
    writer.write("-------------------------\n"); // Tạo dấu phân cách giữa các hóa đơn
    
    writer.close();
    
    // Đọc dữ liệu từ file để hiển thị trên TextArea
    FileReader reader = new FileReader(file);
    BufferedReader bufferedReader = new BufferedReader(reader);
   
    bufferedReader.close();
    reader.close();
} catch (IOException e) {
    // Xử lý ngoại lệ
    e.printStackTrace();
}

    }                                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("FlatLaf Dark".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HoaDon1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDon1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDon1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDon1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new HoaDon1().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(HoaDon1.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JComboBox<String> CBtenhh;
    private javax.swing.JLabel LBgio;
    private javax.swing.JLabel LBmanv;
    private javax.swing.JTextField TFdiemthuong;
    private javax.swing.JTextField TFgia;
    private javax.swing.JTextField TFgiamgia;
    private javax.swing.JTextField TFmahd;
    private javax.swing.JTextField TFmahh;
    private javax.swing.JTextField TFmakh;
    private com.toedter.calendar.JDateChooser TFngay;
    private javax.swing.JTextField TFsdt;
    private javax.swing.JTextField TFsoluong;
    private javax.swing.JTextField TFtenkh;
    private javax.swing.JTextField TFthanhtien;
    private javax.swing.JTextField TFtienkhach;
    private javax.swing.JTextField TFtienthoi;
    private javax.swing.JTextField TFtong;
    private javax.swing.JTextField TFtongtien;
    private javax.swing.JButton btnluu;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthanhtoan;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnthemsanpham2;
    private javax.swing.JButton btnxoa;
    private javax.swing.JButton btnxoasanpham;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbstatus;
    // End of variables declaration                   
public void hienthihoadon(String condition) throws SQLException {
        HoaDonDAL HdDal = new HoaDonDAL();
        HoaDon hd =new HoaDon();
        ArrayList<HoaDon> arrHd = new ArrayList<HoaDon>();
        if (condition == "hien thi") {

            arrHd = HdDal.docHoaDon("dochoadon", null);
        }
        if(condition=="hienlen")
        {
            HoaDonDAL hdd=new HoaDonDAL();
         
            
            int mahd=hdd.getmahd(1);
          
            TFmahd.setText(""+mahd);
            int makh=hdd.getmakh(mahd);
            TFmakh.setText(""+makh);
       
                            
        }
    }

    public void hienthisanpham(String condition) throws SQLException {
        SanPhamDAL SpDal = new SanPhamDAL();
        HoaDonDAL HdDal = new HoaDonDAL();
        ArrayList<SanPham> arrSp = new ArrayList<SanPham>();
        ArrayList<HoaDon> arrHd = new ArrayList<HoaDon>();
        if (condition == "them") {
            arrSp = SpDal.docSanPham("docsanpham", null);
            DefaultComboBoxModel combo = new DefaultComboBoxModel();
            CBtenhh.setModel(combo);
            for (SanPham tenSp : arrSp) {
                combo.addElement(tenSp.getTenSp());

            }

        }
        
//        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
       
//
//        String tensanpham = (String) CBtenhh.getSelectedItem();
//              int masanpham = Integer.parseInt(TFmahh.getText());
//        int soluong = Integer.parseInt(TFsoluong.getText());
//        double thanhtien = Double.parseDouble(TFthanhtien.getText());
//
//        
//        Object[] row = { tensanpham, masanpham, soluong,  thanhtien };
//
//      
//        model.addRow(row);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
         if(condition=="hien thi"){
        String tensanpham = (String) CBtenhh.getSelectedItem();
        int masanpham = Integer.parseInt(TFmahh.getText());
        int soluong = Integer.parseInt(TFsoluong.getText());
        double thanhtien = Double.parseDouble(TFthanhtien.getText());

        boolean found = false;

        for (int i = 0; i < model.getRowCount(); i++) {
            String productName = (String) model.getValueAt(i, 0);
            if (productName.equals(tensanpham)) {
                int oldQuantity = (int) model.getValueAt(i, 2);
                model.setValueAt(oldQuantity + soluong, i, 2);
                found = true;
                break;
            }
        }

        if (!found) {
            Object[] row = {tensanpham, masanpham, soluong, thanhtien};
            model.addRow(row);
}
                }
   

            }
}

 
   


