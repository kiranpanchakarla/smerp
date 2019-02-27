-- View: vw_purchase_order_amount_vendor

-- DROP VIEW vw_purchase_order_amount_vendor;

CREATE OR REPLACE VIEW vw_purchase_order_amount_vendor AS 
 SELECT v.vendor_id,
    av.name,
    sum(v.freight)::numeric(20,2) AS freight_sum,
    sum(v.total_amount_before_discount)::numeric(20,2) AS total_amount_before_discount_sum,
    sum(v.total_amount)::numeric(20,2) AS total_amount_sum
   FROM ( SELECT po.id,
            po.vendor_id,
            po.doc_number,
            po.freight,
            po.total_discount,
            sum(po.product_tax) AS tax_amount,
            sum(po.product_cost_tax_withoutdiscount) AS total_amount_before_discount,
            sum(po.product_cost_tax) AS total_amount
           FROM ( SELECT h.id,
                    h.doc_number,
                    h.vendor_id,
                    COALESCE(h.freight, 0::double precision) AS freight,
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
          GROUP BY po.id, po.vendor_id, po.doc_number, po.freight, po.total_discount) v
     JOIN tbl_admin_vendor av ON v.vendor_id = av.vendor_id
  GROUP BY v.vendor_id, av.name;

ALTER TABLE vw_purchase_order_amount_vendor
  OWNER TO smerp_dev;
