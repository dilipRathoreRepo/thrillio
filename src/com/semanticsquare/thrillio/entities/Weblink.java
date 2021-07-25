package com.semanticsquare.thrillio.entities;

import com.semanticsquare.thrillio.partner.Shareable;
import org.apache.commons.lang3.StringUtils;

public class Weblink extends Bookmark implements Shareable {
    private String url;
    private String host;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "Weblink{" +
                "url='" + url + '\'' +
                ", host='" + host + '\'' +
                '}';
    }

    @Override
    public boolean isKidFriendlyEligible() {
        if (url.contains("porn") || getTitle().contains("porn")){
            return false;
        }
        if (host.contains("adult")){
            return false;
        }
        if (url.contains("adult") && !host.contains("adult")){
            return true;
        }
        if (getTitle().contains("adult") && !(url.contains("adult") && (host.contains("adult")))){
            return true;
        }
        return true;
    }

    @Override
    public String getItemData() {
        StringBuilder builder = new StringBuilder();
        builder.append("<item>");
        builder.append("<type>Weblink</type>");
        builder.append("<title>").append(getTitle()).append("</title>");
        builder.append("<url>").append(url).append("</url>");
        builder.append("<host>").append(host).append("</host>");
        builder.append("</item>");

        return builder.toString();
    }
}
