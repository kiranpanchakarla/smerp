-- View: vw_vendor_wise_inv_report

-- DROP VIEW vw_vendor_wise_inv_report;

CREATE OR REPLACE VIEW vw_vendor_wise_inv_report AS 
 SELECT po.vendor_id,
    v.vendor_code,
    v.name,
    sum(po.inv_amount_tax)::numeric(20,2) AS total_amount,
    date(po.created_at) AS created_date
   FROM ( SELECT ih.id,
            ih.vendor_id,
            ih.created_at,
            (il.required_quantity - COALESCE(cm.required_quantity, 0::bigint))::double precision * il.unit_price * (100::double precision - COALESCE(ih.total_discount, 0::numeric::double precision)) / 100::double precision + (il.required_quantity - COALESCE(cm.required_quantity, 0::bigint))::double precision * il.unit_price * COALESCE(il.tax_code, 0::numeric::double precision) / 100::double precision AS inv_amount_tax
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
          ORDER BY ih.id) po
     JOIN tbl_admin_vendor v ON v.vendor_id = po.vendor_id
  GROUP BY po.vendor_id, v.vendor_code, v.name, date(po.created_at)
  ORDER BY po.vendor_id;

ALTER TABLE vw_vendor_wise_inv_report
  OWNER TO smerp_dev;
