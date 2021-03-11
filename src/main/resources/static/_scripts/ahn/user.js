// $(".btn01").unbind('click').click(function(){
//     $(".table01").loading();
// });
$('.START_DATE').datepicker({
    format: "yyyy-mm-dd"
});

$('.END_DATE').datepicker({
    format: "yyyy-mm-dd"
});
var bSearchToggle = false;
$(".btnSearchToggle").unbind('click').click(function(){
    if (!bSearchToggle) {
        $(".btnSearchToggle").text('收缩');
        $(".divSearch").toggle("fast");
        bSearchToggle = true;
    } else {
        $(".btnSearchToggle").text('展开');
        $(".divSearch").toggle("fast");
        bSearchToggle = false;
    }
});


$(".btnSearch").unbind('click').click(function(){
    lfn_Search(g_currentPage);
});
var lfn_Search = function(g_currentPage) {
    var params = {
        START_DATE : $(".START_DATE").val()
        ,END_DATE : $(".END_DATE").val()
        ,PAGE_NUM: g_currentPage
        ,PAGE_SIZE: 15
    }

    new AjaxRequest({
        url: "/api/user/list",
        params: params,
        callBack: function(res){
            if(res.status === 0) {
                var userData = res.data.list;
                var sbHTML = new StringBuffer();
                $(".userTable tbody").html('');
                for(var i = 0; typeof userData != 'undefined' && i < userData.length; i++) {
                    sbHTML.append("<tr class='tr01'>");
                    sbHTML.append("     <input type='hidden' value='" + userData[i].USER_SEQ + "' class='USER_SEQ' />");
                    sbHTML.append("     <td>" + userData[i].RN + "</td>");
                    sbHTML.append("     <td>" + userData[i].COMPANY + "</td>");
                    sbHTML.append("     <td>" + userData[i].USERNAME + "</td>");
                    sbHTML.append("     <td>" + userData[i].COMPANY_TYPE + "</td>");
                    sbHTML.append("     <td>" + (userData[i].USE_YN === '1' ? '<i class="bi bi-emoji-smile" style="font-size:20px;"></i>' : '<i class="bi bi-emoji-frown-fill" style="font-size:20px;"></i>') + "</td>");
                    sbHTML.append("     <td>" + userData[i].DEVICE_SERIAL + "</td>");
                    sbHTML.append("     <td>" + userData[i].DEVICE_MODEL + "</td>");
                    sbHTML.append("     <td>" + userData[i].DEVICE_COLOR + "</td>");
                    sbHTML.append("     <td><a href='#this' class='btn02 btnDetail'>查看</a>");
                    if(parseInt(userData[i].ORDER_COUNT) >= 1) {
                        sbHTML.append("     <a href='#this' class='btn01 btnOrderByUserSeq'>订单列表</a>");
                    }
                    if(userData[i].USE_YN === "1") {
                        sbHTML.append("     <a href='#this' class='btn03 btnNoActivy'>取消激活</a> ");
                    } else {
                        sbHTML.append("     <a href='#this' class='btn01 btnActivy'>激活</a> ");
                    }
                    sbHTML.append("      </td>");
                    sbHTML.append("</tr>");
                }
                $(".userTable tbody").html(sbHTML.toString());
                lfn_Pagination(res);
            } else {
                swal(res.msg, "", "error");
            }
        }
    });

    lfn_Province();
    lfn_City();
    lfn_District();
}



var UserObject = {
    USER_SEQ: $(".detail_form #USER_SEQ"),
    USERNAME : $(".detail_form #USERNAME"),
    PASSWORD : $(".detail_form #PASSWORD"),
    CHECK_PASSWORD : $(".detail_form #CHECK_PASSWORD"),
    REALNAME : $(".detail_form #REALNAME"),
    COMPANY : $(".detail_form #COMPANY"),
    COMPANY_TYPE : $(".detail_form #COMPANY_TYPE"),
    PHONE : $(".detail_form #PHONE"),
    EMAIL : $(".detail_form #EMAIL"),
    SEX : $(".detail_form #SEX"),
    BIRTHDAY : $(".detail_form #BIRTHDAY"),
    PROVINCE_CODE : $(".detail_form #PROVINCE_CODE"),
    CITY_CODE : $(".detail_form #CITY_CODE"),
    DISTRICT_CODE : $(".detail_form #DISTRICT_CODE"),
}

