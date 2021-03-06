
// new AjaxRequest({
//     type: "get",
//     url: "https://5ae979d7531a580014142797.mockapi.io/api/v1/records",
//     param: "",
//     isShowLoader: true,
//     dataType: "",
//     callBack: function(res){
//         console.log(res);
//     }
// });


jQuery.extend({
    ahnAjax: function(option) {
        var accessToken = localStorage.getItem('token');
        // if (accessToken == null || accessToken === "" || typeof accessToken == 'undefined') {
        //     removeLoading();
        //     swal("请重新登录！").then(function() {
        //         window.location.href = "/login";
        //     });
        //     return;
        // }
        var args = {
            type: option.type || 'POST',
            cache: false,
            dataType: "json", //默认后不显示图片上传中
            data: null,
            contentType: 'application/json',
            // headers : {
            //     "Access-Token" : accessToken
            // },
            beforeSend: function(request) {
                request.setRequestHeader("Access-Token", accessToken);
            },
            dataFilter: function(d) { // 处理返回参数，token 是否过期失效
                // d = JSON.parse(d)
                // if(d.error == 'invalid_token') {
                //     // TODO 这里是token失效的情况  跳登录页面;
                // window.location.href = "/login";
                //     return;
                // }
                // d = JSON.stringify(d)
                // return d;
            },
            error: function(err) {
                console.log(1111111);
                console.log(err)
            },
            complete : function(e) {
                console.log(222222222222);
                removeLoading();
            }
        }

        $.each(option, function(key, value) {
            args[key] = value;
        });

        if(!args.url || args.url === undefined) {
            throw new Error('ajax参数错误！');
        };

        args.url = /^http/.test(args.url) ? args.url : args.url; //是否以http开头


        args.url = /\?/g.test(args.url) ?
            args.url + "&Access-Token=" + accessToken : //TODO  加token
            args.url + "?Access-Token=" + accessToken; //TODO  加token

        $.ajax(args);
    }
})

/**
 *功能：	获取字符串的长度
 *参数：	strVal：	字符串
 *返回：	返回字符串的字节长度
 */
function getReaLength(strVal) {
    var tempStr ;
    tempStr = strVal.replace(/(^\s*)|(\s*$)/g, '');

    return tempStr.replace(/[^\x00-\xff]/g,"**").length;
}

/**
 用途：检查输入字符串是否为空或者全部都是空格
 输入：str
 返回：
 如果全是空返回true,否则返回false
 */
function isNull(str){
    if(str == "" || str == null || str.length == 0) return true;
    var regu = "^[ 　]+$";
    var re = new RegExp(regu);
    return re.test(str);
}


/**
 *功能：	检查参数对象的值是否符合E-Mail格式
 *参数：	str：	参数的字符串
 *返回：	如果通过验证返回true,否则返回false
 */
function isEmail(strEmail) {

    var myReg = /^[_a-zA-Z][_a-zA-Z0-9]*@[_a-z0-9]+\.[a-zA-Z]{2,5}(\.[a-zA-Z]{2,3})?$/;

    var emailReg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;

    if(emailReg.test(strEmail))
        return true;
    return false;

}

/**
 *功能：	检查参数对象的值是否符合E-Mail格式
 *参数：	str：	参数的字符串
 *返回：	如果通过验证返回true,否则返回false
 */
function isEmailNew(strEmail) {
    var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if(reg.test(strEmail)){
        return true;
    }else{
        return false;
    }
}
function checkEmpty(str1) {
    if(typeof str1 == "undefined" || str == "" || str == null) {
        return "";
    } else {
        return str1;
    }
}

function checkEmpty(str, replace) {
    if(typeof str == "undefined" || str == "" || str == null) {
        return replace;
    } else {
        return str;
    }
}

/**
 *规则：	移动: 前3位 134-139  或者 150-159   一共11位
 *		联通: 前3位 130-133  或者 150-159   一共11位
 * 		新增18号段
 *		小灵通: 第一位为0 一共11位
 *	    新增145、147、170、176、177
 *	     这是最新规则
 *功能：	检查参数的电话号码格式是否正确（仅手机号）
 *参数：	str：	字符串
 *返回：	如果通过验证返回true,否则返回false
 */
function isMobile(str){
    //var regu =/(^[1][3][0-9]{9}$)|(^[1][5][0-9]{9}$)|(^[1][8][0-9]{9}$)|(^14[5,7][0-9]{8}$)|(^17[0,6,7][0-9]{8}$)|(^[0][1-9]{1}[0-9]{9}$)/;
    var regu = /^1([3,4,5,6,7,8,9])[0-9]{9}$/;
    var reg = new RegExp(regu);
    if (reg.test(str)) {
        return true;
    }else{
        return false;
    }
}

