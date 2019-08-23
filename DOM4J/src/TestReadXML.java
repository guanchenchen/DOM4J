import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TestReadXML {
	public static void main(String[] args) throws DocumentException {
		File f = new File("D:" + File.separator + "contract1.xml");
		SAXReader sax = new SAXReader();
		Document doc = sax.read(f);
		Element ele = doc.getRootElement();
		Iterator<Element> its = ele.elementIterator();
		while (its.hasNext()) {
			Element eles = its.next();
			System.out.print("ID=" + eles.attributeValue("ids"));
			System.out.print(",name=" + eles.elementText("name"));
			System.out.print(",age=" + eles.elementText("age"));
			System.out.print(",tel=" + eles.elementText("tel"));
			System.out.print(",sex=" + eles.elementText("sex"));
			System.out.println();

		}
	}
}
