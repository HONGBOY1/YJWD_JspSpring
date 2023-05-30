package com.kong.king.spring.guest04.service;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.kong.king.spring.guest04.dto.GuestbookDTO;
import com.kong.king.spring.guest04.dto.PageRequestDTO;
import com.kong.king.spring.guest04.dto.PageResultDTO;
import com.kong.king.spring.guest04.entity.Guestbook;
import com.kong.king.spring.guest04.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Builder;

@SpringBootTest
public class GuestbookServiceTest {
	
	
	@Autowired
	private GuestbookService service;
	
//	@Test
//	public void testReggister() {
//		GuestbookDTO guestbookDTO = GuestbookDTO.builder()
//				.title("샘플 제목....")
//				.content("샘플 내용....")
//				.writer("user0")
//				.build();
//		
//		System.out.println(service.register(guestbookDTO));
//	}
	
	@Test
	public void testList() {
		
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
		
		PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);
		
		System.out.println("PREV : " + resultDTO.isPrev());
		System.out.println("NEXT : " + resultDTO.isNext());
		System.out.println("TOTAL : " + resultDTO.getTotalPage());
		
		System.out.println("------------------------------");
		for(GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
			System.out.println(guestbookDTO);
		}
		System.out.println("------------------------------");
		resultDTO.getPageList().forEach(i -> System.out.println(i));
	}
	
//	@Test
//	public void testSearch() {
//		
//		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).
//				type("tc").keyword("샘플").build();
//		
//		PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);
//		
//		System.out.println("PREV : " + resultDTO.isPrev());
//		System.out.println("NEXT : " + resultDTO.isNext());
//		System.out.println("TOTAL : " + resultDTO.getTotalPage());
//		
//		System.out.println("------------------------------");
//		for(GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
//			System.out.println(guestbookDTO);
//		}
//		System.out.println("------------------------------");
//		resultDTO.getPageList().forEach(i -> System.out.println(i));
//	}
}
