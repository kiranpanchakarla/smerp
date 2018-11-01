<script src="/resources/js/core/libraries/jquery.min.js" type="text/javascript"></script> 
<script src="/resources/js/core/libraries/bootstrap.min.js" type="text/javascript"></script> 
<!-- <script src="vendors/js/ui/perfect-scrollbar.jquery.min.js" type="text/javascript"></script>  -->
<script src="/resources/vendors/js/ui/unison.min.js" type="text/javascript"></script> 
<script src="/resources/vendors/js/charts/chart.min.js" type="text/javascript"></script> 
<script src="/resources/js/core/app-menu.js" type="text/javascript"></script> 
<script src="/resources/js/core/app.js" type="text/javascript"></script> 
<script src="/resources/js/scripts/pages/dashboard-lite.js" type="text/javascript"></script> 
<!-- alertifyjs -->
<link rel="stylesheet" href="/resources/components/alertifyjs/css/alertify.css" />
<link rel="stylesheet" href="/resources/components/alertifyjs/css/themes/default.css" />
<!-- alertifyjs -->
<!-- alertfy-->
<script type="text/javascript" src="/resources/components/alertifyjs/alertify.min.js"></script>
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
	  		var dataString  = "id="+id;
	  			 $.ajax({
	  				 type:"POST",
	  				 url: url,
	  				 data : dataString,
	  				 success: function(result){
	  					 alertify.success('Deleted successfully');
	  					 setTimeout(function(){
	  						 location.reload();
	  					 }, 800);
	  			    }});
	  			 
	          }, function(){
	      alertify.error('Cancel')
	   });
   }
  

  function goBack() {
      window.history.back();
  }
 
  
</script>