/**
 *格式要求 (1)国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(2到5位)
 *			/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{2,5}))?$/
 *		(2)手机号
 *			/(^0{0,1}13[0-9]{9}$)|(^0{0,1}15[0-9]{9}$)|(^0{0,1}18[0-9]{9}$)/
 *		(2)小灵通
 *			/^[0][1-9]{1}[0-9]{9}$/
 *功能：	检查参数的电话号码格式是否正确(包含手机)
 *参数：	str：	字符串
 *返回：	如果通过验证返回true,否则返回false
 */
function isPhone(str){

    var regu = /(^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{2,5}))?$)|(^0{0,1}13[0-9]{9}$)|(^0{0,1}15[0-9]{9}$)|(^0{0,1}18[0-9]{9}$)|(^[0][1-9]{1}[0-9]{9}$)/;

    var reg = new RegExp(regu);
    if (reg.test(str)) {
        return true;
    }else{
        return false;
    }
}

/**
 *功能：	检查参数对象的值是否符邮政编码格式
 *参数：	str：		参数的字符串
 *返回：	如果通过验证返回true,否则返回false
 */
function isZip(str){
    //var filter=/^[1-9]\d{5}$/;
    var filter = /^[0-9]{6}$/;
    if(!filter.test(str)) return false;
    return true;
}

/**
 *功能：	判断用户名是否是字母 数字 . _等组成并且6-20位
 *参数：	str：		参数的字符串
 *返回：	如果通过验证返回true,否则返回false
 */
function isUsername(str){

    var filter=/^\s*[.A-Za-z0-9_-]{6,20}\s*$/;

    if(!filter.test(str)) return false;

    return true;

}

/**
 *功能：	判断字符串中是否含有特殊字符
 *参数：	str：		验证的字符串
 *		spChars：	特殊字符
 *返回：	如果通过验证返回true,否则返回false
 */
function isValidCode(str, special){


    var spChars = /[~!@%^&*();\'\"\"?><\[\]{}\|,:\/=+—“”‘]/;



    if(typeof(special) != 'undefined') spChars = special;



    if (spChars.test(str)){

        alert("不能以含有非法字符("+str.match(spChars)+")！");

        return true;

    }

    return false;

}

/**
 *功能：	判断密码是否是字母 数字等组成并且6-20位
 *参数：	str：		参数的字符串
 *返回：	如果通过验证返回true,否则返回false
 */
function isPassword(str){

    var filter = /^([0-9a-zA-Z]){6,20}$/;

    if (!filter.exec(str)) return false;

    return true;

}

/**
 * 保留2位小数
 * @param x
 */
function changeTwoDecimal_f(x) {
    var f_x = parseFloat(x);
    if (isNaN(f_x)) {
        return false;
    }
    var f_x = Math.round(x*100)/100;
    var s_x = f_x.toString();
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0) {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + 2) {
        s_x += '0';
    }
    return s_x;
}

/**
 * 保留int位小数
 * @param x
 */
function changeValue(x, int) {
    var f_x = parseFloat(x);
    if (isNaN(f_x)) {
        return false;
    }
    var f_x = Math.round(x*100)/100;
    var s_x = f_x.toString();
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0) {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + int) {
        s_x += '0';
    }
    return s_x;
}

function changeValueFour(x, int) {
    var f_x = parseFloat(x);
    if (isNaN(f_x)) {
        return false;
    }
    var f_x = Math.round(x*10000)/10000;
    var s_x = f_x.toString();
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0) {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + int) {
        s_x += '0';
    }
    return s_x;
}


/**
 * 校验人名
 *
 * @returns {Boolean}
 */
