$(document).on("change", ".taxCode", function() {

	var itemParentRow = $(this).parents(".multTot");
	var requiredQuantity=  $(itemParentRow).find(".requiredQuantity").val();
	var unitPrice=  $(itemParentRow).find(".unitPrice").val();
	var tax=  $(itemParentRow).find(".taxCode option:selected").val();
	
	var taxDescription=  $(itemParentRow).find(".taxCode option:selected").text();
	$(itemParentRow).find(".taxDescription").val(taxDescription);
	
//	alert("tax--->" +tax);
	var tax_amt = getDiscount(tax);
	var totalValue = getCalculateAmt(requiredQuantity,unitPrice,tax_amt);
	var invoiceAmount = getInvoiceAmt(requiredQuantity,unitPrice);
	//alert("tax_amt" +tax_amt);
	//alert("totalValue" +totalValue);
	 //alert("totalValue" +invoiceAmount);
	 $(itemParentRow).find(".taxTotal").val(parseFloat((requiredQuantity * unitPrice) * tax_amt).toFixed(2));
	 $(itemParentRow).find(".total").val(invoiceAmount.toFixed(2));
	
	 var sum_total = 0;
	 var invoiceAmt = 0;
	 var sum_tax_total = 0;
  	 $(".total").each(function() {
  		invoiceAmt += parseFloat($(this).val());
		    });
  	 
  	 $(".taxTotal").each(function() {
  		sum_tax_total += parseFloat($(this).val());
 		    });
  	 
  	sum_total = parseFloat(invoiceAmt) + parseFloat(sum_tax_total);
 	if(!isNaN(sum_total)) {
	 $("#taxAmt").val(parseFloat(sum_tax_total).toFixed(2));
  	 $("#totalBeforeDisAmt").val(parseFloat(invoiceAmt).toFixed(2));
  	 $("#amtRounding").val(sum_total);
  	 $("#totalPayment").val(Math.round(sum_total));
  	 $("#totalDiscount").val("");
  	 $("#freight").val("");
 	}
  	 
	});
	
	
	

$(document).on("keyup", ".unitPrice", function() {
	 var itemParentRow = $(this).parents(".multTot");
	
	var requiredQuantity=  $(itemParentRow).find(".requiredQuantity").val();
	var unitPrice=  $(itemParentRow).find(".unitPrice").val();
	var tax=  $(itemParentRow).find(".taxCode option:selected").val();
//	alert("tax--->" +tax);
	var invoiceAmount = parseFloat(requiredQuantity * unitPrice);
	var tax_amt = Number(tax) / 100;
	var totalValue = parseFloat(requiredQuantity * unitPrice) + parseFloat(requiredQuantity * unitPrice * tax_amt);
	//alert("tax_amt" +tax_amt);
	//alert("totalValue" +totalValue);
		if( tax !="Select") {
	 $(itemParentRow).find(".taxTotal").val(parseFloat((requiredQuantity * unitPrice) * tax_amt).toFixed(2));
	 $(itemParentRow).find(".total").val(invoiceAmount.toFixed(2));
		}
	
	
	 var sum_total = 0;
	 var invoiceAmt = 0;
	 var sum_tax_total = 0;
	 
  	 $(".total").each(function() {
  		invoiceAmt += parseFloat($(this).val());
		    });
  	// alert(invoiceAmt);
  	 $(".taxTotal").each(function() {
  		sum_tax_total += parseFloat($(this).val());
 		    });
  	 
  	sum_total = parseFloat(invoiceAmt) + parseFloat(sum_tax_total);
  	
  	if( tax !="Select"  && !isNaN(sum_total)) {
	 $("#taxAmt").val(parseFloat(sum_tax_total).toFixed(2));
  	 $("#totalBeforeDisAmt").val(parseFloat(invoiceAmt).toFixed(2));
  	 $("#amtRounding").val(sum_total);
  	 $("#totalPayment").val(Math.round(sum_total));
  	 $("#totalDiscount").val("");
  	 $("#freight").val("");
  	$("#roundedOff").val(Math.round(sum_total) - sum_total );
    	}
	});
	

