package siteModels;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by WEO on 11/21/16.
 */
public class CommonModel {

    private Document doc;
    private Elements links;
    private Elements resultLinks;
    private Elements pictures;
    private Elements pictures2;
    private Elements div1;
    private Elements div2;
    private Elements attributes1;
    private Elements attributes2;

    public CommonModel(Document doc) {
        this.doc = doc;
        this.links = doc.select("a[href]");             // a with href
        this.pictures = doc.select("img[src~=(?i).(png|jpe?g)]");       // img with src ending .png
      //  this.divClassName = doc.select("div.masthead").first();   // div with class=masthead
        this.resultLinks = doc.select("h3.r > a.t[href]");      // direct a after h3
        this.pictures2 = doc.select("h3 > a[href]:last-child");
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }


    public Elements getLinks() {
        return links;
    }

    public void setLinks(Elements links) {
        this.links = links;
    }


    public Elements getResultLinks() {
        return resultLinks;
    }

    public void setResultLinks(Elements resultLinks) {
        this.resultLinks = resultLinks;
    }

    public Elements getPictures() {
        return pictures;
    }

    public void setPictures1(Elements pictures) {
        this.pictures = pictures;
    }

    public Elements getPictures2() {
        return pictures2;
    }

    public void setPictures2(Elements pictures2) {
        this.pictures2 = pictures2;
    }

    public Elements getDiv1() {
        return div1;
    }

    public void setDiv1(Elements div1) {
        this.div1 = div1;
    }

    public Elements getDiv2() {
        return div2;
    }

    public void setDiv2(Elements div2) {
        this.div2 = div2;
    }

    public Elements getAttributes1() {
        return attributes1;
    }

    public void setAttributes1(Elements attributes1) {
        this.attributes1 = attributes1;
    }

    public Elements getAttributes2() {
        return attributes2;
    }

    public void setAttributes2(Elements attributes2) {
        this.attributes2 = attributes2;
    }

    @Override
    public String toString() {
        return "CommonModel{" +
                "links=" + links +
                ", pictures=" + pictures +
                '}';
    }
}


