package com;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WEO on 11/20/16.
 */
public class ServiceUtils {

    public static void fileOutput(String title, Elements elements, String filename) {

        RandomAccessFile raf = null;
        File file = new File(filename);

        try {
            raf = new RandomAccessFile(filename, "rw");
            raf.skipBytes((int) raf.length());

            raf.writeBytes("\n|\n");
            raf.write(title.getBytes());
            raf.writeBytes("\n");

            for (Element element : elements) {
                System.out.println(element.toString());
                raf.write(element.toString().getBytes());
                raf.writeUTF(",");
            }
            raf.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void fileOutput(Object element, String filename) {

        File fileOut = new File(filename);

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileOut, true));
            bufferedWriter.write(element + "\n" + ",|," + "\n");
            bufferedWriter.flush();
            bufferedWriter.close();
            //   System.out.println(element);
        } catch (IOException e) {
            System.out.println("Ошибка");
        }
    }


    public static boolean md5equals(String fileName1, String fileName2) throws IOException {
        FileInputStream fis1 = new FileInputStream(new File(fileName1));
        String md51 = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis1);
        fis1.close();

        FileInputStream fis2 = new FileInputStream(new File(fileName2));
        String md52 = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis2);
        fis2.close();

        return md51.equals(md52);
    }

    public static boolean md5equals(File fileName1, File fileName2) throws IOException {
        FileInputStream fis1 = new FileInputStream(fileName1);
        String md51 = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis1);
        fis1.close();

        FileInputStream fis2 = new FileInputStream(fileName2);
        String md52 = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis2);
        fis2.close();

        return md51.equals(md52);
    }

    public static String parsingUrl(Element element, String divClassname, String urlAttrName, String oldValue, String newValue) {
        return element.select(divClassname).attr(urlAttrName).replace(oldValue, newValue);
    }

    public static String cleaningUrl(String element, String oldValue, String newValue) {
        return element.replace(oldValue, newValue);
        // return element.select(tagName).attr(urlAttrName).replace("leave?url=", "");
    }

    public static Document getDocument(String domain, String userAgent) throws IOException {
        Document doc = null;
        try {
            doc = Jsoup.connect(domain).userAgent(userAgent).timeout(10000).get();

        } catch (HttpStatusException e) {
            System.out.println("Bad Link");
        }
        return doc;
    }

    public List<String> getArrayListFromFile(String fileName) throws IOException {
        FileReader fileReaderCategories = new FileReader(fileName);
        BufferedReader bufferedReaderCategories = new BufferedReader(fileReaderCategories);

        List<String> stringList = new ArrayList<String>(100);
        String stringBuffer;
        int index = 0;
        while ((stringBuffer = bufferedReaderCategories.readLine()) != null) {
            stringList.add(index, stringBuffer);
         //   System.out.println(stringBuffer);
        }
        return stringList;
    }

    public void parsingTagAttrFromDomain(String domain, String USERAGENT, String tagName, String attrName) throws IOException {
        Document doc;
        Elements docElements;

        doc = getDocument(domain, USERAGENT);

        Elements links = doc.select("a[href]");             // a with href
        Elements pngs = doc.select("img[src~=(?i).(png|jpe?g)]");       // img with src ending .png
        Element masthead = doc.select("div.masthead").first();   // div with class=masthead
        Elements resultLinks = doc.select("h3.r > a");      // direct a after h3

        String linkCleaningValue = "/shop";
        String linkCleaningNewValue = domain + "/shop";


        System.out.println(pngs);

        //  System.out.println(links);

        // System.out.println(doc);
        docElements = links;
        //   System.out.println(links);
        //   docElements = doc.body().getElementsByClass(tagName);

        // parsingLinks(docElements, attrName, linkCleaningValue, linkCleaningNewValue);
    }

    public List <String> parsingLinks(Elements docElements, String attrName, String attrValue) {
        List<String> elementsList = new ArrayList<String>();
        if (attrValue != null) {
        for (Element aDocElement : docElements) {
            if (aDocElement.attr(attrName).contains(attrValue)) {
                elementsList.add(aDocElement.attr(attrName));
            }
        }
        } else {
            for (Element aDocElement : docElements) {
                elementsList.add(aDocElement.attr(attrName));
            }
        }
        return elementsList;
    }

    public String decodingUrlUTF8(String url) throws UnsupportedEncodingException {
        return java.net.URLDecoder.decode(url, "UTF-8");
    }

    public void parsingTagAttrCssQueryFromDomain(String domain, String USERAGENT, String tagName, String attrName, String cssQuery) throws IOException {
        Document doc;
        Elements docElements;

        doc = getDocument(domain, USERAGENT);
        System.out.println(doc);
        docElements = doc.body().getElementsByClass(tagName);

        for (Element aDocElement : docElements) {
            System.out.println(aDocElement.getElementsByAttribute(attrName).select(cssQuery));
            fileOutput(doc.title(), aDocElement.select(cssQuery), tagName + "-" + attrName);
        }
    }

    public void parsingTagAttrFromList(String USERAGENT, String tagClassName, String attrName, List<String> listUrlsForParsing) throws IOException {
        String urlDupBuffer = null;
        Document document;
        Elements elements;

        for (String anUrlForParsing : listUrlsForParsing) {
            try {
                document = getDocument(anUrlForParsing, USERAGENT);
                assert document != null;
                elements = document.body().getElementsByClass(tagClassName);
                assert elements != null;
            } catch (AssertionError error) {
                continue;
            }

            for (Element anElement : elements) {
                String elementTagAttr = anElement.select(tagClassName).attr(attrName);
                if (!elementTagAttr.equals(urlDupBuffer)) {
                    fileOutput(anElement.select(tagClassName).attr(attrName), tagClassName);    // Saving urls of elements in categories;
                    urlDupBuffer = elementTagAttr;
                    System.out.println(elementTagAttr);
                }
            }
        }
    }

}