$(document).on("keyup", ".requiredQuantity", function() {
	
	var itemParentRow = $(this).parents(".multTot");
	 
	var requiredQuantity=  $(itemParentRow).find(".requiredQuantity").val();
	var unitPrice=  $(itemParentRow).find(".unitPrice").val();
	var tax=  $(itemParentRow).find(".taxCode option:selected").val();
	
	var invoiceAmount = parseFloat(requiredQuantity * unitPrice);
	var tax_amt = Number(tax) / 100;
	var totalValue = parseFloat(requiredQuantity * unitPrice) + parseFloat(requiredQuantity * unitPrice * tax_amt);
	//alert("tax_amt" +tax_amt);
	//alert("totalValue" +totalValue);
		if( tax !="Select") {
	 $(itemParentRow).find(".taxTotal").val(parseFloat((requiredQuantity * unitPrice) * tax_amt).toFixed(2));
	 $(itemParentRow).find(".total").val(invoiceAmount.toFixed(2));
		}
	
		var sum_total = 0;
	 var invoiceAmt = 0;
	 var sum_tax_total = 0;
  	 $(".total").each(function() {
  		if($(this).val() !="") {
  		invoiceAmt += parseFloat($(this).val());
  		}
		    });
  	 
  	 $(".taxTotal").each(function() {
  		if($(this).val() !="") {
  		sum_tax_total += parseFloat($(this).val());
  		}
 		    });
  	
    var discAmt = 0;
 	var discount = $("#totalDiscount").val();
 	//alert(discount);
  	if(!isNaN(discount)  && discount!="" ){
  		discAmt = parseFloat(invoiceAmt) - parseFloat(invoiceAmt * discount/100);
  	}
  	
  //	alert("cccc"+invoiceAmt);
  	
  	var freight = $("#freight").val();
	//alert(freight);
  	
  	if(!isNaN(freight)  && freight!="" ){
  		//alert(freight);
  		sum_total += parseFloat(freight);
  	}
  	//alert(sum_total);
  	
  	sum_total += parseFloat(discAmt) + parseFloat(sum_tax_total);
  	
  	if( tax !="Select"  && !isNaN(sum_total)) {
	 $("#taxAmt").val(parseFloat(sum_tax_total).toFixed(2));
  	 $("#totalBeforeDisAmt").val(parseFloat(invoiceAmt).toFixed(2));
  	 $("#amtRounding").val(sum_total);
  	 $("#totalPayment").val(Math.round(sum_total));
  	 //$("#totalDiscount").val("");
  	 //$("#freight").val("");
  	 $("#roundedOff").val(Math.round(sum_total) - sum_total );
    	}
  	
  	
	});

	
	
	$('#totalDiscount').keyup(function() {
	var totalBeforeDisAmt = $("#totalBeforeDisAmt").val();
	//alert(totalBeforeDisAmt);
	var discount_val = $("#totalDiscount").val();
	//alert(discount_val);
	var taxAmt = $("#taxAmt").val();
	//alert(taxAmt);
	var totalAmt =  parseFloat(totalBeforeDisAmt);
	//alert(taxAmt);
	//alert(totalBeforeDisAmt);
	//alert(totalAmt);
	if(discount_val<100){
	var discount = Number(discount_val) / 100;
	var discountAmt = parseFloat(totalAmt) - parseFloat(totalAmt * discount);
	var totalPayment = parseFloat(discountAmt) + parseFloat(taxAmt);
	
    
	if( $("#freight").val()!="") {
		totalPayment += parseFloat($("#freight").val());
	}
	if(totalAmt!="") {
	 $("#totalPayment").val(Math.round(totalPayment));
	 $("#amtRounding").val(parseFloat(totalPayment).toFixed(2));
	 $("#roundedOff").val(parseFloat(Math.round(totalPayment) - totalPayment).toFixed(2) );
	}
	
	}else {
		 $("#totalDiscount").val("");
		alertify.alert("Purchase Order Discount","Please Enter Valid Discount!");
		 return false;
	}
	
	});
	
	
	function getDiscount(discount_val){
		var discount = Number(discount_val) / 100;
		return discount;
	}
	
	function getCalculateAmt(requiredQuantity,unitPrice,tax_amt){
		var amt = parseFloat(requiredQuantity * unitPrice) + parseFloat(requiredQuantity * unitPrice * tax_amt);
		return amt;
	}
	
	function getInvoiceAmt(requiredQuantity,unitPrice){
		var amt = parseFloat(requiredQuantity * unitPrice);
		return amt;
	}



$('#freight').keyup(function() {
	
	var totalBeforeDisAmt = $("#totalBeforeDisAmt").val();
	var discount_val = $("#totalDiscount").val();
	var freight =  $(this).val();
	var taxAmt = $("#taxAmt").val();
	var totalAmt =  parseFloat(totalBeforeDisAmt) ;
	
//alert("discount_val" +discount_val);
//alert("freight" +freight);
//alert("totalAmt" +totalAmt);
	if( discount_val !="" && $("#totalDiscount").val()!="") {
		var discount = Number(discount_val) / 100;
		var discountAmt = parseFloat(totalAmt) - parseFloat(totalAmt * discount);
		totalAmt = parseFloat(discountAmt) +  parseFloat(taxAmt);
		
	}
	
	if(totalAmt!="") {
	var finalValue =  Number(totalAmt) + Number(freight);
	 $("#totalPayment").val(Math.round(finalValue));
	 $("#amtRounding").val( parseFloat(finalValue).toFixed(2));
	 $("#roundedOff").val(parseFloat(Math.round(finalValue) - finalValue).toFixed(2));
	}
});
	
	function setCalculationAmt(index){
			 var sum_total = 0;
			 var sum_tax_total = 0;
		  	 $(".total").each(function(row) {
		  		///alert("ttt-->" +isNaN($(this).val()));
		  		 if(row!=index) {
		  		 sum_total += parseFloat((isNaN($(this).val())) ? 0 : $(this).val());
		  		             }
				    });
		  	 
		  	 $(".taxTotal").each(function(row) {
		  		if(row!=index) {
		  		 sum_tax_total += parseFloat((isNaN($(this).val())) ? 0 : $(this).val());
		  	                }
		 		    });
		  	 //alert(sum_total);
		  	// alert(sum_tax_total);
		  	 
		  	 $("#taxAmt").val(parseFloat(sum_tax_total).toFixed(2));
		  	 $("#totalBeforeDisAmt").val(parseFloat(sum_total).toFixed(2));
		  	 
		var discount_val = $("#totalDiscount").val();
		var discount = Number(discount_val) / 100;
		var totalPayment = parseFloat(sum_total) - parseFloat(sum_total * discount);
	    
		if( $("#freight").val()!="") {
			totalPayment += parseFloat(Math.round($("#freight").val()));
		}
		if(sum_total!="") {
		 $("#totalPayment").val(Math.round(totalPayment));
		 $("#amtRounding").val( totalPayment);
		 $("#rounderOff").val(Math.round(totalPayment) - totalPayment);
		}
	
	} 
	

	
	$(document).on("keypress", ".validatePrice", function(e) {	
		if (this.value.length == 0 && e.which == 48 ){
			      return false;
			   }
		});