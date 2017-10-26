package test.bwie.myapplication.bean;


import java.util.List;

/**
 * Created by 蒋六六 on 2017/10/25.
 */

public class ShopLiBean {
  public String sellerName;
    public List<DataBean> list;
    public ShopLiBean(String sellerName,List<DataBean> list){
        this.sellerName=sellerName;
        this.list=list;
    }

    public static class DataBean{
        public String producrName;
        public double price;
        public boolean isSelect;
        public int text_sum;
        public DataBean(String producrName,double price,boolean isSelect,int text_sum){
            this.producrName=producrName;
            this.price=price;
            this.isSelect=isSelect;
            this.text_sum=text_sum;
        }



    }
}
