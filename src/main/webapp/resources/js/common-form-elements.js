function showBootStrapValidationError(selector, databvfor, message, scrollingTop, focusing){
  $(selector).parents(".form-group").addClass("has-error");
      $(selector).parents(".form-group").removeClass("has-success");
      $(selector).parents(".form-group").find(".scl-form-error-container").show().html(
      '<small class="help-block" data-bv-validator="notEmpty" data-bv-for="'+databvfor+'" data-bv-result="INVALID" style="">'+message+'</small>');
      $('[type=submit]').effect("bounce", {
          direction: "left",
          times: 5,
          distance: 20
        }, 600, "easeOutBounce");
      if(scrollingTop){
        $('html, body').animate({ scrollTop: 0 }, 'slow');
      }
      if(focusing){
        $(selector).focus();
        $(selector).select();
      }
}
function commonFormCloseIconsLoad(selector){
  $(selector).find('.scl-nullable-icon').click(function() {
        var inpElement = $(this).prevAll('input.scl-nullable, textarea.scl-nullable');
        inpElement.val('').trigger('change');
        $(this).parents('.input-group').find("input.hidden_datatable_id:hidden").val('');
        $('[type=submit]').prop('disabled', false);
        //$(selector).bootstrapValidator('revalidateField', $inp);
  });
  $(selector).find('input.scl-nullable, textarea.scl-nullable').on('change keyup', function() {
      if($(this).val().length > 0)
      {
        $(this).nextAll('.scl-nullable-icon').css({'left': "", 'right': ""});
      }
      else
      {
        $(this).nextAll('.scl-nullable-icon').css({'left': "-5000px", 'right': "auto"}); // we can't hide it, it loses its position....
      }
  });
  $(selector).find('input.scl-nullable, textarea.scl-nullable').each(function(){
      if($(this).val().length > 0)
      {
        $(this).nextAll('.scl-nullable-icon').css({'left': "", 'right': ""});
      }
      else
      {
        $(this).nextAll('.scl-nullable-icon').css({'left': "-5000px", 'right': "auto"}); // we can't hide it, it loses its position....
      }
  });
}
function commonBootStrapValidations(formObj){
      var validatorCount = 1;
      var isValid = false;
      var validator = null;
      try
      {
        //console.log('1');
        formObj.bootstrapValidator({
          //group: '.form-group, .input-group'
        });
        validator = formObj.data('bootstrapValidator');
        formObj.on('success.form.bv', function (e) {
          e.preventDefault();
          if(validatorCount > 1) {
            console.log("commonFormValidation - Validator infinite loop detected!");
            //showErrorAlertModal('While sending the form a unknown error has occurred! Please refresh the site, if this error happens again please call your system administrator.');
            throw "Validator infinite loop detected!";
          }
          validatorCount++;
        });

        validator.validate();
        isValid = validator.isValid();
      }
      catch(e)
      {
        console.log('2');
        console.log("commonFormValidation => " + e.message, formObj);
        validatorCount = 1;
        return false;
      }
      finally
      {
        //console.log('3');
        formObj.off('.bv') //Remove all generated events
            .removeData('bootstrapValidator')
            .find('[data-bv-submit-hidden]').remove();//Remove generated hidden elements
        validator = null;
      }

      if(isValid)
      {
        //return true;
      }
      else
      {
        //formObj.find('input[type=submit]').button('reset');

        /*$('[type=submit]').effect("bounce", {
          direction: "left",
          times: 5,
          distance: 20
        }, 600, "easeOutBounce");*/
        $('[type=submit]').prop('disabled', false);
        return false;
      }
      return isValid;
}