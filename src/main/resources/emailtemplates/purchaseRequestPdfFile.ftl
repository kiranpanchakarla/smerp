<#ftl output_format="XML" auto_esc=true>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<link rel="stylesheet" media="screen,print" type="text/css" href="${contextPath}/resources/css/print.css"/>
 
    

</head>
<body>
    <div id="background">
  <p id="watermark"> ${user.company.name}</p>
	</div>
   <div style="-fs-page-sequence: start;">
      <div style="position: running(current);">
      	<div id="page-header" class="head">
 		
 		<div style="width:100%; margin-top: 50px;">
 			<table>
		   <tr>
            
	         <td style="width: 30%; border: 1px solid #fff !important;">
                 <img style="height:70px;width:auto;" src="${contextPath}/${user.company.logo}" />
            </td>
            <td style="width: 25%; border: 1px solid #fff !important; text-align:center;">
                <strong style="font-size:20px;">${moduleName}</strong><br/>
            </td>
        </tr>
	</table>
 		</div>
		 <div class="line" style="margin-top:-15px;">&#160;</div>
		</div>
		</div>
		</div>
	 
	<br></br>
	  
	<div style="z-index:1; width:100%; position: absolute;">
	<table style="width: 100%;  z-index:1;">
		<tr>
			<td><strong>User</strong></td>
			<td>: <#if pr.user.username??> ${pr.user.username}</#if></td>
			<td><strong>Doc No</strong></td>
			<td>:<#if pr.docNumber??> ${pr.docNumber}</#if> </td>
		</tr>

		<tr>
			<td><strong>Requester Name</strong></td>
			<td>:<#if pr.referenceUser.firstname??> ${pr.referenceUser.firstname}</#if></td>
			<td><strong>Posting Date </strong></td>
			<td>:<#if pr.postingDate??>${pr.postingDate?string("dd-MM-yyyy")!''}</#if> </td>
		</tr>

		<tr>
			<td><strong>Plant</strong></td>
			<td>:<#if pr.referenceUser.plant.plantName??> ${pr.referenceUser.plant.plantName}</#if></td>
			<td><strong>Doc Date</strong></td>
			<td>:<#if pr.documentDate??>${pr.documentDate?string("dd-MM-yyyy")!''}</#if> </td>
		</tr>

		<tr>
			<td><strong>Email- ID</strong></td>
			<td>: <#if pr.referenceUser.userEmail??> ${pr.referenceUser.userEmail}</#if></td>
			<td><strong>Require Date</strong></td>
			<td>:<#if pr.requiredDate??>${pr.requiredDate?string("dd-MM-yyyy")!''}</#if></td>
		</tr>

		<tr>
			<td><strong>Status</strong></td>
			<td>: <#if pr.status??> ${pr.status}</#if></td>
			<td><strong >Remarks</strong></td>
            <td>: <#if pr.remarks??> ${pr.remarks}</#if></td>
		</tr>

	</table>
	<br></br>
	<#if pr.type??>
                 <#assign sno = 1/>
               <#if pr.type = "Item"> 
                <table style="width:100% ; border-collapse: collapse;  " >
                <tr>
                <td style="border: solid 1px ;"><strong >S.No</strong></td>
                <td style="border: solid 1px ;"><strong >Product#</strong></td>
                <td style="border: solid 1px ;"><strong >Description</strong></td>
                <td style="border: solid 1px ;"><strong >UOM</strong></td>
                <td style="border: solid 1px ;"><strong >SKU</strong></td>
                <td style="border: solid 1px ;"><strong >Quantity</strong></td>
                <td style="border: solid 1px ;"><strong >Group</strong></td>
                <td style="border: solid 1px ;"><strong >Warehouse	</strong></td>
                <td style="border: solid 1px ;"><strong >HSN</strong></td>
                </tr>
                <#list pr.purchaseRequestLists as prlist>
                <tr>
                <td style="border: solid 1px ; text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if prlist.prodouctNumber??>&nbsp;${prlist.prodouctNumber}</#if></td>
                <td style="border: solid 1px ;"><#if prlist.description??>&nbsp;${prlist.description}</#if></td>
                <td style="border: solid 1px ;"><#if prlist.uom??> &nbsp;${prlist.uom}</#if></td>
                <td style="border: solid 1px ;"><#if prlist.sku??> &nbsp;${prlist.sku}</#if></td>
                <td style="border: solid 1px ;"><#if prlist.requiredQuantity??>&nbsp;${prlist.requiredQuantity}</#if></td>
                <td style="border: solid 1px ;"><#if prlist.productGroup??>&nbsp;${prlist.productGroup}</#if></td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (prlist.warehouse) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list>
                
                </td>
                <td style="border: solid 1px ;">&nbsp;${prlist.hsn}</td>
                </tr>
                
                </#list>
                </table>
                <#else>
                <table style="width:100% ; border-collapse: collapse; " >
                <tr>
                <td style="border: solid 1px ;"><strong >S.no</strong></td>
                <td style="border: solid 1px ;"><strong >SAC Code</strong></td>
                <td style="border: solid 1px ;"><strong >Description</strong></td>
                <td style="border: solid 1px ;"><strong >Quantity</strong></td>
                <td style="border: solid 1px ;"><strong >Warehouse</strong></td>
                </tr>
                <#list pr.purchaseRequestLists as prlist>
                <tr>
                <td style="border: solid 1px ;text-align:center; ">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if prlist.sacCode??>&nbsp;${prlist.sacCode}</#if></td>
                <td style="border: solid 1px ;"><#if prlist.description??>&nbsp;${prlist.description}</#if></td>
                <td style="border: solid 1px ;"><#if prlist.requiredQuantity??>&nbsp;${prlist.requiredQuantity}</#if></td>
                <td style="border: solid 1px ;"> <#list plantMap as key, value>
                <#if (prlist.warehouse) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list></td>
                </tr>
                </#list>
                </table>
                </#if></#if>
                
    
    </div>
     
    </body>

</html>