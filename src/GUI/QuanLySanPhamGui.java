package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JToolBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import BLL.LoaiHang;
import BLL.NhaCungCap;
import BLL.SanPham;
import DAL.LoaiHangDAL;
import DAL.NhaCungCapDAL;
import DAL.SanPhamDAL;

import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class QuanLySanPhamGui extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldImg;
	private JTextField textFieldMasp;
	private JTextField textFieldTensp;
	private JTextField textFieldGianhap;
	private JTextField textFieldHansd;
	private JTextField textFieldGiaban;
	private JTable table;
	private JTextField textFieldDonvi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLySanPhamGui frame = new QuanLySanPhamGui();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	JComboBox comboBox;
	JLabel lbThemanh = new JLabel();
	File selectedFile;
	ImageIcon icon = new ImageIcon();
	JButton btnCapNhatAnh = new JButton();
	Object lastValueMaSp;
	JButton btnXoa = new JButton("Xóa");
	JButton btnSua = new JButton("Sửa");
	boolean isNumber = true;
	JButton btnThem = new JButton("Thêm");
	JButton btnLuu = new JButton("Lưu");
	int lastRow=0;
	JRadioButton radio1 = new JRadioButton("Tên sản phẩm");
	JLabel lblNewLabel = new JLabel("QUẢN LÝ SẢN PHẨM");
	JRadioButton radio2 = new JRadioButton("Giá sản phẩm");
	ButtonGroup btg = new ButtonGroup();
	JScrollPane scrollPane = new JScrollPane();
	boolean addbtn, fixbtn = false;
//	dung grap 2d tao size cho anh
	int newWidth = 130;
	int newHeight = 110;
	BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
	Graphics2D g = resizedImage.createGraphics();
	String patternNumber = "\\d+(\\.\\d+)?";
	JLabel lbThongbao = new JLabel();
	String oldMaSP = null;
	String valueFind = null;
	private JTextField textFieldSearch;
	boolean checkFix = false;
	private JTextField textMaSP1;
	private JTextField textTenSp;
	
//	Tabbed 2
	JButton btnThem_1 = new JButton("Thêm");
	JButton btnSua_1 = new JButton("Sửa");
	JButton btnXoa_1 = new JButton("Xóa");
	JButton btnLuu_1 = new JButton("Lưu");
	JTable table_1 = new JTable();
	Object lastValueMaLh;
	String oldTenMaLH = null;
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JTextField textNhaCC;
	private JTextField textTenNcc;
	private JTextField textDiaChiCC;
	private JTextField textSDTNCC;
	
	
