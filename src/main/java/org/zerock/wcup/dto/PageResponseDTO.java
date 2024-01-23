package org.zerock.wcup.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.IntStream;

@Getter
@ToString
public class PageResponseDTO<E> {

    private List<E> dtoList;
    //이전 , 다음
    private boolean prev,next;
    //현재 페이지 번호
    private int current;
    //시작페이지번호, 끝페이지 번호
    private int start,end;
    //페이지 번호 목록
    private List<Integer> pageNumList;

    private long total;

    public PageResponseDTO(long total, int current, int size, List<E> dtoList) {
        this.total = total;
        this.current = current;

        this.dtoList = dtoList;

        int tempEnd = (int)(Math.ceil(current / 10.0) * 10);
        this.start = tempEnd - 9;
        this.prev = start > 1;

        int realEnd = (int)(Math.ceil(total/(size *1.0)) );

        this.end = realEnd < tempEnd ? realEnd : tempEnd;

        this.next = this.end * size < total;

        this.pageNumList =
                IntStream.rangeClosed(start,end).boxed().toList();
    }

//    public static void main(String[] args) {
//
//        long total = 100;
//        int pageNum = 7;
//        int size = 10;
//
//        System.out.println(
//                new PageResponseDTO(total,pageNum,size));
//
//    }

}
