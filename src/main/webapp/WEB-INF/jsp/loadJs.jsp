<script src="/resources/js/core/libraries/jquery.min.js" type="text/javascript"></script> 
<script src="/resources/js/core/libraries/bootstrap.min.js" type="text/javascript"></script> 
<!-- <script src="vendors/js/ui/perfect-scrollbar.jquery.min.js" type="text/javascript"></script>  -->
<script src="/resources/vendors/js/ui/unison.min.js" type="text/javascript"></script> 
<script src="/resources/vendors/js/charts/chart.min.js" type="text/javascript"></script> 
<script src="/resources/js/core/app-menu.js" type="text/javascript"></script> 
<script src="/resources/js/core/app.js" type="text/javascript"></script> 
<script src="/resources/js/scripts/pages/dashboard-lite.js" type="text/javascript"></script> 
<script>
  $(document).ready(function(){
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
  
  

  function deleteUser(id){
  	if (confirm("Are you Sure Want to delete!")) {
       //"You pressed OK!";
  		var url="/currency/delete"
  		var dataString  = "id="+id;
  			 $.ajax({
  				 type:"POST",
  				 url: url, 
  				 data : dataString,
  				 success: function(result){
  					 location.reload(); 
  					 
  			    }});
       
      } else {
      /// "You pressed Cancel!";
      }
  }

  
  
  
</script>