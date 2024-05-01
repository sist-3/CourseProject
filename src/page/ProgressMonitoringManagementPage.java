package page;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import component.piechart.ModelPieChart;
import component.piechart.PieChart;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import java.awt.Font;

import component.piechart.PieChart.PeiChartType;

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

		JLabel lblNewLabel = new JLabel("시험 응시 현황");
		lblNewLabel.setFont(new Font("KCC-한빛", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 158, 140, 19);
		panel.add(lblNewLabel);
		
		PieChart pieChart1 = new PieChart();
		pieChart1.setLocation(0, 184);
		pieChart1.setSize(new Dimension(262, 242));
		pieChart1.setFont(new Font("프리젠테이션 4 Regular", Font.PLAIN, 19));
		pieChart1.setChartType(PieChart.PeiChartType.DONUT_CHART);
		pieChart1.addData(new ModelPieChart("제출", 0, makeColor()));
		pieChart1.addData(new ModelPieChart("미제출", 0, makeColor()));
		panel.add(pieChart1);
		
		JLabel lblNewLabel_1 = new JLabel("점수 분포");
		lblNewLabel_1.setFont(new Font("KCC-한빛", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(278, 158, 140, 19);
		panel.add(lblNewLabel_1);
		
		PieChart pieChart2 = new PieChart();
		pieChart2.setSize(new Dimension(262, 242));
		pieChart2.setFont(new Font("Dialog", Font.PLAIN, 19));
		pieChart2.setChartType(PeiChartType.DONUT_CHART);
		pieChart2.setBounds(263, 184, 262, 242);
		pieChart2.addData(new ModelPieChart("0 ~ 39", 0, makeColor()));
		pieChart2.addData(new ModelPieChart("40 ~ 59", 0, makeColor()));
		pieChart2.addData(new ModelPieChart("60 ~ 79", 0, makeColor()));
		pieChart2.addData(new ModelPieChart("80 ~ 100", 0, makeColor()));
		panel.add(pieChart2);
		
		JLabel lblNewLabel_1_1 = new JLabel("합격 현황");
		lblNewLabel_1_1.setFont(new Font("KCC-한빛", Font.PLAIN, 19));
		lblNewLabel_1_1.setBounds(545, 158, 140, 19);
		panel.add(lblNewLabel_1_1);
		
		PieChart pieChart3 = new PieChart();
		pieChart3.setSize(new Dimension(262, 242));
		pieChart3.setFont(new Font("Dialog", Font.PLAIN, 19));
		pieChart3.setChartType(PeiChartType.DONUT_CHART);
		pieChart3.setBounds(538, 184, 262, 242);
		pieChart3.addData(new ModelPieChart("합격", 15, new Color(23, 126, 238)));
		pieChart3.addData(new ModelPieChart("불합격", 5, new Color(221, 65, 65)));
		panel.add(pieChart3);
		
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
	
	private Color makeColor() {
		
		int r = (int)(Math.random() * 255);
		int g = (int)(Math.random() * 255);
		int b = (int)(Math.random() * 255);
		return new Color(r, g, b);
	}
}
