package aop;

public class GoodsDTO {

	public String g_idx;
	public String goods_name;
	public String goods_price;
	public String p_code;
	
	public GoodsDTO() {
		// TODO Auto-generated constructor stub
	}

	public GoodsDTO(String g_idx, String goods_name, String goods_price, String p_code) {
		super();
		this.g_idx = g_idx;
		this.goods_name = goods_name;
		this.goods_price = goods_price;
		this.p_code = p_code;
	}

	public String getG_idx() {
		return g_idx;
	}

	public void setG_idx(String g_idx) {
		this.g_idx = g_idx;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}

	public String getP_code() {
		return p_code;
	}

	public void setP_code(String p_code) {
		this.p_code = p_code;
	}
	
}
