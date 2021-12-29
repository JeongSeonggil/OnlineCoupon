package poly.dto;

public class CouponDTO {
    private String coupon_seq; // seq
    private String coupon_store_seq; // (FK) store_seq
    private String coupon_name; // 쿠폰 이름
    private String coupon_cnt; // 받은 도장 수
    private String coupon_max; // 도장 최대 값 (10개)
    private String coupon_contents; // 쿠폰에 들어갈 내용
    private String exists_yn; // 중복 확인

    public String getCoupon_seq() {
        return coupon_seq;
    }

    public void setCoupon_seq(String coupon_seq) {
        this.coupon_seq = coupon_seq;
    }

    public String getCoupon_store_seq() {
        return coupon_store_seq;
    }

    public void setCoupon_store_seq(String coupon_store_seq) {
        this.coupon_store_seq = coupon_store_seq;
    }

    public String getCoupon_name() {
        return coupon_name;
    }

    public void setCoupon_name(String coupon_name) {
        this.coupon_name = coupon_name;
    }

    public String getCoupon_cnt() {
        return coupon_cnt;
    }

    public void setCoupon_cnt(String coupon_cnt) {
        this.coupon_cnt = coupon_cnt;
    }

    public String getCoupon_max() {
        return coupon_max;
    }

    public void setCoupon_max(String coupon_max) {
        this.coupon_max = coupon_max;
    }

    public String getCoupon_contents() {
        return coupon_contents;
    }

    public void setCoupon_contents(String coupon_contents) {
        this.coupon_contents = coupon_contents;
    }

    public String getExists_yn() {
        return exists_yn;
    }

    public void setExists_yn(String exists_yn) {
        this.exists_yn = exists_yn;
    }
}
