package poly.dto;

public class UserCouponRelationDTO {
    private String ucr_seq; // seq
    private String ucr_user_seq; // (FK) user_seq
    private String ucr_coupon_seq; // (FK) coupon_seq
    private String ucr_date; // 쿠폰 발행 날짜

    public String getUcr_seq() {
        return ucr_seq;
    }

    public void setUcr_seq(String ucr_seq) {
        this.ucr_seq = ucr_seq;
    }

    public String getUcr_user_seq() {
        return ucr_user_seq;
    }

    public void setUcr_user_seq(String ucr_user_seq) {
        this.ucr_user_seq = ucr_user_seq;
    }

    public String getUcr_coupon_seq() {
        return ucr_coupon_seq;
    }

    public void setUcr_coupon_seq(String ucr_coupon_seq) {
        this.ucr_coupon_seq = ucr_coupon_seq;
    }

    public String getUcr_date() {
        return ucr_date;
    }

    public void setUcr_date(String ucr_date) {
        this.ucr_date = ucr_date;
    }
}