//	Tabbed 3
	JTable table_2 = new JTable();
	JButton btnThem_2 = new JButton("Thêm");
	JButton btnSua_2 = new JButton("Sửa");
	JButton btnXoa_2 = new JButton("Xóa");
	JButton btnLuu_2 = new JButton("Lưu");
	String oldTenNcc = null;
	public void resetValueTabbed3() {
		textNhaCC.setText("");
		textDiaChiCC.setText("");
		textSDTNCC.setText("");
		textTenNcc.setText("");
		textDiaChiCC.setEnabled(false);
		textSDTNCC.setEnabled(false);
		textTenNcc.setEnabled(false);
		btnSua_2.setEnabled(false);
		btnXoa_2.setEnabled(false);
		btnLuu_2.setEnabled(false);
	}
    public static boolean validatePhone(String phoneNumber) {
        String regex = "^0[0-9]{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

	public void hienthisanpham(String condition) throws SQLException {
		
		SanPhamDAL spDal = new SanPhamDAL();
		ArrayList<SanPham> arrSp = new ArrayList<SanPham>();
		if (condition == "hien thi") {

			arrSp = spDal.docSanPham("docsanpham", null);
		}
		if (condition == "sapxeptheoten") {
			arrSp = spDal.docSanPham("sapxeptheoten", null);
		}
		if (condition == "sapxeptheogia") {
			arrSp = spDal.docSanPham("sapxeptheogia", null);
		}
		if (condition == "timkiem") {
			arrSp = spDal.docSanPham("timkiem", textFieldSearch.getText());
			if (arrSp.isEmpty()) {
				JOptionPane.showMessageDialog(contentPane, "Khong co san pham");
				textFieldSearch.requestFocus();
				return;
			}
		}
		if (condition == "them") {

			arrSp = spDal.docSanPham("docsanpham", null);
			LoaiHangDAL test = new LoaiHangDAL();
			ArrayList<LoaiHang> arrMaLH = test.docLoaiHang();
			DefaultComboBoxModel combo = new DefaultComboBoxModel();
			comboBox.setModel(combo);
			for (LoaiHang malh : arrMaLH) {
				combo.addElement(malh.getTenLH());

			}
		}

		String[] columnNames = { "MaLH", "MaSP", "TenSP", "DonVi", "HSD", "GiaMua", "GiaBan", "Image" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);

		table.setModel(model);
		model.setRowCount(0);
		for (SanPham spdata : arrSp) {
			NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
			String formatGiaBan = numberFormat.format(spdata.getGiaBan());
			String formatGiaMua = numberFormat.format(spdata.getGiaMua());
			Object[] row = new Object[] { spdata.getMaLh(), spdata.getMaSp(), spdata.getTenSp(), spdata.getDonVi(),
					spdata.getHanSuDung(), formatGiaMua, formatGiaBan, spdata.getImg() };

			model.addRow(row);
		}
		JTableHeader header = table.getTableHeader();
		Font headerFont = header.getFont(); // get the current font of the header
		header.setFont(new Font(headerFont.getName(), Font.BOLD, 14)); // set the new font for the header with size 16
		lastRow = table.getRowCount() - 1; // get index of the last row
		lastValueMaSp = table.getValueAt(lastRow, 1); // get the value at the last row and column n
	}

	public void hienThiMaSanPham() throws SQLException {
		LoaiHangDAL lhDal = new LoaiHangDAL();
		ArrayList<LoaiHang> arrLH = new ArrayList<LoaiHang>();
		String[] columnNames = { "Mã Loại Hàng","Tên Loại Hàng" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		table_1.setModel(model);
		model.setRowCount(0);
		arrLH = lhDal.docLoaiHang();
		for (LoaiHang spdata : arrLH) {
			Object[] row = new Object[] { spdata.getMaLH(),spdata.getTenLH()};
			model.addRow(row);
		}
		JTableHeader header = table_1.getTableHeader();
		Font headerFont = header.getFont(); // get the current font of the header
		header.setFont(new Font(headerFont.getName(), Font.BOLD, 14)); // set the new font for the header with size 16
//		lastRow = table_1.getRowCount() - 1; // get index of the last row
//		lastValueMaLh = table_1.getValueAt(lastRow, 0); // get the value at the last row and column n
		
	}
	public void hienThiNhaCungCap() throws SQLException {
		ArrayList<NhaCungCap> arrNCC = new ArrayList<NhaCungCap>();
		NhaCungCapDAL ncc = new NhaCungCapDAL();
		arrNCC = ncc.docNhaCungCap();
		String[] columnNames = { "Mã Nhà Cung Cấp","Tên Nhà Cung Cấp","Địa Chỉ","Số Điện Thoại" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		table_2.setModel(model);
		model.setRowCount(0);
		for (NhaCungCap nccData : arrNCC) {
			
			Object[] row = new Object[] { nccData.getMaNCC(),nccData.getTenNCC(),nccData.getDiaChi(),nccData.getSoDT()};
			model.addRow(row);
		}
		JTableHeader header = table_2.getTableHeader();
		Font headerFont = header.getFont(); // get the current font of the header
		header.setFont(new Font(headerFont.getName(), Font.BOLD, 14)); // set the new font for the header with size 16
		lastRow = table_2.getRowCount() - 1; // get index of the last row
		lastValueMaLh = table_2.getValueAt(lastRow, 0); // get the value at the last row and column n
		
	}
	
	public void resetValue() {
		textFieldImg.setText("");
		textFieldImg.setEnabled(true);
		textFieldMasp.setText("");
		textFieldMasp.setEnabled(true);
		textFieldDonvi.setText("");
		textFieldDonvi.setEnabled(true);
		textFieldGiaban.setText("");
		textFieldGiaban.setEnabled(true);
		textFieldGianhap.setText("");
		textFieldGianhap.setEnabled(true);
		textFieldHansd.setText("");
		textFieldHansd.setEnabled(true);
		textFieldTensp.setText("");
		textFieldTensp.setEnabled(true);
		comboBox.setEnabled(true);
		lbThemanh.setIcon(null);
		btnCapNhatAnh.setEnabled(true);
		btnThem.setEnabled(true);
		btnXoa.setEnabled(false);
		btnSua.setEnabled(false);
		btnLuu.setEnabled(false);
		comboBox.setSelectedItem(null);
	
//		Tabbed 2
		textMaSP1.setText("");
		textMaSP1.setEnabled(true);
		textTenSp.setText("");
		textTenSp.setEnabled(true);
		btnXoa_1.setEnabled(false);
		btnSua_1.setEnabled(false);
		btnLuu_1.setEnabled(false);

	}

	public void unSetEnable() {
		textFieldImg.setEnabled(true);
		textFieldMasp.setEnabled(true);
		textFieldDonvi.setEnabled(true);
		textFieldGiaban.setEnabled(true);
		textFieldGianhap.setEnabled(true);
		textFieldHansd.setEnabled(true);
		textFieldTensp.setEnabled(true);

		comboBox.setEnabled(true);
		btnCapNhatAnh.setEnabled(true);
		btnThem.setEnabled(true);
		btnXoa.setEnabled(false);
		btnSua.setEnabled(false);
		btnLuu.setEnabled(false);
//		Tabbed 2
		btnThem_1.setEnabled(true);
		btnXoa_1.setEnabled(false);
		btnSua_1.setEnabled(false);
		btnLuu_1.setEnabled(false);
		textMaSP1.setEnabled(true);
		textTenSp.setEnabled(true);

		
	}

	public void setEnable() {

		textFieldImg.setEnabled(false);

		textFieldMasp.setEnabled(false);

		textFieldDonvi.setEnabled(false);
		textFieldGiaban.setEnabled(false);
		textFieldGianhap.setEnabled(false);
		textFieldHansd.setEnabled(false);
		textFieldTensp.setEnabled(false);
		comboBox.setEnabled(false);
		btnCapNhatAnh.setEnabled(false);
//		Tabbed 2
		
		textMaSP1.setEnabled(false);
		textTenSp.setEnabled(false);
	}

	public Boolean checkEmtyValue() throws SQLException {
		// regular expression pattern
		if (textFieldMasp.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Mã sản phẩm trống!");
			textFieldMasp.requestFocus();
			return false;
		}
		if (!textFieldMasp.getText().isEmpty()) {
			SanPhamDAL spd = new SanPhamDAL();
			ArrayList<SanPham> arrPro = new ArrayList<SanPham>();
			arrPro = spd.docSanPham("docsanpham", null);
			if (fixbtn) {
				for (SanPham sp : arrPro) {
					if (Integer.parseInt(oldMaSP) != Integer.parseInt(textFieldMasp.getText())
							&& sp.getMaSp() == Integer.parseInt(textFieldMasp.getText())) {
						JOptionPane.showMessageDialog(contentPane, "Mã sản phẩm đã tồn tại!");
						textFieldMasp.requestFocus();
						return false;

					}
				}
			}
			if (addbtn) {
				for (SanPham sp : arrPro) {
					if (sp.getMaSp() == Integer.parseInt(textFieldMasp.getText())) {
						JOptionPane.showMessageDialog(contentPane, "Mã sản phẩm đã tồn tại!");
						textFieldMasp.requestFocus();
						return false;

					}
				}
			}

		}
		if (selectedFile == null && textFieldImg.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Chưa chọn ảnh cho sản phẩm");
			return false;
		}
		if (textFieldGiaban.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Giá bán rỗng");
			textFieldGiaban.requestFocus();
			return false;

		}
		if (!textFieldGiaban.getText().isEmpty()) {
			  String input = textFieldGiaban.getText().replaceAll(",", "");
			isNumber = input.matches(patternNumber);
			if (!isNumber) {
				JOptionPane.showMessageDialog(contentPane, "Giá trị phải là số");
				textFieldGiaban.requestFocus();
				textFieldGiaban.selectAll();
				return false;
			}
		}
		if (textFieldGianhap.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Giá nhập rỗng");
			textFieldGianhap.requestFocus();
			return false;

		}
		if (!textFieldGianhap.getText().isEmpty()) {
			  String input = textFieldGianhap.getText().replaceAll(",", "");
			isNumber = input.matches(patternNumber);
			if (!isNumber) {
				JOptionPane.showMessageDialog(contentPane, "Giá trị phải là số");
				textFieldGianhap.requestFocus();
				textFieldGianhap.selectAll();
				return false;
			}

		}
		
		if (textFieldDonvi.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Đơn vị rỗng");
			textFieldDonvi.requestFocus();
			return false;
		}
		if (textFieldTensp.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Tên sản phẩm rỗng");
			textFieldTensp.requestFocus();
			return false;
		}
		if (textFieldHansd.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Hạn sử dụng rỗng");
			textFieldHansd.requestFocus();
			return false;
		}
	
		if(Integer.parseInt(textFieldGianhap.getText().replaceAll(",", ""))> Integer.parseInt(textFieldGiaban.getText().replaceAll(",", ""))) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Giá bán nhỏ hơn giá nhập,Bạn muốn tiếp tục", "Confirmation", JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION) {
			    return true;
			} else if (dialogResult == JOptionPane.NO_OPTION) {
			   return false;
			} else if (dialogResult == JOptionPane.CANCEL_OPTION) {
			   return false;
			}
		}
		return true;
	}
//Tabbed 2
	public Boolean checkEmtyValueTabbed2() throws SQLException{
		if(textTenSp.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Tên Mã Loại Hàng Trống!");
			textTenSp.requestFocus();
			return false;
			
		}
		if(!textTenSp.getText().isEmpty()) {
			LoaiHangDAL LHD = new LoaiHangDAL();
			ArrayList<LoaiHang> arrLh = new ArrayList<LoaiHang>();
			arrLh = LHD.docLoaiHang();
			
			for(LoaiHang lh :arrLh) {
			
					if(fixbtn&& oldTenMaLH.equals(textTenSp.getText())==false &&lh.getTenLH().equals(textTenSp.getText())) {
						JOptionPane.showMessageDialog(contentPane, "Tên Loại Hàng Đã Tồn Tại!");
						textTenSp.requestFocus();
						textTenSp.selectAll();
						
						return false;
					}
				
				if(addbtn&& lh.getTenLH().equals(textTenSp.getText())) {
					JOptionPane.showMessageDialog(contentPane, "Tên Loại Hàng Đã Tồn Tại!");
					textTenSp.requestFocus();
					textTenSp.selectAll();
					
					return false;
				}
			}
			
		}
		return true;
	}
	//Tabbed 3
		public Boolean checkEmtyValueTabbed3() throws SQLException{
			if(textTenNcc.getText().isEmpty()) {
				JOptionPane.showMessageDialog(contentPane, "Tên Nhà Cung Cấp Trống!");
				textTenNcc.requestFocus();
				return false;
				
			}
			if(!textTenNcc.getText().isEmpty()) {
				NhaCungCapDAL NCCD = new NhaCungCapDAL();
				ArrayList<NhaCungCap> arrNcc = new ArrayList<NhaCungCap>();
				arrNcc = NCCD.docNhaCungCap();
				
				for(NhaCungCap lh :arrNcc) {
				
						if(fixbtn&& oldTenNcc.equals(textTenNcc.getText())==false &&lh.getTenNCC().equals(textTenNcc.getText())) {
							JOptionPane.showMessageDialog(contentPane, "Tên Nhà Cung Cấp Đã Tồn Tại!");
							textTenNcc.requestFocus();
							textTenNcc.selectAll();
							
							return false;
						}
					
					if(addbtn&& lh.getTenNCC().equals(textTenNcc.getText())) {
						JOptionPane.showMessageDialog(contentPane, "Tên Nhà Cung Cấp Đã Tồn Tại!");
						textTenNcc.requestFocus();
						textTenNcc.selectAll();
						
						return false;
					}
				}
				
			}
		if(textDiaChiCC.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Địa Chỉ Nhà Cung Cấp Trống!");
			textDiaChiCC.requestFocus();
			return false;
		}
		if(textSDTNCC.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Số Điện Thoại Nhà Cung Cấp Trống!");
			textSDTNCC.requestFocus();
			return false;
		}
		if(!textSDTNCC.getText().isEmpty()) {
			if(!validatePhone(textSDTNCC.getText())) {
				JOptionPane.showMessageDialog(contentPane, "Số Điện Thoại Không hợp lệ,gồm 10 số và bắt đầu bằng 0");
				textSDTNCC.requestFocus();
				return false;
			}
			
		}
			return true;
		}
	public QuanLySanPhamGui() throws SQLException {
		
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int selectedIndex = tabbedPane.getSelectedIndex();
				if(selectedIndex==0) {
						
						lblNewLabel.setText("QUẢN LÝ SẢN PHẨM");
					
				}
		       if(selectedIndex==1) {
		    	   try {
					hienThiMaSanPham();
					lblNewLabel.setText("QUẢN LÝ LOẠI HÀNG");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	  
		       }
		       if(selectedIndex==2) {
		    	   try {
					hienThiNhaCungCap();
					lblNewLabel.setText("QUẢN LÝ NHÀ CUNG CẤP");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		       }
		        
		        
				
			}
		});
		setTitle("Quản lý sản phẩm");
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener((WindowListener) new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		        int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn muốn thoát chương trình?", "Confirmation", JOptionPane.YES_NO_OPTION);
		        if (dialogResult == JOptionPane.YES_OPTION) {
		            System.exit(0);
		        }
		    }
		});
		setBounds(100, 100, 1106, 651);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		// Get an array of all buttons in the application

		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 1088, 104);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(1093, 118, 0, 522);

		
		tabbedPane.setBounds(5, 86, 1082, 528);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Thông tin sản phẩm", null, panel_2, null);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(0, 10, 1067, 119);
		btnCapNhatAnh.setBounds(159, 11, 111, 28);

		btnCapNhatAnh.setText("Cập nhật ảnh");
		btnCapNhatAnh.setEnabled(false);
		btnCapNhatAnh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "png");
				fileChooser.setFileFilter(imageFilter);
				fileChooser.setMultiSelectionEnabled(false);
				int returnVal = fileChooser.showDialog(tabbedPane, "Chọn ảnh");
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();

					ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
					Image image = icon.getImage();
					g.drawImage(image, 0, 0, newWidth, newHeight, null);