var lfn_Detail_init = function(data) {
    if (data !== undefined) {
        UserObject.USER_SEQ.val(data.USER_SEQ);
        UserObject.USERNAME.val(data.USERNAME);
        UserObject.REALNAME.val(data.REALNAME);
        UserObject.COMPANY.val(data.COMPANY);
        UserObject.COMPANY_TYPE.val(data.COMPANY_TYPE);
        UserObject.PHONE.val(data.PHONE);
        UserObject.EMAIL.val(data.EMAIL);
        UserObject.SEX.val(data.SEX);
        UserObject.BIRTHDAY.val(data.BIRTHDAY);
        UserObject.PROVINCE_CODE.val(data.PROVINCE_CODE);
        UserObject.CITY_CODE.val(data.CITY_CODE);
        UserObject.DISTRICT_CODE.val(data.DISTRICT_CODE);
    } else {
        UserObject.USER_SEQ.val('');
        UserObject.USERNAME.val('');
        UserObject.REALNAME.val('');
        UserObject.COMPANY.val('');
        UserObject.COMPANY_TYPE.val('');
        UserObject.PHONE.val('');
        UserObject.EMAIL.val('');
        UserObject.SEX.val('');
        UserObject.BIRTHDAY.val('');
        UserObject.PROVINCE_CODE.val('');
        UserObject.CITY_CODE.val('');
        UserObject.DISTRICT_CODE.val('');
    }

    $("#PROVINCE_CODE").unbind('change').change(function(){
        var params = { CODE_ID: this.value }

        new AjaxRequest({
            url: '/api/common/listByOPTION01',
            params: params,
            callBack: function(res) {
                if (res.status === 0) {
                    var sbOPTION = new StringBuffer();
                    $("#CITY_CODE").html('');
                    $("#DISTRICT_CODE").html('');
                    sbOPTION.append("<option></option>");
                    for (var i = 0; i < res.data.length; i++) {
                        sbOPTION.append("<option value='" + res.data[i].CODE_TYPE +"'>" + res.data[i].CODE_NAME + "</option>")
                    }
                    $("#CITY_CODE").html(sbOPTION.toString());
                }
            }
        });
    });

    $("#CITY_CODE").unbind('change').change(function(){
        var params = { CODE_ID: this.value }

        new AjaxRequest({
            url: '/api/common/listByOPTION01',
            params: params,
            callBack: function(res) {
                if (res.status === 0) {
                    var sbOPTION = new StringBuffer();
                    $("#DISTRICT_CODE").html('');
                    sbOPTION.append("<option></option>");
                    for (var i = 0; i < res.data.length; i++) {
                        sbOPTION.append("<option value='" + res.data[i].CODE_TYPE +"'>" + res.data[i].CODE_NAME + "</option>")
                    }
                    $("#DISTRICT_CODE").html(sbOPTION.toString());
                }
            }
        });
    });

}

//Detail Save
$(document).on('click', '.btnSave', function(e){
    var params = {
        USER_SEQ: $.trim(UserObject.USER_SEQ.val())
        ,USERNAME: $.trim(UserObject.USERNAME.val())
        ,PASSWORD: $.trim(UserObject.PASSWORD.val())
        ,REALNAME: $.trim(UserObject.REALNAME.val())
        ,COMPANY: $.trim(UserObject.COMPANY.val())
        ,COMPANY_TYPE: $.trim(UserObject.COMPANY_TYPE.val())
        ,PHONE: $.trim(UserObject.PHONE.val())
        ,EMAIL: $.trim(UserObject.EMAIL.val())
        ,SEX: $.trim(UserObject.SEX.val())
        ,BIRTHDAY: $.trim(UserObject.BIRTHDAY.val())
        ,PROVINCE_CODE: $.trim(UserObject.PROVINCE_CODE.val())
        ,CITY_CODE: $.trim(UserObject.CITY_CODE.val())
        ,DISTRICT_CODE: $.trim(UserObject.DISTRICT_CODE.val())
    };

    new AjaxRequest({
        url: '/api/user/edit'
        ,params: params
        ,callBack: function(res) {
            if (res.status === 0) {
                swal("修改成功！", "", "success").then(function(){
                    $(".detail_wrap").bPopup().close();
                    lfn_Search(g_currentPage);
                });
            } else {
                swal(res.msg, "", "error");
            }
        }
    });
});


//Detail
$(document).on('click', '.btnDetail', function(e) {
    var params = {
        USER_SEQ:  $(this).parents('.tr01').find('.USER_SEQ').val()
    };

    new AjaxRequest({
        url: '/api/user/info',
        params: params,
        callBack: function(res) {
            if(res.status === 0) {
                $(".detail_wrap").bPopup({
                    modalClose: false,
                    closeClass: 'btnClose',
                    onOpen: function() {
                        console.log(res.data);
                        lfn_Detail_init(res.data);
                    }
                });
            } else {
                swal(res.msg, "", "error");
            }
        }
    });
})

