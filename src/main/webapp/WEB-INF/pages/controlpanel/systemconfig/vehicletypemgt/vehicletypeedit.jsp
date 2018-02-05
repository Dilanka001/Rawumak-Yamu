<%-- 
    Document   : vehicletypeedit
    Created on : Feb 5, 2018, 9:56:00 AM
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
        <title>Update Vehicle Type</title> 
        <script type="text/javascript">
            function editVehicleType(keyval) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/FindVehicleType.action',
                    data: {id: keyval},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {
                        $('#amessageedit').empty();
                        var msg = data.message;
                        if (msg) {
                            $('#descriptionEdit').val("");
                            $('#descriptionEdit').attr('readOnly', false);
                            $("#descriptionEdit").css("color", "black");
                            $('#priceKmEdit').val("");
                            $('#priceMinEdit').val("");
                            $('#baseFareEdit').val("");
                            $('#commissionEdit').val("");
                            $('#personCapacityEdit').val("");
                            $('#statusEdit').val("");
                            $('#divmsg').text("");
                        }
                        else {
                            $('#descriptionEdit').attr('readOnly', true);
                            $("#descriptionEdit").css("color", "#858585");
                            $('#descriptionEdit').val(data.description);
                            $('#priceKmEdit').val(data.priceKm);
                            $('#priceMinEdit').val(data.priceMin);
                            $('#baseFareEdit').val(data.baseFare);
                            $('#commissionEdit').val(data.commission);
                            $('#personCapacityEdit').val(data.personCapacity);
                            $('#statusEdit').val(data.status);
                        }
                    },
                    error: function(data) {
                        window.location = "${pageContext.request.contextPath}/LogoutUserLogin.action?";
                    }
                });
            }

            function cancelData() {
                var id = $('#idEdit').val();
                editVehicleType(id);
            }

        </script>
    </head>
    <body>
        <s:div id="amessageedit">
            <s:actionerror theme="jquery"/>
            <s:actionmessage theme="jquery"/>
        </s:div>
        <s:form id="vehicletypeedit" method="post" action="VehicleType" theme="simple" cssClass="form" >
            <div class="row row_popup">

                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Vehicle Type</label>
                        <s:hidden id="idEdit" name="id" ></s:hidden>
                        <s:textfield value="%{description}" cssClass="form-control" name="description" id="descriptionEdit" maxLength="30" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))"/>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Price Per Km </label>
                        <s:textfield value="%{priceKm}" cssClass="form-control" name="priceKm" id="priceKmEdit" maxLength="6" onkeyup="$(this).val($(this).val().replace(/[^0-9]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^0-9/g,''))"/>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Price Per Min </label>
                        <s:textfield value="%{priceMin}" cssClass="form-control" name="priceMin" id="priceMinEdit" maxLength="6" onkeyup="$(this).val($(this).val().replace(/[^0-9]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^0-9/g,''))"/>
                    </div>
                </div>
            </div>  
            <div class="row row_popup">

                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Base Fare</label>
                        <s:textfield value="%{baseFare}" cssClass="form-control" name="baseFare" id="baseFareEdit" maxLength="30" onkeyup="$(this).val($(this).val().replace(/[^0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^0-9 ]/g,''))"/>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Commission</label>
                        <s:textfield value="%{commission}" cssClass="form-control" name="commission" id="commissionEdit" maxLength="30" onkeyup="$(this).val($(this).val().replace(/[^0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^0-9 ]/g,''))"/>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Person Capacity</label>
                        <s:textfield value="%{personCapacity}" cssClass="form-control" name="personCapacity" id="personCapacityEdit" maxLength="30" onkeyup="$(this).val($(this).val().replace(/[^0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^0-9 ]/g,''))"/>
                    </div>
                </div>
            </div>  
            <div class="row row_popup">

                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Status</label>
                        <s:select value="%{status}" cssClass="form-control" id="statusEdit" list="%{statusList}"  name="status" headerKey=""  headerValue="--Select Status--" listKey="statuscode" listValue="description"/>
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
                <div class="col-sm-3 text-right">
                    <div class="form-group" style=" margin-left: 10px;margin-right: 0px;">
                        <sj:submit 
                            button="true" 
                            value="Reset" 
                            onClick="cancelData()"
                            cssClass="btn btn-default btn-sm"
                            />                          
                    </div>
                    <div class="form-group" style=" margin-left: 0px;margin-right: 10px;">                                               
                        <s:url action="UpdateVehicleType" var="updateturl"/>
                        <sj:submit
                            button="true"
                            value="Update"
                            href="%{updateturl}"
                            targets="amessageedit"
                            id="updateButton"
                            cssClass="btn btn-sm active" 
                            cssStyle="background-color: #ada9a9"
                            />  
                    </div>
                </div>
            </div>
        </s:form>
    </body>
</html>

