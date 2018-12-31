<#ftl output_format="XML"  auto_esc=true>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
    <script type="text/javascript">
   /*  @page { 
        @top-center {content: element(header)}      /* Header */
        @bottom-center {content: element(footer)}   /* Enpied */
    }
    #header {position: running(header);}
    #footer {position: running(footer);}
    #pagenumber:before {content: counter(page);}
    #pagecount:before {content: counter(pages);} */
    .header img {
    	  float: left;
    	  width: 100px;
    	  height: 100px;
    	  background: #555;
    	}
 
    	 
    </script>

</head>
<body>
       
        <div class="header">
        <img src="data:image/jpeg;base64,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" 
             style="width:13%" alt="" />
        </div>
		<h2 style="position: fixed; left: 0;  top : 0; width: 100%; color: black;  text-align: center;">Purchase Request </h2> 
         <br></br>     
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
                
                
          
          <!-- <div id="footer"> Page <span id="pagenumber" /> / <span id="pagecount" /></div> -->
    
    
    <div style="position: fixed; left: 0;  bottom: 0; width: 100%; color: black;  text-align: center;">
     <p>Created by: ${pr.user.username}</p>
     <p>Contact information: <a href="">help@manuhindia.com</a>.</p>
     </div>
    
     
    </body>
     
</html>