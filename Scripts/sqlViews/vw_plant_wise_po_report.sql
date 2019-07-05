-- View: vw_plant_wise_po_report

-- DROP VIEW vw_plant_wise_po_report;

CREATE OR REPLACE VIEW vw_plant_wise_po_report AS 
 SELECT pol.warehouse,
    pl.plant_name,
    sum(po.product_cost_tax)::numeric(20,2) AS total_amount,
    date(po.created_at) AS created_date
   FROM ( SELECT h.id,
            h.created_at,
            l.required_quantity::double precision * l.unit_price * (100::double precision - COALESCE(h.total_discount, 0::numeric::double precision)) / 100::double precision + l.required_quantity::double precision * l.unit_price * COALESCE(l.tax_code, 0::numeric::double precision) / 100::double precision + COALESCE(h.freight, 0::numeric::double precision) AS product_cost_tax
           FROM tbl_purchase_order h
             JOIN tbl_purchase_order_lineitems l ON h.id = l.po_id) po
     JOIN tbl_purchase_order_lineitems pol ON po.id = pol.po_id
     JOIN tbl_admin_plant pl ON pl.plant_id = pol.warehouse
  GROUP BY pol.warehouse, pl.plant_name, date(po.created_at)
  ORDER BY pol.warehouse;

ALTER TABLE vw_plant_wise_po_report
  OWNER TO smerp_dev;
