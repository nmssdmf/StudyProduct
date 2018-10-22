package com.nmssdmf.knowledge.util;

import android.content.Context;

import com.nmssdmf.knowledge.R;
import com.nmssdmf.knowledge.bean.KnowledgeBean;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by ${nmssdmf} on 2018/10/22 0022.
 */

public class XmlUtil {
    private static final String GROUP = "Group";
    private static final String KNOWLEDGE = "Knowledge";
    private static final String ITEM = "Item";

    public static List<KnowledgeBean> parseXml(Context context) {
        List<KnowledgeBean> list = new ArrayList<>();
        try {
            //获取xml输入流
            InputStream inputStream = context.getResources().openRawResource(R.raw.knowledge);
            //获取工厂对象，以及通过DOM工厂对象获取DOMBuilder对象
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            //解析XML输入流，得到Document对象，表示一个XML文档
            Document document = builder.parse(inputStream);
            //获得文档中的次以及节点，persons
            Element element = document.getDocumentElement();

            // 获取Element下一级的person节点集合，以NodeList的形式存放。
            NodeList groupNodes = element.getElementsByTagName(GROUP);//.item(0).getChildNodes();
            for (int i = 0; i < groupNodes.getLength(); i++) {
                Element groupElement = (Element) groupNodes.item(i);
                KnowledgeBean groupBean = new KnowledgeBean();
                String groupTitle = groupElement.getAttribute("title");
                groupBean.setGroupTitle(groupTitle);
                List<KnowledgeBean> childList = new ArrayList<>();
                groupBean.setItems(childList);
                list.add(groupBean);
                NodeList childNodes = groupElement.getElementsByTagName(ITEM);
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Element childElement = (Element) childNodes.item(i);
                    String childTitle = childElement.getFirstChild().getNodeValue();
                    KnowledgeBean childBean = new KnowledgeBean();
                    childBean.setItemTitle(childTitle);
                    childList.add(childBean);
                }
            }
            return list;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