function validatePerson(person) {
    if (/[0-9~!@%^&*();\'\"\"?><\[\]{}\|,:\/=+—“”‘]/.test(person)) {
        return false;
    }
    return true;
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
function ltrim(str){ //删除左边的空格
    return str.replace(/(^\s*)/g,"");
}
function rtrim(str){ //删除右边的空格
    return str.replace(/(\s*$)/g,"");
}

function validateAge(age){
    //var regu = "^[0-9]*[1-9][0-9]*$";
    var regu = "[0-9]{2,3}";
    var re = new RegExp(regu);
    //alert(re.test(age));
    return re.test(age);
}

/**
 * 开启DIV遮罩层
 */
function divBlockShow(){
    $(".bg_body").css('display','block');
}

/**
 * 隐藏DIV遮罩层
 */
function divBlockHidden(){
    if (typeof EasyDialog_data != 'undefined' && EasyDialog_data && EasyDialog_data.length > 0) {
        return;
    }
    $(".bg_body").css('display','none');
}

//浮点运算精度加法
Number.prototype.add = function(arg){
    var r1,r2,m;
    try{r1=this.toString().split(".")[1].length}catch(e){r1=0}
    try{r2=arg.toString().split(".")[1].length}catch(e){r2=0}
    m=Math.pow(10,Math.max(r1,r2))
    return (this*m+arg*m)/m
}

//浮点运算精度减法
Number.prototype.sub = function (arg){
    return this.add(-arg);
}

//浮点运算精度乘法
Number.prototype.mul = function (arg)
{
    var m=0,s1=this.toString(),s2=arg.toString();
    try{m+=s1.split(".")[1].length}catch(e){}
    try{m+=s2.split(".")[1].length}catch(e){}
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
}

//浮点运算精度除法
Number.prototype.div = function (arg){
    var t1=0,t2=0,r1,r2;
    try{t1=this.toString().split(".")[1].length}catch(e){}
    try{t2=arg.toString().split(".")[1].length}catch(e){}
    with(Math){
        r1=Number(this.toString().replace(".",""))
        r2=Number(arg.toString().replace(".",""))
        return (r1/r2)*pow(10,t2-t1);
    }
}

String.prototype.trim = function() {
    return this.replace(/(^\s*)|(^\s*$)/gi, "");
}

String.prototype.replaceAll = function(str1, str2) {
    var temp_str = "";
    if (this.trim() != "" && str1 != str2) {
        temp_str = this.trim();
        while (temp_str.indexOf(str1) > -1) {
            temp_str = temp_str.replace(str1, str2);
        }
    }
    return temp_str;
}

function StringBuffer() {
    this.__strings__ = new Array;
}

StringBuffer.prototype.append = function (str) {
    this.__strings__.push(str);
    return this;
};

StringBuffer.prototype.toString = function () {
    return this.__strings__.join("");
};

Array.prototype.contains = function(obj) {
    var i = this.length;
    while (i--) {
        if (this[i] === obj) {
            return true;
        }
    }
    return false;
};

Array.prototype.contains2 = function(obj, pos) {
    var i = this.length;
    while (i--) {
        if (this[i][pos] === obj) {
            return true;
        }
    }
    return false;
};

function CheckInputFloat(input) {
    var reg =  /\d{1,5}\.{0,1}\d{0,2}/;
    if (reg.test(input))
    {
        if(input.indexOf('.') != -1)
        {
            return true;
        }
    }
    return false;
}

function clearNoNum(obj){
    //先把非数字的都替换掉，除了数字和.
    obj.value = obj.value.replace(/[^\d.]/g,"");
    //必须保证第一个为数字而不是.
    obj.value = obj.value.replace(/^\./g,"");
    //保证只有出现一个.而没有多个.
    obj.value = obj.value.replace(/\.{2,}/g,".");
    //保证.只出现一次，而不能出现两次以上
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
}
function checkNum(obj){
    //为了去除最后一个.
    obj.value = obj.value.replace(/\.$/g,"");
}

function checkZero(zero) {
    if (undefined == zero || null == zero) {
        return false;
    } else if (0 == zero || '0.00' == zero || '00.00' == zero) {
        return true;
    } else {
        return false;
    }
}

function checkNotZero(zero) {

    if (undefined == zero || null == zero  || '' == zero || 0 == zero || '0.00' == zero || '00.00' == zero) {
        return false;
    } else {
        return true;
    }
}


function isIdCardNo(num)
{
    var factorArr = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1);
    var error;
    var varArray = new Array();
    var intValue;
    var lngProduct = 0;
    var intCheckDigit;
    var intStrLen = num.length;
    var idNumber = num;
    // initialize
    if ((intStrLen != 15) && (intStrLen != 18)) {
        return false;
    }
    // check and set value
    for(i=0;i<intStrLen;i++) {
        varArray[i] = idNumber.charAt(i);
        if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
            //error = "错误的身份证号码！.";
            //alert(error);
            //frmAddUser.txtIDCard.focus();
            return false;
        } else if (i < 17) {
            varArray[i] = varArray[i]*factorArr[i];
        }
    }
    if (intStrLen == 18) {
        //check date
        var date8 = idNumber.substring(6,14);
        if (checkDate(date8) == false) {
            //error = "身份证中日期信息不正确！.";
            //alert(error);
            return false;
        }
        // calculate the sum of the products
        for(i=0;i<17;i++) {
            lngProduct = lngProduct + varArray[i];
        }
        // calculate the check digit
        intCheckDigit = 12 - lngProduct % 11;
        switch (intCheckDigit) {
            case 10:
                intCheckDigit = 'X';
                break;
            case 11:
                intCheckDigit = 0;
                break;
            case 12:
                intCheckDigit = 1;
                break;
        }
        // check last digit
        if (varArray[17].toUpperCase() != intCheckDigit) {
            //error = "身份证效验位错误!...正确为： " + intCheckDigit + ".";
            //alert(error);
            return false;
        }
    }else{ //length is 15
        //check date
        var date6 = idNumber.substring(6,12);
        if (checkDate(date6) == false) {
            //alert("身份证日期信息有误！.");
            return false;
        }
    }
    //alert ("Correct.");
    return true;
}

