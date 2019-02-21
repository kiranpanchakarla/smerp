-- View: vw_invoice_amount

-- DROP VIEW vw_invoice_amount;

CREATE OR REPLACE VIEW vw_invoice_amount AS 
 SELECT inv.id,
    inv.invoice_number,
    inv.freight,
    inv.total_discount,
    sum(inv.inv_product_tax) AS total_inv_amount_product_tax,
    sum(inv.inv_amount_tax) AS total_inv_amount_before_discount,
    round((sum(inv.inv_amount_tax) - sum(inv.inv_amount_tax) * inv.total_discount / 100::double precision + inv.freight::double precision)::numeric, 2) AS total_inv_amount_after_discount,
    round(round((sum(inv.inv_amount_tax) - sum(inv.inv_amount_tax) * inv.total_discount / 100::double precision + inv.freight::double precision)::numeric, 2), 0) AS total_inv_amount_after_discount_rounding
   FROM ( SELECT ih.id,
            ih.doc_number AS invoice_number,
            COALESCE(ih.freight, 0::numeric) AS freight,
            COALESCE(ih.total_discount, 0::double precision) AS total_discount,
            il.product_id,
            il.product_number,
            il.required_quantity AS inv_quantity,
            COALESCE(cm.required_quantity, 0::bigint) AS creditmemo_quantity,
            il.required_quantity - COALESCE(cm.required_quantity, 0::bigint) AS inv_final_quantity,
            il.tax_code,
            il.unit_price,
            (il.required_quantity - COALESCE(cm.required_quantity, 0::bigint))::double precision * il.unit_price * il.tax_code / 100::double precision AS inv_product_tax,
            (il.required_quantity - COALESCE(cm.required_quantity, 0::bigint))::double precision * il.unit_price + (il.required_quantity - COALESCE(cm.required_quantity, 0::bigint))::double precision * il.unit_price * il.tax_code / 100::double precision AS inv_amount_tax
           FROM tbl_invoice ih
             JOIN tbl_invoice_lineitems il ON ih.id = il.inv_id
             LEFT JOIN ( SELECT cmh.inv_id,
                    cmh.status,
                    cml.product_id,
                    sum(cml.required_quantity) AS required_quantity
                   FROM tbl_credit_memo cmh
                     LEFT JOIN tbl_credit_memo_lineitems cml ON cmh.id = cml.cre_id
                  WHERE cmh.status::text = 'Approved'::text
                  GROUP BY cmh.inv_id, cmh.status, cml.product_id) cm ON cm.inv_id = ih.id AND cm.product_id = il.product_id) inv
  GROUP BY inv.id, inv.invoice_number, inv.freight, inv.total_discount;

ALTER TABLE vw_invoice_amount
  OWNER TO smerp_dev;
