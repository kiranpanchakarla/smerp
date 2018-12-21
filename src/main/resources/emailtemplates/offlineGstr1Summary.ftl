<#ftl output_format="XML"  auto_esc=true>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
    <link rel="stylesheet" media="screen,print" type="text/css" href="${contextPath}/resources/css/print.css"/>
<style>
table {
    border-collapse: collapse;
    font-family: Arial, Helvetica, sans-serif;
}
body{
font-family: 'Open Sans',  'Helvetica Neue', Helvetica, Arial, sans-serif !important;
}
table {
    table-layout: auto;
    border-collapse: collapse;
    width: 100%;
}
headColor{
background-color:#bfbfbf !important;
}
table td {
    border: 1px solid #ccc;
    word-wrap:break-word;
}
table .absorbing-column {
    width: 100%;
}
td {
	padding: 3px;
	font-size: 8pt;
	border: 1px solid #000;
	word-wrap: break-word;
	height: 100%;
}
th {
	padding: 3px;
	font-size: 7pt;
	border: 1px solid #000;
	text-align: center;
	background-color: #bfbfbf !important;
}


</style>

</head>
<body>

     <div style="-fs-page-sequence: start;">
      <div style="position: running(current);">
      	<div id="page-header" class="head">
      	
      	<div style="width:100%;">
 			<div style="float:left; width:50%;">
 	 		<span>TaxPeriod:</span>
				<span class="align-right">07-2018</span><br/>
				<span class="align-right">Test</span>
			</div>
 			<div style="float:left; width:50%; text-align:right;">
				<span class="align-right">Generated Date: ${generatedDate} </span><br/>
				<span class="align-right">Generated By:: <#if generatedBy??>${generatedBy}</#if></span>
			</div>
 		</div>
      	
		<div class="line">&#160;</div>
		</div>
	 </div>
		<div style="position: running(footer);">
      	<div id="page-footer" class="small">
	        <div class="line">&#260;</div>
	       Prepared By &nbsp;&nbsp;:&nbsp;<span><b>test</b></span>
	    </div> 
      </div>
       </div>
	