//					g.dispose();
					ImageIcon resizedIcon = new ImageIcon(resizedImage);
					lbThemanh.setIcon(resizedIcon);
					textFieldImg.setText(selectedFile.getAbsolutePath());

				}
			}
		});
		btnCapNhatAnh.setFocusPainted(false);
		btnCapNhatAnh.setHorizontalAlignment(SwingConstants.LEADING);
		btnCapNhatAnh.setFont(new Font("Arial", Font.BOLD, 12));

		textFieldImg = new JTextField();
		textFieldImg.setBounds(280, 12, 130, 27);
		textFieldImg.setEnabled(false);
		textFieldImg.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Mã sản phẩm");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_1.setBounds(420, 14, 87, 25);

		textFieldMasp = new JTextField();
		textFieldMasp.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldMasp.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMasp.setBounds(506, 10, 109, 29);
		textFieldMasp.setEnabled(false);
		textFieldMasp.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Tên sản phẩm");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_3.setBounds(625, 14, 80, 25);

		textFieldTensp = new JTextField();
		textFieldTensp.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldTensp.setBounds(710, 11, 117, 28);
		textFieldTensp.setEnabled(false);
		textFieldTensp.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Giá nhập");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_4.setBounds(635, 45, 62, 26);

		textFieldGianhap = new JTextField();
		textFieldGianhap.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldGianhap.setBounds(710, 42, 117, 29);
		textFieldGianhap.setEnabled(false);
		textFieldGianhap.setColumns(10);

		textFieldGianhap.addKeyListener(new KeyAdapter() {
			  public void keyReleased(KeyEvent e) {
			        formatInput();
			    }


		    private void formatInput() {
		        String input = textFieldGianhap.getText().replaceAll(",", "");
		        if (input.isEmpty()) {
		            return;
		        }
		        try {
		            int number = Integer.parseInt(input);
		            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
		            String formattedNumber = numberFormat.format(number);
		            textFieldGianhap.setText(formattedNumber);
		        } catch (NumberFormatException ex) {
		            // Ignore invalid input
		        }
		    }
		});
		

		JLabel lblNewLabel_6 = new JLabel("Loại sản phẩm");
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_6.setBounds(169, 45, 97, 26);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.BOLD, 14));
		comboBox.setBounds(280, 43, 130, 28);
		comboBox.setEnabled(false);

		JLabel lblNewLabel_7 = new JLabel("Đơn vị");
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_7.setBounds(420, 48, 76, 20);

		JLabel lblNewLabel_8 = new JLabel("Hạn sử dụng");
		lblNewLabel_8.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_8.setBounds(837, 14, 87, 25);

		textFieldHansd = new JTextField();
		textFieldHansd.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldHansd.setBounds(914, 12, 137, 28);
		textFieldHansd.setEnabled(false);
		textFieldHansd.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Giá bán");
		lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_9.setBounds(847, 45, 62, 26);

		textFieldGiaban = new JTextField();
		textFieldGiaban.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldGiaban.setBounds(914, 44, 138, 29);
		textFieldGiaban.setEnabled(false);
		textFieldGiaban.setColumns(10);
		textFieldGiaban.addKeyListener(new KeyAdapter() {
			  public void keyReleased(KeyEvent e) {
			        formatInput();
			    }


		    private void formatInput() {
		        String input = textFieldGiaban.getText().replaceAll(",", "");
		        if (input.isEmpty()) {
		            return;
		        }
		        try {
		            int number = Integer.parseInt(input);
		            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
		            String formattedNumber = numberFormat.format(number);
		            textFieldGiaban.setText(formattedNumber);
		        } catch (NumberFormatException ex) {
		            // Ignore invalid input
		        }
		    }
		});
		textFieldDonvi = new JTextField();
		textFieldDonvi.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldDonvi.setBounds(506, 45, 110, 28);
		textFieldDonvi.setEnabled(false);
		textFieldDonvi.setColumns(10);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(0, 139, 1067, 78);
		btnLuu.setFont(new Font("Arial", Font.BOLD, 12));
		btnLuu.setBounds(10, 10, 104, 53);

		btnLuu.setEnabled(false);
		btnLuu.setFocusPainted(false);
		btnLuu.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Save.png"))));
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkEmtyValue()) {
						// -------------------Copy img vao thu muc Image cua project
						if (selectedFile != null) {
							Path sourcePath = selectedFile.toPath();
							Path projectPath = Paths.get(System.getProperty("user.dir")); // get the path to the project
																							// directory
							Path imageDirectory = projectPath.resolve("src//GUI//Image"); // resolve the path to the
																							// Image

							try {
								Files.createDirectories(imageDirectory);
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} // create the Image directory if it does not exist
							Path destinationPath = imageDirectory.resolve(selectedFile.getName());

							try {
								Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

						SanPham sp = new SanPham();
						SanPhamDAL luusp;
						if (addbtn) {
							try {
								luusp = new SanPhamDAL();
								int malh = luusp.layMaLoaiSP((String) (comboBox.getSelectedItem()));
								sp.setMaLh(malh);
								sp.setMaSp(Integer.parseInt(textFieldMasp.getText()));
								sp.setDonVi(textFieldDonvi.getText());
								String inputBan = textFieldGiaban.getText().replaceAll(",", "");
								sp.setGiaBan(Float.parseFloat(inputBan));
								String inputNhap = textFieldGianhap.getText().replaceAll(",", "");
								sp.setGiaMua(Float.parseFloat(inputNhap));
								sp.setHanSuDung(textFieldHansd.getText());
								sp.setTenSp(textFieldTensp.getText());
								sp.setImg(selectedFile.getName());
								boolean checkAddPro = luusp.themsanpham(sp, "themsanpham", null);
								if (checkAddPro) {
									JOptionPane.showMessageDialog(contentPane, "Thêm thành công");
									resetValue();
									setEnable();
									hienthisanpham("hien thi");
									addbtn = false;
								} else {
									JOptionPane.showMessageDialog(contentPane, "Thêm thất bại");
								}
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}
						if (fixbtn) {

							try {
								if (selectedFile == null) {
									File file = new File(textFieldImg.getText());
									String fileName = file.getName();

									sp.setImg(fileName);
								} else {
									sp.setImg(selectedFile.getName());
								}
								luusp = new SanPhamDAL();
								int malh = luusp.layMaLoaiSP((String) (comboBox.getSelectedItem()));
								sp.setMaLh(malh);
								sp.setMaSp(Integer.parseInt(textFieldMasp.getText()));
								sp.setDonVi(textFieldDonvi.getText());
								String inputBan = textFieldGiaban.getText().replaceAll(",", "");
								sp.setGiaBan(Float.parseFloat(inputBan));
								String inputNhap = textFieldGianhap.getText().replaceAll(",", "");
								sp.setGiaMua(Float.parseFloat(inputNhap));
								sp.setHanSuDung(textFieldHansd.getText());
								sp.setTenSp(textFieldTensp.getText());

								boolean checkAddPro = luusp.themsanpham(sp, "suasanpham", oldMaSP);
								if (checkAddPro) {
									JOptionPane.showMessageDialog(contentPane, "Sửa thành công");
									resetValue();
									setEnable();
									hienthisanpham("hien thi");
									fixbtn = false;
								} else {
									JOptionPane.showMessageDialog(contentPane, "Sửa thất bại");
								}
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}

					}
				} catch (NumberFormatException | HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThem.setFont(new Font("Arial", Font.BOLD, 12));
		btnThem.setBounds(146, 10, 104, 53);
		btnThem.setFocusPainted(false);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addbtn = true;
				resetValue();
				textFieldMasp.setEnabled(false);
				int masp = (int) lastValueMaSp;
				masp++;
				textFieldMasp.setText("" + masp);
				btnThem.setEnabled(false);
				btnLuu.setEnabled(true);
				btnXoa.setEnabled(false);
				btnSua.setEnabled(false);
				try {
					hienthisanpham("them");
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}

			}
		});
		btnThem.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Add.png"))));
		btnSua.setFont(new Font("Arial", Font.BOLD, 12));
		btnSua.setBounds(283, 10, 104, 53);

		btnSua.setEnabled(false);
		btnSua.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Change.png"))));
		btnSua.setFocusPainted(false);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fixbtn = true;
				addbtn = false;
				oldMaSP = textFieldMasp.getText();
				unSetEnable();
				btnThem.setEnabled(false);
				btnLuu.setEnabled(true);

				try {
					hienthisanpham("them");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnXoa.setFont(new Font("Arial", Font.BOLD, 12));
		btnXoa.setBounds(419, 10, 104, 53);

		btnXoa.setEnabled(false);
		btnXoa.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Delete.png"))));
		btnXoa.setFocusPainted(false);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa sản phẩm này", "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (confirmed == JOptionPane.YES_OPTION) {
					SanPhamDAL deleteSp;
					try {
						deleteSp = new SanPhamDAL();
						if (deleteSp.xoaSanPham(textFieldMasp.getText())) {
							JOptionPane.showMessageDialog(contentPane, "Xóa sản phẩm thành công!");
							hienthisanpham("hien thi");
							resetValue();
							setEnable();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});

		JPanel panel_7 = new JPanel();
		panel_7.setBounds(697, 10, 370, 53);
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));

		JButton btnDongBo = new JButton("");
		btnDongBo.setBounds(563, 10, 104, 53);
		btnDongBo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.createImage(QuanLySanPhamGui.class.getResource(".\\Image\\Refresh-icon.png"))));
		btnDongBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hienthisanpham("hien thi");
					resetValue();
					setEnable();
					btg.clearSelection();
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDongBo.setFocusPainted(false);

		JButton btnSapxep = new JButton("Sắp xếp");
		btnSapxep.setFont(new Font("Arial", Font.BOLD, 12));
		btnSapxep.setFocusPainted(false);
		btnSapxep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SanPhamDAL spd;
				if (radio1.isSelected()) {

					try {
						spd = new SanPhamDAL();

						hienthisanpham("sapxeptheoten");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				if (radio2.isSelected()) {

					try {
						spd = new SanPhamDAL();

						hienthisanpham("sapxeptheogia");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});

		btg.add(radio1);
		btg.add(radio2);
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup().addContainerGap()
						.addComponent(btnSapxep, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(radio1, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(radio2, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(15, Short.MAX_VALUE)));
		radio1.setFont(new Font("Arial", Font.BOLD, 12));
		radio2.setFont(new Font("Arial", Font.BOLD, 12));
		gl_panel_7.setVerticalGroup(gl_panel_7.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel_7.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSapxep, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
								.addComponent(radio1).addComponent(radio2))
						.addContainerGap()));
		panel_7.setLayout(gl_panel_7);

		JPanel panel_8 = new JPanel();
		panel_8.setBounds(0, 223, 1067, 257);
		lbThongbao.setBounds(227, 14, 569, 32);

		lbThongbao.setText("SẢN PHẨM SIÊU THỊ");

		lbThongbao.setForeground(new Color(255, 128, 128));
		lbThongbao.setHorizontalAlignment(SwingConstants.CENTER);
		lbThongbao.setFont(new Font("Arial", Font.BOLD, 20));

		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setDefaultEditor(Object.class, null);
		JTableHeader header = table.getTableHeader();
		header.setPreferredSize(new Dimension(header.getWidth(), 30));

		table.setRowHeight(30);
		table.setFocusable(false);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				int row = table.getSelectedRow(); // get the selected row
				String maLh = table.getModel().getValueAt(row, 0).toString();
				String maSP = table.getModel().getValueAt(row, 1).toString(); // get the value of the first column
				String tenSP = table.getModel().getValueAt(row, 2).toString(); // get the value of the second column
				String donVi = table.getModel().getValueAt(row, 3).toString();
				String hSD = table.getModel().getValueAt(row, 4).toString();
				String giaMua = table.getModel().getValueAt(row, 5).toString();
				String giaBan = table.getModel().getValueAt(row, 6).toString();
//				String img = table.getModel().getValueAt(row, 7).toString();
				textFieldMasp.setText(maSP);
				textFieldTensp.setText(tenSP);
				textFieldHansd.setText(hSD);
				textFieldGianhap.setText(giaMua);
				textFieldGiaban.setText(giaBan);
				textFieldDonvi.setText(donVi);

				setEnable();
				btnXoa.setEnabled(true);
				btnSua.setEnabled(true);
				btnThem.setEnabled(true);
				btnLuu.setEnabled(false);

				String img = null;
				Image image = null;
				Object value = table.getModel().getValueAt(row, 7);
				if (value != null) {
					img = value.toString();
				}
				if (img == null || img.isEmpty()) {
					// If the image path is null or empty, use a default image instead

					icon = new ImageIcon(Toolkit.getDefaultToolkit()
							.createImage(LoginGui.class.getResource(".//Image//default.png")));
					image = icon.getImage();
					textFieldImg.setText("");
					Image resizedImg = image.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
					ImageIcon resizedIcon = new ImageIcon(resizedImg);
					lbThemanh.setIcon(resizedIcon);

				} else {
					try {

						icon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(img)));
						image = icon.getImage();
						textFieldImg.setText(img);
						g.drawImage(image, 0, 0, newWidth, newHeight, null);
						ImageIcon resizedIcon = new ImageIcon(resizedImage);
						lbThemanh.setIcon(resizedIcon);

					} catch (Exception e1) {
						e1.printStackTrace();

					}
				}

