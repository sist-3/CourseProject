package page;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import component.bar.CircleBar;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DropMode;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;

public class ProgressMonitoringManagementPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ProgressMonitoringManagementPage() {
		setSize(new Dimension(800, 600));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setSize(new Dimension(800, 600));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		CircleBar circleBar = new CircleBar();
		circleBar.setBorder(new EmptyBorder(5, 5, 5, 5));
		circleBar.setBackground(Color.WHITE);
		circleBar.setBounds(12, 189, 249, 237);
		circleBar.setForeground(Color.RED);
		panel.add(circleBar);
		
		CircleBar circleBar_1 = new CircleBar();
		circleBar_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		circleBar_1.setBackground(Color.WHITE);
		circleBar_1.setForeground(Color.RED);
		circleBar_1.setBounds(278, 189, 249, 237);
		panel.add(circleBar_1);
		
		CircleBar circleBar_2 = new CircleBar();
		circleBar_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		circleBar_2.setBackground(Color.WHITE);
		circleBar_2.setForeground(Color.RED);
		circleBar_2.setBounds(545, 189, 249, 237);
		panel.add(circleBar_2);
		
		JLabel lblNewLabel = new JLabel("시험 응시 현황");
		lblNewLabel.setFont(new Font("KCC-한빛", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 158, 140, 19);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("점수 분포");
		lblNewLabel_1.setFont(new Font("KCC-한빛", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(278, 158, 140, 19);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("합격 현황");
		lblNewLabel_1_1.setFont(new Font("KCC-한빛", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(545, 158, 140, 19);
		panel.add(lblNewLabel_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(12, 436, 776, 154);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"\u3141\u3134\u3141\u3134\u3147", null, null, null},
				{"\u3141\u3134\u3147", null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"\uD559\uC0DD \uC774\uB984", "\uACFC\uBAA9\uC815\uBCF4", "\uC810\uC218", "\uD569\uACA9\uC720\uBB34"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(12, 97, 89, 30);
		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(113, 97, 345, 30);
		panel.add(comboBox_1);
		
		JLabel lblNewLabel_2 = new JLabel("성취도 관리");
		lblNewLabel_2.setFont(new Font("KCC-한빛", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(12, 30, 372, 38);
		panel.add(lblNewLabel_2);
		table.getColumnModel().getColumn(1).setPreferredWidth(210);
		table.getColumnModel().getColumn(2).setPreferredWidth(66);
		table.getColumnModel().getColumn(3).setPreferredWidth(62);
	}
}
