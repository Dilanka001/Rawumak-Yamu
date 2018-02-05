<%-- 
    Document   : driveredit
    Created on : Feb 5, 2018, 4:32:24 PM
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
        <title>Update Driver</title> 
        <script type="text/javascript">
            function editDriver(keyval) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/FindDriver.action',
                    data: {id: keyval},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {
                        $('#amessageedit').empty();
                        var msg = data.message;
                        if (msg) {
                            $('#nicEdit').val("");
                            $('#nicEdit').attr('readOnly', false);
                            $("#nicEdit").css("color", "black");
                            $('#firstNameEdit').val("");
                            $('#lastNameEdit').val("");
                            $('#addressEdit').val("");
                            $('#licenceEdit').val("");
                            $('#emailEdit').val("");
                            $('#accountEdit').val("");
                            $('#statusEdit').val("");
                            $('#divmsg').text("");
                        }
                        else {
                            $('#nicEdit').attr('readOnly', true);
                            $("#nicEdit").css("color", "#858585");
                            $('#nicEdit').val(data.nic);
                            $('#firstNameEdit').val(data.firstName);
                            $('#lastNameEdit').val(data.lastName);
                            $('#addressEdit').val(data.address);
                            $('#licenceEdit').val(data.licence);
                            $('#emailEdit').val(data.email);
                            $('#accountEdit').val(data.account);
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
                editDriver(id);
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
                        <span style="color: red">*</span><label>First Name</label>
                        <s:hidden id="idEdit" name="id" ></s:hidden>
                        <s:textfield value="%{firstName}" cssClass="form-control" name="firstName" id="firstNameEdit" maxLength="64" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z ]/g,''))"/>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Last Name</label>
                        <s:textfield value="%{lastName}" cssClass="form-control" name="lastName" id="lastNameEdit" maxLength="6" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z ]/g,''))"/>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Address </label>
                        <s:textfield value="%{address}" cssClass="form-control" name="address" id="addressEdit" maxLength="255" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9/g,''))"/>
                    </div>
                </div>
            </div>  
            <div class="row row_popup">

                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Nic</label>
                        <s:textfield value="%{nic}" cssClass="form-control" name="nic" id="nicEdit" maxLength="16" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))"/>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>License</label>
                        <s:textfield value="%{licence}" cssClass="form-control" name="licence" id="licenceEdit" maxLength="20" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))"/>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Email</label>
                        <s:textfield value="%{email}" cssClass="form-control" name="email" id="emailEdit" maxLength="20" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))"/>
                    </div>
                </div>
            </div>  
            <div class="row row_popup">
                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Account</label>
                        <s:textfield value="%{account}" cssClass="form-control" name="account" id="accountEdit" maxLength="20" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))"/>
                    </div>
                </div>
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
                        <s:url action="UpdateDriver" var="updateturl"/>
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