function checkDate(date)
{
    return true;
}

function RemoveArray(array,attachId)
{
    for(var i=0,n=0;i<array.length;i++)
    {
        if(array[i]!=attachId)
        {
            array[n++]=array[i]
        }
    }
    array.length -= 1;
}

Array.prototype.remove = function (obj) {
    return RemoveArray(this,obj);
}

/**
 * 用于计算保险期间
 */
function compareDateAndCurrentDate(dateString) {
    var startDate = new Date(dateString),
        endDate  = new Date();
    var useMonth = (endDate.getFullYear() - startDate.getFullYear()) * 12
        + (endDate.getMonth() - startDate.getMonth())
    if (endDate.getDate() >= startDate.getDate()) {
        useMonth++;
    }
    return useMonth;
}

Number.prototype.toFixed2 = function (number) {
    var result = Math.round(this*Math.pow(10,number))/Math.pow(10,number) + "";
    if (result.indexOf('.') == -1) {
        var zero = ".";
        for(var i = 0; i < number; i++) {
            zero +="0";
        }
        result += zero;
    } else {
        var str = result.substring(result.indexOf('.') + 1);
        if (str.length < number) {
            var n = number - str.length;
            for (var j = 0; j < n; j++) {
                result += '0';
            }
        }
    }
    return result;
};


(function(){
    function AjaxRequest(options) {
        this.type           = options.type || "POST";
        this.url            = options.url;
        this.async          = options.async || true;
        this.params         = options.params || {};
        this.isShowLoader   = options.isShowLoader || true;
        this.dataType       = options.dataType || "json";
        this.callBack       = options.callBack;
        this.contentType    = options.contentType || 'application/x-www-form-urlencoded';
        this.init();
    }

    AjaxRequest.prototype = {
        init: function() {
            this.sendRequest();
        },
        showLoader: function() {
            if(this.isShowLoader) { $('body').loading(); }
        },
        hideLoader: function() {
            if(this.isShowLoader) { removeLoading();}
        },
        sendRequest: function() {
            var self = this;
            $.ajax({
                method: this.type,
                url: this.url,
                data: this.params,
                async: this.async,
                // dataType: this.dataType,
                contentType: this.contentType,
                beforeSend: this.showLoader(),
                headers : {"Access-Token": getWithExpiry("token")},
                success: function(res){
                    self.hideLoader();
                    //token延长10分钟， 10분내로 아무런 움직임이 없으면 에러로 감
                    setWithExpiry("token", getWithExpiry("token"), 1000 * 60 * 60);// 1000 * 60 * 60)
                    if (res != null && res !== "") {
                        if(self.callBack){
                            if (Object.prototype.toString.call(self.callBack) === "[object Function]") {
                                self.callBack(res);
                            } else {
                                console.log("callBack is not a function");
                            }
                        }
                    } else {
                        console.log("error error");
                    }
                }
                ,error: function(jqXHR, textStatus, errorThrown) {
                    self.hideLoader();
                    if (textStatus == 'error') {
                        swal("网络超时，请重新登录", "", "error").then(function() {
                            window.location.href = '/login';
                        });

                    }
                }
            });
        },
    }

    window.AjaxRequest = AjaxRequest;
})();

function setWithExpiry(key, value, ttl) {
    const now = new Date()

    // `item` is an object which contains the original value
    // as well as the time when it's supposed to expire
    const item = {
        value: value,
        expiry: now.getTime() + ttl,
    }
    localStorage.setItem(key, JSON.stringify(item))
}

function getWithExpiry(key) {
    const itemStr = localStorage.getItem(key)
    // if the item doesn't exist, return null
    if (!itemStr) {
        return null
    }
    const item = JSON.parse(itemStr)
    const now = new Date()
    // compare the expiry time of the item with the current time
    if (now.getTime() > item.expiry) {
        // If the item is expired, delete the item from storage
        // and return null
        localStorage.removeItem(key)
        return null
    }
    return item.value
}
var g_currentPage = 1;


