<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>






<script src=<c:url value="/resources/js/core/libraries/jquery.min.js"/> type="text/javascript"></script>

<link href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" rel="Stylesheet"></link>
<script src='https://cdn.rawgit.com/pguso/jquery-plugin-circliful/master/js/jquery.circliful.min.js'></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js" ></script>

<!-- <script src=<c:url value="/resources/js/core/libraries/bootstrap.min.js"/> type="text/javascript"></script> -->
<script src=<c:url value="/resources/vendors/js/ui/unison.min.js"/> type="text/javascript"></script> 
<script src=<c:url value="/resources/vendors/js/charts/chart.min.js"/> type="text/javascript"></script> 
<script src=<c:url value="/resources/js/core/app-menu.js"/> type="text/javascript"></script> 
<script src=<c:url value="/resources/js/core/app.js"/> type="text/javascript"></script> 
<!-- <script src=<c:url value="/resources/js/scripts/pages/dashboard-lite.js"/> type="text/javascript"></script>   -->


<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 




<!-- alertifyjs -->

<link href="<c:url value="/resources/components/alertifyjs/css/alertify.css"/>"
    rel="stylesheet" type="text/css" />
    <link href="<c:url value="/resources/components/alertifyjs/css/themes/default.css"/>"
    rel="stylesheet" type="text/css" />

<!-- <link rel="stylesheet" href="/resources/components/alertifyjs/css/alertify.css" />
<link rel="stylesheet" href="/resources/components/alertifyjs/css/themes/default.css" /> -->
<!-- alertifyjs -->
<!-- alertfy-->

<script src=<c:url value="/resources/components/alertifyjs/alertify.min.js"/> type="text/javascript"></script>  
<!-- <script type="text/javascript" src="/resources/components/alertifyjs/alertify.min.js"></script> -->
<!-- alertfy-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script>





  $(document).ready(function(){
	 
	// allow only numerics
	   var specialKeys = new Array();
	   specialKeys.push(8); //Backspace
	   $(".numericwithoutdecimal").bind("keypress", function (e) {
	     var keyCode = e.which ? e.which : e.keyCode
	     var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1);
	     return ret;
	   });
	   // allow only numerics with .dot
	   $(".numericwithdecimal").on("keypress keyup blur",function (event) {
	        $(this).val($(this).val().replace(/[^0-9\.]/g,''));
	         if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)){
	             event.preventDefault();
	         }
	   });
	   $(".numericwithoutdecimal, .numericwithdecimal").bind("paste", function (e) {
	     return false;
	   });
	   $(".numericwithoutdecimal, .numericwithdecimal").bind("drop", function (e) {
	     return false;
	   });
	  
    $('.icon-menu5').click(function(){
      if($('.menu-shadow').find('.sub_menu').hasClass('collapse_sub')){
        $('.menu-shadow').find('.sub_menu').removeClass('collapse_sub');
        $('.menu_text').css('display','block');
        $('.menu_text_pad').css('display','none');
      }else{
        $('.menu-shadow').find('.sub_menu').addClass('collapse_sub');
        $('.menu_text').css('display','none');
        $('.menu_text_pad').css('display','block');
      }
      //$('.menu-shadow').find('.sub_menu').addClass('collapse');
    });
    
  });
  
  
  function deleteById(id,url){
      alertify.confirm('Are you Sure Want to Delete', function(){
          //alertify.success('Ok');
            //"You pressed OK!";
            
            
            var parts = url.split('/');
      /* alert(parts); */
      var answer = parts[parts.length - 1];
      /* alert(answer);
             */
            
            
              var dataString  = "id="+id;
                   $.ajax({
                       type:"POST",
                      /*  url: url, */
                       url: answer,
                       data : dataString,
                       success: function(result){
                           alertify.success('Deleted successfully');
                           setTimeout(function(){
                               location.reload();
                           }, 800);
                      }});
                  
              }, function(){
          alertify.error('Cancelled')
       });
  }

 function isValidName(nameId,url,displayId,msg){
      
      
      var parts = url.split('/');
     /*  alert(parts); */
      var answer = parts[parts.length - 1];
    /*   alert(answer); */
      
      var dataString  ="name="+$('#'+nameId).val();
      $.ajax({
             type:"GET",
            /*  url: url, */
              url: answer,
             data : dataString,
             success: function(result){
                 if(result==true){
                     alertify.success(msg);
                     $('#'+nameId).val('');
                     $('#'+displayId).html(msg);
                 }else {
                     $('#'+displayId).html('');
                 }
            }});
      
 }

  function goBack() {
      window.history.back();
  }
 
  
</script>