/**
 * 
 */
$(document).on("keyup", ".camelCase", function(e) {	
		this.value = capitalize_Words(this.value);
	});
		 
function capitalize_Words(str)
{
 return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
}		

$(document).on("keyup", ".capitalCase", function(e) {	
	this.value = this.value.toUpperCase();;
});
