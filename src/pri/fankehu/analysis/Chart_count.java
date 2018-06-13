package pri.fankehu.analysis;

import java.util.ArrayList;
import java.util.List;

import java.awt.Font;//������������ÿ��Խ��
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.general.DefaultPieDataset;

public class Chart_count {
	List<String[]> list=new ArrayList<String[]>();
	DefaultPieDataset dataset=new DefaultPieDataset();
	public void chart() {
		
		int flag=11;
		if(list.size()<flag) {
			for(String[] attribute : list) {
				dataset.setValue(attribute[0], Double.valueOf(attribute[2]));
			}
		}
		else {
			int i=0;
			boolean other=false;
			String[] attr=new String[2];
			attr[0]="�����ͻ�";
			attr[1]=String.valueOf(0);
			for(String[] attribute : list) {
				if(i<flag) {
					dataset.setValue(attribute[0], Double.valueOf(attribute[2]));
					i++;
				}
				else {
					attr[1]=String.valueOf(Double.valueOf(attr[1])+Double.valueOf(attribute[2]));
					other=true;
				}
			}
			if(other) {
				dataset.setValue(attr[0], Double.valueOf(attr[1]));
			}
		}
		
		/**
		 * ����������룬����������ʽ
		 */
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        standardChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20));
        standardChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15));
        standardChartTheme.setLargeFont(new Font("����", Font.PLAIN, 15));
        ChartFactory.setChartTheme(standardChartTheme);
		
		JFreeChart chart = ChartFactory.createPieChart("�ͻ��������ֲ�ͼ", dataset, true, false, false);
//		JPanel jPanel=new ChartPanel(chart);
		
		ChartFrame chartFrame=new ChartFrame("ͳ��", chart);
		chartFrame.pack();
		chartFrame.setVisible(true);
		
//		jPanel.setVisible(true);
		/**
		 * �������
		 */
		
	}
	public void setList(List<String[]> list ) {
		this.list=list;
	}
}
