<%-- 
    Document   : home
    Created on : Sep 14, 2016, 5:11:38 PM
    Author     : dilanka_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib  uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<!DOCTYPE html>


<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <%@include file="/stylelinks.jspf" %>
        <title>Home Page</title>

        <!--<script src = "https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" ></script>-->
        <link rel="stylesheet" href="resouces/chart/chart.css">
            <script src="resouces/chart/jquery.chart.js"></script>
            <script>
                $(function() {
                    $('.bar-chart').cssCharts({type: "bar"});
                    $('.donut-chart').cssCharts({type: "donut"}).trigger('show-donut-chart');
                    $('.line-chart').cssCharts({type: "line"});

                    $('.pie-thychart').cssCharts({type: "pie"});
                });
            </script>
            <style>
                /* page specific styles*/
                h1{text-align:center;font-family:sans-serif;font-size:28px;color:#333;padding:10px 0 0 0;}
                h2{text-align:center;font-family:sans-serif;font-size:18px;color:#333;padding:10px 0 0 0;}
                h3{text-align:center;font-family:sans-serif;font-size:14px;color:#333;padding:10px 0 0 0;}
                hr{width:60%;height:1px;background:none;border:none;border-bottom:1px dashed rgba(0,0,0,0.1);outline:none;margin:40px auto 60px auto;}

                .desc p{text-align:center;font-size:16px;color:rgba(0,0,0,0.6);padding:20px 0 0 0;font-family:sans-serif;}
                .desc a{color:blue;}
                /*.wrap{margin:0 auto;width:640px;padding-bottom:100px;}*/
                .wrap{margin:0 auto;padding-bottom:100px;}
                #line{width:400px;}
                /* page specific styles*/
            </style>
    </head>
    <body>

        <jsp:include page="/header.jsp"/>
        <div class="main-container">
            <jsp:include page="/leftmenu.jsp">
                <jsp:param name="firstpage" value="0"/>
            </jsp:include>
            <div class="main-content">

                <div class="container" style="min-height: 760px;">
                    <s:div>
                        <s:actionerror theme="jquery"/>
                        <s:actionmessage theme="jquery"/>
                    </s:div>
                    <!-- start: PAGE NAVIGATION BAR -->
                    <%--<jsp:include page="/navbar.jsp"/>--%>
                    <!-- end: NAVIGATION BAR -->


                    <div class="wrap">



                        <h3>Passengers This Month</h3>

                        <div class="chart" style="width: 1263px; height: 220px;">
                            <ul class="bar-chart" data-bars="[4],[4],[8],[4],[4],[4],[4],[8],[4],[4],[4],[4],[4]" data-max="10" data-unit="k" data-width="24"></ul>
                        </div>
                        <!-- data max is the 100% point of the graph -->
                        <!-- set data-grid to 0 for no grid -->
                        <!-- data-width is the individual bars width -->


                        <h3>Vehicle Type</h3>


                        <div class="chart">
                            <div class="pie-thychart" data-set='[["Mini", 25], ["Car",20], ["Nano",55]]' data-colors="#FBE4DB,#F17F49,#BD380F"></div>
                        </div>




                        <!-- pie-chart class for pie chart -->
                        <!-- fill class for default donut fill enabled-->

                        <h2>Test chart v1</h2>
                        <hr>

                            <div class="chart">
                                <ul data-cord="[[0,70,120,160,200,262,280,340,380,420,460],[140,150,40,90,40,60,120,20,60,90,10]],[[0,70,120,160,200,262,280,340,380,420,460],[200,190,80,130,80,100,160,60,100,130,50]]"  class="line-chart"></ul>
                            </div>
                            <!-- multiple graphs [[x cords],[y cords]] [[x cords],[y cords]] -->



                            <h2>Test chart v4</h2>
                            <hr>

                                <div class="chart dotted">
                                    <ul data-cord="[0,90,120,160,200,262,280,340,380,420,460],[40,150,40,90,40,60,120,20,60,90,10]" data-fill="1" class="line-chart no-points"></ul>
                                </div>
                                <!-- single graph [x cords],[y cords] -->


                                <br><br><br>


                                            </div>
                                            </div>

                                            <!-- end: PAGE CONTENT-->
                                            </div>
                                            </div>
                                            <!-- end: PAGE -->
                                            <!-- end: MAIN CONTAINER -->
                                            <!-- start: FOOTER -->
                                            <jsp:include page="/footer.jsp"/>
                                            <!-- end: FOOTER -->
                                            <!-- end: BODY -->
                                            </body>
                                            </html>
