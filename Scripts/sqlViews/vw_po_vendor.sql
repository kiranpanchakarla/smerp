-- View: vw_po_vendor

-- DROP VIEW vw_po_vendor;

CREATE OR REPLACE VIEW vw_po_vendor AS 
 SELECT po.vendor_id,
    v.vendor_code,
    v.name,
    sum(po.product_cost_tax)::numeric(20,2) AS total_amount,
    date(po.created_at) AS po_date
   FROM ( SELECT h.id,
            h.vendor_id,
            h.doc_number,
            h.created_at,
            COALESCE(h.freight, 0::numeric::double precision) AS freight,
            COALESCE(h.total_discount, 0::double precision) AS total_discount,
            l.product_number,
            l.product_id,
            l.required_quantity,
            l.tax_code,
            l.unit_price,
            l.required_quantity::double precision * l.unit_price * COALESCE(l.tax_code, 0::numeric::double precision) / 100::double precision AS product_tax,
            l.required_quantity::double precision * l.unit_price * (100::double precision - COALESCE(h.total_discount, 0::numeric::double precision)) / 100::double precision + l.required_quantity::double precision * l.unit_price * COALESCE(l.tax_code, 0::numeric::double precision) / 100::double precision + COALESCE(h.freight, 0::numeric::double precision) AS product_cost_tax,
            l.required_quantity::double precision * l.unit_price + l.required_quantity::double precision * l.unit_price * COALESCE(l.tax_code, 0::numeric::double precision) / 100::double precision + COALESCE(h.freight, 0::numeric::double precision) AS product_cost_tax_withoutdiscount
           FROM tbl_purchase_order h
             JOIN tbl_purchase_order_lineitems l ON h.id = l.po_id) po
     JOIN tbl_admin_vendor v ON v.vendor_id = po.vendor_id
  GROUP BY po.vendor_id, v.vendor_code, v.name, date(po.created_at)
  ORDER BY po.vendor_id;

ALTER TABLE vw_po_vendor
  OWNER TO smerp_dev;
