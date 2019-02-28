-- View: vw_invoice_amount

-- DROP VIEW vw_invoice_amount;

CREATE OR REPLACE VIEW vw_invoice_amount AS 
 SELECT inv.id,
    inv.invoice_number,
    inv.freight,
    inv.total_discount,
    sum(inv.inv_product_tax)::numeric(20,2) AS total_inv_amount_product_tax,
    sum(inv.inv_product_amount)::numeric(20,2) AS total_inv_amount_product,
    sum(inv.inv_amount_tax_before_discount)::numeric(20,2) AS total_inv_amount_before_discount,
    sum(inv.inv_amount_tax)::numeric(20,2) AS total_inv_amount_after_discount,
    sum(inv.inv_amount_tax)::numeric(20,2)::numeric(20,0) AS total_inv_amount_after_discount_rounding
   FROM vw_invoice_lineitems_amount inv
  GROUP BY inv.id, inv.invoice_number, inv.freight, inv.total_discount;

ALTER TABLE vw_invoice_amount
  OWNER TO smerp_dev;
