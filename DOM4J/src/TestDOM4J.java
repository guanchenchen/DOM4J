import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class TestDOM4J {
	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
		String ids[] = { "001", "002", "003" };
		String names[] = { "С��", "С��", "С��" };
		String ages[] = { "23", "21", "19" };
		String teles[] = { "111", "101", "201" };
		String sexs[] = { "��", "Ů", "��" };
		// 1.����xml�ļ�
		Document doc = DocumentHelper.createDocument();
		// 2.��Ӹ��ڵ�
		Element ele = doc.addElement("contract");

		for (int i = 0; i < ids.length; i++) {
			// 3.����ӽڵ�
			Element memberElement = ele.addElement("member");
			memberElement.addAttribute("ids", ids[i]);
			// 4.���Ԫ��
			Element nameElement = memberElement.addElement("name");
			nameElement.setText(names[i]);

			Element ageElement = memberElement.addElement("age");
			ageElement.setText(ages[i]);

			Element telElement = memberElement.addElement("tel");
			telElement.setText(teles[i]);

			Element sexElement = memberElement.addElement("sex");
			sexElement.setText(sexs[i]);

		}
		OutputFormat format = OutputFormat.createCompactFormat();
		format.setEncoding("UTF-8");
		File file = new File("D:" + File.separator + "contract1.xml");
		XMLWriter out = new XMLWriter(new FileOutputStream(file), format);
		try {
			out.write(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
