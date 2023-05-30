package com.kong.king.spring.exam02.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.kong.king.spring.exam02.entity.MemoBoard;

import jakarta.transaction.Transactional;


@SpringBootTest
public class MemoBoardRepositoryTest {
	
	@Autowired
	MemoBoardRepository memoBoardRepository;
	
	/*
	 * @Test public void testClass(){ System.out.println("객채생성 확인 ----"
	 * +memoBoardRepository.getClass().getName()); }
	 */
	
//	
//	  @Test public void testInsertDummies(){ IntStream.rangeClosed(1, 100).forEach(
//	  i -> { MemoBoard memo = MemoBoard.builder().content("메모...." + i).build();
//	  memoBoardRepository.save(memo); }); }

	
//	
//	 @Test public void testInsertDummies2(){ MemoBoard memo;
//	  
//	 for(int i = 200; i<=250; i++) { memo = new MemoBoard();
//	 memo.setContent("메모x테스트..."+i); memoBoardRepository.save(memo); } }
//	 
	
//	@Test
//	public void testSelectById() {
//		Long mid = 3L;
//		
//		Optional<MemoBoard> result = memoBoardRepository.findById(mid);
//		
//		System.out.println("-------------------");
//		
//		if(result.isPresent()) {
//			MemoBoard memo = result.get();
//			System.out.println(memo);
//		}
//	}	
	
//	@Transactional
//	@Test
//	public void testSelect2ById() {
//		Long mid = 30L;
//		
//		MemoBoard memo = memoBoardRepository.getOne(mid);
//		
//		System.out.println("-------------------");
//		System.out.println(memo);
//		
//	}

//	@Test
//	public void testSelctAll() {
//		List<MemoBoard> list = new ArrayList<MemoBoard>();
//		
//		list = memoBoardRepository.findAll();
//		
//		System.out.println(list);
//	}

//	@Test
//	public void testUpdate() {
//		MemoBoard memo = MemoBoard.builder()
//				.mid(100L)
//				.content("100번 데이터 수정....")
//				.build();
//		System.out.println(memoBoardRepository.save(memo));
//	}
	
//	@Test
//	public void testDelete() {
//		Long mid = 100L;
//		
//		memoBoardRepository.deleteById(mid);
//	}
	
//	@Test
//	public void testPageDefault() {
//		Pageable pageable = PageRequest.of(0, 10);
//		Page<MemoBoard> result = memoBoardRepository.findAll(pageable);
//		
//		System.out.println(result);
//		
//		System.out.println("-----------------------");
//		
//		System.out.println("Total Pages : " + result.getTotalPages());
//		System.out.println("Total Count : " + result.getTotalElements());
//		System.out.println("Total Number : " + result.getNumber());
//		System.out.println("Total Size : " + result.getSize());
//		System.out.println("has next page? : " + result.hasNext());
//		System.out.println("first page? : " + result.isFirst());
//		
//		
//		System.out.println("-----------------------");
//		
//		for(MemoBoard memo : result.getContent()) {
//			System.out.println(memo);
//		}
//	}
	
//	@Test
//	public void testSort() {
//		Sort sort1 = Sort.by("mid").descending();
//		
//		Pageable pageable = PageRequest.of(0, 10, sort1);
//		Page<MemoBoard> result = memoBoardRepository.findAll(pageable);
//		
//		result.get().forEach(memo -> {
//			System.out.println(memo);
//		});
//	}
	
//	@Test
//	public void testSort2() {
//		Sort sort1 = Sort.by("content").ascending();
//		Sort sort2 = Sort.by("mid").descending();
//		Sort sortAll= sort1.and(sort2);
//		
//		
//		Pageable pageable = PageRequest.of(0, 10, sort1);
//		Page<MemoBoard> result = memoBoardRepository.findAll(pageable);
//		
//		result.get().forEach(memo -> {
//			System.out.println(memo);
//		});
//	}
	
	
//	@Test
//	public void testQueryMethodes() {
//		List<MemoBoard> list = memoBoardRepository.findByMidBetweenOrderByMidDesc(70L, 80L);
//		
//		for(MemoBoard memo : list) {
//			System.out.println(memo);
//		}
//	}
	
//	@Test
//	public void testQueryMethodWithPageable() {
//		Pageable pageable = PageRequest.of(0,10,Sort.by("mid").descending());
//		
//		Page<MemoBoard> result  = memoBoardRepository.findByMidBetween(10L, 50L, pageable);
//	
//		result.get().forEach(memo -> System.out.println(memo));
//	}
	
//	@Test
//	public void testAtQueryGetAllData() {
//		List<MemoBoard> list = memoBoardRepository.getMemoBoardsListAllDesc();
//		
//		for(MemoBoard memo : list) {
//			System.out.println(memo);
//		}
//	}

//	@Test
//	public void testAtQueryUpdateById() {
//		int result = memoBoardRepository.updateMemoBoardContentWithMid(2L, "@Query를 활용한 수정 실습");
//		
//		System.out.println("수정결과 : " +result);
//	}
	
//	@Test
//	public void testAtQueryUpdateByObj() {
//		MemoBoard memo = new MemoBoard();
//		memo.setMid(2L);
//		memo.setContent("@Query를 이용한 수정 - Object전달 방식");
//		int result = memoBoardRepository.updateMemoBoardContenWithObj(memo);
//		
//		System.out.println("수정결과 : " + result);
//	}
//	
	
//	@Test
//	public void testAtQueryWithPageable() {
//		
//		Pageable pageable = PageRequest.of(0, 10, Sort.by("mid").descending());
//		
//		Page<MemoBoard> result = memoBoardRepository.getListWithQueryandPaging(1L, pageable);
//		
//		System.out.println("------------------");
//		System.out.println("result = " +result);
//		result.get().forEach(memo -> System.out.println(memo));
//	}
	
//	@Test
//	public void testAtQueryObjectWithPageable() {
//		
//		Pageable pageable = PageRequest.of(0, 10, Sort.by("mid").descending());
//		
//		Page<Object[]> result = memoBoardRepository.getListWithQueryObject(1L, pageable);
//		
//		System.out.println("-----------------");
//		System.out.println("retult = " + result);
//		result.get().forEach(memo -> System.out.println(Arrays.toString(memo)));
//		
//	}
	
	@Test
	public void testNativeQuery() {
		List<MemoBoard> list = memoBoardRepository.getNativeResult();
		
		for(MemoBoard memo : list) {
			System.out.println(memo);
		}
	}
}
