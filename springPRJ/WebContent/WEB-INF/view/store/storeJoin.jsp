<%@ page import="poly.util.CmmUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

    <title>Hexashop Ecommerce HTML CSS Template</title>


    <!-- Additional CSS Files -->
    <link rel="stylesheet" type="text/css" href="/resources/assets/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="/resources/assets/css/font-awesome.css">

    <link rel="stylesheet" href="/resources/assets/css/templatemo-hexashop.css">

    <link rel="stylesheet" href="/resources/assets/css/owl-carousel.css">

    <link rel="stylesheet" href="/resources/assets/css/lightbox.css">
    <!--

    TemplateMo 571 Hexashop

    https://templatemo.com/tm-571-hexashop

    -->
</head>

<body>

<!-- ***** Preloader Start ***** -->
<div id="preloader">
    <div class="jumper">
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>
<!-- ***** Preloader End ***** -->


<!-- ***** Header Area Start ***** -->
<header class="header-area header-sticky">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <nav class="main-nav">
                    <!-- ***** Logo Start ***** -->
                    <a href="/index.do" class="logo">
                        <img src="/resources/assets/images/logo.png">
                    </a>
                </nav>
            </div>
        </div>
    </div>
</header>
<!-- ***** Header Area End ***** -->

<!-- ***** Subscribe Area Starts ***** -->
<div class="subscribe" style="margin-top: 30%;">
    <div class="container">
        <div class="row">
            <div class="col-lg-8">
                <div class="section-heading">
                    <h2>Join</h2>
                </div>
                <%-- Login Form--%>
                <form id="subscribe" action="/store/insertStoreInfo.do" method="post">
                    <div class="row">
                        <div class="col-lg-5">
                            <fieldset>
                                <input name="store_id" type="text" id="name" placeholder="Your ID" required="">
                            </fieldset>
                        </div>
                        <div class="col-lg-5">
                            <fieldset>
                                <input name="store_password" type="password" placeholder="Your PASSWORD" required="">
                            </fieldset>
                        </div>
                        <div class="col-lg-5">
                            <fieldset>
                                <input name="store_address" type="text" placeholder="Your ADDRESS" required="">
                            </fieldset>
                        </div>
                        <div class="col-lg-5">
                            <fieldset>
                                <input name="store_address2" type="text" placeholder="Your ADDRESS2" required="">
                            </fieldset>
                        </div>
                        <div class="col-lg-2">
                            <fieldset>
                                <button type="submit" id="form-submit" class="main-dark-button"><i class="fa fa-paper-plane"></i></button>
                                <br>
                                <br>
                                <a href="https://templatemo.com" target="_parent" title="free css templates">회원가입</a><br>
                                <a href="https://themewagon.com" target="_blank" title="free & premium responsive templates">사업자로그인</a></p>
                                <br>
                            </fieldset>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="first-item">
                    <div class="logo">
                        <img src="/resources/assets/images/white-logo.png" alt="hexashop ecommerce templatemo">
                    </div>
                    <ul>
                        <li><a href="#">16501 Collins Ave, Sunny Isles Beach, FL 33160, United States</a></li>
                    </ul>
                </div>
            </div>
            <div class="under-footer">
            </div>
        </div>
    </div>
</footer>


<!-- jQuery -->
<script src="/resources/assets/js/jquery-2.1.0.min.js"></script>

<!-- Bootstrap -->
<script src="/resources/assets/js/popper.js"></script>
<script src="/resources/assets/js/bootstrap.min.js"></script>

<!-- Plugins -->
<script src="/resources/assets/js/owl-carousel.js"></script>
<script src="/resources/assets/js/accordions.js"></script>
<script src="/resources/assets/js/datepicker.js"></script>
<script src="/resources/assets/js/scrollreveal.min.js"></script>
<script src="/resources/assets/js/waypoints.min.js"></script>
<script src="/resources/assets/js/jquery.counterup.min.js"></script>
<script src="/resources/assets/js/imgfix.min.js"></script>
<script src="/resources/assets/js/slick.js"></script>
<script src="/resources/assets/js/lightbox.js"></script>
<script src="/resources/assets/js/isotope.js"></script>

<!-- Global Init -->
<script src="/resources/assets/js/custom.js"></script>

<script>

    $(function() {
        var selectedClass = "";
        $("p").click(function(){
            selectedClass = $(this).attr("data-rel");
            $("#portfolio").fadeTo(50, 0.1);
            $("#portfolio div").not("."+selectedClass).fadeOut();
            setTimeout(function() {
                $("."+selectedClass).fadeIn();
                $("#portfolio").fadeTo(50, 1);
            }, 500);

        });
    });

</script>

</body>
</html>