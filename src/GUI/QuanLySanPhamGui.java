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
	int newWidth = 100;
	int newHeight = 100;
	BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
	Graphics2D g = resizedImage.createGraphics();
	String patternNumber = "\\d+(\\.\\d+)?";
	JLabel lbThongbao = new JLabel();

	public void hienthisanpham(String condition) throws SQLException {
		SanPhamDAL spDal = new SanPhamDAL();
		ArrayList<SanPham> arrSp = new ArrayList<SanPham>();
		if (condition == "hien thi") {

			arrSp = spDal.docSanPham("docsanpham");
		}
		if (condition == "sapxeptheoten") {
			arrSp = spDal.docSanPham("sapxeptheoten");
		}
		if (condition == "sapxeptheogia") {
			arrSp = spDal.docSanPham("sapxeptheogia");
		}
if (condition == "them") {
			
			arrSp = spDal.docSanPham("docsanpham");
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

	public Boolean checkEmtyValue() {
		// regular expression pattern

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

		JPanel panel_1 = new JPanel();

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Thông tin sản phẩm", null, panel_2, null);

		JPanel panel_5 = new JPanel();

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
		textFieldImg.setEnabled(false);
		textFieldImg.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Mã sản phẩm");

		textFieldMasp = new JTextField();
		textFieldMasp.setEnabled(false);
		textFieldMasp.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Tên sản phẩm");

		textFieldTensp = new JTextField();
		textFieldTensp.setEnabled(false);
		textFieldTensp.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Giá nhập");

		textFieldGianhap = new JTextField();
		textFieldGianhap.setEnabled(false);
		textFieldGianhap.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Loại sản phẩm");

		comboBox = new JComboBox();
		comboBox.setEnabled(false);

		JLabel lblNewLabel_7 = new JLabel("Đơn vị");

		JLabel lblNewLabel_8 = new JLabel("Hạn sử dụng");

		textFieldHansd = new JTextField();
		textFieldHansd.setEnabled(false);
		textFieldHansd.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Giá bán");

		textFieldGiaban = new JTextField();
		textFieldGiaban.setEnabled(false);
		textFieldGiaban.setColumns(10);

		textFieldDonvi = new JTextField();
		textFieldDonvi.setEnabled(false);
		textFieldDonvi.setColumns(10);

		JPanel panel_6 = new JPanel();

		btnLuu.setEnabled(false);
		btnLuu.setFocusPainted(false);
		btnLuu.setIcon(new ImageIcon(
		Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Save.png"))));
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					else {
						
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
							boolean checkAddPro = luusp.themsanpham(sp,"themsanpham");
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
								int fileNameStartIndex = textFieldImg.getText().lastIndexOf("\\") + 1;
								String fileName = textFieldImg.getText().substring(fileNameStartIndex);
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
							
							boolean checkAddPro = luusp.themsanpham(sp,"suasanpham");
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
			}
		});
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

		btnSua.setEnabled(false);
		btnSua.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".\\Image\\Change.png"))));
		btnSua.setFocusPainted(false);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fixbtn = true;
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
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));

		JButton btnDongBo = new JButton("");
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
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup().addContainerGap()
						.addComponent(btnLuu, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE).addGap(31)
						.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE).addGap(35)
						.addComponent(btnSua, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE).addGap(31)
						.addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE).addGap(39)
						.addComponent(btnDongBo, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
						.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 370, GroupLayout.PREFERRED_SIZE)));
		gl_panel_6.setVerticalGroup(gl_panel_6.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_6
				.createSequentialGroup()
				.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_6
						.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnSua, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panel_6.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnXoa, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
								.addComponent(btnDongBo, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnLuu, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(btnThem, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
						.addComponent(panel_7, 0, 0, Short.MAX_VALUE))
				.addContainerGap()));

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
		panel_6.setLayout(gl_panel_6);

		JPanel panel_8 = new JPanel();

		lbThongbao.setText("SẢN PHẨM SIÊU THỊ");

		lbThongbao.setForeground(new Color(255, 128, 128));
		lbThongbao.setHorizontalAlignment(SwingConstants.CENTER);
		lbThongbao.setFont(new Font("Arial", Font.BOLD, 20));
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
						.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_8.createSequentialGroup().addGap(227).addComponent(lbThongbao,
										GroupLayout.PREFERRED_SIZE, 569, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_8.createSequentialGroup().addContainerGap().addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 1057, Short.MAX_VALUE)))
						.addContainerGap()));
		gl_panel_8.setVerticalGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup().addGap(8)
						.addComponent(lbThongbao, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

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
				     Image resizedImg = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
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
//				        // If we couldn't load the image for some reason, use a default image instead
//				        ImageIcon defaultIcon = new ImageIcon(
//				        Toolkit.getDefaultToolkit().createImage(LoginGui.class.getResource(".//Image//Delete.png")));
//				        Image defaultImage = defaultIcon.getImage();
//				        g.drawImage(defaultImage, 0, 0, newWidth, newHeight, null);
//				        ImageIcon resizedIcon = new ImageIcon(resizedImage);
//						lbThemanh.setIcon(resizedIcon);
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

		scrollPane.setViewportView(table);

		panel_8.setLayout(gl_panel_8);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2
				.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 1077, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addGap(10)
						.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE).addGap(10)
						.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE).addGap(20)
						.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)));
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(gl_panel_5.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_5
				.createSequentialGroup()
				.addComponent(lbThemanh, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE).addGap(10)
				.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCapNhatAnh, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
				.addGap(10)
				.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldImg, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
				.addGap(10)
				.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_5.createSequentialGroup().addGap(86).addComponent(textFieldMasp,
								GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_5.createSequentialGroup().addGap(10)
								.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
								.addGap(13).addComponent(textFieldDonvi, GroupLayout.PREFERRED_SIZE, 110,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(10)
				.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_5.createSequentialGroup().addGap(18).addComponent(lblNewLabel_4,
								GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)))
				.addGap(5)
				.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldTensp, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldGianhap, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
				.addGap(10)
				.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
				.addGap(3)
				.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup().addGap(1).addComponent(textFieldHansd,
								GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
						.addComponent(textFieldGiaban, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))));
		gl_panel_5.setVerticalGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addComponent(lbThemanh, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_panel_5.createSequentialGroup().addGap(10).addComponent(btnCapNhatAnh).addGap(10)
						.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_5.createSequentialGroup().addGap(11)
						.addComponent(textFieldImg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(11).addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_5.createSequentialGroup().addGap(14)
						.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_1)
								.addComponent(textFieldMasp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(9)
						.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_5.createSequentialGroup().addGap(3).addComponent(lblNewLabel_7))
								.addComponent(textFieldDonvi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_panel_5.createSequentialGroup().addGap(14).addComponent(lblNewLabel_3).addGap(18)
						.addComponent(lblNewLabel_4))
				.addGroup(gl_panel_5.createSequentialGroup().addGap(11)
						.addComponent(textFieldTensp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(12).addComponent(textFieldGianhap, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_5.createSequentialGroup().addGap(14).addComponent(lblNewLabel_8).addGap(18)
						.addComponent(lblNewLabel_9))
				.addGroup(gl_panel_5.createSequentialGroup().addGap(11)
						.addComponent(textFieldHansd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(12).addComponent(textFieldGiaban, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)));
		panel_5.setLayout(gl_panel_5);
		panel_2.setLayout(gl_panel_2);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(166, 78, 138, 55);
		panel_3.add(lblNewLabel_2);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_4, null);
		panel_4.setLayout(null);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 1082, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE))
				.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(50).addComponent(panel_1,
										GroupLayout.PREFERRED_SIZE, 522, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup().addGap(18).addComponent(tabbedPane,
										GroupLayout.PREFERRED_SIZE, 522, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1
				.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 1082, Short.MAX_VALUE));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 522, Short.MAX_VALUE));

		panel_1.setLayout(gl_panel_1);

		JLabel lblNewLabel = new JLabel("QUẢN LÝ SẢN PHẨM");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap(374, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 466, GroupLayout.PREFERRED_SIZE)
						.addGap(242)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(42, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		hienthisanpham("hien thi");

	}
}
