<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Plant [·]</title>
</head>

	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/typed.js@2.0.11/lib/typed.min.js"></script>
    
<body>
	 <%@ include file="./header.jsp" %>

	 <div class="site-mobile-menu site-navbar-target">
		<div class="site-mobile-menu-header">
			<div class="site-mobile-menu-close">
				<span class="icofont-close js-menu-toggle"></span>
			</div>
		</div>
		<div class="site-mobile-menu-body"></div>
	</div>

	<div class="hero">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-7">
					<div class="intro-wrap">
						<h1 class="mb-5"><span class="d-block maintest1">Welcome To</span>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="typed-words maintest2"></span></h1>

						<div class="row">
							<div class="col-12">
									<div class="row mb-2">
										<div class="col-sm-12 col-md-6 mb-3 mb-lg-0 col-lg-4">
										</div>
										<div class="col-sm-12 col-md-6 mb-3 mb-lg-0 col-lg-5">
										</div>
										<div class="col-sm-12 col-md-6 mb-3 mb-lg-0 col-lg-3">
										</div>

									</div>
									<div class="row align-items-center">
										<div class="col-sm-12 col-md-6 mb-3 mb-lg-0 col-lg-4">
										</div>
										<div class="col-lg-8">
										</div>
									</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-5">
					<div class="slides">
						<img src="<%=mianImage%>/1.jpg" alt="Image" class="img-fluid active">
						<img src="<%=mianImage%>/2.jpg" alt="Image" class="img-fluid">
						<img src="<%=mianImage%>/hero-slider-2.jpg" alt="Image" class="img-fluid">
						<img src="<%=mianImage%>/3.jpg" alt="Image" class="img-fluid">
						<img src="<%=mianImage%>/4.jpg" alt="Image" class="img-fluid">
					</div>
				</div>
			</div>
		</div>
	</div>
	 
	<div class="container-xxl py-5">
            <div class="container">
                <div class="text-center mx-auto" data-wow-delay="0.1s" style="max-width: 500px;">
                    <p class="fs-5 fw-bold">쇼핑몰</p>
                    <h1 class="display-5 mb-5">집에서 식물들을 직접 길러 보세요!</h1>
                </div>

                <div class="row g-4">
                    <div class="col-lg-4 col-md-6" data-wow-delay="0.1s" onclick="move(1)">
                        <div class="service-item rounded d-flex h-100">
                            <div class="service-img rounded">
                                <img class="img-fluid" src="<%=mianImage%>/service-1.jpg" alt="">
                            </div>
                            <div class="service-text rounded p-5">
                                <div class="btn-square rounded-circle mx-auto mb-2">
                                    <img class="img-fluid" src="<%=mianImage%>/icon-4.png" alt="Icon">
                                </div>
                                <h4 class="mb-3">씨앗</h4>
                                <p class="mb-4 tt">딸기, 토마토, 허브 등 다양한 씨앗들을 &nbsp;&nbsp;&nbsp;만나보세요!</p>
                                <a class="btn btn-sm"><i class="bi bi-cart-plus-fill"></i>&nbsp;&nbsp;이동하기</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6" data-wow-delay="0.3s" onclick="move(0)">
                        <div class="service-item rounded d-flex h-100">
                            <div class="service-img rounded">
                                <img class="img-fluid" src="<%=mianImage%>/service-2.jpg" alt="">
                            </div>
                            <div class="service-text rounded p-5">
                                <div class="btn-square rounded-circle mx-auto mb-3">
                                    <img class="img-fluid" src="<%=mianImage%>/icon-5.png" alt="Icon">
                                </div>
                                <h4 class="mb-3">전체보기</h4>
                                <p class="mb-4">Plant [·] 에서 모든 것을 한번에&nbsp;&nbsp;느껴보세요!</p>
                                <a class="btn btn-sm"><i class="bi bi-cart-plus-fill"></i>&nbsp;&nbsp;이동하기</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6" data-wow-delay="0.5s" onclick="move(2)">
                        <div class="service-item rounded d-flex h-100">
                            <div class="service-img rounded">
                                <img class="img-fluid" src="<%=mianImage%>/service-3.jpg" alt="">
                            </div>
                            <div class="service-text rounded p-5">
                                <div class="btn-square rounded-circle mx-auto mb-3">
                                    <img class="img-fluid" src="<%=mianImage%>/icon-6.png" alt="Icon">
                                </div>
                                <h4 class="mb-3">반려식물</h4>
                                <p class="mb-4">친구처럼 정서적인 교감과위안을 얻는 식물 Plant [·] 에서 만나보세요</p>
								<a class="btn btn-sm"><i class="bi bi-cart-plus-fill"></i>&nbsp;&nbsp;이동하기</a>                            
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6" data-wow-delay="0.5s" onclick="move(4)">
                        <div class="service-item rounded d-flex h-100">
                            <div class="service-img rounded">
                                <img class="img-fluid" src="<%=mianImage%>/service-4.jpg" alt="">
                            </div>
                            <div class="service-text rounded p-5">
                                <div class="btn-square rounded-circle mx-auto mb-3">
                                    <img class="img-fluid" src="<%=mianImage%>/icon-8.png" alt="Icon">
                                </div>
                                <h4 class="mb-3">중고식물</h4>
                                <p class="mb-4">키우다 포기한 식물들을 훨씬 더 저럼한 가격에 키워보세요!</p>
                            	<a class="btn btn-sm"><i class="bi bi-cart-plus-fill"></i>&nbsp;&nbsp;이동하기</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6" data-wow-delay="0.5s" onclick="move(3)">
                        <div class="service-item rounded d-flex h-100">
                            <div class="service-img rounded">
                                <img class="img-fluid" src="<%=mianImage%>/service-5.jpg" alt="">
                            </div>
                            <div class="service-text rounded p-5">
                                <div class="btn-square rounded-circle mx-auto mb-3">
                                    <img class="img-fluid" src="<%=mianImage%>/icon-9.png" alt="Icon">
                                </div>
                                <h4 class="mb-3">정원용품</h4>
                                <p class="mb-4">식물들이 잘자라 라고 도와주는 용품들을 만나 보세요!</p>
                            	<a class="btn btn-sm"><i class="bi bi-cart-plus-fill"></i>&nbsp;&nbsp;이동하기</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6" data-wow-delay="0.5s">
                        <div class="service-item rounded d-flex h-100">
                            <div class="service-img rounded">
                                <img class="img-fluid" src="<%=mianImage%>/service-6.jpg" alt="">
                            </div>
                            <div class="service-text rounded p-5">
                                <div class="btn-square rounded-circle mx-auto mb-3">
                                    <img class="img-fluid" src="<%=mianImage%>/icon-1.png" alt="Icon">
                                </div>
                                <h4 class="mb-3">준비중 ....</h4>
                                <p class="mb-4">&nbsp;&nbsp; 준 비 중 입 니 다. &nbsp;&nbsp;&nbsp;&nbsp; 나 중 에 만 나 요. &nbsp; Comming Soon</p>
                            	
                            </div>
                        </div>
                    </div>
                </div>

            </div>
    </div>
     <script type="text/javascript">
	 	function move(cg) {
	 		location.href = "./ProductController.pro?actionType=pro_mlist&pro_cg="+cg;
		}
	 	
	 	$(function() {
			var slides = $('.slides'),
			images = slides.find('img');

			images.each(function(i) {
				$(this).attr('data-id', i + 1);
			})

			var typed = new Typed('.typed-words', {
				strings: ["PLANT[ · ]"," Seed[ · ]","Garden[ · ]","JSP[ · ]","Shopping[ · ]"],
				typeSpeed: 80,
				backSpeed: 80,
				backDelay: 4000,
				startDelay: 1000,
				loop: true,
				showCursor: true,
				preStringTyped: (arrayPos, self) => {
					arrayPos++;
					console.log(arrayPos);
					$('.slides img').removeClass('active');
					$('.slides img[data-id="'+arrayPos+'"]').addClass('active');
				}

			});
		})
	 </script>
   

</body>
</html>