//会员激活
$(document).on('click', '.btnActivy', function(e) {
    var params = {
        USER_SEQ : $(this).parents('.tr01').find('.USER_SEQ').val(),
        USE_YN : '1'
    }

    new AjaxRequest({
        url: '/api/user/edit',
        params: params,
        callBack: function(res) {
            if(res.status === 0) {
                swal("状态更新成功！", "", "success").then(function(){
                    lfn_Search(g_currentPage);
                });
            } else {
                swal(res.msg, "", "error");
            }
        }
    });
});


//取消激活
$(document).on('click', '.btnNoActivy', function(e) {
    var params = {
        USER_SEQ : $(this).parents('.tr01').find('.USER_SEQ').val(),
        USE_YN : '0'
    }

    new AjaxRequest({
        url: '/api/user/edit',
        params: params,
        callBack: function(res) {
            if(res.status === 0) {
                swal("状态更新成功！", "", "success").then(function(){
                    lfn_Search(g_currentPage);
                });
            } else {
                swal(res.msg, "", "error");
            }
        }
    });
});

//订单详情popup
$(document).on('click', '.btnOrderByUserSeq', function(){
    var params = {
        USER_SEQ:  $(this).parents('.tr01').find('.USER_SEQ').val()
    };

    new AjaxRequest({
        url: '/api/user/order_list_by_user',
        params: params,
        callBack: function(res) {
            if(res.status === 0) {
                $(".OrderInfoByUserPopup").bPopup({
                    modalClose: false,
                    closeClass: 'btnClose',
                    onOpen: function() {
                        console.log(res.data);
                    }
                });
            } else {
                swal(res.msg, "", "error");
            }
        }
    });
});

var lfn_Pagination = function(res) {
    var sbPaging = new StringBuffer();
    $(".page").html('');
    if (res.data) {
        sbPaging.append("<ul>");
        res.data.navigatepageNums.map(function(currentValue, index, arr) {
            if(res.data.pageNum === currentValue) {
                sbPaging.append("   <li class='on'><a href='#this' class=''>" + currentValue + "</a> </li>");
            } else {
                sbPaging.append("   <li><a href='#this' class=''>" + currentValue + "</a> </li>");
            }
        });

        sbPaging.append("</ul>");
        $(".page").html(sbPaging.toString());
    }
}

$(document).on('click', '.page ul li a', function(e){
    lfn_Search(e.target.innerText);
});

var lfn_Province = function() {
    var params = {
        CODE_ID : 'PROVINCE_CODE'
    };

    new AjaxRequest({
        url: '/api/common/listByCodeId',
        params: params,
        callBack: function(res){
            if (res.status === 0) {
                var sbOPTION = new StringBuffer();
                $("#PROVINCE_CODE").html('');
                sbOPTION.append("<option value=''>选择省份</option>");
                for (var i = 0; i < res.data.length; i++) {
                    sbOPTION.append("<option value='" + res.data[i].CODE_TYPE +"'>" + res.data[i].CODE_NAME + "</option>")
                }
                $("#PROVINCE_CODE").html(sbOPTION.toString());
            }
        }
    });
};



var lfn_City = function() {
    var params = {
        CODE_ID : 'CITY_CODE'
    };

    new AjaxRequest({
        url: '/api/common/listByCodeId',
        params: params,
        callBack: function(res){
            if (res.status === 0) {
                var sbOPTION = new StringBuffer();
                $("#CITY_CODE").html('');
                sbOPTION.append("<option value=''>选择城市</option>");
                for (var i = 0; i < res.data.length; i++) {
                    sbOPTION.append("<option value='" + res.data[i].CODE_TYPE +"'>" + res.data[i].CODE_NAME + "</option>")
                }
                $("#CITY_CODE").html(sbOPTION.toString());
            }
        }
    });
};

var lfn_District = function() {
    var params = {
        CODE_ID : 'DISTRICT_CODE'
    };

    new AjaxRequest({
        url: '/api/common/listByCodeId',
        params: params,
        callBack: function(res){
            if (res.status === 0) {
                var sbOPTION = new StringBuffer();
                $("#DISTRICT_CODE").html('');
                sbOPTION.append("<option value=''>选择区域</option>");
                for (var i = 0; i < res.data.length; i++) {
                    sbOPTION.append("<option value='" + res.data[i].CODE_TYPE +"'>" + res.data[i].CODE_NAME + "</option>")
                }
                $("#DISTRICT_CODE").html(sbOPTION.toString());
            }
        }
    });
};





lfn_Search(g_currentPage);