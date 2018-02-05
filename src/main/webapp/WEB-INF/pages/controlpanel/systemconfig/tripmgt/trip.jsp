<%-- 
    Document   : trip
    Created on : Feb 5, 2018, 11:25:54 PM
    Author     : chanuka
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib  uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <%@include file="/stylelinks.jspf" %>
        <script type="text/javascript">
            $.subscribe('onclicksearch', function(event, data) {
                $('#message').empty();

                var customerSearch = $('#customerSearch').val();
                var driverSearch = $('#driverSearch').val();
                var vehicleSearch = $('#vehicleSearch').val();
                var sourceSearch = $('#sourceSearch').val();
                var destinationSearch = $('#destinationSearch').val();
                var startedTimeSearch = $('#startedTimeSearch').val();
                var endTimeSearch = $('#endTimeSearch').val();
                var fareSearch = $('#fareSearch').val();
                var statusSearch = $('#statusSearch').val();

                $("#gridtable").jqGrid('setGridParam', {
                    postData: {
                        customerSearch: customerSearch,
                        statusSearch: statusSearch,
                        driverSearch: driverSearch,
                        vehicleSearch: vehicleSearch,
                        sourceSearch: sourceSearch,
                        destinationSearch: destinationSearch,
                        startedTimeSearch: startedTimeSearch,
                        endTimeSearch: endTimeSearch,
                        fareSearch: fareSearch,
                        search: true
                    }
                });

                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");
            });

            function searchTrip(param) {
                $('#message').empty();

                var customerSearch = $('#customerSearch').val();
                var driverSearch = $('#driverSearch').val();
                var vehicleSearch = $('#vehicleSearch').val();
                var sourceSearch = $('#sourceSearch').val();
                var destinationSearch = $('#destinationSearch').val();
                var startedTimeSearch = $('#startedTimeSearch').val();
                var endTimeSearch = $('#endTimeSearch').val();
                var fareSearch = $('#fareSearch').val();
                var statusSearch = $('#statusSearch').val();

                $("#gridtable").jqGrid('setGridParam', {
                    postData: {
                        customerSearch: customerSearch,
                        statusSearch: statusSearch,
                        driverSearch: driverSearch,
                        vehicleSearch: vehicleSearch,
                        sourceSearch: sourceSearch,
                        destinationSearch: destinationSearch,
                        startedTimeSearch: startedTimeSearch,
                        endTimeSearch: endTimeSearch,
                        fareSearch: fareSearch,
                        search: param
                    }
                });

                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");
            }

            $.subscribe('anyerrors', function(event, data) {
                window.location = "${pageContext.request.contextPath}/LogoutUserLogin.action?";
            });

        

            function resetAllData() {
                $('#customerSearch').val("");
                $('#statusSearch').val("");
                $('#driverSearch').val("");
                $('#vehicleSearch').val("");
                $('#sourceSearch').val("");
                $('#destinationSearch').val("");
                $('#startedTimeSearch').val("");
                $('#endTimeSearch').val("");
                $('#fareSearch').val("");

                $("#gridtable").jqGrid('setGridParam', {
                    postData: {
                        customerSearch: '',
                        statusSearch: '',
                        driverSearch: '',
                        vehicleSearch: '',
                        sourceSearch: '',
                        destinationSearch: '',
                        startedTimeSearch: '',
                        endTimeSearch: '',
                        fareSearch: '',
                        search: false
                    }
                });
                jQuery("#gridtable").trigger("reloadGrid");
            }

            function resetFieldData() {

                $('#customer').val("");
                $('#status').val("");
                $('#driver').val("");
                $('#vehicle').val("");
                $('#source').val("");
                $('#destination').val("");
                $('#startedTime').val("");
                $('#endTime').val("");
                $('#fare').val("");

                $("#gridtable").jqGrid('setGridParam', {postData: {search: false}});
                jQuery("#gridtable").trigger("reloadGrid");
            }


        </script>  
        <title></title>
    </head>
    <body style="">
        <jsp:include page="/header.jsp"/>
        <div class="main-container">
            <jsp:include page="/leftmenu.jsp"/>
            <div class="main-content">
                <div class="container" style="min-height: 760px;">
                    <!-- start: PAGE NAVIGATION BAR -->
                    <jsp:include page="/navbar.jsp"/>
                    <!-- end: NAVIGATION BAR -->
                    <div class="row">
                        <div id="content1">
                            <s:div id="divmsg">
                                <s:actionerror theme="jquery"/>
                                <s:actionmessage theme="jquery"/>
                            </s:div>

                            <s:set id="vadd" var="vadd"><s:property value="vadd" default="true"/></s:set>
                            <s:set var="vupdatebutt"><s:property value="vupdatebutt" default="true"/></s:set>
                            <s:set var="vupdatelink"><s:property value="vupdatelink" default="true"/></s:set>
                            <s:set var="vdelete"><s:property value="vdelete" default="true"/></s:set>

                            <s:set var="vsearch"><s:property value="vsearch" default="true"/></s:set>

                                <div id="formstyle">
                                <s:form cssClass="form" id="tripsearch" method="post" action="Trip" theme="simple" >
                                    <div class="row row_1">
                                        <div class="col-sm-3">
                                            <div class="form-group">
                                                <label>Customer</label>
                                                <s:textfield cssClass="form-control" name="customerSearch" id="customerSearch" maxLength="64" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="form-group form-inline">
                                                <label >Status</label>
                                                <s:select  cssClass="form-control" name="status" id="statusSearch" list="%{statusList}"   headerKey=""  headerValue="--Select Status--" listKey="statuscode" listValue="description" value="%{status}" disabled="false"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="form-group">
                                                <label>Driver</label>
                                                <s:textfield cssClass="form-control" name="driverSearch" id="driverSearch" maxLength="64" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="form-group">
                                                <label>Vehicle</label>
                                                <s:textfield cssClass="form-control" name="vehicleSearch" id="vehicleSearch" maxLength="64" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))"/>
                                            </div>
                                        </div>


                                    </div>
                                    <div class="row row_1">
                                        <div class="col-sm-3">
                                            <div class="form-group">
                                                <label>Source</label>
                                                <s:textfield cssClass="form-control" name="sourceSearch" id="sourceSearch" maxLength="64" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="form-group">
                                                <label>Destination</label>
                                                <s:textfield cssClass="form-control" name="destinationSearch" id="destinationSearch" maxLength="64" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="form-group">
                                                <label>Started Time</label>
                                                <s:textfield cssClass="form-control" name="startedTimeSearch" id="startedTimeSearch" maxLength="64" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="form-group">
                                                <label>End Time</label>
                                                <s:textfield cssClass="form-control" name="endTimeSearch" id="endTimeSearch" maxLength="64" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row row_1">
                                        <div class="col-sm-3">
                                            <div class="form-group">
                                                <label>Fare</label>
                                                <s:textfield cssClass="form-control" name="fareSearch" id="fareSearch" maxLength="64" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))"/>
                                            </div>
                                        </div>

                                    </div>
                                </s:form>
                                <div class="row row_1"></div>
                                <div class="row row_1 form-inline">
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <sj:submit 
                                                button="true"
                                                value="Search" 
                                                disabled="#vsearch"
                                                onclick="searchTrip(true)"  
                                                id="searchbut"
                                                cssClass="form-control btn_normal"
                                                cssStyle="border-radius: 12px;background-color:#969595;color:white;"
                                                />
                                        </div> 
                                        <div class="form-group">
                                            <sj:submit 
                                                button="true" 
                                                value="Reset" 
                                                name="reset" 
                                                onClick="resetAllData()" 
                                                cssClass="form-control btn_normal"
                                                cssStyle="border-radius: 12px;"
                                                />
                                        </div>
                                    </div>
                                    <div class="col-sm-5"></div>

                                </div>
                            </div>
                         
                        </div>
                        <div id="tablediv">
                            <s:url var="listurl" action="ListTrip"/>
                            <s:set var="pcaption">${CURRENTPAGE}</s:set>

                            <sjg:grid
                                id="gridtable"
                                caption="%{pcaption}"
                                dataType="json"
                                href="%{listurl}"
                                pager="true"
                                gridModel="gridModel"
                                rowList="10,15,20"
                                rowNum="10"
                                autowidth="true"
                                rownumbers="true"
                                onCompleteTopics="completetopics"
                                rowTotal="false"
                                viewrecords="true"
                                onErrorTopics="anyerrors"
                                shrinkToFit="false"
                                >
                                <sjg:gridColumn name="id" index="u.id" title="Trip Id"  sortable="false" width="250"/>
                                <sjg:gridColumn name="customer" index="u.customer.firstname" title="Customer"  sortable="false" width="250"/>
                                <sjg:gridColumn name="driver" index="u.driver.firstname" title="Driver"  sortable="false" width="250"/>
                                <sjg:gridColumn name="vehicle" index="u.vehicle.description" title="Vehicle"  sortable="false" width="250"/>
                                <sjg:gridColumn name="source" index="u.source" title="Source"  sortable="false" width="250"/>
                                <sjg:gridColumn name="destination" index="u.destination" title="Destination"  sortable="false" width="250"/>
                                <sjg:gridColumn name="startedTime" index="u.startedTime" title="Started Time"  sortable="false" width="250"/>
                                <sjg:gridColumn name="endTime" index="u.endTime" title="End Time"  sortable="false" width="250"/>
                                <sjg:gridColumn name="fare" index="u.fare" title="Fare"  sortable="false" width="250"/>
                                <sjg:gridColumn name="status" index="u.status.description" title="Status"  sortable="false" width="300"/>
                                <sjg:gridColumn name="createdtime" index="u.createdtime" title="Created Time"  sortable="false" width="350"/> 

                            </sjg:grid> 
                        </div>
                    </div>
                    <!-- end: PAGE CONTENT-->
                </div>
            </div>
            <!-- end: PAGE -->
        </div>
        <!-- end: MAIN CONTAINER -->
        <!-- start: FOOTER -->
        <jsp:include page="/footer.jsp"/>
        <!-- end: FOOTER -->

        <!-- end: BODY -->
    </body>
</html>

