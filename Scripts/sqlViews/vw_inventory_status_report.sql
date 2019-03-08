-- View: vw_inventory_status_report

-- DROP VIEW vw_inventory_status_report;

CREATE OR REPLACE VIEW vw_inventory_status_report AS 
 SELECT vi.product_id,
    vi.product_no,
    vi.plant_id,
    vi.plant_name,
    p.description AS product_description,
    pt.description AS product_group_description,
    u.uom_name,
    vi.instock_quantity
   FROM vw_inventory_product_quantity vi
     JOIN tbl_inventory_product p ON vi.product_id = p.product_id
     JOIN tbl_inventory_product_type pt ON pt.product_type_id = p.product_type_id
     JOIN tbl_inventory_uom u ON u.uom_id = p.inventory_uom_id;

ALTER TABLE vw_inventory_status_report
  OWNER TO smerp_dev;
