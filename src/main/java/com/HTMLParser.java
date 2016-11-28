package com;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import siteModels.CommonModel;

import java.io.IOException;
import java.util.List;

/**
 * Created by WEO on 11/16/16.
 */
public class HTMLParser {

    public static void main(String args[]) throws IOException, ClassNotFoundException {

        final String USERAGENT = "Mozilla";

        String domain = "http://rabota.ua";
        String link = "/";

        ServiceUtils serviceUtils = new ServiceUtils();

        String tagName = "a";
        String attrName = "href";
        String attrValue = "";


        List <String> urlList = serviceUtils.getArrayListFromFile("faucets-list a[target='_blank']-href.txt");
        for (String aUrlList : urlList) {
            System.out.println(aUrlList);
            ServiceUtils.fileOutput(ServiceUtils.getDocument(aUrlList,USERAGENT),aUrlList.replace("http://", ""));
        }


      //  getLinksFromClass(USERAGENT, domain, link, serviceUtils, attrName, attrValue);


        /**
         * First Document
         */
        //  serviceUtils.parsingTagAttrFromDomain(domain, USERAGENT, tagName, attrName);

        /**
         *
         * Second Document
         */


        //  List<String> listUrlsForParsing = getArrayListFromFile(divCategoriesClassname);
        // parseListGetElementsSaveFile(domain, USERAGENT, divCategoriesElementsClassname, attrName, listUrlsForParsing);


        /**
         *
         * Third Document
         */

        // List<String> listUrlsForParsing = getArrayListFromFile(divCategoriesElementsClassname);

        //   savingElementsDescriptionsToFile(USERAGENT, divCategoriesElementsDescriptionClassname, listUrlsForParsing);
    }

    private static void getLinksFromClass(String USERAGENT, String domain, String link, ServiceUtils serviceUtils, String attrName, String attrValue) throws IOException {
        CommonModel commonModel = new CommonModel(ServiceUtils.getDocument(domain + link, USERAGENT));

        // System.out.println(commonModel.getPictures());
        List<String> urls = serviceUtils.parsingLinks(commonModel.getLinks(), attrName, attrValue);
        for (int i = 0; i < urls.size(); i++) {
             System.out.println(serviceUtils.decodingUrlUTF8(urls.get(i)));
        }

        //  urls = ServiceUtils.cleaningUrl(urls, attrName, attrValue);

        //   urls = serviceUtils.decodingUrlUTF8(urls);

        //  System.out.println(urls);
        System.out.println(commonModel.getResultLinks());
        System.out.println(commonModel.getPictures2());
        System.out.println(serviceUtils.decodingUrlUTF8("/%d0%b2%d0%b0%d0%ba%d0%b0%d0%bd%d1%81%d0%b8%d0%b8/hr-%d1%81%d0%bf%d0%b5%d1%86%d0%b8%d0%b0%d0%bb%d0%b8%d1%81%d1%82%d1%8b/%d1%83%d0%ba%d1%80%d0%b0%d0%b8%d0%bd%d0%b0"));


        //    ServiceUtils.fileOutput(ServiceUtils.cleaningUrl(serviceUtils.parsingLinks(commonModel.getLinks(),attrName,link).toString(),link,domain+link),"x");
        //  System.out.println(commonModel.getLinks());
        //   System.out.println(commonModel.getPictures());
    }

    private void savingElementsDescriptionsToFile(String USERAGENT, String divCategoriesElementsDescriptionClassname, List<String> listUrlsForParsing) throws IOException {
        Document docCategoriesDescription;
        Elements docCategoriesElementsDescription;
        for (String aListUrlforParsing : listUrlsForParsing) {
            try {
                docCategoriesDescription = ServiceUtils.getDocument(aListUrlforParsing, USERAGENT);
                assert docCategoriesDescription != null;
                docCategoriesElementsDescription = docCategoriesDescription.body().getElementsByClass(divCategoriesElementsDescriptionClassname);
                assert docCategoriesElementsDescription != null;
            } catch (AssertionError error) {
                continue;
            } catch (NullPointerException npError) {
                continue;
            }
            System.out.println(docCategoriesDescription.title());
            ServiceUtils.fileOutput(docCategoriesDescription.title(), docCategoriesElementsDescription, divCategoriesElementsDescriptionClassname);
        }
    }

}
                /*FileReader fileReaderCategories = new FileReader(divCategoriesElementsClassname);
                BufferedReader bufferedReader = new BufferedReader(fileReaderCategories);

                String urlForParsing;
                while ((urlForParsing = bufferedReader.readLine()) != null) {

                    if (!urlForParsing.equals(urlDupBuffer)) {
                        urlDupBuffer = urlForParsing;

                        docCategoriesDescription = getDocument(urlForParsing, USERAGENT);
                        docCategoriesElementsDescription = docCategoriesDescription.body().getElementsByClass(divCategoriesElementsDescriptionClassname);

                        System.out.println(urlForParsing);
                        System.out.println(docCategoriesDescription.title());
                        //       System.out.println(docCategoriesDescription);
                        fileOutput(docCategoriesDescription.title(), docCategoriesElementsDescription, divCategoriesElementsDescriptionClassname);
                    }
                }*/


//   fileOutput(title, docElements, divClassname);

       /* title = doc.title();*/

//   fileOutput(finalDocument.title(), finalDocElements, descriptionDivClassName);
            /*MappedByteBuffer out = new RandomAccessFile(fileOut, "rwd").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 0x9FFFFFF);*/
        /*url = doc.select("." + divClassname).attr(attrName);*/

         /*   Node N;
            N =  (Node) doc.body().getElementsByTagName("line").item(0);
            N.setTextContent("мой текст");
            Element line=(Element) N;
            line.setAttribute("status", "вот и всё оказывается");
*/

// String url = "http://play.google.com" + docFinal.get(0).tagName("href='/store/apps/category/'").getElementsByAttribute("href").attr("href", "/store/apps/category/Business");

            /*replaceAll("href='/store/apps/category/'","href='http://www.play.google.com/store/apps/category/'");
            docFinal.replaceAll(href="/store/apps/category/",href="http://www.play.google.com/store/apps/category/");*/
            /*  .select("/store/apps/category/").("https://play.google.com/store/apps/category");*/
            /*docFinal = doc.body().getElementsByClass(divClassname).attr("href", "/store/apps/category/");*/
