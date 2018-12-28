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
                <td>: <#if pr.user.username??> ${pr.user.username}</#if></td>
                <td><strong >Doc No</strong></td>
                <td>:<#if pr.docNumber??> ${pr.docNumber}</#if> </td>
                </tr>
                
                <tr>
                <td><strong >Requester Name</strong></td>
                <td>:<#if pr.referenceUser.firstname??> ${pr.referenceUser.firstname}</#if></td>
                <td><strong >Posting Date </strong></td>
                <td>:<#if pr.postingDate??>${pr.postingDate?string("dd-MM-yyyy")!''}</#if> </td>
                </tr>
                
                <tr>
                <td><strong >Plant</strong></td>
                <td>:<#if pr.referenceUser.plant.plantName??> ${pr.referenceUser.plant.plantName}</#if></td>
                <td><strong >Doc Date</strong></td>
                <td>:<#if pr.documentDate??>${pr.documentDate?string("dd-MM-yyyy")!''}</#if> </td>
                </tr>
                
                <tr>
                <td><strong >Email- ID</strong></td>
                <td>:  <#if pr.referenceUser.userEmail??> ${pr.referenceUser.userEmail}</#if></td>
                <td><strong >Require Date</strong></td>
                <td>:<#if pr.requiredDate??>${pr.requiredDate?string("dd-MM-yyyy")!''}</#if></td>
                </tr>
                
                <tr>
                <td><strong >Type</strong></td>
                <td>: <#if pr.type??> ${pr.type}</#if></td>
                <td><strong >Status</strong></td>
                <td>: <#if pr.status??> ${pr.status}</#if></td> 
                </tr>
                
            </table>
                <br></br>
                <#if pr.type??>
                 <#assign sno = 1/>
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
                <td style="border: solid 1px ; text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if prlist.prodouctNumber??>&nbsp;${prlist.prodouctNumber}</#if></td>
                <td style="border: solid 1px ;"><#if prlist.description??>&nbsp;${prlist.description}</#if></td>
                <td style="border: solid 1px ;"><#if prlist.uom??> &nbsp;${prlist.uom}</#if></td>
                <td style="border: solid 1px ;"><#if prlist.requiredQuantity??>&nbsp;${prlist.requiredQuantity}</#if></td>
                <td style="border: solid 1px ;"><#if prlist.productGroup??>&nbsp;${prlist.productGroup}</#if></td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (prlist.warehouse) == (key?string)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list>
                
                </td>
                <td style="border: solid 1px ;">&nbsp;${prlist.hsn}</td>
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
                <td style="border: solid 1px ;text-align:center; ">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if prlist.sacCode??>&nbsp;${prlist.sacCode}</#if></td>
                <td style="border: solid 1px ;"><#if prlist.description??>&nbsp;${prlist.description}</#if></td>
                <td style="border: solid 1px ;"><#if prlist.requiredQuantity??>&nbsp;${prlist.requiredQuantity}</#if></td>
                <td style="border: solid 1px ;"> <#list plantMap as key, value>
                <#if (prlist.warehouse) == (key?string)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list></td>
                </tr>
                </#list>
                </table>
                </#if></#if>
                
                
          
      
     
     
     
    </body>
</html>