//				g.dispose();

				LoaiHangDAL test;
				try {
					test = new LoaiHangDAL();
					ArrayList<LoaiHang> arrMaLH = test.docLoaiHangMaLH(Integer.parseInt(maLh));
					DefaultComboBoxModel combo = new DefaultComboBoxModel();
					comboBox.setModel(combo);

					combo.addElement(arrMaLH.get(0).getTenLH());

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});
		scrollPane.setBounds(0, 52, 1067, 206);

		scrollPane.setViewportView(table);
		panel_8.setLayout(null);
		panel_8.add(lbThongbao);
		panel_8.add(scrollPane);
		panel_6.setLayout(null);
		panel_6.add(btnLuu);
		panel_6.add(btnThem);
		panel_6.add(btnSua);
		panel_6.add(btnXoa);
		panel_6.add(btnDongBo);
		panel_6.add(panel_7);
		icon = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource("/GUI/Image/Background.png")));
		Image image_bg = icon.getImage();
		Image resizedImg_bg = image_bg.getScaledInstance(1300, 130, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon_bg = new ImageIcon(resizedImg_bg);
		panel_2.setLayout(null);
		panel_5.setLayout(null);
		lbThemanh.setBounds(10, 0, 139, 123);
		panel_5.add(lbThemanh);
		panel_5.add(btnCapNhatAnh);
		panel_5.add(lblNewLabel_6);
		panel_5.add(textFieldImg);
		panel_5.add(comboBox);
		panel_5.add(lblNewLabel_1);
		panel_5.add(textFieldMasp);
		panel_5.add(lblNewLabel_7);
		panel_5.add(textFieldDonvi);
		panel_5.add(lblNewLabel_3);
		panel_5.add(lblNewLabel_4);
		panel_5.add(textFieldTensp);
		panel_5.add(textFieldGianhap);
		panel_5.add(lblNewLabel_8);
		panel_5.add(lblNewLabel_9);
		panel_5.add(textFieldHansd);
		panel_5.add(textFieldGiaban);
		panel_2.add(panel_5);

		textFieldSearch = new JTextField();
		textFieldSearch.setBounds(336, 83, 341, 28);
		panel_5.add(textFieldSearch);
		textFieldSearch.setColumns(10);

		JButton btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 12));
		btnTimKiem.setFocusPainted(false);
		btnTimKiem.setHorizontalAlignment(SwingConstants.LEADING);
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldSearch.getText().isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Chưa nhập nội dung tìm kiếm!");
					textFieldSearch.requestFocus();
				} else {

					try {
						hienthisanpham("timkiem");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

		});
		
		ImageIcon iconSearch = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Find.png")));
		Image imageSearch = iconSearch.getImage();
		Image resizedImgSearch = imageSearch.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedIconSearch = new ImageIcon(resizedImgSearch);
		btnTimKiem.setIcon(resizedIconSearch);

		btnTimKiem.setBounds(179, 75, 123, 42);
		panel_5.add(btnTimKiem);
		panel_2.add(panel_6);
		panel_2.add(panel_8);
				header.setPreferredSize(new Dimension(header.getWidth(), 30));
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Thông Tin Nhà Cung Cấp", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_11 = new JLabel("Mã Nhà Cung Cấp");
		lblNewLabel_11.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_11.setBounds(10, 10, 110, 29);
		panel_4.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Tên Nhà Cung Cấp");
		lblNewLabel_12.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_12.setBounds(214, 10, 115, 29);
		panel_4.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Địa Chỉ");
		lblNewLabel_13.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_13.setBounds(549, 10, 62, 29);
		panel_4.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Số Điện Thoại");
		lblNewLabel_14.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_14.setBounds(799, 10, 81, 29);
		panel_4.add(lblNewLabel_14);
		
		textNhaCC = new JTextField();
		textNhaCC.setHorizontalAlignment(SwingConstants.CENTER);
		textNhaCC.setFont(new Font("Arial", Font.BOLD, 14));
		textNhaCC.setEnabled(false);
		textNhaCC.setBounds(123, 10, 81, 27);
		panel_4.add(textNhaCC);
		textNhaCC.setColumns(10);
		
		textTenNcc = new JTextField();
		textTenNcc.setFont(new Font("Arial", Font.BOLD, 14));
		textTenNcc.setEnabled(false);
		textTenNcc.setBounds(339, 10, 190, 27);
		panel_4.add(textTenNcc);
		textTenNcc.setColumns(10);
		
		textDiaChiCC = new JTextField();
		textDiaChiCC.setFont(new Font("Arial", Font.BOLD, 14));
		textDiaChiCC.setEnabled(false);
		textDiaChiCC.setBounds(621, 11, 149, 27);
		panel_4.add(textDiaChiCC);
		textDiaChiCC.setColumns(10);
		
		textSDTNCC = new JTextField();
		textSDTNCC.setFont(new Font("Arial", Font.BOLD, 14));
		textSDTNCC.setEnabled(false);
		textSDTNCC.setBounds(893, 10, 174, 29);
		panel_4.add(textSDTNCC);
		textSDTNCC.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBounds(61, 133, 946, 325);
		panel_4.add(panel_10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		JTableHeader header2 = table.getTableHeader();
		header2.setPreferredSize(new Dimension(header2.getWidth(), 30));
		table_2.setDefaultEditor(Object.class, null);
		table_2.setRowHeight(30);
		table_2.setFocusable(false);
		table_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table_2.getSelectedRow(); // get the selected row
				String maNCC = table_2.getModel().getValueAt(row, 0).toString();
				String tenNCC = table_2.getModel().getValueAt(row, 1).toString(); // get the value of the first column
				String diaChi = table_2.getModel().getValueAt(row, 2).toString();
				String dienThoai = table_2.getModel().getValueAt(row, 3).toString();
				textNhaCC.setText(maNCC);
				textTenNcc.setText(tenNCC);
				textDiaChiCC.setText(diaChi);
				textSDTNCC.setText(dienThoai);
				textDiaChiCC.setEnabled(false);
				textSDTNCC.setEnabled(false);
				textTenNcc.setEnabled(false);
				btnXoa_2.setEnabled(true);
				btnSua_2.setEnabled(true);
				btnThem_2.setEnabled(true);
				btnLuu_2.setEnabled(false);
				
			}});
		
		JLabel lblNhCungCp = new JLabel();
		lblNhCungCp.setText("NHÀ CUNG CẤP");
		lblNhCungCp.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhCungCp.setForeground(new Color(255, 128, 128));
		lblNhCungCp.setFont(new Font("Arial", Font.BOLD, 20));
		GroupLayout gl_panel_10 = new GroupLayout(panel_10);
		gl_panel_10.setHorizontalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_10.createSequentialGroup()
							.addGap(163)
							.addComponent(lblNhCungCp, GroupLayout.PREFERRED_SIZE, 569, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_10.createSequentialGroup()
							.addGap(51)
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 838, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(57, Short.MAX_VALUE))
		);
		gl_panel_10.setVerticalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNhCungCp, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(43, Short.MAX_VALUE))
		);
		
		
		
		scrollPane_2.setViewportView(table_2);
		panel_10.setLayout(gl_panel_10);
		btnThem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNhaCC.setText("");
				textDiaChiCC.setText("");
				textSDTNCC.setText("");
				textTenNcc.setText("");
