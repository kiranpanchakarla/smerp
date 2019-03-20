<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<title> Email Template</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<body style="margin:0px; padding:0px; background-color:#88bdbf; font-family:Arial, Helvetica, sans-serif">
<table width="600" border="0" align="center" cellpadding="8" cellspacing="0">
  <tr>
    <td height="580" align="center" valign="top" bgcolor="#f3f3f3">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      
      <tr>
        <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          
          <tr>
            <td height="220" align="center" valign="middle" bgcolor="#FFFFFF" style="padding:10px;"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <td align="center" valign="top" style="font-size:22px;"></td>
              </tr>
              <tr>
                <td height="80" align="center" valign="middle" style="font-size:12px; line-height:18px; color:#545454">
               <!--  <p style="text-align: left;font-family: inherit;">Dear <b>Admin,</b> </p> -->
                <p style="text-align: left;font-family: inherit;"></p>
                <div style="background:#e6ecf3; text-align:left;padding:15px">
                 
                <h2 style="font-size: 1.4rem;color: #106570;font-weight: 600;margin: 10px 0; border-bottom: 1px solid rgba(0, 0, 0, 0.1); padding-bottom: 10px;font-family: inherit;">Inventory Goods Issue Daily Report</h2>
                 
                 <#assign sno = 1/>
                 
                <#if invCount??>
                 
                <table style="width:100% ; border-collapse: collapse;" >
                <tr>
                <td style="border: solid 1px ;"><strong >S.no</strong></td>
                <td style="border: solid 1px ;"><strong >Doc#</strong></td>
                <td style="border: solid 1px ;"><strong >Doc Date</strong></td>
                <td style="border: solid 1px ;"><strong >Product#</strong></td>
                <td style="border: solid 1px ;"><strong >Description</strong></td>
                <td style="border: solid 1px ;"><strong >Group</strong></td>
                <td style="border: solid 1px ;"><strong >UOM</strong></td>
                <td style="border: solid 1px ;"><strong >Quantity</strong></td>
                <td style="border: solid 1px ;"><strong >Department</strong></td>
                <td style="border: solid 1px ;"><strong >Remarks</strong></td>
                </tr>
                <#list invCount as productlist>
                <tr>
                <td style="border: solid 1px ; text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if productlist.docNumber??>&nbsp;${productlist.docNumber}</#if></td>
                <td style="border: solid 1px ;"><#if productlist.docDate??> &nbsp;${productlist.docDate}</#if></td>
                <td style="border: solid 1px ;"><#if productlist.productNumber??> &nbsp;${productlist.productNumber}</#if></td>
                <td style="border: solid 1px ;"><#if productlist.productDescription??>&nbsp;${productlist.productDescription}</#if></td>
                <td style="border: solid 1px ;"><#if productlist.productGroup??>&nbsp;${productlist.productGroup}</#if></td>
                <td style="border: solid 1px ;"><#if productlist.uomName??>&nbsp;${productlist.uomName}</#if></td>
                <td style="border: solid 1px ;"><#if productlist.requiredQty??>&nbsp;${productlist.requiredQty}</#if></td>
                <td style="border: solid 1px ;"><#if productlist.departmentName??>&nbsp;${productlist.departmentName}</#if></td>
                <td style="border: solid 1px ;"><#if productlist.remarks??>&nbsp;${productlist.remarks}</#if></td>
                </tr>
                </#list>
                </table>
               </#if>
               
               
                </div>
               </td>
              </tr>
              
              <tr>
              <td style="font-size:12px; line-height:18px; color:#545454">
               <p style="text-align: center;font-family: inherit;">Email Sent by SMERP, do not reply. <br>
                                             Copyright &copy; 2019. All rights Reserved.
              </td>
              </tr>
              
            </table></td>
          </tr>
        </table></td>
      </tr>
      
    </table></td>
  </tr>
</table>
</body>
</html>