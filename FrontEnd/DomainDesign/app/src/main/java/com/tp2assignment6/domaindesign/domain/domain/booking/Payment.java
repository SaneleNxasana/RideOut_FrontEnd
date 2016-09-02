package com.tp2assignment6.domaindesign.domain.domain.booking;

/**
 * Created by NXA-C.unltd on 2016/04/18.
 */
public class Payment {
    private Long idNumber;
    private String paymentType;
    private String amount;

    public Payment() {
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getAmount() {
        return amount;
    }

    public static class Builder{
        private Long idNumber;
        private String paymentType;
        private String amount;

        public Builder idNumber(Long value){
            this.idNumber = value;
            return this;
        }
        public Builder paymentType(String value){
            this.paymentType = value;
            return this;
        }
        public Builder amount(String value){
            this.amount = value;
            return this;
        }

        public Builder copy(Payment value){
            this.idNumber = value.idNumber;
            this.paymentType = value.paymentType;
            this.amount = value.amount;
            return this;
        }

        public Payment build() {return new Payment(this);}

    }

    public Payment(Builder builder){
            this.idNumber = builder.idNumber;
            this.paymentType = builder.paymentType;
            this.amount = builder.amount;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Payment that = (Payment) obj;

        return idNumber != null ? idNumber.equals(that.idNumber) : that.idNumber == null;
    }

    @Override
    public int hashCode() {return idNumber != null ? idNumber.hashCode() : 0;}
}