<div id="content">
		<p align="center" style="font-size: 17pt;">Form GSTR-1 Summary</p>
		<p align="center" style="font-size: 14pt;">[See rule (59(1)]</p>
		<p align="center" style="font-size: 13pt;">Details of outward supplies of goods or services</p>
     <table width="100%" border="1">
        <tr>
          <td width="190" style="font-size: 10pt;" class="headColor">1. GSTIN </td>
          <td width="150" style="font-size: 9pt;">123</td>
        </tr>
        <tr>
          <td width="190" style="font-size: 10pt;" class="headColor"> 2(a). Legal name of the registered person</td>
          <td width="150" style="font-size: 9pt;">456</td>
        </tr>
        <tr>
          <td width="190" style="font-size: 10pt;" class="headColor">2(b). Trade name, if any</td>
          <td width="150" style="font-size: 9pt;"></td>
        </tr>
        <tr>
          <td width="190" style="font-size: 10pt;" class="headColor">3(a). Aggregate Turnover in the preceding Financial Year</td>
          <td width="150" style="font-size: 9pt;">6541</td>
        </tr>
         <tr>
          <td width="190" style="font-size: 10pt;" class="headColor">3(b). Aggregate Turnover - April to June, 2017 </td>
          <td width="150" style="font-size: 9pt;">964555</td>
        </tr>
     </table>   
     
     

     <h6>4A, 4B, 4C, 6B, 6C - B2B Invoices</h6>
     <table width="100%" border="1">
        <tr>
          <td style="font-size: 9pt;" class="headColor">No. of Records</td>
          <td style="font-size: 9pt;" class="headColor">Total Invoice value</td>
          <td style="font-size: 9pt;" class="headColor">Total Taxable value</td>
          <td style="font-size: 9pt;" class="headColor">Total Integrated Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total Central Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total State/UT Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total Cess</td>
        </tr>
        <tr>
     	<td align="right">554</td>
     	<td align="right">5555</td>
     	<td align="right">412</td>
     	<td align="right">455</td>
     	<td align="right">55</td>
     	<td align="right">32</td>
     	<td align="right">32</td>
     	</tr>
     </table>

     <h6>B2BA Invoices</h6>
     <table width="100%" border="1">
        <tr>
          <td style="font-size: 9pt;" class="headColor">No. of Records</td>
          <td style="font-size: 9pt;" class="headColor">Total Invoice value</td>
          <td style="font-size: 9pt;" class="headColor">Total Taxable value</td>
          <td style="font-size: 9pt;" class="headColor">Total Integrated Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total Central Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total State/UT Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total Cess</td>
        </tr>
        <tr>
     	<td align="right">32</td>
     	<td align="right">32</td>
     	<td align="right">43</td>
     	<td align="right">54</td>
     	<td align="right">54</td>
     	<td align="right">54</td>
     	<td align="right">54</td>
     	</tr>
     </table>

<h6>5A, 5B - B2C (Large) Invoices</h6>
     <table width="100%" border="1">
        <tr>
          <td style="font-size: 9pt;" class="headColor">No. of Records</td>
          <td style="font-size: 9pt;" class="headColor">Total Invoice value</td>
          <td style="font-size: 9pt;" class="headColor">Total Taxable value</td>
          <td style="font-size: 9pt;" class="headColor">Total Integrated Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total Cess</td>
        </tr>
     	<tr>
     	<td align="right">54</td>
     	<td align="right">546</td>
     	<td align="right">657</td>
     	<td align="right">76</td>
     	<td align="right">9898</td>
     	</tr>
     </table>
<h6>B2CLA</h6>
     <table width="100%" border="1">
        <tr>
          <td style="font-size: 9pt;" class="headColor">No. of Records</td>
          <td style="font-size: 9pt;" class="headColor">Total Invoice value</td>
          <td style="font-size: 9pt;" class="headColor">Total Taxable value</td>
          <td style="font-size: 9pt;" class="headColor">Total Integrated Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total Cess</td>
        </tr>
     	<tr>
     	<td align="right">6546</td>
     	<td align="right">767</td>
     	<td align="right">1</td>
     	<td align="right">1</td>
     	<td align="right">1</td>
     	</tr>
     </table>

 <h6>9B - Credit / Debit Notes (Registered) D</h6>
     <table width="100%" border="1">
        <tr>
          <td style="font-size: 9pt;" class="headColor">No. of Records</td>
          <td style="font-size: 9pt;" class="headColor">Total Invoice value</td>
          <td style="font-size: 9pt;" class="headColor">Total Taxable value</td>
          <td style="font-size: 9pt;" class="headColor">Total Integrated Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total Central Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total State/UT Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total Cess</td>
        </tr>
     	<tr>
     	<td align="right">1</td>
     	<td align="right">1</td>
     	<td align="right">1</td>
     	<td align="right">32</td>
     	<td align="right">45</td>
     	<td align="right">545</td>
     	<td align="right">564</td>
     	</tr>
     </table>
 
 
 
 <h6>CDNRA</h6>
     <table width="100%" border="1">
        <tr>
          <td style="font-size: 9pt;" class="headColor">No. of Records</td>
          <td style="font-size: 9pt;" class="headColor">Total Invoice value</td>
          <td style="font-size: 9pt;" class="headColor">Total Taxable value</td>
          <td style="font-size: 9pt;" class="headColor">Total Integrated Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total Central Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total State/UT Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total Cess</td>
        </tr>
     	<tr>
     	<td align="right">65</td>
     	<td align="right">65</td>
     	<td align="right">65</td>
     	<td align="right">65</td>
     	<td align="right">65</td>
     	<td align="right">65</td>
     	<td align="right">65</td>
     	</tr>
     </table>

 <h6>9B - Credit / Debit Notes (Unregistered)</h6>
     <table width="100%" border="1">
        <tr>
          <td style="font-size: 9pt;" class="headColor">No. of Records</td>
          <td style="font-size: 9pt;" class="headColor">Total Invoice value</td>
          <td style="font-size: 9pt;" class="headColor">Total Taxable value</td>
          <td style="font-size: 9pt;" class="headColor">Total Integrated Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total Cess</td>
        </tr>
     	<tr>
     <td align="right">65</td>
     <td align="right">65</td>
     <td align="right">65</td>
     <td align="right">65</td>
     <td align="right">65</td>
     <td align="right">65</td>
     	</tr>
     </table>

 <h6>CDNURA</h6>
     <table width="100%" border="1">
        <tr>
          <td style="font-size: 9pt;" class="headColor">No. of Records</td>
          <td style="font-size: 9pt;" class="headColor">Total Invoice value</td>
          <td style="font-size: 9pt;" class="headColor">Total Taxable value</td>
          <td style="font-size: 9pt;" class="headColor">Total Integrated Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total Cess</td>
        </tr>
     	<tr>
     	
<td align="right">65</td>
<td align="right">65</td>
<td align="right">65</td>
<td align="right">65</td>
<td align="right">65</td>
     	</tr>
     </table>

 <h6>6A - Exports Invoices</h6>
     <table width="100%" border="1">
        <tr>
          <td style="font-size: 9pt;" class="headColor">No. of Records</td>
          <td style="font-size: 9pt;" class="headColor">Total Invoice value</td>
          <td style="font-size: 9pt;" class="headColor">Total Taxable value</td>
          <td style="font-size: 9pt;" class="headColor">Total Integrated Tax</td>
        </tr>
     	<tr>
			
<td align="right">65</td>
<td align="right">65</td>
<td align="right">65</td>
<td align="right">65</td>
     	</tr>
     </table>

 <h6>6A - Exports Invoices</h6>
     <table width="100%" border="1">
        <tr>
          <td style="font-size: 9pt;" class="headColor">No. of Records</td>
          <td style="font-size: 9pt;" class="headColor">Total Invoice value</td>
          <td style="font-size: 9pt;" class="headColor">Total Taxable value</td>
          <td style="font-size: 9pt;" class="headColor">Total Integrated Tax</td>
        </tr>
     	<tr>
		<td align="right">65</td>
		<td align="right">65</td>
		<td align="right">65</td>
		<td align="right">65</td>
     	</tr>
     </table>

 <h6>7 - B2C (Others) </h6>
     <table width="100%" border="1">
        <tr>
          <td style="font-size: 9pt;" class="headColor">No. of Records</td>
          <td style="font-size: 9pt;" class="headColor">Total Invoice value</td>
          <td style="font-size: 9pt;" class="headColor">Total Taxable value</td>
          <td style="font-size: 9pt;" class="headColor">Total Integrated Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total Central Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total State/UT Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total Cess</td>
        </tr>
     	<tr>
     	<td align="right">22</td>
     	<td align="right">65</td>
     	<td align="right">65</td>
     	<td align="right">65</td>
     	<td align="right">65</td>
     	<td align="right">65</td>
     	<td align="right">65</td>
     	</tr>
     </table>

 <h6>B2CSA</h6>
     <table width="100%" border="1">
        <tr>
          <td style="font-size: 9pt;" class="headColor">No. of Records</td>
          <td style="font-size: 9pt;" class="headColor">Total Invoice value</td>
          <td style="font-size: 9pt;" class="headColor">Total Taxable value</td>
          <td style="font-size: 9pt;" class="headColor">Total Integrated Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total Central Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total State/UT Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total Cess</td>
        </tr>
     	<tr>
     <td align="right">65</td>
     <td align="right">65</td>
     <td align="right">65</td>
     <td align="right">65</td>
     <td align="right">65</td>
     <td align="right">65</td>
     <td align="right">65</td>
     	</tr>
     </table>


     
          <h6>11A(1), 11A(2) - Tax Liability (Advances Received)</h6>
     <table width="100%" border="1">
        <tr>
          <td style="font-size: 9pt;" class="headColor">No. of Records</td>
          <td style="font-size: 9pt;" class="headColor">Total Invoice value</td>
          <td style="font-size: 9pt;" class="headColor">Total Taxable value</td>
          <td style="font-size: 9pt;" class="headColor">Total Integrated Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total Central Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total State/UT Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total Cess</td>
        </tr>
     	<tr>
     
<td align="right">65</td>
<td align="right">65</td>
<td align="right">65</td>
<td align="right">65</td>
<td align="right">65</td>
<td align="right">65</td>
<td align="right">65</td>
     	</tr>
     </table>

          <h6>ATA</h6>
     <table width="100%" border="1">
        <tr>
          <td style="font-size: 9pt;" class="headColor">No. of Records</td>
          <td style="font-size: 9pt;" class="headColor">Total Invoice value</td>
          <td style="font-size: 9pt;" class="headColor">Total Taxable value</td>
          <td style="font-size: 9pt;" class="headColor">Total Integrated Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total Central Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total State/UT Tax</td>
          <td style="font-size: 9pt;" class="headColor">Total Cess</td>
        </tr>
     	<tr>
     <td align="right">65</td>
     <td align="right">65</td>
     <td align="right">65</td>
     <td align="right">65</td>
     <td align="right">65</td>
     <td align="right">65</td>
     <td align="right">65</td>
     	</tr>
     </table>

     
      </div>
    </body>
</html>