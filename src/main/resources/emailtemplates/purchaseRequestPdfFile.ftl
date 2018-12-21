<#ftl output_format="XML"  auto_esc=true>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>

</head>
<body>
	

		<p align="center" style="font-size: 17pt;">Purchase Request </p>
              
        <table style="width:100%">
                <tr>
                <td><strong >User</strong></td>
                <td>: ${pr.user.username}</td>
                <td><strong >Doc No</strong></td>
                <td>: ${pr.docNumber} </td>
                </tr>
                
                <tr>
                <td><strong >Requester Name</strong></td>
                <td>: ${pr.referenceUser.firstname}</td>
                <td><strong >Posting Date</strong></td>
                <td><#if pr.postingDate??>${pr.postingDate?string("dd-MM-yyyy")!''}</#if> </td>
                </tr>
                
                <tr>
                <td><strong >Plant</strong></td>
                <td>: ${pr.referenceUser.plant.plantName}</td>
                <td><strong >Doc Date</strong></td>
                <td><#if pr.documentDate??>${pr.documentDate?string("dd-MM-yyyy")!''}</#if> </td>
                </tr>
                
                <tr>
                <td><strong >Email- ID</strong></td>
                <td>:   ${pr.referenceUser.userEmail}</td>
                <td><strong >Require Date</strong></td>
                <td><#if pr.requiredDate??>${pr.requiredDate?string("dd-MM-yyyy")!''}</#if></td>
                </tr>
                
                <tr>
                <td><strong >Type</strong></td>
                <td>:  ${pr.type}</td>
                <td><strong >Status</strong></td>
                <td>:  ${pr.status}</td> 
                </tr>
                
            </table>
                <br></br>
                <#if pr.type??>
               <#if pr.type = "Item"> 
                <table style="width:100% ; border-collapse: collapse;" >
                <tr>
                <td style="border: solid 1px ;"><strong >S.no</strong></td>
                <td style="border: solid 1px ;"><strong >Product Name</strong></td>
                <td style="border: solid 1px ;"><strong >Description</strong></td>
                <td style="border: solid 1px ;"><strong >UOM</strong></td>
                <td style="border: solid 1px ;"><strong >Quantity</strong></td>
                <td style="border: solid 1px ;"><strong >Product Group</strong></td>
                <td style="border: solid 1px ;"><strong >Warehouse	</strong></td>
                <td style="border: solid 1px ;"><strong >HSN</strong></td>
                </tr>
                <#list pr.purchaseRequestLists as prlist>
                <tr>
                 <td style="border: solid 1px ;">${prlist?index}</td>
                <td style="border: solid 1px ;">${prlist.prodouctNumber}</td>
                <td style="border: solid 1px ;">${prlist.description}</td>
                <td style="border: solid 1px ;">${prlist.uom}</td>
                <td style="border: solid 1px ;">${prlist.requiredQuantity}</td>
                <td style="border: solid 1px ;">${prlist.productGroup}</td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (prlist.warehouse) == (key?string)>
                     <p> ${value}</p>
                     </#if>
                </#list>
               
             
              
                
                </td>
                <td style="border: solid 1px ;">${prlist.hsn}</td>
                </tr>
                </#list>
                </table>
                <#else>
                <table style="width:100% ; border-collapse: collapse;" >
                <tr>
                <td style="border: solid 1px ;"><strong >S.no</strong></td>
                <td style="border: solid 1px ;"><strong >SAC Code</strong></td>
                <td style="border: solid 1px ;"><strong >Description</strong></td>
                <td style="border: solid 1px ;"><strong >Quantity</strong></td>
                <td style="border: solid 1px ;"><strong >Warehouse</strong></td>
                </tr>
                <#list pr.purchaseRequestLists as prlist>
                <tr>
                <td style="border: solid 1px ;">${prlist?index}</td>
                <td style="border: solid 1px ;">${prlist.sacCode}</td>
                <td style="border: solid 1px ;">${prlist.description}</td>
                <td style="border: solid 1px ;">${prlist.requiredQuantity}</td>
                <td style="border: solid 1px ;">${prlist.warehouse}</td>
                </tr>
                </#list>
                </table>
                </#if></#if>
                
                
          
      
     
     
     
    </body>
</html>