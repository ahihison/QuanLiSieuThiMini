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
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import BLL.LoaiHang;
import BLL.SanPham;
import DAL.LoaiHangDAL;
import DAL.SanPhamDAL;

import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
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
	int lastRow;
	JRadioButton radio1 = new JRadioButton("Tên sản phẩm");

	JRadioButton radio2 = new JRadioButton("Giá sản phẩm");
	ButtonGroup btg = new ButtonGroup();
	JScrollPane scrollPane = new JScrollPane();
	boolean addbtn,fixbtn = false;
//	dung grap 2d tao size cho anh
	int newWidth = 130;
	int newHeight = 110;
	BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
	Graphics2D g = resizedImage.createGraphics();
	String patternNumber = "\\d+(\\.\\d+)?";
	JLabel lbThongbao = new JLabel();
	String oldMaSP = null;
	private JTextField textFieldSearch;
boolean checkFix = false;
	public void hienthisanpham(String condition) throws SQLException {
		SanPhamDAL spDal = new SanPhamDAL();
		ArrayList<SanPham> arrSp = new ArrayList<SanPham>();
		if (condition == "hien thi") {

			arrSp = spDal.docSanPham("docsanpham",null);
		}
		if (condition == "sapxeptheoten") {
			arrSp = spDal.docSanPham("sapxeptheoten",null);
		}
		if (condition == "sapxeptheogia") {
			arrSp = spDal.docSanPham("sapxeptheogia",null);
		}
if (condition == "them") {
			
			arrSp = spDal.docSanPham("docsanpham",null);
			LoaiHangDAL test = new LoaiHangDAL();
			ArrayList<LoaiHang> arrMaLH = test.docLoaiHang();
			DefaultComboBoxModel combo = new DefaultComboBoxModel();
			comboBox.setModel(combo);
			for (LoaiHang malh : arrMaLH) {
				combo.addElement(malh.getTenLH());

			}
		}

		String[] columnNames = { "MaLH", "MaSP", "TenSP", "DonVi", "HSD", "GiaMua", "GiaBan", "Img" };
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
		
		lastRow = table.getRowCount() - 1; // get index of the last row
		lastValueMaSp = table.getValueAt(lastRow, 1); // get the value at the last row and column n
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
	}

	public Boolean checkEmtyValue() throws SQLException {
		// regular expression pattern
		if(textFieldMasp.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Mã sản phẩm trống!");
			textFieldMasp.requestFocus();
			return false;
		}
		if(!textFieldMasp.getText().isEmpty()) {
			SanPhamDAL spd = new SanPhamDAL();
			ArrayList<SanPham> arrPro = new ArrayList<SanPham>();
			arrPro = spd.docSanPham("docsanpham",null);
			if(fixbtn) {
				for(SanPham sp:arrPro) {
					if(Integer.parseInt(oldMaSP)!=Integer.parseInt(textFieldMasp.getText()) && sp.getMaSp()== Integer.parseInt(textFieldMasp.getText())) {
						JOptionPane.showMessageDialog(contentPane, "Mã sản phẩm đã tồn tại!");
						textFieldMasp.requestFocus();
						return false;
						
					}
				}
			}
			if(addbtn) {
				for(SanPham sp:arrPro) {
					if(sp.getMaSp()== Integer.parseInt(textFieldMasp.getText())) {
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
			isNumber = textFieldGiaban.getText().matches(patternNumber);
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
			isNumber = textFieldGianhap.getText().matches(patternNumber);
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
		return true;
	}

	public QuanLySanPhamGui() throws SQLException {

		setTitle("Quản lý sản phẩm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1106, 673);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		// Get an array of all buttons in the application

		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 1088, 104);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(1093, 118, 0, 522);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 86, 1082, 522);

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
		btnCapNhatAnh.setFont(new Font("Arial", Font.PLAIN, 11));

		textFieldImg = new JTextField();
		textFieldImg.setBounds(280, 12, 130, 27);
		textFieldImg.setEnabled(false);
		textFieldImg.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Mã sản phẩm");
		lblNewLabel_1.setBounds(420, 14, 87, 25);

		textFieldMasp = new JTextField();
		textFieldMasp.setBounds(506, 10, 109, 29);
		textFieldMasp.setEnabled(false);
		textFieldMasp.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Tên sản phẩm");
		lblNewLabel_3.setBounds(625, 14, 80, 25);

		textFieldTensp = new JTextField();
		textFieldTensp.setBounds(710, 11, 117, 28);
		textFieldTensp.setEnabled(false);
		textFieldTensp.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Giá nhập");
		lblNewLabel_4.setBounds(635, 45, 62, 26);

		textFieldGianhap = new JTextField();
		textFieldGianhap.setBounds(710, 42, 117, 29);
		textFieldGianhap.setEnabled(false);
		textFieldGianhap.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Loại sản phẩm");
		lblNewLabel_6.setBounds(173, 45, 97, 26);

		comboBox = new JComboBox();
		comboBox.setBounds(280, 43, 130, 28);
		comboBox.setEnabled(false);

		JLabel lblNewLabel_7 = new JLabel("Đơn vị");
		lblNewLabel_7.setBounds(420, 48, 62, 20);

		JLabel lblNewLabel_8 = new JLabel("Hạn sử dụng");
		lblNewLabel_8.setBounds(837, 14, 87, 25);

		textFieldHansd = new JTextField();
		textFieldHansd.setBounds(914, 12, 137, 28);
		textFieldHansd.setEnabled(false);
		textFieldHansd.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Giá bán");
		lblNewLabel_9.setBounds(847, 45, 62, 26);

		textFieldGiaban = new JTextField();
		textFieldGiaban.setBounds(914, 44, 138, 29);
		textFieldGiaban.setEnabled(false);
		textFieldGiaban.setColumns(10);

		textFieldDonvi = new JTextField();
		textFieldDonvi.setBounds(506, 45, 110, 28);
		textFieldDonvi.setEnabled(false);
		textFieldDonvi.setColumns(10);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(0, 139, 1067, 78);
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
							Path imageDirectory = projectPath.resolve("src//GUI//Image"); // resolve the path to the Image

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
						if(addbtn) {
							try {
								luusp = new SanPhamDAL();
								int malh = luusp.layMaLoaiSP((String) (comboBox.getSelectedItem()));
								sp.setMaLh(malh);
								sp.setMaSp(Integer.parseInt(textFieldMasp.getText()));
								sp.setDonVi(textFieldDonvi.getText());
								sp.setGiaBan(Float.parseFloat(textFieldGiaban.getText()));
								sp.setGiaMua(Float.parseFloat(textFieldGianhap.getText()));
								sp.setHanSuDung(textFieldHansd.getText());
								sp.setTenSp(textFieldTensp.getText());
								sp.setImg(selectedFile.getName());
								boolean checkAddPro = luusp.themsanpham(sp,"themsanpham",null);
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
						if(fixbtn) {
							
							try {
								if(selectedFile==null) {
									File file = new File(textFieldImg.getText());
									String fileName = file.getName();

									sp.setImg(fileName);
								}
								else {
									sp.setImg(selectedFile.getName());
								}
								luusp = new SanPhamDAL();
								int malh = luusp.layMaLoaiSP((String) (comboBox.getSelectedItem()));
								sp.setMaLh(malh);
								sp.setMaSp(Integer.parseInt(textFieldMasp.getText()));
								sp.setDonVi(textFieldDonvi.getText());
								sp.setGiaBan(Float.parseFloat(textFieldGiaban.getText()));
								sp.setGiaMua(Float.parseFloat(textFieldGianhap.getText()));
								sp.setHanSuDung(textFieldHansd.getText());
								sp.setTenSp(textFieldTensp.getText());
								
								boolean checkAddPro = luusp.themsanpham(sp,"suasanpham",oldMaSP);
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
		btnSua.setBounds(283, 10, 104, 53);

		btnSua.setEnabled(false);
		btnSua.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Change.png"))));
		btnSua.setFocusPainted(false);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fixbtn = true;
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
		btnDongBo.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLySanPhamGui.class.getResource(".\\Image\\Refresh-icon.png"))));
		btnDongBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hienthisanpham("hien thi");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDongBo.setFocusPainted(false);

		JButton btnSapxep = new JButton("Sắp xếp");
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
		gl_panel_7.setVerticalGroup(gl_panel_7.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel_7.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSapxep, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
								.addComponent(radio1).addComponent(radio2))
						.addContainerGap()));
		panel_7.setLayout(gl_panel_7);

		JPanel panel_8 = new JPanel();
		panel_8.setBounds(0, 223, 1067, 272);
		lbThongbao.setBounds(227, 14, 569, 32);

		lbThongbao.setText("SẢN PHẨM SIÊU THỊ");

		lbThongbao.setForeground(new Color(255, 128, 128));
		lbThongbao.setHorizontalAlignment(SwingConstants.CENTER);
		lbThongbao.setFont(new Font("Arial", Font.BOLD, 20));

		table = new JTable();
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
					
				     icon = new ImageIcon(
				     Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//default.png")));
				     image = icon.getImage();
				     Image resizedImg = image.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
				     ImageIcon resizedIcon = new ImageIcon(resizedImg);
				     lbThemanh.setIcon(resizedIcon);
				   
				} else {
				    try {
				    	
				         icon = new ImageIcon(
				        Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(img)));
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
		scrollPane.setBounds(0, 52, 1067, 210);

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
		textFieldSearch.setBounds(710, 83, 341, 28);
		panel_5.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JButton btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setFocusPainted(false);
		btnTimKiem.setHorizontalAlignment(SwingConstants.LEADING);
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldSearch.getText().isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Chưa nhập nội dung tìm kiếm!");
					textFieldSearch.requestFocus();
				}
				else {
					
				}
			}
		});
		ImageIcon iconSearch = new ImageIcon(
			     Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Find.png")));
			     Image imageSearch = iconSearch.getImage();
			     Image resizedImgSearch = imageSearch.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			     ImageIcon resizedIconSearch = new ImageIcon(resizedImgSearch);
		btnTimKiem.setIcon(resizedIconSearch);
	
		btnTimKiem.setBounds(582, 83, 123, 26);
		panel_5.add(btnTimKiem);
		panel_2.add(panel_6);
		panel_2.add(panel_8);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(166, 78, 138, 55);
		panel_3.add(lblNewLabel_2);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_4, null);
		panel_4.setLayout(null);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1
				.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 1082, Short.MAX_VALUE));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 522, Short.MAX_VALUE));

		panel_1.setLayout(gl_panel_1);

		JLabel lblNewLabel = new JLabel("QUẢN LÝ SẢN PHẨM");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(198, 0, 664, 63);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lbIconShop = new JLabel("");
		lbIconShop.setBounds(10, 0, 170, 65);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		icon = new ImageIcon(
	     Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//shop.png")));
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
		contentPane.add(panel_1);
		contentPane.add(panel);
		hienthisanpham("hien thi");

	}
}
