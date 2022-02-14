package poly.service.impl;

import poly.dto.CouponDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {
        List<CouponDTO> testList = new ArrayList<>();
        CouponDTO testDTO1 = new CouponDTO();
        CouponDTO testDTO2 = new CouponDTO();
        CouponDTO testDTO3 = new CouponDTO();

        testDTO1.setCoupon_seq("1");
        testDTO1.setCoupon_cnt("1");
        testDTO2.setCoupon_seq("2");
        testDTO2.setCoupon_cnt("2");
        testDTO3.setCoupon_seq("1");
        testDTO3.setCoupon_cnt("3");
        testList.add(testDTO1);
        testList.add(testDTO2);
        testList.add(testDTO3);

        List<CouponDTO> newList = testList.parallelStream().filter(p -> p.getCoupon_seq() == "1").collect(Collectors.toList());

        // List에 있는 모든 DTO에서 특정 값 뽑아오기

        for (int i = 0; i < newList.size(); i++) {
            System.out.println(newList.get(i).getCoupon_cnt());
        }

//        seq List seq 1인 cnt 리스트 뽑기

        // filter링 p -> p.getCoupon_seq() / List안에 값 p 즉 CouponDTO에서 get seq값이 "1"인 것 / List로 뽑아오기
    }
}
