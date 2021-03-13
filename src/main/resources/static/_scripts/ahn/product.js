
$('.START_DATE').datepicker({
    format: "yyyy-mm-dd"
});

$('.END_DATE').datepicker({
    format: "yyyy-mm-dd"
});
var bSearchToggle = false;
$(".btnSearchToggle").unbind('click').click(function(){
    if (!bSearchToggle) {
        $(".btnSearchToggle").text('展开');
        $(".divSearch").toggle("fast");
        bSearchToggle = true;
    } else {
        $(".btnSearchToggle").text('收缩');
        $(".divSearch").toggle("fast");
        bSearchToggle = false;
    }
});


$(".btnSearch").unbind('click').click(function(){
    lfn_Search(g_currentPage);
});

var lfn_Search = function(g_currentPage) {
    //LIST_ATTRIBUTE ATTRIBUTE
    var params = {
        PRODUCT_NAME: $.trim($(".divSearch  .PRODUCT_NAME").val())
        ,USE_YN: $(".divSearch .USE_YN").val()
        ,ATTRIBUTE: $(".divSearch .ATTRIBUTE").val() === "00" ? ($(".LIST_ATTRIBUTE").val() === "" ? "" : $(".LIST_ATTRIBUTE").val()) : $(".divSearch .ATTRIBUTE").val()
        ,PAGE_NUM: g_currentPage
        ,PAGE_SIZE: $(".divSearch .PAGE_SIZE").val()
    }

    new AjaxRequest({
        url: "/api/product/list",
        contentType: "application/json",
        params: JSON.stringify(params),
        callBack: function(res){
            if(res.status === 0) {
                $(".LIST_ATTRIBUTE").val("");
                var productData = res.data.list;
                var sbHTML = new StringBuffer();
                $(".productTable tbody").html('');
                for(var i = 0; typeof productData != 'undefined' && i < productData.length; i++) {
                    sbHTML.append("<tr class='tr01'>");
                    sbHTML.append("     <input type='hidden' value='" + productData[i].PRODUCT_SEQ + "' class='PRODUCT_SEQ' />");
                    sbHTML.append("     <td>" + productData[i].RN + "</td>");
                    sbHTML.append("     <td>" + productData[i].PRODUCT_NAME + "</td>");
                    if(productData[i].MAIN_IMAGE !== "") {
                        sbHTML.append("     <td><img width='100' height='100' src='" + productData[i].MAIN_IMAGE + "' alt='111'></td>");
                    } else {
                        sbHTML.append("     <td>-</td>");
                    }
                    if(productData[i].USE_YN === "1") {
                        sbHTML.append("     <td>上线</td>");
                    } else if(productData[i].USE_YN === "0") {
                        sbHTML.append("     <td>下线</td>");
                    } else {
                        sbHTML.append("     <td>未知</td>");
                    }
                    sbHTML.append("     <td>" + productData[i].COUNT_SPEC + "</td>");
                    sbHTML.append("     <td>" + productData[i].ATTRIBUTE_NAME + "</td>");
                    sbHTML.append("     <td><a href='#this' class='btn02 btnDetail'>查看</a>");
                    sbHTML.append("      </td>");
                    sbHTML.append("</tr>");
                }
                $(".productTable tbody").html(sbHTML.toString());
                lfn_Pagination(res);
            } else {
                swal(res.msg, "", "error");
            }
        }
    });

}

lfn_Search(g_currentPage);

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

$(document).on('click', '.btnDetail', function(){
     var PRODUCT_SEQ = $(this).parents("tr").find(".PRODUCT_SEQ").val();
     window.location.href = "/backend/product/edit/" + PRODUCT_SEQ;
});