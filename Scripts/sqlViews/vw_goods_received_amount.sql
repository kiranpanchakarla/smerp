-- View: vw_goods_received_amount

-- DROP VIEW vw_goods_received_amount;

CREATE OR REPLACE VIEW vw_goods_received_amount AS 
 SELECT grl.id,
    grl.doc_number,
    grl.freight,
    grl.total_discount,
    sum(grl.product_tax) AS total_gr_amount_product_tax,
    sum(grl.product_cost) AS total_gr_amount_product_cost,
    (sum(grl.product_cost_tax_without_discount)::double precision + COALESCE(grl.freight, 0::numeric::double precision))::numeric(20,2) AS total_gr_amount_before_discount,
    (sum(grl.product_cost_tax)::double precision + COALESCE(grl.freight, 0::numeric::double precision))::numeric(20,2) AS total_gr_amount_after_discount,
    round((sum(grl.product_cost_tax)::double precision + COALESCE(grl.freight, 0::numeric::double precision))::numeric(20,2)) AS total_gr_amount_after_discount_rounding
   FROM vw_goods_received_lineitems_amount grl
  GROUP BY grl.id, grl.doc_number, grl.freight, grl.total_discount;

ALTER TABLE vw_goods_received_amount
  OWNER TO smerp_dev;