//				textNhaCC.setEnabled(true);
				textDiaChiCC.setEnabled(true);
				textSDTNCC.setEnabled(true);
				textTenNcc.setEnabled(true);
				btnSua_2.setEnabled(false);
				btnXoa_2.setEnabled(false);
				btnLuu_2.setEnabled(true);
				try {
					NhaCungCapDAL LHD = new NhaCungCapDAL();
					int maNcc =LHD.getLastMaNCC();
							maNcc++;
					textNhaCC.setText("" + maNcc);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				addbtn = true;
				fixbtn = false;
			}
		});
		
		
		btnThem_2.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Add.png"))));
		btnThem_2.setFocusPainted(false);
		btnThem_2.setBounds(100, 73, 136, 53);
		panel_4.add(btnThem_2);
		btnSua_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnThem_2.setEnabled(false);
				btnLuu_2.setEnabled(true);
				btnXoa_2.setEnabled(false);
				textTenNcc.setEnabled(true);
				textDiaChiCC.setEnabled(true);
				textSDTNCC.setEnabled(true);
				try {
					hienThiNhaCungCap();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				oldTenNcc = textTenNcc.getText();
				fixbtn = true;
				addbtn = false;
			
			}
			
		});
		
		
		btnSua_2.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Change.png"))));
		btnSua_2.setFocusPainted(false);
		btnSua_2.setEnabled(false);
		btnSua_2.setBounds(336, 73, 126, 53);
		panel_4.add(btnSua_2);
		
		
		btnXoa_2.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Delete.png"))));
		btnXoa_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					NhaCungCap NCC = new NhaCungCap();
				
					NCC.setMaNCC(Integer.parseInt(textNhaCC.getText()));
					NhaCungCapDAL LHD = new NhaCungCapDAL();
					int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa nhà cung cấp này", "Confirmation",
							JOptionPane.YES_NO_OPTION);
					if (confirmed == JOptionPane.YES_OPTION) {
						if(LHD.ThemNhaCungCap(NCC, "xoanhacungcap")) {
							
							JOptionPane.showMessageDialog(contentPane, "Xóa Nhà Cung Cấp Thành Công!");
						resetValueTabbed3();
							hienThiNhaCungCap();
							btnXoa_2.setEnabled(false);
							btnThem_2.setEnabled(true);
						}
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		btnXoa_2.setFocusPainted(false);
		btnXoa_2.setEnabled(false);
		btnXoa_2.setBounds(560, 73, 126, 53);
		panel_4.add(btnXoa_2);
		btnLuu_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(checkEmtyValueTabbed3()) {
						NhaCungCap NCC = new NhaCungCap();
						NCC.setMaNCC(Integer.parseInt(textNhaCC.getText()));
						NCC.setTenNCC(textTenNcc.getText());
						NCC.setDiaChi(textDiaChiCC.getText());
						NCC.setSoDT(textSDTNCC.getText());
						NhaCungCapDAL NCCD;
						if(addbtn) {
							try {
								NCCD = new NhaCungCapDAL();
								if(NCCD.ThemNhaCungCap(NCC, "themnhacungcap")) {
									JOptionPane.showMessageDialog(contentPane, "Thêm Thành công!");
									resetValueTabbed3();
									hienThiNhaCungCap();
									addbtn = false;
								}
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(contentPane, "Thêm Thất bại!");
							}
						}
						if(fixbtn) {
							try {
								NCCD = new NhaCungCapDAL();
								if(NCCD.ThemNhaCungCap(NCC, "suanhacungcap")) {
									JOptionPane.showMessageDialog(contentPane, "Sửa Thành công!");
									resetValueTabbed3();
									btnThem_2.setEnabled(true);
									hienThiNhaCungCap();
									fixbtn = false;
								}
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(contentPane, "Sửa Thất bại!");
							}
						}
						
					}
				} catch (NumberFormatException | HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnLuu_2.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Save.png"))));
		btnLuu_2.setFocusPainted(false);
		btnLuu_2.setEnabled(false);
		btnLuu_2.setBounds(776, 73, 136, 53);
		panel_4.add(btnLuu_2);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1
				.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 1082, Short.MAX_VALUE));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 522, Short.MAX_VALUE));

		panel_1.setLayout(gl_panel_1);

		
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(190, 10, 664, 63);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lbIconShop = new JLabel("");
		lbIconShop.setHorizontalAlignment(SwingConstants.RIGHT);
		lbIconShop.setBounds(908, 8, 170, 65);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		icon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//shop.png")));
		Image image = icon.getImage();
		Image resizedImg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImg);
		panel.setLayout(null);
		lbIconShop.setIcon(resizedIcon);
		panel.add(lbIconShop);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_10 = new JLabel("");
		icon = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Background2.png")));
		Image image2 = icon.getImage();
		Image resizedImg2 = image2.getScaledInstance(1300, 100, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon2 = new ImageIcon(resizedImg2);
		lblNewLabel_10.setIcon(resizedIcon2);
		lblNewLabel_10.setBounds(0, 0, 1078, 104);
		panel.add(lblNewLabel_10);
		contentPane.setLayout(null);
		contentPane.add(tabbedPane);
		
				JPanel panel_3 = new JPanel();
				tabbedPane.addTab("Thông Tin Loại Hàng", null, panel_3, null);
				panel_3.setLayout(null);
				
	
				textMaSP1 = new JTextField();
				textMaSP1.setHorizontalAlignment(SwingConstants.CENTER);
				textMaSP1.setFont(new Font("Arial", Font.BOLD, 14));
				textMaSP1.setEnabled(false);
				textMaSP1.setBounds(283, 10, 143, 30);
				panel_3.add(textMaSP1);
				textMaSP1.setColumns(10);
				
				JLabel lblNewLabel_2 = new JLabel("Mã Sản Phẩm");
				lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
				lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_2.setBounds(140, 10, 133, 30);
				panel_3.add(lblNewLabel_2);
				
				textTenSp = new JTextField();
				textTenSp.setFont(new Font("Arial", Font.BOLD, 14));
				textTenSp.setEnabled(false);
				textTenSp.setBounds(686, 10, 165, 30);
				panel_3.add(textTenSp);
				textTenSp.setColumns(10);
				
				JLabel lblNewLabel_5 = new JLabel("Tên Loại Hàng");
				lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 15));
				lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_5.setBounds(551, 10, 125, 30);
				panel_3.add(lblNewLabel_5);
				btnThem_1.setFont(new Font("Arial", Font.BOLD, 14));
				btnThem_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						resetValue();
						textTenSp.setEnabled(true);
						btnLuu_1.setEnabled(true);
						try {
							LoaiHangDAL LHD = new LoaiHangDAL();
							int maLh =LHD.getLastMaLH();
									maLh++;
							textMaSP1.setText("" + maLh);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						textMaSP1.setEnabled(false);
						addbtn = true;
						fixbtn = false;
						
					}
				});
				
				
				btnThem_1.setIcon(new ImageIcon(
						Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Add.png"))));
				
				btnThem_1.setFocusPainted(false);
				btnThem_1.setBounds(169, 75, 143, 53);
				panel_3.add(btnThem_1);
				btnSua_1.setFont(new Font("Arial", Font.BOLD, 14));
				btnSua_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnThem_1.setEnabled(false);
						btnLuu_1.setEnabled(true);
						btnXoa_1.setEnabled(false);
						textTenSp.setEnabled(true);
						try {
							hienThiMaSanPham();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						oldTenMaLH = textTenSp.getText();
						fixbtn = true;
						addbtn = false;
					
					}
				});
				
				
				btnSua_1.setIcon(new ImageIcon(
						Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Change.png"))));
				btnSua_1.setFocusPainted(false);
				btnSua_1.setEnabled(false);
				btnSua_1.setBounds(366, 75, 133, 53);
				panel_3.add(btnSua_1);
				btnXoa_1.setFont(new Font("Arial", Font.BOLD, 14));
				btnXoa_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							
							LoaiHang LH = new LoaiHang();
							LH.setMaLH(Integer.parseInt(textMaSP1.getText()));
							LoaiHangDAL LHD = new LoaiHangDAL();
							int confirmed = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa loại hàng này", "Confirmation",
									JOptionPane.YES_NO_OPTION);
							if (confirmed == JOptionPane.YES_OPTION) {
								if(LHD.ThemLoaiHang(LH, "xoaloaihang")) {
									
									JOptionPane.showMessageDialog(contentPane, "Xóa Loại Hàng Thành Công!");
									resetValue();
									hienThiMaSanPham();
									btnXoa_1.setEnabled(false);
									btnThem_1.setEnabled(true);
								}
							}
							
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
				
				
				btnXoa_1.setIcon(new ImageIcon(
						Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Delete.png"))));
				btnXoa_1.setFocusPainted(false);
				btnXoa_1.setEnabled(false);
				btnXoa_1.setBounds(572, 75, 133, 53);
				panel_3.add(btnXoa_1);
				btnLuu_1.setFont(new Font("Arial", Font.BOLD, 14));
				btnLuu_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(checkEmtyValueTabbed2()) {
								LoaiHang LH = new LoaiHang();
								LH.setMaLH(Integer.parseInt(textMaSP1.getText()));
								LH.setTenLH(textTenSp.getText());
								LoaiHangDAL LHD;
								if(addbtn) {
									try {
										LHD = new LoaiHangDAL();
										if(LHD.ThemLoaiHang(LH, "themloaihang")) {
											JOptionPane.showMessageDialog(contentPane, "Thêm Thành công!");
											resetValue();
											hienThiMaSanPham();
											addbtn = false;
										}
									} catch (SQLException e1) {
										JOptionPane.showMessageDialog(contentPane, "Thêm Thất bại!");
									}
								}
								if(fixbtn) {
									try {
										LHD = new LoaiHangDAL();
										if(LHD.ThemLoaiHang(LH, "sualoaihang")) {
											JOptionPane.showMessageDialog(contentPane, "Sửa Thành công!");
											resetValue();
											btnThem_1.setEnabled(true);
											hienThiMaSanPham();
											fixbtn = false;
										}
									} catch (SQLException e1) {
										JOptionPane.showMessageDialog(contentPane, "Sửa Thất bại!");
									}
								}
								
							}
						} catch (NumberFormatException | HeadlessException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				
				
				btnLuu_1.setIcon(new ImageIcon(
						Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Save.png"))));
				btnLuu_1.setFocusPainted(false);
				btnLuu_1.setEnabled(false);
				btnLuu_1.setBounds(793, 75, 133, 53);
				panel_3.add(btnLuu_1);
				
				JPanel panel_9 = new JPanel();
				panel_9.setBounds(0, 138, 1077, 314);
				panel_3.add(panel_9);
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(133, 63, 792, 177);
				
				JLabel lblLoiHngSiu = new JLabel();
				lblLoiHngSiu.setBounds(209, 10, 604, 47);
				lblLoiHngSiu.setText("LOẠI HÀNG SIÊU THỊ");
				lblLoiHngSiu.setHorizontalAlignment(SwingConstants.CENTER);
				lblLoiHngSiu.setForeground(new Color(255, 128, 128));
				lblLoiHngSiu.setFont(new Font("Arial", Font.BOLD, 20));
				table_1.setDefaultEditor(Object.class, null);
				JTableHeader header1 = table_1.getTableHeader();
				scrollPane_1.setViewportView(table_1);
				table_1.setRowHeight(30);
				table_1.setFocusable(false);
				panel_9.setLayout(null);
				panel_9.add(lblLoiHngSiu);
				panel_9.add(scrollPane_1);
				table_1.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						int row = table_1.getSelectedRow(); // get the selected row
						String maLh = table_1.getModel().getValueAt(row, 0).toString();
						String tenLh = table_1.getModel().getValueAt(row, 1).toString(); // get the value of the first column
						
						textMaSP1.setText(maLh);
						textTenSp.setText(tenLh);

						setEnable();
						btnXoa_1.setEnabled(true);
						btnSua_1.setEnabled(true);
						btnThem_1.setEnabled(true);
						btnLuu_1.setEnabled(false);
						
					}});
		contentPane.add(panel_1);
		contentPane.add(panel);
		hienthisanpham("hien thi");
		
		

	}
}
