-- View: vw_inv_stock_qty_report

-- DROP VIEW vw_inv_stock_qty_report;

CREATE OR REPLACE VIEW vw_inv_stock_qty_report AS 
 SELECT vw.product_id,
    vw.product_no,
    vw.plant_id,
    vw.plant_name,
    vw.product_description,
    vw.product_group_description,
    vw.uom_name,
    vw.instock_quantity::double precision AS instock_quantity,
    prod.product_cost,
    COALESCE(prod.product_cost * vw.instock_quantity::double precision, 0::double precision) AS stock_value
   FROM vw_inventory_status_report vw
     JOIN tbl_inventory_product prod ON vw.product_id = prod.product_id
  ORDER BY vw.product_id;

ALTER TABLE vw_inv_stock_qty_report
  OWNER TO smerp_dev;
