<%-- 
    Document   : vehicletypemgt
    Created on : Feb 5, 2018, 12:12:05 AM
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

                var descriptionSearch = $('#descriptionSearch').val();
                var statusSearch = $('#statusSearch').val();
                var personCapacitySearch = $('#personCapacitySearch').val();
                var baseFareSearch = $('#baseFareSearch').val();
                var commissionSearch = $('#commissionSearch').val();
                var pricekmSearch = $('#pricekmSearch').val();
                var priceMinSearch = $('#priceMinSearch').val();

                $("#gridtable").jqGrid('setGridParam', {
                    postData: {
                        descriptionSearch: descriptionSearch,
                        statusSearch: statusSearch,
                        personCapacitySearch: personCapacitySearch,
                        baseFareSearch: baseFareSearch,
                        commissionSearch: commissionSearch,
                        pricekmSearch: pricekmSearch,
                        priceMinSearch: priceMinSearch,
                        search: true
                    }
                });

                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");
            });

            function searchVehicle(param) {
                $('#message').empty();

                var descriptionSearch = $('#descriptionSearch').val();
                var statusSearch = $('#statusSearch').val();
                var personCapacitySearch = $('#personCapacitySearch').val();
                var baseFareSearch = $('#baseFareSearch').val();
                var commissionSearch = $('#commissionSearch').val();
                var pricekmSearch = $('#pricekmSearch').val();
                var priceMinSearch = $('#priceMinSearch').val();

                $("#gridtable").jqGrid('setGridParam', {
                    postData: {
                        descriptionSearch: descriptionSearch,
                        statusSearch: statusSearch,
                        personCapacitySearch: personCapacitySearch,
                        baseFareSearch: baseFareSearch,
                        commissionSearch: commissionSearch,
                        pricekmSearch: pricekmSearch,
                        priceMinSearch: priceMinSearch,
                        search: param
                    }
                });

                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");
            }

            $.subscribe('anyerrors', function(event, data) {
                window.location = "${pageContext.request.contextPath}/LogoutUserLogin.action?";
            });

            function editformatter(cellvalue, options, rowObject) {
                return "<a href='#' title='Edit' onClick='javascript:editVehicleInit(&#34;" + cellvalue + "&#34;)'><img class='ui-icon ui-icon-pencil' style='display: block;margin-left: auto;margin-right: auto;'/></a>";
            }

            function deleteformatter(cellvalue, options, rowObject) {
                return "<a href='#/' title='Delete' onClick='javascript:deleteVehicleInit(&#34;" + cellvalue + "&#34;)'><img class='ui-icon ui-icon-trash' style='display: block;margin-left: auto;margin-right: auto;'/></a>";
            }

            function editVehicleInit(keyval) {
                $("#updatedialog").data('id', keyval).dialog('open');
            }

            $.subscribe('openviewtasktopage', function(event, data) {
                var $led = $("#updatedialog");
                $led.html("Loading..");
                $led.load("DetailVehicleType.action?id=" + $led.data('id'));
            });

            function deleteVehicleInit(keyval) {
                $('#divmsg').empty();

                $("#deletedialog").data('keyval', keyval).dialog('open');
                $("#deletedialog").html('Are you sure you want to delete Vehicle Type ' + keyval + ' ?');
                return false;
            }

            function deleteVehicle(keyval) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/DeleteVehicleType.action',
                    data: {id: keyval},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {
                        $("#deletesuccdialog").dialog('open');
                        $("#deletesuccdialog").html(data.message);
                        resetFieldData();
                    },
                    error: function(data) {
                        window.location = "${pageContext.request.contextPath}/LogoutUserLogin.action?";
                    }
                });
            }

            function resetAllData() {
                $('#descriptionSearch').val("");
                $('#statusSearch').val("");
                $('#personCapacitySearch').val("");
                $('#baseFareSearch').val("");
                $('#commissionSearch').val("");
                $('#pricekmSearch').val("");
                $('#priceMinSearch').val("");

                $("#gridtable").jqGrid('setGridParam', {
                    postData: {
                        descriptionSearch: '',
                        statusSearch: '',
                        personCapacitySearch: '',
                        baseFareSearch: '',
                        commissionSearch: '',
                        pricekmSearch: '',
                        priceMinSearch: '',
                        search: false
                    }
                });
                jQuery("#gridtable").trigger("reloadGrid");
            }

            function resetFieldData() {

                $('#description').val("");
                $('#status').val("");
                $('#personCapacity').val("");
                $('#baseFare').val("");
                $('#commission').val("");
                $('#pricekm').val("");
                $('#priceMin').val("");

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
                                <s:form cssClass="form" id="vehiclesearch" method="post" action="Vehicle" theme="simple" >
                                    <div class="row row_1">
                                        <div class="col-sm-3">
                                            <div class="form-group">
                                                <label>Vehicle Type</label>
                                                <s:textfield cssClass="form-control" name="descriptionSearch" id="descriptionSearch" maxLength="64" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))"/>
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
                                                <label>Person Capacity </label>
                                                <s:textfield cssClass="form-control" name="personCapacitySearch" id="personCapacitySearch" maxLength="6" onkeyup="$(this).val($(this).val().replace(/[^0-9]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^0-9]/g,''))"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="form-group">
                                                <label>Base Fare </label>
                                                <s:textfield cssClass="form-control" name="baseFareSearch" id="baseFareSearch" maxLength="6" onkeyup="$(this).val($(this).val().replace(/[^0-9]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^0-9]/g,''))"/>
                                            </div>
                                        </div>


                                    </div>
                                    <div class="row row_1">
                                        <div class="col-sm-3">
                                            <div class="form-group">
                                                <label>Commission</label>
                                                <s:textfield cssClass="form-control" name="commissionSearch" id="commissionSearch" maxLength="64" onkeyup="$(this).val($(this).val().replace(/[^0-9]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^0-9]/g,''))"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="form-group form-inline">
                                                <label >Price Per KM</label>
                                                <s:textfield cssClass="form-control" name="pricekmSearch" id="pricekmSearch" maxLength="64" onkeyup="$(this).val($(this).val().replace(/[^0-9]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^0-9]/g,''))"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="form-group">
                                                <label>Price Per Min </label>
                                                <s:textfield cssClass="form-control" name="priceMinSearch" id="priceMinSearch" maxLength="6" onkeyup="$(this).val($(this).val().replace(/[^0-9]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^0-9]/g,''))"/>
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
                                                onclick="searchVehicle(true)"  
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
                                    <div class="col-sm-3  text-right">
                                        <div class="form-group">                                               
                                            <s:url var="addurl" action="ViewPopupVehicleType"/>                                                    
                                            <sj:submit 
                                                openDialog="remotedialog"
                                                button="true"
                                                href="%{addurl}"
                                                buttonIcon="ui-icon-newwin"
                                                disabled="#vadd"
                                                value="Add New Vehicle Type"
                                                id="addButton"
                                                cssClass="form-control btn_normal"
                                                cssStyle="border-radius: 12px;background-color:#969595;color:white;"
                                                />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Start add dialog box -->
                            <sj:dialog                                     
                                id="remotedialog"                                 
                                autoOpen="false"                              
                                modal="true" 
                                title="Add Vehicle Type"                            
                                loadingText="Loading .."                            
                                position="center"
                                width="900"
                                height="400"
                                dialogClass="dialogclass"
                                /> 

                            <!-- Start update dialog box -->
                            <sj:dialog                                     
                                id="updatedialog"                                 
                                autoOpen="false" 
                                modal="true" 
                                position="center"
                                title="Update Vehicle"
                                onOpenTopics="openviewtasktopage" 
                                loadingText="Loading .."
                                width="900"
                                height="400"
                                dialogClass= "dialogclass"
                                />
                            <!-- Start delete confirm dialog box -->
                            <sj:dialog 
                                id="deletedialog" 
                                buttons="{ 
                                'OK':function() { deleteVehicle($(this).data('keyval'));$( this ).dialog( 'close' ); },
                                'Cancel':function() { $( this ).dialog( 'close' );} 
                                }" 
                                autoOpen="false" 
                                modal="true" 
                                title="Delete Vehicle Type"                            
                                />
                            <!-- Start delete process dialog box -->
                            <sj:dialog 
                                id="deletesuccdialog" 
                                buttons="{
                                'OK':function() { $( this ).dialog( 'close' );}
                                }"  
                                autoOpen="false" 
                                modal="true" 
                                title="Deleting Process." 
                                />
                            <!-- Start delete error dialog box -->
                            <sj:dialog 
                                id="deleteerrordialog" 
                                buttons="{
                                'OK':function() { $( this ).dialog( 'close' );}                                    
                                }" 
                                autoOpen="false" 
                                modal="true" 
                                title="Delete error."
                                />
                        </div>
                        <div id="tablediv">
                            <s:url var="listurl" action="ListVehicleType"/>
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
                                <sjg:gridColumn name="id" index="u.id" title="Edit" width="50" align="center" formatter="editformatter" sortable="false" hidden="#vupdatelink" frozen="true"/>
                                <sjg:gridColumn name="id" index="u.id" title="Delete" width="50" align="center" formatter="deleteformatter" sortable="false" hidden="#vdelete" frozen="true"/>  
                                <sjg:gridColumn name="description" index="u.description" title="Vehicle Type"  sortable="false" width="250"/>
                                <sjg:gridColumn name="priceKm" index="u.priceKm" title="Price Per Km"  sortable="false" width="250"/>
                                <sjg:gridColumn name="priceMin" index="u.priceMin" title="Price Per Min"  sortable="false" width="250"/>
                                <sjg:gridColumn name="baseFare" index="u.baseFare" title="Base Fare"  sortable="false" width="250"/>
                                <sjg:gridColumn name="commission" index="u.commission" title="Commission"  sortable="false" width="250"/>
                                <sjg:gridColumn name="personCapacity" index="u.personCapacity" title="Person Capacity"  sortable="false" width="250"/>
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
