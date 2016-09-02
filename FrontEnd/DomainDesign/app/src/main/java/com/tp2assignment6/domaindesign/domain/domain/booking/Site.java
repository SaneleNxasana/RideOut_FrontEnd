package com.tp2assignment6.domaindesign.domain.domain.booking;

import java.io.Serializable;

/**
 * Created by NXA-C.unltd on 2016/04/18.
 */
public class Site implements Serializable{
    private Long idNumber;
    private String name;
    private String url;
    private String reservationUrl;

    public Site() {
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getReservationUrl() {
        return reservationUrl;
    }

    public static class Builder{
        private Long idNumber;
        private String name;
        private String url;
        private String reservationUrl;

        public Builder idNumber(Long value){
            this.idNumber = value;
            return this;
        }
        public Builder name(String value){
            this.name = value;
            return this;
        }
        public Builder url(String value){
            this.url = value;
            return this;
        }
        public Builder reservationUrl(String value){
            this.reservationUrl = value;
            return this;
        }

        public Builder copy(Site value){
            this.idNumber = value.idNumber;
            this.name = value.name;
            this.url = value.url;
            this.reservationUrl = value.reservationUrl;
            return this;
        }

        public Site build(){return new Site(this);}
    }

    public Site (Builder builder){
        this.idNumber = builder.idNumber;
        this.name = builder.name;
        this.url = builder.url;
        this.reservationUrl = builder.reservationUrl;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Site that = (Site) obj;

        return idNumber != null ? idNumber.equals(that.idNumber) : that.idNumber == null;
    }

    @Override
    public int hashCode() {return idNumber != null ? idNumber.hashCode() : 0;}
}
