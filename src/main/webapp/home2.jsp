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
        <script type="text/javascript" src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script> 
        <script type="text/javascript" src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script> 
        <script type="text/javascript">
            window.onload = function() {
                $(".chartContainer").CanvasJSChart({
                    title: {
                        text: "Pasengers This Month"
                    },
                    axisY: {
                        title: "Count",
                        suffix: "",
                        includeZero: false
                    },
                    data: [
                        {
                            type: "column",
                            toolTipContent: "{label}: {y}",
                            dataPoints: [
                                {label: 1, y: 250},
                                {label: 2, y: 400},
                                {label: 3, y: 460},
                                {label: 4, y: 723},
                                {label: 5, y: 299},
                                {label: 6, y: 365},
                                {label: 7, y: 452},
                                {label: 8, y: 582},
                                {label: 9, y: 652},
                                {label: 10, y: 522},
                                {label: 11, y: 489},
                                {label: 12, y: 200},
                                {label: 13, y: 620},
                                {label: 14, y: 620},
                                {label: 15, y: 620},
                                {label: 16, y: 620},
                                {label: 17, y: 620},
                                {label: 18, y: 620},
                                {label: 19, y: 620},
                                {label: 20, y: 620},
                                {label: 21, y: 620},
                                {label: 22, y: 620},
                                {label: 23, y: 620},
                                {label: 24, y: 620},
                                {label: 25, y: 620},
                                {label: 26, y: 620},
                                {label: 27, y: 620},
                                {label: 28, y: 620},
                                {label: 29, y: 620},
                                {label: 30, y: 458}
                            ]
                        }
                    ]
                });
            }
        </script>
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
                    <jsp:include page="/navbar.jsp"/>
                    <!-- end: NAVIGATION BAR -->
                    <div class="chartContainer" style="height: 300px; width: 100%"></div>
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
