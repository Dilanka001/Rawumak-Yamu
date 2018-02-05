<%-- 
    Document   : vehicletypeinsert
    Created on : Feb 5, 2018, 9:56:14 AM
    Author     : chanuka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib  uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resouces/css/common/common_popup.css">
        <title>Insert Vehicle Type</title> 
        <script type="text/javascript">
            $.subscribe('resetAddButton', function(event, data) {
                $('#amessage').empty();
                $('#taskCode').val("");
                $('#description').val("");
                $('#status').val("");
            });
        </script>
    </head>
    <body>
        <s:div id="amessage">
            <s:actionerror theme="jquery"/>
            <s:actionmessage theme="jquery"/>
        </s:div>
        <s:form id="vehicleTypeadd" method="post" action="VehicleType" theme="simple" cssClass="form" >
            <div class="row row_popup">

                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Vehicle Type</label>
                        <s:textfield value="%{description}" cssClass="form-control" name="description" id="description" maxLength="30" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))"/>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Price Per Km </label>
                        <s:textfield value="%{priceKm}" cssClass="form-control" name="priceKm" id="priceKm" maxLength="6" onkeyup="$(this).val($(this).val().replace(/[^0-9]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^0-9/g,''))"/>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Price Per Min </label>
                        <s:textfield value="%{priceMin}" cssClass="form-control" name="priceMin" id="priceMin" maxLength="6" onkeyup="$(this).val($(this).val().replace(/[^0-9]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^0-9/g,''))"/>
                    </div>
                </div>
            </div>  
            <div class="row row_popup">

                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Base Fare</label>
                        <s:textfield value="%{baseFare}" cssClass="form-control" name="baseFare" id="baseFare" maxLength="30" onkeyup="$(this).val($(this).val().replace(/[^0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^0-9 ]/g,''))"/>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Commission</label>
                        <s:textfield value="%{commission}" cssClass="form-control" name="commission" id="commission" maxLength="30" onkeyup="$(this).val($(this).val().replace(/[^0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^0-9 ]/g,''))"/>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Person Capacity</label>
                        <s:textfield value="%{personCapacity}" cssClass="form-control" name="personCapacity" id="personCapacity" maxLength="30" onkeyup="$(this).val($(this).val().replace(/[^0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^0-9 ]/g,''))"/>
                    </div>
                </div>
            </div>  
            <div class="row row_popup">
              
                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Status</label>
                        <s:select value="%{status}" cssClass="form-control" id="status" list="%{statusList}"  name="status" headerKey=""  headerValue="--Select Status--" listKey="statuscode" listValue="description"/>
                    </div>
                </div>
            </div>  
                    
            <div class="row row_popup">
                <div class="horizontal_line_popup"></div>
            </div>
            <div class="row row_popup form-inline">
                <div class="col-sm-9">
                    <div class="form-group">
                        <span class="mandatoryfield">Mandatory fields are marked with *</span>
                    </div>
                </div>
                <div class="col-sm-3  text-right">
                    <div class="form-group" style=" margin-left: 10px;margin-right: 0px;">
                        <sj:submit 
                            button="true" 
                            value="Reset" 
                            name="reset" 
                            cssClass="btn btn-default btn-sm"
                            onClickTopics="resetAddButton"
                            />   
                    </div>
                    <div class="form-group" style=" margin-left: 0px;margin-right: 10px;">
                        <s:url action="AddVehicleType" var="inserturl"/>
                        <sj:submit
                            button="true"
                            value="Add"
                            href="%{inserturl}"
                            onClickTopics=""
                            targets="amessage"
                            id="addbtn"
                            cssClass="btn btn-sm active" 
                            cssStyle="background-color: #ada9a9"                          
                            />                        
                    </div>
                </div>
            </div>
        </s:form>
    </body>
</html>
