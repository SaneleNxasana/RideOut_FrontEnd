package com.tp2assignment6.domaindesign.domain.factories.booking;

import com.tp2assignment6.domaindesign.domain.domain.booking.Site;

/**
 * Created by NXA-C.unltd on 2016/04/19.
 */
public class SiteFactory {
    public static Site getSite(String name, String url, String resUrl){
        return new Site.Builder()
                .name(name)
                .url(url)
                .reservationUrl(resUrl)
                .build();
    }
}
