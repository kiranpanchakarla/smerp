-- View: vw_invoice_lineitems_amount

-- DROP VIEW vw_invoice_lineitems_amount;

CREATE OR REPLACE VIEW vw_invoice_lineitems_amount AS 
 SELECT ih.id,
    ih.doc_number AS invoice_number,
    COALESCE(ih.freight, 0::numeric::double precision) AS freight,
    COALESCE(ih.total_discount, 0::double precision) AS total_discount,
    il.product_id,
    il.product_number,
    il.description,
    il.uom,
    il.sku_quantity,
    il.product_group,
    il.hsn,
    il.warehouse,
    il.required_quantity AS inv_quantity,
    COALESCE(cm.required_quantity, 0::bigint) AS creditmemo_quantity,
    il.required_quantity - COALESCE(cm.required_quantity, 0::bigint) AS inv_final_quantity,
    il.tax_code,
    il.tax_description,
    il.unit_price,
    ((il.required_quantity - COALESCE(cm.required_quantity, 0::bigint))::double precision * il.unit_price * COALESCE(il.tax_code, 0::numeric::double precision) / 100::double precision)::numeric(20,2) AS inv_product_tax,
    ((il.required_quantity - COALESCE(cm.required_quantity, 0::bigint))::double precision * il.unit_price)::numeric(20,2) AS inv_product_amount,
    (il.required_quantity - COALESCE(cm.required_quantity, 0::bigint))::double precision * il.unit_price * (100::double precision - COALESCE(ih.total_discount, 0::numeric::double precision)) / 100::double precision + (il.required_quantity - COALESCE(cm.required_quantity, 0::bigint))::double precision * il.unit_price * COALESCE(il.tax_code, 0::numeric::double precision) / 100::double precision AS inv_amount_tax,
    (il.required_quantity - COALESCE(cm.required_quantity, 0::bigint))::double precision * il.unit_price + (il.required_quantity - COALESCE(cm.required_quantity, 0::bigint))::double precision * il.unit_price * COALESCE(il.tax_code, 0::numeric::double precision) / 100::double precision AS inv_amount_tax_before_discount
   FROM tbl_invoice ih
     JOIN tbl_invoice_lineitems il ON ih.id = il.inv_id
     LEFT JOIN ( SELECT cmh.inv_id,
            cmh.status,
            cml.product_id,
            sum(cml.required_quantity) AS required_quantity
           FROM tbl_credit_memo cmh
             LEFT JOIN tbl_credit_memo_lineitems cml ON cmh.id = cml.cre_id
          WHERE cmh.status::text = 'Approved'::text
          GROUP BY cmh.inv_id, cmh.status, cml.product_id) cm ON cm.inv_id = ih.id AND cm.product_id = il.product_id
  ORDER BY ih.id;

ALTER TABLE vw_invoice_lineitems_amount
  OWNER TO smerp_dev;
