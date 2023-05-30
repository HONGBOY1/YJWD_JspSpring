package com.kong.king.spring.guest04.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import com.kong.king.spring.guest04.entity.Guestbook;
import com.kong.king.spring.guest04.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Builder;

@SpringBootTest
public class GuestbookRepositoryTest {
	
	
	@Autowired
	private GuestbookRepository guestbookRepository;
	
//	@Test
//	public void insertDummies() {
//		IntStream.rangeClosed(1,300).forEach(i ->{
//			Guestbook guestbook = Guestbook.builder()
//					.title("제목..."+i)
//					.content("내용..." + i%5)
//					.writer("박준홍" + (i%10))
//					.build();
//			
//			System.out.println(guestbookRepository.save(guestbook));
//		});
//	}
	
//	@Test
//	public void updateTest() {
//		Optional<Guestbook> result = guestbookRepository.findById(302L);
//		
//		if(result.isPresent()) {
//			Guestbook guestbook = result.get();
//			
//			guestbook.changeTitle("업데이트 제목....");
//			guestbook.changeContent("업데이트 내용....");
//			
//			guestbookRepository.save(guestbook);
//		}
//	}
	
//	@Test
//	public void testQuery1() {
//		Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
//		
//		QGuestbook qGuestbook = QGuestbook.guestbook;
//		
//		String keyword = "1";
//		
//		BooleanBuilder builder = new BooleanBuilder();
//		
//		BooleanExpression expression = qGuestbook.title.contains(keyword);
//		
//		builder.and(expression);
//		
//		Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);
//		
//		result.stream().forEach(guestbook ->{
//			System.out.println(guestbook);
//		});
//
//	}
	
	@Test
	public void testQuery2() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
		
		QGuestbook qGuestbook = QGuestbook.guestbook;
		
		String keyword = "3";
		String keyword2 = "1";
		
		BooleanBuilder builder = new BooleanBuilder();
		
		BooleanExpression exTitle = qGuestbook.title.contains(keyword);
		BooleanExpression exContent = qGuestbook.content.contains(keyword2);
		BooleanExpression exAll = exTitle.and(exContent);
		
		builder.and(exAll);
		builder.and(qGuestbook.gno.gt(250L));
		
		Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);
		
		result.stream().forEach(guestbook ->{
			System.out.println(guestbook);
		});

	}
}
