import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;
import javax.swing.DefaultComboBoxModel;

import java.awt.FlowLayout;



import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.tree.DefaultTreeModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;

import javax.swing.JMenuItem;


public class FileSysGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		Root root = new Root();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileSysGUI frame = new FileSysGUI(root);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FileSysGUI(Root rt) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTree userTree = new JTree();
		userTree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Root") {
				{
					for (int i = 0; i < rt.getFileNumber(); i++)
					{
						add(new DefaultMutableTreeNode(rt.getAFileName(i)));
					}
				}
			}
		));
		
		userTree.setShowsRootHandles(true);
		userTree.setBounds(10, 27, 221, 324);
		contentPane.add(userTree);
		
		
		JTree actualTree = new JTree();
		actualTree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Root") {
				{
					DefaultMutableTreeNode node_1;
					DefaultMutableTreeNode node_2;
					DefaultMutableTreeNode node_3;
					for (int i = 0; i < 3; i++){
						node_1 = new DefaultMutableTreeNode ("vnode" + i);
							node_2 = new DefaultMutableTreeNode (rt.getDriveName(i));
								for (int j = 0; j < rt.getInodeSize(i); j++){
									node_3 = new DefaultMutableTreeNode("inode" + j);
										node_3.add(new DefaultMutableTreeNode(rt.getFN(i, j)));
									node_2.add(node_3);
								}
							node_1.add(node_2);
						add(node_1);
					}
				}
			}
		));
		actualTree.setBounds(241, 27, 234, 324);
		contentPane.add(actualTree);
		
		
		
		JButton btnNewFile = new JButton("New File");
		btnNewFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTextField xField = new JTextField(8);
				JTextField yField = new JTextField(5);
				JTextField zField = new JTextField(30);
				
				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("File Name:"));
				myPanel.add(xField);
				myPanel.add(Box.createHorizontalStrut(15));
				myPanel.add(new JLabel("Extension:"));
				myPanel.add(yField);
				myPanel.add(Box.createHorizontalStrut(15));
				myPanel.add(new JLabel("First Line:"));
				myPanel.add(zField);
				
				int result = JOptionPane.showConfirmDialog(null,  myPanel, "Please provide a file name, extension and one line of code", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					rt.addNewFile(xField.getText(), yField.getText(), zField.getText());

				}
				userTree.setModel(new DefaultTreeModel(
						new DefaultMutableTreeNode("Root") {
							{
								for (int i = 0; i < rt.getFileNumber(); i++) {
									add(new DefaultMutableTreeNode(rt.getAFileName(i)));
								}
							}
						}
					));
				actualTree.setModel(new DefaultTreeModel(
						new DefaultMutableTreeNode("Root") {
							{
								DefaultMutableTreeNode node_1;
								DefaultMutableTreeNode node_2;
								DefaultMutableTreeNode node_3;
								for (int i = 0; i < 3; i++){
									node_1 = new DefaultMutableTreeNode ("vnode" + i);
										node_2 = new DefaultMutableTreeNode (rt.getDriveName(i));
											for (int j = 0; j < rt.getInodeSize(i); j++){
												node_3 = new DefaultMutableTreeNode("inode" + j);
													node_3.add(new DefaultMutableTreeNode(rt.getFN(i, j)));
												node_2.add(node_3);
											}
										node_1.add(node_2);
									add(node_1);
								}
							}
						}
					));
				contentPane.add(userTree);
				contentPane.repaint();
				contentPane.add(actualTree);
				contentPane.repaint();
			
			}

		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JPanel delPanel = new JPanel();
				delPanel.add(new JLabel("Please select a file to delete:"));
				DefaultComboBoxModel model = new DefaultComboBoxModel();
				for (int i = 0; i < rt.getFileNumber(); i++)
					model.addElement(rt.getAFileName(i));	
				JComboBox comboBox = new JComboBox(model);
				delPanel.add(comboBox);
				
				int iResult = JOptionPane.showConfirmDialog(null, delPanel, "Delete A File", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				if (iResult == JOptionPane.OK_OPTION) {
					String result = (String) comboBox.getSelectedItem();
					rt.deleteAFile(result);
				}
				userTree.setModel(new DefaultTreeModel(
						new DefaultMutableTreeNode("Root") {
							{
								for (int i = 0; i < rt.getFileNumber(); i++) {
									add(new DefaultMutableTreeNode(rt.getAFileName(i)));
								}
							}
						}
					));
				actualTree.setModel(new DefaultTreeModel(
						new DefaultMutableTreeNode("Root") {
							{
								DefaultMutableTreeNode node_1;
								DefaultMutableTreeNode node_2;
								DefaultMutableTreeNode node_3;
								for (int i = 0; i < 3; i++){
									node_1 = new DefaultMutableTreeNode ("vnode" + i);
										node_2 = new DefaultMutableTreeNode (rt.getDriveName(i));
											for (int j = 0; j < rt.getInodeSize(i); j++){
												node_3 = new DefaultMutableTreeNode("inode" + j);
													node_3.add(new DefaultMutableTreeNode(rt.getFN(i, j)));
												node_2.add(node_3);
											}
										node_1.add(node_2);
									add(node_1);
								}
							}
						}
					));
				contentPane.add(userTree);
				contentPane.repaint();
				contentPane.add(actualTree);
				contentPane.repaint();
			}

			
	
		});
		btnDelete.setBounds(485, 58, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JPanel printPanel = new JPanel();
				printPanel.add(new JLabel("Please select a file to print:"));
				DefaultComboBoxModel model = new DefaultComboBoxModel();
				for (int i = 0; i < rt.getFileNumber(); i++)
					model.addElement(rt.getAFileName(i));	
				JComboBox comboBox = new JComboBox(model);
				printPanel.add(comboBox);
				
				int iResult = JOptionPane.showConfirmDialog(null, printPanel, "Print A File", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (iResult == JOptionPane.OK_OPTION) {
					String result = (String) comboBox.getSelectedItem();
					
					//System.out.println(rt.printAFile(result));
					JPanel dispPanel = new JPanel();
					dispPanel.add(new JLabel("File contents: "));
					dispPanel.add(new JLabel(rt.printAFile(result)));
					
					JFrame frame = new JFrame("File Contents");
					JOptionPane.showMessageDialog(frame, dispPanel);
					
				}
			}
		});
		btnPrint.setBounds(485, 92, 89, 23);
		contentPane.add(btnPrint);
		
		btnNewFile.setBounds(485, 24, 89, 23);
		contentPane.add(btnNewFile);
		
		JButton btnClose = new JButton("Close");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		btnClose.setBounds(485, 126, 89, 23);
		contentPane.add(btnClose);
		
		JLabel lblUserView = new JLabel("User View");
		lblUserView.setBounds(10, 11, 83, 14);
		contentPane.add(lblUserView);
		
		JLabel lblActualStructure = new JLabel("Actual Structure");
		lblActualStructure.setBounds(241, 11, 112, 14);
		contentPane.add(lblActualStructure);
		
		
	}
	
}
